package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * Description

 The index is an unsigned byte that must be an index into the local variable array 
 of the current frame (§2.6). The value on the top of the operand stack must be of 
 type float. It is popped from the operand stack and undergoes value set conversion (§2.8.3),
 resulting in value'. The value of the local variable at index is set to value'.

 Notes

 The fstore opcode can be used in conjunction with the wide instruction (§wide) to access
 a local variable using a two-byte unsigned index.
 */
public class Fstore extends AbstractInstruction {

	public Fstore(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		float poppedValue = (float)currentStackFrame.popOperand();
		currentStackFrame.setLocalVariableValue(index, poppedValue);
	}

}
