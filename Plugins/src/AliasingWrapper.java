import net.sf.cglib.proxy.Enhancer;


public class AliasingWrapper<T> {
	public T base;
	public AliasingWrapper(){
		
	}
	public AliasingWrapper(T base){
		this.base = base;
	}
	
	public void wrap(T base){
		this.base = base;
	}
	
	public T unwrap(){
		return base;
	}
}
