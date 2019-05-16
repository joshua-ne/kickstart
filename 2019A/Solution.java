
import java.util.*;
import java.io.*;

class Solution {
  
  int res;
  int R, C;
  int[][] oriGrid;
  int[][] grid;
  List<int[]> offices;
  
  Solution (List<int[]> offices, int R, int C, int[][] oriGrid) {
    this.offices = offices;
    this.R = R;
    this.C = C;
    this.oriGrid = oriGrid;
    res = Integer.MAX_VALUE;
    grid = new int[R][C];
    for (int i = 0; i < R; i++) {
        for (int j = 0; j < C; j++) {
          if (oriGrid[i][j] == 1) {grid[i][j] = 0; continue;}
            int min = Integer.MAX_VALUE;
            for (int[] office : offices) {
                int x = office[0];
                int y = office[1];
                min = Math.min(min, Math.abs(x - i) + Math.abs(y - j));
                if (min == 1) break;
            }
            grid[i][j] = min;
        }
    }
  }
  
  void solve() {
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
          if (oriGrid[i][j] == 0) {
              int curMax = Integer.MIN_VALUE;
              for (int x = 0; x < R; x++) {
                  for (int y = 0; y < C; y++) {
                      int newDis = Math.min(grid[x][y],Math.abs(x - i) + Math.abs(y - j));
                      curMax = Math.max(curMax, newDis);
                  }
              }
              this.res = Math.min(curMax, res);
          }
      }
    }
    if (this.res == Integer.MAX_VALUE) res = 0;
  }

  public static void main(String[] args) {
      Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
      int T = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
      for (int t = 1; t <= T; ++t) {
        int R = in.nextInt();
        int C = in.nextInt();
        List<int[]> offices = new ArrayList<>();
        int[][] oriGrid = new int[R][C];
        for (int i = 0; i < R; i++) {
          String row = in.next();
            for (int j = 0; j < C; j++) {
                oriGrid[i][j] = row.charAt(j) - '0';
                if (oriGrid[i][j] == 1) offices.add(new int[]{i, j});
            }
        }
        Solution s = new Solution(offices, R, C, oriGrid);
        s.solve();
        //output result
        System.out.println("Case #" + t + ": " + s.res);
      }
    }
}



