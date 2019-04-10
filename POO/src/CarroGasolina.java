


public class CarroGasolina extends Veiculo {

    //-------------------------------------------------------//  

	private float consumoPorKm;
    private float autonomiaMaxima;
    private float autonomiaAtual;

    //-------------------------------------------------------//  

    public CarroGasolina (String nova_matricula,
    					  int velMedia,
    					  float priceKM,
    					  float classiF,
    					  Localizacao nova_local,
    					  float consumo,
    					  float maxAuto,
    					  float currentAuto) {

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

  	    s.append("=> Tipo de Veiculo: Carro a gasolina.\n");
		s.append(super.toString());
		s.append("Cons./KM: " + this.consumoPorKm);
		s.append("Autonomia (Maxima): " + this.autonomiaMaxima);
		s.append("Autonomia (Atual) : " + this.autonomiaAtual);

        return s.toString();
    }


    //-------------------------------------------------------//  

	public float getConsumoPorKM () {

        return this.consumoPorKm;
    }
   
    public float getAutonomiaMax () {

        return this.autonomiaMaxima;
    }

    public float getAutonomiaAtual () {

        return this.autonomiaAtual;
    }

    public float percentagemAutonomia () {
     
        return ((this.autonomiaAtual/this.autonomiaMaxima) * 100);
    }

    //-------------------------------------------------------//  
}