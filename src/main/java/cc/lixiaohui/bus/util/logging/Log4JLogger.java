package cc.lixiaohui.bus.util.logging;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * 这里组合Log4J的Logger, 所有打日志实现都交由Log4J去做
 * 
 */
public class Log4JLogger extends AbstractInternalLogger {

	private static final long serialVersionUID = 2851357342488183058L;

	final transient Logger logger;
	static final String FQCN = Log4JLogger.class.getName();

	// Does the log4j version in use recognize the TRACE level?
	// The trace level was introduced in log4j 1.2.12.
	final boolean traceCapable;

	public Log4JLogger(Logger logger) {
		super(logger.getName());
		this.logger = logger;
		traceCapable = isTraceCapable();
	}

	private boolean isTraceCapable() {
		try {
			logger.isTraceEnabled();
			return true;
		} catch (NoSuchMethodError e) {
			return false;
		}
	}

	@Override
	public boolean isTraceEnabled() {
		if (traceCapable) {
			return logger.isTraceEnabled();
		} else {
			return logger.isDebugEnabled();
		}
	}

	@Override
	public void trace(String msg) {
		logger.log(FQCN, traceCapable ? Level.TRACE : Level.DEBUG, msg, null);
	}

	@Override
	public void trace(String format, Object arg) {
		if (isTraceEnabled()) {
			FormattingTuple ft = MessageFormatter.format(format, arg);
			logger.log(FQCN, traceCapable ? Level.TRACE : Level.DEBUG, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void trace(String format, Object argA, Object argB) {
		if (isTraceEnabled()) {
			FormattingTuple ft = MessageFormatter.format(format, argA, argB);
			logger.log(FQCN, traceCapable ? Level.TRACE : Level.DEBUG, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void trace(String format, Object... arguments) {
		if (isTraceEnabled()) {
			FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
			logger.log(FQCN, traceCapable ? Level.TRACE : Level.DEBUG, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void trace(String msg, Throwable t) {
		logger.log(FQCN, traceCapable ? Level.TRACE : Level.DEBUG, msg, t);
	}

	@Override
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	@Override
	public void debug(String msg) {
		logger.log(FQCN, Level.DEBUG, msg, null);
	}

	@Override
	public void debug(String format, Object arg) {
		if (logger.isDebugEnabled()) {
			FormattingTuple ft = MessageFormatter.format(format, arg);
			logger.log(FQCN, Level.DEBUG, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void debug(String format, Object argA, Object argB) {
		if (logger.isDebugEnabled()) {
			FormattingTuple ft = MessageFormatter.format(format, argA, argB);
			logger.log(FQCN, Level.DEBUG, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void debug(String format, Object... arguments) {
		if (logger.isDebugEnabled()) {
			FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
			logger.log(FQCN, Level.DEBUG, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void debug(String msg, Throwable t) {
		logger.log(FQCN, Level.DEBUG, msg, t);
	}

	@Override
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	@Override
	public void info(String msg) {
		logger.log(FQCN, Level.INFO, msg, null);
	}

	@Override
	public void info(String format, Object arg) {
		if (logger.isInfoEnabled()) {
			FormattingTuple ft = MessageFormatter.format(format, arg);
			logger.log(FQCN, Level.INFO, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void info(String format, Object argA, Object argB) {
		if (logger.isInfoEnabled()) {
			FormattingTuple ft = MessageFormatter.format(format, argA, argB);
			logger.log(FQCN, Level.INFO, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void info(String format, Object... argArray) {
		if (logger.isInfoEnabled()) {
			FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
			logger.log(FQCN, Level.INFO, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void info(String msg, Throwable t) {
		logger.log(FQCN, Level.INFO, msg, t);
	}

	@Override
	public boolean isWarnEnabled() {
		return logger.isEnabledFor(Level.WARN);
	}

	@Override
	public void warn(String msg) {
		logger.log(FQCN, Level.WARN, msg, null);
	}

	@Override
	public void warn(String format, Object arg) {
		if (logger.isEnabledFor(Level.WARN)) {
			FormattingTuple ft = MessageFormatter.format(format, arg);
			logger.log(FQCN, Level.WARN, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void warn(String format, Object argA, Object argB) {
		if (logger.isEnabledFor(Level.WARN)) {
			FormattingTuple ft = MessageFormatter.format(format, argA, argB);
			logger.log(FQCN, Level.WARN, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void warn(String format, Object... argArray) {
		if (logger.isEnabledFor(Level.WARN)) {
			FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
			logger.log(FQCN, Level.WARN, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void warn(String msg, Throwable t) {
		logger.log(FQCN, Level.WARN, msg, t);
	}

	@Override
	public boolean isErrorEnabled() {
		return logger.isEnabledFor(Level.ERROR);
	}

	@Override
	public void error(String msg) {
		logger.log(FQCN, Level.ERROR, msg, null);
	}

	@Override
	public void error(String format, Object arg) {
		if (logger.isEnabledFor(Level.ERROR)) {
			FormattingTuple ft = MessageFormatter.format(format, arg);
			logger.log(FQCN, Level.ERROR, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void error(String format, Object argA, Object argB) {
		if (logger.isEnabledFor(Level.ERROR)) {
			FormattingTuple ft = MessageFormatter.format(format, argA, argB);
			logger.log(FQCN, Level.ERROR, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void error(String format, Object... argArray) {
		if (logger.isEnabledFor(Level.ERROR)) {
			FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
			logger.log(FQCN, Level.ERROR, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void error(String msg, Throwable t) {
		logger.log(FQCN, Level.ERROR, msg, t);
	}
}
