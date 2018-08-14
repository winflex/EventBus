package cc.lixiaohui.bus.util.logging;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * 这里组合JdkLogger的Logger, 所有打日志实现都交由JdkLogger去做
 * 
 * @author lixiaohui
 * @date 2017年3月15日
 */
public class JdkLogger extends AbstractInternalLogger {

	private static final long serialVersionUID = -1767272577989225979L;

	static final String SELF = JdkLogger.class.getName();
	static final String SUPER = AbstractInternalLogger.class.getName();

	private static void fillCallerData(String callerFQCN, LogRecord record) {
		StackTraceElement[] steArray = new Throwable().getStackTrace();

		int selfIndex = -1;
		for (int i = 0; i < steArray.length; i++) {
			final String className = steArray[i].getClassName();
			if (className.equals(callerFQCN) || className.equals(SUPER)) {
				selfIndex = i;
				break;
			}
		}

		int found = -1;
		for (int i = selfIndex + 1; i < steArray.length; i++) {
			final String className = steArray[i].getClassName();
			if (!(className.equals(callerFQCN) || className.equals(SUPER))) {
				found = i;
				break;
			}
		}

		if (found != -1) {
			StackTraceElement ste = steArray[found];
			// setting the class name has the side effect of setting
			// the needToInferCaller variable to false.
			record.setSourceClassName(ste.getClassName());
			record.setSourceMethodName(ste.getMethodName());
		}
	}

	final transient Logger logger;

	public JdkLogger(Logger logger) {
		super(logger.getName());
		this.logger = logger;
	}

	@Override
	public boolean isTraceEnabled() {
		return logger.isLoggable(Level.FINEST);
	}

	@Override
	public void trace(String msg) {
		if (logger.isLoggable(Level.FINEST)) {
			log(SELF, Level.FINEST, msg, null);
		}
	}

	@Override
	public void trace(String format, Object arg) {
		if (logger.isLoggable(Level.FINEST)) {
			FormattingTuple ft = MessageFormatter.format(format, arg);
			log(SELF, Level.FINEST, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void trace(String format, Object argA, Object argB) {
		if (logger.isLoggable(Level.FINEST)) {
			FormattingTuple ft = MessageFormatter.format(format, argA, argB);
			log(SELF, Level.FINEST, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void trace(String format, Object... argArray) {
		if (logger.isLoggable(Level.FINEST)) {
			FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
			log(SELF, Level.FINEST, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void trace(String msg, Throwable t) {
		if (logger.isLoggable(Level.FINEST)) {
			log(SELF, Level.FINEST, msg, t);
		}
	}

	@Override
	public boolean isDebugEnabled() {
		return logger.isLoggable(Level.FINE);
	}

	@Override
	public void debug(String msg) {
		if (logger.isLoggable(Level.FINE)) {
			log(SELF, Level.FINE, msg, null);
		}
	}

	@Override
	public void debug(String format, Object arg) {
		if (logger.isLoggable(Level.FINE)) {
			FormattingTuple ft = MessageFormatter.format(format, arg);
			log(SELF, Level.FINE, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void debug(String format, Object argA, Object argB) {
		if (logger.isLoggable(Level.FINE)) {
			FormattingTuple ft = MessageFormatter.format(format, argA, argB);
			log(SELF, Level.FINE, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void debug(String format, Object... argArray) {
		if (logger.isLoggable(Level.FINE)) {
			FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
			log(SELF, Level.FINE, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void debug(String msg, Throwable t) {
		if (logger.isLoggable(Level.FINE)) {
			log(SELF, Level.FINE, msg, t);
		}
	}

	@Override
	public boolean isInfoEnabled() {
		return logger.isLoggable(Level.INFO);
	}

	@Override
	public void info(String msg) {
		if (logger.isLoggable(Level.INFO)) {
			log(SELF, Level.INFO, msg, null);
		}
	}

	@Override
	public void info(String format, Object arg) {
		if (logger.isLoggable(Level.INFO)) {
			FormattingTuple ft = MessageFormatter.format(format, arg);
			log(SELF, Level.INFO, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void info(String format, Object argA, Object argB) {
		if (logger.isLoggable(Level.INFO)) {
			FormattingTuple ft = MessageFormatter.format(format, argA, argB);
			log(SELF, Level.INFO, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void info(String format, Object... argArray) {
		if (logger.isLoggable(Level.INFO)) {
			FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
			log(SELF, Level.INFO, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void info(String msg, Throwable t) {
		if (logger.isLoggable(Level.INFO)) {
			log(SELF, Level.INFO, msg, t);
		}
	}

	@Override
	public boolean isWarnEnabled() {
		return logger.isLoggable(Level.WARNING);
	}

	@Override
	public void warn(String msg) {
		if (logger.isLoggable(Level.WARNING)) {
			log(SELF, Level.WARNING, msg, null);
		}
	}

	@Override
	public void warn(String format, Object arg) {
		if (logger.isLoggable(Level.WARNING)) {
			FormattingTuple ft = MessageFormatter.format(format, arg);
			log(SELF, Level.WARNING, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void warn(String format, Object argA, Object argB) {
		if (logger.isLoggable(Level.WARNING)) {
			FormattingTuple ft = MessageFormatter.format(format, argA, argB);
			log(SELF, Level.WARNING, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void warn(String format, Object... argArray) {
		if (logger.isLoggable(Level.WARNING)) {
			FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
			log(SELF, Level.WARNING, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void warn(String msg, Throwable t) {
		if (logger.isLoggable(Level.WARNING)) {
			log(SELF, Level.WARNING, msg, t);
		}
	}

	@Override
	public boolean isErrorEnabled() {
		return logger.isLoggable(Level.SEVERE);
	}

	@Override
	public void error(String msg) {
		if (logger.isLoggable(Level.SEVERE)) {
			log(SELF, Level.SEVERE, msg, null);
		}
	}

	@Override
	public void error(String format, Object arg) {
		if (logger.isLoggable(Level.SEVERE)) {
			FormattingTuple ft = MessageFormatter.format(format, arg);
			log(SELF, Level.SEVERE, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void error(String format, Object argA, Object argB) {
		if (logger.isLoggable(Level.SEVERE)) {
			FormattingTuple ft = MessageFormatter.format(format, argA, argB);
			log(SELF, Level.SEVERE, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void error(String format, Object... arguments) {
		if (logger.isLoggable(Level.SEVERE)) {
			FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
			log(SELF, Level.SEVERE, ft.getMessage(), ft.getThrowable());
		}
	}

	@Override
	public void error(String msg, Throwable t) {
		if (logger.isLoggable(Level.SEVERE)) {
			log(SELF, Level.SEVERE, msg, t);
		}
	}

	private void log(String callerFQCN, Level level, String msg, Throwable t) {
		// millis and thread are filled by the constructor
		LogRecord record = new LogRecord(level, msg);
		record.setLoggerName(name());
		record.setThrown(t);
		fillCallerData(callerFQCN, record);
		logger.log(record);
	}

}
