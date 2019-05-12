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
    private Map<String, Cliente> clientes_Sistema;

    private Map<String, Proprietario> proprietarios_Sistema;
    
    private LocalDate data_atual;
    
    //-------------------------------------------------------//  

    public EstadoSistema (Map<String, Cliente> clientes,
                          Map<String, Proprietario> proprietarios,
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
                                                                            e -> e.getValue(),
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

        this.clientes_Sistema = new HashMap<String, Cliente>();
        this.proprietarios_Sistema = new HashMap<String, Proprietario>();
        this.data_atual = LocalDate.now();
    }

    //-------------------------------------------------------//  
    
    public boolean existeProprietario (String email) {

      return this.proprietarios_Sistema.containsKey(email);
    }
    
    public void adicionaProprietario (Proprietario p) {

        if (!this.proprietarios_Sistema.containsKey(p.getEmail())) {

              this.proprietarios_Sistema.put(p.getEmail(), p.clone());
        }
    }

    public void adicionaCliente (Cliente c) {

        if (!this.clientes_Sistema.containsKey(c.getEmail())) {

               this.clientes_Sistema.put(c.getEmail(), c.clone());
        }
    }

    public List<Veiculo> allVeiculos() {

        List<Proprietario> listaP = new ArrayList<>();
        List<Veiculo> listaV = new ArrayList<>();
        
        listaP = this.proprietarios_Sistema.values()
                                           .stream()
                                           .collect(Collectors.toList());
        for(Proprietario p: listaP){
        
            listaV.addAll(p.getListaVeiculos());
        }
   
        return listaV;
    }

    public Veiculo carroMaisProximo (Cliente c) {

        List<Veiculo> todosVeiculos = new ArrayList<Veiculo>();
        
        todosVeiculos =  this.allVeiculos();

        if (todosVeiculos.isEmpty()) {
            return null;
        }

        Localizacao local_c = c.getLocalizacao();

        double distancia_menor = Integer.MAX_VALUE, atual = Integer.MAX_VALUE;

        Veiculo maisPerto = new CarroGasolina();

        for (Veiculo v: todosVeiculos) {

            atual = v.getLocalizacao().distancia(local_c);
            
            if (atual < distancia_menor) {

                maisPerto = v.clone();
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
    public String getMailProprietarioVeiculo(Veiculo v){
    
        List <Proprietario> lista = this.proprietarios_Sistema.values().stream().collect(Collectors.toList());
        
        for(Proprietario p: lista){
            
            if (p.getMapVeiculos().containsKey(v.getMatricula())) 
                return p.getEmail();
        }     
        
        return null;
    }
    
    public Cliente getCliente (String email) {

      return this.clientes_Sistema.get(email);
    }

    public Proprietario getProprietario (String email) {

      return this.proprietarios_Sistema.get(email);
    }

    public Map<String, Cliente> getClientesSistema () {

       return this.clientes_Sistema.entrySet()
                                   .stream()
                                   .collect(Collectors.toMap(e -> e.getKey(),
                                                             e -> e.getValue(),
                                                            (e1, e2) -> e2,
                                                             HashMap::new));
    }

    public Map<String, Proprietario> getProprietariosSistema () {

         return this.proprietarios_Sistema.entrySet()
                                           .stream()
                                           .collect(Collectors.toMap(e -> e.getKey(),
                                                                     e -> e.getValue(),
                                                                    (e1, e2) -> e2,
                                                                     HashMap::new));
   }

    public LocalDate getData () {

        return this.data_atual;
    }
}