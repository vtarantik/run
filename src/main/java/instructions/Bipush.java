package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * The immediate byte is sign-extended to an int value. That value is pushed onto the operand stack.
 */
public class Bipush extends AbstractInstruction{
	private Number value;

	public Bipush(Number value,short opcode, int index, int length,MyClass ownerClass) {
		super(opcode, index, length,ownerClass);
		this.value = value;
	}

	public void execute(Method m) {
		int result = value.intValue();
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		currentStackFrame.pushOperand(result);
	}
	
}
