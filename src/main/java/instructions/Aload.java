package instructions;

import model.MyClass;
import model.MyLocalVariable;

import org.apache.bcel.classfile.LocalVariable;
import org.apache.bcel.classfile.LocalVariableTable;
import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * The index is an unsigned byte that must be an index into the local variable array of the 
 * current frame (§2.6). The local variable at index must contain a reference. The objectref
 *  in the local variable at index is pushed onto the operand stack.
The aload instruction cannot be used to load a value of type returnAddress from a local
 variable onto the operand stack. This asymmetry with the astore instruction (§astore)
  is intentional.

The aload opcode can be used in conjunction with the wide instruction (§wide) to access
 a local variable using a two-byte unsigned index.
 */
public class Aload extends AbstractInstruction{
	
	public Aload(short opcode, int index, int length,MyClass ownerClass) {
		super(opcode,index,length,ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		currentStackFrame.pushOperand(currentStackFrame.getLocalVariableValue(index));
	}

}
