import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Vector;


public class Player {
    
	public static final int MAX = 1;
	public static final int MIN = -1;
	private int maxDepth;
	private int playerColor;
	private int d1;
	private int d2;
	
	public Player() {
		
		maxDepth = 2;
		playerColor = Board.B;
	}
	
	public Player(int maxDepth, int playerColor) {
		
		this.maxDepth = maxDepth;
		this.playerColor = playerColor;
	}
	
	public void roll() {
		
		this.d1 = (int)(Math.random()*6)+1;
		this.d2 = (int)(Math.random()*6)+1;
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
						if(tempB.moveIsLegal(from, to, playerColor, d1, d2)) {
							
							if((!dubs && i==0)||(dubs && i!=3)) {
								tempB.playMove(from, to, playerColor);
								tempB.print();
							}
							pD1 = true;
							gotit = true;
						} else {
							System.out.println("\nIllegal move, try again0. \n");
						}
						
					} else if((Math.abs(from-to) == d2) && direction && (!pD2 || dubs) || lastrun)  {
					
						/* same as above */
						if(tempB.moveIsLegal(from, to, playerColor, d1, d2)) {
							
							if((!dubs && i==0)||(dubs && i!=3)) {
								tempB.playMove(from, to, playerColor);
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
			moves[i] = new Move(from, to, playerColor);
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
	
	public int getD1() {
		
		return this.d1;
	}
	
	public int getD2() {
			
		return this.d2;
	}
	
	public int getDepth() {
		
		return this.maxDepth;
	}
	
	
	public Board MiniMax(Board b, int d1, int d2) {
		
		if(playerColor==Board.B) {
			
			return max(new Board(b), 0, d1 ,d2);
			
		} else if(playerColor==Board.W) {
			
			return min(new Board(b), 0, d1 ,d2);
			
		} else {
			return null;
		}
	}
	
	public Board min(Board b, int depth, int d1, int d2) {
		
		if(b.isTerminal() || depth==maxDepth) {
			
			return b;
		}
		
		Board minBoard = new Board();
		int min;
		
		if(depth==0) {
			
			ArrayList<Board> children = b.getChildren(d1, d2, Board.W);
			
			min = Integer.MAX_VALUE;
			
			for(Board child : children) {
				
				Board temp = max(child, depth+1, d1, d2);
				
				if(temp.evaluate() < min) {
					
					min = temp.evaluate();
					minBoard = new Board(temp);
				}
			}
		} else {
			
			Vector<ArrayList<Board>> poss = new Vector<ArrayList<Board>>();
			
			byte index = 0;
			
			for(int i=0; i<6; i++) {
				for(int j=0; j<6; j++) {
					
					poss.add(index, b.getChildren(i, j, Board.W));
					index++;
				}
			}
			
			min = Integer.MAX_VALUE;
			
			for(int i=0; i<36; i++) {
				for(Board child : poss.get(i)) {
					
					Board temp = max(child, depth+1, d1, d2);
					
					if(temp.evaluate() < min) {
						
						min = temp.evaluate();
						minBoard = new Board(temp);
					}
				}
			}
		}
		return minBoard;
	}
	
	public Board max(Board b, int depth, int d1, int d2) {
		
		if(b.isTerminal() || depth==maxDepth) {
			
			return b;
		}	
		
		Board maxBoard = new Board();
		int max;
		
		if(depth==0) {
			
			ArrayList<Board> children = b.getChildren(d1, d2, Board.B);
			
			max = Integer.MIN_VALUE;
			
			for(Board child : children) {
				
				Board temp = min(child, depth+1, d1, d2);
				
				if(temp.evaluate() > max) {
					
					max = temp.evaluate();
					maxBoard = new Board(temp);
				}
			}
		} else {
			
			Vector<ArrayList<Board>> poss = new Vector<ArrayList<Board>>(); 
			
			byte index = 0;
			
			for(int i=0; i<6; i++) {
				for(int j=0; j<6; j++) {
					
					poss.add(index, b.getChildren(i, j, Board.B));
					index++;
				}
			}
			
			max = Integer.MIN_VALUE;
			
			for(int i=0; i<36; i++) {
				for(Board child : poss.get(i)) {
					
					Board temp = min(child, depth+1, d1, d2);
					
					if(temp.evaluate() > max) {
						
						max = temp.evaluate();
						maxBoard = new Board(temp);
					}
					
				}
			}
		}
		return maxBoard;
	}
	
	
	/*public Board chance(Board b, int depth, int caller) {
		
		Board[] moves = new Board[21];
		
		for(int i=0; i<21; i++) {
			
			int d1,d2;
			
			if(i<6) {
				d1 = 1;
				d2 = i+1;
			} else if(i<11) {
				d1 = 2;
				d2 = i-4;
			} else if(i<15) {
				d1 = 3;
				d2 = i-8;
			} else if(i<18) {
				d1 = 4;
				d2 = i-11;
			} else if(i<20) {
				d1 = 5;
				d2 = i-13;
			} else {
				d1 = 6;
				d2 = 6;
			}
			
			if(caller==MAX) {
				moves[i] = min(b, depth+1, d1, d2);
			} else if(caller==MIN) {
				moves[i] = max(b, depth+1, d1, d2);
			}
		}
		
		return new Move(genVal(moves));
	}
	
	private int genVal(Move[] moves) {
		
		int val = 0;
		
		for(int i=0; i<21; i++) {
			
			if(i==0 || i==6 || i==11 || i==15 || i==18 || i==20) {
				val += moves[i].getVal()*2;
			} else {
				val += moves[i].getVal();
			}
		}
		
		return val;
	}*/
	
}
