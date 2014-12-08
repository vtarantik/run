package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * Description

 Both value1 and value2 must be of type int. The values are popped from the operand stack. 
 The int result is value1 + value2. The result is pushed onto the operand stack.

 The result is the 32 low-order bits of the true mathematical result in a sufficiently wide
 two's-complement format, represented as a value of type int. If overflow occurs, then the
 sign of the result may not be the same as the sign of the mathematical sum of the two values.

 Despite the fact that overflow may occur, execution of an iadd instruction never throws
 a run-time exception.
 */
public class Iadd extends AbstractInstruction {

	public Iadd(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		int poppedInt1 = (int) currentStackFrame.popOperand();
		int poppedInt2 = (int) currentStackFrame.popOperand();
		currentStackFrame.pushOperand(poppedInt1+poppedInt2);
	}

}
