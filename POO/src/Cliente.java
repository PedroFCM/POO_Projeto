//-------------------------------------------------------------------------------------------------------------

/**
 * Class que implementa um Cliente, cuja superclass é AtorSistema. 
 *
 * @author João Pedro Rodrigues Azevedo
 * @author Pedro Filipe Costa Machado
 * @author Paulo Jorge da Silva Araújo 
 *
 * @version 2019/05/25
 */

//-------------------------------------------------------------------------------------------------------------

import java.util.Comparator;
import java.time.LocalDate;
import java.lang.StringBuilder;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import java.io.Serializable;

//-------------------------------------------------------------------------------------------------------------

public class Cliente extends AtorSistema implements Comparable<Cliente> {

    //-------------------------------------------------------------------------------------------------------------

    /*
     * Localizacao de um cliente
    */
    private Localizacao coordenadas;
    /*
     * Lista de Alugueres nao classificados do cliente
    */
    private List<Aluguer> naoClassificado;

    //-------------------------------------------------------------------------------------------------------------

    /**
     * Construtor parameterizado da classe CarroEletrico
     *
     * @param email_cliente Email do cliente.
     * @param nome_cliente Nome do cliente.
     * @param pass_cliente Password do cliente
     * @param morada_cliente Morada do cliente
     * @param dataNasc_cliente Data de criação do cliente
     * @param local_cliente Localizacao do cliente             
     * @param historico Historico de Alugueres do cliente
     * @param classif Classificacao do cliente
     * @param nif Nif do cliente
     * @param naoClass Lista de Alugueres nao classificados
     *
    */

    public Cliente (String email_cliente, 
                    String nome_cliente, 
                    String pass_cliente, 
                    String morada_cliente, 
                    LocalDate dataNasc_cliente, 
                    Localizacao local_cliente,
                    List<Aluguer> historico,
                    int classif,
                    String nif,
                    List<Aluguer> naoClass) {
        
        super(email_cliente, nome_cliente, 
              pass_cliente, morada_cliente, 
              dataNasc_cliente, classif, historico, nif);

        this.naoClassificado = naoClass.stream().map(Aluguer::clone).collect(Collectors.toList());
        this.coordenadas = new Localizacao(local_cliente);
    }

    /**
     * Construtor copia da classe Cliente
     *
     * @param novo novo Cliente.
     *
    */
    
    public Cliente (Cliente novo) {

        super(novo);
        this.coordenadas = new Localizacao(novo.getLocalizacao());
        this.naoClassificado = novo.getNaoClassificados();
    }

    /**
     * Construtor vazio da classe Cliente
    */
    
    public Cliente () {

        super();
        this.coordenadas = new Localizacao();
        this.naoClassificado = new ArrayList<>();
    }

    //------------------------------------------------------------------------------------------------------  

    /**
     * Determina se dois clientes são iguais.
     *
     * @param o Objeto para fazer comparação.
     *
     * @return true se forem iguais.
    */

    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        Cliente c = (Cliente) o;
     
        return (super.equals(c) && 
                this.coordenadas.equals(c.getLocalizacao()));
    }

    /**
     * Constrói uma cópia de um cliente.
     *
     * @return Novo Cliente.
    */

    public Cliente clone () {

        return new Cliente(this);
    }

    /**
     * Converte um Cliente para a sua representação em String
     *
     * @return A representação do Cliente em String.
    */

    public String toString() {
   
        StringBuilder s = new StringBuilder();

        s.append("\nCliente do sistema:");
        s.append(super.toString());
        s.append(this.coordenadas.toString());

        return s.toString();
    }

    //-------------------------------------------------------------------------------------------------------  

    /**
     * Devolve a Lista de alugueres nao classificados de um cliente
     *
     * @return Historico de alugueres nao classificados.
    */

    public List<Aluguer> getNaoClassificados () {

        return this.naoClassificado.stream().collect(Collectors.toList());
    }

    /**
     * Adiciona um aluguer a lista de alugueres nao classificados
     *
     * @param a Aluguer novo
     *
    */

    public void adicionaAluguerNaoClassificao(Aluguer a) {

        this.naoClassificado.add(a.clone());
    }

    /**
     * Remove um aluguer a lista de alugueres nao classificados
     *
     * @param a Aluguer novo
     *
    */

    public void removeAluguerNaoClassificao(Aluguer a) {

        this.naoClassificado.remove(a);
    }

    /**
     * Devolve a localizacao de um cliente
     *
     * @return Localizacao do cliente
     *
    */

    public Localizacao getLocalizacao() {
        
        return this.coordenadas.clone();
    }

    /**
     * Altera a localizacao do cliente
     *
     * @param local nova localizacao
     *
    */

    public void setLocalizacao (Localizacao local){
        
        this.coordenadas = local;
    }

    /**
     * Devolve o numero de km percorridos por um cliente
     *
     * @return Numero total de km percorridos
     *
    */

    public Double getTotalKmPercorridos() {

        Double total = 0.0;

        total = this.getHistoricoAlugueres().stream()
                                            .mapToDouble(Aluguer::getDistancia)
                                            .sum();
        return total;
    }
 
    /**
     * Faz a comparacao de clientes pela ordem natural, neste caso, pelo numero de km total percorridos
     *
     * @param a Aluguer novo
     *
    */

    public int compareTo(Cliente c) {
        
        return c.getTotalKmPercorridos().compareTo(this.getTotalKmPercorridos());
    }
}