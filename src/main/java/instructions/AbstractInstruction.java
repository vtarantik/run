package instructions;

public abstract class AbstractInstruction {
	protected short opcode;
	protected int index;
	protected int length;
	
	public AbstractInstruction(short opcode,int index,int length){
		this.opcode = opcode;
		this.index = index;
		this.length = length;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName().toUpperCase()+ " OC: "+ opcode+" idx: "+index+" length: "+length;
	}
	
	public abstract void execute();
}
