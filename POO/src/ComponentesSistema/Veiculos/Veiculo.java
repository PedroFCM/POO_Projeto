
package ComponentesSistema.Veiculos;

import BaseClasses.Localizacao;

import java.io.Serializable;

public abstract class Veiculo implements Serializable {

    //-------------------------------------------------------//  

    private String marca;
    private String matricula;
    private double velMediaPorKM;
    private double precoPorKm;
    private int classificacao;
    private Localizacao local;
    private String proprietario;
    private boolean disponivel;
    
    //-------------------------------------------------------//  

    public Veiculo (String new_marca,
                    String new_mat,
                    double new_mediumVel, 
                    double new_precKM,
                    int new_classif,
                    Localizacao new_local,
                    String new_prop,
                    boolean new_estado) {

        this.marca           = new_marca;
        this.matricula       = new_mat; 
        this.velMediaPorKM   = new_mediumVel;
        this.precoPorKm      = new_precKM;
        this.classificacao   = new_classif;
        this.local           = new Localizacao(new_local);
        this.proprietario    = new_prop;
        this.disponivel      = new_estado;
    }
   
    public Veiculo (Veiculo novo) {

        this.marca           = novo.getMarca();
        this.matricula       = novo.getMatricula();
        this.velMediaPorKM   = novo.getVelMediaPorKM();
        this.precoPorKm      = novo.getPrecoPorKM();
        this.classificacao   = novo.getClassificacao();
        this.local           = novo.getLocalizacao();
        this.proprietario    = novo.getProprietario();
        this.disponivel      = novo.getDisponivel();
    }

    public Veiculo () {

        this.matricula       = "00-00-00";
        this.velMediaPorKM   = 0;
        this.precoPorKm      = 0;
        this.classificacao   = 0;
        this.local           = new Localizacao();
        this.proprietario    = "N/A";
        this.disponivel      = false;
        this.marca           = "N/A";

    }
  
    //-------------------------------------------------------//  

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

    //-------------------------------------------------------//  

    public abstract Veiculo clone ();

    //-------------------------------------------------------//  

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

        return s.toString();
    }
    
    //-------------------------------------------------------//  

    public String getMarca () {

        return this.marca;
    }

    public String getProprietario() {

        return this.proprietario;
    }

    public String getMatricula () {

        return this.matricula;
    }

    public double getVelMediaPorKM () {

        return this.velMediaPorKM; 
    }

    public double getPrecoPorKM () {

        return this.precoPorKm;
    }

    public int getClassificacao () {

        return this.classificacao;
    }

    public Localizacao getLocalizacao () {

        return this.local;
    }

    public boolean getDisponivel(){
        
        return this.disponivel;
    }
    
    public void setPrecoPorKm(double preco){
        
        this.precoPorKm = preco;
    }
    
    public void setDisponivel(boolean estado){
        
        this.disponivel = estado;
    }
    
    public void setLocalizacao(Localizacao l){
    
        this.local = l.clone();
    }

    public void setMarca(String new_marca) {

        this.marca = new_marca;
    }

    public void setClassificacao(int clas) {

        this.classificacao = clas;
    }
    
    //-------------------------------------------------------//  
    
    public abstract void moverParaPosicao (double x, double y);
 
    public abstract boolean veiculoDisponivelAluguer();

    //-------------------------------------------------------//

    public int hashCode () {

        return this.matricula.hashCode();
    }

}