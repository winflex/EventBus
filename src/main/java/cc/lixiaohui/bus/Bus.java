package cc.lixiaohui.bus;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import cc.lixiaohui.bus.Dispatcher.AsyncDispatcher;
import cc.lixiaohui.bus.Dispatcher.SyncDispatcher;
import cc.lixiaohui.bus.util.logging.InternalLogger;
import cc.lixiaohui.bus.util.logging.InternalLoggerFactory;

public class Bus {

    public static final Builder builder() {
        return new Builder();
    }

    private final Map<Class<?>, Set<EventHandler>> eventType2Handlers = new HashMap<>();

    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    private final Dispatcher dispatcher;

    // configurable handlers
    private final ExceptionHandler exceptionHandler;
    private final LostEventHandler lostEventHandler;

    public Bus() {
        this(new SyncDispatcher(), LoggingHandler.INSTANCE, LoggingHandler.INSTANCE);
    }

    protected Bus(Dispatcher dispatcher, ExceptionHandler eHandler,
            LostEventHandler lHandler) {
        this.dispatcher = dispatcher;
        this.exceptionHandler = eHandler;
        this.lostEventHandler = lHandler;
    }

    public void post(Object event) {
        final Lock readLock = rwLock.readLock();
        readLock.lock();
        try {
            Set<EventHandler> handlers = eventType2Handlers.get(event.getClass());
            if (handlers == null || handlers.isEmpty()) {
                lostEventHandler.handleLostEvent(event);
            } else {
                dispatcher.dispatch(event, handlers);
            }
        } finally {
            readLock.unlock();
        }
    }

    public void register(Object listener) {
        Map<Class<?>, Set<EventHandler>> eventType2Handlers = findEventHandlers(listener);

        final Lock writeLock = rwLock.writeLock();
        writeLock.lock();
        try {
            eventType2Handlers.forEach((eventType, handlers) -> {
                if (this.eventType2Handlers.containsKey(eventType)) {
                    this.eventType2Handlers.get(eventType).addAll(handlers);
                } else {
                    this.eventType2Handlers.put(eventType, handlers);
                }
            });
        } finally {
            writeLock.unlock();
        }
    }

    public void unregister(Object listener) {
        Map<Class<?>, Set<EventHandler>> eventType2Handlers = findEventHandlers(listener);

        final Lock writeLock = rwLock.writeLock();
        writeLock.lock();
        try {
            eventType2Handlers.forEach((eventType, handlers) -> {
                if (this.eventType2Handlers.containsKey(eventType)) {
                    this.eventType2Handlers.get(eventType).removeAll(handlers);
                }
            });
        } finally {
            writeLock.unlock();
        }
    }

    private Map<Class<?>, Set<EventHandler>> findEventHandlers(Object listener) {
        Map<Class<?>, Set<EventHandler>> eventType2Handlers = new HashMap<>();
        Method[] methods = listener.getClass().getDeclaredMethods();
        for (Method method : methods) {
            // Not a handler method
            Subscribe subscribe = method.getAnnotation(Subscribe.class);
            if (subscribe == null) {
                continue;
            }

            Class<?>[] paramTypes = method.getParameterTypes();
            if (paramTypes.length != 1) {
                throw new RuntimeException(String.format(
                        "Subscribe method {}.{} must have one and only one parameter.",
                        listener.getClass().getName(), method.getName()));
            }

            EventHandler handler = new EventHandler(this, listener, method);
            Class<?> eventType = paramTypes[0];
            if (eventType2Handlers.containsKey(eventType)) {
                eventType2Handlers.get(eventType).add(handler);
            } else {
                Set<EventHandler> handlers = new HashSet<>();
                handlers.add(handler);
                eventType2Handlers.put(eventType, handlers);
            }
        }
        return eventType2Handlers;
    }

    void handleException(Throwable e) {
        exceptionHandler.handleException(e);
    }

    static class LoggingHandler implements ExceptionHandler, LostEventHandler {

        static final InternalLogger logger = InternalLoggerFactory
                .getInstance(LoggingHandler.class);

        static final LoggingHandler INSTANCE = new LoggingHandler();

        @Override
        public void handleException(Throwable cause) {
            logger.error(cause.getMessage(), cause);
        }

        @Override
        public void handleLostEvent(Object event) {
            logger.warn("Lost event: {}", event);
        }
    }

    public static class Builder {
        ExceptionHandler exceptionHandler = LoggingHandler.INSTANCE;
        LostEventHandler lostEventHandler = LoggingHandler.INSTANCE;
        Executor executor;

        public Builder handleExceptionWith(ExceptionHandler exceptionHandler) {
            this.exceptionHandler = exceptionHandler;
            return this;
        }

        public Builder handleLostEventWith(LostEventHandler lostEventHandler) {
            this.lostEventHandler = lostEventHandler;
            return this;
        }

        /**
         * 分发线程池, 需要用户自己维护, 如应用停止时需要正确关闭该线程池
         */
        public Builder executeWith(Executor executor) {
            this.executor = executor;
            return this;
        }

        public Bus build() {
            Dispatcher dispatcher = executor == null ? new SyncDispatcher()
                    : new AsyncDispatcher(executor);
            return new Bus(dispatcher, exceptionHandler, lostEventHandler);
        }
    }

    static class DirectExecutor implements Executor {
        static final DirectExecutor INSTANCE = new DirectExecutor();

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }
}
