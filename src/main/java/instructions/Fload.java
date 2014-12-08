package instructions;

import model.MyClass;

import org.apache.bcel.classfile.ConstantDouble;
import org.apache.bcel.classfile.ConstantFloat;
import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * Description

The index is an unsigned byte that must be an index into the local variable array 
of the current frame (§2.6). The local variable at index must contain a float. 
The value of the local variable at index is pushed onto the operand stack.

Notes

The fload opcode can be used in conjunction with the wide instruction (§wide)
 to access a local variable using a two-byte unsigned index.
 */
public class Fload extends AbstractInstruction{

	public Fload(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length,ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		currentStackFrame.pushOperand(currentStackFrame.getLocalVariableValue(index));
	}

}
