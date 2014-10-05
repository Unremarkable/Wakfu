import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class AliasingAspect {

	@Around("call(* * (..)) && @annotation(alias)")
    public Object aroundAdvice(
ProceedingJoinPoint joinPoint, Alias alias)
			throws Throwable {

		Object args[] = joinPoint.getArgs();
		Object object = joinPoint.getTarget();
		Alias classAlias = object.getClass().getAnnotation(Alias.class);
		
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		
		if (classAlias != null && alias != null) {
			Class<?> baseClass = classAlias.clazz();

			Object parameters[] = null;
			Class<?> parameterTypes[] = null;

			if (args != null) {
				parameters = new Object[args.length];
				parameterTypes = new Class[args.length];

				for (int i = 0; i < args.length; i++) {
					Class<?> paramType = signature.getParameterTypes()[i];
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
			}

			Method baseMethod = baseClass.getMethod(alias.methodName(),
					parameterTypes);

			Object returnValue = baseMethod.invoke(unwrapProxy(object),
					parameters);
			Class<?> returnType = signature.getReturnType();
			Alias returnTypeAlias = returnType.getAnnotation(Alias.class);

			if (returnTypeAlias != null
					&& returnTypeAlias.clazz().equals(returnValue.getClass())
					&& AliasingWrapper.class.isAssignableFrom(returnType)) {
				AliasingWrapper newWrapper = (AliasingWrapper) returnType.newInstance();
				newWrapper.wrap(returnType);
				return newWrapper;
			} else {
				return returnValue;
			}
		}
		return joinPoint.proceed();
	}
	
	public static boolean isAliasingProxy(Object proxy) {
		return proxy instanceof AliasingWrapper;
	}

	public static Object unwrapProxy(Object proxy) {
		if(proxy instanceof AliasingWrapper){
			Object foo =((AliasingWrapper)proxy).unwrap();
			return foo;
		}
		return proxy;
	}
}
