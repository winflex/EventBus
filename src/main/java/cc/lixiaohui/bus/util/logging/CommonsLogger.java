package cc.lixiaohui.bus.util.logging;

import org.apache.commons.logging.Log;


/**
 * 这里组合apache commons-loggings的Logger, 所有打日志实现都交由commons-loggings去做
 * 
 * @author lixiaohui
 * @date 2017年3月15日
 */
public class CommonsLogger extends AbstractInternalLogger {

	private static final long serialVersionUID = 8647838678388394885L;

	private final transient Log logger;

	public CommonsLogger(Log logger, String name) {
		super(name);
		if (logger == null) {
			throw new NullPointerException("logger");
		}
		this.logger = logger;
	}

	@Override
	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	@Override
	public void trace(String msg) {
		logger.trace(msg);
	}

	@Override
	public void trace(String format, Object arg) {
		if (logger.isTraceEnabled()) {
			FormattingTuple ft = MessageFormatter.format(format, arg);
			logger.trace(ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void trace(String format, Object argA, Object argB) {
		if (logger.isTraceEnabled()) {
			FormattingTuple ft = MessageFormatter.format(format, argA, argB);
			logger.trace(ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void trace(String format, Object... arguments) {
		if (logger.isTraceEnabled()) {
			FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
			logger.trace(ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void trace(String msg, Throwable t) {
		logger.trace(msg, t);
	}

	@Override
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	@Override
	public void debug(String msg) {
		logger.debug(msg);
	}

	@Override
	public void debug(String format, Object arg) {
		if (logger.isDebugEnabled()) {
			FormattingTuple ft = MessageFormatter.format(format, arg);
			logger.debug(ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void debug(String format, Object argA, Object argB) {
		if (logger.isDebugEnabled()) {
			FormattingTuple ft = MessageFormatter.format(format, argA, argB);
			logger.debug(ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void debug(String format, Object... arguments) {
		if (logger.isDebugEnabled()) {
			FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
			logger.debug(ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void debug(String msg, Throwable t) {
		logger.debug(msg, t);
	}

	@Override
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	@Override
	public void info(String msg) {
		logger.info(msg);
	}

	@Override
	public void info(String format, Object arg) {
		if (logger.isInfoEnabled()) {
			FormattingTuple ft = MessageFormatter.format(format, arg);
			logger.info(ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void info(String format, Object argA, Object argB) {
		if (logger.isInfoEnabled()) {
			FormattingTuple ft = MessageFormatter.format(format, argA, argB);
			logger.info(ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void info(String format, Object... arguments) {
		if (logger.isInfoEnabled()) {
			FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
			logger.info(ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void info(String msg, Throwable t) {
		logger.info(msg, t);
	}

	@Override
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	@Override
	public void warn(String msg) {
		logger.warn(msg);
	}

	@Override
	public void warn(String format, Object arg) {
		if (logger.isWarnEnabled()) {
			FormattingTuple ft = MessageFormatter.format(format, arg);
			logger.warn(ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void warn(String format, Object argA, Object argB) {
		if (logger.isWarnEnabled()) {
			FormattingTuple ft = MessageFormatter.format(format, argA, argB);
			logger.warn(ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void warn(String format, Object... arguments) {
		if (logger.isWarnEnabled()) {
			FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
			logger.warn(ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void warn(String msg, Throwable t) {
		logger.warn(msg, t);
	}

	@Override
	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	@Override
	public void error(String msg) {
		logger.error(msg);
	}

	@Override
	public void error(String format, Object arg) {
		if (logger.isErrorEnabled()) {
			FormattingTuple ft = MessageFormatter.format(format, arg);
			logger.error(ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void error(String format, Object argA, Object argB) {
		if (logger.isErrorEnabled()) {
			FormattingTuple ft = MessageFormatter.format(format, argA, argB);
			logger.error(ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void error(String format, Object... arguments) {
		if (logger.isErrorEnabled()) {
			FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
			logger.error(ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void error(String msg, Throwable t) {
		logger.error(msg, t);
	}
}
