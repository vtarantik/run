package parser;

import heap.MyHeap;
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
import instructions.AbstractInstruction;
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

import model.MyClass;

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

import stack.MyStack;
import stack.StackFrame;

public class Parser {
	private HashMap<Method, ArrayDeque<Instruction>> methodsInstructions;
	private HashMap<Method, ArrayDeque<AbstractInstruction>> methodsLoadedInstructions;
	private String className;

	public Parser(String className) {
		this.className = className;
		try {
			MembersHelper.init(className);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		MyClass c = MyHeap.getInstance().getClassType(className);
		c.runInit();
		c.runMain();
	}
	
}
