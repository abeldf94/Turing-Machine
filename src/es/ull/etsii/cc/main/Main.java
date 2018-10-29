package es.ull.etsii.cc.main;

import java.io.File;

import es.ull.etsii.cc.machine.TuringMachine;

/**
 * @author Abel Delgado Falcón (<a href="mailto:alu0100792218@ull.edu.es">)
 * @version 1.0 
 * @date 28 oct. 2018
 * 
 * Class used for separate the main method from the program.
 *
 */
public class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			TuringMachine machine = new TuringMachine(new File(args[0]));
			machine.computeInput();
		} catch(Exception error) {
			System.err.println(error.getMessage());
			error.printStackTrace();
		}
	}

}
