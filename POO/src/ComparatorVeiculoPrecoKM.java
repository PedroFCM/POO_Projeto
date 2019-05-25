//-------------------------------------------------------------------------------------------------------------

/**
 * Class que implementa um Comparator de veiculo por preco/KM
 *
 * @author João Pedro Rodrigues Azevedo
 * @author Pedro Filipe Costa Machado
 * @author Paulo Jorge da Silva Araújo 
 *
 * @version 2019/05/25
 */

//-------------------------------------------------------------------------------------------------------------

import java.util.Comparator;

//-------------------------------------------------------------------------------------------------------------

public class ComparatorVeiculoPrecoKM implements Comparator<Veiculo> {

    /**
     * Efetua a comparacao entre veiculo cujo criterio é o preco por Km
     *
     * @param c1 Veiculo 1.
     * @param c2 Veiculo 2.
     *
    */
    
    public int compare(Veiculo c1, Veiculo c2) {

    	int r = 1;

        if(c1.getPrecoPorKM() <= c2.getPrecoPorKM()) {
            r = 1;
        }
    
        if(c1.getPrecoPorKM() > c2.getPrecoPorKM()) {
            r = -1;
        }
    
    	return r;

        //return 0;
    
    }
}