package test;

import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.LoadInstruction;

import parser.Parser;


public class Main {
	public static void main(String[] args) {
		Parser parser = new Parser("RUN_KnapSack.class");
		parser.run(false);
		
	}
}
