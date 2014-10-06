@Alias(clazz=bCe.class)
public class EntityManager
{
	@Alias(methodName = "bNO")
	static EntityManager getInstance() { throw new NoSuchMethodError(); }
	
	@Alias(methodName = "bNP")
	Player getPlayer() { throw new NoSuchMethodError(); }
}