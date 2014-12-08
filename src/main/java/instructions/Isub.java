package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

/*
 * Both value1 and value2 must be of type int. The values are popped from the operand stack. The int result is value1 - value2. The result is pushed onto the operand stack.

 For int subtraction, a-b produces the same result as a+(-b). For int values, subtraction from zero is the same as negation.

 The result is the 32 low-order bits of the true mathematical result in a sufficiently wide two's-complement format, represented as a value of type int. If overflow occurs, then the sign of the result may not be the same as the sign of the mathematical difference of the two values.

 Despite the fact that overflow may occur, execution of an isub instruction never throws a run-time exception.
 */
public class Isub extends AbstractInstruction {

	public Isub(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
	}

}
