package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

public class Fcmp extends AbstractInstruction{
	private FcmpType fcmpType;
	
	public enum FcmpType{
		FCMPL,
		FCMPG
	}
	
	public Fcmp(FcmpType fcmpType,short opcode,int index,int length,MyClass ownerClass){
		super(opcode,index,length,ownerClass);
		this.fcmpType = fcmpType;
	}

	public void execute() {
		System.out.println(toString());
	}

}
