package es.ull.etsii.cc.machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

	/** The iteration loop limit. */
	private final Integer ITERATION_LOOP_LIMIT = 1000;

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
	 * @throws Exception the exception
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
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void computeInput() throws IOException {

		// It won't comput if there is no input.
		if (inputTape == null || inputTape.getInput().isEmpty()) {
			System.out.println("You must define an input first");
			return;
		}

		List<String> tape = inputTape.getInput();

		// Starts at index = 2 because index = 0 is for white symbol
		// If input tape has [ 1 0 1 0 ], it will have [ . . 1 0 1 0 . . ]
		Integer head = 2;
		State currentState = initialState;
		String move = ""; // Move for the head
		Integer iteration = 0;
		Integer globalIteration = 0;

		printTapeStatus(globalIteration, head);

		String symbol = tape.get(head);

		// Keep loop until the current move is 'stop'
		// or the current state hasn't more transitions
		while(hasTransitions(currentState, symbol) && !move.equals(STOP)) {

			// Check if there are transitions for this state and this symbol
			Transition next = findTransition(currentState, symbol);

			if (next != null) { // If there is a transition
				currentState = next.getNextState(); // Update state
				move = next.getMove(); // Update head move
				// Just overwrite symbol if it's different
				if (!tape.get(head).equals(next.getWriteSymbol()))
					tape.set(head, next.getWriteSymbol());

			} else // If there are not transitions, stop
				move = STOP;

			// Move head depending on the transition move
			if (move.equals(LEFT))
				head--;
			else if (move.equals(RIGHT))
				head++;


			// "Infinite" tape, if it is in the borders of the list,
			// it add a new white symbol.
			if (head == tape.size())
				tape.add(tape.size(), white);
			else if (head == 0){
				tape.add(0, white);
				head++; // If a white is added at begin, head need to move to keep position
			}

			// Prevents the machine from falling in infinite loop
			if (iteration >= ITERATION_LOOP_LIMIT) {
				System.out.println("Warning: Turing machine it's iterating without find a solution.");
				System.out.println("If you want keep trying, insert 'continue'");
				System.out.println("If you want to stop machine, insert 'stop'.");
				System.out.print("Input: ");

				// Read user input decision
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String userInput = "";
				do {
					userInput = reader.readLine();
					System.out.println(userInput);
					if (!userInput.equals("continue") && !userInput.equals("stop"))
						System.out.print("Error: bad option.\nPlease follow the instructions and try again: ");

				} while (!userInput.equals("continue") && !userInput.equals("stop"));

				if (userInput.equals("continue"))
					iteration = 0;
				else if (userInput.equals("stop"))
					move = STOP;
			}

			iteration++;
			globalIteration++;

			printTapeStatus(globalIteration, head);

			// Update symbol
			if (!symbol.equals(tape.get(head)))
				symbol = tape.get(head);
		}

		if (acceptedStates.contains(currentState))
			System.out.println("Input is accepted.");
		else
			System.out.println("Input is not accepted.");
	}

	/**
	 * Checks if there are transitions available.
	 *
	 * @param currentState the current state
	 * @param symbol the symbol
	 * @return the boolean
	 */
	public Boolean hasTransitions(State currentState, String symbol) {
		for (Transition i : setOfTransitions)
			if (i.getReadSymbol().equals(symbol) && i.getCurrentState().equals(currentState))
				return true;

		return false;
	}

	/**
	 * Find transition if there is one available.
	 *
	 * @param currentState the current state
	 * @param symbol the symbol
	 * @return the transition
	 */
	private Transition findTransition(State currentState, String symbol) {
		for (Transition i : setOfTransitions)
			if (i.getReadSymbol().equals(symbol) && i.getCurrentState().equals(currentState))
				return i;

		return null;
	}

	/**
	 * Prints the tape status with head.
	 *
	 * @param global the global
	 * @param head the head
	 */
	private void printTapeStatus(Integer global, Integer head) {
		List<String> tape = inputTape.getInput();

		System.out.println("Iteration: " + global);
		String list = "... | ";
		for (int i = head; i < tape.size(); i++)
			list += tape.get(i) + " | ";

		list += " ...";
		System.out.println(list);
		System.out.println("      ^");
	}

	/**
	 * Write Turing machine content.
	 */
	public void writeMachine() {
		try {
			System.out.print("States Q: ");
			for (State i : getSetOfStates())
				System.out.print(i.getId() + " ");

			System.out.print("\nInput alphabet E: ");
			for (String i : getInputAlphabet().getElements())
				System.out.print(i + " ");

			System.out.print("\nTape alphabet P: ");
			for (String i : getTapeAlphabet().getElements())
				System.out.print(i + " ");

			System.out.println("\nInitial state s: " + getInitialState().getId());

			System.out.println("White symbol b: " + getWhite());

			System.out.print("Accepted states F: ");
			for (State i : getAcceptedStates())
				System.out.print(i.getId() + " ");

			System.out.println("\nTransitions d: ");
			for (Transition i : getSetOfTransitions())
				System.out.println(i.toString());

		} catch (Exception error) {
			System.out.println("Error: unable to write Turing machine.");
		}
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
