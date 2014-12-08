package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * Description

 Push the int constant <i> (-1, 0, 1, 2, 3, 4 or 5) onto the operand stack.

 Notes

 Each of this family of instructions is equivalent to bipush <i> for the respective
 value of <i>, except that the operand <i> is implicit.
 */
public class Iconst extends AbstractInstruction {
	private Number value;

	public Iconst(Number value,short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
		this.value = value;
	}

	public void execute(Method m) {
		System.out.println(toString());
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		float intToPush = value.intValue();
		currentStackFrame.pushOperand(intToPush);
	}

}
