import java.util.*;
import java.io.*;

class BadHorse {
	boolean res;
	Map<String, Set<String>> graph;
	List<String> horses;
	List<String> group1;
	List<String> group2;

	BadHorse(Map<String, Set<String>> graph) {
		res = true;
		this.graph = graph;
		horses = new ArrayList<>(graph.keySet());
		group1 = new ArrayList<>();
		group2 = new ArrayList<>();
	}

	void solve() {
		res = assignHorse(0);
	}

	boolean assignHorse(int curIndex) {
		if (curIndex == horses.size()) return true;
		
		String curHorse = horses.get(curIndex);
		if (check(curHorse, group1)) {
			group1.add(curHorse);
			if (assignHorse(curIndex + 1)) return true;
			group1.remove(group1.size() - 1);
		}

		if (check(curHorse, group2)) {
			group2.add(curHorse);
			if (assignHorse(curIndex + 1)) return true;
		}

		return false;
	}

	boolean check(String horse, List<String> group) {
		for (String s : group) {
			if (graph.get(s).contains(horse)) return false;
		}
		return true;
	}




	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int T = in.nextInt(); 
		for (int t = 1; t <= T; ++t) {
			int m = in.nextInt();
			Map<String, Set<String>> graph = new HashMap<>();
			for (int i = 0; i < m; i++) {

				String[] pair = new String[]{in.next(), in.next()};
				for (String s : pair) {
					if (!graph.containsKey(s)) {graph.put(s, new HashSet<>());}
				}
				graph.get(pair[0]).add(pair[1]);
				graph.get(pair[1]).add(pair[0]);
			}

			BadHorse bh = new BadHorse(graph);
			bh.solve();
			System.out.println("Case #" + t + ": " + (bh.res ? "Yes" : "No"));
			Jren.p(bh.group1.toString());
			Jren.p(bh.group2.toString());

		}
	}
}