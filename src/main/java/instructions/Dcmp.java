package instructions;

public class Dcmp extends AbstractInstruction{
	private DcmpType dcmpType;
	
	public Dcmp(DcmpType dcmpType,short opcode,int index,int length){
		super(opcode,index,length);
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
