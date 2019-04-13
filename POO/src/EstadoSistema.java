import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.lang.Integer;
import java.util.TreeMap;
import java.util.SortedMap;

public class EstadoSistema {
    
    //-------------------------------------------------------//  
    
    //Key: Cliente; Value: Nome
    private Map<Cliente, String> clientes_Sistema;

    private Map<Proprietario, List<Veiculo>> proprietarios_Sistema;
    
    private LocalDate data_atual;
    
    //-------------------------------------------------------//  

    public EstadoSistema (Map<Cliente, String> clientes,
                          Map<Proprietario, List<Veiculo>> proprietarios,
                          LocalDate data) {

        this.clientes_Sistema = clientes.entrySet()
                                        .stream()
                                        .collect(Collectors.toMap(e -> e.getKey(),
                                                                  e -> e.getValue(),
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

        this.clientes_Sistema = new HashMap<Cliente, String>();
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

			   this.clientes_Sistema.put(c.clone(), c.getNome());
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

    public Veiculo carroMaisBarato () {

        List<Veiculo> todosVeiculos = new ArrayList<Veiculo>();
        
        todosVeiculos =  this.allVeiculos();

        if (todosVeiculos.isEmpty()) {
            return null;
        }
        
       SortedMap<Double, Veiculo> veiculosOrdenadosPorPreco = 
                            new TreeMap<Double, Veiculo>();

        for (Veiculo v: todosVeiculos) {

          veiculosOrdenadosPorPreco.put(v.getPrecoPorKM(), v.clone());
        }

        return veiculosOrdenadosPorPreco.get(veiculosOrdenadosPorPreco.firstKey());  
    }

    public Veiculo carroMaisBarato (Cliente c, Double distanciaMax) {

        List<Veiculo> todosVeiculos = new ArrayList<Veiculo>();
        
        todosVeiculos =  this.allVeiculos();

        if (todosVeiculos.isEmpty()) {
            return null;
        }

        SortedMap<Double, Veiculo> veiculosOrdenadosPorPreco = 
                            new TreeMap<Double, Veiculo>();
        
        for (Veiculo v: todosVeiculos) {

          if (c.getLocalizacao().distancia(v.getLocalizacao()) <= distanciaMax) {
            
            veiculosOrdenadosPorPreco.put(v.getPrecoPorKM(), v.clone());
          }
        
        }

        if (veiculosOrdenadosPorPreco.size()==0) return null;

        return veiculosOrdenadosPorPreco.get(veiculosOrdenadosPorPreco.firstKey());  
    }

    public List<Veiculo> carroEspecifico (String tipoDeCarroDesejado) {

      List<Veiculo> todosVeiculos = new ArrayList<Veiculo>();    
      todosVeiculos =  this.allVeiculos();

      if (todosVeiculos.isEmpty()) {
          return null;
      }

      List <Veiculo> carroEspecifico = new ArrayList<Veiculo>();

      String currentClass;

      for (Veiculo v: todosVeiculos) {

        currentClass = v.getClass().getSimpleName();

        if (currentClass.equals(tipoDeCarroDesejado)) {

          carroEspecifico.add(v);

        } else if (currentClass.equals(tipoDeCarroDesejado)) {

          carroEspecifico.add(v);
        }

      }

      return carroEspecifico;
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

    public Map<Cliente, String> getClientesSistema () {

       return this.clientes_Sistema.entrySet()
                                   .stream()
                                   .collect(Collectors.toMap(e -> e.getKey(),
                                                             e -> e.getValue(),
                                   					        (e1, e2) -> e2,
                                          					 HashMap::new));
    }

    public Map<Proprietario, List<Veiculo>> getProprietariosSistema () {

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