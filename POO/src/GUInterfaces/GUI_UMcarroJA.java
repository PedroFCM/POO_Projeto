
//------------------------------------------------------------------

package GUInterfaces;

//------------------------------------------------------------------

import static java.lang.System.out;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.lang.StringBuilder;

import EstadoSistema.*;

//------------------------------------------------------------------

public class GUI_UMcarroJA {  

	//------------------------------------------------------------------

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
                                                                                         
	//------------------------------------------------------------------
	
    public void cat (String file_path) {

	    try {

	    	BufferedReader input = new BufferedReader(new FileReader(file_path));
	        
	        String x = null;  
	        
	        while( (x = input.readLine()) != null ) {    
	            System.out.println(x); 
	        }    
	    }	

    	catch (Exception e) {}
	}

	public void clearConsole() {

		System.out.print("\033[H\033[2J");
	}

	public void printError(String erroMsg) {

		out.println(ANSI_RED + "[ERROR] " + erroMsg + ANSI_RESET);
	}

	public void printHint(String erroMsg) {

		out.println(ANSI_GREEN + erroMsg + ANSI_RESET);
	}

	public void printPreferredVehicles() {

			out.println(ANSI_GREEN + "[1] Hybrid" + ANSI_RESET);
			out.println(ANSI_GREEN + "[2] Gasoline" + ANSI_RESET);
			out.println(ANSI_GREEN + "[3] Eletric" + ANSI_RESET);
			out.println(ANSI_GREEN + "[4] I have no preference\n" + ANSI_RESET);

			out.println(ANSI_YELLOW + "Stay tunned, we are working on getting surf boards, scooters and MORE! ;)" + ANSI_RESET);
	}

	public void printUserDetails (String user_name,
		                          String user_morada,
		                          String user_email,
		                          int user_class,
		                          String user_nif,
		                          String tipoUser) {

		out.println(ANSI_YELLOW + "\n\n\tHello " + user_name + ", welcome back!\n\n" + ANSI_RESET);
		
		out.println(ANSI_BLACK + "^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^\n" + ANSI_RESET);
		out.println(ANSI_CYAN + " Profile details: (" + tipoUser + ")\n" + ANSI_RESET);

		out.println(ANSI_GREEN + "\tE-mail   : " + ANSI_RESET + ANSI_YELLOW + user_email + ANSI_RESET);
		out.println(ANSI_GREEN + "\tNif      : " + ANSI_RESET + ANSI_YELLOW + user_nif + ANSI_RESET);
		out.println(ANSI_GREEN + "\tMorada   : " + ANSI_RESET + ANSI_YELLOW + user_morada + ANSI_RESET);
		out.println(ANSI_GREEN + "\tClassif. : " + ANSI_RESET + ANSI_YELLOW + user_class + ANSI_RESET);

		out.println();

		out.println(ANSI_BLACK + "^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^\n\n" + ANSI_RESET);

		if (tipoUser.equals("Cliente")) {

			out.println(ANSI_GREEN + "Rent a car, available options: \n" + ANSI_RESET);

			out.println(ANSI_YELLOW + "[1] Rent the closest car;" + ANSI_RESET);
			out.println(ANSI_YELLOW + "[2] Rent the cheapest car;" + ANSI_RESET);
			out.println(ANSI_YELLOW + "[3] Rent the car with a certain autonomy." + ANSI_RESET);

			out.println(ANSI_GREEN + "\n[NOTE] Go back to menu? [y] | Clear error::note logs? [c]\n" + ANSI_RESET);
		}
	}

	//------------------------------------------------------------------

	public void presentation () {

	    System.out.println(ANSI_YELLOW + "\n\n.-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.\n" + ANSI_RESET);
		
		out.println(ANSI_WHITE + "\n\t\t   UMCarroJÁ - Rent a car NOW!\n" + ANSI_RESET);

		out.println(ANSI_YELLOW + "\n\t\t   Press any key to continue...\n" + ANSI_RESET);

		try {

	    	System.in.read();
			
			clearConsole();
		}

		catch (Exception e) {

			out.println("Something went wrong, restart the app please...");
		}

	}

	//------------------------------------------------------------------

	public void mainMenu() {

		cat("GUInterfaces/title.txt");

		printHint("\n\n  Press \"l\" to log-in or \"r\" to register.");

	}

}