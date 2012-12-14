public class Test {
	
	public static void main(String[] args) {
		
		Player Bplayer = new Player(2, Board.B);
		Player Wplayer = new Player(0, Board.W);
		
		/* initializing the Move boards 
		 * for human players*/
		Move Wmove[] = new Move[0];
		Move Bmove[] = new Move[0];
		
		/* moves for computer players */
		Move WcompMove;
		Move BcompMove;
		
		int counter = 0;
		
		Board b = new Board();
		
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
						
						Wmove = Wplayer.inputMove(Wplayer.getD1(), Wplayer.getD2(), b);
						
						for(int i=0; i<Wmove.length; i++) {
							if(b.moveIsLegal(Wmove[i].getFrom(), Wmove[i].getTo(), Board.W, Wplayer.getD1(), Wplayer.getD2())) {
								b.playMove(Wmove[i].getFrom(), Wmove[i].getTo(), Board.W );
								counter = 0;
							}
						}
					} else {
						
						System.out.println("White rolled "+Wplayer.getD1()+" and "+Wplayer.getD2()+" .");
						
						WcompMove = Wplayer.MiniMax(b, Wplayer.getD1(), Wplayer.getD2());
						
						if(b.moveIsLegal(WcompMove.getFrom(), WcompMove.getTo(), WcompMove.getCol(), Wplayer.getD1(), Wplayer.getD2())) {
							b.playMove(WcompMove.getFrom(), WcompMove.getTo(), WcompMove.getCol());
						}
					}
					
					break;
				
				case Board.W:
					
					System.out.println("Black moves");
					
					if(counter==1)
						Bplayer.roll();
						
					if(Bplayer.getDepth()==0) {
						
						Bmove = Bplayer.inputMove(Bplayer.getD1(), Bplayer.getD2(), b);
						
						for(int i=0; i<Bmove.length; i++) {
							if(b.moveIsLegal(Bmove[i].getFrom(), Bmove[i].getTo(), Board.B, Bplayer.getD1(), Bplayer.getD2())) {
								b.playMove(Bmove[i].getFrom(), Bmove[i].getTo(), Board.B );
								counter = 0;
							}
						}
					} else {
						System.out.println("Black rolled "+Bplayer.getD1()+" and "+Bplayer.getD2()+" .");
						
						BcompMove = Bplayer.MiniMax(b, Bplayer.getD1(), Bplayer.getD2());
						
						if(b.moveIsLegal(BcompMove.getFrom(), BcompMove.getTo(), BcompMove.getCol(), Bplayer.getD1(), Bplayer.getD2())) {
							b.playMove(BcompMove.getFrom(), BcompMove.getTo(), BcompMove.getCol());
						}
					}
					
					break;
				default:
					break;
			}
			b.print();
		}
		Position[] pos = b.getPositions();
		if(pos[26].getNum()==5)
			System.out.println("\nWhite Wins!\n");
		else if(pos[27].getNum()==5)
			System.out.println("\nBlack Wins!\n");
	}
}
