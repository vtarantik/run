package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * Push the double constant <d> (0.0 or 1.0) onto the operand stack.
 */
public class Dconst extends AbstractInstruction{
	private Number value;

	public Dconst(Number value,short opcode, int index, int length,MyClass ownerClass) {
		super(opcode, index, length,ownerClass);
		this.value = value;
	}

	public void execute(Method m) {
		System.out.println(toString());
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		double d = value.doubleValue();
		currentStackFrame.pushOperand(d);
		
	}

}
