


public interface Autonomia {

	public double getAutonomiaMaxima();

    public double getAutonomiaAtual();

    public void setAutonomiaAtual(double atual);

    public double percentagemAutonomia();

    public boolean temAutonomiaParaViagem (double distancia);

}