package test;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import bytecodeparser.CodeParser;
import bytecodeparser.OpHandler;
import bytecodeparser.analysis.opcodes.Op;

public class Main {
	public static void main(String[] args) {
		ClassPool cp = ClassPool.getDefault();
		try{
			CtClass ctClass = cp.getCtClass("test.Knapsack");
			for(CtMethod method: ctClass.getMethods()) {
		          new CodeParser(method).parse(new OpHandler() {
					
					public void handle(Op op, int index) {
						System.out.println(op.getName() + " at index " + index);
					}
				});
		        }
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
