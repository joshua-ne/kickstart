import java.util.*;
import java.io.*;

class ParcelSmall {
  int R, C, overallDeliveryTime;
  int[][] grid;
  int[][] deliverTimeGrid;
  List<Position> curOffices;

  ParcelSmall(int[][] _grid) {
    grid = _grid;
    R = grid.length;
    C = grid[0].length;
    overallDeliveryTime = 0;
    curOffices = new ArrayList<>();
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        if (grid[i][j] == 1) curOffices.add(new Position(i, j));
      }
    }
    deliverTimeGrid = new int[R][C];
    for (int i = 0; i < R; i++) {
      Arrays.fill(deliverTimeGrid[i], Integer.MAX_VALUE);
    }
    constructDeliveryTimeGrid();
  }

  void constructDeliveryTimeGrid() {
    for (Position p : curOffices) {
      int[][] curVisitMap = new int[R][C];
      //curSearch.add(p);
      updateDeliveryTimeGrid(p, 0, curVisitMap);
      //break;
    }
  }

  void updateDeliveryTimeGrid(Position p, int curValue, int[][] curVisitMap) {
    Queue<Position> queue = new ArrayDeque<>();
    queue.add(p);
    curVisitMap[p.x][p.y] = 1;
    while (!queue.isEmpty()) {
      
      int size = queue.size();
      //Jren.p(size);
      for (int i = 0; i < size; i++) {
        Position cur = queue.remove();
        deliverTimeGrid[cur.x][cur.y] = Math.min(deliverTimeGrid[cur.x][cur.y], curValue);
        //curVisitMap[cur.x][cur.y] = 1;
        if (cur.x - 1 >= 0 && curVisitMap[cur.x - 1][cur.y] == 0) {
          queue.add(new Position(cur.x - 1, cur.y));
          curVisitMap[cur.x - 1][cur.y] = 1;
        }

        if (cur.x + 1 < R && curVisitMap[cur.x + 1][cur.y] == 0) {
          queue.add(new Position(cur.x + 1, cur.y));
          curVisitMap[cur.x + 1][cur.y] = 1;
        }

        if (cur.y - 1 >= 0 && curVisitMap[cur.x][cur.y - 1] == 0) {
          queue.add(new Position(cur.x, cur.y - 1));
          curVisitMap[cur.x][cur.y - 1] = 1;
        }

        if (cur.y + 1 < C && curVisitMap[cur.x][cur.y + 1] == 0) {
          queue.add(new Position(cur.x, cur.y + 1));
          curVisitMap[cur.x][cur.y + 1] = 1;
        }
      }
      curValue++;
    }
  }

  int maxDeliveryTime(int[][] deliverTimeGrid) {
    int max = 0;
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        max = Math.max(max, deliverTimeGrid[i][j]);
      }
    }
    return max;
  } 

  void solve() {
    overallDeliveryTime = maxDeliveryTime(deliverTimeGrid);
    //Jren.p(overallDeliveryTime);
    //Jren.p(deliverTimeGrid);
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        if (deliverTimeGrid[i][j] != 0) {
          int[][] curVisitMap = new int[R][C];
          int[][] backupGrid = new int[R][C];
          for (int k = 0; k < R; k++) {
            backupGrid[k] = deliverTimeGrid[k].clone();
          }
          updateDeliveryTimeGrid(new Position(i, j), 0, curVisitMap);
          overallDeliveryTime = Math.min(overallDeliveryTime, maxDeliveryTime(deliverTimeGrid));
          deliverTimeGrid = backupGrid;
        }
      }
    }
  }





  class Position {
    int x, y;
    Position(int r, int c) {
      x = r; 
      y = c;
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    int T = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
    for (int t = 1; t <= T; ++t) {
      int R = in.nextInt();
      int C = in.nextInt();
      //Jren.p(R + " " + C);
      int[][] grid = new int[R][C];
      for (int i = 0; i < R; i++) {
        char[] row = in.next().toCharArray();
        for (int j = 0; j < C; j++) {
          grid[i][j] = row[j] - '0';
        }
      }

      ParcelSmall p = new ParcelSmall(grid);
      p.solve();
      System.out.println("Case #" + t + ": " + p.overallDeliveryTime);
      
    }
  }
}