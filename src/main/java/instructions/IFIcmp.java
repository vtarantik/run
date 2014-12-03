package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

public class IFIcmp extends AbstractInstruction{
	private IfIcmpType ificmpType;
	
	public enum IfIcmpType{
		ICMPEQ,
		ICMPGE,
		ICMPGT,
		ICMPLE,
		ICMPLT,
		ICMPNE
	}
	
	public IFIcmp(IfIcmpType ificmpType,short opcode,int index,int length,MyClass ownerClass){
		super(opcode,index,length,ownerClass);
		this.ificmpType = ificmpType;
	}

	public void execute() {
		//TODO switch for different types
		System.out.println(toString());
	}

}
