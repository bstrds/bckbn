
public class Board {
	
	public static final int W = 1;
	public static final int B = -1;
	public static final int EMPTY = 0;
	
	private Position[] positions;
	
	private Move lastMove;
	
	private int lastColorPlayed;
	
	public Board() {
		
		lastMove = new Move();
		lastColorPlayed = 0;
		positions = new Position[28];
		for(int i=0; i<28; i++) {
			positions[i] = new Position();
		}	
		
		/* setting standard backgammon starting positions
		 */
		positions[0].setCol(W);
		positions[0].setNum((byte)2);
		positions[5].setCol(B);
		positions[5].setNum((byte)5);
		positions[7].setCol(B);
		positions[7].setNum((byte)3);
		positions[11].setCol(W);
		positions[11].setNum((byte)5);
		positions[12].setCol(B);
		positions[12].setNum((byte)5);
		positions[16].setCol(W);
		positions[16].setNum((byte)3);
		positions[18].setCol(W);
		positions[18].setNum((byte)5);
		positions[23].setCol(B);
		positions[23].setNum((byte)2);
		
		/* we keep the eaten pills in [24] for white, and [25] for
		 * black. also, we keep the pills that have exited the board 
		 * in [26] for white, and [27] for black
		 */ 
		positions[24].setCol(W);
		positions[25].setCol(B);
		positions[26].setCol(W);
		positions[27].setCol(B);
	}
	
	public Board(Board board) {
		
		this.lastMove = board.lastMove;
		this.lastColorPlayed = board.lastColorPlayed;
		this.positions = new Position[28];
		for(int i=0; i<28; i++) {
			positions[i] = new Position(board.positions[i]);
		}
	}
	
	
	public Move getLastMove() {
		
		return lastMove;
	}
	
	public int getLastColPlayed() {
		
		return lastColorPlayed;
	}
	
	public Position[] getPositions() {
		
		return positions;
	}
	
	public void setLastMove(Move lastMove) {
		
		this.lastMove.setFrom(lastMove.getFrom());
		this.lastMove.setTo(lastMove.getTo());
		this.lastMove.setCol(lastMove.getCol());
	}
	
	public void setLastColPlayed(int lastCol) {
		
		this.lastColorPlayed = lastCol;
	}
	
	public void setPositions(Position[] pst) {
		
		for(int i=0; i<28; i++) {
			
			this.positions[i] = new Position(pst[i]);
		}
	}


	public void playMove(int from, int to, int col) {
		
		/* checking who wants to make the move, and manipulating
		 * the board accordingly
		 */
		if(col==W) {
			/* in the case below, the black pill in position [to] is
			 * eaten, so we store it in position [25] the 'else'
			 * statement is written with the same logic
			 */
			if(positions[to].getCol()==B) {
				positions[to].decr();
				positions[25].incr();
			}
			
		} else {
			if(positions[to].getCol()==W) {
				positions[to].decr();
				positions[24].incr();
			}
		}
		
		positions[from].decr();
		if(positions[from].getNum()==0) {
			positions[from].setCol(EMPTY);
		}
		positions[to].incr();
		positions[to].setCol(col);
	}
	
	public boolean moveIsLegal(int from, int to, int col) {
		
		if((from < 0) || (to<0) || (from>27) || (to>27)) {
			return false;
		}
		if(col==W) {
			if(positions[to].getCol()==B && positions[to].getNum()>1) {
				return false;
			}
		} else {
			if(positions[to].getCol()==W && positions[to].getNum()>1) {
				return false;
			}
		}
		return true;
	}
	
	public void print() {
		
		for(int i=11; i>=0; i--) {
			printHelp(i);
		}
		
		System.out.println("\n|\t\t\t\t\t\t|");
		
		for(int i=12; i<24; i++) {
			printHelp(i);
		}
		
		System.out.println("\n");
		System.out.println("eaten pills : <w"+positions[24].getNum()+
						   "> <b"+positions[25].getNum()+">");
		System.out.println("pills out : <w"+positions[26].getNum()+
						   "> <b"+positions[27].getNum()+">");
		
	}
	
	public void printHelp(int i) {
	
		if(positions[i].getNum()==0) {
				System.out.print("<  >");
			} else {
				if(positions[i].getCol()==W) {
					System.out.print("<w"+positions[i].getNum()+">");
				} else {
					System.out.print("<b"+positions[i].getNum()+">");
				}
			}	
	}
}
