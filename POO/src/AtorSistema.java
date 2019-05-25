//-------------------------------------------------------------------------------------------------------------

/**
 * Class que implementa um User deste projeto (Ator do Sistema).
 * Esta classe é super classe de Clientes e Proprietarios.
 *
 * @author João Pedro Rodrigues Azevedo
 * @author Pedro Filipe Costa Machado
 * @author Paulo Jorge da Silva Araújo 
 *
 * @version 2019/05/25
 */

//-------------------------------------------------------------------------------------------------------------

import java.time.LocalDate;
import java.time.Month;
import java.lang.StringBuilder;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import java.io.Serializable;

//-------------------------------------------------------------------------------------------------------------

public class AtorSistema implements Serializable {

    //-------------------------------------------------------------------------------------------------------------

    /*
    * Email do utilizador
    */
    private String email;
    /*
    * Nome do utilizador
    */
    private String nome;
    /*
    * Password do utilizador
    */
    private String password;
    /*
    * Morada do utilizador
    */
    private String morada;
    /*
    * Data de criação do utilizador
    */
    private LocalDate dataDeNascimento;
    /*
    * Classificacao do utilizador
    */
    private int classificacao;
    /*
    * Lista de Alugueres do utilizador
    */
    private List<Aluguer> historico_alugueres;
    /*
    * Nif do utilizador
    */
    private String nif;

    //-------------------------------------------------------------------------------------------------------------

    /**
     * Construtor parameterizado da classe AtorSistema
     *
     * @param novo_email Email do utilizador.
     * @param novo_nome Nome do utilizador.
     * @param novo_pass Password do utilizador.
     * @param nova_morada Morada do utilizador.
     * @param nova_dataNasc Data de criação do user.             
     * @param classif Classificacao do utilizador.
     * @param historico Historico de Alugueres.
     * @param nif_as Nif do utilizador
     *
    */

    public AtorSistema (String novo_email, 
                        String novo_nome, 
                        String nova_pass, 
                        String nova_morada, 
                        LocalDate nova_dataNasc,
                        int classif,
                        List<Aluguer> historico,
                        String nif_as) {
        
        this.email               = novo_email;
        this.nome                = novo_nome;
        this.password            = nova_pass;
        this.morada              = nova_morada;
        this.dataDeNascimento    = nova_dataNasc;
        this.classificacao       = classif;
        this.historico_alugueres = historico.stream()
                                            .map(Aluguer::clone)
                                            .collect(Collectors.toList());
        this.nif = nif_as;
    }

    /**
     * Construtor cópia de um Aluguer
     *           
     * @param novo Ator de Sistema novo.
     *
    */

    public AtorSistema (AtorSistema novo) {

        this.email    = novo.getEmail();
        this.nome     = novo.getNome();
        this.password = novo.getPassword();
        this.morada   = novo.getMorada();
        this.dataDeNascimento = novo.getDataDeNascimento();
        this.classificacao = novo.getClassificacao();
        this.historico_alugueres = novo.getHistoricoAlugueres();
        this.nif = novo.getNif();
    }

    /**
     * Construtor vazio de Aluguer
     *
    */

    public AtorSistema () {

        this.email            = "n/a";
        this.nome             = "n/a";
        this.password         = "n/a";
        this.morada           = "n/a";
        this.dataDeNascimento = LocalDate.now();
        this.classificacao    = 0;
        this.historico_alugueres = new ArrayList<Aluguer>();
        this.nif = "n/a";
    }

    //-------------------------------------------------------------------------------------------------------------

    /**
     * Adiciona um Aluguer ao historico de alugueres do user.
     *
     * @param novo Novo aluguer.
     *
    */

    public void adicionaAluguer (Aluguer novo) {

        this.historico_alugueres.add(novo.clone());
    }

    //-------------------------------------------------------------------------------------------------------------

    /**
     * Determina se dois alugueres são iguais.
     *
     * @param o Objeto para fazer comparação.
     *
     * @return true se forem iguais.
    */

    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        AtorSistema c = (AtorSistema) o;

        return(c.getClassificacao() == this.classificacao             && 
               c.getEmail().equals(this.email)                        && 
               c.getNome().equals(this.nome)                          &&
               c.getPassword().equals(this.password)                  &&
               c.getMorada().equals(this.morada)                      &&
               c.getDataDeNascimento().equals(this.dataDeNascimento));
    }

    /**
     * Constrói uma cópia de um AtorSistema.
     *
     * @return Novo Ator de Sistema.
    */

    public AtorSistema clone () {

        return new AtorSistema(this);
    }

    /**
     * Converte um AtorSistema para a sua representação em String
     *
     * @return A representação do ator de sistema em String.
    */
    
    public String toString (){
        
        StringBuilder s = new StringBuilder();

        s.append("\nNome: ").append(this.nome).append("\n");
        s.append("Nif: ").append(this.nif).append("\n");
        s.append("E-mail: ").append(this.email).append("\n");
        s.append("Password: ").append(this.password).append("\n");
        s.append("Morada: ").append(this.morada).append("\n");
        s.append("Data de Nascimento: ").append(this.dataDeNascimento.toString()).append("\n");
        s.append("Class.: ").append(this.classificacao).append("\n");
        s.append("Historico: ").append(this.historico_alugueres.toString()).append("\n");

        return s.toString();
    }

   //-------------------------------------------------------------------------------------------------------------

    /**
     * Função hashcode para um Ator Sistema a partir do email
     *
     * @return hashcode de um cliente.
    */

   public int hashCode() {
        
        return this.getEmail().hashCode();
    }
    
   //-------------------------------------------------------------------------------------------------------------

    /**
     * Devolve a Lista de alugueres de um user
     *
     * @return Historico de alugueres.
    */

    public List<Aluguer> getHistoricoAlugueres () {

        return this.historico_alugueres.stream()
                                       .map(Aluguer::clone)
                                       .collect(Collectors.toList());
    }

   /**
     * Devolve o Nif de um Ator Sistema
     *
     * @return O nif do Ator Sistema.
    */

    public String getNif () {

        return this.nif;
    }

   /**
     * Devolve a classificacao de um Ator Sistema
     *
     * @return a classificacao do Ator Sistema.
    */

    public int getClassificacao () {

        return this.classificacao;
    }

   /**
     * Devolve o email de um Ator Sistema
     *
     * @return O email do Ator Sistema.
    */

    public String getEmail(){
    
        return this.email;
    }

   /**
     * Devolve o Nome de um Ator Sistema
     *
     * @return O nome do Ator Sistema.
    */

    public String getNome(){
        
        return this.nome;
    }

   /**
     * Devolve a password de um Ator Sistema
     *
     * @return A password do Ator Sistema.
    */

    public String getPassword(){
        
        return this.password;
    }

   /**
     * Devolve a Morada de um Ator Sistema
     *
     * @return A morada do Ator Sistema.
    */

    public String getMorada(){
        
        return this.morada;
    }

   /**
     * Devolve a data de criacao do Ator Sistema
     *
     * @return A data de criação.
    */

    public LocalDate getDataDeNascimento(){
        
        return this.dataDeNascimento;
    }

    /**
     * Atribui um novo email ao AtorSistema
     *
     * @param email Email novo.
    */

    public void setEmail(String email){
    
        this.email = email;
    }
  
    /**
     * Atribui um novo nome ao AtorSistema
     *
     * @param nome Nome novo.
    */

    public void setNome(String nome){
        
        this.nome = nome;
    }

    /**
     * Atribui uma nova password ao AtorSistema
     *
     * @param password Password nova.
    */

    public void setPassword(String password){
        
        this.password = password;
    }
    
    /**
     * Atribui uma nova morada ao AtorSistema
     *
     * @param morada Morada nova.
    */

    public void setMorada(String morada){
       
        this.morada = morada;
    }

    /**
     * Atribui uma nova data ao AtorSistema
     *
     * @param dataDeNascimento data nova.
    */

    public void setDataDeNascimento(LocalDate dataDeNascimento){
        
        this.dataDeNascimento = dataDeNascimento;
    }

    /**
     * Atribui uma nova classificacao ao AtorSistema
     *
     * @param classi Classificacao nova.
    */

    public void setClassificacao(int classi){
    
        this.classificacao = classi;
    }

    /**
     * Atribui um novo nif ao AtorSistema
     *
     * @param new_nif Nif novo.
    */

    public void setNif(String new_nif) {

        this.nif = new_nif;
    }

}