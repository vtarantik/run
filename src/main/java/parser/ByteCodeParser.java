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
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import javassist.CtMethod;

public class ByteCodeParser {
	
	private List<CtMethod> methods;
	
	public ByteCodeParser(){
		methods = new ArrayList<CtMethod>();
	}
	
	private void parseClasses(){
		//TODO implement
	}
	
	
}
