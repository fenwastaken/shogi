package pieces;

import gui.Board;
import gui.Cell;

import java.awt.Color;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Pawn extends Piece{

	String type = "";

	ImageIcon pawnT;
	ImageIcon pawnB;
	ImageIcon pawnZT;
	ImageIcon pawnZB;
	ImageIcon pawnA;

	public Pawn() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pawn(Pawn pawn, Boolean isTop, Cell attachedCell){
		this.pawnA = pawn.pawnA;
		this.pawnT = pawn.pawnT;
		this.pawnB = pawn.pawnB;
		this.pawnZT = pawn.pawnZT;
		this.pawnZB = pawn.pawnZB;

		this.isTop = isTop;
		this.attachedCell = attachedCell;
		this.setType("Pawn");

		setSprite();

	}

	public ImageIcon getPawnT() {
		return pawnT;
	}


	public void setPawnT(ImageIcon pawnT) {
		this.pawnT = pawnT;
	}


	public ImageIcon getPawnB() {
		return pawnB;
	}


	public void setPawnB(ImageIcon pawnB) {
		this.pawnB = pawnB;
	}


	public ImageIcon getPawnZT() {
		return pawnZT;
	}


	public void setPawnZT(ImageIcon pawnZT) {
		this.pawnZT = pawnZT;
	}


	public ImageIcon getPawnZB() {
		return pawnZB;
	}


	public void setPawnZB(ImageIcon pawnZB) {
		this.pawnZB = pawnZB;
	}


	public ImageIcon getPawnA() {
		return pawnA;
	}


	public void setPawnA(ImageIcon pawnA) {
		this.pawnA = pawnA;
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
				this.setIcon(pawnZT);
			}
			else{
				this.setIcon(pawnZB);
			}
		}
		else{
			if(isTop){
				this.setIcon(pawnT);
			}
			else{
				this.setIcon(pawnB);
			}
		}

	}

	public void highlight(boolean paintCells){

		int[] coords = this.getAttachedCellCoordinates();
		int x = coords[0];
		int y = coords[1];

		if(this.isPromoted){

			this.vCells = new Vector<Cell>();
			if(this.isTop){
				vCells.addElement(Board.cellFinder((x + 1) + "-" + (y - 1))); // top left
				vCells.addElement(Board.cellFinder((x + 1) + "-" + y)); // top
				vCells.addElement(Board.cellFinder((x + 1) + "-" + (y + 1))); // top right
				vCells.addElement(Board.cellFinder(x + "-" + (y - 1))); // left
				vCells.addElement(Board.cellFinder(x + "-" + (y + 1))); // right
				vCells.addElement(Board.cellFinder((x - 1) + "-" + y)); // bottom
			}
			else{
				vCells.addElement(Board.cellFinder((x - 1) + "-" + (y - 1))); // top left
				vCells.addElement(Board.cellFinder((x - 1) + "-" + y)); // top
				vCells.addElement(Board.cellFinder((x - 1) + "-" + (y + 1))); // top right
				vCells.addElement(Board.cellFinder(x + "-" + (y - 1))); // left
				vCells.addElement(Board.cellFinder(x + "-" + (y + 1))); // right
				vCells.addElement(Board.cellFinder((x + 1) + "-" + y)); // bottom
			}
			paintCells(paintCells);
			
			
		}
		else{

			this.vCells = new Vector<Cell>();
			Cell destination = null;

			if(this.isTop){
				destination = Board.cellFinder(x + 1 + "-" + y);
				this.vCells.add(destination);
			}
			else{
				destination = Board.cellFinder(x - 1 + "-" + y);
				this.vCells.add(destination);
			}
			paintCells(paintCells);
		}

	}

}
