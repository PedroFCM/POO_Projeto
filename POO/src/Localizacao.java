
import java.lang.Math;
import java.lang.StringBuilder;

import java.io.Serializable;

public class Localizacao implements Serializable {

    //-------------------------------------------------------//  
    
    private double x;
    private double y;
    
    //-------------------------------------------------------//  
    
    public Localizacao () {
        
        this.x = 0;
        this.y = 0;
    }
    
    public Localizacao (double x, double y){
    
        this.x = x;
        this.y = y;
    }
    
    public Localizacao (Localizacao nova_local){
        
        this.x = nova_local.getX();
        this.y = nova_local.getY();
    }
    
    //-------------------------------------------------------//  
    
    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        Localizacao c = (Localizacao) o;
     
        return(this.x == c.getX() && this.y == c.getY());
    }

    public Localizacao clone () {

        return new Localizacao(this);
    }

    public String toString(){
        
        StringBuilder s = new StringBuilder();

        s.append("Localizacao: X = ");
        s.append(this.x);
        s.append(" Y = ");
        s.append(this.y + "\n");

        return s.toString();
    }

    //-------------------------------------------------------//  
  
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
    
    //-------------------------------------------------------//  
    
    public double distancia (Localizacao local){
    
        return (Math.sqrt(Math.pow(local.getY()-this.y, 2) + 
                          Math.pow(local.getX()-this.x, 2)));
    }
    
    //-------------------------------------------------------//  
    
}