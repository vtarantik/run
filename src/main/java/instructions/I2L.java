package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * Description

 The value on the top of the operand stack must be of type int. It is popped from the 
 operand stack and sign-extended to a long result. That result is pushed onto the operand stack.

 Notes

 The i2l instruction performs a widening primitive conversion (JLS §5.1.2).
 Because all values of type int are exactly representable by type long, the conversion 
 is exact.
 */
public class I2L extends AbstractInstruction {

	public I2L(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		int poppedInt = (int) currentStackFrame.popOperand();
		currentStackFrame.pushOperand((long)poppedInt);
	}

}
