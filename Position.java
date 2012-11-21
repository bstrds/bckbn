
public class Position {
	
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
