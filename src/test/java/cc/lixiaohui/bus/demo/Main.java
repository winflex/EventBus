package cc.lixiaohui.bus.demo;

import java.util.concurrent.Executors;

import cc.lixiaohui.bus.Bus;
import cc.lixiaohui.bus.Subscribe;

public class Main {
    static Bus bus = Bus.builder().executeWith(Executors.newFixedThreadPool(4)).build();
	public static void main(String[] args) {
		bus.register(new EventHandler());
		bus.post(new StringEvent("a"));
		bus.post(new StringEvent("b"));
		bus.post(new StringEvent("c"));
		bus.post(new StringEvent("d"));
		bus.post(new StringEvent("e"));
	}
	
	public static class EventHandler {
	    
	    @Subscribe
	    public void handle(StringEvent event) {
	        System.out.println(event.getHelloMessage());
	    }
	    
	    @Subscribe
	    public void handle(IntEvent event) {
	        System.out.println(event.value);
	    }
	}

	
	public static class StringEvent {
	    
	    final String helloMessage;

	    public StringEvent(String helloMessage) {
	        super();
	        this.helloMessage = helloMessage;
	    }

	    public String getHelloMessage() {
	        return helloMessage;
	    }
	}
	
	public static class IntEvent {
	    final int value;

        public IntEvent(int value) {
            super();
            this.value = value;
        }
	}
}
