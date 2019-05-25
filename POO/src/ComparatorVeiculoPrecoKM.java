
import java.util.Comparator;

public class ComparatorVeiculoPrecoKM implements Comparator<Veiculo> {

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