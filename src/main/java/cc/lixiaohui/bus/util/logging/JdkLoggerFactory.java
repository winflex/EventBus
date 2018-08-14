package cc.lixiaohui.bus.util.logging;

import java.util.logging.Logger;

import cc.lixiaohui.bus.util.logging.InternalLogger;

public class JdkLoggerFactory extends InternalLoggerFactory {

	@Override
	public InternalLogger newInstance(String name) {
		return new JdkLogger(Logger.getLogger(name));
	}
}
