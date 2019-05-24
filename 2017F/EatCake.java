//starts at 10:15

import java.util.*;
import java.io.*;

class EatCake {

	static Map<Integer, Integer> dp = new HashMap<>(){{put(0, 0);put(1, 1);}};
	static int curMaxKey = 1;

	static int solve(int N) {
		if (N <= curMaxKey) return dp.get(N);

		for (int cur = curMaxKey + 1; cur <= N; cur++) {
			int min = N;
			for (int i = 1; i * i <= cur; i++) {
				min = Math.min(min, 1 + dp.get(cur - i * i));
			}
			dp.put(cur, min);
			//Jren.p(cur + " " + min);
		}

		curMaxKey = N;

		return dp.get(N);
	}




	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int T = in.nextInt(); 
		for (int t = 1; t <= T; ++t) {
			int N = in.nextInt();
			int n = solve(N);
			System.out.println("Case #" + t + ": " + n);
		}
	}
}
