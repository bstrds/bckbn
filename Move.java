

public class Move {
	
	/* a simple class that produces 
	 * Move objects
	 */
	
	private byte from;
	private byte to;
	private byte color;
	
	public Move() {
		this.from = 0;
		this.to = 0;
		this.color = 0;
	}
	
	public Move(Move m) {
		this.from = m.from;
		this.to = m.to;
		this.color = m.color;
	}
	
	public Move(byte from, byte to, byte color) {
		this.from = from;
		this.to = to;
		this.color = color;
	}
	
	public Move(byte from, byte to, byte color, int value) {
		this.from = from;
		this.to = to;
		this.color = color;
	}
	
	public byte getFrom() {
		
		return from;
	}
	
	public byte getTo() {
		
		return to;
	}
	
	public byte getCol() {
		
		return color;
	}
	
	public void setFrom(byte from) {
		
		this.from = from;
	}
	
	public void setTo(byte to) {
		
		this.to = to;
	}
	
	public void setCol(byte col) {
		
		this.color = col;
	}
}
