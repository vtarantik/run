package instructions;

public class Fcmp implements IAbstractInstruction{
	private FcmpType fcmpType;
	
	public enum FcmpType{
		FCMPL,
		FCMPG
	}
	
	public Fcmp(FcmpType fcmpType){
		this.fcmpType = fcmpType;
	}

	public void execute() {
		System.out.println("FCMP");
	}

}
