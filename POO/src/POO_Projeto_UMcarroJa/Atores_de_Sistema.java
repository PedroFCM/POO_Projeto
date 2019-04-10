import java.util.Date;
import java.util.List;

public class Atores_de_Sistema
{   
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private String email;
    private String nome;
    private String password;
    private String morada;
    private Date dataDeNascimento;
            
    private List<Aluguer> listaAlugueres;

    public Atores_de_Sistema(String email, String nome, String password, String morada, Date data) {
        
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.dataDeNascimento = data;  
        // listaAlugueeres falta
    }
    
    public String getEmail(){
    
        return this.email;
    }
  
    public String getNome(){
        
        return this.nome;
    }
    
    public String getPassword(){
        
        return this.password;
    }
    
    public String getMorada(){
        
        return this.morada;
    }
    
    public Date getDataDeNascimento(){
        
        return this.dataDeNascimento;
    }
    
    /*
    public getListaAlugueres(){}
    */

    public void setEmail(String email){
    
        this.email = email;
    }
  
    public void setNome(String nome){
        
        this.nome = nome;
    }
    
    public void setPassword(String password){
        
        this.password = password;
    }
    
    public void setMorada(String morada){
       
        this.morada = morada;
    }
    
    public void setDataDeNascimento(Date dataDeNascimento){
        
        this.dataDeNascimento = dataDeNascimento;
    }
}
