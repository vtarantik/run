package test;

import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.LoadInstruction;

import parser.Parser;


public class Main {
	public static void main(String[] args) {
		Parser parser = new Parser("RUN_KnapSack.class");
		parser.run();
		
//		ClassParser parser;
//		try {
//			parser = new ClassParser("RUN_Knapsack.class");
//			JavaClass jc = parser.parse();
//			Method[] methods = jc.getMethods();
//			ConstantPoolGen cpg = new ConstantPoolGen(jc.getConstantPool());
//			MethodGen mg = new MethodGen(methods[1], jc.getClassName(), cpg);
//			InstructionList il = mg.getInstructionList();
//			Instruction[] instructions = il.getInstructions();
//			for (int i = 0; i < il.getLength(); i++) {
//				System.out.println(instructions[i].toString());
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
