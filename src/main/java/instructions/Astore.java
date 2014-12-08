package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * Description

The index is an unsigned byte that must be an index into the local variable array
 of the current frame (§2.6). The objectref on the top of the operand stack must
  be of type returnAddress or of type reference. It is popped from the operand stack,
   and the value of the local variable at index is set to objectref.

Notes

The astore instruction is used with an objectref of type returnAddress when implementing
 the finally clause of the Java programming language (§3.13).

The aload instruction (§aload) cannot be used to load a value of type returnAddress 
from a local variable onto the operand stack. This asymmetry with the astore instruction
 is intentional.

The astore opcode can be used in conjunction with the wide instruction (§wide) to access 
a local variable using a two-byte unsigned index.
 */
public class Astore extends AbstractInstruction{

	public Astore(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		Object poppedValue = currentStackFrame.popOperand();
		currentStackFrame.setLocalVariableValue(index, poppedValue);
	}

}
