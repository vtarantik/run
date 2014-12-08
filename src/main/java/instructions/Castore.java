package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * Description

 The arrayref must be of type reference and must refer to an array whose components
 are of type char. The index and the value must both be of type int. The arrayref, 
 index, and value are popped from the operand stack. The int value is truncated to
 a char and stored as the component of the array indexed by index.

 Run-time Exceptions

 If arrayref is null, castore throws a NullPointerException.

 Otherwise, if index is not within the bounds of the array referenced by arrayref,
 the castore instruction throws an ArrayIndexOutOfBoundsException.
 */
public class Castore extends AbstractInstruction {

	public Castore(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		char[] poppedArray = (char [])currentStackFrame.popOperand();
		int index = (int) currentStackFrame.popOperand();
		int value = (int) currentStackFrame.popOperand();
		char charValue = (char)value;
		poppedArray[index] = charValue;
	}

}
