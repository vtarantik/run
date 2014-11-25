package instructions;

public class Nop implements IAbstractInstruction{

	public void execute() {
		System.out.println("NOP");
	}

}
