//-------------------------------------------------------------------------------------------------------------

/**
 * Class que implementa um objeto Aluguer usado por Atores do Sistema.
 *
 * @author João Pedro Rodrigues Azevedo
 * @author Pedro Filipe Costa Machado
 * @author Paulo Jorge da Silva Araújo 
 *
 * @version 2019/05/25
 */

//-------------------------------------------------------------------------------------------------------------

import java.time.LocalDate;
import java.util.List;
import java.io.Serializable;

//-------------------------------------------------------------------------------------------------------------

public class Aluguer implements Serializable {

	/*
	* Matricula do Veiculo
	*/
    private String veiculo;
	/*
	* Nif do cliente
	*/
    private String cliente;
	/*
	* Nif do proprietario
	*/
    private String proprietario;
	/*
	* Distancia da Viagem
	*/
    private double distancia;
	/*
	* Preco por km da viatura
	*/
    private double preco;
	/*
	* Data de aluguer
	*/
    private LocalDate data;
	/*
	* Localizacao do destino da viagem
	*/
    private Localizacao destinoViagem;
    
    /**
	 * Construtor parameterizado da classe Aluguer
	 *
	 * @param veiculo Matricula do veiculo.
	 * @param cliente Nif do cliente.
	 * @param proprietario Nif do proprietario.
	 * @param distancia Distancia da viagem.
	 * @param preco preco por km do veiculo.	 	 	 
	 * @param dest Localizacao do destino da viagem.
	 *
	*/

    public Aluguer(String veiculo, String cliente, String proprietario, double distancia, double preco, LocalDate data, Localizacao dest) {    
       
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.proprietario = proprietario;
        this.distancia = distancia;
        this.preco = preco;
        this.data = data;
        this.destinoViagem = dest.clone();
    }
    
    /**
	 * Construtor cópia de um Aluguer
	 *	 	 	 
	 * @param umAluguer Aluguer para cópia.
	 *
	*/

    public Aluguer(Aluguer umAluguer) {

        this.veiculo = umAluguer.getVeiculo();
        this.cliente = umAluguer.getCliente();
        this.proprietario = umAluguer.getProprietario();
        this.distancia = umAluguer.getDistancia();
        this.preco = umAluguer.getPreco();
        this.data = umAluguer.getData();
        this.destinoViagem = umAluguer.getDestino();
    }

    /**
	 * Construtor vazio de um Aluguer
	 *
	*/    
    
    public Aluguer () {
        
        this.veiculo = "Not initialized";
        this.cliente = "Not initialized";
        this.proprietario = "Not initialized";
        this.preco = 0.0;
        this.distancia = 0.0;
        this.data = LocalDate.now();
        this.destinoViagem = new Localizacao();
    }

 	//--------------------------------------------------------------------------

    /**
	 * Converte um Aluguer para a sua representação em String
	 *
	 * @return A representação do aluguer em String.
	*/

    public String toString() {

        StringBuffer sb = new StringBuffer();
        
        sb.append('\n' + "Cliente = "+ this.getCliente() + '\n');
        sb.append("Proprietario = "+ this.getProprietario() + '\n');
        sb.append("Veiculo = "+ this.getVeiculo() + '\n');
        sb.append("Preco = "+ this.getPreco() + '\n');
        sb.append("Distancia = "+ this.getDistancia() + '\n');
        sb.append("Data = "+ this.getData().toString() + '\n'); 
        
        return sb.toString();
        
    }
    
    /**
	 * Constrói uma cópia de um Aluguer
	 *
	 * @return Um aluguer novo.
	*/

    public Aluguer clone() {

        return new Aluguer(this);
    }

    /**
	 * Determina se dois alugueres são iguais.
	 *
	 * @param o Objeto para fazer comparação.
	 *
	 * @return true se forem iguais.
	*/

    public boolean equals(Object o) {

        if(this == o)
            return true;
        
        if((o == null) || (this.getClass() != o.getClass()))
          return false;
          
        Aluguer umAluguer = (Aluguer) o;
        return (this.cliente.equals(umAluguer.getCliente()) && this.proprietario.equals(umAluguer.getProprietario()) 
            && this.veiculo.equals(umAluguer.getVeiculo()) && this.preco == umAluguer.getPreco() && this.distancia == umAluguer.getDistancia() 
            && this.data.equals(umAluguer.getData()) ); 
    }

 	//--------------------------------------------------------------------------

    /**
	 * Calcula o preço de uma viagem (aluguer).
	 *
	 * @return preco da viagem.
	*/

    public double precoViagem(){
        
        return (this.preco * this.distancia);
    }
   

    /**
	 * Devolve o nif do cliente no aluguer.
	 *
	 * @return a string nif.
	*/

	public String getCliente() {

        return this.cliente;
    }
    
    /**
	 * Devolve o nif do proprietario do aluguer.
	 *
	 * @return a string nif.
	*/

    public String getProprietario() {

        return this.proprietario;
    }
    
    /**
	 * Devolve a matricula do veiculo do aluguer.
	 *
	 * @return a string matricula.
	*/

    public String getVeiculo() {

        return this.veiculo;
    }
    
    /**
	 * Devolve o preco por km de um veiculo.
	 *
	 * @return preco por km.
	*/

    public double getPreco() {

        return this.preco;
    }
    
    /**
	 * Devolve a distancia percorrida na viagem.
	 *
	 * @return distancia.
	*/

    public double getDistancia() {

        return this.distancia;
    }
    
    /**
	 * Devolve a data de aluguer.
	 *
	 * @return data de aluguer.
	*/

	public LocalDate getData() {

        return this.data;
    }

    /**
	 * Devolve a Localizacao destino da viagem.
	 *
	 * @return destino.
	*/

    public Localizacao getDestino() {

        return this.destinoViagem.clone();
    }

    /**
	 * Altera a variavel de instancia cliente.
	 *
	 * @param cliente Nif do cliente
	 *
	*/

    public void setCliente(String cliente) {

        this.cliente = cliente;
    }
    
    /**
	 * Altera a variavel de instancia proprietario.
	 *
	 * @param proprietario Nif do proprietario
	 *
	*/

    public void setProprietario(String proprietario) {

        this.proprietario = proprietario;
    }

    /**
	 * Altera a variavel de instancia veiculo.
	 *
	 * @param veiculo Nif do veiculo
	 *
	*/
    
    public void setVeiculo(String veiculo) {

        this.veiculo = veiculo;
    }

    /**
	 * Altera a variavel de instancia preco.
	 *
	 * @param preco Nif do preco
	 *
	*/
    
    public void setPreco(double preco) {

        this.preco = preco;
    }

    /**
	 * Altera a variavel de instancia distancia.
	 *
	 * @param distancia Nif do distancia
	 *
	*/
    
    public void setDistancia(double distancia) {

        this.distancia = distancia;
    }

    /**
	 * Altera a variavel de instancia data.
	 *
	 * @param data Nif do data
	 *
	*/
    
    public void setData(LocalDate data) {

        this.data = data;
    }
}