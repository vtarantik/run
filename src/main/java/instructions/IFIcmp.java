package instructions;

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
	
	public IFIcmp(IfIcmpType ificmpType,short opcode,int index,int length){
		super(opcode,index,length);
		this.ificmpType = ificmpType;
	}

	public void execute() {
		//TODO switch for different types
		System.out.println(toString());
	}

}
