import org.junit.Test;


public class TestAliasingWrapper {
	
	@Alias(clazz=CrypticBaseClassA.class)
	public static class WrappedClassA extends AliasingWrapper<CrypticBaseClassA>{
		public WrappedClassA() {
		}	
		public WrappedClassA(CrypticBaseClassA base) {
			super(base);
		}

		@Alias(methodName = "crypticMethod")
		public WrappedClassA wrappedMethod(WrappedClassA foo, WrappedClassB bar){return null;};
	}
	
	@Alias(clazz=CrypticBaseClassB.class)
	public static class WrappedClassB extends AliasingWrapper<CrypticBaseClassB>{
		public WrappedClassB() {
		}	
		public WrappedClassB(CrypticBaseClassB base) {
			super(base);
		}

		@Alias(methodName = "someMethod")
		public WrappedClassA wrappedMethod(WrappedClassA foo){ return null;};
		
		@Alias(methodName = "getInstance")
		public static WrappedClassB getInstance(){return null;};
	}
	
	public static class CrypticBaseClassA{
		public CrypticBaseClassA crypticMethod(CrypticBaseClassA foo, CrypticBaseClassB bar){
			return bar.someMethod(foo);
		}
	}
	
	public static class CrypticBaseClassB{
		static CrypticBaseClassB instance;
		public CrypticBaseClassA someMethod(CrypticBaseClassA foo){
			return foo;
		}
		
		public static CrypticBaseClassB getInstance(){
			if(instance == null)			{
				instance = new CrypticBaseClassB();
			}
			return instance;
		}
	}
	
	@Test
	public void test() throws InstantiationException, IllegalAccessException{
		CrypticBaseClassA baseA = new CrypticBaseClassA();
		CrypticBaseClassB baseB = new CrypticBaseClassB();
		
		WrappedClassA wrapperA = new WrappedClassA(baseA);
		WrappedClassB wrapperB = new WrappedClassB(baseB);
		
		System.out.println(wrapperA.wrappedMethod(wrapperA, wrapperB));
	}

	@Test
	public void testStatic() throws InstantiationException, IllegalAccessException{
		System.out.println(WrappedClassB.getInstance());
	}
	
}
