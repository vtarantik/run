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
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class ByteCodeParser {

	public Queue<IAbstractInstruction> parseFileIntoInstructions(String fileName) {
		ClassLoader cLoader = this.getClass().getClassLoader();
		File file = null;
		Queue<IAbstractInstruction> instructionsQueue = new PriorityQueue<IAbstractInstruction>();
		String line;
		BufferedReader buffReader = null;
		try {
			file = new File(new URI(cLoader.getResource(fileName).toString()));
			buffReader = new BufferedReader(new FileReader(file));
			while ((line = buffReader.readLine()) != null) {
				// process the line.
				String instruction = "";
				String param1 = "";
				String param2 = "";
				String firstToken = "";
				StringTokenizer tokenizer = new StringTokenizer(line);
				if (tokenizer.hasMoreTokens()) {
					firstToken = tokenizer.nextToken();
				}
				if (firstToken!=null&&firstToken.contains(":")&&!firstToken.contains("Code")) {
					if (tokenizer.hasMoreTokens()) {
						instruction = tokenizer.nextToken();
					}
					if (tokenizer.hasMoreTokens()) {
						param1 = tokenizer.nextToken();
						if(param1.contains(",")&&tokenizer.hasMoreTokens()){
							param2 = tokenizer.nextToken();
						}
					}
					System.out.println(instruction + "(" + param1+ " "+param2 + ")");
				}

			}
			buffReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	
}
