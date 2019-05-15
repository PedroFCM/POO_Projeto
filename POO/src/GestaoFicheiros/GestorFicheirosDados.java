
package GestaoFicheiros;

import ComponentesSistema.Veiculos.*;
import ComponentesSistema.AtorSistema.*;
import EstadoSistema.*;
import EstadoSistema.ExceptionsProgramFlow.*;
import BaseClasses.Localizacao;
import BaseClasses.Aluguer;

import java.io.IOException;
import EstadoSistema.ExceptionsProgramFlow.*;

import java.io.BufferedWriter;
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.FileWriter;

import java.util.StringTokenizer;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.ArrayList;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.io.IOException;
import java.io.FileNotFoundException;

public class GestorFicheirosDados {


	//--------------------------------------------------

	private static final String estadoObjetoPath = "ObjectSaves/estadoObj.obj";

	//--------------------------------------------------

	//--------------------------------------------------

	public GestorFicheirosDados () {
		//...
	}

	//--------------------------------------------------

    public static void carregarEstadoFromLogs (EstadoSistema estado, String path_logs) 
                          throws IOException {

	    //--------------------------------------------------

	    String linha = null, novoComponente = null;
	    
	    StringTokenizer tokens = null;

	    String[] camposProp = new String[5];
	    String[] camposClie = new String[6];
	    String[] camposCarr = new String[11];
	    String[] camposAlug = new String[5];
	    String[] camposClas = new String[3];

	    //--------------------------------------------------
	    
	    BufferedReader inStream = new BufferedReader(new FileReader(path_logs));

	    while ((linha = inStream.readLine()) != null) {
	        
	      tokens = new StringTokenizer(linha);

	      novoComponente = tokens.nextToken(":");

	      switch(novoComponente) {

	    
	      //-----------------------------------------------------------------------------------
	        
	        case "NovoProp": 

	          for (int i = 0; tokens.hasMoreTokens(); i++)
	            camposProp[i] = tokens.nextToken(","); 

	          AtorSistema novoProp = new Proprietario(camposProp[2],
	                                camposProp[0].replaceAll(":", ""),
	                                camposProp[2],
	                                camposProp[3],
	                                LocalDate.now(),
	                                0,
	                                new ArrayList<>(),
	                                new HashMap<>(),
	                                camposProp[1]);

	          estado.adicionaProprietario((Proprietario) novoProp);

	          break;

	      //-----------------------------------------------------------------------------------

	        case "NovoCliente":

	          for (int i = 0; tokens.hasMoreTokens(); i++)
	            camposClie[i] = tokens.nextToken(","); 

	          AtorSistema novoClie = new Cliente (camposClie[2],
	                              camposClie[0].replaceAll(":", ""),
	                              camposClie[2],
	                              camposClie[3],
	                              LocalDate.now(),
	                              new Localizacao(Double.parseDouble(camposClie[4]), 
	                                    Double.parseDouble(camposClie[5])),
	                            new ArrayList<>(),
	                            0,
	                            camposClie[1]);

	          estado.adicionaCliente ((Cliente) novoClie);


	          break;

	      //-----------------------------------------------------------------------------------

	        case "NovoCarro":

	          for (int i = 0; tokens.hasMoreTokens(); i++)
	            camposCarr[i] = tokens.nextToken(","); 

	          String tipoDeCarro = camposCarr[0].replaceAll(":", "");
	    
	          switch (tipoDeCarro) {

	            case "Electrico":
	                            
	              VeiculoComAutonomia eletrico 
	                = new CarroEletrico(camposCarr[2],
	                            Double.parseDouble(camposCarr[4]),
	                            Double.parseDouble(camposCarr[5]),
	                            0,
	                            new Localizacao(Double.parseDouble(camposCarr[8]),
	                                      Double.parseDouble(camposCarr[9])),
	                            Double.parseDouble(camposCarr[6]),
	                            100,
	                            Double.parseDouble(camposCarr[7]),
	                            "?",
	                            true,
	                            camposCarr[1]);

	              if (estado.existeProprietario(camposCarr[3])) {

	                estado.getProprietario(camposCarr[3])
	                      .adicionaVeiculo(eletrico);

	                estado.adicionaVeiculoSistema(eletrico);
	              }

	              break;

	            case "Gasolina": 

	              VeiculoComAutonomia gasolina 
	                = new CarroGasolina(camposCarr[2],
	                            Double.parseDouble(camposCarr[4]),
	                            Double.parseDouble(camposCarr[5]),
	                            0,
	                            new Localizacao(Double.parseDouble(camposCarr[8]),
	                                      Double.parseDouble(camposCarr[9])),
	                            Double.parseDouble(camposCarr[6]),
	                            100,
	                            Double.parseDouble(camposCarr[7]),
	                            "?",
	                            true,
	                            camposCarr[1]);

	              if (estado.existeProprietario(camposCarr[3])) {

	                estado.getProprietario(camposCarr[3])
	                      .adicionaVeiculo(gasolina);

	                estado.adicionaVeiculoSistema(gasolina);
	              }

	              break;

	            case "Hibrido":

	              VeiculoComAutonomia hibrido 
	                = new CarroHibrido(camposCarr[2],
	                            Double.parseDouble(camposCarr[4]),
	                            Double.parseDouble(camposCarr[5]),
	                            0,
	                            new Localizacao(Double.parseDouble(camposCarr[8]),
	                                      Double.parseDouble(camposCarr[9])),
	                            Double.parseDouble(camposCarr[6]),
	                            100,
	                            Double.parseDouble(camposCarr[7]),
	                            "?",
	                            true,
	                            camposCarr[1]);

	              if (estado.existeProprietario(camposCarr[3])) {

	                estado.getProprietario(camposCarr[3])
	                      .adicionaVeiculo(hibrido);

	                estado.adicionaVeiculoSistema(hibrido);
	              }

	              break;
	    
	            default: break;

	          }

	          break;

	      //-----------------------------------------------------------------------------------

	      case "Aluguer": 

	          //-----------------------------------------------------------------------------------

	          for (int i = 0; tokens.hasMoreTokens(); i++)
	            camposAlug[i] = tokens.nextToken(","); 

	          //-----------------------------------------------------------------------------------

	          String tipoCarroAlugado = camposAlug[3];
	          String cli_nif = camposAlug[0].replaceAll(":", "");
	          Cliente cli_aluguer = estado.getCliente(cli_nif);
	          String tipoCombustivel = "Carro" + camposAlug[3];

	          if (tipoCombustivel.equals("CarroElectrico")) {

	            tipoCombustivel = tipoCombustivel.replaceAll("Electrico", 
	                                                         "Eletrico");
	          }

	          //-----------------------------------------------------------------------------------

	          if (cli_aluguer == null) 
	            continue;

	          Localizacao destino = new Localizacao(Double.parseDouble(camposAlug[1]),
	                                                Double.parseDouble(camposAlug[2]));

	          double distancia = cli_aluguer.getLocalizacao().distancia(destino);

	          //-----------------------------------------------------------------------------------

	          switch (camposAlug[4]) {

	            case "MaisPerto": 
	              
	              Veiculo novoMP = estado.carroMaisProximo(cli_aluguer, tipoCombustivel)
	                                     .clone();

	              if (novoMP == null) continue;

	              Aluguer novoAluguerMP = new Aluguer (novoMP.getMatricula(),
	                                                   cli_nif,
	                                                   novoMP.getProprietario(),
	                                                   distancia,
	                                                   novoMP.getPrecoPorKM() * distancia,
	                                                   LocalDate.now());

	              cli_aluguer.adicionaAluguer(novoAluguerMP);

	              break;

	            case "MaisBarato": 

	              Veiculo novoMB = estado.carroMaisBarato(tipoCombustivel)
	                                     .clone();

	              if (novoMB == null) continue;

	              Aluguer novoAluguerMB = new Aluguer (novoMB.getMatricula(),
	                                                   cli_nif,
	                                                   novoMB.getProprietario(),
	                                                   distancia,
	                                                   novoMB.getPrecoPorKM() * distancia,
	                                                   LocalDate.now());

	              cli_aluguer.adicionaAluguer(novoAluguerMB);                

	              break;

	            default: break;
	          }

	        break;

	      //-----------------------------------------------------------------------------------

	        case "Classificar":

	          //-----------------------------------------------------------------------------------

	          for (int i = 0; tokens.hasMoreTokens(); i++)
	            camposClas[i] = tokens.nextToken(","); 

	          //-----------------------------------------------------------------------------------

	          camposClas[0] = camposClas[0].replaceAll(":", "");

	          String itemParaClassificar = "";

	          if (isStringMatricula(camposClas[0]))
	            itemParaClassificar = "Matricula";
	          
	          if (isStringNif(camposClas[0]))
	            itemParaClassificar = "Nif";
	    
	          switch (itemParaClassificar) {

	            case "Matricula":
	                            
	              if (estado.existeMatricula(camposClas[0])) {

	                estado.getVeiculo(camposClas[0])
	                      .setClassificacao(Integer.parseInt(camposClas[1]));

	              }

	              break;

	            case "Nif":

	              if (estado.existeCliente(camposClas[0])) {

	                estado.getCliente(camposClas[0])
	                      .setClassificacao(Integer.parseInt(camposClas[1]));

	              } else 

	              if (estado.existeProprietario(camposClas[0])) {

	                estado.getProprietario(camposClas[0])
	                      .setClassificacao(Integer.parseInt(camposClas[1]));                  

	              }

	              break;

	              default: break;
	          }

	        default: break;

	      //-----------------------------------------------------------------------------------

	      }
	    }
    }

    public static boolean isStringMatricula (String possibleMatricula) {

      //Matricula => JI-75-97

      boolean result = true;

      result = result && (possibleMatricula.charAt(2) == '-') 
                      && (possibleMatricula.charAt(5) == '-');

      result = result && Character.isLetter(possibleMatricula.charAt(0))
                      && Character.isLetter(possibleMatricula.charAt(1));

      try {

        Integer.parseInt(possibleMatricula.substring(3, 4));
        Integer.parseInt(possibleMatricula.substring(6, 7));

      }

      catch (NumberFormatException e) {

        result = false;
      }

      return result;
    }

    public static boolean isStringNif (String possibleNif) {

      boolean result;

      long numberInString;

      try {

        numberInString = Long.parseLong(possibleNif);
        result = true;
      }

      catch (NumberFormatException e) {

          result = false;
      }

      return result;
    }

    public static void stats_iniciais(EstadoSistema e) {

		System.out.println ("=> STATS pós carregamento: \n");

		System.out.println ("Existem " + e.getNumProprietarios() + " Proprietarios.");
		System.out.println ("Existem " + e.getNumClientes() + " Clientes.");
		System.out.println ("Existem " + e.getNumVeiculos() + " Veiculos.");

	}

	public static void estadoParaObjectFile(EstadoSistema estado) 
					throws FileNotFoundException, IOException {

		FileOutputStream fileOut = new FileOutputStream(estadoObjetoPath);
	    
	    ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
	    
	    objectOut.writeObject(estado);

	    objectOut.close();
	    fileOut.close();
	    
 	}

 	public static EstadoSistema objectFileParaEstado () 
 								throws  FileNotFoundException, 
 										IOException, 
 										ClassNotFoundException {

 		FileInputStream fileInput = new FileInputStream(estadoObjetoPath);
		ObjectInputStream objectIn = new ObjectInputStream(fileInput);
 		
 		EstadoSistema novo = new EstadoSistema((EstadoSistema) objectIn.readObject());

 		objectIn.close();
 		fileInput.close();

 		return novo;
 	}

}