import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
	static boolean flag = true;

	public static void dfs(int i,int size, int[] visited,
			int[][] c, boolean[] color) {
		for (int v = 0; v < size; v++) {
			if (c[i][v] == 1) {
				if (visited[v] == 0) {
					visited[v] = 1;
					color[v] = !color[i];
					dfs(v, size, visited, c, color);
				} else if (color[v] == color[i]) {
					flag = false;
					return;
				}
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();
		scanner.nextLine();
		int i = 1;
		int M = 0;
		while (T-- > 0) {
			flag = true;
  			M = scanner.nextInt();
			scanner.nextLine();
			ArrayList<String> strList = new ArrayList<String>();
			int count = M;
			int[][] c = new int[M*2][M*2];
			while (count-- > 0) {
				String line = scanner.nextLine();
				String[] pairs = line.split(" ");
				String one = pairs[0];
				String two = pairs[1];
				int index1 = -1;
				int index2 = -1;
				
				if (strList.size() == 0) {
					strList.add(one);
					index1 = strList.size() - 1;
					strList.add(two);
					index2 = strList.size() - 1;
				}else{
					for (int j = 0; j < strList.size(); j++) {
						if (strList.get(j).equals(one))
							index1 = j;
						if (strList.get(j).equals(two))
							index2 = j;
					}
					if(index1 == -1){
						strList.add(one);
						index1 = strList.size() - 1;
					}
					
					if(index2 == -1){
						strList.add(two);
						index2 = strList.size() - 1;
					}
						
				}
				 c[index1][index2] = 1;
				 c[index2][index1] = 1;
			}
			boolean[] color = new boolean[strList.size()];
			int[] visited = new int[strList.size()];
			dfs(0, strList.size(),visited, c, color);
			System.out.println("Case #" + (i++) + ": " + (flag ? "Yes" : "No"));
		}
	}

}
