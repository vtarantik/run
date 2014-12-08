package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

/*
 * Description

 The unsigned indexbyte1 and indexbyte2 are used to construct an index into the run-time 
 constant pool of the current class (§2.6), where the value of the index is 
 (indexbyte1 << 8) | indexbyte2. The run-time constant pool item at that index must
 be a symbolic reference to a method (§5.1), which gives the name and descriptor (§4.3.3) 
 of the method as well as a symbolic reference to the class in which the method is to be
 found. The named method is resolved (§5.4.3.3). The resolved method must not be an 
 instance initialization method (§2.9) or the class or interface initialization method
 (§2.9). It must be static, and therefore cannot be abstract.

 On successful resolution of the method, the class that declared the resolved method is 
 initialized (§5.5) if that class has not already been initialized.

 The operand stack must contain nargs argument values, where the number, type, and order
 of the values must be consistent with the descriptor of the resolved method.

 If the method is synchronized, the monitor associated with the resolved Class object is
 entered or reentered as if by execution of a monitorenter instruction (§monitorenter)
 in the current thread.

 If the method is not native, the nargs argument values are popped from the operand stack.
 A new frame is created on the Java Virtual Machine stack for the method being invoked.
 The nargs argument values are consecutively made the values of local variables of the 
 new frame, with arg1 in local variable 0 (or, if arg1 is of type long or double, in local\
 variables 0 and 1) and so on. Any argument value that is of a floating-point type undergoes 
 value set conversion (§2.8.3) prior to being stored in a local variable. The new frame is 
 then made current, and the Java Virtual Machine pc is set to the opcode of the first 
 instruction of the method to be invoked. Execution continues with the first instruction
 of the method.

 If the method is native and the platform-dependent code that implements it has not yet been
 bound (§5.6) into the Java Virtual Machine, that is done. The nargs argument values are 
 popped from the operand stack and are passed as parameters to the code that implements
 the method. Any argument value that is of a floating-point type undergoes value set
 conversion (§2.8.3) prior to being passed as a parameter. The parameters are passed 
 and the code is invoked in an implementation-dependent manner. When the platform-dependent 
 code returns, the following take place:

 If the native method is synchronized, the monitor associated with the resolved Class object
 is updated and possibly exited as if by execution of a monitorexit instruction (§monitorexit)
 in the current thread.

 If the native method returns a value, the return value of the platform-dependent code is 
 converted in an implementation-dependent way to the return type of the native method and 
 pushed onto the operand stack.

 Linking Exceptions

 During resolution of the symbolic reference to the method, any of the exceptions pertaining 
 to method resolution (§5.4.3.3) can be thrown.

 Otherwise, if the resolved method is an instance method, the invokestatic instruction throws
 an IncompatibleClassChangeError.

 Run-time Exceptions

 Otherwise, if execution of this invokestatic instruction causes initialization of the
 referenced class, invokestatic may throw an Error as detailed in §5.5.

 Otherwise, if the resolved method is native and the code that implements the method cannot 
 be bound, invokestatic throws an UnsatisfiedLinkError.

 Notes

 The nargs argument values are not one-to-one with the first nargs local variables. 
 Argument values of types long and double must be stored in two consecutive local variables,
 thus more than nargs local variables may be required to pass nargs argument values to the
 invoked method.
 */
public class InvokeStatic extends AbstractInstruction {

	public InvokeStatic(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
	}

}
