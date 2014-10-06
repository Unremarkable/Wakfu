import java.util.List;

@Alias(clazz=cjH.class)
public class Actor
{
	@Alias(methodName = "Q")
	Direction getOrientation() { throw new NoSuchMethodError(); }

	@Alias(methodName = "c")
	Path getPath(deO target, boolean unk1, boolean unk2) { throw new NoSuchMethodError(); }

	@Alias(methodName = "awR")
	Point3 getLocation() { throw new NoSuchMethodError(); }

	@Alias(methodName = "b")
	Path getPathTo(boolean unk1, boolean unk2, List<Point3> locations) { throw new NoSuchMethodError(); }
}