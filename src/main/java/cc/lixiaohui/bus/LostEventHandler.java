package cc.lixiaohui.bus;

/**
 * 未被订阅的事件处理器
 */
public interface LostEventHandler {
    
    void handleLostEvent(Object event);
    
}
