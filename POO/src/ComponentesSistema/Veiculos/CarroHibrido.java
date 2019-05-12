
package ComponentesSistema.Veiculos;

import BaseClasses.Localizacao;

import java.io.Serializable;

public class CarroHibrido extends VeiculoComAutonomia {

    public CarroHibrido (String nova_matricula,
                          double velMedia,
                          double priceKM,
                          double classiF,
                          Localizacao nova_local,
                          double consumo,
                          double maxAuto,
                          double currentAuto,
                          String prop,
                          boolean estado,
                          String marca) {

        super(marca,
              nova_matricula, 
              velMedia, 
              priceKM, 
              classiF, 
              nova_local,
              consumo,
              maxAuto,
              currentAuto, 
              prop, 
              estado);
    }

    public CarroHibrido (CarroHibrido carroGas) {

        super(carroGas);
   }

    public CarroHibrido () {

        super();
    }

    //-------------------------------------------------------//  

    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        CarroHibrido g = (CarroHibrido) o;
     
        return(super.equals(g));
    }

    public CarroHibrido clone () {

        return new CarroHibrido(this);
    }

    public String toString (){
        
        StringBuilder s = new StringBuilder();

        s.append("\n=> Tipo de Veiculo: Carro Hibrido.\n");
        s.append(super.toString());

        return s.toString();
    }

    //-------------------------------------------------------//  

}