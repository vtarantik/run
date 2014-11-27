package parser;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import org.apache.bcel.classfile.Attribute;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.Constant;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.MethodGen;

public class MembersHelper {
	private static ClassParser classParser;
	private static JavaClass javaClass;
	
	public static void init(String classFileName){
		try {
			classParser = new ClassParser(classFileName);
			javaClass = classParser.parse();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayDeque<Constant> getConstants() {
		ArrayDeque<Constant> constantsQueue = new ArrayDeque<Constant>();
		ConstantPool pool = javaClass.getConstantPool();
		Constant[] cnstnts = pool.getConstantPool();
		for (int i = 0; i < cnstnts.length; i++) {
			if (cnstnts[i] != null) {
				constantsQueue.add(cnstnts[i]);
			}
		}
		return constantsQueue;
	}

	public static List<Method> getMethods() {
		List<Method> methodList = new ArrayList<Method>();
		Method[] methds = javaClass.getMethods();
		for (int i = 0; i < methds.length; i++) {
			methodList.add(methds[i]);
		}
		return methodList;
	}

	public static ArrayDeque<Field> getFields() {
		ArrayDeque<Field> fieldQueue = new ArrayDeque<Field>();
		Field[] flds = javaClass.getFields();
		for (int i = 0; i < flds.length; i++) {
			fieldQueue.add(flds[i]);
			System.out.println(flds[i]);
		}
		return fieldQueue;
	}

	public static ArrayDeque<Attribute> getAttributes() {
		ArrayDeque<Attribute> attributesQueue = new ArrayDeque<Attribute>();
		Attribute[] attrs = javaClass.getAttributes();
		for (int i = 0; i < attrs.length; i++) {
			attributesQueue.add(attrs[i]);
		}
		return attributesQueue;
	}

	public static ArrayDeque<JavaClass> getInterfaces() {
		ArrayDeque<JavaClass> ifaceQueue = new ArrayDeque<JavaClass>();
		JavaClass[] ifaces = null;
		try {
			ifaces = javaClass.getAllInterfaces();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < ifaces.length; i++) {
			ifaceQueue.add(ifaces[i]);
		}

		return ifaceQueue;
	}

	public static ArrayDeque<Instruction> getInstructions(Method method) {
		ArrayDeque<Instruction> instructionsQueue = new ArrayDeque<Instruction>();

		ConstantPoolGen cpg = new ConstantPoolGen(javaClass.getConstantPool());
		MethodGen mg = new MethodGen(method, javaClass.getClassName(), cpg);
		InstructionList il = mg.getInstructionList();
		Instruction[] instructions = il.getInstructions();
		for (int i = 0; i < il.getLength(); i++) {
			instructionsQueue.add(instructions[i]);
		}
		
		return instructionsQueue;

	}
	
	public static HashMap<Method,ArrayDeque<Instruction>> getMethodsInstructions(){
		HashMap<Method, ArrayDeque<Instruction>> mapToReturn = new HashMap<Method, ArrayDeque<Instruction>>();
		List<Method> methodsList = getMethods();
		for (Method method : methodsList) {
			mapToReturn.put(method, getInstructions(method));
		}
		return mapToReturn;
	}
	
	public static ConstantPool getConstantPool(Method method){
		return method.getConstantPool();
	}
	
}
