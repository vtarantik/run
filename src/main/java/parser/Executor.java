package parser;

import instructions.IAbstractInstruction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.Instruction;

import javassist.CtMethod;

public class Executor {
	private MembersHelper membersHelper;
	private HashMap<Method,ArrayDeque<Instruction>> methodsInstructions;
	
	public Executor(){
		membersHelper = new MembersHelper("RUN_Knapsack.class");
		methodsInstructions = membersHelper.getMethodsInstructions();
	}
	
	public void run(){
		for (Method method : methodsInstructions.keySet()) {
			System.out.println("Method: "+method.getName());
			ArrayDeque<Instruction> instructions = methodsInstructions.get(method);
			while(!instructions.isEmpty()){
				Instruction i = instructions.poll();
				System.out.println("I: "+i.getName());
			}
		}
		System.out.println("-----------------------------------------------");
	}
	
	
}
