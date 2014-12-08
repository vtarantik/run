package instructions;

import java.lang.reflect.Constructor;

import heap.HeapedObject;
import heap.MyHeap;
import model.MyClass;

import org.apache.bcel.classfile.Constant;
import org.apache.bcel.classfile.ConstantClass;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.Method;

import parser.MembersHelper;
import stack.MyStack;

/*
 * Description

 The unsigned indexbyte1 and indexbyte2 are used to construct an index into the run-time constant pool of the current class (§2.6), where the value of the index is (indexbyte1 << 8) | indexbyte2. The run-time constant pool item at the index must be a symbolic reference to a class or interface type. The named class or interface type is resolved (§5.4.3.1) and should result in a class type. Memory for a new instance of that class is allocated from the garbage-collected heap, and the instance variables of the new object are initialized to their default initial values (§2.3, §2.4). The objectref, a reference to the instance, is pushed onto the operand stack.

 On successful resolution of the class, it is initialized (§5.5) if it has not already been initialized.

 Linking Exceptions

 During resolution of the symbolic reference to the class, array, or interface type, any of the exceptions documented in §5.4.3.1 can be thrown.

 Otherwise, if the symbolic reference to the class, array, or interface type resolves to an interface or is an abstract class, new throws an InstantiationError.

 Run-time Exception

 Otherwise, if execution of this new instruction causes initialization of the referenced class, new may throw an Error as detailed in JLS §15.9.4.

 Notes

 The new instruction does not completely create a new instance; instance creation is not completed until an instance initialization method (§2.9) has been invoked on the uninitialized instance.
 */
public class New extends AbstractInstruction {

	public New(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
		ConstantPool cp = ownerClass.getConstantPool();
		ConstantClass o = (ConstantClass) cp.getConstant(index);
		Constant con = cp.getConstant(o.getNameIndex());
		String className = cp.getConstantString(index, o.getTag());

		MyClass objectClass = MembersHelper.loadClass(className);
		// TODO create instance from class
		HeapedObject ho = new HeapedObject(objectClass, null);

		MyHeap.getInstance().store(ho);
		MyStack.getInstance().peek().pushOperand(objectClass);

	}

}
