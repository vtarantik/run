package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

/*
 * Description

 The unsigned indexbyte1 and indexbyte2 are used to construct an index into the run-time constant pool of the current class (§2.6), where the value of the index is (indexbyte1 << 8) | indexbyte2. The run-time constant pool item at that index must be a symbolic reference to a field (§5.1), which gives the name and descriptor of the field as well as a symbolic reference to the class or interface in which the field is to be found. The referenced field is resolved (§5.4.3.2).

 On successful resolution of the field, the class or interface that declared the resolved field is initialized (§5.5) if that class or interface has not already been initialized.

 The type of a value stored by a putstatic instruction must be compatible with the descriptor of the referenced field (§4.3.2). If the field descriptor type is boolean, byte, char, short, or int, then the value must be an int. If the field descriptor type is float, long, or double, then the value must be a float, long, or double, respectively. If the field descriptor type is a reference type, then the value must be of a type that is assignment compatible (JLS §5.2) with the field descriptor type. If the field is final, it must be declared in the current class, and the instruction must occur in the <clinit> method of the current class (§2.9).

 The value is popped from the operand stack and undergoes value set conversion (§2.8.3), resulting in value'. The class field is set to value'.

 Linking Exceptions

 During resolution of the symbolic reference to the class or interface field, any of the exceptions pertaining to field resolution (§5.4.3.2) can be thrown.

 Otherwise, if the resolved field is not a static (class) field or an interface field, putstatic throws an IncompatibleClassChangeError.

 Otherwise, if the field is final, it must be declared in the current class, and the instruction must occur in the <clinit> method of the current class. Otherwise, an IllegalAccessError is thrown.

 Run-time Exception

 Otherwise, if execution of this putstatic instruction causes initialization of the referenced class or interface, putstatic may throw an Error as detailed in §5.5.

 Notes

 A putstatic instruction may be used only to set the value of an interface field on the initialization of that field. Interface fields may be assigned to only once, on execution of an interface variable initialization expression when the interface is initialized (§5.5, JLS §9.3.1).
 */
public class PutStatic extends AbstractInstruction {

	public PutStatic(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
	}

}
