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
}
