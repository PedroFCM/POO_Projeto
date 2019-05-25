
import java.io.Serializable;

public class VeiculoComAutonomia extends Veiculo {

	//-------------------------------------------------------//  

    private double consumoPorKm;
    private double autonomiaMaxima;
    private double autonomiaAtual;

    //-------------------------------------------------------//  

    public VeiculoComAutonomia (String new_marca,
    							String nova_matricula,
		                        double velMedia,
		                        double priceKM,
		                        int classiF,
		                        Localizacao nova_local,
		                        double consumo,
		                        double maxAuto,
		                        double currentAuto,
		                        String prop,
		                        boolean estado,
                                int vezesA) {

        super(new_marca, 
        	  nova_matricula, 
        	  velMedia, 
        	  priceKM, 
        	  classiF, 
        	  nova_local, 
        	  prop, 
        	  estado,
              vezesA);

        this.consumoPorKm    = consumo;
        this.autonomiaMaxima = maxAuto;
        this.autonomiaAtual  = currentAuto;
    }

    public VeiculoComAutonomia (VeiculoComAutonomia veiculoAutonomia) {

        super(veiculoAutonomia);

        this.consumoPorKm    = veiculoAutonomia.getConsumoPorKM();
        this.autonomiaMaxima = veiculoAutonomia.getAutonomiaMaxima();
        this.autonomiaAtual  = veiculoAutonomia.getAutonomiaAtual(); 
    }

    public VeiculoComAutonomia () {

        super();

        this.consumoPorKm    = 0;
        this.autonomiaMaxima = 0;
        this.autonomiaAtual  = 0;
    }

    //-------------------------------------------------------//  

    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        VeiculoComAutonomia g = (VeiculoComAutonomia) o;
     
        return(super.equals(g)                             && 
               this.consumoPorKm == g.getConsumoPorKM()    &&
               this.autonomiaMaxima == g.getAutonomiaMaxima() &&
               this.autonomiaAtual == g.getAutonomiaAtual());
    }

    public VeiculoComAutonomia clone () {

        return new VeiculoComAutonomia(this);
    }

    public String toString (){
        
        StringBuilder s = new StringBuilder();

        s.append(":: Veiculo com autonomia.\n\n");
        s.append(super.toString());
        s.append("Cons./KM: " + this.consumoPorKm + "\n");
        s.append("Deposito (Max): " + this.autonomiaMaxima + "\n");
        s.append("Deposito (Atual) : " + this.autonomiaAtual + "\n");

        return s.toString();
    }


    //-------------------------------------------------------//  

    public double getConsumoPorKM () {

        return this.consumoPorKm;
    }
   
    public double getAutonomiaMaxima () {

        return this.autonomiaMaxima;
    }

    public double getAutonomiaAtual () {

        return this.autonomiaAtual;
    }
    
    public void setAutonomiaAtual(double atual){
        
        this.autonomiaAtual = atual;
    }

    public double percentagemAutonomia () {
     
        return ((this.autonomiaAtual/this.autonomiaMaxima) * 100);
    }

    public boolean veiculoDisponivelAluguer(){
    
        return(this.percentagemAutonomia() >= 10);
    }
    
    //-------------------------------------------------------//  

    public void moverParaPosicao (double x, double y){
        
        Localizacao l = new Localizacao(x,y);
        
        double p = this.getAutonomiaAtual() - this.getLocalizacao().distancia(l);
        
        this.setAutonomiaAtual((p<0)?0:p);
        
        this.setLocalizacao(l);
    }

    public boolean temAutonomiaParaViagem (double distancia){
    
        return(this.getAutonomiaAtual() >= distancia);
    } 

}