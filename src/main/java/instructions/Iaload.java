package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * Description

 The arrayref must be of type reference and must refer to an array whose components 
 are of type int. The index must be of type int. Both arrayref and index are popped
 from the operand stack. The int value in the component of the array at index is 
 retrieved and pushed onto the operand stack.

 Run-time Exceptions

 If arrayref is null, iaload throws a NullPointerException.

 Otherwise, if index is not within the bounds of the array referenced by arrayref, 
 the iaload instruction throws an ArrayIndexOutOfBoundsException.
 */
public class Iaload extends AbstractInstruction {

	public Iaload(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		int[]poppedArray = (int[])currentStackFrame.popOperand();
		int index = (int)currentStackFrame.popOperand();
		currentStackFrame.pushOperand(poppedArray[index]);
	}

}
