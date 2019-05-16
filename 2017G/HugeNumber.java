import java.util.*;
import java.io.*;

class HugeNumber {

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int T = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
		for (int t = 1; t <= T; ++t) {
			long A = in.nextInt();
			long N = in.nextInt();
			long P = in.nextInt();

			A = A % P;
			for (long i = 1; i <= N; i++) {
				long tmp = helper(A, i, P);
				A = tmp;
				//Jren.p(A);
				if (A == 0 || A == 1) break;
			}

			System.out.println("Case #" + t + ": " + A);
		}
	}

	// Math.pow(A, i) % P
	static long helper(long A, long i, long P) {
		if (i == 0) return 1;
		long tmp = helper(A, i / 2, P);

		if (i % 2 == 0) {
			return  (tmp * tmp) % P;
		} else {
			return (tmp * tmp * A) % P;
		}
	}
}
