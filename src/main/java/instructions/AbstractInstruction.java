package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

public abstract class AbstractInstruction {
	protected short opcode;
	protected int index;
	protected int length;
	protected MyClass ownerClass;
	
	public AbstractInstruction(short opcode,int index,int length,MyClass ownerClass){
		this.opcode = opcode;
		this.index = index;
		this.length = length;
		this.ownerClass = ownerClass;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName().toUpperCase()+ " OC: "+ opcode+" idx: "+index+" length: "+length;
	}
	
	public abstract void execute();
}
