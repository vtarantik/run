package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

/*
 * Description

 The current method must have return type void. If the current method is a synchronized method, the monitor entered or reentered on invocation of the method is updated and possibly exited as if by execution of a monitorexit instruction (§monitorexit) in the current thread. If no exception is thrown, any values on the operand stack of the current frame (§2.6) are discarded.

 The interpreter then returns control to the invoker of the method, reinstating the frame of the invoker.

 Run-time Exceptions

 If the Java Virtual Machine implementation does not enforce the rules on structured locking described in §2.11.10, then if the current method is a synchronized method and the current thread is not the owner of the monitor entered or reentered on invocation of the method, return throws an IllegalMonitorStateException. This can happen, for example, if a synchronized method contains a monitorexit instruction, but no monitorenter instruction, on the object on which the method is synchronized.

 Otherwise, if the Java Virtual Machine implementation enforces the rules on structured locking described in §2.11.10 and if the first of those rules is violated during invocation of the current method, then return throws an IllegalMonitorStateException.
 */
public class Return extends AbstractInstruction {

	public Return(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
	}

}
