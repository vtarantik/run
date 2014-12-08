package instructions;

import model.MyClass;
import model.MyLocalVariable;

import org.apache.bcel.classfile.ConstantDouble;
import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * Description

The index is an unsigned byte. Both index and index+1 must be indices into the local
 variable array of the current frame (§2.6). The local variable at index must contain
  a double. The value of the local variable at index is pushed onto the operand stack.

Notes

The dload opcode can be used in conjunction with the wide instruction (§wide) to access
 a local variable using a two-byte unsigned index.
 */
public class Dload extends AbstractInstruction{

	public Dload(short opcode, int index, int length,MyClass ownerClass) {
		super(opcode, index, length,ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		currentStackFrame.pushOperand(currentStackFrame.getLocalVariableValue(index));
	}

}
