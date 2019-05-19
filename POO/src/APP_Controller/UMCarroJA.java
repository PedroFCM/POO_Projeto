
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
import java.time.format.DateTimeParseException;
import java.io.File;

import java.util.Scanner;
import java.util.List;
import java.lang.Character;
import java.time.LocalDate;

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
	private int currAluguerCarNum;

	private boolean appSaved;
	private boolean needToSave;
	private boolean appQuit;

	//--------------------------------------------------

    public UMCarroJA (EstadoSistema estadoM, GUI_UMcarroJA viewModel) {

    	this.estadoMODEL = new EstadoSistema(estadoM);
    	this.menusVIEW   = viewModel;
    }

	//--------------------------------------------------

    public void initLogs () throws IOException, FileNotFoundException {


    	GestorFicheirosDados fileManager = new GestorFicheirosDados();
    	
    	File load = new File("ObjectSaves/estadoObj.obj");

    	if (load.exists() && !load.isDirectory()) {

    		try {

	    		this.estadoMODEL = fileManager.objectFileParaEstado();

		    	//fileManager.stats_iniciais(this.estadoMODEL);
	    
	    		return;

    		}
    		catch (FileNotFoundException e) {}
    		catch (IOException e) {}
    		catch (ClassNotFoundException e) {}
    	}

    	this.menusVIEW.printHint("\t\t\t\nPrimeira vez a utilizar a APP, ou ainda não foram feitos SAVES!");

    	fileManager.carregarEstadoFromLogs(this.estadoMODEL, pathLogs);
        
    	//fileManager.stats_iniciais(this.estadoMODEL);
    }

    public void run () {
    	//this.estadoMODEL.top10Clientes();
	   	Scanner scanner = new Scanner(System.in);

		//--------------------------------------------------

	    this.menusVIEW.mainMenu();

	    boolean loginSuccess = controllLogIn();

		//--------------------------------------------------

	    welcomeMenu();
   	}

   	public void clientMenuSelection() {

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

				} else if (optionCliente.charAt(0) == '5') {

					welcomeMenu();					
				} else
					this.menusVIEW.printError("Option unavailable.");

			} else 
				this.menusVIEW.printError("Insert only 1 haracter, please.");
   		}

   	}

   	public Double getDoubleInput (String msg) {

  		Scanner scan = new Scanner(System.in);

  		Double x;
  		int n_spaces;

  		while (true) {

	  		this.menusVIEW.printHighlight(msg);

  			String doub = scan.nextLine();
  			
  			n_spaces = doub.length() - doub.replaceAll(" ", "").length();

  			if (n_spaces == 0) {

  				try {

  					x = Double.parseDouble(doub);
  				
  					break;
  				}
  				catch (NumberFormatException e) {

  					this.menusVIEW.printError("Formato inserido inválido, insira por exemplo: 12.2");
  				}

  			} else
  				this.menusVIEW.printError("Formato inserido inválido, insira por exemplo: 12.2");
  		}

  		return x;
   	}

   	public int getIntegerInput (String msg) {

  		Scanner scan = new Scanner(System.in);

  		int x;
  		int n_spaces;

  		while (true) {

	  		this.menusVIEW.printHighlight(msg);

  			String doub = scan.nextLine();
  			
  			n_spaces = doub.length() - doub.replaceAll(" ", "").length();

  			if (n_spaces == 0) {

  				try {

  					x = Integer.parseInt(doub);
  				
  					break;
  				}
  				catch (NumberFormatException e) {

  					this.menusVIEW.printError("Formato inserido inválido, insira por exemplo: 12");

  					return -1;
  				}

  			} else
  				this.menusVIEW.printError("Formato inserido inválido, insira por exemplo: 12");
  		}

  		return x;
   	}

   	public LocalDate getDataInput (String msg) {

   		Scanner scan = new Scanner(System.in);

   		String data;
		LocalDate nova;

   		while(true) {

   			this.menusVIEW.printHighlight("Insira uma data " + msg + ": (Ex: \"2019-05-18\" no formato \"yyyy-mm-dd\"");

   			data = scan.next();

   			try {

	   			nova = LocalDate.parse(data);
	   			break;
   			}
   			catch (DateTimeParseException e) {

   				this.menusVIEW.printError("Formato inválido! Respeite o seguinte formato yyyy-mm-dd");
   			}
   		}	

   		return nova;
   	}

   	public Localizacao whereToGo (String msg) {	

  		Scanner scan = new Scanner(System.in);

  		int n_spaces = 0;
  		Double x, y;

  		while (true) {

	  		this.menusVIEW.printHighlight(msg);

  			String posicao = scan.nextLine();
  			
  			n_spaces = posicao.length() - posicao.replaceAll(" ", "").length();

  			if (n_spaces == 1) {

  				try {

			  		String[] parts = posicao.split(" ");
  			
  					x = Double.parseDouble(parts[0]);
  					y = Double.parseDouble(parts[1]);
  				
  					break;
  				}
  				catch (NumberFormatException e) {

  					this.menusVIEW.printError("Formato inserido inválido, insira por exemplo: 2.0 2.0");
  				}
  			} else
  				this.menusVIEW.printError("Formato inserido inválido, insira por exemplo: 2.0 2.0");
  		}

  		return new Localizacao(x, y);
   	}

   	public void classificarCarroMenu (Veiculo v) {

   		int classParaAtribuir = -1;

   		while (classParaAtribuir < 0 || classParaAtribuir > 100) {

	   		classParaAtribuir = getIntegerInput("Qual classificação deseja atribuir ao carro? ");

	   		this.menusVIEW.printError("Atribua uma classificação entre 0 e 100 pontos!");
   		}

   		this.menusVIEW.printHighlight("Atribuiu " + classParaAtribuir + " pontos em 100!");

   		//Classificar o proprietario

   		v.atribuiClassificacao(classParaAtribuir);

   		this.needToSave = true;

   		this.menusVIEW.printHighlight("O carro tem agora " + v.getClassificacao());
   	}

   	public int getCarChoice(int max, List<Veiculo> listV) {

   		Scanner scan = new Scanner(System.in);

   		String optionCliente = "s";

   		int inp;

   		while (true) {

   			optionCliente = scan.next();

   				try { 
							
					inp = Integer.parseInt(optionCliente);
				}
				catch (NumberFormatException e) {

					continue;
				}

				if (inp >= 1 && inp <= max) {

					Localizacao destino = whereToGo("Para onde deseja ir? (Ex: insira x y => 2.0 2.0)");

	   				this.menusVIEW.printHighlight("Efetuar aluguer do carro " + inp + "? [y/n]");

	   				char choice;

	   				while (true) {

	   					choice = getStringInput().charAt(0);

	   					if (choice == 'y') {

	   						Veiculo paraAlugar = listV.get(inp-1);

					   		this.menusVIEW.clearConsole();
							this.menusVIEW.cat("GUInterfaces/welcomeUser.txt");

							this.menusVIEW.printUserDetails(user.getNome(),
															user.getMorada(),
															user.getEmail(),
															user.getClassificacao(),
															user.getNif(),
															user.getClass().getSimpleName());
	   						
	   						Double distancia = destino.distancia(paraAlugar.getLocalizacao());

	   						if (!((VeiculoComAutonomia) paraAlugar).temAutonomiaParaViagem(distancia)) {
						   		
						   		this.menusVIEW.clearConsole();
								this.menusVIEW.cat("GUInterfaces/welcomeUser.txt");

								this.menusVIEW.printUserDetails(user.getNome(),
																user.getMorada(),
																user.getEmail(),
																user.getClassificacao(),
																user.getNif(),
																user.getClass().getSimpleName());

								this.menusVIEW.printError("This vehicle does not have enough autonomy, sorry try again later.");

	   							break;

	   						}

				            Aluguer novoAluguerMP = new Aluguer (paraAlugar.getMatricula(),
	                                                   			 this.user.getNif(),
	                                                   			 paraAlugar.getProprietario(),
	                                                   			 distancia,
	                                                    		 paraAlugar.getPrecoPorKM() * distancia,
	                                                   			 LocalDate.now());

				            this.user.adicionaAluguer(novoAluguerMP);
				            this.estadoMODEL.replaceClienteSistema((Cliente) this.user);

				            this.menusVIEW.printFaturaAlguer(novoAluguerMP.getCliente(),
				            								 novoAluguerMP.getProprietario(),
				            								 novoAluguerMP.getVeiculo(),
				            								 novoAluguerMP.getPreco(),
				            								 novoAluguerMP.getDistancia(),
				            								 novoAluguerMP.getData());

				            this.estadoMODEL.consumirkmAluguer((VeiculoComAutonomia) paraAlugar,
				            							 distancia);
				            
				            classificarCarroMenu(paraAlugar);

				            //paraAlugar.incrementVezesAlugadoBy1();
				            
				            paraAlugar.setLocalizacao(destino);
				            ((Cliente) this.user).setLocalizacao(destino);

				            Proprietario p = this.estadoMODEL.getProprietario(paraAlugar.getProprietario()); 
				            p.replaceVeiculo(paraAlugar);
				            
					   		p.classifica(paraAlugar);
					   		
				            this.estadoMODEL.replaceProprietario(p);
				            this.estadoMODEL.replaceVeiculoSistema(paraAlugar);

				            this.needToSave = true;

	   						break;

	   					} else if (choice == 'n') {

					   		this.menusVIEW.clearConsole();
							this.menusVIEW.cat("GUInterfaces/welcomeUser.txt");

							this.menusVIEW.printUserDetails(user.getNome(),
															user.getMorada(),
															user.getEmail(),
															user.getClassificacao(),
															user.getNif(),
															user.getClass().getSimpleName());

	   						break;
	   					} else 
	   						this.menusVIEW.printError("Option not available...");

	   				}

	   				break;

				} else {
					this.menusVIEW.printError("Option unavailable.");
					System.out.print("Please, choose a car by its NUMBER: ");
				}
   		}

   		return inp;
  	}

   	public void welcomeMenu() {

   		if (this.appQuit == true)
   			System.exit(1);

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
						optionCliente.charAt(0) == '3' ||
						optionCliente.charAt(0) == '4' ||
						optionCliente.charAt(0) == '5') {

						if (optionCliente.charAt(0) != '5') 
							clientMenuSelection();

				   		this.menusVIEW.clearConsole();
						this.menusVIEW.cat("GUInterfaces/welcomeUser.txt");

						this.menusVIEW.printUserDetails(user.getNome(),
														user.getMorada(),
														user.getEmail(),
														user.getClassificacao(),
														user.getNif(),
														user.getClass().getSimpleName());
						
						if (optionCliente.charAt(0) == '1') {			 
						
							try {

								this.menusVIEW
									.printClientLocationBasicInfo(((Cliente) this.user).getLocalizacao()
																		             .getX(),
																  ((Cliente) this.user).getLocalizacao()
																  		             .getY());

								List<Veiculo> listaV = this.estadoMODEL
												.carroMaisProximo((Cliente) this.user, this.currentAlugerPref);

								int num_cars = printAvailableCars(listaV);
							
								int car = getCarChoice(num_cars, listaV);
			
							}
							
							catch (CarNotAvailableException e) {

								this.menusVIEW.printError("There's no car available now.");
							}

						} else if (optionCliente.charAt(0) == '2') {

							try {

								this.menusVIEW
									.printClientLocationBasicInfo(((Cliente) this.user).getLocalizacao()
																		             .getX(),
																  ((Cliente) this.user).getLocalizacao()
																  		             .getY());

								//System.out.println(this.currentAlugerPref);

								List<Veiculo> listaV = this.estadoMODEL
												.carroMaisBarato(this.currentAlugerPref);
								

								int num_cars = printAvailableCars(listaV);
							
								int car = getCarChoice(num_cars, listaV);
							}
							
							catch (CarNotAvailableException e) {

								this.menusVIEW.printError("There's no car available now.");
							}
						} else if (optionCliente.charAt(0) == '3') {

							try {

								this.menusVIEW
									.printClientLocationBasicInfo(((Cliente) this.user).getLocalizacao()
																		             .getX(),
																  ((Cliente) this.user).getLocalizacao()
																  		             .getY());

								//System.out.println(this.currentAlugerPref);
								Double x = getDoubleInput("Qual a distância que deseja? ");
									
								System.out.println("Escolheu um distância de " + x + " km.");

								List<Veiculo> listaV = this.estadoMODEL
												.carroMaisBarato(((Cliente) this.user), x, this.currentAlugerPref);								

								if (listaV.size() == 0) {
								
									this.menusVIEW.printError("There's no car available now.");
								} else {

									int num_cars = printAvailableCars(listaV);
									
									int car = getCarChoice(num_cars, listaV);
								}
							}
							
							catch (CarNotAvailableException e) {

								this.menusVIEW.printError("There's no car available now.");
							}

						} else if (optionCliente.charAt(0) == '4') {

							try {

								this.menusVIEW
									.printClientLocationBasicInfo(((Cliente) this.user).getLocalizacao()
																		               .getX(),
																  ((Cliente) this.user).getLocalizacao()
																  		               .getY());

								Double auto = getDoubleInput("Qual a autonomia que deseja? ");
									
								System.out.println("Escolheu uma autonomia de " + auto + " km.");

								List<Veiculo> listaV = this.estadoMODEL
												.carroComCertaAutonomia(this.currentAlugerPref, auto);								

								if (listaV.size() == 0) {
								
									this.menusVIEW.printError("There's no car available now.");
								} else {

									int num_cars = printAvailableCars(listaV);
								
									int car = getCarChoice(num_cars, listaV);
								}
							}
							
							catch (CarNotAvailableException e) {

								this.menusVIEW.printError("There's no car available now.");
							}

						} else if (optionCliente.charAt(0) == '5') {

							List<Aluguer> listaAlugueres = ((Cliente) this.user).getHistoricoAlugueres();

							LocalDate inf = getDataInput("inferior");
							LocalDate sup = getDataInput("superior");

							printListBetweenDates(listaAlugueres, inf, sup);

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


		} else if (user.getClass().getSimpleName().equals("Proprietario")) {

			String optionCliente = "s";

			while (optionCliente.charAt(0) != 'z') {
	
				optionCliente = scan.next();

				if (optionCliente.length() == 1) {

					char inp = optionCliente.charAt(0);

					if (optionCliente.charAt(0) == '1' ||
						optionCliente.charAt(0) == '2' ||
						optionCliente.charAt(0) == '3' ||
						optionCliente.charAt(0) == '4' ||
						optionCliente.charAt(0) == 'c' ||
						optionCliente.charAt(0) == 'y') {

					//----------------------------------------------------------------------------------------

						if (optionCliente.charAt(0) == '1') {
							
							List<Veiculo> veiculosP = ((Proprietario) this.user).getListaVeiculos();
							
							presentCarsOwner(veiculosP);
							
							this.menusVIEW.clearConsole();
				
							this.menusVIEW.cat("GUInterfaces/welcomeUser.txt");

							this.menusVIEW.printUserDetails(user.getNome(),
															user.getMorada(),
															user.getEmail(),
															user.getClassificacao(),
															user.getNif(),
															user.getClass().getSimpleName());
	
					//----------------------------------------------------------------------------------------
					
						} else if (optionCliente.charAt(0) == '2') {

							LocalDate antes = getDataInput("inferior");
							
							LocalDate depois;

							while (true) {
								
								depois = getDataInput("superior");
								
								if(depois.isAfter(antes) || depois.isEqual(antes)) break;
								else {
									this.menusVIEW.printError("Essa data não é superior!");
								} 
							}
							
							this.menusVIEW.printHighlight("List of rentals between " + antes + " and " + depois);
							
							printListBetweenDates(((Proprietario) this.user).getHistoricoAlugueres(), antes, depois);

					//----------------------------------------------------------------------------------------

						} else if (optionCliente.charAt(0) == '3') {

							List<Veiculo> veiculosP = ((Proprietario) this.user).getListaVeiculos();

							this.menusVIEW.printHint("Visit the first (1) menu to choose your car, example: ...Vehicle x....");
							this.menusVIEW.printHint("(Press \"q\" to go back to menu)");

							int sel_car = getIntegerInput("Car option:");

							if (sel_car == -1) 
								welcomeMenu();

							if (sel_car >= 0 && sel_car < veiculosP.size()) {

								Double aumentarAuto = getDoubleInput("Insira a quantidade de combustivel: ");

								Veiculo paraAumentar = veiculosP.get(sel_car);

								((Proprietario) this.user).abastecer((VeiculoComAutonomia) paraAumentar, 
																	 aumentarAuto);
								
								((Proprietario) this.user).replaceVeiculo(paraAumentar);

					            this.estadoMODEL.replaceProprietario((Proprietario) this.user);
					            this.estadoMODEL.replaceVeiculoSistema(paraAumentar);

					            this.needToSave = true;
							}

					//----------------------------------------------------------------------------------------

						} else if (optionCliente.charAt(0) == '4') {

							Scanner s = new Scanner(System.in);

							this.menusVIEW.printCreateVehicles();							

							String tipo, marca, matricula;
							Double vel_media, pkm, cons, auton, xloc, yloc;

							GestorFicheirosDados gestor = new GestorFicheirosDados();

							while(true) {
								
								this.menusVIEW.printHint("Choose the kind of car you want to add: ");

								tipo = s.nextLine();
							
								if (tipo.length() == 1) {

									if (tipo.charAt(0) == '1') {
										tipo = "Hybrid";
										break;
									}
									else if (tipo.charAt(0) == '2') {
										tipo = "Gasoline";
										break;
									} 
									else if (tipo.charAt(0) == '3') {
										tipo = "Eletric";
										break;
									}
								} else 
									this.menusVIEW.printError("Invalid argument...");
							}

							while(true) {
																	
								this.menusVIEW.printHint("Marca: ");
								
								marca = s.nextLine();
							
								if (tipo.length() == 0) {

									this.menusVIEW.printError("Marca vazia!");
								} else break;
							}

							while(true) {
																	
								this.menusVIEW.printHint("Matricula: ");
								
								matricula = s.nextLine();
							
								if (gestor.isStringMatricula(matricula) == false) {

									this.menusVIEW.printError("Não é uma matricula!");
								} else break;
							}
											
							vel_media = getDoubleInput("Vel. Med: ");
							pkm = getDoubleInput("Price/km: ");
							cons = getDoubleInput("Cons/km: ");
							auton = getDoubleInput("Auto.: ");
							xloc = getDoubleInput("Local. X: ");
							yloc = getDoubleInput("Local. Y: ");

							if (tipo.equals("Hybrid")) {

								VeiculoComAutonomia v = new CarroHibrido(matricula, vel_media, pkm, 0, new Localizacao(xloc, yloc), cons, auton, auton, ((Proprietario) this.user).getNif(), true, marca, 0);
								
								((Proprietario) this.user).adicionaVeiculo(v);								

								this.estadoMODEL.replaceProprietario((Proprietario) this.user);

								this.estadoMODEL.adicionaVeiculoSistema(v);
								this.needToSave = true;
							} else
							if (tipo.equals("Gasoline")) {

								VeiculoComAutonomia v = new CarroGasolina(matricula, vel_media, pkm, 0, new Localizacao(xloc, yloc), cons, auton, auton, ((Proprietario) this.user).getNif(), true, marca, 0);
								
								((Proprietario) this.user).adicionaVeiculo(v);								

								this.estadoMODEL.replaceProprietario((Proprietario) this.user);

								this.estadoMODEL.adicionaVeiculoSistema(v);
								this.needToSave = true;
							} else 
							if (tipo.equals("Eletric")) {

								VeiculoComAutonomia v = new CarroEletrico(matricula, vel_media, pkm, 0, new Localizacao(xloc, yloc), cons, auton, auton, ((Proprietario) this.user).getNif(), true, marca, 0);
								
								((Proprietario) this.user).adicionaVeiculo(v);								

								this.estadoMODEL.replaceProprietario((Proprietario) this.user);

								this.estadoMODEL.adicionaVeiculoSistema(v);
								this.needToSave = true;	
							}	


						} else if (optionCliente.charAt(0) == 'y') {

							this.menusVIEW.clearConsole();

							run();
					
						} else if (optionCliente.charAt(0) == 'c') {

							//System.out.println("ola");

							welcomeMenu();

						} else {

							this.menusVIEW.printError("Option unavailable.");
						}

					}

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
			while (optionLogMenu != 'r' && optionLogMenu != 'l' && optionLogMenu != 's' && optionLogMenu != 'q') {

				this.menusVIEW.printError("Please, insert a valid option...");				

				optionLogMenu = scanner.next().charAt(0);
			}

			GestorFicheirosDados gestor = new GestorFicheirosDados();

			if (optionLogMenu == 's') {
				try {
					gestor.estadoParaObjectFile(this.estadoMODEL);
					this.appSaved = true;
					this.needToSave = false;
					controllLogIn();
				}
				catch(FileNotFoundException e) {
					System.out.println(e.getMessage());
				}
				catch(IOException e) {
					System.out.println(e.getMessage());					
				}
			}

			if (optionLogMenu == 'q') {

				System.exit(0);
			}

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

						this.user = this.estadoMODEL.getProprietario(nif);

						logFinished = true;
						this.needToSave = true;
						return logFinished;
					}
					catch (AtorAlreadyExistsException e) {

						this.menusVIEW.printError(e.getMessage());

						this.menusVIEW.printHint("\nPress Y to go back to menu...");
						
						while (scanner.next().charAt(0) != 'y' &&
							scanner.next().charAt(0) != 'Y') {
							this.menusVIEW.printError("Insira uma opção válida!");
						}

						this.menusVIEW.clearConsole();

						run();
					}

				} else if (optionRegister == '2') {

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

					Localizacao cl_loc = whereToGo("Onde se encontra? (Insira por exemplo: 15.2 12.0)");

					try {

						this.estadoMODEL.criaAdicionaCliente(email, 
																  nomeAtor, 
																  email, 
																  morada,
																  nif,
																  cl_loc);

						this.user = this.estadoMODEL.getCliente(nif);

						return logFinished;
					}
					catch (AtorAlreadyExistsException e) {

						this.menusVIEW.printError(e.getMessage());

						this.menusVIEW.printHint("\nPress Y to go back to menu...");
						
						while (scanner.next().charAt(0) != 'y' &&
							scanner.next().charAt(0) != 'Y') {
							this.menusVIEW.printError("Insira uma opção válida!");
						}

						this.menusVIEW.clearConsole();

						run();
					}
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

   	public int printAvailableCars (List<Veiculo> listV) {

   		int i = 1;

   		this.menusVIEW.printHighlight("\nThere's(re) " + listV.size() + " car(s) available: \n");

   		for (Veiculo v: listV) {

   			this.menusVIEW.printHint("\nVehicle " + i + ":________________________________________________________");

   			this.menusVIEW.printVehicleBasicInfo(v.getMarca(),
   												 v.getMatricula(),
   												 v.getVelMediaPorKM(),
   												 v.getPrecoPorKM(),
   												 ((VeiculoComAutonomia) v).getConsumoPorKM(),
   												 ((VeiculoComAutonomia) v).getAutonomiaAtual(),
   												 v.getLocalizacao().getX(),
   												 v.getLocalizacao().getY(),
   												 v.getLocalizacao().distancia(((Cliente) user).getLocalizacao()),
   												 v.getClassificacao(),
   												 v.getVezesAlugado());
   			i++;
   		}

   		System.out.print("Please, choose a car by its NUMBER: ");
   		
   		return listV.size();
   	}

   	public int presentCarsOwner (List<Veiculo> cars) {

   		this.menusVIEW.printHighlight("\nThere's(re) " + cars.size() + " car(s) available: \n");
			
   		if (cars.size() == 0) {
   			return 0;
   		}

   		int i = 1;

   		Scanner scan = new Scanner(System.in);

   		String option = "s";

   		int posLISTA = 0, size = cars.size();

   		Veiculo v = null;

   		while (true) {
		
   			v = cars.get(posLISTA);

			this.menusVIEW.clearConsole();
			this.menusVIEW.cat("GUInterfaces/welcomeUser.txt");

			this.menusVIEW.printUserDetails(user.getNome(),
											user.getMorada(),
											user.getEmail(),
											user.getClassificacao(),
											user.getNif(),
											user.getClass().getSimpleName());
		
			this.menusVIEW.printHighlight("Press \"f\" to see next page or \"b\" to go back. (\"q\" to leave this menu)");

			this.menusVIEW.printHint("\nVehicle " + posLISTA + ":________________________________________________________");

			this.menusVIEW.printVehicleBasicInfoOWNER(v.getMarca(),
   						 v.getMatricula(),
   						 v.getVelMediaPorKM(),
   						 v.getPrecoPorKM(),
   						 ((VeiculoComAutonomia) v).getConsumoPorKM(),
   						 ((VeiculoComAutonomia) v).getAutonomiaAtual(),
   						 v.getLocalizacao().getX(),
   						 v.getLocalizacao().getY(),
   						 v.getClassificacao(),
   						 v.getVezesAlugado());
					option = scan.next();

			if (option.length() == 1) {

				if (option.charAt(0) == 'f') {

						if (posLISTA + 1 < size)
							posLISTA++;
				
				} else if (option.charAt(0) == 'b') {

						if (posLISTA - 1 >= 0)
							posLISTA--;

				} else if (option.charAt(0) == 'q') {

					break;
				} else {
					this.menusVIEW.printError("Insira uma opção válida!");
				}

			} else this.menusVIEW.printError("Insira apenas 1 caracter!");

		}

   		return cars.size();
   	}

   	public void printListBetweenDates (List<Aluguer> listaAluguers, LocalDate antes, LocalDate depois) {

   		if (listaAluguers.size() == 0) {
   			this.menusVIEW.printError("There are no rentals at the moment to display!");
   			return;
   		}

   		for (Aluguer a: listaAluguers) {

   			if ((a.getData().isEqual(antes) || a.getData().isAfter(antes)) && 
   				(a.getData().isEqual(depois) || a.getData().isBefore(depois))) {

   				this.menusVIEW.printFaturaAlguer(a.getCliente(),
				            					 a.getProprietario(),
				            					 a.getVeiculo(),
				            					 a.getPreco(),
				            					 a.getDistancia(),
				            					 a.getData());
   			}
   		}

   	}

}