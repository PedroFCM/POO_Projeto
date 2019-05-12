
public class CarroHibrido extends Veiculo implements Autonomia {

    //-------------------------------------------------------//  
    private double consumoPorKm;
    private double autonomiaMaxima;
    private double autonomiaAtual;

    //-------------------------------------------------------//  

    public CarroHibrido(String nova_matricula,
                        double velMedia,
                        double priceKM,
                        double classiF,
                        Localizacao nova_local,
                        double consumo,
                        double maxAuto,
                        double currentAuto,
                        String prop,
                        boolean estado) 
    {
        super(nova_matricula, velMedia, priceKM, classiF, nova_local, prop, estado);

        this.consumoPorKm    = consumo;
        this.autonomiaMaxima = maxAuto;
        this.autonomiaAtual  = currentAuto;
    }

    public CarroHibrido(CarroHibrido carroHib) 
    {
        super(carroHib);
        this.consumoPorKm    = carroHib.getConsumoPorKM();
        this.autonomiaMaxima = carroHib.getAutonomiaMaxima();
        this.autonomiaAtual  = carroHib.getAutonomiaAtual(); 
    }

    public CarroHibrido() 
    {
        super();
        this.consumoPorKm    = 0;
        this.autonomiaMaxima = 0;
        this.autonomiaAtual  = 0;
    }

    //-------------------------------------------------------//  

    public boolean equals(Object o) 
    {    
        if (this == o) 
            return true;
        
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        CarroHibrido g = (CarroHibrido) o;
     
        return(super.equals(g)                             && 
               this.consumoPorKm == g.getConsumoPorKM()    &&
               this.autonomiaMaxima == g.getAutonomiaMaxima() &&
               this.autonomiaAtual == g.getAutonomiaAtual());
    }

    public CarroHibrido clone() 
    {
        return new CarroHibrido(this);
    }

    public String toString ()
    {    
        StringBuilder s = new StringBuilder();

        s.append("\n=> Tipo de Veiculo: Carro Hibrido.\n");
        s.append(super.toString());
        s.append("Cons./KM: " + this.consumoPorKm + "\n");
        s.append("Deposito (Max): " + this.autonomiaMaxima + "\n");
        s.append("Deposito (Atual) : " + this.autonomiaAtual + "\n");

        return s.toString();
    }


    //-------------------------------------------------------//  

    public double getConsumoPorKM() 
    {
        return this.consumoPorKm;
    }
   
    public double getAutonomiaMaxima() 
    {
        return this.autonomiaMaxima;
    }

    public double getAutonomiaAtual() 
    {
        return this.autonomiaAtual;
    }
    
    public void setAutonomiaAtual(double atual)
    {    
        this.autonomiaMaxima = atual;
    }

    public double percentagemAutonomia() 
    { 
        return ((this.autonomiaAtual/this.autonomiaMaxima) * 100);
    }

    public boolean veiculoDisponivelAluguer()
    {
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