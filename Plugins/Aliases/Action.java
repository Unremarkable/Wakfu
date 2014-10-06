@Alias(clazz=cyo.class)
public class Action
{
	@Alias(methodName = "isEnabled")
	boolean isEnabled() { throw new NoSuchMethodError(); }
	
	@Alias(methodName = "run")
	void run() { throw new NoSuchMethodError(); }
}
