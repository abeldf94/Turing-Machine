package es.ull.etsii.cc.machine;

/**
 * @author Abel Delgado Falcón (<a href="mailto:alu0100792218@ull.edu.es">)
 * @version 1.0 
 * @date 28 oct. 2018
 * 
 * Turing machine implementation. It computes a given input after load a language from a file.
 *
 */
public class TuringMachine {
	
	/** The left movement. */
	private final String LEFT = "L";
	
	/** The right movement. */
	private final String RIGHT = "R";
	
	/** The stop movement. */
	private final String STOP = "S";
	
	/** The white symbol. */
	private final String WHITE = "$";
	
	/**
	 * Instantiates a new Turing machine.
	 */
	public TuringMachine() {}
	
	/** Getters and Setters **/

	public String getLEFT() {
		return LEFT;
	}

	public String getRIGHT() {
		return RIGHT;
	}

	public String getSTOP() {
		return STOP;
	}

	public String getWHITE() {
		return WHITE;
	}
	
}
