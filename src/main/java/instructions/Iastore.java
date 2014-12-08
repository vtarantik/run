package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * Description

 The arrayref must be of type reference and must refer to an array whose components are 
 of type int. Both index and value must be of type int. The arrayref, index, and value 
 are popped from the operand stack. The int value is stored as the component of the
 array indexed by index.

 Run-time Exceptions

 If arrayref is null, iastore throws a NullPointerException.

 Otherwise, if index is not within the bounds of the array referenced by arrayref,
s the iastore instruction throws an ArrayIndexOutOfBoundsException.
 */
public class Iastore extends AbstractInstruction {

	public Iastore(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		int[] poppedArray = (int [])currentStackFrame.popOperand();
		int index = (int) currentStackFrame.popOperand();
		int value = (int) currentStackFrame.popOperand();
		poppedArray[index] = value;
	}

}
