import java.util.ArrayList;

class NodeList
	extends ArrayList<ResourceNode>
	implements dOx<ResourceNode>
{
	@Override
	public boolean c(ResourceNode arg0) {
		return this.add(arg0);
	}	
}