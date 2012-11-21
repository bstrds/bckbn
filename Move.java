
public class Move {
	
	private int from;
	private int to;
	private int color;
	
	public Move() {
		this.from = -1;
		this.to = -1;
	}
	
	public Move(int from, int to, int color) {
		this.from = from;
		this.to = to;
		this.color = color;
		
	}
	
	public int getFrom() {
		
		return from;
	}
	
	public int getTo() {
		
		return to;
	}
	
	public int getCol() {
		
		return color;
	}
	
	public void setFrom(int from) {
		
		this.from = from;
	}
	
	public void setTo(int to) {
		
		this.to = to;
	}
	
	public void setCol(int col) {
		
		this.color = col;
	}
}
