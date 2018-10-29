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
	
	public final static  Integer SHOW_MACHINE = 1;
	private final static Integer SHOW_TAPE = 2;
	private final static Integer COMPUTE = 3;
	private final static Integer EXIT = 4;
	
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
				
				// TODO: show menu in console and read from console the option selected.
				// Simple menu system
				System.out.println("--------- Turing machine ---------");
				System.out.println("   1. Mostrar Maquina de Turing.");
				System.out.println("   2. Mostrar cinta de la Maquina.");
				System.out.println("   3. Analizar cadena de la cinta.");
				System.out.println("   4. Salir.");
				System.out.print("   Elige una opcion: ");
				// Read input from console
				option = Integer.parseInt(reader.readLine());
								
				// If-else statements (switch-case don't accept constants as cases)
				if (option == SHOW_MACHINE)
					machine.writeMachine();
				else if (option == SHOW_TAPE)
					machine.writeMachine();
				else if (option == COMPUTE)
					machine.computeInput();
				else if (option == EXIT)
					System.out.println("Shutting down..");
				else
					throw new Exception("invalid option inserted.");	
				}
			
		} catch(Exception error) {
			System.err.println(error.getMessage());
			error.printStackTrace();
		}
	}

}
