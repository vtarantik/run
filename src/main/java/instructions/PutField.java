package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

/*
 * Description

 The unsigned indexbyte1 and indexbyte2 are used to construct an index into the run-time constant pool of the current class (§2.6), where the value of the index is (indexbyte1 << 8) | indexbyte2. The run-time constant pool item at that index must be a symbolic reference to a field (§5.1), which gives the name and descriptor of the field as well as a symbolic reference to the class in which the field is to be found. The class of objectref must not be an array. If the field is protected (§4.6), and it is a member of a superclass of the current class, and the field is not declared in the same run-time package (§5.3) as the current class, then the class of objectref must be either the current class or a subclass of the current class.

 The referenced field is resolved (§5.4.3.2). The type of a value stored by a putfield instruction must be compatible with the descriptor of the referenced field (§4.3.2). If the field descriptor type is boolean, byte, char, short, or int, then the value must be an int. If the field descriptor type is float, long, or double, then the value must be a float, long, or double, respectively. If the field descriptor type is a reference type, then the value must be of a type that is assignment compatible (JLS §5.2) with the field descriptor type. If the field is final, it must be declared in the current class, and the instruction must occur in an instance initialization method (<init>) of the current class (§2.9).

 The value and objectref are popped from the operand stack. The objectref must be of type reference. The value undergoes value set conversion (§2.8.3), resulting in value', and the referenced field in objectref is set to value'.

 Linking Exceptions

 During resolution of the symbolic reference to the field, any of the exceptions pertaining to field resolution (§5.4.3.2) can be thrown.

 Otherwise, if the resolved field is a static field, putfield throws an IncompatibleClassChangeError.

 Otherwise, if the field is final, it must be declared in the current class, and the instruction must occur in an instance initialization method (<init>) of the current class. Otherwise, an IllegalAccessError is thrown.

 Run-time Exception

 Otherwise, if objectref is null, the putfield instruction throws a NullPointerException.
 */
public class PutField extends AbstractInstruction {

	public PutField(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
	}

}
