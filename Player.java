import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;


public class Player {
    
	public static final int MAX = 1;
	public static final int MIN = -1;
	public static final int NEWBOARD = new Board().hashCode();
	private byte maxDepth;
	private byte playerColor;
	private byte d1;
	private byte d2;
	
	public Player() {
		
		maxDepth = 2;
		playerColor = Board.B;
	}
	
	public Player(byte maxDepth, byte playerColor) {
		
		this.maxDepth = maxDepth;
		this.playerColor = playerColor;
	}
	
	public void roll() {
		
		this.d1 = (byte)((Math.random()*6)+1);
		this.d2 = (byte)((Math.random()*6)+1);
	}
	
	public byte getD1() {
		
		return this.d1;
	}
	
	public byte getD2() {
			
		return this.d2;
	}
	
	public byte getDepth() {
		
		return this.maxDepth;
	}
	
	public Move[] inputMove(Board b) {
		
		Move[] moves;
		String fr;
		String t;
		int from = -1;
		int to = -1;
		boolean gotit = false;
		boolean dubs = (d1==d2);
		boolean direction = false;
		
		/* to determine which dice was played */
		boolean pD1 = false;
		boolean pD2 = false;
		
		/* makes new Move array with length depending on
		 * whether or not we have doubles 
		 */
		if(dubs)
			moves = new Move[4];
		else
			moves = new Move[2];
		
		/* creates a temporary board so that we can 
		 * display moves while a turn has not finished
		 */
		Board tempB = new Board(b);
		
		for(int i=0; i<moves.length; i++) { 
			
			InputStreamReader read = new InputStreamReader(System.in);
			BufferedReader in = new BufferedReader(read);
			
			if(playerColor==Board.W)
				System.out.println("White rolled "+d1+" and "+d2+" .");
			else 
				System.out.println("Black rolled "+d1+" and "+d2+" .");
			
			//Position[] tempP = tempB.getPositions();
			
			/* checking if we are in lastrun mode */
			boolean lastrun = tempB.lastrun(playerColor);
			
			while(!gotit) {
				
				try {
					
					System.out.println("Enter where you want to move from :");
					fr = in.readLine();
					from = Integer.parseInt(fr);
					System.out.println("Enter where you want to move to :");
					t = in.readLine();
					to = Integer.parseInt(t);
					
					/* checking if everyone is moving in the right direction */
					direction = (((playerColor==Board.W) && ((to-from)>0)) || ((playerColor==Board.B) && ((to-from)<0))); 
					
					if((Math.abs(from-to) == d1) && direction && (!pD1 || dubs) || lastrun) {
						
						
						/* making the move on the temporary board
						 * for displaying purposes
						 */
						if(tempB.moveIsLegal((byte)from, (byte)to, playerColor, d1, d2)) {
							
							if((!dubs && i==0)||(dubs && i!=3)) {
								tempB.playMove((byte)from, (byte)to, playerColor);
								tempB.print();
							}
							pD1 = true;
							gotit = true;
						} else {
							System.out.println("\nIllegal move, try again0. \n");
						}
						
					} else if((Math.abs(from-to) == d2) && direction && (!pD2 || dubs) || lastrun)  {
					
						/* same as above */
						if(tempB.moveIsLegal((byte)from, (byte)to, playerColor, d1, d2)) {
							
							if((!dubs && i==0)||(dubs && i!=3)) {
								tempB.playMove((byte)from, (byte)to, playerColor);
								tempB.print();
							}
							pD2 = true;
							gotit = true;
						} else {
							System.out.println("\nIllegal move, try again1. \n");
						}	
					}else {
					
						System.out.println("\nIllegal move, try again2. \n");
					}
					
				} catch(Exception e) {
					
					System.out.println("\nTry again3.\n");
					//e.printStackTrace();
				}
			}
			gotit = false;
			moves[i] = new Move((byte)from, (byte)to, playerColor);
			if(tempB.isTerminal()) {
				Move[] tempM = new Move[i+1];
				for(int c=0; c<tempM.length; c++) {
					tempM[c] = moves[c];
				}
				return tempM;
			}
		}
			
		return moves;
	}
	
	public Board MiniMax(Board b, byte d1, byte d2) {
		
		
		
		if(playerColor==Board.B) {
			
			Board max = max(new Board(b), 0, d1 ,d2);
			
			if(max.hashCode()==NEWBOARD) {
				return b;
			} else {
				return max;
			}
			
		} else { /* if white player is to play */
			
			Board min = min(new Board(b), 0, d1, d2);
			
			if(min.hashCode()==NEWBOARD) {
				return b;
			} else {
				return min;
			}
		}
	}
	
	public Board min(Board b, int depth, byte d1, byte d2) {
		
		if(b.isTerminal() || depth==maxDepth) {
			
			return b;
		}
		
		Board minBoard = new Board();
		int min;
		
		if(depth==0) {
			
			ArrayList<Board> children = b.getChildren(d1, d2, Board.W);
			
			min = Integer.MAX_VALUE;
			
			for(Board child : children) {
				
				max(child, depth+1, d1, d2);
				
				if(child.getValue() < min) {
					
					min = child.getValue();
					minBoard = new Board(child);
				}
			}
			
			return minBoard;
			
		} else {
			
			ArrayList<Board> children = new ArrayList<Board>();
			ArrayList<Board> tmp;
			
			for(byte i=1; i<7; i++) {
				for(byte j=1; j<7; j++) {
					
					
					tmp = new ArrayList<Board>(b.getChildren(i, j, Board.W));
					
					for(Board child : tmp) {
						children.add(child);
						child.setParent(b);
					}
				}
			}
			
			min = Integer.MAX_VALUE;
			
			for(Board child : children) {
				
				Board temp = max(child, depth+1, d1, d2);
				
				if(temp!=null) {
					
					if(temp.evaluate() < min) {
						
						min = temp.evaluate();
						child.getParent().setValue(temp.evaluate());
					}
				} else {
					
					if(child.getValue() < min) {
						
						min = child.getValue();
						child.getParent().setValue(child.getValue());
					}
				}
			}
		}
		return null;
	}
	
	public Board max(Board b, int depth, byte d1, byte d2) {
		
		if(b.isTerminal() || depth==maxDepth) {
			
			return b;
		}	
		
		Board maxBoard = new Board();
		int max;
		
		if(depth==0) {
			
			ArrayList<Board> children = b.getChildren(d1, d2, Board.B);
			
			max = Integer.MIN_VALUE;
			
			for(Board child : children) {
				
				min(child, depth+1, d1, d2);
				
				if(child.getValue() > max) {
					
					max = child.getValue();
					maxBoard = new Board(child);
				}
			}
			
			return maxBoard;
			
		} else {
			
			ArrayList<Board> children = new ArrayList<Board>();
			ArrayList<Board> tmp;
			
			for(byte i=1; i<7; i++) {
				for(byte j=1; j<7; j++) {
	
					tmp = new ArrayList<Board>(b.getChildren(i, j, Board.B));
					
					for(Board child : tmp) {
						children.add(child);
						child.setParent(b);
					}
				}
			}

			max = Integer.MIN_VALUE;
			
			for(Board child : children) {
				
				Board temp = min(child, depth+1, d1, d2);
				
				if(temp!=null) {
					
					if(temp.evaluate() > max) {
						
						max = temp.evaluate();
						child.getParent().setValue(temp.evaluate());
					}
				} else {
					
					if(child.getValue() > max) {
						
						max = child.getValue();
						child.getParent().setValue(child.getValue());
					}
				}
			}
		}
		return null;
	}
}
