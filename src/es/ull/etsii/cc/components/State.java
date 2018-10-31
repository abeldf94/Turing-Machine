package es.ull.etsii.cc.components;

/**
 * @author Abel Delgado Falcón (<a href="mailto:alu0100792218@ull.edu.es">)
 * @version 1.0
 * @date 28 oct. 2018
 * 
 *       State representation for the Turing machine transitions.
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (!(other instanceof State))
			return false;

		State otherState = (State) other;
		return this.getId().equals(otherState.getId());
	}

	/** Getters and Setters **/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
