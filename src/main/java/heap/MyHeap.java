package heap;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import model.MyClass;

public class MyHeap {
	private static MyHeap instance = null;
	private Map<String, MyClass> classTypes;
	private Map<Integer, HeapedObject> data;

	private MyHeap() {
		classTypes = new HashMap<String, MyClass>();
		data = new HashMap<Integer, HeapedObject>();
	}

	public static MyHeap getInstance() {
		if (instance == null) {
			instance = new MyHeap();
		}
		return instance;
	}

	private int getFirstFreeIndex() {
		for (int i = 0; i < data.size(); i++) {
			if (!data.containsKey(i)) {
				return i;
			}
		}
		return data.size();
	}

	public void store(HeapedObject o) {
		data.put(getFirstFreeIndex(), o);
	}

	public void addClassType(MyClass classType) {
		classTypes.put(classType.getClassName(), classType);
	}

	public MyClass getClassType(String classTypeName) {
		return classTypes.get(classTypeName);
	}
}