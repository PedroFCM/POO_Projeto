
//------------------------------------------------------------------

package GUInterfaces;

//------------------------------------------------------------------

import static java.lang.System.out;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.lang.StringBuilder;

import java.time.LocalDate;

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
	public void printHighlight (String msg) {

		out.println(ANSI_YELLOW + msg + ANSI_RESET);
	}

	public void printCreateVehicles() {

			out.println(ANSI_GREEN + "[1] Hybrid" + ANSI_RESET);
			out.println(ANSI_GREEN + "[2] Gasoline" + ANSI_RESET);
			out.println(ANSI_GREEN + "[3] Eletric" + ANSI_RESET);

			out.println(ANSI_YELLOW + "Stay tunned, we are working on getting surf boards, scooters and MORE! ;)" + ANSI_RESET);		
	}

	public void printPreferredVehicles() {

			out.println(ANSI_GREEN + "[1] Hybrid" + ANSI_RESET);
			out.println(ANSI_GREEN + "[2] Gasoline" + ANSI_RESET);
			out.println(ANSI_GREEN + "[3] Eletric" + ANSI_RESET);
			out.println(ANSI_GREEN + "[4] I have no preference\n" + ANSI_RESET);
			out.println(ANSI_GREEN + "[5] Go back to the selection menu\n" + ANSI_RESET);

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
			out.println(ANSI_YELLOW + "[3] Rent the cheapest car, by DISTANCE;" + ANSI_RESET);
			out.println(ANSI_YELLOW + "[4] Rent the car with a certain autonomy." + ANSI_RESET);
			out.println(ANSI_YELLOW + "[5] Apresentar lista de alugueres efetuados." + ANSI_RESET);

			out.println(ANSI_GREEN + "\n[NOTE] Go back to menu? [y] | Clear error::note logs? [c]\n" + ANSI_RESET);
		
		} else {

			out.println(ANSI_GREEN + "Manage your cars, rents and more!\n" + ANSI_RESET);

			out.println(ANSI_YELLOW + "[1] List of vehicles in your database" + ANSI_RESET);
			out.println(ANSI_YELLOW + "[2] List of rentals" + ANSI_RESET);
			out.println(ANSI_YELLOW + "[3] Abastecer veiculo" + ANSI_RESET);
			out.println(ANSI_YELLOW + "[4] Inserir nova viatura." + ANSI_RESET);

			out.println(ANSI_GREEN + "\n[NOTE] Go back to menu? [y] | Clear error::note logs? [c]\n" + ANSI_RESET);


		}
	}


	public void printClientLocationBasicInfo (Double xLoc, Double yLoc) {

			out.println(ANSI_GREEN + "\nYour location from your GPS: X = " 
								   + ANSI_RESET + ANSI_RED + xLoc + ANSI_RESET 
								   + ANSI_GREEN + " Y = " + ANSI_RESET + ANSI_RED + yLoc + ANSI_RESET);
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

		printHint("\n\n\n\tPress \"l\" to log-in, \"r\" to register or \"s\" to save.");

	}

	public void printVehicleBasicInfo(String brand,
									  String plate,
									  Double vel,
									  Double precoPKm,
									  Double cons,
									  Double autoAtual,
									  Double x,
									  Double y,
									  Double dist,
									  int classi,
									  int vezesAlugado) {

	out.println(ANSI_PURPLE + "\nBrand         : " + ANSI_RESET + ANSI_CYAN + brand + ANSI_RESET);	
	out.println(ANSI_PURPLE + "License plate : " + ANSI_RESET + ANSI_CYAN + plate + ANSI_RESET);	
	out.println(ANSI_PURPLE + "Med. Speed    : " + ANSI_RESET + ANSI_CYAN + (Math.round(vel*100) / 100.0) + " km/h" + ANSI_RESET);	
	out.println(ANSI_PURPLE + "Price / Km    : " + ANSI_RESET + ANSI_CYAN + (Math.round(precoPKm*1000000) / 1000000.0) + " €" +  ANSI_RESET);	
	out.println(ANSI_PURPLE + "Cons. / Km    : " + ANSI_RESET + ANSI_CYAN + (Math.round(cons*100) / 100.0) + " litters" + ANSI_RESET);	
	out.println(ANSI_PURPLE + "Curr. Auton.  : " + ANSI_RESET + ANSI_CYAN + autoAtual + " km" + ANSI_RESET);	
	out.println(ANSI_PURPLE + "Classif.      : " + ANSI_RESET + ANSI_CYAN + classi + ANSI_RESET);	
	out.println(ANSI_WHITE + "\nThis Vehicle was rented " + ANSI_RESET + ANSI_CYAN + vezesAlugado + ANSI_RESET + ANSI_WHITE + " times." + ANSI_RESET);	
	
	out.println(ANSI_GREEN + "\nThis car is " + ANSI_RESET + ANSI_YELLOW + (Math.round(dist*100) / 100.0) + ANSI_RESET + ANSI_GREEN + " km away from you.");	
	out.println(ANSI_GREEN + "\nCar location from the GPS: X = " 
						   + ANSI_RESET + ANSI_RED + x + ANSI_RESET 
						   + ANSI_GREEN + " Y = " + ANSI_RESET + ANSI_RED + y + "\n" + ANSI_RESET);
	}

	public void printVehicleBasicInfoOWNER(String brand,
									  String plate,
									  Double vel,
									  Double precoPKm,
									  Double cons,
									  Double autoAtual,
									  Double x,
									  Double y,
									  int classi,
									  int vezesAlugado) {

	out.println(ANSI_PURPLE + "\nBrand         : " + ANSI_RESET + ANSI_CYAN + brand + ANSI_RESET);	
	out.println(ANSI_PURPLE + "License plate : " + ANSI_RESET + ANSI_CYAN + plate + ANSI_RESET);	
	out.println(ANSI_PURPLE + "Med. Speed    : " + ANSI_RESET + ANSI_CYAN + (Math.round(vel*100) / 100.0) + " km/h" + ANSI_RESET);	
	out.println(ANSI_PURPLE + "Price / Km    : " + ANSI_RESET + ANSI_CYAN + (Math.round(precoPKm*1000000) / 1000000.0) + " €" +  ANSI_RESET);	
	out.println(ANSI_PURPLE + "Cons. / Km    : " + ANSI_RESET + ANSI_CYAN + (Math.round(cons*100) / 100.0) + " litters" + ANSI_RESET);	
	out.println(ANSI_PURPLE + "Curr. Auton.  : " + ANSI_RESET + ANSI_CYAN + autoAtual + " km" + ANSI_RESET);	
	out.println(ANSI_PURPLE + "Classif.      : " + ANSI_RESET + ANSI_CYAN + classi + ANSI_RESET);	
	out.println(ANSI_WHITE + "\nThis Vehicle was rented " + ANSI_RESET + ANSI_CYAN + vezesAlugado + ANSI_RESET + ANSI_WHITE + " times." + ANSI_RESET);	

	out.println(ANSI_GREEN + "\nCar location from the GPS: X = " 
						   + ANSI_RESET + ANSI_RED + x + ANSI_RESET 
						   + ANSI_GREEN + " Y = " + ANSI_RESET + ANSI_RED + y + "\n" + ANSI_RESET);
	}

	public void printFaturaAlguer (String cli_nif,
								   String prop,
								   String matric,
								   Double precAlug,
								   Double dist,
								   LocalDate data) {

		System.out.println(ANSI_YELLOW + "\n\n.-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.\n" + ANSI_RESET);		out.println(ANSI_RED + "\n");	
	

		out.println(ANSI_RED + "Fatura da viagem do dia " + ANSI_RESET + ANSI_BLUE + data + ANSI_RESET + ANSI_RED + "." + ANSI_RESET);	

		out.println(ANSI_PURPLE + "\nNIF              : " + ANSI_RESET + ANSI_CYAN + cli_nif + ANSI_RESET);	
		out.println(ANSI_PURPLE + "Owner            : " + ANSI_RESET + ANSI_CYAN + prop + ANSI_RESET);	
		out.println(ANSI_PURPLE + "Vehicle Plate    : " + ANSI_RESET + ANSI_CYAN + matric + ANSI_RESET);	
		out.println(ANSI_PURPLE + "Price            : " + ANSI_RESET + ANSI_CYAN + (Math.round(precAlug*100) / 100.0) + " €" +  ANSI_RESET);	
		out.println(ANSI_PURPLE + "Total distance   : " + ANSI_RESET + ANSI_CYAN + (Math.round(dist*100) / 100.0) + " km" + ANSI_RESET);	
		out.println(ANSI_PURPLE + "\n\nProcessado por computador, Rent a car now ©" + ANSI_RESET);	
	

		System.out.println(ANSI_YELLOW + "\n\n.-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.\n" + ANSI_RESET);
	}
}