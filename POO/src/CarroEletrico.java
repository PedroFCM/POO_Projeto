//-------------------------------------------------------------------------------------------------------------

/**
 * Class que um Carro Eletrico, cuja super class é VeiculoComAutonomia. 
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

public class CarroEletrico extends VeiculoComAutonomia {


    /**
     * Construtor parameterizado da classe CarroEletrico
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
     * @param v Vezes que foi alugado
     *
    */

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
                          String marca,
                          int v) {

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
              v);
    }

    /**
     * Construtor copia da classe CarroEletrico
     *
     * @param carroEle novo Carro Eletrico.
     *
    */

    public CarroEletrico (CarroEletrico carroEle) {

        super(carroEle);
   }

    /**
     * Construtor vazio da classe CarroEletrico
    */

    public CarroEletrico () {

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
        
        CarroEletrico g = (CarroEletrico) o;
     
        return(super.equals(g));
    }
    
    /**
     * Constrói uma cópia de um CarroEletrico.
     *
     * @return Novo CarroEletrico.
    */

    public CarroEletrico clone () {

        return new CarroEletrico(this);
    }

    /**
     * Converte um CarroEletrico para a sua representação em String
     *
     * @return A representação do carro eletrico em String.
    */

    public String toString (){
        
        StringBuilder s = new StringBuilder();

        s.append("\n:: Tipo de Veiculo: Carro Eletrico.\n");
        s.append(super.toString());

        return s.toString();
    }

    //-------------------------------------------------------------------------------------------------------------

}