//start by 11:30
//pass by 12:30

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;


class DanceBattle {

	int res;
	int n;
	int[] rivals;
	Deque<Integer> queue;
	int e;

	DanceBattle(int _e, int[] nums) {
		e = _e;
		rivals = nums;
		n = rivals.length;
		res = 0;
		Arrays.sort(nums);
		queue = new ArrayDeque<>(Arrays.stream(nums).boxed().collect(Collectors.toList()));
	}

	void solve() {
		if (e <= queue.getFirst()) {return;}
		res++;
		e -= queue.removeFirst();

		

		//now res == 2
		while (true) {

			while (queue.size()> 0 && e > queue.getFirst()) {
				e -= queue.removeFirst();
				res++;
			}

			if (queue.size() > 1) {
				e += queue.removeLast();
				e -= queue.removeFirst();
			}

			else {return;}
		}


	}



	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int T = in.nextInt(); 
		for (int t = 1; t <= T; ++t) {
			int E = in.nextInt();
			int N = in.nextInt();
			int[] nums = new int[N];
			for (int i = 0; i < N ; i++) {
				nums[i] = in.nextInt();
			}

			DanceBattle db = new DanceBattle(E, nums);
			//Jren.p(db.e);
			//Jren.p(db.queue.toString());
			db.solve();
			System.out.println("Case #" + t + ": " + db.res);
			
			//Jren.p();
		}
	}
}
