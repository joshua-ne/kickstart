import java.util.*;
import java.io.*;

class BadHorse {
	boolean res;
	Map<String, Set<String>> graph;
	Set<String> horses;
	List<String> group1;
	List<String> group2;

	BadHorse(Map<String, Set<String>> graph) {
		res = true;
		this.graph = graph;
		horses = new HashSet<>(graph.keySet());
		group1 = new ArrayList<>();
		group2 = new ArrayList<>();

	}

	void solve() {
		//res = assignHorse(0);
		res = assignHorse(horses);
	}

	boolean assignHorse(Set<String> horses) {
		if (horses.isEmpty()) return true;
		String cur = horses.iterator().next();

		if (check(cur, group1, group2)) {
			
			group1.add(cur);
			group2.addAll(graph.get(cur));
			Set<String> newHorses = new HashSet<>(horses);
			newHorses.removeAll(graph.get(cur));
			newHorses.remove(cur);

			if (assignHorse(newHorses)) return true;

			group1.remove(group1.size() - 1);
			for (int i = 0; i < graph.get(cur).size();i++) {
				group2.remove(group2.size() - 1);
			}
		}

		if (check(cur, group2, group1)) {
			
			group2.add(cur);
			group1.addAll(graph.get(cur));

			Set<String> newHorses = new HashSet<>(horses);
			newHorses.removeAll(graph.get(cur));
			newHorses.remove(cur);

			if (assignHorse(newHorses)) return true;

			group2.remove(group2.size() - 1);
			for (int i = 0; i < graph.get(cur).size(); i++) {
				group1.remove(group1.size() - 1);
			}
		}

		return false;
	}


/*
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
			group2.remove(group2.size() - 1);
		}

		return false;
	}
	*/

	boolean check(String horse, List<String> group1, List<String> group2) {
		for (String s : group1) {
			if (graph.get(s).contains(horse)) return false;
		}

		int count = 0;

		for (String s : graph.get(horse)) {
			for (String t : group2) {
				if (graph.get(t).contains(s)) {
					for (int i = 0; i < count; i++) {
						group2.remove(group2.size() - 1);
					}
					return false;
				}
			}
			group2.add(s);
			count++;
		}

		for (int i = 0; i < graph.get(horse).size(); i++) {
			group2.remove(group2.size() - 1);
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
			//if (t == 78) Jren.p(bh.graph.keySet().toString());
			//Jren.p(bh.graph.keySet().toString());
			//Jren.p(bh.group1.toString());
			//Jren.p(bh.group2.toString());

		}
	}
}