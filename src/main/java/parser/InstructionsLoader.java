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
import instructions.Dconst;
import instructions.Dload;
import instructions.Dup;
import instructions.Fcmp;
import instructions.Fconst;
import instructions.Fload;
import instructions.Fstore;
import instructions.GetField;
import instructions.GetStatic;
import instructions.Goto;
import instructions.I2C;
import instructions.IAbstractInstruction;
import instructions.IFIcmp;
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
import instructions.NewArray;
import instructions.Nop;
import instructions.Pop;
import instructions.PutField;
import instructions.PutStatic;
import instructions.Return;
import instructions.Dcmp.DcmpType;
import instructions.Fcmp.FcmpType;
import instructions.IFIcmp.IfIcmpType;
import instructions.If.Iftype;

import java.util.ArrayDeque;
import java.util.HashMap;

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
import org.apache.bcel.generic.LCMP;
import org.apache.bcel.generic.LCONST;
import org.apache.bcel.generic.LDC;
import org.apache.bcel.generic.LDC2_W;
import org.apache.bcel.generic.LDC_W;
import org.apache.bcel.generic.LSHL;
import org.apache.bcel.generic.NEW;
import org.apache.bcel.generic.NEWARRAY;
import org.apache.bcel.generic.NOP;
import org.apache.bcel.generic.POP;
import org.apache.bcel.generic.PUTFIELD;
import org.apache.bcel.generic.PUTSTATIC;
import org.apache.bcel.generic.RETURN;

public class InstructionsLoader {
	private MembersHelper membersHelper;
	private HashMap<Method,ArrayDeque<Instruction>> methodsInstructions;
	private HashMap<Method, ArrayDeque<IAbstractInstruction>> methodsLoadedInstructions;
	
	public InstructionsLoader(String className){
		membersHelper = new MembersHelper(className);
		methodsInstructions = membersHelper.getMethodsInstructions();
		methodsLoadedInstructions = loadInstructions();
		printLoadedInstructions();
	}
	
	/*
	 * Method loads instructions and creates AbstractInstructions from them
	 */
	public HashMap<Method,ArrayDeque<IAbstractInstruction>> loadInstructions(){
		HashMap<Method,ArrayDeque<IAbstractInstruction>> loadedMethodsInstructions = new HashMap<>();
		for (Method method : methodsInstructions.keySet()) {
			ArrayDeque<IAbstractInstruction> loadedInstructions = new ArrayDeque<>();
			ArrayDeque<Instruction> instructions = methodsInstructions.get(method).clone();
			while(!instructions.isEmpty()){
				Instruction i = instructions.poll();
					saveInstruction(loadedInstructions, i);
			}
			loadedMethodsInstructions.put(method, loadedInstructions);
		}
		return loadedMethodsInstructions;
	}
	
	private void printLoadedInstructions(){
		for (Method m : methodsInstructions.keySet()) {
			while(!methodsInstructions.get(m).isEmpty()){
				System.out.println(methodsInstructions.get(m).poll());
				methodsLoadedInstructions.get(m).poll().execute();
			}
		}
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
		}else if(i instanceof IF_ICMPEQ){
			instructionToStore = new IFIcmp(IfIcmpType.ICMPEQ);
		}else if(i instanceof IF_ICMPGE){
			instructionToStore = new IFIcmp(IfIcmpType.ICMPGE);
		}else if(i instanceof IF_ICMPGT){
			instructionToStore = new IFIcmp(IfIcmpType.ICMPGT);
		}else if(i instanceof IF_ICMPLE){
			instructionToStore = new IFIcmp(IfIcmpType.ICMPLE);
		}else if(i instanceof IF_ICMPLT){
			instructionToStore = new IFIcmp(IfIcmpType.ICMPLT);
		}else if(i instanceof IF_ICMPNE){
			instructionToStore = new IFIcmp(IfIcmpType.ICMPNE);
		}else if(i instanceof IFNONNULL){
			instructionToStore = new IfNonNull();
		}else if(i instanceof IFNULL){
			instructionToStore = new Ifnull();
		}else if(i instanceof IINC){
			instructionToStore = new Iinc();
		}else if(i instanceof ILOAD){
			instructionToStore = new Iload();
		}else if(i instanceof INVOKESPECIAL){
			instructionToStore = new InvokeSpecial();
		}else if(i instanceof INVOKESTATIC){
			instructionToStore = new InvokeStatic();
		}else if(i instanceof INVOKEVIRTUAL){
			instructionToStore = new InvokeVirtual();
		}else if(i instanceof IRETURN){
			instructionToStore = new Ireturn();
		}else if(i instanceof ISTORE){
			instructionToStore = new Istore();
		}else if(i instanceof ISUB){
			instructionToStore = new Isub();
		}else if(i instanceof LCMP){
			instructionToStore = new Lcmp();
		}else if(i instanceof LCONST){
			instructionToStore = new Lconst();
		}else if(i instanceof LDC){
			instructionToStore = new Ldc();
		}else if(i instanceof LDC2_W){
			instructionToStore = new Ldc2W();
		}else if(i instanceof LDC_W){
			instructionToStore = new LdcW();
		}else if(i instanceof LSHL){
			instructionToStore = new Lshl();
		}else if(i instanceof NEW){
			instructionToStore = new New();
		}else if(i instanceof NEWARRAY){
			instructionToStore = new NewArray();
		}else if(i instanceof NOP){
			instructionToStore = new Nop();
		}else if(i instanceof POP){
			instructionToStore = new Pop();
		}else if(i instanceof PUTFIELD){
			instructionToStore = new PutField();
		}else if(i instanceof PUTSTATIC){
			instructionToStore = new PutStatic();
		}else if(i instanceof RETURN){
			instructionToStore = new Return();
		}else{
			throw new IllegalArgumentException("NOT SUPPORTED INSTRUCTION: "+i.getName());
		}
		
		collectionToSaveTo.add(instructionToStore);
	}
	
	
}
