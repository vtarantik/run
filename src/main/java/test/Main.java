package test;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.MethodGen;

import parser.Executor;
import parser.InstructionsLoader;
import parser.MembersHelper;


public class Main {
	public static void main(String[] args) {
		InstructionsLoader loader = new InstructionsLoader("RUN_KnapSack.class");
		loader.loadInstructions();
		
		
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
