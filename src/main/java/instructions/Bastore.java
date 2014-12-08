package instructions;

import model.MyClass;



/*
 * Description

 The arrayref must be of type reference and must refer to an array whose components 
 are of type byte or of type boolean. The index and the value must both be of type int. 
 The arrayref, index, and value are popped from the operand stack. The int value is
 truncated to a byte and stored as the component of the array indexed by index.

 Run-time Exceptions

 If arrayref is null, bastore throws a NullPointerException.

 Otherwise, if index is not within the bounds of the array referenced by arrayref,
 the bastore instruction throws an ArrayIndexOutOfBoundsException.

 Notes

 The bastore instruction is used to store values into both byte and boolean arrays.
 In Oracle's Java Virtual Machine implementation, boolean arrays - that is, arrays
 of type T_BOOLEAN (§2.2, §newarray) - are implemented as arrays of 8-bit values.
 Other implementations may implement packed boolean arrays; in such implementations
 the bastore instruction must be able to store boolean values into packed boolean 
 arrays as well as byte values into byte arrays.
 */
import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * Description

 The arrayref must be of type reference and must refer to an array whose components are of type byte or of type boolean.
 The index and the value must both be of type int. The arrayref, index, and value are popped from the operand stack.
 The int value is truncated to a byte and stored as the component of the array indexed by index.

 Run-time Exceptions

 If arrayref is null, baload throws a NullPointerException.

 Otherwise, if index is not within the bounds of the array referenced by arrayref, the baload instruction throws an ArrayIndexOutOfBoundsException.

 Notes

 The baload instruction is used to load values from both byte and boolean arrays. In Oracle's Java Virtual Machine implementation, boolean arrays - that is, arrays of type T_BOOLEAN (§2.2, §newarray) - are implemented as arrays of 8-bit values. Other implementations may implement packed boolean arrays; the baload instruction of such implementations must be used to access those arrays.
 */
public class Bastore extends AbstractInstruction {

	public Bastore(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		Object poppedArray = currentStackFrame.popOperand();
		int index = (int) currentStackFrame.popOperand();
		int value = (int) currentStackFrame.popOperand();
		if(poppedArray.getClass().getComponentType()==byte.class){
			byte[] array = (byte[])poppedArray;
			byte byteValue = (byte)value;
			array[index] = byteValue;
		}else if(poppedArray.getClass().getComponentType()==boolean.class){
			boolean[] array = (boolean[])poppedArray;
			boolean booleanValue = value==0?false:true;
			array[index] = booleanValue;
		}
		
	}

}
