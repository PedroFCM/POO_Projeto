//-------------------------------------------------------------------------------------------------------------

/**
 * Class que implementa um Veiculo, abstrata. 
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

public abstract class Veiculo implements Serializable {


    //-------------------------------------------------------------------------------------------------------------

    /*
    * Marca do veiculo
    */
    private String marca;
    /*
    * Matricula do veiculo
    */
    private String matricula;
    /*
    * Velocidade media do veiculo
    */
    private double velMediaPorKM;
    /*
    * Preco por km do veiculo
    */
    private double precoPorKm;
    /*
    * Classificacao do veiculo
    */
    private int classificacao;
    /*
    * Localizacao do veiculo
    */
    private Localizacao local;
    /*
    * Nif do proprietario do veiculo
    */
    private String proprietario;
    /*
    * Disponibilidade do veiculo
    */
    private boolean disponivel;
    /*
    * Numer de vezes alugado
    */
    private int vezesAlugado;
    
    //-------------------------------------------------------------------------------------------------------------

    /**
     * Construtor parameterizado da classe CarroEletrico
     *
     * @param new_marca Marca do veiculo.
     * @param new_matricula Matricula do veiculo.
     * @param new_mediumVel Velocidade Media do Veiculo
     * @param new_precKM Preco por km do veiculo
     * @param new_classif Classificacao do veiculo
     * @param new_local Localizacao do veiculo             
     * @param new_prop Proprietario do veiculo (Nif)
     * @param new_estado Disponivel = true
     * @param vezes Vezes que foi alugado
     *
    */

    public Veiculo (String new_marca,
                    String new_mat,
                    double new_mediumVel, 
                    double new_precKM,
                    int new_classif,
                    Localizacao new_local,
                    String new_prop,
                    boolean new_estado,
                    int vezes) {

        this.marca           = new_marca;
        this.matricula       = new_mat; 
        this.velMediaPorKM   = new_mediumVel;
        this.precoPorKm      = new_precKM;
        this.classificacao   = new_classif;
        this.local           = new Localizacao(new_local);
        this.proprietario    = new_prop;
        this.disponivel      = new_estado;
        this.vezesAlugado    = vezes;
    }
   
    /**
     * Construtor copia da classe Veciulo
     *
     * @param novo novo Veiculo.
     *
    */

    public Veiculo (Veiculo novo) {

        this.marca           = novo.getMarca();
        this.matricula       = novo.getMatricula();
        this.velMediaPorKM   = novo.getVelMediaPorKM();
        this.precoPorKm      = novo.getPrecoPorKM();
        this.classificacao   = novo.getClassificacao();
        this.local           = novo.getLocalizacao();
        this.proprietario    = novo.getProprietario();
        this.disponivel      = novo.getDisponivel();
        this.vezesAlugado    = novo.getVezesAlugado();
    }

    /**
     * Construtor vazio da classe Veiculo
    */

    public Veiculo () {

        this.matricula       = "00-00-00";
        this.velMediaPorKM   = 0;
        this.precoPorKm      = 0;
        this.classificacao   = 0;
        this.local           = new Localizacao();
        this.proprietario    = "N/A";
        this.disponivel      = false;
        this.marca           = "N/A";
        this.vezesAlugado    = 0;
    }
  
    //-------------------------------------------------------------------------------------------------------------

    /**
     * Determina se dois veiculos são iguais.
     *
     * @param o Objeto para fazer comparação.
     *
     * @return true se forem iguais.
    */

    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        Veiculo v = (Veiculo) o;
     
        return(this.matricula.equals(v.getMatricula())       &&
               this.velMediaPorKM == v.getVelMediaPorKM()    &&
               this.precoPorKm == v.getPrecoPorKM()          &&
               this.classificacao == v.getClassificacao()    &&
               this.local.equals(v.getLocalizacao())         &&
               this.proprietario.equals(v.getProprietario()) &&
               this.disponivel == v.getDisponivel() &&
               this.marca == v.getMarca());
    }

    /**
     * Constrói uma cópia de um veiculo.
     * Obriga cada subclasse a implementar este metodo
     *
     * @return Novo Veiculo.
    */

    public abstract Veiculo clone ();


    /**
     * Converte um Veiculo para a sua representação em String
     *
     * @return A representação do veiculo em String.
    */

    public String toString (){
        
        StringBuilder s = new StringBuilder();

        s.append("Marca: ").append(this.marca).append(".\n");
        s.append("Matrícula: " + this.matricula + "\n");
        s.append("Vel_Med/KM: " + this.velMediaPorKM + "\n");
        s.append("Preco/KM: " + this.precoPorKm + "\n");
        s.append("Class.: " + this.classificacao + "\n");
        s.append("Proprietario: " + this.proprietario + "\n");
        s.append(this.local.toString());
        s.append("Disponivel: " + this.disponivel + "\n");
        s.append("Alugado " + this.vezesAlugado + " vezes\n");

        return s.toString();
    }
    
    //----------------------------------------------------------------------------------------
    
    /**
     * Devolve a marca do veiculo
     *
     * @return A marca do veiculo.
    */

    public String getMarca () {

        return this.marca;
    }

    /**
     * Devolve a proprietario do veiculo
     *
     * @return A proprietario do veiculo.
    */

    public String getProprietario() {

        return this.proprietario;
    }

    /**
     * Devolve a matricula do veiculo
     *
     * @return A matricula do veiculo.
    */

    public String getMatricula () {

        return this.matricula;
    }

    /**
     * Devolve a velMedia do veiculo
     *
     * @return A velMedia do veiculo.
    */

    public double getVelMediaPorKM () {

        return this.velMediaPorKM; 
    }

    /**
     * Devolve o preco por km do veiculo
     *
     * @return o preco por km do veiculo.
    */

    public double getPrecoPorKM () {

        return this.precoPorKm;
    }

    /**
     * Devolve a classificacao do veiculo
     *
     * @return A classificacao do veiculo.
    */

    public int getClassificacao () {

        return this.classificacao;
    }

    /**
     * Devolve a localizacao do veiculo
     *
     * @return A localizacao do veiculo.
    */

    public Localizacao getLocalizacao () {

        return this.local;
    }

    /**
     * Devolve a disponibilidade do veiculo
     *
     * @return A disponibilidade do veiculo.
    */

    public boolean getDisponivel(){
        
        return this.disponivel;
    }

    /**
     * Atribui o precoKm de um veiculo
     *
     * @param preco precoKm do veiculo
    */

    public void setPrecoPorKm(double preco){
        
        this.precoPorKm = preco;
    }
    
    /**
     * Atribui a disponibilidade de um veiculo
     *
     * @param estado disponibilidade do veiculo
    */

    public void setDisponivel(boolean estado){
        
        this.disponivel = estado;
    }

    /**
     * Atribui a Localizacao de um veiculo
     *
     * @param l Localizacao do veiculo
    */

    public void setLocalizacao(Localizacao l){
    
        this.local = l.clone();
    }

    /**
     * Atribui a marca de um veiculo
     *
     * @param new_marca marca do veiculo
    */

    public void setMarca(String new_marca) {

        this.marca = new_marca;
    }

    /**
     * Atribui a clas de um veiculo
     *
     * @param clas classificacao do veiculo
    */

    public void setClassificacao(int clas) {

        this.classificacao = clas;
    }

    /**
     * Devolve o numero de vezes alugado
     *
     * @return Numero de vezes alugado
    */

    public int getVezesAlugado() {

        return this.vezesAlugado;
    }

    /**
     * Atribui o numero de vezes alugado de um veiculo
     *
     * @param v numero de vezes alugado do veiculo
    */

    public void setVezesAlugado(int v) {

        this.vezesAlugado = v;
    }

    /**
     * Aumenta o numero de vezes alugado por 1
     *
    */

    public void incrementVezesAlugadoBy1() {

        this.vezesAlugado = this.vezesAlugado + 1;
    }   

    /**
     * Atribui classificacao ao veiculo mediante as suas classificacao antigas
     *
     * @param classi classificacao a atribuir ao veiculo
    */

    public void atribuiClassificacao(int classi){
    
        int n = this.vezesAlugado;

        if (this.classificacao == 0) {

            this.setClassificacao(classi);
        } else {

            this.setClassificacao((this.classificacao + classi)/(n+1));
        }
    }
    
    //-------------------------------------------------------//  
    
    /**
     * Metodo abstrato. Atribui uma nova posicao x, y ao veiculo
     *
     * @param x Localizacao X
     * @param y Localizacao Y
    */

    public abstract void moverParaPosicao (double x, double y);
 
    /**
     * Metodo abstrato. Determina se um veiculo esta disponivel para aluguer
     *
    */

    public abstract boolean veiculoDisponivelAluguer();

    //-------------------------------------------------------//

    /**
     * Hashcode de um veiculo pela matricula.
     *
     * @return hashcode de um veiculo
    */

    public int hashCode () {

        return this.matricula.hashCode();
    }

}