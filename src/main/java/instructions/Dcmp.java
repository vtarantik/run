package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

public class Dcmp extends AbstractInstruction{
	private DcmpType dcmpType;
	
	public Dcmp(DcmpType dcmpType,short opcode,int index,int length,MyClass ownerClass){
		super(opcode,index,length,ownerClass);
		this.dcmpType = dcmpType;
	}
	public enum DcmpType{
		DCMPG,
		DCMPL
	}

	public void execute() {
		System.out.println(toString());
	}

}
