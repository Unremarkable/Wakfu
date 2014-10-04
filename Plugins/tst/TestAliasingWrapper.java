import org.junit.Test;


public class TestAliasingWrapper {
	
	@Alias(clazz=CrypticBaseClassA.class)
	public static interface WrappedClassA{
			
		@Alias(methodName = "crypticMethod")
		public WrappedClassA wrappedMethod(WrappedClassA foo, WrappedClassB bar);
	}
	
	@Alias(clazz=CrypticBaseClassB.class)
	public static interface WrappedClassB{
			
		@Alias(methodName = "someMethod")
		public WrappedClassA wrappedMethod(WrappedClassA foo);
	}
	
	public static class CrypticBaseClassA{
		public CrypticBaseClassA crypticMethod(CrypticBaseClassA foo, CrypticBaseClassB bar){
			return bar.someMethod(foo);
		}
	}
	
	public static class CrypticBaseClassB{
		public CrypticBaseClassA someMethod(CrypticBaseClassA foo){
			return foo;
		}
	}
	
	@Test
	public void test() throws InstantiationException, IllegalAccessException{
		CrypticBaseClassA baseA = new CrypticBaseClassA();
		CrypticBaseClassB baseB = new CrypticBaseClassB();
		
		WrappedClassA wrapperA = AliasingInvocationHandler.createWrapper(WrappedClassA.class, baseA);
		WrappedClassB wrapperB = AliasingInvocationHandler.createWrapper(WrappedClassB.class, baseB);
		
		System.out.println(wrapperA.wrappedMethod(wrapperA, wrapperB));
	}
}
