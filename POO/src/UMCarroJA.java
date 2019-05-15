
//--------------------------------------------------

//Packages deste projeto

import ComponentesSistema.Veiculos.*;
import ComponentesSistema.AtorSistema.*;
import EstadoSistema.*;
import EstadoSistema.ExceptionsProgramFlow.*;
import BaseClasses.Localizacao;
import GestaoFicheiros.GestorFicheirosDados;
import GUInterfaces.*;

//--------------------------------------------------

//Packages JAVA

import java.time.LocalDate;

import java.util.Map;

import java.util.HashMap;
import java.util.ArrayList;

import java.io.IOException;
import java.io.FileNotFoundException;

//--------------------------------------------------

public class UMCarroJA {


    public static void main(String[] args) {
		
		//----------------------------------------------------------------------------------    	

    	//Guarda o estado da App

    	EstadoSistema estado = new EstadoSistema();
        GestorFicheirosDados initStatesGestor = new GestorFicheirosDados();
		
	    GUI_UMcarroJA novo = new GUI_UMcarroJA();

        novo.main_menu_view();

    	//----------------------------------------------------------------------------------    	

    	//Carregar o log inicial
/*
    	try {

    		System.out.println("\n> A fazer o carregamento inicial de logs...\nLoading...");

    		initStatesGestor.carregarEstadoFromLogs(estado, "Logs/log_simples.bak");

           // System.out.println(estado.getClientesSistema().toString());

    		System.out.println("> Sistema inicial carregado.\n");

    		initStatesGestor.stats_iniciais(estado);
    	}
    	
    	catch (IOException io_exc) {

    		System.out.println(io_exc.getMessage());    		
    	}

		//----------------------------------------------------------------------------------    	

    	//Gravar o estado num ficheiro objeto

    	try {

    		initStatesGestor.estadoParaObjectFile(estado);
  			
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
    				= new EstadoSistema(initStatesGestor.objectFileParaEstado());
    	
    		initStatesGestor.stats_iniciais(estado);
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
*/
    }

}