import java.io.File;

public class Entry
{
	private static String PluginSource = "D:/eclipse/workspace/WakfuPlugins/bin";
	//private static String PluginSource = "D:/games/wakfu/plugins/";

	private static void LoadClasses() throws RuntimeException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		File source = new File(PluginSource);
		PluginClassLoader cl = new PluginClassLoader(source);
		
		if (source.isDirectory()) {
			for (String fileName : source.list()) {
				if (fileName.endsWith(".class")) {
					Class<?> c = cl.loadClass(fileName.substring(0, fileName.length() - 6));
					System.out.println("Class loaded!! " + c);
					if (Plugin.class.isAssignableFrom(c)) {
						Plugin p = (Plugin)c.newInstance();
						p.load();
						p.unload();
						p.exec("Start please");
					}
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		System.out.println("main()");

		LoadClasses();
		LoadClasses();
		
		System.out.println("/main()");
	}
}