package es.ull.etsii.cc.components;

import java.util.LinkedList;
import java.util.List;

/**
 * Input tape for the automaton.
 */
public class Tape {
	
	/** The input. */
	List<String> input;
	
	/**
	 * Instantiates a new tape.
	 */
	public Tape () {
		input = new LinkedList<>();
	}
	
	/** Getters and Setters **/

	public List<String> getInput() {
		return input;
	}

	public void setInput(List<String> input) {
		this.input = input;
	}
}
