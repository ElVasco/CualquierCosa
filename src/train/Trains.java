package trains;
import trains.Graph;


public class Trains {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file = "data/graphs/example1.csv";
		Graph G = new Graph(file);
		G.readGraph(file);
		System.out.println(G.distance("A-B-C"));
		System.out.println(G.distance("A-D"));
		System.out.println(G.distance("A-D-C"));
		System.out.println(G.distance("A-E-B-C-D"));
		System.out.println(G.distance("A-E-D"));
		System.out.println(G.dijkstra("A", "C", true));
	}

}
