package instructions;

import model.MyClass;

import org.apache.bcel.classfile.Method;

/*
 * Description

 The objectref must be of type reference. The unsigned indexbyte1 and indexbyte2 are used to
 construct an index into the run-time constant pool of the current class (§2.6), where the 
 value of the index is (indexbyte1 << 8) | indexbyte2. The run-time constant pool item at the 
 index must be a symbolic reference to a class, array, or interface type.

 If objectref is null, then the operand stack is unchanged.

 Otherwise, the named class, array, or interface type is resolved (§5.4.3.1). If objectref 
 can be cast to the resolved class, array, or interface type, the operand stack is unchanged;
 otherwise, the checkcast instruction throws a ClassCastException.

 The following rules are used to determine whether an objectref that is not null can be cast 
 to the resolved type: if S is the class of the object referred to by objectref and T is the 
 resolved class, array, or interface type, checkcast determines whether objectref can be cast
 to type T as follows:

 If S is an ordinary (nonarray) class, then:

 If T is a class type, then S must be the same class as T, or S must be a subclass of T;

 If T is an interface type, then S must implement interface T.

 If S is an interface type, then:

 If T is a class type, then T must be Object.

 If T is an interface type, then T must be the same interface as S or a superinterface of S.

 If S is a class representing the array type SC[], that is, an array of components of type SC,
 then:

 If T is a class type, then T must be Object.

 If T is an interface type, then T must be one of the interfaces implemented by arrays
 (JLS §4.10.3).

 If T is an array type TC[], that is, an array of components of type TC, then one of the 
 following must be true:

 TC and SC are the same primitive type.

 TC and SC are reference types, and type SC can be cast to TC by recursive application of
 these rules.

 Linking Exceptions

 During resolution of the symbolic reference to the class, array, or interface type, any
 of the exceptions documented in §5.4.3.1 can be thrown.

 Run-time Exception

 Otherwise, if objectref cannot be cast to the resolved class, array, or interface type,
 the checkcast instruction throws a ClassCastException.

 Notes

 The checkcast instruction is very similar to the instanceof instruction (§instanceof). 
 It differs in its treatment of null, its behavior when its test fails (checkcast throws
 an exception, instanceof pushes a result code), and its effect on the operand stack.
 */
public class Checkcast extends AbstractInstruction {

	public Checkcast(short opcode, int index, int length, MyClass ownerClass) {
		super(opcode, index, length, ownerClass);
	}

	public void execute(Method m) {
		System.out.println(toString());
	}

}
