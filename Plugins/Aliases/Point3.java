@Alias(clazz=deO.class)
public class Point3
{	
	@Alias
	Point3(Point3 p) { throw new NoSuchMethodError(); }
	
	@Alias
	Point3(int x, int y, short z) { throw new NoSuchMethodError(); }
	
	@Alias(methodName = "getX")
	int getX() { throw new NoSuchMethodError(); }
	
	@Alias(methodName = "getY")
	int getY() { throw new NoSuchMethodError(); }
	
	@Alias(methodName = "KY")
	short getZ() { throw new NoSuchMethodError(); }

	@Alias(methodName = "getX")
	void setX(int x) { throw new NoSuchMethodError(); }
	
	@Alias(methodName = "getY")
	void setY(int y) { throw new NoSuchMethodError(); }
	
	@Alias(methodName = "eh")
	void setZ(short z) { throw new NoSuchMethodError(); }
	
	@Alias(methodName = "n")
	void add(Direction direction) { throw new NoSuchMethodError(); }
	
	@Alias(methodName = "add")
	void add(int x, int y) { throw new NoSuchMethodError(); }
	
	@Alias(methodName = "aa")
	int manhattenDistance(Point3 to) { throw new NoSuchMethodError(); }
}