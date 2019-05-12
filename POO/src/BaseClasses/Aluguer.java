
package BaseClasses;

import java.time.LocalDate;
import java.util.List;

import ComponentesSistema.Veiculos.*;

import java.io.Serializable;

public class Aluguer implements Serializable {

    private String veiculo;
    private String cliente;
    private String proprietario;
    private double distancia;
    private double preco;
    private LocalDate data;
    
    public Aluguer(String veiculo, String cliente, String proprietario, double distancia, double preco, LocalDate data) {    
       
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.proprietario = proprietario;
        this.distancia = distancia;
        this.preco = preco;
        this.data = data;
    }
    
    public Aluguer(Aluguer umAluguer) {

        this.veiculo = umAluguer.getVeiculo();
        this.cliente = umAluguer.getCliente();
        this.proprietario = umAluguer.getProprietario();
        this.distancia = umAluguer.getDistancia();
        this.preco = umAluguer.getPreco();
        this.data = umAluguer.getData();
    }
    
    public Aluguer () {
        
        this.veiculo = "Not initialized";
        this.cliente = "Not initialized";
        this.proprietario = "Not initialized";
        this.preco = 0.0;
        this.distancia = 0.0;
        this.data = LocalDate.now();
    }

   //-------------------------------------------------------//  



   //-------------------------------------------------------//  

    public String toString() 
    {    
        StringBuffer sb = new StringBuffer();
        
        sb.append('\n' + "Cliente = "+ this.getCliente() + '\n');
        sb.append("Proprietario = "+ this.getProprietario() + '\n');
        sb.append("Veiculo = "+ this.getVeiculo() + '\n');
        sb.append("Preco = "+ this.getPreco() + '\n');
        sb.append("Distancia = "+ this.getDistancia() + '\n');
        sb.append("Data = "+ this.getData().toString() + '\n'); 
        
        return sb.toString();
        
    }
    
    public Aluguer clone()
    {
        return new Aluguer(this);
    }
    
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        
        if((o == null) || (this.getClass() != o.getClass()))
          return false;
          
        Aluguer umAluguer = (Aluguer) o;
        return (this.cliente.equals(umAluguer.getCliente()) && this.proprietario.equals(umAluguer.getProprietario()) 
            && this.veiculo.equals(umAluguer.getVeiculo()) && this.preco == umAluguer.getPreco() && this.distancia == umAluguer.getDistancia() 
            && this.data.equals(umAluguer.getData()) ); 
    }

   //-------------------------------------------------------//  

    public double precoViagem(){
        
        return (this.preco * this.distancia);
    }
   
    public String getCliente()
    {
        return this.cliente;
    }
    
    public String getProprietario()
    {
        return this.proprietario;
    }
    
    public String getVeiculo()
    {
        return this.veiculo;
    }
    
    public double getPreco()
    {
        return this.preco;
    }
    
    public double getDistancia()
    {
        return this.distancia;
    }
    
    public LocalDate getData()
    {
        return this.data;
    }
    
    public void setCliente(String cliente)
    {
        this.cliente = cliente;
    }
    
    public void setProprietario(String proprietario)
    {
        this.proprietario = proprietario;
    }
    
    public void setVeiculo(String veiculo)
    {
        this.veiculo = veiculo;
    }
    
    public void setPreco(double preco)
    {
        this.preco = preco;
    }
    
    public void setDistancia(double distancia)
    {
        this.distancia = distancia;
    }
    
    public void setData(LocalDate data)
    {
        this.data = data;
    }
        
}