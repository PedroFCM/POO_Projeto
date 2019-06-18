//-------------------------------------------------------------------------------------------------------------

/**
 * Class que implementa o controlador de Um carro Ja.
 * Utiliza a VIEW e o MODELO para implementar um MVC.
 *
 * @author João Pedro Rodrigues Azevedo
 * @author Pedro Filipe Costa Machado
 * @author Paulo Jorge da Silva Araújo 
 *
 * @version 2019/05/25
 */

//-------------------------------------------------------------------------------------------------------------

import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.io.File;

import java.util.Scanner;
import java.util.List;
import java.lang.Character;
import java.time.LocalDate;

import ExceptionsProgramFlow.*;

//----------------------------------------------------------------------------------------------

public class ControladorAPP {

	//----------------------------------------------------------------------------------------------

	/*
	 * Path para o ficheiro de carregamento inicial desta App
	*/
	private final static String pathLogs = "Logs/logsPOO_carregamentoInicial.bak";

	//----------------------------------------------------------------------------------------------

	/*
	 * Guarda o estado da aplicação - MODELO
	*/
	private EstadoSistema estadoMODEL;
	/*
	 * Para utilizar as funcionalidades da - VIEW
	*/
	private GUI_UMcarroJA menusVIEW;
	/*
	 * Nome do utilizador Loggado
	*/
	private String usernameLogged;
	/*
	 * Indica se o user é proprietario ou não
	*/
	private boolean isProprietario;
	/*
	 * Nome do utilizador Loggado
	*/
	private String currentAlugerPref;
	/*
	 * Guarda o utilizador que está loggado atualmente
	*/
	private AtorSistema user;
	/*
	 * Utilizador do ultimo aluguer feito
	*/
	private AtorSistema clienteQueAlugou;
	/*
	 * Numero do carro que foi alugado atualmente
	*/
	private int currAluguerCarNum;
	/*
	 * Indica se a app foi guardada
	*/
	private boolean appSaved;
	/*
	 * Indica se existem dados por guardar
	*/
	private boolean needToSave;
	/*
	 * Indica se é preciso dar quit à app
	*/
	private boolean appQuit;

	//----------------------------------------------------------------------------------------------
    
    /**
     * Construtor parameterizado da classe ControladorAPP
     *
     * @param estadoM Estado == modelo.
     * @param viewModel VIEW == apresentacao.
     *
    */

    public ControladorAPP (EstadoSistema estadoM, GUI_UMcarroJA viewModel) {

    	this.estadoMODEL = new EstadoSistema(estadoM);
    	this.menusVIEW   = viewModel;
    }
    
    /**
     * Construtor cópia de um ControladorAPP
     *           
     * @param c ControladorAPP novo.
     *
    */
    
    public ControladorAPP (ControladorAPP c) {

    	this.estadoMODEL = c.getModel();
    	this.menusVIEW   = c.getView();
    }

    /**
     * Construtor vazio de um ControladorAPP
     *
    */

    public ControladorAPP () {

    	this.estadoMODEL = new EstadoSistema();
    	this.menusVIEW   = new GUI_UMcarroJA();
    }

	//-------------------------------------------------------------------------------------------------

    /**
     * Retorna uma cópia do MODELO do Sistema.
     *
     * @return Copia do Sistema.
    */

    public EstadoSistema getModel () {

    	return new EstadoSistema(this.estadoMODEL);
    }

	//-------------------------------------------------------------------------------------------------

    /**
     * Retorna o objeto VIEW usado.
     * Não necessita de ser uma cópia visto que este nao tem estado interno.
     *
     * @return Copia do Sistema.
    */

    public GUI_UMcarroJA getView () {

    	return this.menusVIEW;
    }

	//-------------------------------------------------------------------------------------------------

    /**
     * Gere a inicializacao do sistema.
     * Caso já exista um SAVE, então o programa inicia a partir desse save.
     *
     * @throws IOExcetpion Caso hajam problemas de IO     
     * @throws FileNotFoundException Caso não exista o ficheiro de leitura/escrita
     *
     * @return 1 caso não existam saves, 0 caso existam.
    */

    public int initLogs () throws IOException, FileNotFoundException {


    	GestorFicheirosDados fileManager = new GestorFicheirosDados();
    	
    	File load = new File("ObjectSaves/estadoObj.obj");

    	if (load.exists() && !load.isDirectory()) {

    		try {

	    		this.estadoMODEL = fileManager.objectFileParaEstado();
	    
    		return 0;

    		}
    		catch (FileNotFoundException e) {

    			System.out.println(e.getMessage());
    		}
    		catch (IOException e) {

    			System.out.println(e.getMessage());
    		}
    		catch (ClassNotFoundException e) {

    			System.out.println(e.getMessage());
    		}
    	}

    	fileManager.carregarEstadoFromLogs(this.estadoMODEL, pathLogs);
        
    	return 1;
    }

    /**
     * Corre o programa após inicializado com SAVES ou LOGS.
    */

    public void run () {
    	
    	Scanner scanner = new Scanner(System.in);

		//--------------------------------------------------

	    this.menusVIEW.mainMenu();

	    boolean loginSuccess = controllLogIn();

		//--------------------------------------------------

	    welcomeMenu();
   	}

    /**
     * Menu de seleção do carro por parte de um cliente.
    */

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

    /**
     * Função que gere o input do user para Doubles
     *
     * @param msg mensagem a apresentar ao user
     *
     * @return Double lido
    */

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

    /**
     * Função que gere o input do user para Integers
     *
     * @param msg mensagem a apresentar ao user
     *
     * @return Integer lido
    */

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

    /**
     * Função que gere o input do user para LocalDates
     *
     * @param msg mensagem a apresentar ao user
     *
     * @return LocalDate lido
    */

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

    /**
     * Função que gere o input do user para recolher o destino do user
     *
     * @param msg mensagem a apresentar ao user
     *
     * @return Localizacao destino lida
    */

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

    /**
     * Menu de classificacao do carro e interação com o user.
     *
     * @param v Veiculo para classificar
     * @param a Aluguer 
     *
    */

   	public void classificarCarroMenu (Veiculo v, Aluguer a) {

   		int classParaAtribuir = -1;

   		while (classParaAtribuir < 0 || classParaAtribuir > 100) {

	   		classParaAtribuir = getIntegerInput("Qual classificação deseja atribuir ao carro? ");

	   		if (classParaAtribuir >= 0 && classParaAtribuir <= 100)
	   			break;
	   		
	   		this.menusVIEW.printError("Atribua uma classificação entre 0 e 100 pontos!");
   		}

   		this.menusVIEW.printHighlight("Atribuiu " + classParaAtribuir + " pontos em 100!");

   		v.atribuiClassificacao(classParaAtribuir);

		Proprietario p = this.estadoMODEL.getProprietario(v.getProprietario()); 
		
		p.replaceVeiculo(v); 

 		this.estadoMODEL.replaceProprietario(p);
		this.estadoMODEL.replaceVeiculoSistema(v);

		((Cliente) this.user).removeAluguerNaoClassificao(a);
		
		this.estadoMODEL.replaceClienteSistema((Cliente) this.user);
   		
   		this.menusVIEW.printHighlight("O carro tem agora " + v.getClassificacao());
   	}

    /**
     * Menu de apresentação e interação para obter a escolha de aluguer do cliente.
     *
     * @param max Numero maximo da lista de carros disponiveis calculada
     * @param listV Lista de carros disponiveis 
     *
     * @return Escolha do user
     *
    */

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

	   				this.menusVIEW.printHighlight("Efetuar pedido de aluguer do carro " + inp + "? [y/n]");

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

	   						} else {

	   							this.menusVIEW.printHighlight("Este carro tem autonomia para a sua viagem!");
	   						}
				            
				            Aluguer novoAluguerMP = new Aluguer (paraAlugar.getMatricula(),
	                                                   			 this.user.getNif(),
	                                                   			 paraAlugar.getProprietario(),
	                                                   			 distancia,
	                                                    		 paraAlugar.getPrecoPorKM() * distancia,
	                                                   			 LocalDate.now(),
	                                                   			 destino);

	   						this.estadoMODEL.enviarAluguerProprietario(novoAluguerMP.clone());

				            this.menusVIEW.printFaturaAlguer(novoAluguerMP.getCliente(),
				            								 novoAluguerMP.getProprietario(),
				            								 novoAluguerMP.getVeiculo(),
				            								 novoAluguerMP.getPreco(),
				            								 novoAluguerMP.getDistancia(),
				            								 novoAluguerMP.getData(),1,
				            								 this.estadoMODEL.getCliente(novoAluguerMP.getCliente()).getClassificacao());
				            
				            this.menusVIEW.printHighlight("O seu pedido de aluguer foi enviado com sucesso! [Pressione \"c\" para voltar]");

				            Proprietario p = this.estadoMODEL.getProprietario(paraAlugar.getProprietario()); 
				            					   		
				            this.estadoMODEL.replaceProprietario(p);

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

    /**
     * Menu de apresentação do perfil do utilizador (Proprietario ou Cliente)
     *
     * São aqui geridadas todas as Opções do utilizador chamando
     * os menus necessários ao funcionamento.
     *
     * Menu principal de I/O
    */

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
						optionCliente.charAt(0) == '5' ||
						optionCliente.charAt(0) == '6') {

						if (optionCliente.charAt(0) != '5' && optionCliente.charAt(0) != '6') 
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

						} else if (optionCliente.charAt(0) == '6') {

							List<Aluguer> listaAlugSemClass = ((Cliente) this.user).getNaoClassificados();

							if (listaAlugSemClass.size() == 0) {

								this.menusVIEW.printError("Não existem viagens por classificar.");
							} else {

								this.printListBetweenDates(listaAlugSemClass, LocalDate.now(), LocalDate.now());

								int toClassify = -1;

								while (true) {

									toClassify = getIntegerInput("Qual fatura deseja classificar? [Ex: ...Fatura x do dia .....]");
								
									if (toClassify >= 1 && toClassify <= listaAlugSemClass.size())
										break;
									else 
										this.menusVIEW.printError("Opção Inválida!");
								}

								Veiculo paraClassificar = this.estadoMODEL.getVeiculo(listaAlugSemClass.get(toClassify-1).getVeiculo());

								classificarCarroMenu(paraClassificar, listaAlugSemClass.get(toClassify-1));
							}
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
						optionCliente.charAt(0) == '5' ||
						optionCliente.charAt(0) == '6' ||
						optionCliente.charAt(0) == 'c' ||
						optionCliente.charAt(0) == 'y') {

					//----------------------------------------------------------------------------------------

						if (optionCliente.charAt(0) == '1') {
							
							List<Veiculo> veiculosP = ((Proprietario) this.user).getListaVeiculos();
														
							this.menusVIEW.clearConsole();
				
							this.menusVIEW.cat("GUInterfaces/welcomeUser.txt");

							this.menusVIEW.printUserDetails(user.getNome(),
															user.getMorada(),
															user.getEmail(),
															user.getClassificacao(),
															user.getNif(),
															user.getClass().getSimpleName());
	
							presentCarsOwner(veiculosP);

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

							if (veiculosP.size() == 0) 
								this.menusVIEW.printError("Não existem carros registados, insira carros para poder abastecer.");
							else {
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

						} else if (optionCliente.charAt(0) == '5') {

							List<Aluguer> listAluguer = ((Proprietario) this.user).getPedidosAluguer();

							int r = printListBetweenDates(listAluguer, LocalDate.of(2018, 1, 1), LocalDate.of(2020, 1, 1));

							if (r == -1) {

									this.menusVIEW.clearConsole();
						
									this.menusVIEW.cat("GUInterfaces/welcomeUser.txt");

									this.menusVIEW.printUserDetails(user.getNome(),
																	user.getMorada(),
																	user.getEmail(),
																	user.getClassificacao(),
																	user.getNif(),
																	user.getClass().getSimpleName());

									this.menusVIEW.printError("Não existem pedidos pendentes de momento!");
							}

							String op = "s";

							Scanner s = new Scanner(System.in);

							while (r != -1) {

								this.menusVIEW.printHint("(1) Aceitar pedido | (2) Regeitar pedido | (3) Voltar");

								op = s.next();

								if (op.charAt(0) == '1') {

									int pedidoParaAceitar;

									pedidoParaAceitar = getIntegerInput("Qual o pedido que quer aceitar? <insira o numero da fatura>"); 
									
									if ((pedidoParaAceitar >= 1) && (pedidoParaAceitar <= listAluguer.size())) {
										
										Aluguer aceitado = listAluguer.get(pedidoParaAceitar - 1).clone();
										
										Cliente clienteQueAlugou = this.estadoMODEL.getCliente(aceitado.getCliente()).clone();

										clienteQueAlugou.adicionaAluguer(aceitado);
										
										this.menusVIEW.clearConsole();
						
										this.menusVIEW.cat("GUInterfaces/welcomeUser.txt");

										this.menusVIEW.printUserDetails(user.getNome(),
																	user.getMorada(),
																	user.getEmail(),
																	user.getClassificacao(),
																	user.getNif(),
																	user.getClass().getSimpleName());

										this.menusVIEW.printFaturaAlguer(aceitado.getCliente(),
							            								 aceitado.getProprietario(),
							            								 aceitado.getVeiculo(),
							            								 aceitado.getPreco(),
							            								 aceitado.getDistancia(),
							            								 aceitado.getData(),
							            								 -1,
							            								 clienteQueAlugou.getClassificacao());


										clienteQueAlugou.setLocalizacao(aceitado.getDestino());

										clienteQueAlugou.adicionaAluguerNaoClassificao(aceitado.clone());

										this.estadoMODEL.replaceClienteSistema(clienteQueAlugou);

										Veiculo alugado = this.estadoMODEL.getVeiculo(aceitado.getVeiculo()).clone();

										this.estadoMODEL.consumirkmAluguer(((VeiculoComAutonomia) alugado).clone(), 
																		   aceitado.getDistancia());

										alugado.setLocalizacao(aceitado.getDestino());

										alugado.incrementVezesAlugadoBy1();

										Proprietario p = this.estadoMODEL.getProprietario(alugado.getProprietario()); 
				            			
										p.removePedidoAluguer(aceitado);

				            			p.replaceVeiculo(alugado); 

				            			this.user = p.clone();

 										this.estadoMODEL.replaceProprietario(p);
				           			 	this.estadoMODEL.replaceVeiculoSistema(alugado);
										
										this.menusVIEW.printHint("Pedido de Aluguer efetuado, pressione \"c\" para sair");

										while (true) {

											op = s.next();

											if ((op.length() == 1) && (op.charAt(0) == 'c')) {

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
												this.menusVIEW.printError("Opção inválida!");

										}

										break;
									}

									this.menusVIEW.printError("Opção Inválida!");

								} else if (op.charAt(0) == '2') {

									int pedidoParaRemover = 0;
									
									while (true) {
										
										pedidoParaRemover = getIntegerInput("Qual aluguer pretende rejeitar?");

										if ((pedidoParaRemover >= 1) && (pedidoParaRemover <= listAluguer.size())) {

											Aluguer aceitado = listAluguer.get(pedidoParaRemover - 1).clone();

											Veiculo alugado = this.estadoMODEL.getVeiculo(aceitado.getVeiculo()).clone();

											Proprietario p = this.estadoMODEL.getProprietario(alugado.getProprietario()); 
					            			
											p.removePedidoAluguer(aceitado);
																						
                      p.replaceVeiculo(alugado); 

					            			this.user = p.clone();

	 										this.estadoMODEL.replaceProprietario(p);
					           			 	this.estadoMODEL.replaceVeiculoSistema(alugado);
											
											break;

										} else {

											this.menusVIEW.printError("Essa fatura não existe!");
										}
									}

								} else if (op.charAt(0) == '3') {

									this.menusVIEW.clearConsole();
						
									this.menusVIEW.cat("GUInterfaces/welcomeUser.txt");

									this.menusVIEW.printUserDetails(user.getNome(),
																	user.getMorada(),
																	user.getEmail(),
																	user.getClassificacao(),
																	user.getNif(),
																	user.getClass().getSimpleName());
									break;
								}
							}

						} else if (optionCliente.charAt(0) == '6') {

							List<Veiculo> veiculosP = ((Proprietario) this.user).getListaVeiculos();

							if (veiculosP.size() == 0) 
								this.menusVIEW.printError("Não existem carros registados ainda.");
							else {
								this.menusVIEW.printHint("Visit the first (1) menu to choose your car, example: ...Vehicle x....");
								this.menusVIEW.printHint("(Press \"q\" to go back to menu)");

								int sel_car = getIntegerInput("Car option:");

								if (sel_car == -1) 
									welcomeMenu();

								if (sel_car >= 0 && sel_car < veiculosP.size()) {

									LocalDate antes = getDataInput("inferior");
									LocalDate depois;

									while (true) {
										
										depois = getDataInput("superior");
										
										if(depois.isAfter(antes) || depois.isEqual(antes)) break;
										else {
											this.menusVIEW.printError("Essa data não é superior!");
										} 
									}

									Veiculo v = veiculosP.get(sel_car);

									this.presentFaturacaoViatura(v, this.estadoMODEL.totalFaturadoNoPeriodoViatura(v, antes, depois));
								}
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

    /**
     * Menu de apresentação do perfil de administrador
     *
     * São aqui geridadas todas as Opções do admin. 
     *
    */

   	public void adminMenu () {

   		String adminOp = " ";

   		Scanner sc = new Scanner(System.in);

   		while(adminOp.charAt(0) != 'q') {

   			this.menusVIEW.clearConsole();
	   		this.menusVIEW.cat("GUInterfaces/admin.txt");

	   		adminOp = sc.next();

	   		if ((adminOp.length() == 1) && (adminOp.charAt(0) == '1')) {

	   			List<Cliente> top10List = this.estadoMODEL.top10Clientes();

	   			if (top10List.size() == 0) {

	   				this.menusVIEW.printError("Ainda não existem clientes registados!");
	   			
	   			} else {

	   				this.printTopClientes(top10List);

	   				int out = 0;
	   				
	   				this.menusVIEW.printHighlight("\nPressione \"1\" para voltar para o menu de ADMIN.");

	   				while (true) {
	   					out = getIntegerInput("");
	   				
	   					if (out == 1) 
	   						break;
	   				}
	   			}

	   		} else if ((adminOp.length() == 1) && (adminOp.charAt(0) == '2')) {
	   		
	   				GestorFicheirosDados fileManager = new GestorFicheirosDados();

	   				fileManager.stats_iniciais(this.estadoMODEL);
	   				
	   				int out = 0;
	   				
	   				this.menusVIEW.printHighlight("\nPressione \"1\" para voltar para o menu de ADMIN.");

	   				while (true) {
	   					out = getIntegerInput("");
	   				
	   					if (out == 1) 
	   						break;
	   				}

	   		} else if (adminOp.charAt(0) == 'q') {

	   			this.menusVIEW.clearConsole();

	   			run();
	   			
	   			return;
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
			while (optionLogMenu != 'r' && optionLogMenu != 'l' && optionLogMenu != 's' && optionLogMenu != 'q' && optionLogMenu != 'a') {

				this.menusVIEW.printError("Please, insert a valid option...");				

				optionLogMenu = scanner.next().charAt(0);
			}

			GestorFicheirosDados gestor = new GestorFicheirosDados();

			if (optionLogMenu == 'a') {

				adminMenu();

				controllLogIn();
			}

			if (optionLogMenu == 's') {
				try {
					gestor.estadoParaObjectFile(this.estadoMODEL);
					this.menusVIEW.printHint("The app was saved successfully!");
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

				this.menusVIEW.printHighlight("\nAre you an \"Owner\" or \"Client?\" (Press: 1 -> Owner | 2 -> Client)");

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

    /**
     * Devolve a string lida do terminal
     *
     * @return String lida
    */

   	public String getStringInput () {

		Scanner scanner = new Scanner(System.in);

		String input = scanner.next();
   	
		return input;
   	}

    /**
     * Apreseta os carros disponiveis da lista de carros, de forma interativa.
	 *
	 * @param listV lista de veiculos a apresentar
     *
     * @return tamanho da lista argumento
    */

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

    /**
     * Apreseta os carros do Proprietario da lista de carros, de forma interativa.
	 *
	 * @param cars lista de veiculos a apresentar
     *
     * @return tamanho da lista argumento
    */

   	public int presentCarsOwner (List<Veiculo> cars) {

   		this.menusVIEW.printHighlight("\nThere's(re) " + cars.size() + " car(s) available: \n");
			
   		if (cars.size() == 0) {

   			this.menusVIEW.printError("Não existem veiculos ainda registados!");

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

    /**
     * Apreseta uma lista de alugueres entre 2 datas.
	 *
	 * @param listaAluguers lista de alugueres a apresentar
	 * @param antes data inferior
	 * @param depois data superior
     *
     * @return 0 caso tudo corra bem.
    */

   	public int printListBetweenDates (List<Aluguer> listaAluguers, LocalDate antes, LocalDate depois) {

   		if (listaAluguers.size() == 0) {
   			this.menusVIEW.printError("There are no rentals at the moment to display!");
   			return -1;
   		}

   		int i = 1;

   		for (Aluguer a: listaAluguers) {

   			if ((a.getData().isEqual(antes) || a.getData().isAfter(antes)) && 
   				(a.getData().isEqual(depois) || a.getData().isBefore(depois))) {

   				this.menusVIEW.printFaturaAlguer(a.getCliente(),
				            					 a.getProprietario(),
				            					 a.getVeiculo(),
				            					 a.getPreco(),
				            					 a.getDistancia(),
				            					 a.getData(), i,
				            					 this.estadoMODEL.getCliente(a.getCliente())
				            					 				 .getClassificacao());
   				i++;
   			}
   		}

   		return 0;
   	}

    /**
     * Apresenta a lista dos top 10 clientes com mais KM percorridos.
	 * Recorre, claramente, ao modelo para criar essa lista.
	 *
	 * @param topList lista de clientes a apresentar
     *
    */

	public void printTopClientes (List<Cliente> topList) {

		int rank = 1;

		for (Cliente c: topList) {

			this.menusVIEW.printIndividual(c.getNome(), 
				                           c.getTotalKmPercorridos(),
				                           rank);
			rank++;
		}

	}

    /**
     * Apresenta a faturacao de uma viatura.
	 * Recorre, claramente, ao modelo para criar essa faturacao.
	 *
	 * @param v Veiculo.
	 * @param fat Faturacao.
     *
    */

	public void presentFaturacaoViatura (Veiculo v, double fat) {

		this.menusVIEW.printFaturacaoVeiculo(v.getMatricula(),
											 v.getMarca(),
											 fat);
	}
}
