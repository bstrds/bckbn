public class Test {
	
	public static void main(String[] args) {
		
		Player Bplayer = new Player((byte)3, Board.B);
		Player Wplayer = new Player((byte)2, Board.W);
		
		/* initializing the Move boards 
		 * for human players*/
		Move Wmove[] = new Move[0];
		Move Bmove[] = new Move[0];
		
		int counter = 0;
		
		boolean fuck = false;
		
		Board b = new Board();
		
		Board fucku = new Board();
		
		b.print();
		
		while(!b.isTerminal()) {
			
			System.out.println();
			counter++;
			
			switch(b.getLastColPlayed()) {
				
				case Board.B:
					
					System.out.println("White moves");
					
					if(counter==1)
						Wplayer.roll();
					
					/* this means human player */
					if(Wplayer.getDepth()==0) {
						
						Wmove = Wplayer.inputMove(b);
						
						for(int i=0; i<Wmove.length; i++) {
							if(b.moveIsLegal(Wmove[i].getFrom(), Wmove[i].getTo(), Board.W, Wplayer.getD1(), Wplayer.getD2())) {
								b.playMove(Wmove[i].getFrom(), Wmove[i].getTo(), Board.W );
								counter = 0;
							}
						}
					} else {
						
						System.out.println("White rolled "+Wplayer.getD1()+" and "+Wplayer.getD2()+" .");
						
						b = new Board(Wplayer.MiniMax(b, Wplayer.getD1(), Wplayer.getD2()));
						
						b.setLastColPlayed(Board.W);

						counter = 0;
					}
					
					break;
				
				case Board.W:
					
					System.out.println("Black moves");
					
					if(counter==1)
						Bplayer.roll();
						
					if(Bplayer.getDepth()==0) {
						
						Bmove = Bplayer.inputMove(b);
						
						for(int i=0; i<Bmove.length; i++) {
							if(b.moveIsLegal(Bmove[i].getFrom(), Bmove[i].getTo(), Board.B, Bplayer.getD1(), Bplayer.getD2())) {
								b.playMove(Bmove[i].getFrom(), Bmove[i].getTo(), Board.B );
								counter = 0;
							}
						}
					} else {
						System.out.println("Black rolled "+Bplayer.getD1()+" and "+Bplayer.getD2()+" .");
						
						b = new Board(Bplayer.MiniMax(b, Bplayer.getD1(), Bplayer.getD2()));
						
						b.setLastColPlayed(Board.B);
						
						counter = 0;
					}
					
					break;
				default:
					break;
			}
			b.print();
			System.out.println("Board evaluation = "+b.evaluate());
			if(b.getPositions()[26].getNum() > 0 || b.getPositions()[27].getNum()>0) {
				fuck = true;
			}
			
			if(b.hashCode()==fucku.hashCode()) {
				System.out.println("\n\n\n\n FUCK FUCK FUCK!!!\n\n\n\n");
				break;
			}
			
			if(fuck && (b.getPositions()[26].getNum()==0 && b.getPositions()[27].getNum()==0)) {
				System.out.println("\n\n\n\n FUCK!!! \n\n\n\n");
				break;
			}
			
		}
		Position[] pos = b.getPositions();
		if(pos[26].getNum()==15)
			System.out.println("\nWhite Wins!\n");
		else if(pos[27].getNum()==15)
			System.out.println("\nBlack Wins!\n");
	}
}
