import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.TypeVariable;

import com.sun.xml.internal.ws.wsdl.writer.document.ParamType;

public class AliasingInvocationHandler implements InvocationHandler {
	protected Object base;

	protected AliasingInvocationHandler() {

	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

		Class<?> proxyInterface = proxy.getClass().getInterfaces()[0];
		Alias classAlias = proxyInterface.getAnnotation(Alias.class);

		Alias methodAlias = method.getAnnotation(Alias.class);
		if (classAlias != null && methodAlias != null) {
			Class<?> baseClass = classAlias.clazz();
			Object parameters[] = new Object[args.length];
			Class<?> parameterTypes[] = new Class[args.length];

			for (int i = 0; i < args.length; i++) {
				Class<?> paramType = method.getParameterTypes()[i];
				Alias paramAlias = paramType.getAnnotation(Alias.class);
				if (paramAlias != null) {

					parameterTypes[i] = paramAlias.clazz();
					if (args[i] != null && isAliasingProxy(args[i])) {
						parameters[i] = unwrapProxy(args[i]);
					} else {
						parameters[i] = args[i];
					}
				} else {
					parameterTypes[i] = paramType;
					parameters[i] = args[i];
				}
			}

			Method baseMethod = baseClass.getMethod(methodAlias.methodName(),
					parameterTypes);

			Object returnValue = baseMethod.invoke(base, parameters);
			Class<?> returnType = method.getReturnType();
			Alias returnTypeAlias = returnType.getAnnotation(Alias.class);

			if (returnTypeAlias != null
					&& returnTypeAlias.clazz().equals(returnValue.getClass())
					&& returnType.isInterface()) {
				return createWrapper(returnType, returnValue);
			} else {
				return returnValue;
			}
		}
		return method.invoke(unwrapProxy(proxy), args);
	}

	public static <W> W createWrapper(Class<W> clazz, Object base) {
		AliasingInvocationHandler proxy;
		try {
			proxy = new AliasingInvocationHandler();
			proxy.wrap(base);
			return (W) Proxy.newProxyInstance(
					proxy.getClass().getClassLoader(), new Class[] { clazz },
					proxy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalStateException(e);
		}
	}

	public static boolean isAliasingProxy(Object proxy) {
		if (Proxy.isProxyClass(proxy.getClass())) {
			InvocationHandler handler = Proxy.getInvocationHandler(proxy);
			return handler instanceof AliasingInvocationHandler;
		}

		return false;
	}

	public static Object unwrapProxy(Object proxy) {
		if (Proxy.isProxyClass(proxy.getClass())) {
			InvocationHandler handler = Proxy.getInvocationHandler(proxy);
			if (handler instanceof AliasingInvocationHandler) {
				return ((AliasingInvocationHandler) handler).base;
			}
		}
		return proxy;
	}

	public Object unwrap() {
		return base;
	}

	public void wrap(Object base) {
		this.base = base;
	}
}
