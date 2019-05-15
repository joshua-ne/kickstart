import java.util.*;
import java.io.*;

public class Solution {
  public static void main(String[] args) {
    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
    //System.out.println(t);
    for (int i = 1; i <= t; ++i) {
      int n = in.nextInt();
      //System.out.println(n);
      int maxLength = 0;
      String leaderName = "";

      for(int j = 0; j <= n; j++){
      	String name = in.nextLine();
      	//System.out.println(name);
      	int nameLength = calcNameLength(name);
      	if(nameLength > maxLength) {maxLength = nameLength; leaderName = name;}
      	else if(nameLength == maxLength) {
      		if(evalTie(leaderName, name)) leaderName = name; maxLength = nameLength;}
      }

      System.out.println("Case #" + i + ": " + leaderName);
    }
  }

  static int calcNameLength(String s){
  	int length = 0;
  	Set<Character> set = new HashSet<>();
  	for(char ch : s.toCharArray()){
  		set.add(ch);
  	}
  	return set.contains(' ') ? set.size() - 1 : set.size();
  }

  static boolean evalTie(String leaderName, String name){
  	//return true if name wins over leadeNname
  	int res = leaderName.compareTo(name);
  	if(res > 0) return true;
  	else return false;
  }
}