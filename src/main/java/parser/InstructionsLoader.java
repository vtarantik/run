package parser;

import java.util.ArrayDeque;
import java.util.HashMap;

import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ALOAD;
import org.apache.bcel.generic.Instruction;

public class InstructionsLoader {
	private MembersHelper membersHelper;
	private HashMap<Method,ArrayDeque<Instruction>> methodsInstructions;
	
	public InstructionsLoader(String className){
		membersHelper = new MembersHelper(className);
		methodsInstructions = membersHelper.getMethodsInstructions();
	}
	
	/*
	 * Method loads instructions and creates AbstractInstructions from them
	 */
	public void loadInstructions(){
		for (Method method : methodsInstructions.keySet()) {
			System.out.println("Method: "+method.getName());
			ArrayDeque<Instruction> instructions = methodsInstructions.get(method);
			while(!instructions.isEmpty()){
				Instruction i = instructions.poll();
				if(i instanceof ALOAD){
					ALOAD aload = (ALOAD)i;
					System.out.println("I: "+i.toString());
				}
			}
		}
		System.out.println("-----------------------------------------------");
	}
	
	
}
