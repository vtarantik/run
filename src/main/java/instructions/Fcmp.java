package instructions;

public class Fcmp extends AbstractInstruction{
	private FcmpType fcmpType;
	
	public enum FcmpType{
		FCMPL,
		FCMPG
	}
	
	public Fcmp(FcmpType fcmpType,short opcode,int index,int length){
		super(opcode,index,length);
		this.fcmpType = fcmpType;
	}

	public void execute() {
		System.out.println(toString());
	}

}
