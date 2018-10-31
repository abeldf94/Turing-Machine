package es.ull.etsii.cc.components;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import es.ull.etsii.cc.machine.TuringMachine;
import es.ull.etsii.cc.util.TMCheck;

/**
 * @author Abel Delgado Falcón (<a href="mailto:alu0100792218@ull.edu.es">)
 * @version 1.0
 * @date 28 oct. 2018
 * 
 *       Read from file the content that describes a Turing Machine
 *
 */
public class TMFileLoader {

	/** The machine. */
	private TuringMachine machine;

	/** The reader. */
	private BufferedReader reader;

	/** The line. */
	private String line;

	/** The tokens. */
	private String[] tokens;

	/** The white spaces. */
	private final String WHITE_SPACES = "\\s+";

	/** The comments delimiter. */
	private final String COMMENTS_DELIMiTER = "#";

	/** The skiped top. */
	private Boolean skipedTop;

	/**
	 * Instantiates a new TM file loader.
	 *
	 * @param machine the machine
	 * @param file the file
	 * @throws FileNotFoundException the file not found exception
	 */
	public TMFileLoader(TuringMachine machine, File file) throws FileNotFoundException {
		this.machine = machine;
		reader = new BufferedReader(new FileReader(file));
		skipedTop = false;
	}

	/**
	 * Load the Turing machine from file.
	 * 
	 * @throws Exception
	 */
	public void read() throws Exception {
		try {
			skipTopComments();
			skipedTop = true;
			readStates();
			readInputAlphabet();
			readTapeAlphabet();
			readInitialState();
			readWhiteSymbol();
			readAcceptedStates();
			readTransitions();
		} catch (Exception error) {
			throw error;
		} finally {
			reader.close();
		}

	}

	/**
	 * Read a line from file.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void readLine() throws IOException {
		line = reader.readLine();
		if (skipedTop)
			splitLineAndIgnoreComments();
		else
			splitBySpaces();
	}

	private void splitBySpaces() {
		tokens = line.split(WHITE_SPACES);
	}

	/**
	 * Split line by white spaces and ignore comments in the line.
	 */
	private void splitLineAndIgnoreComments() {
		tokens = line.split(COMMENTS_DELIMiTER)[0].split(WHITE_SPACES);
	}

	/**
	 * Skip top file comments.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void skipTopComments() throws IOException {
		readLine();

		while (tokens[0].equals(COMMENTS_DELIMiTER)) {
			readLine();
		}
	}

	/**
	 * Read states from file.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void readStates() throws IOException {
		splitLineAndIgnoreComments();

		for (String i : tokens) {
			State state = new State(i);
			machine.getSetOfStates().add(state);
		}
	}

	/**
	 * Read input alphabet from file.
	 * 
	 * @throws IOException
	 */
	private void readInputAlphabet() throws IOException {
		readLine();

		for (String i : tokens)
			machine.getInputAlphabet().addElement(i);
	}
	
	/**
	 * Read tape alphabet from file.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void readTapeAlphabet() throws IOException {
		readLine();

		for (String i : tokens)
			machine.getTapeAlphabet().addElement(i);
	}

	/**
	 * Read initial state from file.
	 *
	 * @throws Exception the exception
	 */
	private void readInitialState() throws Exception {
		readLine();
		machine.setInitialState(new State(tokens[0]));

		if (!TMCheck.checkInitialState(machine.getSetOfStates(), machine.getInitialState()))
			throw new Exception("initial state not belongs to the set of states.");
	}

	/**
	 * Read white symbol from file.
	 *
	 * @throws Exception the exception
	 */
	private void readWhiteSymbol() throws Exception {
		readLine();
		machine.setWhite(tokens[0]);

		if (!TMCheck.checkWhiteSymbol(machine.getTapeAlphabet(), machine.getWhite()))
			throw new Exception("white symbol not belongs to the tape alphabet.");
	}

	/**
	 * Read accepted states from file.
	 * 
	 * @throws Exception
	 */
	private void readAcceptedStates() throws Exception {
		readLine();

		for (String i : tokens)
			machine.getAcceptedStates().add(new State(i));

		if (!TMCheck.checkAcceptedStates(machine.getAcceptedStates(), machine.getSetOfStates()))
			throw new Exception("set of accepted states is incorrectly.");

	}

	/**
	 * Read transitions from file.
	 *
	 * @throws Exception the exception
	 */
	private void readTransitions() throws Exception {
		// Read transitions and create it
		while ((line = reader.readLine()) != null) {
			splitLineAndIgnoreComments();

			// Current state, entry symbol, next state, output symbol and movement
			Transition transition = new Transition(new State(tokens[0]), tokens[1], new State(tokens[2]), tokens[3],
					tokens[4]);
			machine.getSetOfTransitions().add(transition);
		}

		if (!TMCheck.checkTransitions(machine))
			throw new Exception("transitions are incorrectly.");
	}
}
