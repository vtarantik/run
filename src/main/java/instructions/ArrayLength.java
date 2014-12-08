package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * The arrayref must be of type reference and must refer to an array.
 *  It is popped from the operand stack. The length of the array it 
 *  references is determined. That length is pushed onto the operand stack as an int.

 Run-time Exceptions

 If the arrayref is null, the arraylength instruction throws a NullPointerException.
 */
public class ArrayLength extends AbstractInstruction {

	public ArrayLength(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		Object[] array = (Object[])currentStackFrame.popOperand();
		int valueToReturn = array.length;
		currentStackFrame.pushOperand(valueToReturn);
	}

}
