package pieces;

import gui.Board;
import gui.Cell;

import java.util.Vector;

import javax.swing.ImageIcon;

public class Bishop extends Piece{

	String type = "";

	ImageIcon rookT;
	ImageIcon rookB;
	ImageIcon rookZT;
	ImageIcon rookZB;
	ImageIcon rookA;

	public Bishop() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bishop(Bishop rook, Boolean isTop, Cell attachedCell){
		this.rookA = rook.rookA;
		this.rookT = rook.rookT;
		this.rookB = rook.rookB;
		this.rookZT = rook.rookZT;
		this.rookZB = rook.rookZB;

		this.isTop = isTop;
		this.attachedCell = attachedCell;
		this.setType("Bishop");

		setSprite();

	}

	public ImageIcon getRookT() {
		return rookT;
	}


	public void setRookT(ImageIcon rookT) {
		this.rookT = rookT;
	}


	public ImageIcon getRookB() {
		return rookB;
	}


	public void setRookB(ImageIcon rookB) {
		this.rookB = rookB;
	}


	public ImageIcon getRookZT() {
		return rookZT;
	}


	public void setRookZT(ImageIcon rookZT) {
		this.rookZT = rookZT;
	}


	public ImageIcon getRookZB() {
		return rookZB;
	}


	public void setRookZB(ImageIcon rookZB) {
		this.rookZB = rookZB;
	}


	public ImageIcon getRookA() {
		return rookA;
	}


	public void setRookA(ImageIcon rookA) {
		this.rookA = rookA;
	}

	public String getType() {
		return this.type;
	}


	public void setType(String type) {
		this.type = type;
	}

	public void setSprite(){

		if(this.isPromoted){
			if(isTop){
				this.setIcon(rookZT);
			}
			else{
				this.setIcon(rookZB);
			}
		}
		else{
			if(isTop){
				this.setIcon(rookT);
			}
			else{
				this.setIcon(rookB);
			}
		}

	}

	public void calculateLines(){

		int[] coords = this.getAttachedCellCoordinates();
		int x = coords[0];
		int y = coords[1];

		for(int i = 1 ; i < 5; i++){

			boolean end = false;
			Cell cell = null;
			int varX = x;
			int varY = y;

			while(!end){


				switch(i){
				case 1: //top
					varX--;
					varY--;
					cell =  Board.cellFinder(varX + "-" + varY );
					break;
				case 2:
					varX++;//bot
					varY++;
					cell =  Board.cellFinder(varX + "-" + varY );
					break;
				case 3:
					varX++;
					varY--;//left
					cell =  Board.cellFinder(varX + "-" + varY );
					break;
				case 4:
					varX--;
					varY++;//right
					cell =  Board.cellFinder(varX + "-" + varY );
					break;
				}

				if(cell != null){
					if(Board.isCellOccupied(cell)){//met another piece
						vCells.addElement(cell);
						end = true;
						break;
					}
					else{
						vCells.addElement(cell);
					}
				}
				else{
					end = true;
					break;
				}
			}

		}
	}

	public void highlight(boolean paintCells){

		int[] coords = this.getAttachedCellCoordinates();
		int x = coords[0];
		int y = coords[1];
		this.vCells = new Vector<Cell>();

		if(this.isPromoted){	

			calculateLines();
			vCells.addElement(Board.cellFinder((x - 1) + "-" + (y))); // top left
			vCells.addElement(Board.cellFinder((x + 1) + "-" + (y))); // topright
			vCells.addElement(Board.cellFinder((x) + "-" + (y - 1))); // bottom left
			vCells.addElement(Board.cellFinder((x) + "-" + (y + 1))); // bottom right

			paintCells(paintCells);

		}
		else{

			calculateLines();
			paintCells(paintCells);


		}

	}

}


