package bol8_Ejer2;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.synth.Region;

public class tecladoMovl extends JFrame implements ActionListener {
	private JButton[] btnsTeclado;
	private String[] numsTeclado = { "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "*", "0", "#" };
	private JTextField numero;
	private JButton reset;
	private MouseListener Raton;
	private JMenuBar menu;
	String[] menus = { "Archivo", "Movil", "Otros" };
	char[] atajMenus = { 'A', 'M', 'O' };
	String[][] subMenus = { { "Grabar número", "Leer números" },
			{ "Reset", "-", "Salir" }, { "Acerca de..." } };
	char[][] atajSubMenus = { { 'G', 'L' }, { 'R', '-', 'S' }, { 'c' } };
	Color pulsado = new Color(100, 100, 225);

	private JMenuItem item(String titu, char ataj) {
		JMenuItem mnu = new JMenuItem(titu);
		mnu.setMnemonic(ataj);
		mnu.addActionListener(this);
		return mnu;
	}

	public tecladoMovl() {
		super("Teclado móvil");
		this.setLayout(null);
		this.setResizable(false);
		this.setFocusable(true);

		Raton evRaton = new Raton();
		Teclado evTeclado = new Teclado();

		btnsTeclado = new JButton[12];
		for (int i = 0, x = 5, y = 45; i < 12; i++, x += 60) {
			btnsTeclado[i] = new JButton(numsTeclado[i]);
			btnsTeclado[i].setSize(60, 60);
			btnsTeclado[i].setLocation(x, y);
			btnsTeclado[i].addMouseListener(evRaton);
			btnsTeclado[i].addKeyListener(evTeclado);
			this.add(btnsTeclado[i]);
			if (x == 125) {
				x = -55;
				y += 60;
			}
		}
		reset = new JButton("Reset");
		reset.setSize(reset.getPreferredSize());
		reset.setLocation(57, 300);
		reset.addActionListener(this);
		reset.addKeyListener(evTeclado);
		this.add(reset);

		numero = new JTextField();
		numero.setSize(185, 25);
		numero.setLocation(5, 10);
		numero.setEditable(false);
		this.add(numero);

		menu = new JMenuBar();

		this.setJMenuBar(menu);
		for (int i = 0; i < subMenus.length; i++) {
			JMenu m = new JMenu(menus[i]);
			m.setMnemonic(atajMenus[i]);
			for (int j = 0; j < subMenus[i].length; j++) {
				if (subMenus[i][j].equals("-")) {
					m.addSeparator();
				} else {
					JMenuItem mI = item(subMenus[i][j], atajSubMenus[i][j]);
					m.add(mI);
				}
			}
			menu.add(m);
		}
		this.addKeyListener(evTeclado);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				salir((Window)e.getSource());
			}
		});
	}

	public class Raton extends MouseAdapter {
		@Override
		public void mouseEntered(MouseEvent e) {
			super.mouseEntered(e);
			if (!((JButton) e.getSource()).getBackground().equals(pulsado))
				((JButton) e.getSource())
						.setBackground(new Color(255, 255, 255));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			super.mouseExited(e);
			if (!((JButton) e.getSource()).getBackground().equals(pulsado))
				((JButton) e.getSource()).setBackground(null);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			((JButton) e.getSource()).setBackground(pulsado);
			numero.setText(numero.getText()
					+ ((JButton) e.getSource()).getText());
		}
	}

	public class Teclado extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_3
					&& e.getModifiers() == e.ALT_GRAPH_MASK) {
				numero.setText(numero.getText() + "#");
				btnsTeclado[11].setBackground(pulsado);
			} else {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_0:
				case KeyEvent.VK_NUMPAD0:
					numero.setText(numero.getText() + "0");
					btnsTeclado[10].setBackground(pulsado);
					break;
				case KeyEvent.VK_1:
				case KeyEvent.VK_NUMPAD1:
					numero.setText(numero.getText() + "1");
					btnsTeclado[0].setBackground(pulsado);
					break;
				case KeyEvent.VK_2:
				case KeyEvent.VK_NUMPAD2:
					numero.setText(numero.getText() + "2");
					btnsTeclado[1].setBackground(pulsado);
					break;
				case KeyEvent.VK_3:
				case KeyEvent.VK_NUMPAD3:
					numero.setText(numero.getText() + "3");
					btnsTeclado[2].setBackground(pulsado);
					break;
				case KeyEvent.VK_4:
				case KeyEvent.VK_NUMPAD4:
					numero.setText(numero.getText() + "4");
					btnsTeclado[3].setBackground(pulsado);
					break;
				case KeyEvent.VK_5:
				case KeyEvent.VK_NUMPAD5:
					numero.setText(numero.getText() + "5");
					btnsTeclado[4].setBackground(pulsado);
					break;
				case KeyEvent.VK_6:
				case KeyEvent.VK_NUMPAD6:
					numero.setText(numero.getText() + "6");
					btnsTeclado[5].setBackground(pulsado);
					break;
				case KeyEvent.VK_7:
				case KeyEvent.VK_NUMPAD7:
					numero.setText(numero.getText() + "7");
					btnsTeclado[6].setBackground(pulsado);
					break;
				case KeyEvent.VK_8:
				case KeyEvent.VK_NUMPAD8:
					numero.setText(numero.getText() + "8");
					btnsTeclado[7].setBackground(pulsado);
					break;
				case KeyEvent.VK_9:
				case KeyEvent.VK_NUMPAD9:
					numero.setText(numero.getText() + "9");
					btnsTeclado[8].setBackground(pulsado);
					break;
				case 106:
					numero.setText(numero.getText() + "*");
					btnsTeclado[9].setBackground(pulsado);
					break;
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ruta = System.getProperty("user.home") + "/numerosTelefono.txt";
		if (e.getSource() == reset) {
			numero.setText("");
			for (int i = 0; i < 12; i++) {
				btnsTeclado[i].setBackground(null);
			}
		}
		if (e.getActionCommand() == "Reset") {
			numero.setText("");
			for (int i = 0; i < 12; i++) {
				btnsTeclado[i].setBackground(null);
			}
		}
		if (e.getActionCommand() == "Salir") {
			salir(this);
		}
		if (e.getActionCommand() == "Grabar número"
				|| e.getActionCommand() == "Leer números") {
			try {
				File f = new File(ruta);
				if (!f.exists()) {
					JOptionPane.showMessageDialog(this,
							"El archivo no existia, se ha creado", " ",
							JOptionPane.INFORMATION_MESSAGE);
					f.createNewFile();
				}
				String numeros = "";
				Scanner t = new Scanner(f);
				while (t.hasNext()) {
					numeros += t.nextLine();
					numeros = String.format("%s%n", numeros);
				}
				if (e.getActionCommand() == "Grabar número") {
					numeros = String.format("%s%s", numeros, numero.getText());
					t.close();
					PrintWriter pW = new PrintWriter(ruta);
					pW.write(numeros);
					pW.close();
					if (numero.getText().equals("")) {
						JOptionPane.showMessageDialog(this,
								"No se ha introducido ningún número", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(this,
								"Número añadido a la agenda", "Añadido",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					t.close();
					if (numeros.equals("")) {
						JOptionPane.showMessageDialog(this,
								"La agenda está vacía", "Agenda",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(this, numeros, "Agenda",
								JOptionPane.PLAIN_MESSAGE);
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getActionCommand() == "Acerca de...") {
			JOptionPane
					.showMessageDialog(
							this,
							String.format("Autor: Christopher González Teijeira%nVersión:1.2.7."),
							"Acerca de...", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	static void salir(Window f) {
		int res = JOptionPane.showConfirmDialog(f ,
				"Deseas salir del programa?", "Salir",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (res == JOptionPane.OK_OPTION)
			f.dispose();
	}
}