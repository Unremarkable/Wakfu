@Alias(clazz=aCq.class)
public class ResourceNode
	implements IEntity
{
	@Alias(methodName = "awR")
	Point3 getLocation() { throw new NoSuchMethodError(); }
	
	@Alias(methodName = "aPI")
	Action[] getActions() { throw new NoSuchMethodError(); }
	
	@Alias(methodName = "aPQ")
	dFi getBase() { throw new NoSuchMethodError(); }
	

	// IEntity
	@Alias(methodName = "getWorldX")
	public float getWorldX() { throw new NoSuchMethodError(); }

	@Alias(methodName = "getWorldY")
	public float getWorldY() { throw new NoSuchMethodError(); }
	
	@Alias(methodName = "getAltitude")
	public float getAltitude() { throw new NoSuchMethodError(); }
	
	@Alias(methodName = "fO")
	public int getX() { throw new NoSuchMethodError(); }

	@Alias(methodName = "fP")
	public int getY() { throw new NoSuchMethodError(); }

	@Alias(methodName = "fQ")
	public short getZ() { throw new NoSuchMethodError(); }
}
