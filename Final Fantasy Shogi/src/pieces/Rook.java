package pieces;

import gui.Board;
import gui.Cell;

import java.awt.Color;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Rook extends Piece{

	String type = "";

	ImageIcon knightT;
	ImageIcon knightB;
	ImageIcon knightZT;
	ImageIcon knightZB;
	ImageIcon knightA;

	public Rook() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rook(Rook knight, Boolean isTop, Cell attachedCell){
		this.knightA = knight.knightA;
		this.knightT = knight.knightT;
		this.knightB = knight.knightB;
		this.knightZT = knight.knightZT;
		this.knightZB = knight.knightZB;

		this.isTop = isTop;
		this.attachedCell = attachedCell;
		this.setType("Rook");

		setSprite();

	}

	public ImageIcon getKnightT() {
		return knightT;
	}


	public void setKnightT(ImageIcon knightT) {
		this.knightT = knightT;
	}


	public ImageIcon getKnightB() {
		return knightB;
	}


	public void setKnightB(ImageIcon knightB) {
		this.knightB = knightB;
	}


	public ImageIcon getKnightZT() {
		return knightZT;
	}


	public void setKnightZT(ImageIcon knightZT) {
		this.knightZT = knightZT;
	}


	public ImageIcon getKnightZB() {
		return knightZB;
	}


	public void setKnightZB(ImageIcon knightZB) {
		this.knightZB = knightZB;
	}


	public ImageIcon getKnightA() {
		return knightA;
	}


	public void setKnightA(ImageIcon knightA) {
		this.knightA = knightA;
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
				this.setIcon(knightZT);
			}
			else{
				this.setIcon(knightZB);
			}
		}
		else{
			if(isTop){
				this.setIcon(knightT);
			}
			else{
				this.setIcon(knightB);
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
				cell =  Board.cellFinder(varX + "-" + varY );
				break;
				case 2:
					varX++;//bot
					cell =  Board.cellFinder(varX + "-" + varY );
					break;
				case 3:
					varY--;//left
					cell =  Board.cellFinder(varX + "-" + varY );
					break;
				case 4:
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
			vCells.addElement(Board.cellFinder((x - 1) + "-" + (y - 1))); // top left
			vCells.addElement(Board.cellFinder((x - 1) + "-" + (y + 1))); // topright
			vCells.addElement(Board.cellFinder((x + 1) + "-" + (y - 1))); // bottom left
			vCells.addElement(Board.cellFinder((x + 1) + "-" + (y + 1))); // bottom right
			
			paintCells(paintCells);

		}
		else{

			calculateLines();
			paintCells(paintCells);


		}

	}
}
