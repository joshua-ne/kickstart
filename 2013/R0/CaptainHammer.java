import java.util.*;
import java.io.*;

class CaptainHammer {




	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int T = in.nextInt(); 
		for (int t = 1; t <= T; ++t) {
			int v = in.nextInt();
			int d = in.nextInt();
			double tmp = (d * 9.8) / (double)(v * v);
			tmp = Math.min(tmp, 1);
			double theta = (Math.asin(tmp)) / 3.1415926 * 180 / 2;

			//System.out.println("hh: " + Math.asin(1)/3.14 *  180);
			System.out.println("Case #" + t + ": " + theta);
		}
	}
}

