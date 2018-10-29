package es.ull.etsii.cc.machine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.ull.etsii.cc.components.Alphabet;
import es.ull.etsii.cc.components.State;
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

	/**
	 * Instantiates a new Turing machine.
	 * @throws Exception 
	 */
	public TuringMachine(File inputFile) throws Exception {
		setOfStates = new ArrayList<>();
		inputAlphabet = new Alphabet();
		tapeAlphabet = new Alphabet();
		initialState = new State();
		setOfTransitions = new ArrayList<>();
		acceptedStates = new ArrayList<>();
		white = new String();
		loadFile(inputFile);
	}

	/**
	 * Load the Turing machine from file.
	 *
	 * @param file the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void loadFile(File file) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(file));

		String line = reader.readLine();
		String[] tokens = line.split("\\s+");

		// Skip first lines if they are comments
		while (tokens[0].equals("#")) {
			line = reader.readLine();
			tokens = line.split("\\s+");
		}

		tokens = line.split("#")[0].split("\\s+"); // Ignore comments in line

		// Here come the set of states so we create it
		for (String i : tokens) {
			State state = new State(i);
			setOfStates.add(state);
		}

		// Read input alphabet and ignore comments
		line = reader.readLine();
		tokens = line.split("#")[0].split("\\s+");

		// Create input alphabet
		for (String i : tokens)
			inputAlphabet.addElement(i);

		// Read tape alphabet and ignore comments
		line = reader.readLine();
		tokens = line.split("#")[0].split("\\s+");

		// Create tape alphabet
		for (String i : tokens)
			tapeAlphabet.addElement(i);

		// Read initial state
		line = reader.readLine();
		tokens = line.split("#")[0].split("\\s+");

		// Set initial state
		setInitialState(new State(tokens[0]));

		// Check initial state is correct
		if (!checkInitialState()) {
			reader.close();
			throw new Exception("initial state not belongs to the set of states.");
		}

		// Read white symbol
		line = reader.readLine();
		tokens = line.split("#")[0].split("\\s+");

		// Set the white symbol
		setWhite(tokens[0]);

		// Check white symbol is correct
		if (!checkWhiteSymbol()) { 
			reader.close(); 
			throw new Exception("white symbol not belongs to the tape alphabet."); 
		}

		// Read accepted states
		line = reader.readLine();
		tokens = line.split("#")[0].split("\\s+");

		// Read accepted states and create it
		for (String i : tokens)
			acceptedStates.add(new State(i));
		
		// Check that accepted states is included in the set of states
		if (!checkAcceptedStates()) {
			reader.close();
			throw new Exception("set of accepted states is incorrectly.");
		}

		// Read transitions and create it
		while ((line = reader.readLine()) != null) {
			tokens = line.split("#")[0].split("\\s+");
			
			// Current state, entry symbol, next state, output symbol and movement
			Transition transition = new Transition(new State(tokens[0]), tokens[1], new State(tokens[2]), tokens[3],
					tokens[4]);
			setOfTransitions.add(transition);
		}

		reader.close();

		// Check transitions are correctly
		if (!checkTransitions()) 
			throw new Exception("transitions are incorrectly."); 
		
	}

	/**
	 * Compute input and determine if it's accepted for the language.
	 */
	public void computeInput() {
		System.out.println("Computing, not algorithm implemented.");
	}

	/**
	 * Check initial state.
	 *
	 * @return the boolean
	 */
	public Boolean checkInitialState() {
		for (State i : getSetOfStates()) {
			if (i.getId().equals(getInitialState().getId()))
				return true;
		}
		return false;
	}

	/**
	 * Check white symbol.
	 *
	 * @return the boolean
	 */
	public Boolean checkWhiteSymbol() {
		for (String i : getTapeAlphabet().getElements()) {
			if (i.equals(getWhite()))
				return true;
		}
		return false;
	}

	/**
	 * Check transition state belong to set of states.
	 *
	 * @param state the state
	 * @return the boolean
	 */
	public Boolean checkState(State state) {
		for (State i : getSetOfStates()) {
			if (i.getId().equals(state.getId()))
				return true;
		}
		return false;
	}

	/**
	 * Check symbol belongs input alphabet.
	 *
	 * @param element the element
	 * @return the boolean
	 */
	public Boolean checkSymbol(String element) {
		for (String i : getTapeAlphabet().getElements()) {
			if (i.equals(element))
				return true;
		}
		return false;
	}
	
	/**
	 * Check the accepted states belongs to the set of states.
	 *
	 * @return the boolean
	 */
	public Boolean checkAcceptedStates() {
		return getSetOfStates().containsAll(getAcceptedStates());
	}

	/**
	 * Check all the transitions.
	 *
	 * @return the boolean
	 */
	public Boolean checkTransitions() {
		for (Transition i : getSetOfTransitions()) {
			if (!checkState(i.getCurrentState()))
				return false;
			else if (!checkState(i.getNextState()))
				return false;
			else if (!checkSymbol(i.getEntrySymbol()))
				return false;
			else if (!checkSymbol(i.getOutputSymbol()))
				return false;
			else if (!i.getMove().equals(getRIGHT()) && !i.getMove().equals(getLEFT()) && !i.getMove().equals(getSTOP())) 
				return false;
		}
		return true;
	}

	/**
	 * Write Turing machine content.
	 */
	public void writeMachine() {
		System.out.print("Printing states Q: ");
		for (State i : getSetOfStates())
			System.out.print(i.getId() + " ");

		System.out.println();

		System.out.print("Printing input alphabet E: ");
		for (String i : getInputAlphabet().getElements())
			System.out.print(i + " ");

		System.out.println();

		System.out.print("Printing tape alphabet P: ");
		for (String i : getTapeAlphabet().getElements())
			System.out.print(i + " ");

		System.out.println();

		System.out.println("Printing initial state s: " + getInitialState().getId());

		System.out.println("Printing white symbol b: " + getWhite());

		System.out.print("Printing accepted states F: ");
		for (State i : getAcceptedStates())
			System.out.print(i.getId() + " ");

		System.out.println();

		System.out.println("Printing transitions d: ");
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

}
