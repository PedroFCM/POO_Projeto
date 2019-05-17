
//----------------------------------------------------------------------------------        

import APP_Controller.*;
import GUInterfaces.*;
import EstadoSistema.*;

//----------------------------------------------------------------------------------        

import java.io.IOException;
import java.io.FileNotFoundException;

//----------------------------------------------------------------------------------        

public class Main {

	public static void main(String[] args) {
	
        //----------------------------------------------------------------------------------        

		EstadoSistema estadoApp = new EstadoSistema(); 
		GUI_UMcarroJA viewApp   = new GUI_UMcarroJA();
		UMCarroJA controller    = new UMCarroJA(estadoApp, viewApp);

		try {

			viewApp.clearConsole();

	    	
			viewApp.printHint("\n\n.-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.\n");

	    	System.out.println("\n\t\t A carregar a aplicação UMCarroJA...");

			controller.initLogs();
	
	    	System.out.println("\n\n\t    >> Sistema inicial carregado com sucesso. <<\n");
	
		    GUI_UMcarroJA gui = new GUI_UMcarroJA(); 

		    gui.presentation();
	
			controller.run();
		}

	   	catch (FileNotFoundException fnf_exc) {
    	
    		System.out.println("error> Ficheiro de carregamento inicial não existente...");    		
    	}

    	catch (IOException io_exc) {
    	
    		System.out.println("error> A aplicação não foi carregada com sucesso...");    		
		}

        //----------------------------------------------------------------------------------        
	}

}