package Cube_Crash;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Cube_Crash extends Frame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static void main(String args[]){
		JFrame frame=new JFrame("My blockie");
		JMenuBar menuBar=new JMenuBar();
		JMenu menu=new JMenu("Help");
		JMenuItem item_1=new JMenuItem("About the game");
		item_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Please read the text of help","information", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		frame.setBounds(100,100,340,420);
		frame.setJMenuBar(menuBar);
		menuBar.add(menu);
		menu.add(item_1);
		frame.setVisible(true);
	}
	
}
