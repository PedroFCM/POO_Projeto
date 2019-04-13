


public class CarroGasolina extends Veiculo {

    //-------------------------------------------------------//  

	private double consumoPorKm;
    private double autonomiaMaxima;
    private double autonomiaAtual;

    //-------------------------------------------------------//  

    public CarroGasolina (String nova_matricula,
    					  double velMedia,
    					  double priceKM,
    					  double classiF,
    					  Localizacao nova_local,
    					  double consumo,
    					  double maxAuto,
    					  double currentAuto) {

    	super(nova_matricula, velMedia, priceKM, classiF, nova_local);

    	this.consumoPorKm    = consumo;
    	this.autonomiaMaxima = maxAuto;
    	this.autonomiaAtual  = currentAuto;
    }

    public CarroGasolina (CarroGasolina carroGas) {

    	super(carroGas);

    	this.consumoPorKm    = carroGas.getConsumoPorKM();
    	this.autonomiaMaxima = carroGas.getAutonomiaMax();
    	this.autonomiaAtual  = carroGas.getAutonomiaAtual(); 
    }

    public CarroGasolina () {

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
        
        CarroGasolina g = (CarroGasolina) o;
     
        return(super.equals(g) 							   && 
        	   this.consumoPorKm == g.getConsumoPorKM()    &&
        	   this.autonomiaMaxima == g.getAutonomiaMax() &&
        	   this.autonomiaAtual == g.getAutonomiaAtual());
    }

    public CarroGasolina clone () {

        return new CarroGasolina(this);
    }

    public String toString (){
        
        StringBuilder s = new StringBuilder();

  	    s.append("\n=> Tipo de Veiculo: Carro a gasolina.\n");
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