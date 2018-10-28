package es.ull.etsii.cc.components;

/**
 * @author Abel Delgado Falcón (<a href="mailto:alu0100792218@ull.edu.es">)
 * @version 1.0 
 * @date 28 oct. 2018
 * 
 * State representation for the Turing machine transitions.
 *
 */
public class State {
	
	/** The id. */
	private String id;

	/**
	 * Instantiates a new state.
	 */
	public State() {
		id = null;
	}

	/**
	 * Instantiates a new state.
	 *
	 * @param id the id
	 */
	public State(String id) {
		this.id = id;
	}
	
	/** Getters and Setters **/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
