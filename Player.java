import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Player {

	private int maxDepth;
	private int playerColor;
	private int d1;
	private int d2;
	
	public Player() {
		
		maxDepth = 2;
		playerColor = Board.B;
	}
	
	public Player(int maxDepth, int playerColor) {
		
		this.maxDepth = maxDepth;
		this.playerColor = playerColor;
	}
	
	public void roll() {
		
		this.d1 = (int)(Math.random()*6)+1;
		this.d2 = (int)(Math.random()*6)+1;
	}
	
	public Move[] inputMove(int d1, int d2) {
		
		Move[] moves;
		String fr;
		String t;
		int from = -1;
		int to = -1;
		boolean gotit = false;
		boolean dubs = (d1==d2);
		
		/* to determine which dice was played */
		boolean pD1 = false;
		boolean pD2 = false;
		
		/* makes new Move board with length depending on
		 * whether or not we have doubles 
		 */
		if(dubs)
			moves = new Move[4];
		else
			moves = new Move[2];
		
		for(int i=0; i<moves.length; i++) { 
			
			InputStreamReader read = new InputStreamReader(System.in);
			BufferedReader in = new BufferedReader(read);
			
			if(playerColor==Board.W)
				System.out.println("White rolled "+d1+" and "+d2+" .");
			else 
				System.out.println("Black rolled "+d1+" and "+d2+" .");
			
			while(!gotit) {
				try {
					
					System.out.println("Enter where you want to move from :");
					fr = in.readLine();
					from = Integer.parseInt(fr);
					System.out.println("Enter where you want to move to :");
					t = in.readLine();
					to = Integer.parseInt(t);
					if((Math.abs(from-to) == d1) && !pD1) {
						pD1 = true;
						gotit = true;
					} else if((Math.abs(from-to) == d2) && !pD2) {
						pD2 = true;
						gotit = true;
					}else {
					
						System.out.println("You entered an illegal move biatch\n");
					}
					
				} catch(Exception e) {
					
					System.out.println("Try again.");
					//e.printStackTrace();
				}
			}
			gotit = false;
			moves[i] = new Move(from, to, playerColor);
		}
			
		//Move wmove = new Move(from, to, playerColor);
		return moves;
	}
	
	public int getD1() {
		
		return this.d1;
	}
	
	public int getD2() {
			
		return this.d2;
	}
	
	public int getDepth() {
		
		return this.maxDepth;
	}
}
