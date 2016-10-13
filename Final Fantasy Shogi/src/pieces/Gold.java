package pieces;

import gui.Board;
import gui.Cell;

import java.awt.Color;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Gold extends Piece{

	String type = "";

	ImageIcon goldT;
	ImageIcon goldB;
	ImageIcon goldZT;
	ImageIcon goldZB;
	ImageIcon goldA;

	public Gold() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Gold(Gold gold, Boolean isTop, Cell attachedCell){
		this.goldA = gold.goldA;
		this.goldT = gold.goldT;
		this.goldB = gold.goldB;
		this.goldZT = gold.goldZT;
		this.goldZB = gold.goldZB;

		this.isTop = isTop;
		this.attachedCell = attachedCell;
		this.setType("Gold");

		setSprite();

	}

	public ImageIcon getgoldT() {
		return goldT;
	}


	public void setgoldT(ImageIcon goldT) {
		this.goldT = goldT;
	}


	public ImageIcon getgoldB() {
		return goldB;
	}


	public void setgoldB(ImageIcon goldB) {
		this.goldB = goldB;
	}


	public ImageIcon getgoldA() {
		return goldA;
	}


	public void setgoldA(ImageIcon goldA) {
		this.goldA = goldA;
	}

	public String getType() {
		return this.type;
	}


	public void setType(String type) {
		this.type = type;
	}

	public void setSprite(){


		if(isTop){
			this.setIcon(goldT);
		}
		else{
			this.setIcon(goldB);
		}
	}

	public void highlight(boolean paintCells){
		
		int[] coords = this.getAttachedCellCoordinates();
		int x = coords[0];
		int y = coords[1];
		
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

}
