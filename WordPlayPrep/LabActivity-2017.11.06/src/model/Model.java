package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Model {

	private ArrayList<Observer> _observers;	
	private ArrayList<Character> _inventoryLetters;
	private ArrayList<Character> _guessLetters;
	
	public Model(String filename) {
		ArrayList<String> longWords = readDictionaryFromFile(filename);
		Collections.shuffle(longWords);
		String target = longWords.get(0);

		_observers = new ArrayList<Observer>();
		_guessLetters = new ArrayList<Character>();
		_inventoryLetters = string2charList(target);
		Collections.shuffle(_inventoryLetters);
	}
	
	public void moveLetterFromInventoryToGuess(int index) {
		_guessLetters.add(_inventoryLetters.remove(index));
		notifyObservers();		
	}
	
	public void moveLetterFromGuessToInventory(int index) {
		_inventoryLetters.add(_guessLetters.remove(index));
		notifyObservers();		
	}
	
	public ArrayList<Character> getLetters() { return _inventoryLetters; }
	public ArrayList<Character> getGuess() { return _guessLetters; }
	
	public void addObserver(Observer obs) {
		_observers.add(obs);
		notifyObservers();
	}

	public void notifyObservers() {
		for (Observer obs : _observers) {
			obs.update();
		}
	}

	public ArrayList<String> readDictionaryFromFile(String filename) {
		ArrayList<String> answer = new ArrayList<String>();
		try {
			for (String word : Files.readAllLines(Paths.get(filename))) {
				if (word.length() == 2) {  // hard coded for this exercise
					answer.add(word);
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return answer;
	}

	public ArrayList<Character> string2charList(String w) {
		ArrayList<Character> list = new ArrayList<Character>();
		for (int i=0; i<w.length(); i=i+1) {
			list.add(w.charAt(i));
		}
		return list;
	}
	
}
