// start 9:07
// pass by 10:02

import java.util.*;
import java.io.*;

class Kicksort {

	int n;
	int[] nums;
	Deque<Integer> deque;
	Kicksort(int[] _nums) {
		nums = _nums;
		n = nums.length;
		deque = new ArrayDeque<>();
		for (int i = 1 ; i < n + 1; i++) {
			deque.add(i);
		}
	}



	boolean solve() {
		if (n % 2 == 0) {
			for (int i = (n - 1) / 2; i >= 1; i--) {
				if (nums[i] != deque.getFirst() && nums[i] != deque.getLast()) {return false;}
				else {
					if (nums[i] == deque.getLast()) deque.removeLast();
					else deque.removeFirst();
				}

				if (nums[n - 1 - i] != deque.getFirst() && nums[n - 1 - i] != deque.getLast()) {return false;}
				else {
					if (nums[n - 1 - i] == deque.getLast()) deque.removeLast();
					else deque.removeFirst();
				}
			}
			return true;

		} else {
			for (int i = (n - 2) - ((n - 1) / 2); i >= 1; i--) {
				if (nums[n - 2 - i] != deque.getFirst() && nums[n - 2 - i] != deque.getLast()) return false;
				else {
					if (nums[n - 2 - i] == deque.getLast()) deque.removeLast();
					else deque.removeFirst();
				}


				if (nums[i] != deque.getFirst() && nums[i] != deque.getLast()) {return false;}
				else {
					if (nums[i] == deque.getLast()) deque.removeLast();
					else deque.removeFirst();
				}
			}
			if (nums[n - 2] != deque.getFirst() && nums[n - 2] != deque.getLast()) { return false;}

			return true;
		}
	}




	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int T = in.nextInt(); 
		for (int t = 1; t <= T; ++t) {
			int n = in.nextInt();
			int[] nums = new int[n];
			for (int i = 0; i < n; i++) {
				nums[i] = in.nextInt();
			}

			Kicksort ks = new Kicksort(nums);
			String res = ks.solve() ? "YES" : "NO";

			System.out.println("Case #" + t + ": " + res);
			//Jren.p(ks.nums);
		}
	}
}