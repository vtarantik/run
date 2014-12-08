package instructions;

import model.MyClass;

import org.apache.bcel.classfile.ConstantFieldref;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * Description

 The objectref, which must be of type reference, is popped from the operand stack.
 The unsigned indexbyte1 and indexbyte2 are used to construct an index into the 
 run-time constant pool of the current class (§2.6), where the value of the index
 is (indexbyte1 << 8) | indexbyte2. The run-time constant pool item at that index
 must be a symbolic reference to a field (§5.1), which gives the name and descriptor
 of the field as well as a symbolic reference to the class in which the field 
 is to be found. The referenced field is resolved (§5.4.3.2). The value of the
 referenced field in objectref is fetched and pushed onto the operand stack.

 The type of objectref must not be an array type. If the field is protected (§4.6),
 and it is a member of a superclass of the current class, and the field is not declared 
 in the same run-time package (§5.3) as the current class, then the class of objectref 
 must be either the current class or a subclass of the current class.

 Linking Exceptions

 During resolution of the symbolic reference to the field, any of the errors pertaining
 to field resolution (§5.4.3.2) can be thrown.

 Otherwise, if the resolved field is a static field, getfield throws an 
 IncompatibleClassChangeError.

 Run-time Exception

 Otherwise, if objectref is null, the getfield instruction throws a NullPointerException.

 Notes

 The getfield instruction cannot be used to access the length field of an array.
 The arraylength instruction (§arraylength) is used instead.
 */
public class GetField extends AbstractInstruction {

	public GetField(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		//TODO manage popped Instance
		Object poppedObjectRef = currentStackFrame.popOperand();
		ConstantPool currentClassConstantPool = ownerClass.getConstantPool();
		ConstantFieldref fieldRef = (ConstantFieldref)currentClassConstantPool.getConstant(index);
		System.out.println(currentClassConstantPool.getConstant(fieldRef.getNameAndTypeIndex()));
	}

}
