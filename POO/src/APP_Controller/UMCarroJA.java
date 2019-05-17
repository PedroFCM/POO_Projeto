
//--------------------------------------------------

package APP_Controller;

//--------------------------------------------------

import GUInterfaces.*;
import EstadoSistema.*;
import GestaoFicheiros.GestorFicheirosDados;
import EstadoSistema.ExceptionsProgramFlow.*;

import ComponentesSistema.Veiculos.*;
import ComponentesSistema.AtorSistema.*;

import BaseClasses.Localizacao;
import BaseClasses.Aluguer;

//--------------------------------------------------

import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;

//--------------------------------------------------

public class UMCarroJA {

	//--------------------------------------------------

	private final static String pathLogs = "Logs/log_simples.bak";

	//--------------------------------------------------

	private EstadoSistema estadoMODEL;
	private GUI_UMcarroJA menusVIEW;

	private String usernameLogged;
	private boolean isProprietario;
	private String currentAlugerPref;

	private AtorSistema user;

	//--------------------------------------------------

    public UMCarroJA (EstadoSistema estadoM, GUI_UMcarroJA viewModel) {

    	this.estadoMODEL = new EstadoSistema(estadoM);
    	this.menusVIEW   = viewModel;
    }

	//--------------------------------------------------

    public void initLogs () throws IOException, FileNotFoundException {

    	GestorFicheirosDados fileManager = new GestorFicheirosDados();
    		
    	fileManager.carregarEstadoFromLogs(this.estadoMODEL, 
    									   pathLogs);
           	    		    		
    	//fileManager.stats_iniciais(this.estadoMODEL);
    }

    public void run () {

	   	Scanner scanner = new Scanner(System.in);

		//--------------------------------------------------

	    this.menusVIEW.mainMenu();

	    boolean loginSuccess = controllLogIn();

		//--------------------------------------------------

	    welcomeMenu();
   	}

   	public void cheapestCarMenu() {

   		Scanner scan = new Scanner(System.in);

   		this.menusVIEW.printPreferredVehicles();

   		String optionCliente = "s", preferencia = "CarroRandom";

   		while (optionCliente.charAt(0) != 'q') {

   			optionCliente = scan.next();

   			if (optionCliente.length() == 1) {

				char inp = optionCliente.charAt(0);

				if (optionCliente.charAt(0) == '1') {

					this.currentAlugerPref = "CarroHibrido";
					optionCliente = "q";

				} else if (optionCliente.charAt(0) == '2') {

					this.currentAlugerPref = "CarroGasolina";
					optionCliente = "q";
				
				} else if (optionCliente.charAt(0) == '3') {

					this.currentAlugerPref = "CarroEletrico";
					optionCliente = "q";

				} else if (optionCliente.charAt(0) == '4') {

					this.currentAlugerPref = "SemPreferencia";
					optionCliente = "q";
				} else
					this.menusVIEW.printError("Option unavailable.");

			} else 
				this.menusVIEW.printError("Insert only 1 haracter, please.");
   		}
   	}

   	public void clientMenuSelection(char optionSelected) {

   		switch(optionSelected) {

   			//Carro mais perto
   			case '1':
				
				cheapestCarMenu();

				break;

   			case '2':
				

				break;

   			case '3':
				

				break;
   		}
   	} 

   	public void welcomeMenu() {

   		this.menusVIEW.clearConsole();
		this.menusVIEW.cat("GUInterfaces/welcomeUser.txt");

		this.menusVIEW.printUserDetails(user.getNome(),
										user.getMorada(),
										user.getEmail(),
										user.getClassificacao(),
										user.getNif(),
										user.getClass().getSimpleName());
   		
		Scanner scan = new Scanner(System.in);

		if (user.getClass().getSimpleName().equals("Cliente")) {

			String optionCliente = "s";

			while (optionCliente.charAt(0) != 'q') {
	
				optionCliente = scan.next();

				if (optionCliente.length() == 1) {

					char inp = optionCliente.charAt(0);

					if (optionCliente.charAt(0) == '1' ||
						optionCliente.charAt(0) == '2' ||
						optionCliente.charAt(0) == '3') {

						clientMenuSelection(optionCliente.charAt(0));

				   		this.menusVIEW.clearConsole();
						this.menusVIEW.cat("GUInterfaces/welcomeUser.txt");

						this.menusVIEW.printUserDetails(user.getNome(),
														user.getMorada(),
														user.getEmail(),
														user.getClassificacao(),
														user.getNif(),
														user.getClass().getSimpleName());
				 
						this.menusVIEW.printHint("There's this car available: \n");

						try {

							System.out.println(this.user);

							System.out.println(this.estadoMODEL
							.carroMaisProximo((Cliente) this.user, this.currentAlugerPref));

						}
						
						catch (CarNotAvailableException e) {

							this.menusVIEW.printError("There's no car available now.");
						}

					} else if (optionCliente.charAt(0) == 'y') {

						this.menusVIEW.clearConsole();

						run();
					
					} else if (optionCliente.charAt(0) == 'c') {

						welcomeMenu();

					} else {

						this.menusVIEW.printError("Option unavailable.");
					}

				} else {

					this.menusVIEW.printError("Insert only 1 haracter, please.");
				}

			}


		}
   	}

   	public boolean controllLogIn () {

		//--------------------------------------------------

	   	Scanner scanner = new Scanner(System.in);

		boolean logFinished = false;

		boolean clearScreen = false;

		//--------------------------------------------------

		try {

			char optionLogMenu = scanner.next().charAt(0);

			//Press l to login or r to register
			while (optionLogMenu != 'r' && optionLogMenu != 'l') {

				this.menusVIEW.printError("Please, insert a valid option...");				

				optionLogMenu = scanner.next().charAt(0);
			}

			GestorFicheirosDados gestor = new GestorFicheirosDados();

			if (optionLogMenu == 'r' && logFinished == false) {

				this.menusVIEW.clearConsole();
				this.menusVIEW.cat("GUInterfaces/register.txt");

				System.out.println("\nAre you an \"Owner\" or \"Client?\" (Press: 1 -> Owner | 2 -> Client)");

				char optionRegister = scanner.next().charAt(0);

				while (optionRegister != '1' && optionRegister != '2') {

					this.menusVIEW.printError("Please insert a valid option...");

					optionRegister = scanner.next().charAt(0);
				}

				this.menusVIEW.clearConsole();
				this.menusVIEW.cat("GUInterfaces/register.txt");
				
				String nomeAtor, nif, email, morada;

				if (optionRegister == '1') {

					scanner.nextLine();

					while(true) {
						System.out.print("\nName: ");
						nomeAtor = scanner.nextLine();
					
						if (nomeAtor.length() == 0)
							this.menusVIEW.printError("Nome vazio!");
						else break;
					}

					while(true) {
						System.out.print("Nif: ");
						nif = scanner.nextLine();
					
						if (gestor.isStringNif(nif) == false)
							this.menusVIEW.printError("NIF inválido!");
						else break;
					}					

					while(true) {
						System.out.print("Email: ");
						email = scanner.nextLine();
					
						if (gestor.isStringEmail(email) == false)
							this.menusVIEW.printError("Email invalid!");
						else break;
					}					

					System.out.print("Morada: ");
					morada = scanner.nextLine();
					
					try {

						this.estadoMODEL.criaAdicionaProprietario(email, 
																  nomeAtor, 
																  email, 
																  morada,
																  nif);

						logFinished = true;

						this.user = this.estadoMODEL.getProprietario(nif).clone();
					}
					catch (AtorAlreadyExistsException e) {

						this.menusVIEW.printError(e.getMessage());

						this.menusVIEW.printHint("\nPress Y to go back to menu...");
						
						if (scanner.next().charAt(0) == 'y' ||
							scanner.next().charAt(0) == 'Y') {
						
							this.menusVIEW.clearConsole();

							run();
						}					}

				} else if (optionRegister == '2') {


				}

			} else if (optionLogMenu == 'l') {

				this.menusVIEW.clearConsole();
				this.menusVIEW.cat("GUInterfaces/logInForm.txt");

				while (logFinished != true) {

					if (clearScreen == true) {

						this.menusVIEW.clearConsole();
						this.menusVIEW.cat("GUInterfaces/logInForm.txt");

						clearScreen = false;
					}

					System.out.print("\nYour NIF: ");

					String uname = getStringInput();

					System.out.print("Your password: ");

					String passw = getStringInput();

					if (gestor.isStringNif(uname)) {

						if (this.estadoMODEL.existeProprietario(uname)) {

							logFinished = 
								this.estadoMODEL.getProprietario(uname)
												.getPassword()
												.equals(passw);

							this.isProprietario = logFinished?true:false;

							this.user = this.estadoMODEL.getProprietario(uname).clone();
						
						} else if (this.estadoMODEL.existeCliente(uname)) {

							logFinished = 
								this.estadoMODEL.getCliente(uname)
												.getPassword()
												.equals(passw);							
							
							this.isProprietario = logFinished?false:false;

							this.user = this.estadoMODEL.getCliente(uname).clone();
						}

						if (logFinished) {

							this.usernameLogged = uname;
							break;
						}
					}

					this.menusVIEW.printError("Oops! Looks like you don't exist in the System...");
					
					this.menusVIEW.printError("Clear screen? [y/n]");
					
					if (scanner.next().charAt(0) == 'y')
						clearScreen = true;

					this.menusVIEW.printError("Go back to menu? [y/n]");
					
					if (scanner.next().charAt(0) == 'y') {
						
						this.menusVIEW.clearConsole();

						run();
					}
				}
			}
		}

		catch (java.util.InputMismatchException e) {

			System.out.println("Something went wrong, restart the app please...");
		}

		return logFinished;
   	}

   	public String getStringInput () {

		Scanner scanner = new Scanner(System.in);

		String input = scanner.next();
   	
		return input;
   	}

}