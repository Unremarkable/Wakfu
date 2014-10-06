import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.aspectj.lang.Aspects;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.ConstructorSignature;

public class AliasAspects {
	public static Object magic(ProceedingJoinPoint joinPoint, Alias alias) throws Throwable
	{

		Object args[] = joinPoint.getArgs();
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		Object object = joinPoint.getTarget();
		Alias classAlias = (Alias)signature.getDeclaringType().getAnnotation(Alias.class);

		System.out.println("Wrapping " + signature);
		if (classAlias != null && alias != null) {
			Class<?> baseClass = classAlias.clazz();

			Object parameters[] = getUnwrappedParameters(signature.getParameterTypes(), args);
			Class<?> parameterTypes[] = getUnwrappedParameterTypes(signature.getParameterTypes(), args);

			Method baseMethod = baseClass.getMethod(alias.methodName(),
					parameterTypes);

			Object returnValue = baseMethod.invoke(unwrapProxy(object),
					parameters);
			Class<?> returnType = signature.getReturnType();
			Alias returnTypeAlias = returnType.getAnnotation(Alias.class);
			
		//	Lord help me
			if (returnType.isArray()) {
				returnTypeAlias = returnType.getComponentType().getAnnotation(Alias.class);
				returnType = returnType.getComponentType();
				
				if (returnTypeAlias != null
						&& returnTypeAlias.clazz().equals(returnValue.getClass().getComponentType())) {
				    Object newWrapper = Array.newInstance(returnType, ((Object[])returnValue).length);
					wrapProxy(returnType, returnValue);
					return newWrapper;
				}
			}

			if (returnTypeAlias != null
					&& returnTypeAlias.clazz().equals(returnValue.getClass())) {
			    Object newWrapper = returnType.newInstance();
				wrapProxy(returnType, returnValue);
				return newWrapper;
			} else {
				return returnValue;
			}
		}
		return joinPoint.proceed();
	}
	
	public static boolean isAliasingProxy(Object proxy) {
		return Aspects.hasAspect(AliasingAspect.class, proxy);
	}

	public static Object unwrapProxy(Object proxy) {
		if(isAliasingProxy(proxy)){
			return Aspects.aspectOf(AliasingAspect.class, proxy).base;
		}
		return proxy;
	}
	
	public static void wrapProxy(Object proxy, Object base) {
		if(isAliasingProxy(proxy)){
			AliasingAspect aspect = Aspects.aspectOf(AliasingAspect.class, proxy);
			aspect.base = base;
		}
	}
	
	public static Class<?>[] getUnwrappedParameterTypes(Class<?> declaredParameterTypes[], Object args[]){
		Class<?> parameterTypes[] = null;
		if (args != null) {
			parameterTypes = new Class[args.length];

			for (int i = 0; i < args.length; i++) {
				Class<?> paramType = declaredParameterTypes[i];
				Alias paramAlias = paramType.getAnnotation(Alias.class);
				if (paramAlias != null) {
					parameterTypes[i] = paramAlias.clazz();
				} else {
					parameterTypes[i] = paramType;
				}
			}
		}
		return parameterTypes;
	}
	
	public static Object[] getUnwrappedParameters(Class<?> declaredParameterTypes[], Object args[]){
		Object parameters[] = null;
		if (args != null) {
			parameters = new Object[args.length];
			for (int i = 0; i < args.length; i++) {
				Class<?> paramType = declaredParameterTypes[i];
				Alias paramAlias = paramType.getAnnotation(Alias.class);
				if (paramAlias != null) {
					if (args[i] != null && isAliasingProxy(args[i])) {
						parameters[i] = unwrapProxy(args[i]);
					} else {
						parameters[i] = args[i];
					}
				} else {
					parameters[i] = args[i];
				}
			}
		}
		return parameters;
	}
	
	@Aspect
	public static class StaticAspect {
		@Around("execution(static * *(..)) && @annotation(alias)")
		public Object alias(ProceedingJoinPoint joinPoint, Alias alias) throws Throwable {
			Object o = magic(joinPoint, alias);
			return o;
		}
	}
	
	@Aspect("perthis((execution(*.new (..)) || execution(* * (..)))  && @this(Alias))")
	public static class AliasingAspect {
		
		Object base = null;
		
		public AliasingAspect(){
		}
		
		 @Around("execution(*.new(..)) && @target(alias)")
		 public void asdf( ProceedingJoinPoint joinPoint, Alias alias)
					throws Throwable {
	
	//		joinPoint.proceed();
			Object args[] = joinPoint.getArgs();
			ConstructorSignature signature = (ConstructorSignature)joinPoint.getSignature();
			Object parameters[] = getUnwrappedParameters(signature.getParameterTypes(), args);
			Class<?> parameterTypes[] = getUnwrappedParameterTypes(signature.getParameterTypes(), args);
			
			Class<?> baseClass = alias.clazz();
			Constructor constructor = baseClass.getConstructor(parameterTypes);
			if(constructor != null){
				Object newBase = constructor.newInstance(parameters);
				this.base = newBase;
			}
		}
		
		@Around("execution(* * (..)) && @annotation(alias)")
	    public Object aliasMethod( ProceedingJoinPoint joinPoint, Alias alias)
				throws Throwable {
			return magic(joinPoint, alias);
		}
		
	}
}