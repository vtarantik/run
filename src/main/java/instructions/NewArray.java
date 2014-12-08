package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

/*
 * Description

 The count must be of type int. It is popped off the operand stack. The count represents the number of elements in the array to be created.

 The atype is a code that indicates the type of array to create. It must take one of the following values:

 Table 6.1. Array type codes

 Array Type	atype
 T_BOOLEAN	4
 T_CHAR	5
 T_FLOAT	6
 T_DOUBLE	7
 T_BYTE	8
 T_SHORT	9
 T_INT	10
 T_LONG	11
 A new array whose components are of type atype and of length count is allocated from the garbage-collected heap. A reference arrayref to this new array object is pushed into the operand stack. Each of the elements of the new array is initialized to the default initial value (§2.3, §2.4) for the element type of the array type.

 Run-time Exception

 If count is less than zero, newarray throws a NegativeArraySizeException.

 Notes

 In Oracle's Java Virtual Machine implementation, arrays of type boolean (atype is T_BOOLEAN) are stored as arrays of 8-bit values and are manipulated using the baload and bastore instructions (§baload, §bastore) which also access arrays of type byte. Other implementations may implement packed boolean arrays; the baload and bastore instructions must still be used to access those arrays.
 */
public class NewArray extends AbstractInstruction {

	public NewArray(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
	}

}
