package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * Push the float constant <f> (0.0, 1.0, or 2.0) onto the operand stack.
 */
public class Fconst extends AbstractInstruction{
	private Number value;

	public Fconst(Number value,short opcode, int index, int length,MyClass ownerClass) {
		super(opcode, index, length,ownerClass);
		this.value = value;
	}

	public void execute(Method m) {
		System.out.println(toString());
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		float floatToPush = value.floatValue();
		currentStackFrame.pushOperand(floatToPush);
	}
	
}
