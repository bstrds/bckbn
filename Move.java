
public class Move {
	
	private int from;
	private int to;
	private int color;
	private int value;
	
	public Move() {
		this.from = -1;
		this.to = -1;
		this.value = 0;
	}
	
	public Move(Move m) {
		this.from = m.from;
		this.to = m.to;
		this.color = m.color;
		this.value = m.value;
	}
	
	public Move(int from, int to, int color) {
		this.from = from;
		this.to = to;
		this.color = color;
		this.value = 0;
		
	}
	
	public Move(int from, int to, int color, int value) {
		this.from = from;
		this.to = to;
		this.color = color;
		this.value = value;
	}
	
	public Move(int value) {
		this.value = value;
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
	
	public int getVal() {
		
		return value;
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
	
	public void setVal(int value) {
		
		this.value = value;
	}
}
