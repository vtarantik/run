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
import instructions.Iadd;
import instructions.Iastore;
import instructions.Iconst;
import instructions.If;
import instructions.Iload;
import instructions.New;
import instructions.If.Iftype;

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
import org.apache.bcel.generic.ILOAD;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.NEW;

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
			// TODO Auto-generated catch block
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

	private ArrayDeque<IAbstractInstruction> findInstructions(JavaClass javaClass, Method method) {
		ArrayDeque<IAbstractInstruction> instructionsQueue = new ArrayDeque<IAbstractInstruction>();

		ConstantPoolGen cpg = new ConstantPoolGen(javaClass.getConstantPool());
		MethodGen mg = new MethodGen(method, javaClass.getClassName(), cpg);
		InstructionList il = mg.getInstructionList();
		Instruction[] instructions = il.getInstructions();
		for (int i = 0; i < il.getLength(); i++) {
			//instructionsQueue.add(instructions[i]);
		}
		
		return instructionsQueue;

	}
	
	private HashMap<Method,ArrayDeque<Instruction>> findMethodsInstructions(JavaClass javaClass){
		HashMap<Method, ArrayDeque<Instruction>> mapToReturn = new HashMap<Method, ArrayDeque<Instruction>>();
		ArrayDeque<Method> methodQueue = findMethods(javaClass);
		while(!methodQueue.isEmpty()){
			Method m = methodQueue.poll();
			//mapToReturn.put(m, findInstructions(javaClass, m));
		}
		return mapToReturn;
	}
	
	//TODO for parametrized instructions create constructors
	private void saveInstruction(ArrayDeque<IAbstractInstruction> collectionToSaveTo, Instruction i){
		IAbstractInstruction instructionToStore;
		if(i instanceof ALOAD ){
			instructionToStore = new Aload();
		}else if(i instanceof NEW){
			instructionToStore = new New();
		}else if(i instanceof GOTO){
			instructionToStore = new Goto();
		}else if(i instanceof IFEQ){
			instructionToStore = new If(Iftype.IFEQ);
		}else if(i instanceof IFGE){
			instructionToStore = new If(Iftype.IFGE);
		}else if(i instanceof IFGT){
			instructionToStore = new If(Iftype.IFGT);
		}else if (i instanceof IFLE){
			instructionToStore = new If(Iftype.IFLE);
		}else if(i instanceof IFLT){
			instructionToStore = new If(Iftype.IFLT);
		}else if(i instanceof IFNE){
			instructionToStore = new If(Iftype.IFNE);
		}else if(i instanceof IFNULL){
			instructionToStore = new If(Iftype.IFNULL);
		}else if(i instanceof IFNONNULL){
			instructionToStore = new If(Iftype.IFNONNULL);
		}else if(i instanceof ACONST_NULL){
			instructionToStore = new AconstNull();
		}else if(i instanceof ARETURN){
			instructionToStore = new Areturn();
		}else if(i instanceof ARRAYLENGTH){
			instructionToStore = new ArrayLength();
		}else if(i instanceof ASTORE){
			instructionToStore = new Astore();
		}else if(i instanceof BALOAD){
			instructionToStore = new Baload();
		}else if(i instanceof BASTORE){
			instructionToStore = new Bastore();
		}else if(i instanceof BIPUSH){
			instructionToStore = new Bipush();
		}else if(i instanceof CALOAD){
			instructionToStore = new Caload();
		}else if(i instanceof CASTORE){
			instructionToStore = new Castore();
		}else if(i instanceof DCMPG){
			instructionToStore = new Dcmp(DcmpType.DCMPG);
		}else if(i instanceof DCMPL){
			instructionToStore = new Dcmp(DcmpType.DCMPL);
		}else if(i instanceof DCONST){
			instructionToStore = new Dconst();
		}else if(i instanceof DLOAD){
			instructionToStore = new Dload();
		}else if(i instanceof DUP){
			instructionToStore = new Dup();
		}else if(i instanceof FCMPG){
			instructionToStore = new Fcmp(FcmpType.FCMPG);
		}else if(i instanceof FCMPL){
			instructionToStore = new Fcmp(FcmpType.FCMPL);
		}else if(i instanceof FCONST){
			instructionToStore = new Fconst();
		}else if(i instanceof FLOAD){
			instructionToStore = new Fload();
		}else if(i instanceof FSTORE){
			instructionToStore = new Fstore();
		}else if(i instanceof GETFIELD){
			instructionToStore = new GetField();
		}else if(i instanceof GETSTATIC){
			instructionToStore = new GetStatic();
		}else if(i instanceof GOTO){
			instructionToStore = new Goto();
		}else if(i instanceof CHECKCAST){
			instructionToStore = new Checkcast();
		}else if(i instanceof I2B){
			instructionToStore = new instructions.I2B();
		}else if(i instanceof org.apache.bcel.generic.I2C){
			instructionToStore = new I2C();
		}else if(i instanceof I2L){
			instructionToStore = new instructions.I2L();
		}else if(i instanceof IADD){
			instructionToStore = new Iadd();
		}else if(i instanceof ILOAD){
			instructionToStore = new Iload();
		}else if(i instanceof IASTORE){
			instructionToStore = new Iastore();
		}else if(i instanceof ICONST){
			instructionToStore = new Iconst();
		}
	}

}
