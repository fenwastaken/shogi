package pieces;

import gui.Board;
import gui.Cell;

import java.awt.Color;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public abstract class Piece extends JLabel{

	boolean isTop;
	boolean isPromoted = false;
	Cell attachedCell;
	String type = "";
	Vector<Cell> vCells;
	
	
	public Piece() {
		super();
	}

	public boolean isTop() {
		return isTop;
	}

	public void setTop(boolean isTop) {
		this.isTop = isTop;
	}

	public Cell getAttachedCell() {
		return attachedCell;
	}

	public void setAttachedCell(Cell attachedCell) {
		this.attachedCell = attachedCell;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isPromoted() {
		return isPromoted;
	}

	public void setPromoted(boolean isPromoted) {
		this.isPromoted = isPromoted;
	}
	
	public Vector<Cell> getvCells() {
		return vCells;
	}

	public void setvCells(Vector<Cell> vCells) {
		this.vCells = vCells;
	}

	public abstract void setSprite();
	
	public abstract void highlight(boolean paintCells);
	
	public int[] getAttachedCellCoordinates(){
		String attachedCellName = this.getAttachedCell().getName();
		int x = Integer.parseInt(attachedCellName.substring(0, 1));
		int y = Integer.parseInt(attachedCellName.substring(2, 3));
		int[] ret = {x, y};
		return ret;
	}
	
	public boolean isCellInReach(Cell cell){
		boolean isInReach = false;
		if(this.vCells != null){
			for(Cell cl : this.vCells){
				if(cl == cell){
					isInReach = true;
					break;
				}
			}
		}
		return isInReach;
	}
	
	public void paintCells(boolean doIt){
		if(doIt){
			for(Cell cl : this.vCells){
				if(cl != null){
					if(Board.paintBg){
						cl.setOpaque(true);
					}
					if(Board.selectedPiece != null){ //pour le hover
						if(Board.isCellOccupied(cl)){
							if(Board.selectedPiece.isTop == Board.pieceFinder(cl).isTop){
								if(Board.paintBg){
									cl.setBackground(new Color(0, 90, 0, 100));
									//cl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
								}
								else{
									cl.setBorder(BorderFactory.createLineBorder(Color.GREEN));
								}
							}
							else{
								if(Board.paintBg){
									cl.setBackground(new Color(255, 0, 0, 100));
									//cl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
								}
								else{
									cl.setBorder(BorderFactory.createLineBorder(Color.RED));
								}
							}
						}
						else{
							if(Board.paintBg){
								cl.setBackground(new Color(0, 0, 255, 100));
								//cl.setBorder(BorderFactory.createLineBorder(Color.BLACK));
							}
							else{
								cl.setBorder(BorderFactory.createLineBorder(Color.BLUE));
							}
						}
					}
					else{//hover
						cl.setBackground(new Color(100, 20, 100, 100));
					}
					Board.lpane.revalidate();
					Board.lpane.repaint();
					
				}
			}	
		}
	}
	
}
