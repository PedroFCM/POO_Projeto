
//----------------------------------------------------------------------------------        

import java.io.IOException;
import java.io.FileNotFoundException;

//----------------------------------------------------------------------------------        

public class Main {

	public static void main(String[] args) {
	
        //----------------------------------------------------------------------------------        

		EstadoSistema estadoApp = new EstadoSistema(); 
		GUI_UMcarroJA viewApp   = new GUI_UMcarroJA();
		ControladorAPP controller = new ControladorAPP(estadoApp, viewApp);

        //----------------------------------------------------------------------------------        

		try {

			viewApp.clearConsole();

	    	
			viewApp.printHint("\n\n.-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.\n");

	    	System.out.println("\n\t\t A carregar a aplicação UMCarroJA...");

			int i = controller.initLogs();
			
			if (i == 1)
				viewApp.printHint("\n\n\tPrimeira vez a usar a App? Ainda não fez nenhum save?");
			else 
				viewApp.printHint("\n\n\t\t A App já possui um registo de save!");

		    viewApp.presentation();
			
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