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

import stack.StackFrame;

public class Parser {
	private HashMap<Method, ArrayDeque<Instruction>> methodsInstructions;
	private HashMap<Method, ArrayDeque<AbstractInstruction>> methodsLoadedInstructions;

	public Parser(String className) {
		MembersHelper.init(className);
		methodsInstructions = MembersHelper.getMethodsInstructions();
	}

	/*
	 * Method loads instructions and creates AbstractInstructions from them
	 */
	public ArrayDeque<AbstractInstruction> loadInstructions(Method method) {
		ArrayDeque<AbstractInstruction> loadedInstructions = new ArrayDeque<>();
		ArrayDeque<Instruction> instructions = methodsInstructions.get(method)
				.clone();
		while (!instructions.isEmpty()) {
			Instruction i = instructions.poll();
			saveInstruction(loadedInstructions, i);
		}
		return loadedInstructions;
	}

	// TODO for parametrized instructions create constructors
	private void saveInstruction(
			ArrayDeque<AbstractInstruction> collectionToSaveTo, Instruction i) {
		AbstractInstruction instructionToStore;
		if (i instanceof ALOAD) {
			ALOAD aload = (ALOAD)i;
			instructionToStore = new Aload(aload.getOpcode(),aload.getIndex(),aload.getLength());
		} else if (i instanceof NEW) {
			NEW n = (NEW) i;
			instructionToStore = new New(n.getOpcode(), n.getIndex(),n.getLength());
		} else if (i instanceof GOTO) {
			GOTO go = (GOTO) i;
			instructionToStore = new Goto(go.getOpcode(),go.getIndex(),go.getLength());
		} else if (i instanceof IFEQ) {
			IFEQ ifeq = (IFEQ)i;
			instructionToStore = new If(Iftype.IFEQ,ifeq.getOpcode(),ifeq.getIndex(),ifeq.getLength());
		} else if (i instanceof IFGE) {
			IFGE ifge = (IFGE)i;
			instructionToStore = new If(Iftype.IFGE,ifge.getOpcode(),ifge.getIndex(),ifge.getLength());
		} else if (i instanceof IFGT) {
			IFGT ifgt = (IFGT)i;
			instructionToStore = new If(Iftype.IFGT,ifgt.getOpcode(),ifgt.getIndex(),ifgt.getLength());
		} else if (i instanceof IFLE) {
			IFLE ifle = (IFLE)i;
			instructionToStore = new If(Iftype.IFLE,ifle.getOpcode(),ifle.getIndex(),ifle.getLength());
		} else if (i instanceof IFLT) {
			IFLT iflt = (IFLT)i;
			instructionToStore = new If(Iftype.IFLT,iflt.getOpcode(),iflt.getIndex(),iflt.getLength());
		} else if (i instanceof IFNE) {
			IFNE ifne = (IFNE)i;
			instructionToStore = new If(Iftype.IFNE,ifne.getOpcode(),ifne.getIndex(),ifne.getLength());
		} else if (i instanceof IFNULL) {
			IFNULL ifnull = (IFNULL)i;
			instructionToStore = new If(Iftype.IFNULL,ifnull.getOpcode(),ifnull.getIndex(),ifnull.getLength());
		} else if (i instanceof IFNONNULL) {
			IFNONNULL ifnonnull = (IFNONNULL)i;
			instructionToStore = new If(Iftype.IFNONNULL,ifnonnull.getOpcode(),ifnonnull.getIndex(),ifnonnull.getLength());
		} else if (i instanceof ACONST_NULL) {
			ACONST_NULL aconst_NULL = (ACONST_NULL)i;
			instructionToStore = new AconstNull(aconst_NULL.getOpcode(),-1,aconst_NULL.getLength());
		} else if (i instanceof ARETURN) {
			ARETURN aret = (ARETURN)i;
			instructionToStore = new Areturn(aret.getOpcode(),-1,aret.getLength());
		} else if (i instanceof ARRAYLENGTH) {
			ARRAYLENGTH al = (ARRAYLENGTH)i;
			instructionToStore = new ArrayLength(al.getOpcode(),-1,al.getLength());
		} else if (i instanceof ASTORE) {
			ASTORE astore = (ASTORE)i;
			instructionToStore = new Astore(astore.getOpcode(),astore.getIndex(),astore.getLength());
		} else if (i instanceof BALOAD) {
			BALOAD bal = (BALOAD)i;
			instructionToStore = new Baload(bal.getOpcode(),-1,bal.getLength());
		} else if (i instanceof BASTORE) {
			BASTORE bas = (BASTORE)i;
			instructionToStore = new Bastore(bas.getOpcode(),-1,bas.getLength());
		} else if (i instanceof BIPUSH) {
			BIPUSH bp = (BIPUSH)i;
			instructionToStore = new Bipush(bp.getOpcode(),-1,bp.getLength());
		} else if (i instanceof CALOAD) {
			CALOAD cl = (CALOAD)i;
			instructionToStore = new Caload(cl.getOpcode(),-1,cl.getLength());
		} else if (i instanceof CASTORE) {
			CASTORE cs = (CASTORE)i;
			instructionToStore = new Castore(cs.getOpcode(),-1,cs.getLength());
		} else if (i instanceof DCMPG) {
			DCMPG d = (DCMPG)i;
			instructionToStore = new Dcmp(DcmpType.DCMPG,d.getOpcode(),-1,d.getLength());
		} else if (i instanceof DCMPL) {
			DCMPL d = (DCMPL)i;
			instructionToStore = new Dcmp(DcmpType.DCMPL,d.getOpcode(),-1,d.getLength());
		} else if (i instanceof DCONST) {
			DCONST d = (DCONST)i;
			instructionToStore = new Dconst(d.getOpcode(),-1,d.getLength());
		} else if (i instanceof DLOAD) {
			DLOAD d = (DLOAD)i;
			instructionToStore = new Dload(d.getOpcode(),d.getIndex(),d.getLength());
		} else if (i instanceof DUP) {
			DUP d = (DUP)i;
			instructionToStore = new Dup(d.getOpcode(),-1,d.getLength());
		} else if (i instanceof FCMPG) {
			FCMPG f = (FCMPG)i;
			instructionToStore = new Fcmp(FcmpType.FCMPG,f.getOpcode(),-1,f.getLength());
		} else if (i instanceof FCMPL) {
			FCMPL f = (FCMPL)i;
			instructionToStore = new Fcmp(FcmpType.FCMPL,f.getOpcode(),-1,f.getLength());
		} else if (i instanceof FCONST) {
			FCONST f = (FCONST)i;
			instructionToStore = new Fconst(f.getOpcode(),-1,f.getLength());
		} else if (i instanceof FLOAD) {
			FLOAD f = (FLOAD)i;
			instructionToStore = new Fload(f.getOpcode(),f.getIndex(),f.getLength());
		} else if (i instanceof FSTORE) {
			FSTORE f = (FSTORE)i;
			instructionToStore = new Fstore(f.getOpcode(),f.getIndex(),f.getLength());
		} else if (i instanceof GETFIELD) {
			GETFIELD gf = (GETFIELD)i;
			instructionToStore = new GetField(gf.getOpcode(),gf.getIndex(),gf.getLength());
		} else if (i instanceof GETSTATIC) {
			GETSTATIC gs = (GETSTATIC)i;
			instructionToStore = new GetStatic(gs.getOpcode(),gs.getIndex(),gs.getLength());
		} else if (i instanceof CHECKCAST) {
			CHECKCAST ck = (CHECKCAST)i;
			instructionToStore = new Checkcast(ck.getOpcode(),ck.getIndex(),ck.getLength());
		} else if (i instanceof I2B) {
			I2B ib = (I2B)i;
			instructionToStore = new instructions.I2B(ib.getOpcode(),-1,ib.getLength());
		} else if (i instanceof org.apache.bcel.generic.I2C) {
			org.apache.bcel.generic.I2C ic = (org.apache.bcel.generic.I2C)i;
			instructionToStore = new I2C(ic.getOpcode(),-1,ic.getLength());
		} else if (i instanceof I2L) {
			I2L il = (I2L)i;
			instructionToStore = new instructions.I2L(il.getOpcode(),-1,il.getLength());
		} else if (i instanceof IADD) {
			IADD ia = (IADD)i;
			instructionToStore = new Iadd(ia.getOpcode(),-1,ia.getLength());
		} else if (i instanceof ILOAD) {
			ILOAD il = (ILOAD)i;
			instructionToStore = new Iload(il.getOpcode(),il.getIndex(),il.getLength());
		} else if (i instanceof IASTORE) {
			IASTORE is = (IASTORE)i;
			instructionToStore = new Iastore(is.getOpcode(),-1,is.getLength());
		} else if (i instanceof ICONST) {
			ICONST ic= (ICONST)i;
			instructionToStore = new Iconst(ic.getOpcode(),-1,ic.getLength());
		} else if (i instanceof IF_ICMPEQ) {
			IF_ICMPEQ ic = (IF_ICMPEQ)	i;
			instructionToStore = new IFIcmp(IfIcmpType.ICMPEQ,ic.getOpcode(),ic.getIndex(),ic.getLength());
		} else if (i instanceof IF_ICMPGE) {
			IF_ICMPGE ic = (IF_ICMPGE)i;
			instructionToStore = new IFIcmp(IfIcmpType.ICMPGE,ic.getOpcode(),ic.getIndex(),ic.getLength());
		} else if (i instanceof IF_ICMPGT) {
			IF_ICMPGT ic= (IF_ICMPGT)i;
			instructionToStore = new IFIcmp(IfIcmpType.ICMPGT,ic.getOpcode(),ic.getIndex(),ic.getLength());
		} else if (i instanceof IF_ICMPLE) {
			IF_ICMPLE ic = (IF_ICMPLE)i;
			instructionToStore = new IFIcmp(IfIcmpType.ICMPLE,ic.getOpcode(),ic.getIndex(),ic.getLength());
		} else if (i instanceof IF_ICMPLT) {
			IF_ICMPLT ic = (IF_ICMPLT)i;
			instructionToStore = new IFIcmp(IfIcmpType.ICMPLT,ic.getOpcode(),ic.getIndex(),ic.getLength());
		} else if (i instanceof IF_ICMPNE) {
			IF_ICMPNE ic = (IF_ICMPNE)i;
			instructionToStore = new IFIcmp(IfIcmpType.ICMPNE,ic.getOpcode(),ic.getIndex(),ic.getLength());
		} else if (i instanceof IINC) {
			IINC ic = (IINC)i;
			instructionToStore = new Iinc(ic.getIncrement(),ic.getOpcode(),ic.getIndex(),ic.getLength());
		} else if (i instanceof ILOAD) {
			ILOAD il = (ILOAD)i;
			instructionToStore = new Iload(il.getOpcode(),il.getIndex(),il.getLength());
		} else if (i instanceof INVOKESPECIAL) {
			INVOKESPECIAL is = (INVOKESPECIAL)i;
			instructionToStore = new InvokeSpecial(is.getOpcode(),is.getIndex(),is.getLength());
		} else if (i instanceof INVOKESTATIC) {
			INVOKESTATIC	is = (INVOKESTATIC)i;
			instructionToStore = new InvokeStatic(is.getOpcode(),is.getIndex(),is.getLength());
		} else if (i instanceof INVOKEVIRTUAL) {
			INVOKEVIRTUAL iv = (INVOKEVIRTUAL)i;
			instructionToStore = new InvokeVirtual(iv.getOpcode(),iv.getIndex(),iv.getLength());
		} else if (i instanceof IRETURN) {
			IRETURN ir = (IRETURN)i;
			instructionToStore = new Ireturn(ir.getOpcode(),-1,ir.getLength());
		} else if (i instanceof ISTORE) {
			ISTORE is = (ISTORE)i;
			instructionToStore = new Istore(is.getOpcode(),is.getIndex(),is.getLength());
		} else if (i instanceof ISUB) {
			ISUB is = (ISUB)i;
			instructionToStore = new Isub(is.getOpcode(),-1,is.getLength());
		} else if (i instanceof LCMP) {
			LCMP l = (LCMP)i;
			instructionToStore = new Lcmp(l.getOpcode(),-1,l.getLength());
		} else if (i instanceof LCONST) {
			LCONST lc = (LCONST)i;
			instructionToStore = new Lconst(lc.getOpcode(),-1,lc.getLength());
		} else if (i instanceof LDC) {
			LDC l = (LDC)i;
			instructionToStore = new Ldc(l.getOpcode(),l.getIndex(),l.getLength());
		} else if (i instanceof LDC2_W) {
			LDC2_W l = (LDC2_W)i;
			instructionToStore = new Ldc2W(l.getOpcode(),l.getIndex(),l.getLength());
		} else if (i instanceof LDC_W) {
			LDC_W l = (LDC_W)i;
			instructionToStore = new LdcW(l.getOpcode(),l.getIndex(),l.getLength());
		} else if (i instanceof LSHL) {
			LSHL l = (LSHL)i;
			instructionToStore = new Lshl(l.getOpcode(),-1,l.getLength());
		} else if (i instanceof NEWARRAY) {
			NEWARRAY	n = (NEWARRAY)i;
			instructionToStore = new NewArray(n.getOpcode(),-1,n.getLength());
		} else if (i instanceof NOP) {
			NOP n = (NOP)i;
			instructionToStore = new Nop(n.getOpcode(),-1,n.getLength());
		} else if (i instanceof POP) {
			POP p = (POP)i;
			instructionToStore = new Pop(p.getOpcode(),-1,p.getLength());
		} else if (i instanceof PUTFIELD) {
			PUTFIELD p = (PUTFIELD)i;
			instructionToStore = new PutField(p.getOpcode(),p.getIndex(),p.getLength());
		} else if (i instanceof PUTSTATIC) {
			PUTSTATIC p = (PUTSTATIC)i;
			instructionToStore = new PutStatic(p.getOpcode(),p.getIndex(),p.getLength());
		} else if (i instanceof RETURN) {
			RETURN r = (RETURN)i;
			instructionToStore = new Return(r.getOpcode(),-1,r.getLength());
		} else {
			throw new IllegalArgumentException("NOT SUPPORTED INSTRUCTION: "
					+ i.getName());
		}

		collectionToSaveTo.add(instructionToStore);
	}

	private Method getMainMethod() {
		for (Method method : MembersHelper.getMethods()) {
			if (method.getName().equals("main")) {
				return method;
			}
		}
		throw new IllegalArgumentException("NO MAIN METHOD");
	}

	private Method getInitMethod() {
		for (Method method : MembersHelper.getMethods()) {
			if (method.getName().equals("<init>")) {
				return method;
			}
		}
		throw new IllegalArgumentException("NO CONSTRUCTOR");
	}

	private void runMethod(Method m) {
		// StackFrame stackFrame = new StackFrame();
		ArrayDeque<AbstractInstruction> instructions = loadInstructions(m);
		for (AbstractInstruction instruction : instructions) {
			instruction.execute();
		}
	}
	
	public void run(){
		Method init = getInitMethod();
		Method main = getMainMethod();
		
		runMethod(init);
		runMethod(main);
	}
	
}
