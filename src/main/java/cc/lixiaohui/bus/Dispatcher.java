package cc.lixiaohui.bus;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.Executor;

interface Dispatcher {

    void dispatch(Object event, Collection<EventHandler> handlers);

    /**
     * 同步消息分发器, 对于同一个线程来说, 先提交的事件先分发
     */
    static class SyncDispatcher implements Dispatcher {

        final ThreadLocal<Queue<Event>> threadQueue = ThreadLocal.withInitial(() -> {
            return new ArrayDeque<Event>();
        });

        final ThreadLocal<Boolean> threadDispatching = ThreadLocal.withInitial(() -> {
            return false;
        });

        @Override
        public void dispatch(Object event, Collection<EventHandler> handlers) {
            final Queue<Event> queue = threadQueue.get();
            queue.offer(new Event(event, handlers));

            if (!threadDispatching.get()) {
                threadDispatching.set(true);

                try {
                    Event nextEvent;
                    while ((nextEvent = queue.poll()) != null) {
                        final Event e = nextEvent;
                        e.handlers.forEach((handler -> handler.handleEvent(e.event)));
                    }
                } finally {
                    threadDispatching.remove();
                    threadQueue.remove();
                }
            }
        }

        static class Event {
            final Object event;
            final Collection<EventHandler> handlers;

            Event(Object event, Collection<EventHandler> handlers) {
                super();
                this.event = event;
                this.handlers = handlers;
            }
        }
    }

    static class AsyncDispatcher implements Dispatcher {
        private final Executor executor;

        AsyncDispatcher(Executor executor) {
            this.executor = executor;
        }

        @Override
        public void dispatch(Object event, Collection<EventHandler> handlers) {
            // 分开提交任务提高并发度
            handlers.forEach(handler -> {
                executor.execute(() -> handler.handleEvent(event));
            });
        }
    }
}
