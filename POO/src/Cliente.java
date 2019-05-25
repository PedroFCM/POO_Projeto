
import java.util.Comparator;
import java.time.LocalDate;
import java.lang.StringBuilder;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import java.io.Serializable;

public class Cliente extends AtorSistema implements Comparable<Cliente> {

    //-------------------------------------------------------//  

    //Cada cliente tem a sua localização
    private Localizacao coordenadas;

    private List<Aluguer> naoClassificado;

    //-------------------------------------------------------//  

    public Cliente (String email_cliente, 
                    String nome_cliente, 
                    String pass_cliente, 
                    String morada_cliente, 
                    LocalDate dataNasc_cliente, 
                    Localizacao local_cliente,
                    List<Aluguer> historico,
                    int classif,
                    String nif,
                    List<Aluguer> naoClass) {
        
        super(email_cliente, nome_cliente, 
              pass_cliente, morada_cliente, 
              dataNasc_cliente, classif, historico, nif);

        this.naoClassificado = naoClass.stream().map(Aluguer::clone).collect(Collectors.toList());
        this.coordenadas = new Localizacao(local_cliente);
    }

    public Cliente (Cliente novo) {

        super(novo);
        this.coordenadas = new Localizacao(novo.getLocalizacao());
        this.naoClassificado = novo.getNaoClassificados();
    }
    
    public Cliente () {

        super();
        this.coordenadas = new Localizacao();
        this.naoClassificado = new ArrayList<>();
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

        s.append("\nCliente do sistema:");
        s.append(super.toString());
        s.append(this.coordenadas.toString());

        return s.toString();
    }

    //-------------------------------------------------------//  

    public List<Aluguer> getNaoClassificados () {

        return this.naoClassificado.stream().collect(Collectors.toList());
    }

    public void adicionaAluguerNaoClassificao(Aluguer a) {

        this.naoClassificado.add(a.clone());
    }

    public void removeAluguerNaoClassificao(Aluguer a) {

        this.naoClassificado.remove(a);
    }

    public Localizacao getLocalizacao() {
        
        return this.coordenadas.clone();
    }
    
    public void setLocalizacao (Localizacao local){
        
        this.coordenadas = local;
    }

    public Double getTotalKmPercorridos() {

        Double total = 0.0;

        total = this.getHistoricoAlugueres().stream()
                                            .mapToDouble(Aluguer::getDistancia)
                                            .sum();
        return total;
    }
 
    //-------------------------------------------------------//  

    public int compareTo(Cliente c) {
        
        return c.getTotalKmPercorridos().compareTo(this.getTotalKmPercorridos());
    }
}