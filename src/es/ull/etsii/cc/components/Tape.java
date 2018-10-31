package es.ull.etsii.cc.components;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	List<String> tape;

	/**
	 * Instantiates a new tape.
	 */
	public Tape() {
		tape = new LinkedList<>();
	}

	/**
	 * Configure a new tape loading it from console user input.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void configureTape(String white) throws IOException {

		if (tape == null) // Check it's not null
			throw new NullPointerException("tape is null");

		tape.clear(); // Clear input list always

		System.out.print("Add a new tape: ");
		// Read input from console
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = reader.readLine();

		// Insert each character from string into the list
		for (int i = 1; i <= input.length(); i++) {
			tape.add(input.substring(i - 1, i));
		}

		// it add the white symbol at the begin and the end.
		tape.add(0, white);
		tape.add(tape.size(), white);

		if (!input.isEmpty())
			System.out.println("Loaded tape: " + toString());
		else
			System.out.println(toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (tape.size() == 0)
			return "Tape is empty right now.";

		String list = "[ ";
		for (int i = 0; i < tape.size(); i++) {
			if (i == tape.size() - 1) // If it's last position
				list += tape.get(i) + " ";
			else
				list += tape.get(i) + " | ";
		}
		list += "]";

		return list;
	}

	/** Getters and Setters **/

	public List<String> getInput() {
		return tape;
	}

	public void setInput(List<String> tape) {
		this.tape = tape;
	}
}
