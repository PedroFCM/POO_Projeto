import java.time.LocalDate;
import java.time.Month;
import java.lang.StringBuilder;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class AtorSistema {

    //-------------------------------------------------------//  

    private String email;
    private String nome;
    private String password;
    private String morada;
    private LocalDate dataDeNascimento;
    private int classificacao;
    private List<Aluguer> historico_alugueres;

   //-------------------------------------------------------//  

    public AtorSistema (String novo_email, 
    					String novo_nome, 
    					String nova_pass, 
    					String nova_morada, 
    					LocalDate nova_dataNasc,
                        int classif,
                        List<Aluguer> historico) {
        
        this.email    		     = novo_email;
        this.nome  	  		     = novo_nome;
        this.password 		     = nova_pass;
        this.morada   		     = nova_morada;
        this.dataDeNascimento    = nova_dataNasc;
        this.classificacao       = classif;
        this.historico_alugueres = historico.stream()
                                            .map(Aluguer::clone)
                                            .collect(Collectors.toList());
    }
   
    public AtorSistema (AtorSistema novo) {

        this.email    = novo.getEmail();
        this.nome     = novo.getNome();
        this.password = novo.getPassword();
        this.morada   = novo.getMorada();
        this.dataDeNascimento = novo.getDataDeNascimento();
        this.classificacao = novo.getClassificacao();
        this.historico_alugueres = novo.getHistoricoAlugueres();
    }
    
    public AtorSistema () {

        this.email            = "n/a";
        this.nome             = "n/a";
        this.password         = "n/a";
        this.morada           = "n/a";
        this.dataDeNascimento = LocalDate.now();
        this.classificacao    = 0;
        this.historico_alugueres = new ArrayList<Aluguer>();
    }

   //-------------------------------------------------------//  

    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        AtorSistema c = (AtorSistema) o;

        return(c.getClassificacao() == this.classificacao             && 
               c.getEmail().equals(this.email)                        && 
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
        s.append("Class.: " + this.classificacao);
        s.append("\nHistorico: " + this.historico_alugueres.toString() + "\n");

        return s.toString();
    }

    public int hashCode() {
        
        return this.getEmail().hashCode();
    }
    
   //-------------------------------------------------------//  

    public List<Aluguer> getHistoricoAlugueres () {

        return this.historico_alugueres.stream()
                                       .map(Aluguer::clone)
                                       .collect(Collectors.toList());
    }

    public int getClassificacao () {

        return this.classificacao;
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
