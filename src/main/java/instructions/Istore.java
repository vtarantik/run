package instructions;

import model.MyClass;
import model.MyLocalVariable;

import org.apache.bcel.classfile.LocalVariable;
import org.apache.bcel.classfile.LocalVariableTable;
import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * Description

 The index is an unsigned byte that must be an index into the local variable array of the current frame (§2.6). The value on the top of the operand stack must be of type int. It is popped from the operand stack, and the value of the local variable at index is set to value.

 Notes

 The istore opcode can be used in conjunction with the wide instruction (§wide) to access a local variable using a two-byte unsigned index.
 */
public class Istore extends AbstractInstruction {

	public Istore(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		Object poppedValue = currentStackFrame.popOperand();
		if(poppedValue instanceof Integer){
			currentStackFrame.setLocalVariableValue(index, poppedValue);
		}else {
			throw new IllegalArgumentException("popped value is supposed to be of type integer");
		}
	}

}
