import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.lang.Integer;

public class EstadoSistema  {
    
    //-------------------------------------------------------//  
    
    private HashMap<Cliente, List<Aluguer>> clientes_Sistema;
    private HashMap<Proprietario, List<Veiculo>> proprietarios_Sistema;
    
    private LocalDate data_atual;
    
    //-------------------------------------------------------//  

    public EstadoSistema (HashMap<Cliente, List<Aluguer>> clientes,
                          HashMap<Proprietario, List<Veiculo>> proprietarios,
                          LocalDate data) {

        this.clientes_Sistema = clientes.entrySet()
                                        .stream()
                                        .collect(Collectors.toMap(e -> e.getKey(),
                                                                  e -> new ArrayList<Aluguer>(e.getValue()),
                                        					        (e1, e2) -> e2,
                                        					        HashMap::new));
    
        this.proprietarios_Sistema = proprietarios.entrySet()
                                                  .stream()
                                                  .collect(Collectors.toMap(e -> e.getKey(),
                                                                            e -> new ArrayList<Veiculo>(e.getValue()),
                                                  					        (e1, e2) -> e2,
                                                  					        HashMap::new));

        this.data_atual = data;
    }

    public EstadoSistema (EstadoSistema novo) {

        this.clientes_Sistema = novo.getClientesSistema();
        this.proprietarios_Sistema = novo.getProprietariosSistema();
        this.data_atual = novo.getData();
    }

    public EstadoSistema () {

        this.clientes_Sistema = new HashMap<Cliente, List<Aluguer>>();
        this.proprietarios_Sistema = new HashMap<Proprietario, List<Veiculo>>();
        this.data_atual = LocalDate.now();
    }

    //-------------------------------------------------------//  

    public void adicionaProprietario (Proprietario p) {

    	if (!this.proprietarios_Sistema.containsKey(p)) {

			this.proprietarios_Sistema.put(p.clone(), p.getListaVeiculos());
    	}
    }

    public void adicionaCliente (Cliente c) {

    	if (!this.clientes_Sistema.containsKey(c)) {

			this.clientes_Sistema.put(c.clone(), c.getHistoricoAlugueres());
    	}
    }

    public List<Veiculo> allVeiculos() {

        List<Veiculo> lista = new ArrayList<>();
        
        lista = this.proprietarios_Sistema.values()
                                          .stream()
                                          .flatMap(l -> l.stream())
                                                   .map(Veiculo::clone)
                                                   .collect(Collectors.toList());

        return lista;
    }

    public Veiculo carroMaisProximo (Cliente c) {

        List<Veiculo> todosVeiculos = new ArrayList<Veiculo>();
        
        todosVeiculos =  this.allVeiculos();

        if (todosVeiculos.isEmpty()) {
            return null;
        }

        Localizacao local_c = c.getLocalizacao();

        double distancia_menor = Integer.MAX_VALUE, atual = Integer.MAX_VALUE;

        Veiculo maisPerto = new Veiculo();

        for (Veiculo v: todosVeiculos) {

            atual = v.getLocalizacao().distancia(local_c);
            
            if (atual < distancia_menor) {

                maisPerto = new Veiculo(v);
                distancia_menor = atual;             
            }

        }

        return maisPerto;  
    }

    //-------------------------------------------------------//  

    public EstadoSistema clone () {

        return new EstadoSistema(this);
    }

    public String toString (){
        
        StringBuilder s = new StringBuilder();

        s.append("------------Clientes no Sistema------------\n");
        s.append(this.clientes_Sistema.toString());
        s.append("\n------------Proprietarios no Sistema------------\n");
        s.append(this.proprietarios_Sistema.toString());
        s.append("\n------------Data Sistema------------\n\t" + this.data_atual.toString() + "\n");

        return s.toString();
    }

    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        EstadoSistema e = (EstadoSistema) o;
     
        return(e.getClientesSistema().equals(this.clientes_Sistema) &&
               e.getProprietariosSistema().equals(this.proprietarios_Sistema) &&
               e.getData().equals(this.data_atual));
    }


    //-------------------------------------------------------//  

    public HashMap<Cliente, List<Aluguer>> getClientesSistema () {

       return this.clientes_Sistema.entrySet()
                                   .stream()
                                   .collect(Collectors.toMap(e -> e.getKey(),
                                                             e -> new ArrayList<Aluguer>(e.getValue()),
                                   					        (e1, e2) -> e2,
                                          					 HashMap::new));
    }

    public HashMap<Proprietario, List<Veiculo>> getProprietariosSistema () {

         return this.proprietarios_Sistema.entrySet()
                                           .stream()
                                           .collect(Collectors.toMap(e -> e.getKey(),
                                                                     e -> new ArrayList<Veiculo>(e.getValue()),
                                           					        (e1, e2) -> e2,
                                                  					 HashMap::new));
   }

    public LocalDate getData () {

        return this.data_atual;
    }
}