package cc.lixiaohui.bus.util.logging;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.LogFactory;

public class CommonsLoggerFactory extends InternalLoggerFactory {

	Map<String, InternalLogger> loggerMap = new HashMap<String, InternalLogger>();

	@Override
	public InternalLogger newInstance(String name) {
		return new CommonsLogger(LogFactory.getLog(name), name);
	}
}
