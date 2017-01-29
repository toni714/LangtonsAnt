package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import drawing.EngineDrawingManager;
import drawing.SwingDrawingManager;
import events.MyActionListener;
import events.MyEvent;
import main.GC;
import main.LangtonsAnt;
import main.Utils;
import units.Vector2D;

public class SwingWindowManager implements WindowManager {
	JFrame frame;
	JPanel surface;
	HashMap<String, JComponent> components;
	SwingDrawingManager drawingManager;

	public SwingWindowManager(LangtonsAnt controler, String title, int width, int height, Color bgColor) {
		components = new HashMap<String, JComponent>();
		
		createWindow(controler, title, width, height);
		
		addMenuBar(controler);
		Dimension dim=frame.getContentPane().getSize();
		createSurface(0, 0, (int)dim.getWidth(), (int)dim.getHeight(), bgColor);
		drawingManager = new SwingDrawingManager(controler, surface);
		frame.setVisible(true);
	}

	@Override
	public void createWindow(LangtonsAnt controler, String title, int width, int height) {
		frame = new JFrame("Langtons Ant");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setSize(width, height);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				controler.handleQuitRequest();
			}
		});
		frame.pack();
	}

	@SuppressWarnings("serial")
	@Override
	public void createSurface(int x, int y, int width, int height, Color bgColor) {
		GC.surfaceSize=new Vector2D(width, height);
		surface = new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				drawingManager.paint(g);
			}
		};
		surface.setBounds(x, y, width, height);
		surface.setBackground(bgColor);
		frame.getContentPane().add(surface);
		// frame.pack();
	}

	@Override
	public int addMenuBar(LangtonsAnt controler) throws IllegalArgumentException {
		// returns height of the bar
		JMenuBar mBar;
		if (!components.containsKey(ConstantIDs.MENU_BAR_ID)) {
			mBar = new JMenuBar();
			components.put(ConstantIDs.MENU_BAR_ID.toString(), mBar);
			// frame.add(components.get(ConstantIDs.MENU_BAR_ID.toString()));
			JMenu general = new JMenu("General");
			JMenuItem quitItem = new JMenuItem("Quit");
			quitItem.addActionListener(new ActionListener() {
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
		//System.out.println("actual: "+mBar.getPreferredSize().getHeight());
		return (int) Math.ceil(mBar.getPreferredSize().getHeight());
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
	public void addMenuToBar(String menuName) throws IllegalArgumentException {
		if (!components.containsKey(Utils.joinIDName(ConstantIDs.MENU_ID, menuName))) {
			JMenu menu = new JMenu(menuName);
			components.put(Utils.joinIDName(ConstantIDs.MENU_ID, menuName), menu);
			getMenuBar().add(menu);
		} else {
			throw new IllegalArgumentException("Menu does already exist");
		}
	}

	JMenuBar getMenuBar() {
		return (JMenuBar) components.get(ConstantIDs.MENU_BAR_ID.toString());
	}

	@Override
	public void closeWindow() {
		frame.setVisible(false);
		frame.dispose();
	}

	@Override
	public void addItemToMenu(String itemName, MyActionListener listener, String menuName)
			throws IllegalArgumentException {
		if (!components.containsKey(Utils.joinIDName(ConstantIDs.MENU_ITEM_ID, itemName))) {
			JMenu menu = (JMenu) components.get(Utils.joinIDName(ConstantIDs.MENU_ID, menuName));
			JMenuItem item = new JMenuItem(itemName);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					listener.action(new MyEvent(e.getSource()/*add arguments*/));
				}
			});
			menu.add(item);
			components.put(Utils.joinIDName(ConstantIDs.MENU_ITEM_ID, itemName), item);
		}
	}

	@Override
	public void addCheckboxToMenu(String itemName, MyActionListener listener, boolean defaultVal, String menuName)
			throws IllegalArgumentException {
		if (!components.containsKey(Utils.joinIDName(ConstantIDs.MENU_ITEM_ID, itemName))) {
			JMenu menu = (JMenu) components.get(Utils.joinIDName(ConstantIDs.MENU_ID, menuName));
			JCheckBoxMenuItem item = new JCheckBoxMenuItem(itemName, defaultVal);
			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					listener.action(new MyEvent(e.getSource()/*add arguments*/));
				}
			});
			menu.add(item);
			components.put(Utils.joinIDName(ConstantIDs.MENU_ITEM_ID, itemName), item);
		}
	}

	@Override
	public boolean getCheckboxValue(String name) {
		JMenu menu = (JMenu) components.get(Utils.joinIDName(ConstantIDs.MENU_ID, "Options"));
		JCheckBoxMenuItem checkBox = (JCheckBoxMenuItem) menu.getItem(0);
		// JCheckBoxMenuItem checkBox=(JCheckBoxMenuItem)
		// components.get(joinIDName(ConstantIDs.MENU_ITEM_ID, name));
		return checkBox.isSelected();
	}

	@Override
	public EngineDrawingManager getDrawingManager() {
		return drawingManager;
	}
}
