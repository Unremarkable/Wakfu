
@Alias(clazz=cwn.class)
public interface IEntity 
{
	@Alias(methodName = "getWorldX")
	public float getWorldX();

	@Alias(methodName = "getWorldY")
	public float getWorldY();
	
	@Alias(methodName = "getAltitude")
	public float getAltitude();
	
	@Alias(methodName = "fO")
	public int getX();

	@Alias(methodName = "fP")
	public int getY();

	@Alias(methodName = "fQ")
	public short getZ();
}