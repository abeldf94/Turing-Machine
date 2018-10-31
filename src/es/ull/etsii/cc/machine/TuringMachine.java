package es.ull.etsii.cc.machine;

import java.util.ArrayList;
import java.util.List;

import es.ull.etsii.cc.components.Alphabet;
import es.ull.etsii.cc.components.State;
import es.ull.etsii.cc.components.Tape;
import es.ull.etsii.cc.components.Transition;

/**
 * @author Abel Delgado Falcón (<a href="mailto:alu0100792218@ull.edu.es">)
 * @version 1.0
 * @date 28 oct. 2018
 * 
 *       Turing machine implementation. It computes a given input after load a
 *       language from a file.
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
	private String white;

	/** The set of states. */
	private List<State> setOfStates;

	/** The input alphabet. */
	private Alphabet inputAlphabet;

	/** The tape alphabet. */
	private Alphabet tapeAlphabet;

	/** The initial state. */
	private State initialState;

	/** The set of transitions. */
	private List<Transition> setOfTransitions;

	/** The accepted states. */
	private List<State> acceptedStates;

	/** The input tape. */
	private Tape inputTape;

	/**
	 * Instantiates a new Turing machine.
	 * 
	 * @throws Exception
	 */
	public TuringMachine() throws Exception {
		setOfStates = new ArrayList<>();
		inputAlphabet = new Alphabet();
		tapeAlphabet = new Alphabet();
		initialState = new State();
		setOfTransitions = new ArrayList<>();
		acceptedStates = new ArrayList<>();
		inputTape = new Tape();
		white = new String();
	}

	/**
	 * Compute input and determine if it's accepted for the language.
	 */
	public void computeInput() {
		if (inputTape == null)
			throw new NullPointerException("tape is null");

		// TODO: implement algorithm

	}

	/**
	 * Write Turing machine content.
	 */
	public void writeMachine() {
		System.out.print("States Q: ");
		for (State i : getSetOfStates())
			System.out.print(i.getId() + " ");

		System.out.println();

		System.out.print("Input alphabet E: ");
		for (String i : getInputAlphabet().getElements())
			System.out.print(i + " ");

		System.out.println();

		System.out.print("Tape alphabet P: ");
		for (String i : getTapeAlphabet().getElements())
			System.out.print(i + " ");

		System.out.println();

		System.out.println("Initial state s: " + getInitialState().getId());

		System.out.println("White symbol b: " + getWhite());

		System.out.print("Accepted states F: ");
		for (State i : getAcceptedStates())
			System.out.print(i.getId() + " ");

		System.out.println();

		System.out.println("Transitions d: ");
		for (Transition i : getSetOfTransitions())
			System.out.println(i.toString());
	}

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

	public String getWhite() {
		return white;
	}

	public void setWhite(String white) {
		this.white = white;
	}

	public List<State> getSetOfStates() {
		return setOfStates;
	}

	public void setSetOfStates(List<State> setOfStates) {
		this.setOfStates = setOfStates;
	}

	public Alphabet getInputAlphabet() {
		return inputAlphabet;
	}

	public void setInputAlphabet(Alphabet inputAlphabet) {
		this.inputAlphabet = inputAlphabet;
	}

	public Alphabet getTapeAlphabet() {
		return tapeAlphabet;
	}

	public void setTapeAlphabet(Alphabet tapeAlphabet) {
		this.tapeAlphabet = tapeAlphabet;
	}

	public State getInitialState() {
		return initialState;
	}

	public void setInitialState(State initialState) {
		this.initialState = initialState;
	}

	public List<Transition> getSetOfTransitions() {
		return setOfTransitions;
	}

	public void setSetOfTransitions(List<Transition> setOfTransitions) {
		this.setOfTransitions = setOfTransitions;
	}

	public List<State> getAcceptedStates() {
		return acceptedStates;
	}

	public void setAcceptedStates(List<State> acceptedStates) {
		this.acceptedStates = acceptedStates;
	}

	public Tape getInputTape() {
		return inputTape;
	}

	public void setInputTape(Tape inputTape) {
		this.inputTape = inputTape;
	}

}
