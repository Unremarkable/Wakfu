public class TestPlugin 
implements Plugin {
	private static int asdf = 0;

	@Override
	public void load() {
		Util.print("[TestPlugin] Load " + (asdf++));
	}

	@Override
	public void unload() {
		Util.print("[TestPlugin] Unload " + (asdf++));
	}

	@Override
	public void exec(String args) {
		Util.print("[TestPlugin] Exec(There is no cow level! "+args+") " + (asdf++));
	}

	@Override
	public String getName() {
		return "TestPlugin";
	}
}
