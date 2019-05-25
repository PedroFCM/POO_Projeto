//-------------------------------------------------------------------------------------------------------------

/**
 * Class que um Carro Hibrido, cuja super class é VeiculoComAutonomia. 
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

public class CarroHibrido extends VeiculoComAutonomia {

    /**
     * Construtor parameterizado da classe Carrohibrido
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
      public CarroHibrido (String nova_matricula,
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
     * Construtor copia da classe Carrohibrido
     *
     * @param carroHib novo Carro hibrido.
     *
    */

    public CarroHibrido (CarroHibrido carroHib) {

        super(carroHib);
   }

    /**
     * Construtor vazio da classe Carrohibrido
    */

    public CarroHibrido () {

        super();
    }

    //-------------------------------------------------------------------------------------------------------------

    /**
     * Determina se dois carros hibridos são iguais.
     *
     * @param o Objeto para fazer comparação.
     *
     * @return true se forem iguais.
    */

    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        CarroHibrido g = (CarroHibrido) o;
     
        return(super.equals(g));
    }
    /**
     * Constrói uma cópia de um Carrohibrido.
     *
     * @return Novo Carrohibrido.
    */

    public CarroHibrido clone () {

        return new CarroHibrido(this);
    }

    /**
     * Converte um Carrohibrido para a sua representação em String
     *
     * @return A representação do carro hibrido em String.
    */

    public String toString (){
        
        StringBuilder s = new StringBuilder();

        s.append("\n:: Tipo de Veiculo: Carro Hibrido,");
        s.append(super.toString());

        return s.toString();
    }

}