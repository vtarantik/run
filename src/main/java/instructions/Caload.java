package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * The arrayref must be of type reference and must refer to an array whose components
 *  are of type char. The index must be of type int. Both arrayref and index are popped
 *   from the operand stack. The component of the array at index is retrieved and 
 *   zero-extended to an int value. That value is pushed onto the operand stack.

 Run-time Exceptions

 If arrayref is null, caload throws a NullPointerException.

 Otherwise, if index is not within the bounds of the array referenced by arrayref, 
 the caload instruction throws an ArrayIndexOutOfBoundsException.
 */
public class Caload extends AbstractInstruction {

	public Caload(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		char[] arr = (char[]) currentStackFrame.popOperand();
        int index = (int) currentStackFrame.popOperand();
        currentStackFrame.pushOperand((int)arr[index]);
	}

}
