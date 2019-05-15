
package ComponentesSistema.Veiculos;

import BaseClasses.Localizacao;

import java.io.Serializable;

public class CarroGasolina extends VeiculoComAutonomia {

    public CarroGasolina (String nova_matricula,
                          double velMedia,
                          double priceKM,
                          int classiF,
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

    public CarroGasolina (CarroGasolina carroGas) {

        super(carroGas);
   }

    public CarroGasolina () {

        super();
    }

    //-------------------------------------------------------//  

    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        CarroGasolina g = (CarroGasolina) o;
     
        return(super.equals(g));
    }

    public CarroGasolina clone () {

        return new CarroGasolina(this);
    }

    public String toString (){
        
        StringBuilder s = new StringBuilder();

        s.append("\n=> Tipo de Veiculo: Carro a gasolina.\n");
        s.append(super.toString());

        return s.toString();
    }

    //-------------------------------------------------------//  

}