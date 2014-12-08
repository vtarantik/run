package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * Push the null object reference onto the operand stack.

Notes

The Java Virtual Machine does not mandate a concrete value for null.
 */
public class AconstNull extends AbstractInstruction{
	

	public AconstNull(short opcode, int index, int length,MyClass ownerClass) {
		super(opcode, index, length,ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		Object obj = null;
		currentStackFrame.pushOperand(obj);
	}
	

}
