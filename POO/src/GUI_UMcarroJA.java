//-------------------------------------------------------------------------------------------------------------

/**
 * Class que implementa a GUI do projeto.
 * Apresenta diversos metodos para apresentar diferentes informações aos utilizadores
 * desta APP.
 *
 * @author João Pedro Rodrigues Azevedo
 * @author Pedro Filipe Costa Machado
 * @author Paulo Jorge da Silva Araújo 
 *
 * @version 2019/05/25
 */

//-------------------------------------------------------------------------------------------------------------

import static java.lang.System.out;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.StringBuilder;
import java.time.LocalDate;

//-------------------------------------------------------------------------------------------------------------

public class GUI_UMcarroJA {  


	//-------------------------------------------------------------------------------------------------------------

	/*
	* Variaveis de classe de GUI_UMcarroJA
	* Apresenta-se diversas MACROS para apresentar cores no terminal Linux
	*/

	//-------------------------------------------------------------------------------------------------------------

	/*
	* Cores de fonte de texto Ascii Ansi
	*/

	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_BLACK = "\u001B[30m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String ANSI_YELLOW = "\u001B[33m";
	private static final String ANSI_BLUE = "\u001B[34m";
	private static final String ANSI_PURPLE = "\u001B[35m";
	private static final String ANSI_CYAN = "\u001B[36m";
	private static final String ANSI_WHITE = "\u001B[37m";

	//-------------------------------------------------------------------------------------------------------------

	/*
	* Cores de background de texto Ascii Ansi
	*/

	private static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	private static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	private static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	private static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	private static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	private static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
                                                                                         
	//-------------------------------------------------------------------------------------------------------------

    /**
	 * Apresenta no terminal o conteudo do ficheiro argumento.
	 * Semelhante à call "cat" da bash
	 * Trata da exceção na própria função.
	 *
	 * @param file_path Path para o ficheiro.
	 *
	*/

    public void cat (String file_path) {

	    try {

	    	BufferedReader input = new BufferedReader(new FileReader(file_path));
	        
	        String x = null;  
	        
	        while( (x = input.readLine()) != null ) {    
	            System.out.println(x); 
	        }    
	    }	

    	catch (Exception e) {

    		System.out.println(e.getMessage());
    	}
	}

    /**
	 * Faz clear ao terminal. Semelhante a call na bash "clear".
	 *
	*/

	public void clearConsole() {

		System.out.print("\033[H\033[2J");
	}

    /**
	 * Função interativa que apresenta um erro no terminal.
	 * O erro, a string, é mandada como parametro.
	 *
	 * @param erroMsg mensagem de erro.
	 *
	*/

	public void printError(String erroMsg) {

		out.println(ANSI_RED + "[ERROR] " + erroMsg + ANSI_RESET);
	}

   /**	
	 * Apresenta uma mensagem a verde ao utilizador.
	 *
	 * @param hint mensagem.
	 *
	*/

	public void printHint(String hint) {

		out.println(ANSI_GREEN + hint + ANSI_RESET);
	}

   /**	
	 * Apresenta uma mensagem amarela ao utilizador.
	 *
	 * @param msg mensagem.
	 *
	*/

	public void printHighlight (String msg) {

		out.println(ANSI_YELLOW + msg + ANSI_RESET);
	}

   /**	
	 * Apresenta uma mensagem de warning ao utilizador.
	 *
	 * @param msg mensagem.
	 *
	*/

	public void printWarning (String msg) {

		out.println("Warning: " + ANSI_RED + msg + ANSI_RESET);
	}

   /**	
	 * Apresenta o menu de escolha do tipo de veiculo para o proprietario.
	 *
	*/

	public void printCreateVehicles() {

			out.println(ANSI_GREEN + "[1] Hybrid" + ANSI_RESET);
			out.println(ANSI_GREEN + "[2] Gasoline" + ANSI_RESET);
			out.println(ANSI_GREEN + "[3] Eletric" + ANSI_RESET);

			out.println(ANSI_YELLOW + "Stay tunned, we are working on getting surf boards, scooters and MORE! ;)" + ANSI_RESET);		
	}

   /**	
	 * Apresenta o menu de escolha do tipo de veiculo para o cliente.
	 *
	*/

	public void printPreferredVehicles() {

			out.println(ANSI_GREEN + "[1] Hybrid" + ANSI_RESET);
			out.println(ANSI_GREEN + "[2] Gasoline" + ANSI_RESET);
			out.println(ANSI_GREEN + "[3] Eletric" + ANSI_RESET);
			out.println(ANSI_GREEN + "[4] I have no preference\n" + ANSI_RESET);
			out.println(ANSI_GREEN + "[5] Go back to the selection menu\n" + ANSI_RESET);

			out.println(ANSI_YELLOW + "Stay tunned, we are working on getting surf boards, scooters and MORE! ;)" + ANSI_RESET);
	}

   /**	
	 * Apresenta os detalhes do utilizador passado o seu tipo de dados como parametro.
	 * 
	 * @param user_nome Nome do utilizador
	 * @param user_morada Morada do utilizador
	 * @param user_email Email do utilizador
	 * @param user_class Classificacao do utilizador
	 * @param user_nif Nif do utilizador
	 * @param tipoUser "Cliente" ou "Proprietario"
	 *
	*/

	public void printUserDetails (String user_name,
		                          String user_morada,
		                          String user_email,
		                          int user_class,
		                          String user_nif,
		                          String tipoUser) {

		out.println(ANSI_YELLOW + "\n\n\tHello " + user_name + ", welcome back!\n\n" + ANSI_RESET);
		
		out.println(ANSI_BLACK + "^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^\n" + ANSI_RESET);
		out.println(ANSI_CYAN + " Profile details: (" + tipoUser + ")\n" + ANSI_RESET);

		out.println(ANSI_GREEN + "\tE-mail     : " + ANSI_RESET + ANSI_YELLOW + user_email + ANSI_RESET);
		out.println(ANSI_GREEN + "\tNif        : " + ANSI_RESET + ANSI_YELLOW + user_nif + ANSI_RESET);
		out.println(ANSI_GREEN + "\tMorada     : " + ANSI_RESET + ANSI_YELLOW + user_morada + ANSI_RESET);
		
		if (tipoUser.equals("Cliente")) {
	
			out.println(ANSI_GREEN + "\tExperience : " + ANSI_RESET + ANSI_CYAN + classifica(user_class) + ANSI_RESET + ANSI_GREEN + " (Class: " + ANSI_RESET + ANSI_CYAN + user_class + ANSI_RESET + ANSI_GREEN + ")" + ANSI_RESET);	
		}

		out.println();

		out.println(ANSI_BLACK + "^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^-^\n\n" + ANSI_RESET);

		if (tipoUser.equals("Cliente")) {

			out.println(ANSI_GREEN + "Rent a car, available options: \n" + ANSI_RESET);

			out.println(ANSI_YELLOW + "[1] Rent the closest car;" + ANSI_RESET);
			out.println(ANSI_YELLOW + "[2] Rent the cheapest car;" + ANSI_RESET);
			out.println(ANSI_YELLOW + "[3] Rent the cheapest car, by DISTANCE;" + ANSI_RESET);
			out.println(ANSI_YELLOW + "[4] Rent the car with a certain autonomy." + ANSI_RESET);
			out.println(ANSI_YELLOW + "[5] Apresentar lista de alugueres efetuados." + ANSI_RESET);
			out.println(ANSI_YELLOW + "[6] Classificar viagem." + ANSI_RESET);

			out.println(ANSI_GREEN + "\n[NOTE] Go back to menu? [y] | Clear error::note logs? [c]\n" + ANSI_RESET);
		
		} else {

			out.println(ANSI_GREEN + "Manage your cars, rents and more!\n" + ANSI_RESET);

			out.println(ANSI_YELLOW + "[1] List of vehicles in your database" + ANSI_RESET);
			out.println(ANSI_YELLOW + "[2] List of rentals" + ANSI_RESET);
			out.println(ANSI_YELLOW + "[3] Abastecer veiculo" + ANSI_RESET);
			out.println(ANSI_YELLOW + "[4] Inserir nova viatura." + ANSI_RESET);
			out.println(ANSI_YELLOW + "[5] Ver/Aceitar pedidos de aluguer." + ANSI_RESET);
			out.println(ANSI_YELLOW + "[6] Faturacao da viatura num periodo." + ANSI_RESET);

			out.println(ANSI_GREEN + "\n[NOTE] Go back to menu? [y] | Clear error::note logs? [c]\n" + ANSI_RESET);


		}
	}

   /**	
	 * Apresenta a localização de um cliente pelo GPS (Coordenadas enviadas como parametro).
	 * 
	 * @param xLoc Localizacao coordenada X
	 * @param yLoc Localizacao coordenada Y
	 *
	*/

	public void printClientLocationBasicInfo (Double xLoc, Double yLoc) {

			out.println(ANSI_GREEN + "\nYour location from your GPS: X = " 
								   + ANSI_RESET + ANSI_RED + xLoc + ANSI_RESET 
								   + ANSI_GREEN + " Y = " + ANSI_RESET + ANSI_RED + yLoc + ANSI_RESET);
	}

   /**	
	 * Apresenta os detalhes do utilizador passado o seu tipo de dados como parametro.
	 * 
	*/

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

    /**	
	 * Apresenta o menu inicial ao user.
	 * Estes podem escolher entre Login, Register, Admin, Save ou Quit
	 * 
	*/

	public void mainMenu() {

		cat("GUInterfaces/title.txt");

		cat("GUInterfaces/lowerFrame.txt");

		System.out.println(ANSI_CYAN + "\n     Press " + ANSI_RESET + ANSI_GREEN + "\"l\" " + ANSI_RESET + ANSI_CYAN + "to log-in, " + ANSI_RESET + ANSI_GREEN + "\"r\" " + ANSI_RESET  + ANSI_CYAN + "to register, " + ANSI_RESET + ANSI_GREEN + "\"s\"" + ANSI_RESET + ANSI_CYAN + " to save or "+ ANSI_RESET + ANSI_GREEN + "\"q\"" + ANSI_RESET  + ANSI_CYAN  + " to quit." + ANSI_RESET);

		System.out.println(ANSI_CYAN + "\n     Press " + ANSI_RESET + ANSI_GREEN + "\"a\" " + ANSI_RESET + ANSI_CYAN + "to acess admin menu." + ANSI_RESET);

		cat("GUInterfaces/lowerFrame.txt");
	}

    /**	
	 * Apresenta as carateristicas do veiculo, as suas propriedades para o Cliente.
	 * 
	 * @param brand Marca do veiculo.
	 * @param plate Matricula do veiculo.
	 * @param vel Velocidade media do veiculo.
	 * @param precoPKm Preco por km do veiculo.
	 * @param cons Consumo do veiculo.
	 * @param autoAtual Autonomia atual.
	 * @param x localizacao na coordenada X.
	 * @param y localizacao na coordenada Y.	 
	 * @param dist Distancia do veiculo das coordenadas x y.
	 * @param classi Classificacao do veiculo.
	 * @param vezesAlugado Numero de vezes que o veiculo foi alugado.
	 *
	*/

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

    /**	
	 * Apresenta as carateristicas do veiculo, as suas propriedades para o Proprietario.
	 * 
	 * @param brand Marca do veiculo.
	 * @param plate Matricula do veiculo.
	 * @param vel Velocidade media do veiculo.
	 * @param precoPKm Preco por km do veiculo.
	 * @param cons Consumo do veiculo.
	 * @param autoAtual Autonomia atual.
	 * @param x localizacao na coordenada X.
	 * @param y localizacao na coordenada Y.	 
	 * @param classi Classificacao do veiculo.
	 * @param vezesAlugado Numero de vezes que o veiculo foi alugado.
	 *
	*/

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

    /**	
	 * Apresenta as carateristicas do Aluguer.
	 * 
	 * @param cli_nif Nif do cliente.
	 * @param prop Nif do proprietario.
	 * @param matric Matricula do veiculo.
	 * @param precoAlug Preco do aluguer.
	 * @param dist Distancia da viagem.
	 * @param data Data de aluguer.
	 * @param i Numero da fatura.
	 * @param classificacao Classificacao do cliente.	 
	 *
	*/

	public void printFaturaAlguer (String cli_nif,
								   String prop,
								   String matric,
								   Double precAlug,
								   Double dist,
								   LocalDate data,
								   int i,
								   int classificacao) {

		System.out.println(ANSI_YELLOW + "\n\n.-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.\n" + ANSI_RESET);		out.println(ANSI_RED + "\n");	
	
		if (i != -1) {
			out.println(ANSI_RED + "Fatura " + i + " da viagem do dia " + ANSI_RESET + ANSI_BLUE + data + ANSI_RESET + ANSI_RED + "." + ANSI_RESET);	
		} else {			
			out.println(ANSI_RED + "Fatura da viagem do dia " + ANSI_RESET + ANSI_BLUE + data + ANSI_RESET + ANSI_RED + "." + ANSI_RESET);	
		}
		
		out.println(ANSI_PURPLE + "\nNIF               : " + ANSI_RESET + ANSI_CYAN + cli_nif + ANSI_RESET);	
		out.println(ANSI_PURPLE + "Owner             : " + ANSI_RESET + ANSI_CYAN + prop + ANSI_RESET);	
		out.println(ANSI_PURPLE + "Vehicle Plate     : " + ANSI_RESET + ANSI_CYAN + matric + ANSI_RESET);	
		out.println(ANSI_PURPLE + "Price             : " + ANSI_RESET + ANSI_CYAN + (Math.round(precAlug*100) / 100.0) + " €" +  ANSI_RESET);	
		out.println(ANSI_PURPLE + "Total distance    : " + ANSI_RESET + ANSI_CYAN + (Math.round(dist*100) / 100.0) + " km" + ANSI_RESET);	
		out.println(ANSI_PURPLE + "Driver Experience : " + ANSI_RESET + ANSI_CYAN + classifica(classificacao) + ANSI_RESET + ANSI_PURPLE + " (Class: " + ANSI_RESET + ANSI_CYAN + classificacao + ANSI_RESET + ANSI_PURPLE + ")" + ANSI_RESET);	
		out.println(ANSI_PURPLE + "\n\nProcessado por computador, Rent a car now ©" + ANSI_RESET);	
	

		System.out.println(ANSI_YELLOW + "\n\n.-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.\n" + ANSI_RESET);
	}

    /**	
	 * Converte a classificacao do cliente para a classificacao em String.
	 * 
	 * @param classificacao Classificacao do cliente.
	 *
	 * @return Experiencia do cliente.
	 *
	*/

	public String classifica (int classificacao) {

		if (classificacao >= 0 && classificacao < 40) {

			return "Novato";
		} else if (classificacao >= 40 && classificacao < 70) {

			return "Cuidadoso";
		} else if (classificacao >= 70 && classificacao < 100) {

			return "Experiente";
		} else if (classificacao == 100) {

			return "Piloto de Formula 1";
		}

		return "Sem experiencia";
	}

    /**	
	 * Apresenta um posição no ranking de users.
	 * 
	 * @param nome Nome do user.
	 * @param totalKM distancia percorrida pelo user.
	 * @param rank Rank do cliente.
	 *
	 *
	*/

	public void printIndividual (String nome, Double totalKM, int rank) {

		if (rank < 10)
			out.println(ANSI_PURPLE + "[#  " + rank + "] Name: " + ANSI_RESET + ANSI_CYAN + nome + ANSI_RESET + ANSI_YELLOW + " traveled " + ANSI_RESET + ANSI_RED + (Math.round(totalKM*1000) / 1000.0) + ANSI_RESET + ANSI_CYAN + " km" + ANSI_RESET);	
		else 
			out.println(ANSI_PURPLE + "[# " + rank + "] Name: " + ANSI_RESET + ANSI_CYAN + nome + ANSI_RESET + ANSI_YELLOW + " traveled " + ANSI_RESET + ANSI_RED + (Math.round(totalKM*1000) / 1000.0) + ANSI_RESET + ANSI_CYAN + " km" + ANSI_RESET);	
	}

    /**	
	 * Apresenta a faturacao de um veiculo.
	 * 
	 * @param matr Matricula do veiculo.
	 * @param marca Marca do veiculo.
	 * @param fat Quantidade faturada pelo veiculo.
	 *
	 *
	*/

	public void printFaturacaoVeiculo (String matr, String marca, double fat) {

		out.println(ANSI_PURPLE + "A sua viatura  " + ANSI_RESET
				  + ANSI_RED + marca + ANSI_RESET + ANSI_PURPLE + " com matricula " + ANSI_RESET 
				  + ANSI_RED + matr + ANSI_RESET + ANSI_PURPLE + " rendeu " + ANSI_RESET 
			      + ANSI_CYAN + (Math.round(fat*1000) / 1000.0) + " €" +  ANSI_RESET);	
	}
}