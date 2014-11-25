package parser;

import instructions.AconstNull;
import instructions.Aload;
import instructions.Areturn;
import instructions.ArrayLength;
import instructions.Astore;
import instructions.Baload;
import instructions.Bastore;
import instructions.Bipush;
import instructions.Caload;
import instructions.Castore;
import instructions.Checkcast;
import instructions.Dcmp;
import instructions.Dcmp.DcmpType;
import instructions.Dconst;
import instructions.Dload;
import instructions.Dup;
import instructions.Fcmp;
import instructions.Fcmp.FcmpType;
import instructions.Fconst;
import instructions.Fload;
import instructions.Fstore;
import instructions.GetField;
import instructions.GetStatic;
import instructions.Goto;
import instructions.I2C;
import instructions.IAbstractInstruction;
import instructions.IFIcmp;
import instructions.IFIcmp.IfIcmpType;
import instructions.Iadd;
import instructions.Iastore;
import instructions.Iconst;
import instructions.If;
import instructions.IfNonNull;
import instructions.Ifnull;
import instructions.Iinc;
import instructions.Iload;
import instructions.InvokeSpecial;
import instructions.InvokeStatic;
import instructions.InvokeVirtual;
import instructions.Ireturn;
import instructions.Istore;
import instructions.Isub;
import instructions.Lcmp;
import instructions.Lconst;
import instructions.Ldc;
import instructions.Ldc2W;
import instructions.LdcW;
import instructions.Lshl;
import instructions.New;
import instructions.Nop;
import instructions.If.Iftype;
import instructions.NewArray;
import instructions.Pop;
import instructions.PutField;
import instructions.PutStatic;
import instructions.Return;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import org.apache.bcel.classfile.Attribute;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.Constant;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.InnerClass;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ACONST_NULL;
import org.apache.bcel.generic.ALOAD;
import org.apache.bcel.generic.ARETURN;
import org.apache.bcel.generic.ARRAYLENGTH;
import org.apache.bcel.generic.ASTORE;
import org.apache.bcel.generic.BALOAD;
import org.apache.bcel.generic.BASTORE;
import org.apache.bcel.generic.BIPUSH;
import org.apache.bcel.generic.CALOAD;
import org.apache.bcel.generic.CASTORE;
import org.apache.bcel.generic.CHECKCAST;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.DCMPG;
import org.apache.bcel.generic.DCMPL;
import org.apache.bcel.generic.DCONST;
import org.apache.bcel.generic.DLOAD;
import org.apache.bcel.generic.DUP;
import org.apache.bcel.generic.FCMPG;
import org.apache.bcel.generic.FCMPL;
import org.apache.bcel.generic.FCONST;
import org.apache.bcel.generic.FLOAD;
import org.apache.bcel.generic.FSTORE;
import org.apache.bcel.generic.GETFIELD;
import org.apache.bcel.generic.GETSTATIC;
import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.I2B;
import org.apache.bcel.generic.I2L;
import org.apache.bcel.generic.IADD;
import org.apache.bcel.generic.IASTORE;
import org.apache.bcel.generic.ICONST;
import org.apache.bcel.generic.IFEQ;
import org.apache.bcel.generic.IFGE;
import org.apache.bcel.generic.IFGT;
import org.apache.bcel.generic.IFLE;
import org.apache.bcel.generic.IFLT;
import org.apache.bcel.generic.IFNE;
import org.apache.bcel.generic.IFNONNULL;
import org.apache.bcel.generic.IFNULL;
import org.apache.bcel.generic.IF_ICMPEQ;
import org.apache.bcel.generic.IF_ICMPGE;
import org.apache.bcel.generic.IF_ICMPGT;
import org.apache.bcel.generic.IF_ICMPLE;
import org.apache.bcel.generic.IF_ICMPLT;
import org.apache.bcel.generic.IF_ICMPNE;
import org.apache.bcel.generic.IINC;
import org.apache.bcel.generic.ILOAD;
import org.apache.bcel.generic.INVOKESPECIAL;
import org.apache.bcel.generic.INVOKESTATIC;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.bcel.generic.IRETURN;
import org.apache.bcel.generic.ISTORE;
import org.apache.bcel.generic.ISUB;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.LCMP;
import org.apache.bcel.generic.LCONST;
import org.apache.bcel.generic.LDC;
import org.apache.bcel.generic.LDC2_W;
import org.apache.bcel.generic.LDC_W;
import org.apache.bcel.generic.LSHL;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.NEW;
import org.apache.bcel.generic.NEWARRAY;
import org.apache.bcel.generic.NOP;
import org.apache.bcel.generic.POP;
import org.apache.bcel.generic.PUTFIELD;
import org.apache.bcel.generic.PUTSTATIC;
import org.apache.bcel.generic.RETURN;

public class MembersHelper {
	private Queue<Constant> constants;
	private Queue<Method> methods;
	private Queue<Field> fields;
	private Queue<JavaClass> interfaces;
	private Queue<Attribute> attributes;
	private HashMap<Method,ArrayDeque<Instruction>> methodsInstructions;

	public MembersHelper(String classFilePath) {
		try {
			ClassParser parser = new ClassParser(classFilePath);
			JavaClass c = parser.parse();

			this.constants = findConstants(c);
			this.methods = findMethods(c);
			this.fields = findFields(c);
			this.interfaces = findInterfaces(c);
			this.attributes = findAttributes(c);
			this.methodsInstructions = findMethodsInstructions(c);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HashMap<Method, ArrayDeque<Instruction>> getMethodsInstructions() {
		return methodsInstructions;
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
	
	private HashMap<Method,ArrayDeque<Instruction>> findMethodsInstructions(JavaClass javaClass){
		HashMap<Method, ArrayDeque<Instruction>> mapToReturn = new HashMap<Method, ArrayDeque<Instruction>>();
		ArrayDeque<Method> methodQueue = findMethods(javaClass);
		while(!methodQueue.isEmpty()){
			Method m = methodQueue.poll();
			mapToReturn.put(m, findInstructions(javaClass, m));
		}
		return mapToReturn;
	}
	
}
