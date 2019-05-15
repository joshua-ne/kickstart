import java.util.*;
import java.io.*;

class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt();
		for (int i = 1; i <= t; i++) {
			int n_total = in.nextInt();
			int m_total = in.nextInt();

			double prob = 0.0;
			long total = factorial(m_total + n_total);

			int n_voted = 1, m_voted = 0;
			long nAlwaysWin = helper(n_total, m_total, n_voted, m_voted, 'n');
			prob = (double)(nAlwaysWin / (total + 0.0));
			//System.out.println("hhh");
			//prob = helper2(n_total, m_total, n_voted, m_voted, 'n');
			//System.out.println("ggg");
			System.out.println("Case #" + i + ": " + prob);
		}
	}

	static long factorial(int n) {
		if (n == 1) return 1;
		else return (long) n * factorial(n-1);
	}

	static long helper(int n_total, int m_total, int n_voted, int m_voted, char voteWho) {
		//no child, to return
		if(n_voted == n_total && m_voted == m_total) {
			return (long) 1;
		}
		long subTotal = 0;		
		//hasLeft
		if(n_voted < n_total) {
			subTotal += helper(n_total, m_total, n_voted + 1, m_voted, 'n');
		}
		//hasRight
		if (n_voted - m_voted > 1 && m_voted < m_total) {
			subTotal += helper(n_total, m_total, n_voted, m_voted + 1, 'm');
		}
		if(voteWho == 'n') return subTotal * (n_total - n_voted + 1);
		else return subTotal * (m_total - m_voted + 1);
	}


	static double helper2(int n_total, int m_total, int n_voted, int m_voted, char voteWho) {

		//System.out.println("helper2");
		//no child, to return
		if(n_voted == n_total && m_voted == m_total) {
			System.out.println("final:" + voteWho );
			return (double) 1;
		}
		double subTotal = 0;		
		//hasLeft
		if(n_voted < n_total) {
			//System.out.println("n: " + n_voted);
			subTotal += helper2(n_total, m_total, n_voted + 1, m_voted, 'n');
		}
		//hasRight
		if (n_voted - m_voted > 1 && m_voted < m_total) {
			//System.out.println("n: " + n_voted);
			subTotal += helper2(n_total, m_total, n_voted, m_voted + 1, 'm');
		}

		double total = 0.0;
		if(voteWho == 'n') {total = (double) (subTotal * (n_total - n_voted + 1));}
		else {total = (double) (subTotal * (m_total - m_voted + 1));}

		//System.out.println(total);
		return total/(m_total + n_total + 1.0 - m_voted - n_voted);	
	}

	static double helper3(int n_total, int m_total, int n_voted, int m_voted, char voteWho) {
		//no child, to return
		if(n_voted == n_total && m_voted == m_total) {
			return (double) 1;
		}

		//only left
		if(n_voted - m_voted < 2 || m_voted == m_total) {
			return 0;
		}

		double subTotal = 0;		
		//hasLeft
		if(n_voted < n_total) {
			subTotal += helper3(n_total, m_total, n_voted + 1, m_voted, 'n');
		}
		//hasRight
		if (n_voted - m_voted > 1 && m_voted < m_total) {
			subTotal += helper3(n_total, m_total, n_voted, m_voted + 1, 'm');
		}
		if(voteWho == 'n') return subTotal * (n_total - n_voted + 1);
		else return subTotal * (m_total - m_voted + 1);
	}
}