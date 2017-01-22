package testing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import drawing.DrawingEngine;
import main.LangtonsAnt;

public class Main {
	public static void main(String[] args) {
		LangtonsAnt controler=new LangtonsAnt(DrawingEngine.SWING);
		JFrame frame=new JFrame("Langtons Ant");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter(){
			@Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (askForQuit(frame) == JOptionPane.YES_OPTION){
		            controler.sendCloseRequest();
		            frame.setVisible(false);
		            frame.dispose();
		            ///System.exit(0);
		        }
		    }
		});
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		JPanel panel=new JPanel(){
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				controler.getDrawingManager().draw();
			}
		};
		panel.setBounds(0, 0, 800, 600);
		panel.setBackground(Color.RED);
		frame.getContentPane().add(panel);
		controler.init(panel);
		//frame.pack();
		frame.setVisible(true);
		
		controler.start();
	}
	
	static int askForQuit(JFrame frame){
		return JOptionPane.showConfirmDialog(frame, 
	            "Are you sure you want to Quit?", "Quit?", 
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE);
	}
}
