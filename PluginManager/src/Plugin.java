
public interface Plugin {
	void load();
	void unload();
	void exec(String args);
	
	String getName();
}
