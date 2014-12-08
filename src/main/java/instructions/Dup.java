package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * Duplicate the top value on the operand stack and push the duplicated value onto the operand stack.

 The dup instruction must not be used unless value is a value of a category 1 computational type (§2.11.1).
 */
public class Dup extends AbstractInstruction {

	public Dup(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		Object topObject = currentStackFrame.peekOnOperand();
		currentStackFrame.pushOperand(topObject);
	}

}
