package instructions;

public class IFIcmp implements IAbstractInstruction{
	private IfIcmpType ificmpType;
	
	public enum IfIcmpType{
		ICMPEQ,
		ICMPGE,
		ICMPGT,
		ICMPLE,
		ICMPLT,
		ICMPNE
	}
	
	public IFIcmp(IfIcmpType ificmpType){
		this.ificmpType = ificmpType;
	}

	public void execute() {
		//TODO switch for different types
		System.out.println("IFICMP"+ificmpType.name());
	}

}
