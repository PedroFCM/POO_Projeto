
package ComponentesSistema.Veiculos;

import BaseClasses.Localizacao;

import java.io.Serializable;

public class CarroEletrico extends VeiculoComAutonomia {

    public CarroEletrico (String nova_matricula,
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

    public CarroEletrico (CarroEletrico carroEle) {

        super(carroEle);
   }

    public CarroEletrico () {

        super();
    }

    //-------------------------------------------------------//  

    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        CarroEletrico g = (CarroEletrico) o;
     
        return(super.equals(g));
    }

    public CarroEletrico clone () {

        return new CarroEletrico(this);
    }

    public String toString (){
        
        StringBuilder s = new StringBuilder();

        s.append("\n=> Tipo de Veiculo: Carro Eletrico.\n");
        s.append(super.toString());

        return s.toString();
    }

    //-------------------------------------------------------//  

}