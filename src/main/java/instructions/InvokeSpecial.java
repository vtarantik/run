package instructions;

import model.MyClass;

import org.apache.bcel.classfile.ConstantClass;
import org.apache.bcel.classfile.ConstantMethodref;
import org.apache.bcel.classfile.ConstantNameAndType;
import org.apache.bcel.classfile.ConstantString;
import org.apache.bcel.classfile.ConstantUtf8;
import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * Description

 The unsigned indexbyte1 and indexbyte2 are used to construct an index into the run-time
 constant pool of the current class (§2.6), where the value of the index is
 (indexbyte1 << 8) | indexbyte2. The run-time constant pool item at that index must
 be a symbolic reference to a method (§5.1), which gives the name and descriptor (§4.3.3)
 of the method as well as a symbolic reference to the class in which the method is to be
 found. The named method is resolved (§5.4.3.3). Finally, if the resolved method is
 protected (§4.6), and it is a member of a superclass of the current class, and the 
 method is not declared in the same run-time package (§5.3) as the current class, 
 then the class of objectref must be either the current class or a subclass of the 
 current class.

 Next, the resolved method is selected for invocation unless all of the following conditions
 are true:

 The ACC_SUPER flag (Table 4.1) is set for the current class.

 The class of the resolved method is a superclass of the current class.

 The resolved method is not an instance initialization method (§2.9).

 If the above conditions are true, the actual method to be invoked is selected by the 
 following lookup procedure. Let C be the direct superclass of the current class:

 If C contains a declaration for an instance method with the same name and descriptor as 
 the resolved method, then this method will be invoked. The lookup procedure terminates.

 Otherwise, if C has a superclass, this same lookup procedure is performed recursively using
 the direct superclass of C. The method to be invoked is the result of the recursive
 invocation of this lookup procedure.

 Otherwise, an AbstractMethodError is raised.

 The objectref must be of type reference and must be followed on the operand stack by nargs
 argument values, where the number, type, and order of the values must be consistent with the 
 descriptor of the selected instance method.

 If the method is synchronized, the monitor associated with objectref is entered or 
 reentered as if by execution of a monitorenter instruction (§monitorenter) in the current 
 thread.

 If the method is not native, the nargs argument values and objectref are popped from the
 operand stack. A new frame is created on the Java Virtual Machine stack for the method
 being invoked. The objectref and the argument values are consecutively made the values
 of local variables of the new frame, with objectref in local variable 0, arg1 in local
 variable 1 (or, if arg1 is of type long or double, in local variables 1 and 2), 
 and so on. Any argument value that is of a floating-point type undergoes value set
 conversion (§2.8.3) prior to being stored in a local variable. The new frame is then 
 made current, and the Java Virtual Machine pc is set to the opcode of the first 
 instruction of the method to be invoked. Execution continues with the first
 instruction of the method.

 If the method is native and the platform-dependent code that implements it has not yet 
 been bound (§5.6) into the Java Virtual Machine, that is done. The nargs argument values
 and objectref are popped from the operand stack and are passed as parameters to the code
 that implements the method. Any argument value that is of a floating-point type undergoes
 value set conversion (§2.8.3) prior to being passed as a parameter. The parameters are
 passed and the code is invoked in an implementation-dependent manner. When the 
 platform-dependent code returns, the following take place:

 If the native method is synchronized, the monitor associated with objectref is updated
 and possibly exited as if by execution of a monitorexit instruction (§monitorexit)
 in the current thread.

 If the native method returns a value, the return value of the platform-dependent code is 
 converted in an implementation-dependent way to the return type of the native method and
 pushed onto the operand stack.

 Linking Exceptions

 During resolution of the symbolic reference to the method, any of the exceptions pertaining
 to method resolution (§5.4.3.3) can be thrown.

 Otherwise, if the resolved method is an instance initialization method, and the class in 
 which it is declared is not the class symbolically referenced by the instruction, 
 a NoSuchMethodError is thrown.

 Otherwise, if the resolved method is a class (static) method, the invokespecial instruction
 throws an IncompatibleClassChangeError.

 Run-time Exceptions

 Otherwise, if objectref is null, the invokespecial instruction throws a NullPointerException.

 Otherwise, if no method matching the resolved name and descriptor is selected, invokespecial 
 throws an AbstractMethodError.

 Otherwise, if the selected method is abstract, invokespecial throws an AbstractMethodError.

 Otherwise, if the selected method is native and the code that implements the method cannot
 be bound, invokespecial throws an UnsatisfiedLinkError.

 Notes

 The difference between the invokespecial instruction and the invokevirtual instruction
 (§invokevirtual) is that invokevirtual invokes a method based on the class of the object.
 The invokespecial instruction is used to invoke instance initialization methods (§2.9) 
 as well as private methods and methods of a superclass of the current class.

 The invokespecial instruction was named invokenonvirtual prior to JDK release 1.0.2.

 The nargs argument values and objectref are not one-to-one with the first nargs+1 local 
 variables. Argument values of types long and double must be stored in two consecutive 
 local variables, thus more than nargs local variables may be required to pass nargs argument
 values to the invoked method.
 */
public class InvokeSpecial extends AbstractInstruction {

	public InvokeSpecial(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	// TODO check whether calling superclass method
	public void execute(Method m) {
		System.out.println(toString());
		ConstantMethodref methodRef = (ConstantMethodref) ownerClass
				.getConstantPool().getConstant(index);
		ConstantClass mClass = (ConstantClass) ownerClass.getConstantPool()
				.getConstant(methodRef.getClassIndex());
		ConstantNameAndType mType = (ConstantNameAndType) ownerClass
				.getConstantPool().getConstant(methodRef.getNameAndTypeIndex());

		String invokedMethodName = mType.getName(ownerClass.getConstantPool());
		ConstantUtf8 cs = (ConstantUtf8) ownerClass.getConstantPool()
				.getConstant(mClass.getNameIndex());
		String invokingClassName = cs.getBytes();
		if (invokingClassName.equals(ownerClass)) {
			switch (invokedMethodName) {
			case "<init>":
			case "main":
				ownerClass.runMethod(invokedMethodName);
				break;
			default:
				ownerClass.runMethod(invokedMethodName);
				break;
			}
		} else if (invokingClassName.equals("java/lang/Object")) {
			StackFrame currentStackFrame = MyStack.getInstance().peek();
			currentStackFrame.popOperand();
		}

	}

}
