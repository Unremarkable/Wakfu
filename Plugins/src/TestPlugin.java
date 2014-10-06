import java.lang.reflect.Array;

import org.junit.Test;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.reflect.MethodSignature;
//
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.Iterator;
//import java.util.List;
//
//public class TestPlugin implements Plugin {
//	@Override
//	public void load() {
//		Util.print("TestPlugin.load();");
//	}
//
//	@Override
//	public void unload() {
//		Util.print("TestPlugin.unload();");
//	}
//	
//	public List<Point3> getTilesAdjacentTo(Point3 destination) {
//		ArrayList<Point3> path = new ArrayList<Point3>();
//		Player player = EntityManager.getInstance().getPlayer();
//		
////		if (!player.n(false, true)) {
////			System.out.println("[ERROR] getPath: !player.n(false, true)");
////			return (List<Point3>)java.util.Collections.EMPTY_LIST;
////		}
//		
//		int i = 1;	// min range
//		int j = 1; 	// max range
//		
//	//	cDa.a((short)((bCi)player).cv(), player.aip(), player.auQ());
//		
//		boolean k = true;
//		
//		// For each direction around the destination..
//		Direction[] directions = Direction.All();
//		Point3 origin = player.getLocation();
//		for (int m = 0; m < directions.length; m++)
//		{
//			Direction direction = directions[m];
//			Point3 adjacent = new Point3(destination);
//			for (int n = i; n <= j; n++)
//			{
//				adjacent.add(direction);
//				short s = cDa.T(adjacent.getX(), adjacent.getY(), adjacent.getZ());
//				if (s != -32768)
//				{
//					adjacent.setZ(s);
//					path.add(adjacent);
//					k &= !origin.equals(adjacent);
//					break;
//				}
//			}
//		}
//		
//		if (path.isEmpty())
//		{
//			System.out.println("[ERROR] getPath: path.isEmpty()");
//			return (List<Point3>)java.util.Collections.EMPTY_LIST;
//		}
//		
//		if (!k)
//		{
//			System.out.println("[ERROR] getPath: k == 0");
//			return (List<Point3>)java.util.Collections.EMPTY_LIST;
//		}
//		
//		return path;
//	}
//	
//	public Path getPathTo(Point3 destination) {
//		Player player = EntityManager.getInstance().getPlayer();
//		return player.getActor().getPathTo(
//			false,
//			player.getLocation().manhattenDistance(destination) > 2,
//			getTilesAdjacentTo(destination)
//		);
//	}
//	
//	private void test() {
//		Player player = EntityManager.getInstance().getPlayer();
//		
//		Point3 destination = new Point3(player.getLocation());
//		destination.add(0, 4);
//		
//		Path path = getPathTo(destination);
//
//		System.out.println("Length: " + path.length());
//		
//		player.walk(path, true);
//	}
//	
//	private float getManhattenDistance(IEntity a, IEntity b)
//	{
//		return
//			Math.abs(a.getWorldX() - b.getWorldX())
//		+	Math.abs(a.getWorldY() - b.getWorldY());
//	}
//	
//	private void run() {
//		final Player player = EntityManager.getInstance().getPlayer();
//		ResourceManager manager = ResourceManager.getInstance();
//		NodeList nodelist = new NodeList();
//		manager.getResources(nodelist);	// copy into nodelist
//		
//		System.out.println("Nodes: " + nodelist.size());
//		
//		Iterator<ResourceNode> itr = nodelist.iterator();
//		while (itr.hasNext()) {
//			ResourceNode node = itr.next();
//			Action[] actions = node.getActions();
//			boolean hasActionsAvailable = false;
//			for (int i = 0; i < actions.length; ++i) {
//				if (actions[i].isEnabled()) {
//					hasActionsAvailable = true;
//					break;
//				}
//			}
//			if (!hasActionsAvailable)
//				itr.remove();
//		}
//		
//		System.out.println("Usable: " + nodelist.size());
//		
//		Collections.sort(nodelist, new Comparator<ResourceNode>() {
//	        @Override
//	        public int compare(ResourceNode n1, ResourceNode n2) {
//	        	return Float.compare(
//	        		getManhattenDistance(player, n1),
//	        		getManhattenDistance(player, n2)
//	        	);
//	        }
//	    });
//
//		ResourceNode node = nodelist.get(0);
//		Path path = getPathTo(node.getLocation());
//		for (int i = 1; i < nodelist.size(); ++i) {
//			ResourceNode node2 = nodelist.get(i);
//			if (getManhattenDistance(player, node) > path.length())
//				break;
//			Path path2 = getPathTo(node.getLocation());
//			
//			if (path2.length() < path.length()) {
//				path = path2;
//				node = node2;
//			}
//		}
//		
//		System.out.println("Closest: " + node.getBase().getResourceName() + " " + path.length());
//		
//		Action[] actions = node.getActions();
//		for (int i = 0; i < actions.length; ++i) {
//			if (actions[i].isEnabled()) {
//				System.out.println("Running.");
//				actions[i].run();
//				break;
//			}
//		}
//	}
//	
//	@Override
//	public void exec(String args) {
//		
//	}
//	
//	public static void main(String[] args)
//	{
////		run();
//		Player player = new Player();
//		System.out.println("Player level: " + player.getLevel());
//	}
//
//	@Override
//	public String getName() {
//		return "test";
//	}
//
//

//public class TestPlugin {
//	public static class Foo
//	{
//		public Foo() {
//			System.out.println("Foo()");
//		}
//
//		public void test() {
//			System.out.println("Foo.test()");
//		}
//	}
//	
////	@Aspect("perthis(execution(*.new(..)))")
//	@Aspect("perthis(execution(*.new(..)) && @this(Alias))")
//	public static class MyAspect
//	{
////		Foo base;
//		
//		@Before("execution(*.new(..)) && !within(MyAspect)")
//		public void ctor() {
//		//	base = (Foo)(Object)this;
//			System.out.println("Hijacked ctor!");
//		}
//		
////		@Around("call(* * (..)) && @annotation(alias)")
////		public Object asdf() {
////			base.test();
////			return null;
////		}
//	}
//
////	@Aspect("perthis(execution(*.new()))")
////	public static class TestAspect {
////		@Before("execution(*.new()) && !within(TestAspect)")
////		public void ctor() {
////		}
////		
////		@Around("execution(String *.toString())")
////		public String asdf()
////		{
////			return "Hijacked!";
////		}
////	}
//	
//	public void foo()
//	{
//		Foo foo = new Foo();
//		foo.test();
//		System.out.println(foo.toString());
//	}
//	
//	@Test
//	public void test() throws InterruptedException {
//		System.out.println("test()");
//		foo();
//		System.gc();
//		Thread.sleep(10000);
//	}
//}

public class TestPlugin {
	public static void main(String[] args) throws Throwable {
		Point3 p = new Point3(1, 2, (short)3);
		
		System.out.println(p.getX() + ","+p.getY()+","+p.getZ());
		
		Direction[] direction = Direction.Cardinal();
		
		System.out.println(direction.length);
	}
}