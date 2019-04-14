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
    
    //-------------------------------------------------------//  

    public Veiculo (String nova_matric,
                    double mediumVel, 
                    double precKM,
                    double classif,
                    Localizacao local_carro,
                    String proprietario) {

        this.matricula       = nova_matric; 
        this.velMediaPorKM   = mediumVel;
        this.precoPorKm      = precKM;
        this.classificacao   = classif;
        this.local           = new Localizacao(local_carro);
        this.proprietario    = proprietario;
    }
   
    public Veiculo (Veiculo novo) {

        this.matricula       = novo.getMatricula();
        this.velMediaPorKM   = novo.getVelMediaPorKM();
        this.precoPorKm      = novo.getPrecoPorKM();
        this.classificacao   = novo.getClassificacao();
        this.local           = novo.getLocalizacao();
        this.proprietario    = novo.getProprietario();
    }

    public Veiculo () {

        this.matricula       = "00-00-00";
        this.velMediaPorKM   = 0;
        this.precoPorKm      = 0;
        this.classificacao   = 0;
        this.local           = new Localizacao();
        this.proprietario    = "Não tem";
    }
  
    //-------------------------------------------------------//  

    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        Veiculo v = (Veiculo) o;
     
        return(this.matricula.equals(v.getMatricula())    &&
               this.velMediaPorKM == v.getVelMediaPorKM() &&
               this.precoPorKm == v.getPrecoPorKM()       &&
               this.classificacao == v.getClassificacao() &&
               this.local.equals(v.getLocalizacao())      &&
               this.proprietario.equals(v.getProprietario()));
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

        return s.toString();
    }
    
    //-------------------------------------------------------//  
    
    public double getAutonomiaAtual(){
        
        //Esta versao so esta presente para o compilador detetar o metodo
        //A versao correta esta presente nas classes de tipos de carros
        //Gasolina, Eletricos,...

        return 0;
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

    public void setPrecoPorKm(double preco){
        
        this.precoPorKm = preco;
    }
    
    //-------------------------------------------------------//  
    
    public void moverParaPosicao (int x, int y){
        


    }
}