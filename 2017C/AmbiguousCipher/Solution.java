import java.util.*;
import java.io.*;

class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();
		in.nextLine();
		//System.out.println(t + " tests:");
		//System.out.println(in.nextLine() + "h");

		for (int i = 1; i <= t; i++) {
			String res = "";

			String code = in.nextLine();
			//System.out.println(code);



			int[] resArray = new int[code.length()];
			if (code.length() % 2 == 1) res = "AMBIGUOUS";
			else {
				
				//odd
				resArray[1] = code.charAt(0) - 'A';
				int j = 0;
				while (j + 2 < code.length() -1) {
					j = j + 2;
					int letterCode = code.charAt(j) - 'A';
					if (letterCode < resArray[j - 1]) resArray[j + 1] = (26 + letterCode) - resArray[j - 1];
					else resArray[j + 1] = (letterCode) - resArray[j - 1];
				}
			
				//even
				resArray[code.length() - 2] = code.charAt(code.length() - 1) - 'A';
				j = code.length() - 1;
				while (j -2 >= 0) {
					j = j - 2;
					int letterCode = code.charAt(j) - 'A';
					if (letterCode < resArray[j + 1]) resArray[j - 1] = (26 + letterCode) - resArray[j + 1];
					else resArray[j - 1] = (letterCode) - resArray[j + 1];
				}
			
				StringBuffer sb = new StringBuffer();

				for (int x : resArray) {
					//System.out.println(x);
					sb.append((char) (x + 'A'));
					res = sb.toString();
				}

			}

			System.out.println("Case #" + i + ": " + res);
		}
	}
}