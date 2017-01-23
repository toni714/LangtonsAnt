package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import drawing.SwingDrawingManager;
import main.LangtonsAnt;

public class SwingGraphicsManager implements GraphicsManager {
	LangtonsAnt controler;
	
	JFrame frame;
	JPanel surface;
	HashMap<String, JComponent> components;

	public SwingGraphicsManager(LangtonsAnt controler) {
		this.controler=controler;
	}
	
	@SuppressWarnings("serial")
	@Override
	public void createWindow(String title, int width, int height, Color bgColor) {
		frame = new JFrame("Langtons Ant");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new java.awt.event.WindowAdapter(){
			@Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (createYNDialogue("Do You really want to Quit?", "Quit?") ==true){
		            controler.sendCloseRequest();
		            frame.setVisible(false);
		            frame.dispose();
		        }
		    }
		});
		
		surface=new JPanel(){
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				((SwingDrawingManager)controler.drawingManager).paint(g);
			}
		};
		surface.setBounds(0, 0, width, height);
		surface.setBackground(bgColor);
		frame.getContentPane().add(surface);
		//frame.pack();
		frame.setVisible(true);
		
	}
	
	@Override
	public boolean createYNDialogue(String question, String title){
		return JOptionPane.showConfirmDialog(frame, question, title,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION;
	}

	public JPanel getSurface() {
		return surface;
	}

	/*
	 * @Override public void createNewSlider(String name, float min, float max,
	 * float step, int x, int y, int width, int height) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 */

}
