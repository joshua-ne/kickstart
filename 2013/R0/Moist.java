import java.util.*;
import java.io.*;

class Moist {




	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int T = in.nextInt(); 
		for (int t = 1; t <= T; ++t) {
			int n = in.nextInt();
			in.nextLine();
			int cost = 0;
			String curMax = "";
			for (int i = 0; i < n; i++) {
				String cur = in.nextLine();
				//Jren.p(cur + " vs " + curMax + " res: " + cur.compareTo(curMax));
				if (cur.compareTo(curMax) >= 0) {curMax = cur;}
				else {cost++;}
			}
			System.out.println("Case #" + t + ": " + cost);
		}
	}
}

