package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import online.Bot;

public class InputName extends JDialog{

	JButton btOK = new JButton("OK");
	JTextField tfName = new JTextField();
	JLabel labText = new JLabel("Input your name: ");
	Board board = null;
	
	public InputName(Board board){
		this.setSize(200, 120);
		this.setTitle("FFS - select name");
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.board = board;
		
		initControles();
	}
	
	
	public void initControles(){
		JPanel zoneClient = (JPanel) this.getContentPane();
		zoneClient.setLayout(new BoxLayout(zoneClient, BoxLayout.Y_AXIS));
		labText.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		
		tfName.setAlignmentX(JTextField.CENTER_ALIGNMENT);
		tfName.setMaximumSize(new Dimension(200, 20));
		
		zoneClient.add(Box.createRigidArea(new Dimension(20, 10)));
		zoneClient.add(labText);
		zoneClient.add(Box.createRigidArea(new Dimension(20, 5)));
		zoneClient.add(tfName);
		zoneClient.add(Box.createRigidArea(new Dimension(20, 5)));
		
		btOK.setAlignmentX(JButton.CENTER_ALIGNMENT);
		zoneClient.add(btOK);
		
		btOK.addActionListener(new appActionListener());
		tfName.addActionListener(new appActionListener());
	}
	
	public void connect(String name, InputName IN){
		if(!Board.connected){
			Bot bot = new Bot(name, IN, board);
			Board.miConnect.setLabel("Disconnect");
		}
	}
	
	class appActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == btOK && !tfName.getText().isEmpty()){
				labText.setText("Connecting...");
				btOK.setEnabled(false);
				tfName.setEnabled(false);
				String name = tfName.getText();
				Thread th = new Thread(){
					public void run(){
						connect(name, InputName.this);
					}
				};
				
				th.start();	
				Board.setChatVisible(true);
			}
			
			if(e.getSource() == tfName){
				btOK.doClick();
			}
		}
		
	}
	
}
