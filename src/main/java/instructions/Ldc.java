package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

/*
 * Description

 The index is an unsigned byte that must be a valid index into the run-time constant pool of the current class (§2.6). The run-time constant pool entry at index either must be a run-time constant of type int or float, or a reference to a string literal, or a symbolic reference to a class, method type, or method handle (§5.1).

 If the run-time constant pool entry is a run-time constant of type int or float, the numeric value of that run-time constant is pushed onto the operand stack as an int or float, respectively.

 Otherwise, if the run-time constant pool entry is a reference to an instance of class String representing a string literal (§5.1), then a reference to that instance, value, is pushed onto the operand stack.

 Otherwise, if the run-time constant pool entry is a symbolic reference to a class (§5.1), then the named class is resolved (§5.4.3.1) and a reference to the Class object representing that class, value, is pushed onto the operand stack.

 Otherwise, the run-time constant pool entry must be a symbolic reference to a method type or a method handle (§5.1). The method type or method handle is resolved (§5.4.3.5) and a reference to the resulting instance of java.lang.invoke.MethodType or java.lang.invoke.MethodHandle, value, is pushed onto the operand stack.

 Linking Exceptions

 During resolution of a symbolic reference to a class, any of the exceptions pertaining to class resolution (§5.4.3.1) can be thrown.

 During resolution of a symbolic reference to a method type or method handle, any of the exception pertaining to method type or method handle resolution (§5.4.3.5) can be thrown.

 Notes

 The ldc instruction can only be used to push a value of type float taken from the float value set (§2.3.2) because a constant of type float in the constant pool (§4.4.4) must be taken from the float value set.
 */
public class Ldc extends AbstractInstruction {

	public Ldc(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
	}

}
