import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;


public class PluginManager 
implements dtO {
	static {
		try {
			File outFile = new File("out.log");
			File errFile = new File("err.log");
	
			if (!outFile.exists()) outFile.createNewFile();
			if (!errFile.exists()) errFile.createNewFile();
	
			System.setOut(new PrintStream(outFile));
			System.setErr(new PrintStream(outFile));
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}
	
	//private static File PluginSource = new File("D:/Documents/GitHub/Wakfu/Wakfu/Plugins/bin/");

	private PluginClassLoader       PluginClassLoader;
	private HashMap<String, Plugin> Plugins           = new HashMap<String, Plugin>();
	
	public String readPropertiesForPluginSource() {
		Properties properties = new Properties(); 
		try {
			properties.load(new FileReader("pluginmanager.properties"));
			return properties.getProperty("PluginSource");
		} catch (IOException e) {
		}
		return "/plugins/";
	}

	@Override
	public void a(czg arg0, agu arg1, ArrayList<String> args) {
		Util.print("Plugin Manager");
		System.out.println("Plugin Manager");
		String help =
			"Proper usage:"
		+	"/pm reload"
		+	"/pm exec   plugin [args]";
		
		if (args.size() < 3) {
			Util.print(help);
		} else {
			String[] command = args.get(2).split("\\s+", 2);

			if (command[0].equals("reload")) {
				for (Plugin plugin : Plugins.values())
					plugin.unload();
				Plugins.clear();

				String location = readPropertiesForPluginSource();
				Util.print(location);
				System.out.println(location);
				File source = new File(location);
				PluginClassLoader = new PluginClassLoader(source);
				
				try {
					for (Class<?> c : PluginClassLoader.loadAll()) {
						if (Plugin.class.isAssignableFrom(c)) {
							Plugin p = ((Class<Plugin>)c).newInstance();
							Plugins.put(p.getName(), p);
						}
					}
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				
				for (Plugin p : Plugins.values()) 
					p.load();
			} else if (command[0].equals("exec")) {
				String[] e = command[1].split("\\s+", 2);
				
				if (e.length < 1) {
					Util.print("/pm exec plugin [args]");
				} else {
					String name = e[0];
					String params = (e.length < 2) ? "" : e[1];
					
					if (Plugins.containsKey(name)) {
						Plugins.get(name).exec(params);
					} else {
						Util.print("Plugin [" + name + "] not found.");
					}
				}
			}
			
		}
	}

	@Override
	public boolean al() {
		return false;
	}
}
