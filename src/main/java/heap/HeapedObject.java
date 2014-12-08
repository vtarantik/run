package heap;

import model.MyClass;

public class HeapedObject {
	private MyClass objectClass;
	private Object instance;
	
	public HeapedObject(MyClass c,Object o){
		this.objectClass = c;
		this.instance = o;
	}
	
	public MyClass getObjectClass(){
		return objectClass;
	}
	
	public Object getInstance(){
		return instance;
	}
	
	public void setInstance(Object instance){
		this.instance = instance;
	}

}
