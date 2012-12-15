import java.util.ArrayList;

public class Test2 {
	
	public static void main(String[] args) {
		
		Board b = new Board();
		
		b.print();
		
		byte d1 = (byte)((Math.random()*6) + 1);
		byte d2 = (byte)((Math.random()*6) + 1);
		
		//d1 = d2 = 6;
		System.out.println("\n\nWhite rolled "+d1+" and "+d2+"\n\n");
		
		ArrayList<Board> children = b.getChildren(d1, d2, Board.B);
		
		for (Board child : children) {
			
			child.print();
			System.out.println("Child Evaluation = "+child.evaluate());
			
		}
		
		Board c = new Board();
		Board d = new Board();
		
		int ch = c.hashCode();
		int dh = d.hashCode();
		
		System.out.println("c hash: "+ch+" dhash: "+dh);
	}
}