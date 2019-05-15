import java.util.*;
import java.io.*;

class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		System.out.println(in.nextInt());
		System.out.println(in.nextInt());
		System.out.println(in.nextInt());
		for (int i = 1; i <= 3; i++) {
			

			String code = in.nextLine();
			
			System.out.println("Case #" + i + ": " + code);
		}
	}
}