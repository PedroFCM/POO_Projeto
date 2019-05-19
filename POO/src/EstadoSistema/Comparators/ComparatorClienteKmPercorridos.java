
package EstadoSistema.Comparators;

import java.util.Comparator;

import ComponentesSistema.AtorSistema.*;

public class ComparatorClienteKmPercorridos implements Comparator<Cliente> {

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