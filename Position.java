

public class Position {
	
	/* a simple class to describe a
	 * position on a backgammon board.
	 * each position object only needs 
	 * to store the number of checkers 
	 * it contains, and their color,
	 * since it's impossible for a 
	 * single position to contain checkers
	 * of different colors in this game */
	
	private int color;
	private byte numOfPills;
	
	public Position() {
		this.numOfPills = 0;
		this.color = 0;
	}
	
	public Position(int col, byte numP) {
		this.color = col;
		numOfPills = numP;
	}
	
	public Position(Position pos) {
		this.color = pos.color;
		this.numOfPills = pos.numOfPills;
	}
	
	public void incr() {
		this.numOfPills++;
	}
	
	public void decr() {
		this.numOfPills--;
	}
	
	public int getCol(){
		return color;
	}
	
	public void setCol(int col) {
		this.color = col;
	}
	
	public byte getNum() {
		return numOfPills;
	}
	
	public void setNum(byte num) {
		this.numOfPills = num;
	}
}
