package instructions;

public class Aload extends AbstractInstruction{
	
	public Aload(short opcode, int index, int length) {
		super(opcode,index,length);
	}

	public void execute() {
		System.out.println(toString());
	}

}
