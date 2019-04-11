


public class CarroEletrico extends Veiculo {

    //-------------------------------------------------------//  

	private double consumoBateriaPorKm;
    private double autonomiaMaxima;
    private double autonomiaAtual;

    //-------------------------------------------------------//  

    public CarroEletrico (String nova_matricula,
    					  int velMedia,
    					  double priceKM,
    					  double classiF,
    					  Localizacao nova_local,
    					  double consumo,
    					  double maxAuto,
    					  double currentAuto) {

    	super(nova_matricula, velMedia, priceKM, classiF, nova_local);

    	this.consumoBateriaPorKm    = consumo;
    	this.autonomiaMaxima = maxAuto;
    	this.autonomiaAtual  = currentAuto;
    }

    public CarroEletrico (CarroEletrico carroEletrico) {

    	super(carroEletrico);

    	this.consumoBateriaPorKm    = carroEletrico.getConsumoBateriaPorKm();
    	this.autonomiaMaxima = carroEletrico.getAutonomiaMax();
    	this.autonomiaAtual  = carroEletrico.getAutonomiaAtual(); 
    }

    public CarroEletrico () {

    	super();

    	this.consumoBateriaPorKm    = 0;
    	this.autonomiaMaxima = 0;
    	this.autonomiaAtual  = 0;
    }

    //-------------------------------------------------------//  

    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        CarroEletrico g = (CarroEletrico) o;
     
        return(super.equals(g) 							   && 
        	   this.consumoBateriaPorKm == g.getConsumoBateriaPorKm()    &&
        	   this.autonomiaMaxima == g.getAutonomiaMax() &&
        	   this.autonomiaAtual == g.getAutonomiaAtual());
    }

    public CarroEletrico clone () {

        return new CarroEletrico(this);
    }

    public String toString (){
        
        StringBuilder s = new StringBuilder();

  	    s.append("\n=> Tipo de Veiculo: Carro Elétrico.\n");
		s.append(super.toString());
		s.append("Cons./KM: " + this.consumoBateriaPorKm + "\n");
		s.append("Autonomia (Maxima): " + this.autonomiaMaxima + "\n");
		s.append("Autonomia (Atual) : " + this.autonomiaAtual + "\n");

        return s.toString();
    }


    //-------------------------------------------------------//  

	public double getConsumoBateriaPorKm () {

        return this.consumoBateriaPorKm;
    }
   
    public double getAutonomiaMax () {

        return this.autonomiaMaxima;
    }

    public double getAutonomiaAtual () {

        return this.autonomiaAtual;
    }

    public double percentagemAutonomia () {
     
        return ((this.autonomiaAtual/this.autonomiaMaxima) * 100);
    }

    //-------------------------------------------------------//  

}