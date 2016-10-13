package pieces;

import gui.Board;
import gui.Cell;

import java.awt.Color;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Silver extends Piece{

	String type = "";

	ImageIcon silverT;
	ImageIcon silverB;
	ImageIcon silverZT;
	ImageIcon silverZB;
	ImageIcon silverA;

	public Silver() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Silver(Silver silver, Boolean isTop, Cell attachedCell){
		this.silverA = silver.silverA;
		this.silverT = silver.silverT;
		this.silverB = silver.silverB;
		this.silverZT = silver.silverZT;
		this.silverZB = silver.silverZB;

		this.isTop = isTop;
		this.attachedCell = attachedCell;
		this.setType("Silver");

		setSprite();

	}

	public ImageIcon getsilverT() {
		return silverT;
	}


	public void setsilverT(ImageIcon silverT) {
		this.silverT = silverT;
	}


	public ImageIcon getsilverB() {
		return silverB;
	}


	public void setsilverB(ImageIcon silverB) {
		this.silverB = silverB;
	}


	public ImageIcon getsilverZT() {
		return silverZT;
	}


	public void setsilverZT(ImageIcon silverZT) {
		this.silverZT = silverZT;
	}


	public ImageIcon getsilverZB() {
		return silverZB;
	}


	public void setsilverZB(ImageIcon silverZB) {
		this.silverZB = silverZB;
	}


	public ImageIcon getsilverA() {
		return silverA;
	}


	public void setsilverA(ImageIcon silverA) {
		this.silverA = silverA;
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
				this.setIcon(silverZT);
			}
			else{
				this.setIcon(silverZB);
			}
		}
		else{
			if(isTop){
				this.setIcon(silverT);
			}
			else{
				this.setIcon(silverB);
			}
		}

	}

	public void highlight(boolean paintCells){

		int[] coords = this.getAttachedCellCoordinates();
		int x = coords[0];
		int y = coords[1];

		this.vCells = new Vector<Cell>();

		if(this.isPromoted){
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
		}
		else{

			if(this.isTop){
				vCells.addElement(Board.cellFinder((x + 1) + "-" + (y - 1))); // top left
				vCells.addElement(Board.cellFinder((x + 1) + "-" + y)); // top
				vCells.addElement(Board.cellFinder((x + 1) + "-" + (y + 1))); // top right
				vCells.addElement(Board.cellFinder((x - 1) + "-" + (y - 1))); // bottom left
				vCells.addElement(Board.cellFinder((x - 1) + "-" + (y + 1))); // bottom right
			}
			else{
				vCells.addElement(Board.cellFinder((x - 1) + "-" + (y - 1))); // top left
				vCells.addElement(Board.cellFinder((x - 1) + "-" + y)); // top
				vCells.addElement(Board.cellFinder((x - 1) + "-" + (y + 1))); // top right
				vCells.addElement(Board.cellFinder((x + 1) + "-" + (y - 1))); // bottom left
				vCells.addElement(Board.cellFinder((x + 1) + "-" + (y + 1))); // bottom right
			}
		}


		paintCells(paintCells);


	}
}
