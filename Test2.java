import java.util.ArrayList;

public class Test2 {
	
	public static void main(String[] args) {
		
		Board b = new Board();
		
		b.print();
		
		int d1 = 6;
		int d2 = 5;
		
		ArrayList<Board> children = b.getChildren(d1, d2, 1);
		
		for (Board child : children) {
			
			child.print();
			
			if(Math.abs(child.getLastMove().getFrom()-child.getLastMove().getTo())==d1) {
				
				ArrayList<Board> ch2 = child.getChildren(d2, 1);
				
				for (Board kid : ch2) {
					System.out.println("\n\n<KID>\n\n");
					kid.print();
					System.out.println("\n\n</KID>\n\n");
				}
			} else {
				ArrayList<Board> ch2 = child.getChildren(d1,1);
				
				for (Board kid : ch2) {
					System.out.println("\n\n<KID>\n\n");
					kid.print();
					System.out.println("\n\n</KID>\n\n");
				}
			}
		}
	}
}