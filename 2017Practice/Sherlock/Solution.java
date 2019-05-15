import java.util.*;
import java.io.*;

class Solution {
	public static void main(String[] args) {
	Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
	int t = in.nextInt();
	for (int i = 1; i <= t; i++){
		long left = in.nextInt();
		long right = in.nextInt();

		long pair = left > right ? right : left;

		long res = pair * (pair + 1) / 2;




		System.out.println("Case #" + i + ": " + res);
	}
	}
}
