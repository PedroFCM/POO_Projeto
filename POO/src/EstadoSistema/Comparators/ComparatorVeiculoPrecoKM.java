
package EstadoSistema.Comparators;

import java.util.Comparator;

import ComponentesSistema.Veiculos.*;

public class ComparatorVeiculoPrecoKM implements Comparator<Veiculo> {

    public int compare(Veiculo c1, Veiculo c2) {

        if(c1.getPrecoPorKM() < c2.getPrecoPorKM()) {
            return 1;
        }
    
        if(c1.getPrecoPorKM() > c2.getPrecoPorKM()) {
            return -1;
        }
    
        return 0;
    
    }
}