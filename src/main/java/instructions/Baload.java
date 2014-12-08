package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * Description

The arrayref must be of type reference and must refer to an array whose components
 are of type byte or of type boolean. The index must be of type int. Both arrayref 
 and index are popped from the operand stack. The byte value in the component of the
  array at index is retrieved, sign-extended to an int value, and pushed onto the top
   of the operand stack.

Run-time Exceptions

If arrayref is null, baload throws a NullPointerException.

Otherwise, if index is not within the bounds of the array referenced by arrayref,
 the baload instruction throws an ArrayIndexOutOfBoundsException.

Notes

The baload instruction is used to load values from both byte and boolean arrays.
 In Oracle's Java Virtual Machine implementation, boolean arrays - that is, arrays 
 of type T_BOOLEAN (§2.2, §newarray) - are implemented as arrays of 8-bit values. 
 Other implementations may implement packed boolean arrays; the baload instruction
  of such implementations must be used to access those arrays.
 */
public class Baload extends AbstractInstruction{

	public Baload(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length,ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		Object poppedArray = currentStackFrame.popOperand();
		int poppedIndex = (int) currentStackFrame.popOperand();
		if(poppedArray.getClass().getComponentType()==boolean.class){
			boolean[] arrayToReturn = (boolean [])poppedArray;
			boolean b = arrayToReturn[poppedIndex];
			currentStackFrame.pushOperand(b);
		}else if(poppedArray.getClass().getComponentType()==byte.class){
			byte[] arrayToReturn = (byte [])poppedArray;
			byte b = arrayToReturn[poppedIndex];
			int intToPush = (int) b;
			currentStackFrame.pushOperand(intToPush);
		}else {
			throw new IllegalArgumentException("WRONG ARRAY TYPE");
		}
	}

}
