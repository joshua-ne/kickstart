import java.util.*;
import java.io.*;

class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();

		for (int i = 1; i <=t; i++) {
			String res = "POSSIBLE";
			int size = in.nextInt();
			in.nextLine();
			char[][] grid = new char[size][size];

			int[] count = new int[size * 2];

			//load the grid
			for (int j = 0; j < size; j++){
				char[] row = in.nextLine().toCharArray();
				for (int k = 0; k < size; k++) {
					grid[j][k] = row[k];
					if (row[k] == 'X') {
						if(++count[k] > 2) res = "IMPOSSIBLE";
						if(++count[j + size] > 2) res = "IMPOSSIBLE";
					}
				}
			}

			//print the grid for test
			
			//for(int j = 0; j < size; j++){
			//	System.out.println(Arrays.toString(grid[j]));
			//}
			
			//System.out.println(Arrays.toString(count));

			if(res == "POSSIBLE"){
				int[] uX = new int[2];
			//find the one with unique X



				for(int j = 0, k = 1; j < 2 * size; j++){
					if(count[j] == 1) {uX[k] = j; k--;} 
				}

				uX[0] -= size;


				//System.out.println(Arrays.toString(uX));


				if(uX[0] + 1 >=0 && uX[0] + 1 < size ) {
					if(grid[uX[0] + 1][uX[1] ] == 'X') res = "IMPOSSIBLE";
					//System.out.println("1");
				}

				if(uX[0] - 1 >=0 && uX[0] - 1 < size ) {
					if(grid[uX[0] - 1][uX[1] ] == 'X') res = "IMPOSSIBLE";
					//System.out.println("2");
				}

				if( uX[1] - 1 >=0 && uX[1] - 1 < size) {
					if(grid[uX[0] ][uX[1] - 1] == 'X') res = "IMPOSSIBLE";
					//System.out.println("3");
				}

				if(uX[1] + 1 >=0 && uX[1] + 1 < size) {
					if(grid[uX[0]][uX[1] + 1] == 'X') res = "IMPOSSIBLE";
					//System.out.println("4");
				}
				}



			System.out.println("Case #" + i + ": " + res);
			//break;
		}
	}
}