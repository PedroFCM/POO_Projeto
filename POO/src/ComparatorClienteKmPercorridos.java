//-------------------------------------------------------------------------------------------------------------

/**
 * Class que implementa um Comparator de clientes por KM percorridos 
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

public class ComparatorClienteKmPercorridos implements Comparator<Cliente> {

    /**
     * Efetua a comparacao entre clientes cujo criterio é o numer total de km percorridos
     *
     * @param c1 Cliente 1.
     * @param c2 Cliente 2.
     *
    */

    public int compare(Cliente c1, Cliente c2) {

        if(c1.getTotalKmPercorridos() < c2.getTotalKmPercorridos()) {
            return 1;
        }
    
        if(c1.getTotalKmPercorridos() > c2.getTotalKmPercorridos()) {
            return -1;
        }
    
        return 0;
    
    }
}