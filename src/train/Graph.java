package trains;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Graph {
	public int W[][];
	public HashMap<String, Integer> H = new HashMap<String, Integer>();
	public String[] towns = {"A", "B", "C", "D", "E"};
	
	public Graph(String file){
		// TODO: Generalize H for all possible keys. Ask possible names.
		W = new int[5][5];
		
		for(int i = 0; i<5; i++){
			W[i][i] = Integer.MAX_VALUE;
			H.put(towns[i], i);
		}
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
	
	public String distance(String route){
		int sum = 0, w;
		String left = route.substring(0,1);
		route = route.substring(2, route.length());
		
		for(String right : route.split("-")){
			w = W[H.get(left)][H.get(right)];
			if(w > 0)
				sum += w;
			else
				return "NO SUCH ROUTE"; 
			left = right;
		}
		return Integer.toString(sum);
	}
	
	public int trips(String type, String start, String end, int max){
		Set<String> valid_routes = new HashSet<>();
		ArrayList<String> actual_routes = new ArrayList<>();
		String actual, route = "";
		int w;
		actual_routes.add(start);
		
		while(!actual_routes.isEmpty()){
			route = actual_routes.get(0);
			// We assume route is not valid
			actual = route.substring(route.length()-1, route.length());
			
			for(String town : towns){
				w = W[H.get(actual)][H.get(town)]; 
				
				// If they're connected
				if(w > 0 && w < Integer.MAX_VALUE){
					if(route.length() <= max){
						if(town == end)
							if(type == "=="){
								if(route.length() == max)
									valid_routes.add(route+town);
							}else
								valid_routes.add(route+town);
						actual_routes.add(route+town);					
					}
				}
			}
			actual_routes.remove(route);
		}
		
		return valid_routes.size();
	}

	public int trips_weight(String type, String start, String end, int max){
		Set<String> valid_routes = new HashSet<>();
		ArrayList<String> actual_routes = new ArrayList<>();
		String actual, route = "", broute = "";
		int w, weight = 0, pweight = 0;
		
		actual_routes.add("0"+","+start);
		
		while(!actual_routes.isEmpty()){
			route = actual_routes.get(0);
			broute = route;
			// We assume route is not valid

			for(String p : route.split(",")){
				try{
					weight = Integer.parseInt(p);
				}catch(java.lang.NumberFormatException e){
					route = p;
				}
			}
			actual = route.substring(route.length()-1, route.length());
			
			for(String town : towns){
				w = W[H.get(actual)][H.get(town)]; 
				
				// If they're connected
				if(w > 0 && w < Integer.MAX_VALUE){
					pweight = weight + w;
					if(pweight <= max){
						if(town == end){
							switch(type){
								case "==":
									if(pweight == max)
										valid_routes.add(Integer.toString(pweight)+","+route+town);
								break;
								
								case "<":
									if(pweight < max)
										valid_routes.add(Integer.toString(pweight)+","+route+town);
								break;
								
								case "<=":
									valid_routes.add(Integer.toString(pweight)+","+route+town);
								
								default:
									return -1;
							}
						}	
						actual_routes.add(Integer.toString(pweight)+","+route+town);					
					}
				}
			}
			actual_routes.remove(broute);
		}
		
		return valid_routes.size();
	}
	
	public int dijkstra(String start, String end, boolean debug){
		String actual = start;
			
	    // Weird case of do while because (u, u) = infty for all u (should be 0).
		
		// Initialize variables
		ArrayList<String>  Q = new ArrayList<String>();
		HashMap<String, ArrayList<String>> prev = new HashMap<String, ArrayList<String>>();
		HashMap<String, Integer> dist = new HashMap<String, Integer>();
		ArrayList<String> mtowns = new ArrayList<String>();		
		
		Integer min_iter = Integer.MAX_VALUE;
		Integer disti = Integer.MAX_VALUE;
		String towna;
		
		int w = 0;
		
		for(String town : towns){
			mtowns.add(town);
			Q.add(town);
			dist.put(town, Integer.MAX_VALUE);
			prev.put(town, new ArrayList<>());
		}
		
		if(start == end){
			end = "F";
			mtowns.add(end);
			dist.put(end, Integer.MAX_VALUE);
			prev.put(end, new ArrayList<>());
			H.put(end, H.get(start));
			Q.add(end);
		}
		
		if(debug)
			System.out.println("Start node: "+start);
		
		dist.put(start, 0);
		prev.get(start).add(start);
					
		do{
			min_iter = Integer.MAX_VALUE;
			disti = Integer.MAX_VALUE;
			towna = "";
			
			for (HashMap.Entry<String, Integer> entry : dist.entrySet()) {
			    towna = entry.getKey();
			    disti = entry.getValue();
			    if(debug)
			    	System.out.println("comp(d["+start+"]["+towna+"]="+disti+","+min_iter+")");
			    
			    // If the node is in Q and distance is minimum, node is actual
			    if(Q.contains(towna) && disti < min_iter){
			    	if(debug)
			    		System.out.println("Indeed "+disti+ "<"+min_iter);
			    	actual = towna;
			    	min_iter = disti;
			    }
			}
			
			
			// Remove best option from Q
			
			if(debug){
				System.out.println("actual: "+actual+" min_dist="+min_iter);
				for(String q : Q){
					System.out.print(q + " ");
				}
				
			    System.out.println("p");	
			}
			
			Q.remove(actual);
			
			if(debug){
				for(String q : Q){
					System.out.print(q + " ");
				}
				
			    System.out.println("Actual es: "+ actual);	
			}
			
			// For every neighbor of actual
			// TODO: We need a linked list.
			
			if(debug)
				System.out.println("------");
			
			for(String town : mtowns){
				w = W[H.get(actual)][H.get(town)];
				if(w > 0 && w < Integer.MAX_VALUE){
					if(debug){
						System.out.println("Node "+town+" is valid");
						System.out.println("min_dist = "+min_iter);
						System.out.println("w["+actual+"]["+town+"]= w ="+w);
						System.out.println("dist["+town+"]="+dist.get(town));
					}
					if(min_iter + w  < dist.get(town)){
						// New distance, clear neighbors. Set distance
						if(debug){
							System.out.println(town +" desde <");
							System.out.println("w= "+w+" disti = "+min_iter);
							int d = w + min_iter;
							System.out.println("From "+actual+" to "+town+" is "+d);
						}
						dist.put(town, w + min_iter);
						prev.put(town, new ArrayList<>());
						prev.get(start).add(actual);						
					}else if(min_iter + w == dist.get(town)){
						// We have a tie in the best distance. So we add a to the previous.
						if(debug)
							System.out.println(town+" desde ==");
						prev.get(start).add(actual);
					}	
				}
			}
		}while(actual != end);
		
		return dist.get(end);
	}
	
}
