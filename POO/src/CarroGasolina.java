//-------------------------------------------------------------------------------------------------------------

/**
 * Class que um Carro gasolina, cuja super class é VeiculoComAutonomia. 
 *
 * @author João Pedro Rodrigues Azevedo
 * @author Pedro Filipe Costa Machado
 * @author Paulo Jorge da Silva Araújo 
 *
 * @version 2019/05/25
 */

//-------------------------------------------------------------------------------------------------------------

import java.io.Serializable;

//-------------------------------------------------------------------------------------------------------------

public class CarroGasolina extends VeiculoComAutonomia {

    /**
     * Construtor parameterizado da classe gasolina
     *
     * @param nova_matricula Matricula do veiculo.
     * @param velMedia Velocidade Media do Veiculo
     * @param priceKM Preco por km do veiculo
     * @param classiF Classificacao do veiculo
     * @param nova_local Localizacao do veiculo             
     * @param consumo Consumo do veiculo
     * @param maxAuto Autonomia maxima do veiculo
     * @param currentAuto Autonomia atual do veiculo
     * @param prop Proprietario do veiculo (Nif)
     * @param estado Disponivel = true
     * @param marca Marca do veiculo
     * @param vezesA Vezes que foi alugado
     *
    */

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
                          String marca,
                          int vezesA) {

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
              estado,
              vezesA);
    }

    /**
     * Construtor copia da classe gasolina
     *
     * @param carroGas novo Carro Eletrico.
     *
    */

    public CarroGasolina (CarroGasolina carroGas) {

        super(carroGas);
   }

    /**
     * Construtor vazio da classe gasolina
    */
    
    public CarroGasolina () {

        super();
    }

    //-------------------------------------------------------------------------------------------------------------

    /**
     * Determina se dois carros eletricos são iguais.
     *
     * @param o Objeto para fazer comparação.
     *
     * @return true se forem iguais.
    */

    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        CarroGasolina g = (CarroGasolina) o;
     
        return(super.equals(g));
    }

    /**
     * Constrói uma cópia de um gasolina.
     *
     * @return Novo gasolina.
    */

    public CarroGasolina clone () {

        return new CarroGasolina(this);
    }

    /**
     * Converte um gasolina para a sua representação em String
     *
     * @return A representação do carro eletrico em String.
    */

    public String toString (){
        
        StringBuilder s = new StringBuilder();

        s.append("\n:: Tipo de Veiculo: Carro a gasolina.\n");
        s.append(super.toString());

        return s.toString();
    }

    //-------------------------------------------------------//  

}