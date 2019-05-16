import java.util.*;
import java.io.*;

class MatrixCutting {
	int N, M;
	int[][] matrix;
	int res;

	MatrixCutting(int[][] _matrix) {
		matrix = _matrix;
		N = matrix.length;
		M = matrix[0].length;
		res = 0;
	}

	void solve_large() {
		// value: int[2]: int[0] maxCoin; int[1] minValue
		Map<FourTuple, int[]> dp = new HashMap<>();
		res = solve_large(new FourTuple(0, N - 1, 0, M - 1), dp);
	}

	int solve_large(FourTuple ft, Map<FourTuple, int[]> dp) {

		//base case, only one left: 
		if (ft.rStart == ft.rEnd && ft.cStart == ft.cEnd) return 0;
		if (dp.containsKey(ft)) return dp.get(ft)[0];
		int min = getMin(ft);
		int curMax = min;
		for (int i = ft.rStart; i < ft.rEnd; i++) {
			for (int j = ft.cStart; j < ft.cEnd; j++) {
				int cur = solve_large(new FourTuple(ft.rStart, i, ft.cStart, j), dp);
				cur += solve_large(new FourTuple(ft.rStart, i, j + 1, ft.cEnd), dp);
				cur += solve_large(new FourTuple(i + 1, ft.rEnd, j + 1, ft.cEnd), dp);
				cur += solve_large(new FourTuple(i + 1, ft.rEnd, ft.cStart, j), dp);

				curMax = Math.max(curMax, cur + min);
				//Jren.p(curMax);
			}
		}

		dp.put(ft, new int[]{curMax, min});


		return curMax;
	}

	int getMin(FourTuple ft) {
		int res = Integer.MAX_VALUE;
		for (int i = ft.rStart; i <= ft.rEnd; i++) {
			for (int j = ft.cStart; j <= ft.cEnd; j++) {
				res = Math.min(res, matrix[i][j]);
			}
		}
		return res;
	}









	void solve_small() {
		int[][] dp = new int[M][M];
		for (int i = 0; i < M; i++) {
			Arrays.fill(dp[i], -1);
		}
		res = solve_small(0, M - 1, dp);

		//Jren.p(solve_small(0, 1, dp));
		//Jren.p(solve_small(1, 2, dp));

	}

	int solve_small(int start, int end, int[][] dp) {
		if (start >= end) return 0;
		//if (start + 1 == end) return Math.min(matrix[0][start], matrix[0][end]);
		if (dp[start][end] != -1) return dp[start][end];
		int min = min(start, end);
		int curMax = min;
		for (int k = start; k <= end; k++) {
			int cur = (k == end ? 0 : solve_small(start, k, dp)); 
			cur += solve_small(k + 1, end, dp);
			curMax = Math.max(curMax, cur + min);
		}
		dp[start][end] = curMax;
		return curMax;
	}

	int min(int start, int end) {
		int curMin = Integer.MAX_VALUE;
		for (int i = start; i < end + 1; i++) {
			curMin = Math.min(curMin, matrix[0][i]);
		}
		return curMin;
	}

	class FourTuple {
		int rStart, rEnd, cStart, cEnd;
		FourTuple (int r1, int r2, int c1, int c2) {
			rStart = r1;
			rEnd = r2;
			cStart = c1;
			cEnd = c2;
		}
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o.getClass() != this.getClass()) return false;
			FourTuple ft = (FourTuple) o;
			return this.rStart == ft.rStart && this.rEnd == ft.rEnd && this.cStart == ft.cStart && this.cEnd == ft.cEnd;
		}
		public int hashCode() {
			int hash = 7;
			hash = 31 * hash + rStart;
			hash = 31 * hash + rEnd;
			hash = 31 * hash + cStart;
			hash = 31 * hash + cEnd;
			return hash;
		}
	}





	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int T = in.nextInt(); 
		for (int t = 1; t <= T; ++t) {
			int N = in.nextInt();
			int M = in.nextInt();
			int[][] matrix = new int[N][M];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					matrix[i][j] = in.nextInt();
				}
			}

			MatrixCutting mc = new MatrixCutting(matrix);
			mc.solve_large();

			System.out.println("Case #" + t + ": " + mc.res);

			mc.solve_small();

			System.out.println("Case #" + t + ": " + mc.res);
		}
	}
}