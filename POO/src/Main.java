
//--------------------------------------------------

//Packages deste projeto

import ComponentesSistema.Veiculos.*;
import ComponentesSistema.AtorSistema.*;
import EstadoSistema.*;
import EstadoSistema.ExceptionsProgramFlow.*;
import BaseClasses.Localizacao;

//--------------------------------------------------

//Packages JAVA

import java.time.LocalDate;

import java.util.Map;

import java.util.HashMap;
import java.util.ArrayList;

import java.io.IOException;
import java.io.FileNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

//--------------------------------------------------

public class Main {

	//--------------------------------------------------

	private static final String estadoObjetoPath = "ObjectSaves/estadoObj.obj";

	//--------------------------------------------------

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

    public static void main(String[] args) {
		
		//----------------------------------------------------------------------------------    	

    	//Guarda o estado da App

    	EstadoSistema estado = new EstadoSistema();
		
		//----------------------------------------------------------------------------------    	

    	//Carregar o log inicial

    	try {

    		System.out.println("\n> A fazer o carregamento inicial de logs...\nLoading...");

    		estado.carregarEstadoFromLogs(estado, "Logs/log_simples.bak");

            System.out.println(estado.getClientesSistema().toString());

    		System.out.println("> Sistema inicial carregado.\n");

    		stats_iniciais(estado);
    	}
    	
    	catch (IOException io_exc) {

    		System.out.println(io_exc.getMessage());    		
    	}

		//----------------------------------------------------------------------------------    	

    	//Gravar o estado num ficheiro objeto

    	try {

    		estadoParaObjectFile(estado);
  			
  		}

    	catch (FileNotFoundException e) {

    		System.out.println("Oops! Get a FileNotFoundException");
    	}

    	catch (IOException e) {

    		System.out.println("Oops! Got an IOException");
    	}

		//----------------------------------------------------------------------------------    	

    	//Gravar o estado num ficheiro objeto

    	try {

        		EstadoSistema carregadoEstado 
    				= new EstadoSistema(objectFileParaEstado());
    	
    		stats_iniciais(estado);
    	}

    	catch (FileNotFoundException e) {

    		System.out.println("Oops! Get a FileNotFoundException");
    	}

    	catch (ClassNotFoundException e) {

    		System.out.println("Oops! Get a ClassNotFoundException");    		
    	}

    	catch (IOException e) {

    		System.out.println("Oops! Got an IOException");
    	}

    }

}