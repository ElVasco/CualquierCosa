package trains;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Graph {
	public int W[][];
	public HashMap<String, Integer> H = new HashMap<String, Integer>();
	
	public Graph(String file){
		// TODO: Generalize H for all possible keys. Ask possible names.
		W = new int[5][5];
		H.put("A", 0);
		H.put("B", 1);
		H.put("C", 2);
		H.put("D", 3);
		H.put("E", 4);
	}
	
	public void readGraph(String file){
		File f_graph = new File(file);
	
		try {
			Scanner sc = new Scanner(f_graph);
			while(sc.hasNextLine()){
				String l = sc.nextLine();
				W[H.get(l.substring(0, 1))][H.get(l.substring(1, 2))] = Integer.parseInt(l.substring(2, l.length()));
			}
			
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
}
