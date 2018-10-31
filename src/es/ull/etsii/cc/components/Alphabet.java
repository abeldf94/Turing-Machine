package es.ull.etsii.cc.components;

import java.util.LinkedHashSet;

/**
 * @author Abel Delgado Falcón (<a href="mailto:alu0100792218@ull.edu.es">)
 * @version 1.0
 * @date 28 oct. 2018
 * 
 *       Generic class for the Turing Machine alphabet (Input and tape).
 *
 */
public class Alphabet {

	/** The elements. */
	private LinkedHashSet<String> elements;

	/**
	 * Instantiates a new alphabet.
	 */
	public Alphabet() {
		elements = new LinkedHashSet<>();
	}

	/**
	 * Adds a element to the alphabet.
	 *
	 * @param element the element
	 */
	public void addElement(String element) {
		elements.add(element);
	}

	/** Getters and Setters **/

	public LinkedHashSet<String> getElements() {
		return elements;
	}

	public void setElements(LinkedHashSet<String> elements) {
		this.elements = elements;
	}
}
