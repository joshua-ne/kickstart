import java.util.*;
import java.io.*;

class Training {
  int N, P, minHour;
  int[] skills;
  Training(int N, int P, int[] skills) {
    this.N = N;
    this.P = P;
    this.skills = skills;
    Arrays.sort(skills);
    minHour = Integer.MAX_VALUE;
  }

  void solve() {
    int[] sumSkill = new int[N];
    sumSkill[0] = skills[0];
    for (int i = 1; i < N; i++) {
      sumSkill[i] = skills[i] + sumSkill[i - 1];
    }

    for (int i = 0; i <= N - P; i++) {
      minHour = Math.min(minHour, cost(i, sumSkill));
    }

  }

  int cost(int i, int[] sumSkill) {
    int res = 0;
    res = skills[i + P - 1] * P - (sumSkill[i + P - 1] - (i == 0 ? 0 : sumSkill[i - 1]));
    return res;
  }



  public static void main(String[] args) {
      Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
      int T = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
      for (int t = 1; t <= T; ++t) {
        int N = in.nextInt();
        int P = in.nextInt();
        int[] skills = new int[N];
        for (int i = 0; i < N; i++) {
          skills[i] = in.nextInt();
        }

        Training training = new Training(N, P, skills);
        training.solve();
        //output result
        System.out.println("Case #" + t + ": " + training.minHour);
        
      }
    }
}