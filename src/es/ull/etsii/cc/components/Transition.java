package es.ull.etsii.cc.components;

/**
 * @author Abel Delgado Falcón (<a href="mailto:alu0100792218@ull.edu.es">)
 * @version 1.0
 * @date 28 oct. 2018
 * 
 *       Transition representation for the Turing machine.
 *
 */
public class Transition {

	/** The current state. */
	private State currentState;

	/** The next state. */
	private State nextState;

	/** The entry symbol. */
	private String entrySymbol;

	/** The output symbol. */
	private String outputSymbol;

	/** The move. */
	private String move;

	/**
	 * Instantiates a new transition.
	 *
	 * @param currentState the current state
	 * @param nextState the next state
	 * @param entrySymbol the entry symbol
	 * @param outputSymbol the output symbol
	 * @param move the move
	 */
	public Transition(State currentState, String entrySymbol, State nextState, String outputSymbol, String move) {
		super();
		this.currentState = currentState;
		this.nextState = nextState;
		this.entrySymbol = entrySymbol;
		this.outputSymbol = outputSymbol;
		this.move = move;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return currentState.getId() + " " + entrySymbol + " " + nextState.getId() + " " + outputSymbol + " " + move;
	}

	/** Getters and Setters **/

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public State getNextState() {
		return nextState;
	}

	public void setNextState(State nextState) {
		this.nextState = nextState;
	}

	public String getEntrySymbol() {
		return entrySymbol;
	}

	public void setEntrySymbol(String entrySymbol) {
		this.entrySymbol = entrySymbol;
	}

	public String getOutputSymbol() {
		return outputSymbol;
	}

	public void setOutputSymbol(String outputSymbol) {
		this.outputSymbol = outputSymbol;
	}

	public String getMove() {
		return move;
	}

	public void setMove(String move) {
		this.move = move;
	}

}
