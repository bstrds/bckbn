
public class Move {
	
	private byte from;
	private byte to;
	private byte color;
	private int value;
	
	public Move() {
		this.from = 0;
		this.to = 0;
		this.color = 0;
		this.value = 0;
	}
	
	public Move(Move m) {
		this.from = m.from;
		this.to = m.to;
		this.color = m.color;
		this.value = m.value;
	}
	
	public Move(byte from, byte to, byte color) {
		this.from = from;
		this.to = to;
		this.color = color;
		this.value = 0;
		
	}
	
	public Move(byte from, byte to, byte color, int value) {
		this.from = from;
		this.to = to;
		this.color = color;
		this.value = value;
	}
	
	public Move(int value) {
		this.value = value;
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
	
	public int getVal() {
		
		return value;
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
	
	public void setVal(int value) {
		
		this.value = value;
	}
}
