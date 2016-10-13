package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import online.Bot;

public class AboutGUI extends JDialog{

	ImageIcon about = null;
	ImageIcon chocobo = null;
	ImageIcon chocobo2 = null;
	JPanel zoneClient;
	JLayeredPane lpa = new JLayeredPane();
	
	
	public AboutGUI(ImageIcon about, ImageIcon chocobo, ImageIcon chocobo2){
		this.setSize(800, 480);
		this.setTitle("FFS - About");
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.about = about;
		this.chocobo = chocobo;
		this.chocobo2 = chocobo2;
		
		initControles();
	}
	
	JLabel lab = new JLabel();
	
	public void initControles(){
		zoneClient = (JPanel) this.getContentPane();
		zoneClient.add(lpa);
		lab.setBounds(0, 0, 800, 450);
		lab.setIcon(about);
		lpa.add(lab, 2);
		
		lpa.addMouseListener(new appMouseListener());
	}
	
	
 class appMouseListener implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel labChoco;
		
		if(e.getButton() == MouseEvent.BUTTON1){
			labChoco = new JLabel(AboutGUI.this.chocobo);
			labChoco.setBounds(e.getX() - 30, e.getY() - 40, 60, 80);
			lpa.add(labChoco, 0);
			lpa.revalidate();
			lpa.repaint();
		}
		if(e.getButton() == MouseEvent.BUTTON3){
			labChoco = new JLabel(AboutGUI.this.chocobo2);
			labChoco.setBounds(e.getX() - 30, e.getY() - 40, 60, 80);
			lpa.add(labChoco, 0);
			lpa.revalidate();
			lpa.repaint();
		}
		
		if(e.getButton() == MouseEvent.BUTTON2){
			lpa.removeAll();
			lpa.add(lab);
			lpa.revalidate();
			lpa.repaint();
		}

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
