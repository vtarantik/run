package instructions;

public class Dcmp implements IAbstractInstruction{
	private DcmpType dcmpType;
	
	public Dcmp(DcmpType dcmpType){
		this.dcmpType = dcmpType;
	}
	public enum DcmpType{
		DCMPG,
		DCMPL
	}

	public void execute() {
		System.out.println("DCMP");
	}

}
