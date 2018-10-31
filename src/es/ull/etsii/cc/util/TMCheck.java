package es.ull.etsii.cc.util;

import java.util.List;

import es.ull.etsii.cc.components.Alphabet;
import es.ull.etsii.cc.components.State;
import es.ull.etsii.cc.components.Transition;
import es.ull.etsii.cc.machine.TuringMachine;

/**
 * @author Abel Delgado Falcón (<a href="mailto:alu0100792218@ull.edu.es">)
 * @version 1.0
 * @date 31 oct. 2018
 * 
 *       Support class for check some Turing machine features.
 *
 */
public final class TMCheck {

	/**
	 * Check initial state.
	 *
	 * @return the boolean
	 */
	public static Boolean checkInitialState(List<State> setOfStates, State initialState) {
		for (State i : setOfStates) {
			if (i.getId().equals(initialState.getId()))
				return true;
		}
		return false;
	}

	/**
	 * Check white symbol.
	 *
	 * @return the boolean
	 */
	public static Boolean checkWhiteSymbol(Alphabet tapeAlphabet, String white) {
		for (String i : tapeAlphabet.getElements()) {
			if (i.equals(white))
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
	public static Boolean checkState(List<State> setOfStates, State state) {
		for (State i : setOfStates) {
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
	public static Boolean checkSymbol(Alphabet tapeAlphabet, String element) {
		for (String i : tapeAlphabet.getElements()) {
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
	public static Boolean checkAcceptedStates(List<State> acceptedStates, List<State> setOfStates) {
		return setOfStates.containsAll(acceptedStates);
	}

	/**
	 * Check all the transitions.
	 *
	 * @return the boolean
	 */
	public static Boolean checkTransitions(TuringMachine machine) {
		for (Transition i : machine.getSetOfTransitions()) {
			if (!checkState(machine.getSetOfStates(), i.getCurrentState()))
				return false;
			else if (!checkState(machine.getSetOfStates(), i.getNextState()))
				return false;
			else if (!checkSymbol(machine.getTapeAlphabet(), i.getEntrySymbol()))
				return false;
			else if (!checkSymbol(machine.getTapeAlphabet(), i.getOutputSymbol()))
				return false;
			else if (!i.getMove().equals(machine.getRIGHT()) && !i.getMove().equals(machine.getLEFT())
					&& !i.getMove().equals(machine.getSTOP()))
				return false;
		}
		return true;
	}
}
