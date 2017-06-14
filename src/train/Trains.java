package trains;
import trains.Graph;


public class Trains {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file = "data/graphs/example1.csv";
		Graph G = new Graph(file);
		G.readGraph(file);
		System.out.println("Output #1 " + G.distance("A-B-C"));
		System.out.println("Output #2 " + G.distance("A-D"));
		System.out.println("Output #3 " + G.distance("A-D-C"));
		System.out.println("Output #4 " + G.distance("A-E-B-C-D"));
		System.out.println("Output #5 " + G.distance("A-E-D"));
		System.out.println("Output #6 " + G.trips("<=", "C", "C", 3));
		System.out.println("Output #7 " + G.trips("==", "A", "C", 4));
		System.out.println("Output #8 " + G.dijkstra("A", "C", false));
		System.out.println("Output #9 " + G.dijkstra("B", "B", false));
		System.out.println("Output #10 " + G.trips_weight("<", "C", "C", 30));
	}

}
