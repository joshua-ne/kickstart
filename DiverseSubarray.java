
import java.util.*;
import java.io.*;

class DiverseSubarray {
	int N, S, res;
	int[] list;

	DiverseSubarray(int _N, int _S, int[] _list) {
		N = _N;
		S = _S;
		list = _list;
		res = 0;
	}

	void solve() {
		int[] changeList = new int[N];
		//Map<type, count>
		Map<Integer, Integer> counter = new HashMap<>();
		for (int i = 0; i < N; i++) {
			int curType = list[i];
			if (!counter.containsKey(curType)) {
				changeList[i] = 1;
				counter.put(curType, 1);
			} else {
				if (counter.get(curType) == null) {changeList[i] = 0; }
				else if (counter.get(curType) == S) {
					changeList[i] = 0 - S;
					counter.put(curType, null);
				} else {
					changeList[i] = 1;
					counter.put(curType, counter.get(curType) + 1);
				}
			}
		}

		Jren.p(changeList);

		res = maxSubarraySum(changeList);
	}

	int maxSubarraySum(int[] nums) {
		int max = nums[0];
		int preMax = nums[0];
		for (int i = 1; i < nums.length; i++) {
			int curMax;
			if (preMax >= 0) {
				curMax = preMax + nums[i];
			} else {
				curMax = nums[i];
			}
			max = Math.max(max, curMax);
		}
		return max;
	}

  public static void main(String[] args) {
      Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
      int T = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
      for (int t = 1; t <= T; ++t) {
      	int N = in.nextInt();
      	int S = in.nextInt();
      	int[] list = new int[N];
      	for (int i = 0; i < N; i++) {
      		list[i] = in.nextInt();
      	}

      	DiverseSubarray ds = new DiverseSubarray(N, S, list);
      	ds.solve();
        //output result
        System.out.println("Case #" + t + ": " + ds.res);
      }
    }
}



