import java.util.ArrayList;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		char[][] board;
		int testCount = scan.nextInt();
		// loop through all the test cases
		for (int test = 0; test < testCount; test++) {
			ParsedInput pb = parseBoard(scan);
			board = pb.board;
			Coordinate leap = pb.leaper;
			int moveCount = pb.moveCount;
			int S = pb.S;
			System.out.println(countMoves(pb.board, leap, 0, moveCount, S));
		}

	}

	private static int countMoves(char[][] board, Coordinate source, int depth, int moveCount, int S) {
		int count = 0;
		int boardMax = board.length - 1;
		int output = 0;
		ArrayList<Coordinate> potentialSpots = new ArrayList<Coordinate>();
		potentialSpots.add(source);
		
		for (int move = 0; move < moveCount; move++){
			ArrayList<Coordinate> spotCopy = (ArrayList<Coordinate>) potentialSpots.clone();
			potentialSpots = new ArrayList<Coordinate>();
			for (int ar = 0; ar < spotCopy.size(); ar++){
				Coordinate c = spotCopy.get(ar);
				int i = c.i;
				int j = c.j;
				
				int minM = i - S > 0 ? i - S : 0;
				int maxM = i + S > boardMax ? boardMax : i + S;
		
				int minN = j - S > 0 ? j - S : 0;
				int maxN = j + S > boardMax ? boardMax : j + S;
				
				for (int m = minM; m <= maxM; m++){
					for (int n = minN; n <= maxN; n++){
						
						// if within range
						int dist = Math.abs(i - m) + Math.abs(j - n);
						if (dist <= S){
							if (board[m][n] != 'P'){
								if (move == moveCount - 1){
									output++;
								}
								else
									potentialSpots.add(new Coordinate(m,n));
							}
						}
					}
				}
			}
		}
		
		return output;
	}

	private static ParsedInput parseBoard(Scanner scan) {
		int boardWidth = scan.nextInt();
		int moveCount = scan.nextInt();
		int s = Integer.parseInt(scan.nextLine().trim());
		ParsedInput out = new ParsedInput();

		char[][] board = new char[boardWidth][boardWidth];

		for (int i = 0; i < boardWidth; i++) {
			String tmp = scan.next();
			for (int j = 0; j < boardWidth; j++) {
				board[i][j] = tmp.charAt(j);
				if (board[i][j] == 'L')
					out.leaper = new Coordinate(i, j);
			}
		}
		out.board = board;
		out.moveCount = moveCount;
		out.S = s;
		return out;

	}

}

class Coordinate {
	int i, j;

	public Coordinate(int _i, int _j) {
		this.i = _i;
		this.j = _j;
	}
}

class ParsedInput {
	public Coordinate leaper;
	public char[][] board;
	public int moveCount;
	public int S;
}
