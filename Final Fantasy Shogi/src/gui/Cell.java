package gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import pieces.Bishop;
import pieces.Gold;
import pieces.Jking;
import pieces.King;
import pieces.Knight;
import pieces.Lancer;
import pieces.Pawn;
import pieces.Piece;
import pieces.Rook;
import pieces.Silver;

public class Cell extends JLabel{

	int xPos;
	int yPos;
	String name;
	Color color = Color.green;

	boolean debug = false;


	public Cell(int xPos, int yPos) {
		super();
		this.xPos = xPos;
		this.yPos = yPos;
		this.name = xPos + "-" + yPos;

		this.addMouseListener(new appMouseListener());
		/*
		if(((xPos + yPos)%2) > 0){
			this.color = Color.BLUE;
		}
		else{
			this.color = Color.RED;
		}
		 */

		if(debug){
			this.setBorder(BorderFactory.createLineBorder(color));
		}

	}


	public int getxPos() {
		return xPos;
	}



	public void setxPos(int xPos) {
		this.xPos = xPos;
	}



	public int getyPos() {
		return yPos;
	}



	public void setyPos(int yPos) {
		this.yPos = yPos;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Color getColor() {
		return color;
	}



	public void setColor(Color color) {
		this.color = color;
	}


	public String getSide(Piece piece){
		String ret = "null";
		if(piece.isTop()){
			ret = "J";
		}
		else{
			ret = "K";
		}
		return ret;
	}


	public void selectCell(){
		Board.setSelectionFrameVisible(true);
		Board.labPrinter.setText("Selected: " + name);
		String belongs = "";
		Piece attachedPiece = Board.pieceFinder(Cell.this);
		if(attachedPiece != null){
			if(attachedPiece.isTop()){
				belongs = "Jade";
			}
			else{
				belongs = "King";
			}

			if(Board.jadeTurn == attachedPiece.isTop()){
				Board.labPiecePrinter.setText(attachedPiece.getType() + ", " + belongs);
				Board.selectedPiece = attachedPiece;	
			}
		}
		else{
			Board.labPiecePrinter.setText("");
			Board.selectedPiece = null;
		}

		for(Cell cell : Board.vCells){
			cell.setBorder(null);
		}
		Cell.this.setBorder(BorderFactory.createLineBorder(color));
		Board.selectedCell = Cell.this;	

	}

	public void deselectCells(){
		for(Cell cell : Board.vCells){

			cell.setBackground(null);
			cell.setOpaque(false);
			cell.setBorder(null);

			Board.lpane.revalidate();
			Board.lpane.repaint();

		}
		Board.selectedCell = null;	
		Board.labPiecePrinter.setText("");
		Board.labPrinter.setText("Selected: -");
		Board.setSelectionFrameVisible(false);
	}

	public void movePiece(){
		Board.selectedPiece.setAttachedCell(Cell.this);
		Board.pieceBounder(Board.selectedPiece);
		deselectCells();
		checkPromotion(Board.selectedPiece, Cell.this);
	}

	public void checkPromotion(Piece piece, Cell destination){
		int line = Integer.parseInt(destination.getName().substring(0, 1));
		boolean isTop = piece.isTop();
		boolean canBePromoted = false;
		if(isTop){
			if(line > 6){
				canBePromoted = true;
			}
		}
		else{
			if(line < 4){
				canBePromoted = true;
			}
		}
		if(canBePromoted && !piece.isPromoted() && !checkWin()){

			if(piece.getType().equals("Pawn") || piece.getType().equals("Lancer")){
				if(!piece.isTop()){
					if(Integer.parseInt(Cell.this.getName().substring(0, 1)) < 2){
						Board.setPromoteFrameVisible(true, false);
					}
					else{
						Board.setPromoteFrameVisible(true, true);
					}
				}
				else{
					if(Integer.parseInt(Cell.this.getName().substring(0, 1)) > 8){
						Board.setPromoteFrameVisible(true, false);
					}
					else{
						Board.setPromoteFrameVisible(true, true);
					}
				}
			}
			else if(piece.getType().equals("Knight")){
				if(!piece.isTop()){
					if(Integer.parseInt(Cell.this.getName().substring(0, 1)) < 3){
						Board.setPromoteFrameVisible(true, false);
					}
					else{
						Board.setPromoteFrameVisible(true, true);
					}
				}
				else{
					if(Integer.parseInt(Cell.this.getName().substring(0, 1)) > 7){
						Board.setPromoteFrameVisible(true, false);
					}
					else{
						Board.setPromoteFrameVisible(true, true);
					}
				}
			}
			else{
				Board.setPromoteFrameVisible(true, true);
			}

			for(Cell cl : Board.vCells){ // hiding the next player's selection
				cl.setOpaque(false);
			}

			Board.promoting = true;// locking the game till clicked on yes/no
			piece.getAttachedCell().setBorder(BorderFactory.createLineBorder(Color.green));
		}
	}

	public void highlight(){
		if(Board.selectedCell != null && Board.isCellOccupied(Cell.this)){
			Board.selectedPiece.highlight(true);
		}
	}

	public void deselectHolder(){
		if(Board.selectedHolder != null){
			Board.selectedHolder.setBorder(null);
			Board.selectedHolder = null;
		}
	}

	public boolean hasLinePawn(Boolean isTop){
		boolean hasPawn = false;
		int y = Integer.parseInt(Cell.this.getName().substring(2));
		for(Piece piece : Board.vPieces){
			if(piece.getType().equals("Pawn")){
				int pieceY = Integer.parseInt(piece.getAttachedCell().getName().substring(2));
				if(pieceY == y && piece.isTop() == isTop){
					hasPawn = true;
					break;
				}
			}
		}
		return  hasPawn;
	}

	public Boolean dropPiece(Piece attachedPiece){
		Boolean dropped = false;
		if(Board.selectedHolder != null){
			if(attachedPiece != null){
				deselectHolder();
				System.out.println("CAN'T DROP HERE");
			}
			else{

				String holderName = Board.selectedHolder.getName();
				String side = holderName.substring(0, 1);
				String type = holderName.substring(2);
				System.out.println("that is " + side + ", " + type);

				Boolean isTop = false;
				if(side.equals("T")){
					isTop = true;
				}

				if(!type.equals("Pawn") || !hasLinePawn(isTop)){

					//check row [prevent knights, pawns and lancers to be dropped where they can't move]
					boolean canMove = true;
					int row = Integer.parseInt(Cell.this.getName().substring(0, 1));
					if(isTop){
						if(((type.equals("Pawn") || type.equals("Lancer")) &&  row == 9) || ((type.equals("Knight")) && (row > 7))){
							canMove = false;
							System.out.println("LIMIT REACHED (" + type + ")");
						}
					}
					else{
						if(((type.equals("Pawn") || type.equals("Lancer")) &&  row == 1) || ((type.equals("Knight")) && (row < 3))){
							canMove = false;
							System.out.println("LIMIT REACHED (" + type + ")");
						}
					}
					if(canMove){
						switch(type){
						case "King" :
							Jking jade = new Jking(Board.jkingTemplate, isTop, Board.cellFinder(Cell.this.getName()));
							jade.setPromoted(true);
							Board.pieceMaker(jade);
							break;
						case "Jade" :
							King king = new King(Board.kingTemplate, isTop, Board.cellFinder(Cell.this.getName()));
							king.setPromoted(true);
							Board.pieceMaker(king);
							break;
						case "Gold" :
							Gold gold = new Gold(Board.goldTemplate, isTop, Board.cellFinder(Cell.this.getName()));
							gold.setPromoted(true);
							Board.pieceMaker(gold);
							break;
						case "Silver" :
							Silver silver = new Silver(Board.silverTemplate, isTop, Board.cellFinder(Cell.this.getName()));
							Board.pieceMaker(silver);
							break;
						case "Knight" :
							Knight knight = new Knight(Board.horseTemplate, isTop, Board.cellFinder(Cell.this.getName()));
							Board.pieceMaker(knight);
							break;
						case "Lancer" :
							Lancer lancer = new Lancer(Board.lancerTemplate, isTop, Board.cellFinder(Cell.this.getName()));
							Board.pieceMaker(lancer);
							break;
						case "Pawn" :
							Pawn pawn = new Pawn(Board.pawnTemplate, isTop, Board.cellFinder(Cell.this.getName()));
							Board.pieceMaker(pawn);
							break;
						case "Rook" :
							Rook rook = new Rook(Board.knightTemplate, isTop, Board.cellFinder(Cell.this.getName()));
							Board.pieceMaker(rook);
							break;
						case "Bishop" :
							Bishop bishop = new Bishop(Board.rookTemplate, isTop, Board.cellFinder(Cell.this.getName()));
							Board.pieceMaker(bishop);
							break;
						}
						dropped = true;
						Board.selectedHolder.decreaseCounter();
						if(Board.connected && Board.bot != null && Board.matchup != null){
							Board.xml = "|$> drop ;" + Cell.this.getName() + ":" + type + "%" + isTop;
							System.out.println("DROP XML: " + "|$> drop ;" + Cell.this.getName() + ":" + type + "%" + isTop);
							Board.xmlSender();
						}
					}
				}
				deselectHolder();
			}
		}
		return dropped;
	}

	public boolean checkWin(){
		boolean over = false;
		boolean jade = false;
		boolean king = false;
		for(Piece piece : Board.vPieces){
			if(piece.getType().equals("King")){
				king = true;
			}
			if(piece.getType().equals("Jade")){
				jade = true;
			}
		}

		if(king == false || jade == false){
			over = true;
			if(king == true){
				Board.gameOver = true;
				Board.labPrinter.setText("Game Over");
				Board.labPiecePrinter.setText("King won!");
			}
			else{
				Board.gameOver = true;
				Board.labPrinter.setText("Game Over");
				Board.labPiecePrinter.setText("Jade won!");
			}
			Board.setSelectionFrameVisible(true);
		}
		//end game online
		return over; //used to check if turn must be switched when capture occured
	}

	public void turnChanger(){

		if(Board.jadeTurn == true){
			Board.jadeTurn = false;
			System.out.println("King's turn");
		}
		else{
			Board.jadeTurn = true;
			System.out.println("jade's turn");
		}
		paintPieces();
		
		if(!Board.isJade){
			Board.kingChecker(true);
		}
		else{
			Board.kingChecker(false);
		}

		if(Board.connected){
			if(Board.isMyTurn){
				Board.isMyTurn = false;
			}
			else{
				Board.isMyTurn = true;
			}
		}
	}

	public void paintPieces(){
		Color color = null;
		if(Board.connected){
			if(Board.isJade != Board.jadeTurn){
				color = new Color(100, 0, 0, 100);
			}
			else{
				color = new Color(0, 100, 0, 100);
			}
		}
		else{
			color = new Color(0,100,0,100);
		}

		deselectCells();
		for(Cell cl : Board.vCells){
			if(Board.isCellOccupied(cl)){
				for(Piece pc : Board.vPieces){
					if(pc.getAttachedCell() == cl){
						if(pc.isTop() == Board.jadeTurn){
							cl.setBackground(color);
							cl.setOpaque(true);
						}
					}
				}
			}
		}

		if(Board.lastPlayed != null){
			Board.lastPlayed.getAttachedCell().setBackground(new Color(100, 0, 100, 100));
			Board.lastPlayed.getAttachedCell().setOpaque(true);
			Board.lastPlayed = null;
		}

		Board.lpane.revalidate();
		Board.lpane.repaint();
	}

	class appMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

			if(Board.gameOver == false && Board.isCellOccupied(Cell.this) && Board.selectedCell == null && (Board.pieceFinder(Cell.this).isTop() != Board.jadeTurn)){
				Piece pc = null;
					pc = Board.pieceFinder(Cell.this);
					pc.highlight(true);
					deselectCells();
					for(Cell cl : pc.getvCells()){
						if(cl != null){
							cl.setBackground(new Color(100, 20, 100, 100));
							cl.setOpaque(true);
						}
					Cell.this.setBackground(new Color(100, 20, 100, 100));
					Cell.this.setOpaque(true);
					Board.lpane.revalidate();
					Board.lpane.repaint();
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			//System.out.println(Cell.this.getName());
			
			//uncolor hover
			if((Cell.this.getBackground().getRGB() == new Color(200, 0, 0, 100).getRGB() || Cell.this.getBackground().getRGB() == new Color(100, 20, 100, 100).getRGB()) && !Board.gameOver && Board.selectedCell == null){
				for(Cell cell : Board.vCells){
					if(cell.getBackground().getRGB() == new Color(200, 0, 0, 100).getRGB() || cell.getBackground().getRGB() == new Color(100, 20, 100, 100).getRGB()){
						cell.setBackground(null);
						cell.setOpaque(false);
						cell.setBorder(null);
					}

					Board.lpane.revalidate();
					Board.lpane.repaint();

				}
			}
			

			if(e.getButton() == MouseEvent.BUTTON1 && !Board.gameOver && !Board.promoting && Board.isMyTurn){
				Piece attachedPiece = Board.pieceFinder(Cell.this);
				//drop
				Boolean dropped = dropPiece(attachedPiece);
				if(!dropped){//stop here if dropped
					if(Cell.this == Board.selectedCell){ //if selects already selected cell [deselect]
						Cell.this.setBorder(null);
						Board.selectedCell = null;
						Board.labPrinter.setText("Selected: -");
						Board.labPiecePrinter.setText("");
						Board.setSelectionFrameVisible(false);
						deselectCells();
					}
					else{
						//move
						if(Board.selectedCell != null && Board.selectedPiece != null && Board.selectedPiece.isCellInReach(Cell.this)){ //if both cell and piece are selected [move]
							if(attachedPiece == null){
								//System.out.println("DEBUG move");
								System.out.println(Board.selectedPiece.getType() + "(" + Board.selectedCell.getName() + getSide(Board.selectedPiece) + ") moved to " + Cell.this.getName());
								Board.xml = "|$> move ;" + Board.selectedCell.getName() + ":" + Cell.this.getName();
								movePiece();
								if(!Board.promoting){
									if(Board.connected && Board.matchup != null && Board.bot != null){
										Board.xml = Board.xml + "%N";
										Board.xmlSender(); //send movement coordinates ---------------------
									}
									if(!Board.connected){
										Board.lastPlayed = Board.selectedPiece;
									}
									turnChanger();//change turn on move
								}

							}
							else if(attachedPiece.isTop() != Board.selectedPiece.isTop()){ //take piece if not same side
								//System.out.println("DEBUG capture");
								System.out.println(Board.selectedPiece.getType() + "(" + Board.selectedCell.getName() + "-" + getSide(Board.selectedPiece) + ") took " + attachedPiece.getType() + "-" + getSide(attachedPiece) + "(" + Cell.this.getName() + ")");
								Board.xml = "|$> take ;" + Board.selectedCell.getName() + ":" + Cell.this.getName();
								//capture
								Board.holderFinder(attachedPiece.isTop(), attachedPiece.getType()).increaseCounter();

								Board.lpane.remove(attachedPiece);
								Board.vPieces.remove(attachedPiece);
								movePiece();
								attachedPiece.setAttachedCell(null);
								attachedPiece = null;
								checkWin();
								if(Board.connected && Board.matchup != null && Board.bot != null && !Board.promoting){
									Board.xml = Board.xml + "%N";
									Board.xmlSender(); //send movement coordinates ---------------------
								}
								if(!Board.gameOver && !Board.promoting){
									if(!Board.connected){
										Board.lastPlayed = Board.selectedPiece;
									}
									turnChanger();// change turn on capture
								}

							}
							else{ //same side -> reselection
								//System.out.println("DEBUG reselection");
								deselectCells();
								selectCell();
								highlight();
							}
						}
						else{//simple select
							//System.out.println("DEBUG select");
							if(attachedPiece == null || Board.jadeTurn == attachedPiece.isTop()){
								deselectCells();
								selectCell();
								highlight();
							}
							else{
								deselectCells();
							}
						}

					}
				}
				else{
					deselectCells();
					if(!Board.connected){
						Board.lastPlayed = Board.selectedPiece;
					}
					turnChanger();//change turn when dropped
				}

			}
			System.out.println("Pieces en jeu: " + Board.vPieces.size());

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}




}







