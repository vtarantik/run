package parser;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

import org.apache.bcel.classfile.Attribute;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.Constant;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.InnerClass;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.MethodGen;

public class MembersHelper {
	private Queue<Constant> constants;
	private Queue<Method> methods;
	private Queue<Field> fields;
	private Queue<JavaClass> interfaces;
	private Queue<Attribute> attributes;

	public MembersHelper(String classFilePath) {
		try {
			ClassParser parser = new ClassParser(classFilePath);
			JavaClass c = parser.parse();

			this.constants = findConstants(c);
			this.methods = findMethods(c);
			this.fields = findFields(c);
			this.interfaces = findInterfaces(c);
			this.attributes = findAttributes(c);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Queue<Constant> getConstants() {
		return constants;
	}

	public Queue<Method> getMethods() {
		return methods;
	}

	public Queue<Field> getFields() {
		return fields;
	}

	public Queue<JavaClass> getInterfaces() {
		return interfaces;
	}

	public Queue<Attribute> getAttributes() {
		return attributes;
	}

	private ArrayDeque<Constant> findConstants(JavaClass javaClass) {
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

	private ArrayDeque<Method> findMethods(JavaClass javaClass) {
		ArrayDeque<Method> methodQueue = new ArrayDeque<Method>();
		Method[] methds = javaClass.getMethods();
		for (int i = 0; i < methds.length; i++) {
			methodQueue.add(methds[i]);
		}
		return methodQueue;
	}

	private ArrayDeque<Field> findFields(JavaClass javaClass) {
		ArrayDeque<Field> fieldQueue = new ArrayDeque<Field>();
		Field[] flds = javaClass.getFields();
		for (int i = 0; i < flds.length; i++) {
			fieldQueue.add(flds[i]);
		}
		return fieldQueue;
	}

	private ArrayDeque<Attribute> findAttributes(JavaClass javaClass) {
		ArrayDeque<Attribute> attributesQueue = new ArrayDeque<Attribute>();
		Attribute[] attrs = javaClass.getAttributes();
		for (int i = 0; i < attrs.length; i++) {
			attributesQueue.add(attrs[i]);
		}
		return attributesQueue;
	}

	private ArrayDeque<JavaClass> findInterfaces(JavaClass javaClass) {
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

	private ArrayDeque<Instruction> findInstructions(JavaClass javaClass, Method method) {
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

}
