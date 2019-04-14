import java.util.List;
import java.util.ArrayList;

public class Veiculo {

    //-------------------------------------------------------//  

    private String matricula;
    private double velMediaPorKM;
    private double precoPorKm;
    private double classificacao;
    private Localizacao local;
    private String proprietario;
    private boolean disponivel;
    
    //-------------------------------------------------------//  

    public Veiculo (String nova_matric,
                    double mediumVel, 
                    double precKM,
                    double classif,
                    Localizacao local_carro,
                    String proprietario,
                    boolean estado) {

        this.matricula       = nova_matric; 
        this.velMediaPorKM   = mediumVel;
        this.precoPorKm      = precKM;
        this.classificacao   = classif;
        this.local           = new Localizacao(local_carro);
        this.proprietario    = proprietario;
        this.disponivel      = estado; 
    }
   
    public Veiculo (Veiculo novo) {

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
        this.proprietario    = "Não tem";
        this.disponivel      = true;
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
               this.disponivel == v.getDisponivel());
    }

    public Veiculo clone (){
        
        return new Veiculo(this);
    }

    public String toString (){
        
        StringBuilder s = new StringBuilder();

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
    
    public double getAutonomiaAtual(){
        
        //Esta versao so esta presente para o compilador detetar o metodo
        //A versao correta esta presente nas classes de tipos de carros
        //Gasolina, Eletricos,...

        return 0;
    }
   
    public boolean veiculoDisponivelAluguer(){
    
        return true;
    }
    
    public double getAutonomiaMaxima(){
        
        return 0;
    }
    
    public void setAutonomiaAtual(double autonomia){

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

    public double getClassificacao () {

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
    
    //-------------------------------------------------------//  
    
    public void moverParaPosicao (double x, double y){
        
        Localizacao l = new Localizacao(x,y);
        
        double p = this.getAutonomiaAtual() - this.getLocalizacao().distancia(l);
        
        this.setAutonomiaAtual((p<0)?0:p);
        
        this.setLocalizacao(l);
    }
}