import java.time.LocalDate;
import java.time.Month;
import java.lang.StringBuilder;

public class AtorSistema {

    //-------------------------------------------------------//  

    private String email;
    private String nome;
    private String password;
    private String morada;
    private LocalDate dataDeNascimento;

   //-------------------------------------------------------//  

    public AtorSistema (String novo_email, 
    						 String novo_nome, 
    						 String nova_pass, 
    						 String nova_morada, 
    						 LocalDate nova_dataNasc) {
        
        this.email    		  = novo_email;
        this.nome  	  		  = novo_nome;
        this.password 		  = nova_pass;
        this.morada   		  = nova_morada;
        this.dataDeNascimento = nova_dataNasc;  
    }
   
    public AtorSistema (AtorSistema novo) {

        //Aqui todas as variveis são imutaveis;

        this.email    = novo.getEmail();
        this.nome     = novo.getNome();
        this.password = novo.getPassword();
        this.morada   = novo.getMorada();
        this.dataDeNascimento = novo.getDataDeNascimento();
    }
    
    public AtorSistema () {

        this.email            = "n/a";
        this.nome             = "n/a";
        this.password         = "n/a";
        this.morada           = "n/a";
        this.dataDeNascimento = LocalDate.now();
    }

   //-------------------------------------------------------//  

    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        AtorSistema c = (AtorSistema) o;
     
        return(c.getEmail().equals(this.email)                        && 
               c.getNome().equals(this.nome)                          &&
               c.getPassword().equals(this.password)                  &&
               c.getMorada().equals(this.morada)                      &&
               c.getDataDeNascimento().equals(this.dataDeNascimento));
    }

    public AtorSistema clone () {

        return new AtorSistema(this);
    }

    public String toString (){
        
        StringBuilder s = new StringBuilder();

        s.append("\n" + "Nome: " + this.nome + "\n");
        s.append("E-mail: " + this.email + "\n");
        s.append("Nome: " + this.password + "\n");
        s.append("Morada: " + this.morada + "\n");
        s.append("Data de Nascimento: " + this.dataDeNascimento.toString() + "\n");

        return s.toString();
    }

   //-------------------------------------------------------//  

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
    
    public LocalDate getDataDeNascimento(){
        
        return this.dataDeNascimento;
    }
    
   //-------------------------------------------------------//  

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
    
    public void setDataDeNascimento(LocalDate dataDeNascimento){
        
        this.dataDeNascimento = dataDeNascimento;
    }

   //-------------------------------------------------------//  

}
