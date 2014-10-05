import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class TestPlugin implements Plugin {

	@Override
	public void load() {
		Util.print("TestPlugin.load();");
	}

	@Override
	public void unload() {
		Util.print("TestPlugin.unload();");
	}
	
	public List<deO> getTilesAdjacentTo(deO destination) {
		ArrayList<deO> path = new ArrayList<deO>();
		bCi player = Player.core();
		
		if (!player.n(false, true)) {
			System.out.println("[ERROR] getPath: !player.n(false, true)");
			return (List<deO>)java.util.Collections.EMPTY_LIST;
		}
		
		int i = 1;	// min range
		int j = 1; 	// max range
		
		cDa.a((short)player.cv(), player.aip(), player.auQ());
		
		boolean k = true;
		
		// For each direction around the destination..
		DE[] directions = DE.Os();
		deO playerPoint3d = player.Pi();
		for (int m = 0; m < directions.length; m++)
		{
			DE direction = directions[m];
			deO adjacent = new deO(destination);
			for (int n = i; n <= j; n++)
			{
				adjacent.n(direction);
				short s = cDa.T(adjacent.getX(), adjacent.getY(), adjacent.KY());
				if (s != -32768)
				{
					adjacent.eh(s);
					path.add(adjacent);
					k &= !playerPoint3d.equals(adjacent);
					break;
				}
			}
		}
		
		if (path.isEmpty())
		{
			System.out.println("[ERROR] getPath: path.isEmpty()");
			return (List<deO>)java.util.Collections.EMPTY_LIST;
		}
		
		if (!k)
		{
			System.out.println("[ERROR] getPath: k == 0");
			return (List<deO>)java.util.Collections.EMPTY_LIST;
		}
		
		return path;
	}
	
	public cAI getPathTo(deO destination) {
		return Player.actor().b(
			false,
			Player.getPoint3D().aa(destination) > 2,	// Manhattan distance
			getTilesAdjacentTo(destination)
		);
	}
	
	private void test() {
		deO destination = new deO(Player.actor().awR());
		destination.add(0, 4);
		
		cAI path = getPathTo(destination);

		System.out.println("Length: " + path.gd());
		
		Player.core().a(path, true);
	}
	
	private void run() {
		aeB manager = aeB.arW();
		NodeList nodelist = new NodeList();
		manager.m(nodelist);	// copy into nodelist
		
		System.out.println("Nodes: " + nodelist.size());
		
		Iterator<aCq> itr = nodelist.iterator();
		while (itr.hasNext()) {
			aCq node = itr.next();
			cyo[] actions = node.aPI();
			boolean hasActionsAvailable = false;
			for (int i = 0; i < actions.length; ++i) {
				if (actions[i].isUsable()) {
					hasActionsAvailable = true;
					break;
				}
			}
			if (!hasActionsAvailable)
				itr.remove();
		}
		
		System.out.println("Usable: " + nodelist.size());
		
		Collections.sort(nodelist, new Comparator<aCq>() {
	        @Override
	        public int compare(aCq n1, aCq n2) {
	        	return Float.compare(
	        		Player.getManhattenDistanceTo(n1),
	        		Player.getManhattenDistanceTo(n2)
	        	);
	        }
	    });
		
		aCq node = nodelist.get(0);
		cAI path = getPathTo(node.awR());
		for (int i = 1; i < nodelist.size(); ++i) {
			aCq node2 = nodelist.get(i);
			if (Player.getManhattenDistanceTo(node) > path.gd())
				break;
			cAI path2 = getPathTo(node.awR());
			
			if (path2.gd() < path.gd()) {
				path = path2;
				node = node2;
			}
		}
		
		System.out.println("Closest: " + node.aPQ().getResourceName() + " " + path.gd());
		
		cyo[] actions = node.aPI();
		for (int i = 0; i < actions.length; ++i) {
			if (actions[i].isUsable()) {
				System.out.println("Running.");
				actions[i].run();
				break;
			}
		}
	}
	
	@Override
	public void exec(String args) {
		Util.print("TestPlugin.exec("+args+");");
		run();
	}

	@Override
	public String getName() {
		return "test";
	}

}
