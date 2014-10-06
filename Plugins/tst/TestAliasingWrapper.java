import org.junit.Test;


public class TestAliasingWrapper {
	
	@Alias(clazz=CrypticBaseClassA.class)
	public static class WrappedClassA{
		@Alias(methodName = "crypticMethod")
		public WrappedClassA wrappedMethod(WrappedClassA foo, WrappedClassB bar){return null;};
		
//		@Override
//        public void finalize() throws Throwable {
//                System.out.println("~Foo()");
//                super.finalize();
//        }
	}
	
	@Alias(clazz=CrypticBaseClassB.class)
	public static class WrappedClassB{
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
	public void test() throws InstantiationException, IllegalAccessException, InterruptedException{
		WrappedClassA wrapperA = new WrappedClassA();
		WrappedClassB wrapperB = new WrappedClassB();
		System.out.println(wrapperA.wrappedMethod(wrapperA, wrapperB));
	}

	@Test
	public void testStatic() throws InstantiationException, IllegalAccessException{
		System.out.println(WrappedClassB.getInstance());
	}
	
}
