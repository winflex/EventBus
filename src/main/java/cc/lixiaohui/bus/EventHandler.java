package cc.lixiaohui.bus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

final class EventHandler {
    final Bus bus;
    final Object handlerTarget;
    final Method handlerMethod;
    

    EventHandler(Bus bus, Object target, Method handlerMethod) {
        this.handlerTarget = target;
        this.handlerMethod = handlerMethod;
        this.bus = bus;
    }

    void handleEvent(Object event) {
        try {
            handlerMethod.invoke(handlerTarget, new Object[] { event });
        } catch (IllegalAccessException e) {
            throw new Error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            bus.handleException(e);
        }
    }
}