package es.ull.etsii.cc.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

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
	
	private static final Integer SHOW_MACHINE = 1;
	private final static Integer SHOW_TAPE = 2;
	private final static Integer UPDATE_INPUT_TAPE = 3;
	private final static Integer COMPUTE = 4;
	private final static Integer EXIT = 5;
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			TuringMachine machine = new TuringMachine(new File(args[0]));
			
			// Read console input
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			Integer option = -1;
			
			while (option != EXIT) {
				// Simple menu system
				System.out.println();
				System.out.println("--------- Turing machine ---------");
				System.out.println("   1. Show Turing machine.");
				System.out.println("   2. Show input tape content.");
				System.out.println("   3. Update tape with a new input.");
				System.out.println("   4. Compute the tape chain.");
				System.out.println("   5. Exit.");
				System.out.print("   Choose an option: ");
				
				// Read input from console
				try {
					option = Integer.parseInt(reader.readLine());
				} catch (NumberFormatException error) {
					System.err.println("Error: bad input received, please insert a valid option.");
					option = -1;
				}
				
				System.out.println();

				// If-else statements (switch-case don't accept variables as cases in java)
				if (option == SHOW_MACHINE)
					machine.writeMachine();
				else if (option == SHOW_TAPE)
					System.out.println("Write tape function");
				else if (option == UPDATE_INPUT_TAPE)
					System.out.println("Update tape function");
				else if (option == COMPUTE)
					machine.computeInput();
				else if (option == EXIT)
					System.out.println("Shutting down..");
				// If it's not -1, means that the user insert a number but not a correct number
				else if (option != -1) 
					System.out.println("Warning: insert a valid option.");
			}
		} catch(Exception error) {
			System.err.println(error.getMessage());
			error.printStackTrace();
		}
	}

}
