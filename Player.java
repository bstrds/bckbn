import java.io.BufferedReader;
import java.io.IOException;
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
	
	public Move inputMove() {
		
		String fr;
		String t;
		int from = -1;
		int to = -1;
		boolean gotit = false;
		
		InputStreamReader read = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(read);
		
		while(!gotit) {
			try {
				
				System.out.println("Enter where you want to move from :");
				fr = in.readLine();
				from = Integer.parseInt(fr);
				System.out.println("Enter where you want to move to :");
				t = in.readLine();
				to = Integer.parseInt(t);
				gotit = true;
				
			} catch(Exception e) {
				
				System.out.println("Try again.");
				//e.printStackTrace();
			}
		}
			
		Move wmove = new Move(from, to, playerColor);
		return wmove;
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
