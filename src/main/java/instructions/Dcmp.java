package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

import stack.MyStack;
import stack.StackFrame;

/*
 * Description

 Both value1 and value2 must be of type double. The values are popped from the operand stack
 and undergo value set conversion (§2.8.3), resulting in value1' and value2'. A floating-point
 comparison is performed:

 If value1' is greater than value2', the int value 1 is pushed onto the operand stack.

 Otherwise, if value1' is equal to value2', the int value 0 is pushed onto the operand stack.

 Otherwise, if value1' is less than value2', the int value -1 is pushed onto the operand stack.

 Otherwise, at least one of value1' or value2' is NaN. The dcmpg instruction pushes the int value 1 onto the operand stack and the dcmpl instruction pushes the int value -1 onto the operand stack.

 Floating-point comparison is performed in accordance with IEEE 754. All values other than NaN
 are ordered, with negative infinity less than all finite values and positive infinity greater
 than all finite values. Positive zero and negative zero are considered equal.

 Notes

 The dcmpg and dcmpl instructions differ only in their treatment of a comparison involving NaN.
 NaN is unordered, so any double comparison fails if either or both of its operands are NaN.
 With both dcmpg and dcmpl available, any double comparison may be compiled to push the same 
 result onto the operand stack whether the comparison fails on non-NaN values or fails because
 it encountered a NaN. For more information, see §3.5.
 */
public class Dcmp extends AbstractInstruction {
	private DcmpType dcmpType;

	public Dcmp(DcmpType dcmpType, short opcode, int index, int length,
			MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
		this.dcmpType = dcmpType;
	}

	public enum DcmpType {
		DCMPG, DCMPL
	}

	public void execute(Method m) {
		System.out.println(toString());
		StackFrame currentStackFrame = MyStack.getInstance().peek();
		double value1 = (double)currentStackFrame.popOperand();
		double value2 = (double)currentStackFrame.popOperand();
		switch (dcmpType) {
		case DCMPG:
			if(value1>value2){
				currentStackFrame.pushOperand(1);
			}else if(value1==value2){
				currentStackFrame.pushOperand(0);
			}else if(value1<value2){
				currentStackFrame.pushOperand(-1);
			}else{
				if(Double.isNaN(value1)||Double.isNaN(value2)){
					currentStackFrame.pushOperand(1);
				}
			}
			break;
		case DCMPL:
			if(value1>value2){
				currentStackFrame.pushOperand(-1);
			}else if(value1==value2){
				currentStackFrame.pushOperand(0);
			}else if(value1<value2){
				currentStackFrame.pushOperand(1);
			}else{
				if(Double.isNaN(value1)||Double.isNaN(value2)){
					currentStackFrame.pushOperand(-1);
				}
			}
			break;
		}
	}

}
