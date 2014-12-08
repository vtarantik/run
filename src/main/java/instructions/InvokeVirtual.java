package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

/*
 * Description

 The unsigned indexbyte1 and indexbyte2 are used to construct an index into the run-time constant pool of the current class (§2.6), where the value of the index is (indexbyte1 << 8) | indexbyte2. The run-time constant pool item at that index must be a symbolic reference to a method (§5.1), which gives the name and descriptor (§4.3.3) of the method as well as a symbolic reference to the class in which the method is to be found. The named method is resolved (§5.4.3.3). The resolved method must not be an instance initialization method (§2.9) or the class or interface initialization method (§2.9). Finally, if the resolved method is protected (§4.6), and it is a member of a superclass of the current class, and the method is not declared in the same run-time package (§5.3) as the current class, then the class of objectref must be either the current class or a subclass of the current class.

 If the resolved method is not signature polymorphic (§2.9), then the invokevirtual instruction proceeds as follows.

 Let C be the class of objectref. The actual method to be invoked is selected by the following lookup procedure:

 If C contains a declaration for an instance method m that overrides (§5.4.5) the resolved method, then m is the method to be invoked, and the lookup procedure terminates.

 Otherwise, if C has a superclass, this same lookup procedure is performed recursively using the direct superclass of C; the method to be invoked is the result of the recursive invocation of this lookup procedure.

 Otherwise, an AbstractMethodError is raised.

 The objectref must be followed on the operand stack by nargs argument values, where the number, type, and order of the values must be consistent with the descriptor of the selected instance method.

 If the method is synchronized, the monitor associated with objectref is entered or reentered as if by execution of a monitorenter instruction (§monitorenter) in the current thread.

 If the method is not native, the nargs argument values and objectref are popped from the operand stack. A new frame is created on the Java Virtual Machine stack for the method being invoked. The objectref and the argument values are consecutively made the values of local variables of the new frame, with objectref in local variable 0, arg1 in local variable 1 (or, if arg1 is of type long or double, in local variables 1 and 2), and so on. Any argument value that is of a floating-point type undergoes value set conversion (§2.8.3) prior to being stored in a local variable. The new frame is then made current, and the Java Virtual Machine pc is set to the opcode of the first instruction of the method to be invoked. Execution continues with the first instruction of the method.

 If the method is native and the platform-dependent code that implements it has not yet been bound (§5.6) into the Java Virtual Machine, that is done. The nargs argument values and objectref are popped from the operand stack and are passed as parameters to the code that implements the method. Any argument value that is of a floating-point type undergoes value set conversion (§2.8.3) prior to being passed as a parameter. The parameters are passed and the code is invoked in an implementation-dependent manner. When the platform-dependent code returns, the following take place:

 If the native method is synchronized, the monitor associated with objectref is updated and possibly exited as if by execution of a monitorexit instruction (§monitorexit) in the current thread.

 If the native method returns a value, the return value of the platform-dependent code is converted in an implementation-dependent way to the return type of the native method and pushed onto the operand stack.

 If the resolved method is signature polymorphic (§2.9), then the invokevirtual instruction proceeds as follows.

 First, a reference to an instance of java.lang.invoke.MethodType is obtained as if by resolution of a symbolic reference to a method type (§5.4.3.5) with the same parameter and return types as the descriptor of the method referenced by the invokevirtual instruction.

 If the named method is invokeExact, the instance of java.lang.invoke.MethodType must be semantically equal to the type descriptor of the receiving method handle objectref. The method handle to be invoked is objectref.

 If the named method is invoke, and the instance of java.lang.invoke.MethodType is semantically equal to the type descriptor of the receiving method handle objectref, then the method handle to be invoked is objectref.

 If the named method is invoke, and the instance of java.lang.invoke.MethodType is not semantically equal to the type descriptor of the receiving method handle objectref, then the Java Virtual Machine attempts to adjust the type descriptor of the receiving method handle, as if by a call to java.lang.invoke.MethodHandle.asType, to obtain an exactly invokable method handle m. The method handle to be invoked is m.

 The objectref must be followed on the operand stack by nargs argument values, where the number, type, and order of the values must be consistent with the type descriptor of the method handle to be invoked. (This type descriptor will correspond to the method descriptor appropriate for the kind of the method handle to be invoked, as specified in §5.4.3.5.)

 Then, if the method handle to be invoked has bytecode behavior, the Java Virtual Machine invokes the method handle as if by execution of the bytecode behavior associated with the method handle's kind. If the kind is 5 (REF_invokeVirtual), 6 (REF_invokeStatic), 7 (REF_invokeSpecial), 8 (REF_newInvokeSpecial), or 9 (REF_invokeInterface), then a frame will be created and made current in the course of executing the bytecode behavior; when the method invoked by the bytecode behavior completes (normally or abruptly), the frame of its invoker is considered to be the frame for the method containing this invokevirtual instruction.

 The frame in which the bytecode behavior itself executes is not visible.

 Otherwise, if the method handle to be invoked has no bytecode behavior, the Java Virtual Machine invokes it in an implementation-dependent manner.

 Linking Exceptions

 During resolution of the symbolic reference to the method, any of the exceptions pertaining to method resolution (§5.4.3.3) can be thrown.

 Otherwise, if the resolved method is a class (static) method, the invokevirtual instruction throws an IncompatibleClassChangeError.

 Otherwise, if the resolved method is signature polymorphic, then during resolution of the method type derived from the descriptor in the symbolic reference to the method, any of the exceptions pertaining to method type resolution (§5.4.3.5) can be thrown.

 Run-time Exceptions

 Otherwise, if objectref is null, the invokevirtual instruction throws a NullPointerException.

 Otherwise, if the resolved method is not signature polymorphic:

 If no method matching the resolved name and descriptor is selected, invokevirtual throws an AbstractMethodError.

 Otherwise, if the selected method is abstract, invokevirtual throws an AbstractMethodError.

 Otherwise, if the selected method is native and the code that implements the method cannot be bound, invokevirtual throws an UnsatisfiedLinkError.

 Otherwise, if the resolved method is signature polymorphic, then:

 If the method name is invokeExact, and the obtained instance of java.lang.invoke.MethodType is not semantically equal to the type descriptor of the receiving method handle, the invokevirtual instruction throws a java.lang.invoke.WrongMethodTypeException.

 If the method name is invoke, and the obtained instance of java.lang.invoke.MethodType is not a valid argument to the java.lang.invoke.MethodHandle.asType method invoked on the receiving method handle, the invokevirtual instruction throws a java.lang.invoke.WrongMethodTypeException.

 Notes

 The nargs argument values and objectref are not one-to-one with the first nargs+1 local variables. Argument values of types long and double must be stored in two consecutive local variables, thus more than nargs local variables may be required to pass nargs argument values to the invoked method.
 */
public class InvokeVirtual extends AbstractInstruction {

	public InvokeVirtual(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
	}

}
