package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
		this.controler = controler;
		components = new HashMap<String, JComponent>();
	}

	@Override
	public void init(String title, int width, int height, Color bgColor) {
		createWindow(title, width, height);
		int menuHeight = createMenuBar();
		createSurface(0, menuHeight, width, height - menuHeight, bgColor);
		frame.setVisible(true);
	}

	@Override
	public void createWindow(String title, int width, int height) {
		frame = new JFrame("Langtons Ant");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				controler.handleQuitRequest();
			}
		});
	}

	@Override
	public int createMenuBar() throws IllegalArgumentException {
		// returns height of the bar
		JMenuBar mBar;
		if (!components.containsKey(ConstantIDs.MENU_BAR_ID)) {
			mBar=new JMenuBar();
			components.put(ConstantIDs.MENU_BAR_ID.toString(), mBar);
			// frame.add(components.get(ConstantIDs.MENU_BAR_ID.toString()));
			JMenu general=new JMenu("General");
			JMenuItem quitItem=new JMenuItem("Quit");
			quitItem.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event) {
					controler.handleQuitRequest();
				}
			});
			general.add(quitItem);
			mBar.add(general);
			frame.setJMenuBar(mBar);
		} else {
			throw new IllegalArgumentException("Menu Bar already added");
		}
		return (int) Math.ceil(mBar.getSize().getHeight());
	}

	@SuppressWarnings("serial")
	@Override
	public void createSurface(int x, int y, int width, int height, Color bgColor) {
		surface = new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				((SwingDrawingManager) controler.drawingManager).paint(g);
			}
		};
		surface.setBounds(x, y, width, height);
		surface.setBackground(bgColor);
		frame.getContentPane().add(surface);
		// frame.pack();
	}

	@Override
	public boolean createYNDialogue(String question, String title) {
		return JOptionPane.showConfirmDialog(frame, question, title, JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
	}

	public JPanel getSurface() {
		return surface;
	}

	@Override
	public void addMenuToBar(String text) throws IllegalArgumentException {
		if (!components.containsKey(text)) {
			components.put(text, new JMenuItem(text));
		} else {
			throw new IllegalArgumentException("Menu does already exist");
		}
	}

	@Override
	public void closeWindow() {
		frame.setVisible(false);
		frame.dispose();
	}
}
