package main;

import javax.swing.SwingUtilities;

import gui.GUI;
import model.Model;

public class Driver {
	public static void main(String[] args) {
		Model m = new Model("CROSSWD.TXT");
		SwingUtilities.invokeLater(new GUI(m));
//		SwingUtilities.invokeLater(new GUI(m));
	}
}
