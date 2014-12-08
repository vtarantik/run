package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

/*
 * Description

 The unsigned indexbyte1 and indexbyte2 are used to construct an index into the run-time
 constant pool of the current class (§2.6), where the value of the index is
 (indexbyte1 << 8) | indexbyte2. The run-time constant pool item at that index must 
 be a symbolic reference to a field (§5.1), which gives the name and descriptor of the
 field as well as a symbolic reference to the class or interface in which the field 
 is to be found. The referenced field is resolved (§5.4.3.2).

 On successful resolution of the field, the class or interface that declared the resolved
 field is initialized (§5.5) if that class or interface has not already been initialized.

 The value of the class or interface field is fetched and pushed onto the operand stack.

 Linking Exceptions

 During resolution of the symbolic reference to the class or interface field, any of the
 exceptions pertaining to field resolution (§5.4.3.2) can be thrown.

 Otherwise, if the resolved field is not a static (class) field or an interface field, 
 getstatic throws an IncompatibleClassChangeError.

 Run-time Exception

 Otherwise, if execution of this getstatic instruction causes initialization of the
 referenced class or interface, getstatic may throw an Error as detailed in §5.5.
 */
public class GetStatic extends AbstractInstruction {

	public GetStatic(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
	}

}
