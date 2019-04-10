import java.lang.Math;

/**
 * Escreva a descrição da classe Localizacao aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Localizacao
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private double x;
    private double y;
    
    public Localizacao(){
        
        this.x = 0;
        this.y = 0;
    }
    
    public Localizacao(double x, double y){
    
        this.x = x;
        this.y = y;
    }
    
    public Localizacao(Localizacao local){
        
        this.x = local.getX();
        this.y = local.getY();
    }
    
    public double getX(){
        
        return this.x;
    }
    
    public double getY(){
        return this.y;
    }
  
    public void setX(double x){
        
        this.x = x;
    }
    
    public void setY(double y){
    
        this.y = y;
    }
    
    public double distancia(Localizacao local){
    
        return (Math.hypot(local.getX(),local.getY()));
    }
    
    public String toString(){
        
        return "X = " + this.x + " Y = " + this.y;
    }

    public boolean equals(Localizacao local){
    
        return(this.x == local.getX() && this.y == local.getY());
    }
}
