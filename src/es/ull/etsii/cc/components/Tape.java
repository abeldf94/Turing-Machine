package es.ull.etsii.cc.components;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Abel Delgado Falcón (<a href="mailto:alu0100792218@ull.edu.es">)
 * @version 1.0
 * @date 31 oct. 2018
 *
 *       Input tape for the automaton.
 */
public class Tape {

	/** The input. */
	List<String> input;

	/**
	 * Instantiates a new tape.
	 */
	public Tape() {
		input = new LinkedList<>();
	}

	/**
	 * Checks if is empty.
	 *
	 * @return the boolean
	 */
	public Boolean isEmpty() {
		return input.isEmpty();
	}

	/**
	 * Reset input.
	 */
	public void reset() {
		input.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (input.size() == 0)
			return "Tape is empty right now.";

		String list = "[ ";
		for (int i = 0; i < input.size(); i++) {
			if (i == input.size() - 1) // If it's last position
				list += input.get(i) + " ";
			else
				list += input.get(i) + " | ";
		}
		list += "]";

		return list;
	}

	/** Getters and Setters **/

	public List<String> getInput() {
		return input;
	}

	public void setInput(List<String> input) {
		this.input = input;
	}
}
