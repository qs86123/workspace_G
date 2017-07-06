package com.myself.edu.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.LogManager;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.LoggerRepository;
import org.slf4j.Marker;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.Proxy;

public enum Logger {
	INS;
	Logger() {

        try {
            Enhancer eh = new Enhancer();
            eh.setSuperclass(org.apache.log4j.Logger.class);
            eh.setCallbackType(LogInterceptor.class);
            Class c = eh.createClass();
            Enhancer.registerCallbacks(c, new LogInterceptor[]{new LogInterceptor()});

            Constructor<org.apache.log4j.Logger> constructor = c.getConstructor(String.class);
            org.apache.log4j.Logger loggerProxy = constructor.newInstance(Logger.class.getName());

            LoggerRepository loggerRepository = LogManager.getLoggerRepository();
            LoggerFactory lf = ReflectionUtil.INS.getFieldValue(loggerRepository, "defaultFactory");
            Object loggerFactoryProxy = Proxy.newProxyInstance(
                LoggerFactory.class.getClassLoader(),
                new Class[]{LoggerFactory.class},
                new NewLoggerHandler(loggerProxy)
            );

            ReflectionUtil.INS.setFieldValue(loggerRepository, "defaultFactory", loggerFactoryProxy);
            logger = org.slf4j.LoggerFactory.getLogger(Logger.class.getName());
            ReflectionUtil.INS.setFieldValue(loggerRepository, "defaultFactory", lf);
        } catch (
            IllegalAccessException |
                NoSuchMethodException |
                InvocationTargetException |
                InstantiationException e) {
            throw new RuntimeException("初始化Logger失败", e);
        }
	}
	private org.slf4j.Logger logger;
    private final String FQCN = Logger.class.getName();

    private class LogInterceptor implements MethodInterceptor {
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            // 只拦截log方法。
            if (objects.length != 4 || !method.getName().equals("log"))
                return methodProxy.invokeSuper(o, objects);
            objects[0] = FQCN;
            return methodProxy.invokeSuper(o, objects);
        }
    }

    private class NewLoggerHandler implements InvocationHandler {
        private final org.apache.log4j.Logger proxyLogger;

        public NewLoggerHandler(org.apache.log4j.Logger proxyLogger) {
            this.proxyLogger = proxyLogger;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return proxyLogger;
        }
    }

    // 剩下的Logger需要封装的方法可以根据自己的需要来实现
    // 我个人认为slf4j的api足够好用了，所以大部分只是写了一些类似下面的代码
	public void error(String msg) {
		logger.error(msg);
	}

	public void error(String format, Object arg) {
		logger.error(format, arg);
	}

	public void error(String format, Object arg1, Object arg2) {
		logger.error(format, arg1, arg2);
	}

	public void error(String format, Object... arguments) {
		logger.error(format, arguments);
	}

	public void error(String msg, Throwable t) {
		logger.error(msg, t);
	}

	public boolean isErrorEnabled(Marker marker) {
		return logger.isErrorEnabled();
	}

	public void error(Marker marker, String msg) {
		logger.error(marker, msg);
	}

	public void error(Marker marker, String format, Object arg) {
		logger.error(marker, format, arg);
	}

	public void error(Marker marker, String format, Object arg1, Object arg2) {
		logger.error(marker, format, arg1, arg2);
	}

	public void error(Marker marker, String format, Object... arguments) {
		logger.error(marker, format, arguments);
	}

	public void error(Marker marker, String msg, Throwable t) {
		logger.error(marker, msg, t);
	}

	public void info(Marker marker, String msg) {
		logger.info(marker, msg);
	}

	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	public void info(Marker marker, String format, Object arg) {
		logger.info(marker, format, arg);
	}

	public void info(Marker marker, String format, Object arg1, Object arg2) {
		logger.info(marker, format, arg1, arg2);
	}

	public void info(Marker marker, String format, Object... arguments) {
		logger.info(marker, format, arguments);
	}

	public void info(Marker marker, String msg, Throwable t) {
		logger.info(marker, msg, t);
	}

	public void info(String msg) {
		logger.info(msg);
	}

	public void info(String format, Object arg) {
		logger.info(format, arg);
	}

	public void info(String format, Object arg1, Object arg2) {
		logger.info(format, arg1, arg2);
	}

	public void info(String format, Object... arguments) {
		logger.info(format, arguments);
	}

	public void info(String msg, Throwable t) {
		logger.info(msg, t);
	}

	public void debug(String msg) {
		logger.debug(msg);
	}

	public void debug(String format, Object arg) {
		logger.debug(format, arg);
	}

	public void debug(String format, Object arg1, Object arg2) {
		logger.debug(format, arg1, arg2);
	}

	public void debug(String format, Object... arguments) {
		logger.debug(format, arguments);
	}

	public void debug(String msg, Throwable t) {
		logger.debug(msg, t);
	}

	public boolean isDebugEnabled(Marker marker) {
		return logger.isDebugEnabled();
	}

	public void debug(Marker marker, String msg) {
		logger.debug(marker, msg);
	}

	public void debug(Marker marker, String format, Object arg) {
		logger.debug(marker, format, arg);
	}

	public void debug(Marker marker, String format, Object arg1, Object arg2) {
		logger.debug(marker, format, arg1, arg2);
	}

	public void debug(Marker marker, String format, Object... arguments) {
		logger.debug(marker, format, arguments);
	}

	public void debug(Marker marker, String msg, Throwable t) {
		logger.debug(marker, msg, t);
	}
	
	/**
     * Is the logger instance enabled for the WARN level?
     *
     * @return True if this Logger is enabled for the WARN level,
     *         false otherwise.
     */
    public boolean isWarnEnabled() {
    	return logger.isWarnEnabled();
    }

    public void warn(String msg) {
    	logger.warn(msg);
    }

    public void warn(String format, Object arg) {
    	logger.warn(format, arg);
    }

    public void warn(String format, Object... arguments) {
    	logger.warn(format, arguments);
    }

    public void warn(String format, Object arg1, Object arg2) {
    	logger.warn(format, arg1, arg2);
    }

    public void warn(String msg, Throwable t) {
    	logger.warn(msg, t);
    }

    public boolean isWarnEnabled(Marker marker) {
    	return logger.isWarnEnabled(marker);
    }

    public void warn(Marker marker, String msg) {
    	logger.warn(marker, msg);
    }

    public void warn(Marker marker, String format, Object arg) {
    	logger.warn(marker, format, arg);
    }

    public void warn(Marker marker, String format, Object arg1, Object arg2) {
    	logger.warn(marker, format, arg1, arg2);
    }

    public void warn(Marker marker, String format, Object... arguments) {
    	logger.warn(marker, format, arguments);
    }

    public void warn(Marker marker, String msg, Throwable t) {
    	logger.warn(marker, msg, t);
    }
}