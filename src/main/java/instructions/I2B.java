package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * Description

 The value on the top of the operand stack must be of type int. It is popped
 from the operand stack, truncated to a byte, then sign-extended to an int result. 
 That result is pushed onto the operand stack.

 Notes

 The i2b instruction performs a narrowing primitive conversion (JLS §5.1.3).
 It may lose information about the overall magnitude of value. The result may 
 also not have the same sign as value.
 */
public class I2B extends AbstractInstruction {

	public I2B(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		int poppedInt = (int) currentStackFrame.popOperand();
		currentStackFrame.pushOperand((byte)poppedInt);
	}

}
