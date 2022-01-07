package gui;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.event_handlers.GuessButtonHandler;
import gui.event_handlers.InventoryButtonHandler;
import model.Model;
import model.Observer;

public class GUI implements Observer, Runnable {

	private JFrame _window;
	private JPanel _inventoryPanel;
	private JPanel _guessPanel;
	private Model _model;
	
	public GUI(Model m) {
		_model = m;
	}
	
	@Override public void run() {
		JPanel p = new JPanel();
		_window = new JFrame("Game!");
		_window.add(p);
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
				
		_inventoryPanel = new JPanel();
		_inventoryPanel.setLayout(new BoxLayout(_inventoryPanel, BoxLayout.X_AXIS));

		_guessPanel = new JPanel();
		_guessPanel.setLayout(new BoxLayout(_guessPanel, BoxLayout.X_AXIS));
		
		p.add(new JLabel("INVENTORY: "));
    		p.add(_inventoryPanel);
    		p.add(new JLabel("GUESS: "));
		p.add(_guessPanel);

		_model.addObserver(this);
		
		_window.pack();
		_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		_window.setVisible(true);
	}
	
	@Override
	public void update() {
		updateInventoryLetterDisplay();
		updateGuessLetterDisplay();
		_window.pack();
	}

	public void updateGuessLetterDisplay() {
		_guessPanel.removeAll();
		ArrayList<Character> guessLetters = _model.getGuess();
		for (int i=0; i<guessLetters.size(); i=i+1) {
			JButton b = new JButton(""+guessLetters.get(i));
			_guessPanel.add(b);
			b.addActionListener(new GuessButtonHandler(_model, i));
		}
	}

	public void updateInventoryLetterDisplay() {
		_inventoryPanel.removeAll();
		ArrayList<Character> inventoryLetters = _model.getLetters();
		for (int i=0; i<inventoryLetters.size(); i=i+1) {
			JButton b = new JButton(""+inventoryLetters.get(i));
			_inventoryPanel.add(b);
			b.addActionListener(new InventoryButtonHandler(_model, i));
		}
	}

}
