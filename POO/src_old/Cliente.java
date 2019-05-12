import java.time.LocalDate;
import java.lang.StringBuilder;
import java.util.List;
import java.util.stream.Collectors;

public class Cliente extends AtorSistema {

    //-------------------------------------------------------//  

    //Cada cliente tem a sua localização
    private Localizacao coordenadas;

    //-------------------------------------------------------//  

    public Cliente (String email_cliente, 
                    String nome_cliente, 
                    String pass_cliente, 
                    String morada_cliente, 
                    LocalDate dataNasc_cliente, 
                    Localizacao local_cliente,
                    List<Aluguer> historico,
                    int classif) {
        
        super(email_cliente, nome_cliente, 
              pass_cliente, morada_cliente, 
              dataNasc_cliente, classif, historico);

        this.coordenadas = new Localizacao(local_cliente);
    }

    public Cliente (Cliente novo) {

        super(novo);
        this.coordenadas = new Localizacao(novo.getLocalizacao());
    }
    
    public Cliente () {

        super();
        this.coordenadas = new Localizacao();
    }

    //-------------------------------------------------------//  

    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        Cliente c = (Cliente) o;
     
        return (super.equals(c) && 
                this.coordenadas.equals(c.getLocalizacao()));
    }

    public Cliente clone () {

        return new Cliente(this);
    }

    public String toString(){
   
        StringBuilder s = new StringBuilder();

        s.append("\n=> Tipo de ator de Sistema: Cliente.");
        s.append(super.toString());
        s.append(this.coordenadas.toString());

        return s.toString();
    }

    //-------------------------------------------------------//  

    public Localizacao getLocalizacao() {
        
        return this.coordenadas.clone();
    }
    
    public void setLocalizacao (Localizacao local){
        
        this.coordenadas = local;
    }
 
    //-------------------------------------------------------//  
}