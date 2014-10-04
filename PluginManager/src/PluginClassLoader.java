import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PluginClassLoader
extends ClassLoader {
	public PluginClassLoader(File source) {
		Source = source;
	}
	
	public Iterable<Class<?>> loadAll() throws ClassNotFoundException {
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		
		for (String fileName : Source.list()) {
			if (fileName.endsWith(".class")) {
				Class<?> c = findClass(fileName.substring(0, fileName.length() - 6));
				classes.add(c);
			}
		}
		
		for (Class<?> c : classes)
			resolveClass(c);
		
		return classes;
	}
	
	@Override
	public Class<?> findClass (String name) throws ClassNotFoundException {
		try {
			File file = new File(Source, name + ".class");
	
	        int length = (int) file.length();
	        byte[] classbytes = new byte[length];
	        DataInputStream in = new DataInputStream(new FileInputStream(file));
	        in.readFully(classbytes);
	        in.close();

	        return defineClass(name, classbytes, 0, length);        
		} catch (IOException ex) {
			throw new ClassNotFoundException(ex.toString());
		}
	}
	
	private File Source;
}