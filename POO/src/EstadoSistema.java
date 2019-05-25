//-------------------------------------------------------------------------------------------------------------

/**
 * Class que implementa o Estado do Sistema da APP.
 * Possui as funcionalidades que a APP apresenta.
 *
 * @author João Pedro Rodrigues Azevedo
 * @author Pedro Filipe Costa Machado
 * @author Paulo Jorge da Silva Araújo 
 *
 * @version 2019/05/25
 */

//-------------------------------------------------------------------------------------------------------------

import ExceptionsProgramFlow.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.lang.Integer;
import java.util.TreeMap;
import java.util.SortedMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;

import java.io.Serializable;

//-------------------------------------------------------------------------------------------------------------

public class EstadoSistema implements Serializable {
    
    //-------------------------------------------------------------------------------------------------------------

    /* - Serial version - */
    
    private static final long serialVersionUID = 1L;

    //-------------------------------------------------------------------------------------------------------------
    
    /*
     * Map de clientes do sistema, Key = nif.
    */
    private Map<String, Cliente> clientes_Sistema;
    /*
     * Map de proprietarios do sistema, Key = nif.
    */
    private Map<String, Proprietario> proprietarios_Sistema;
    /*
     * Map de veiculos do sistema, Key = matricula.
    */
    private Map<String, Veiculo> veiculos_Sistema;
    /*
     * Data de inicializacao do sistema
    */
    private LocalDate data_atual;
    
    //-------------------------------------------------------------------------------------------------------------

    /**
     * Construtor parameterizado da classe EstadoSistema
     *
     * @param clientes Map de clientes
     * @param proprietarios Map de proprietarios
     * @param veiculos Map de veiculos
     * @param data Data de criação do sistema
     *
    */

    public EstadoSistema (Map<String, Cliente> clientes,
                          Map<String, Proprietario> proprietarios,
                          Map<String, Veiculo> veiculos,
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

        this.veiculos_Sistema = veiculos.entrySet()
                                        .stream()
                                        .collect(Collectors.toMap(e -> e.getKey(),
                                                                            e -> e.getValue(),
                                                                            (e1, e2) -> e2,
                                                                            HashMap::new));
        this.data_atual = data;
    }
    
    /**
     * Construtor cópia de um EstadoSistema
     *           
     * @param novo Estado de Sistema novo.
     *
    */

    public EstadoSistema (EstadoSistema novo) {

        this.clientes_Sistema = novo.getClientesSistema();
        this.proprietarios_Sistema = novo.getProprietariosSistema();
        this.data_atual = novo.getData();
        this.veiculos_Sistema = novo.getVeiculosSistema();
    }

    /**
     * Construtor vazio de Estado Sistema
     *
    */

    public EstadoSistema () {

        this.clientes_Sistema = new HashMap<String, Cliente>();
        this.proprietarios_Sistema = new HashMap<String, Proprietario>();
        this.veiculos_Sistema = new HashMap<String, Veiculo>();
        this.data_atual = LocalDate.now();
    }

    //---------------------------------------------------------------------------------------------------------
   
    /**
     * Verifica se um proprietario existe no map de proprietarios
     *
     * @param nif nif do proprietario.
     *
     * @return true caso exista
    */
   
    public boolean existeProprietario (String nif) {

      return this.proprietarios_Sistema.containsKey(nif);
    }

    /**
     * Verifica se um veiculo existe no map de veiculos
     *
     * @param matricula matricula do veiculo.
     *
     * @return true caso exista
    */

    public boolean existeMatricula (String matricula) {

      return this.veiculos_Sistema.containsKey(matricula);
    }

    /**
     * Verifica se um cliente existe no map de clientes
     *
     * @param nif nif do cliente.
     *
     * @return true caso exista
    */

    public boolean existeCliente (String nif) {

      return this.clientes_Sistema.containsKey(nif);
    }

    /**
     * Adiciona um proprietario ao sistema
     *
     * @param p novo proprietario.
     *
    */

    public void adicionaProprietario (Proprietario p) {

        if (!this.proprietarios_Sistema.containsKey(p.getNif())) {

              this.proprietarios_Sistema.put(p.getNif(), p.clone());
        }
    }

    /**
     * Substitui um proprietario do sistema
     *
     * @param p novo proprietario.
     *
    */

    public void replaceProprietario (Proprietario p) {

        if (!this.proprietarios_Sistema.containsKey(p.getNif())) {

              this.proprietarios_Sistema.put(p.getNif(), p.clone());
        } else {

          this.proprietarios_Sistema.replace(p.getNif(), p.clone());
        }
    }

    /**
     * Cria e adiciona um proprietario ao sistema
     *
     * @param email email do proprietario.
     * @param nome nome do proprietario.
     * @param passw passw do proprietario.
     * @param morada morada do proprietario.
     * @param nif nif do proprietario.
     *
    */

    public void criaAdicionaProprietario(String email,
                                         String nomeAtor,
                                         String passw,
                                         String morada,
                                         String nif) 
      throws AtorAlreadyExistsException {

      AtorSistema novoProp 
      = new Proprietario(email, nomeAtor,
                           passw, morada,
                           LocalDate.now(), 0,
                           new ArrayList<>(),
                           new HashMap<>(), nif, new ArrayList<>());

      if (this.existeProprietario(nif) || this.existeCliente(nif))
        throw new AtorAlreadyExistsException("Nif " + nif + " já existente!");
      else {

        this.adicionaProprietario((Proprietario) novoProp);
      }
    }

    /**
     * Cria e adiciona um cliente ao sistema
     *
     * @param email email do cliente.
     * @param nome nome do cliente.
     * @param passw passw do cliente.
     * @param morada morada do cliente.
     * @param nif nif do cliente.
     * @param local_cliente Localizacao do cliente
     *
    */

    public void criaAdicionaCliente(String email,
                                    String nomeAtor,
                                    String passw,
                                    String morada,
                                    String nif,
                                    Localizacao local_cliente)

      throws AtorAlreadyExistsException {

      AtorSistema novoCliente 
      = new Cliente(email, nomeAtor,
                           passw, morada,
                           LocalDate.now(), 
                           local_cliente,
                           new ArrayList<>(),
                           0,
                           nif,
                           new ArrayList<>());

      if (this.existeCliente(nif) || this.existeProprietario(nif))
        throw new AtorAlreadyExistsException("Nif " + nif + " já existente!");
     
      else {

        this.adicionaCliente((Cliente) novoCliente);
      }
    }

    /**
     * Adiciona um cliente ao sistema
     *
     * @param c novo cliente.
     *
    */

    public void adicionaCliente (Cliente c) {

        if (!this.clientes_Sistema.containsKey(c.getNif())) {

               this.clientes_Sistema.put(c.getNif(), c.clone());
        }
    }

    /**
     * Adiciona um veiculo ao sistema
     *
     * @param novo novo veiculo.
     *
    */

    public void adicionaVeiculoSistema (Veiculo novo) {

      this.veiculos_Sistema.put(novo.getMatricula(), novo.clone());
    }

    /**
     * Substitui um veiculo do sistema
     *
     * @param novo novo veiculo.
     *
    */

    public void replaceVeiculoSistema (Veiculo novo) {

      this.veiculos_Sistema.replace(novo.getMatricula(), novo.clone());
    }

    /**
     * Substitui um cliente do sistema
     *
     * @param c novo cliente.
     *
    */

    public void replaceClienteSistema (Cliente c) {


      this.clientes_Sistema.replace(c.getNif(), c.clone());
    }    

    /**
     * Adiciona um pedido de aluguer a um proprietario
     *
     * @param a o aluguer.
     *
    */

    public void enviarAluguerProprietario (Aluguer a) {

      Proprietario p = this.getProprietario(a.getProprietario());

      p.adicionaPedidoAluguer(a.clone());

      this.replaceProprietario(p);
    }

    /**
     * Apresenta todos os veiculos do sistema numa lista
     *
     * @return Lista de veiculos
     *
    */

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

    /**
     * Calcula o carro mais proximo de uma localizacao
     * Devolve uma lista pois pode existir 2 carros na mesma posicao
     *
     * @param c Cliente
     * @param preferencia Tipo de carro
     *
     * @throws CarNotAvailableException Caso nao haja carros
     *
     * @return Lista de veiculos
     *
    */

    public List<Veiculo> carroMaisProximo (Cliente c, String preferencia) 
          throws CarNotAvailableException {

        List<Veiculo> todosVeiculos = new ArrayList<Veiculo>();
        List<Veiculo> matched       = new ArrayList<>();

        if (!preferencia.equals("SemPreferencia")) {

          todosVeiculos = this.veiculos_Sistema.values()
                                               .stream()
                                               .filter(v -> v.getClass()
                                                             .getSimpleName()
                                                             .equals(preferencia))
                                                             .collect(Collectors.toList());
        
        } else {

           todosVeiculos = this.veiculos_Sistema.values()
                                               .stream()
                                               .collect(Collectors.toList());
        }

        if (todosVeiculos.isEmpty()) {

            throw new CarNotAvailableException("Não existem carros disponivéis...");
        }

        Localizacao local_c = c.getLocalizacao();

        double distancia_menor = Integer.MAX_VALUE, atual = Integer.MAX_VALUE;

        for (Veiculo v: todosVeiculos) {

            atual = v.getLocalizacao().distancia(local_c);
            
            if (atual <= distancia_menor) {

                matched.add(v.clone());
                distancia_menor = atual;             
            }
        }

        final Double x = distancia_menor;

        Veiculo last = matched.get(matched.size() - 1);

        List<Veiculo> res = matched.stream()
                                   .filter(v -> (x == v.getLocalizacao()
                                                       .distancia(local_c)))
                                   .collect(Collectors.toList()); 

        return res;  
    }

    /**
     * Calcula o carro mais barato do sistema
     * Devolve uma lista pois pode existir 2 carros com o mesmo preco
     *
     * @param preferencia Tipo de carro
     *
     * @throws CarNotAvailableException Caso nao haja carros
     *
     * @return Lista de veiculos
     *
    */

    public List<Veiculo> carroMaisBarato (String preferencia) 
      throws CarNotAvailableException {

        List<Veiculo> todosVeiculos = new ArrayList<Veiculo>();
        
        if (!preferencia.equals("SemPreferencia")) {

          todosVeiculos = this.veiculos_Sistema.values()
                                               .stream()
                                               .filter(v -> v.getClass()
                                                             .getSimpleName()
                                                             .equals(preferencia))
                                                             .collect(Collectors.toList());
        }  else {

           todosVeiculos = this.veiculos_Sistema.values()
                                               .stream()
                                               .collect(Collectors.toList());
        }       

        if (todosVeiculos.isEmpty()) {
             throw new CarNotAvailableException("Não existem carros disponivéis...");
        }
        
        TreeSet<Veiculo> veiculosOrdenadosPorPreco = new TreeSet<>(new ComparatorVeiculoPrecoKM());

        for (Veiculo v: todosVeiculos) {

          veiculosOrdenadosPorPreco.add(v.clone());
        }

        List<Veiculo> l = new ArrayList<>();

        Veiculo v = veiculosOrdenadosPorPreco.last();

        l = todosVeiculos.stream()
                         .filter(v1 -> v1.getPrecoPorKM() == v.getPrecoPorKM())
                         .collect(Collectors.toList());

        return l;  
    }

    /**
     * Calcula o carro mais barato do sistema numa distancia x
     * Devolve uma lista pois pode existir 2 ou mais carros
     *
     * @param c Cliente
     * @param distanciaMax Distancia maxima que esta disposta a percorrer
     * @param preferencia Tipo de carro
     *
     * @throws CarNotAvailableException Caso nao haja carros
     *
     * @return Lista de veiculos
     *
    */

    public List<Veiculo> carroMaisBarato (Cliente c, Double distanciaMax, String preferencia) 
            throws CarNotAvailableException {

      List<Veiculo> maisBaratos = carroMaisBarato(preferencia);

      Localizacao clienteLocal = c.getLocalizacao();
    
      List<Veiculo> result = maisBaratos.stream()
                                        .filter(v -> (clienteLocal.distancia(v.getLocalizacao()) <= distanciaMax))
                                        .collect(Collectors.toList());

      return result;  
    }

    /**
     * Calcula os carros com o mesmo ou mais de autonomia que o argumento
     * Devolve uma lista pois pode existir 2 ou mais carros
     *
     * @param autonomia autonomia minima
     * @param preferencia Tipo de carro
     *
     * @throws CarNotAvailableException Caso nao haja carros
     *
     * @return Lista de veiculos
     *
    */

    public List<Veiculo> carroComCertaAutonomia(String preferencia, Double autonomia)
          throws CarNotAvailableException {

      List<Veiculo> todosVeiculos = new ArrayList<Veiculo>();
        
        if (!preferencia.equals("SemPreferencia")) {

          todosVeiculos = this.veiculos_Sistema.values()
                                               .stream()
                                               .filter(v -> v.getClass()
                                                             .getSimpleName()
                                                             .equals(preferencia))
                                                             .collect(Collectors.toList());
        }  else {

           todosVeiculos = this.veiculos_Sistema.values()
                                               .stream()
                                               .collect(Collectors.toList());
        }       

        if (todosVeiculos.isEmpty()) {
             throw new CarNotAvailableException("Não existem carros disponivéis...");
        }
      
        List<Veiculo> result = new ArrayList<>();

        for (Veiculo v: todosVeiculos) {

          if (((VeiculoComAutonomia) v).getAutonomiaAtual() >= autonomia)
            result.add(v);
        }

        return result;
    }

    /**
     * Calcula os carros dada a preferencia [OBSULETO]
     * Devolve uma lista pois pode existir 2 ou mais carros
     *
     * @param tipoDeCarroDesejado Tipo de carro
     *
     * @return Lista de veiculos
     *
    */

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

    /**
     * Consome os km do carro que o aluguer apresenta
     *
     * @param v Veiculo
     * @param dist km para retirar
     *
     * @return Lista de veiculos
     *
    */

    public void consumirkmAluguer (VeiculoComAutonomia v, Double dist) {

      Double atual = v.getAutonomiaAtual();

      v.setAutonomiaAtual((atual-dist)>=0?(atual-dist):0.0);
    }
    

    /**
     * Apresenta a lista dos top10 clientes do sistema
     *
     * @return Lista de Cliente
     *
    */

      public List<Cliente> top10Clientes () {

      //------------------------------------------------------------------

      TreeMap<Cliente, Double> clientesSortedByKmPerc = new TreeMap<>(new ComparatorClienteKmPercorridos());

      //------------------------------------------------------------------

      List<Cliente> clientes = this.clientes_Sistema.values()
                                                    .stream()
                                                    .collect(Collectors.toList());

      //------------------------------------------------------------------

      Double kmAlugados = 0.0;

      for (Cliente c: clientes) {

        clientesSortedByKmPerc.put(c.clone(), c.getTotalKmPercorridos());
      }

      //------------------------------------------------------------------

      TreeMap<Cliente, Double> result = new TreeMap<>();

      int count = 0, max = 10;

      for (Map.Entry<Cliente, Double> e: clientesSortedByKmPerc.entrySet()) {
          
          if (count >= max) break;

          result.put(e.getKey(), e.getValue());
          
          count++;
      }

      return result.keySet().stream().collect(Collectors.toList());
    }

    /**
     * Calcula o total faturado por uma viatura num periodo
     *
     * @param v Veiculo
     * @param dataInf data inferior
     * @param dataSup data superior
     *
     * @return Total faturado
     *
    */

    public Double totalFaturadoNoPeriodoViatura (Veiculo v, LocalDate dataInf, LocalDate dataSup) {

      double totalFaturado = 0.0;

      List<Aluguer> alugueresViaturaV = new ArrayList<>();

      alugueresViaturaV = this.getProprietario(v.getProprietario()).getHistoricoAlugueres();

      for (Aluguer a: alugueresViaturaV) {
          
            if ((a.getData().isEqual(dataInf) || a.getData().isAfter(dataInf)) && 
                (a.getData().isEqual(dataSup) || a.getData().isBefore(dataSup))) {
              
              if (a.getVeiculo().equals(v.getMatricula()))
                totalFaturado += a.getPreco();
            }
      }

      return totalFaturado;
    }

    //-----------------------------------------------------------------------------------  
    
    /**
     * Constrói uma cópia de um EstadoSistema.
     *
     * @return Novo EstadoSistema.
    */

    public EstadoSistema clone () {

        return new EstadoSistema(this);
    }
    
    /**
     * Converte um EstadoSistema para a sua representação em String
     *
     * @return A representação do EstadoSistema em String.
    */

    public String toString (){
        
        StringBuilder s = new StringBuilder();

        s.append("------------Clientes no Sistema------------\n");
        s.append(this.clientes_Sistema.toString());
        s.append("\n------------Proprietarios no Sistema------------\n");
        s.append(this.proprietarios_Sistema.toString());
        s.append("\n------------Data Sistema------------\n\t" + this.data_atual.toString() + "\n");

        return s.toString();
    }

    /**
     * Determina se dois EstadoSistema são iguais.
     *
     * @param o Objeto para fazer comparação.
     *
     * @return true se forem iguais.
    */

    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        EstadoSistema e = (EstadoSistema) o;
     
        return(e.getClientesSistema().equals(this.clientes_Sistema) &&
               e.getProprietariosSistema().equals(this.proprietarios_Sistema) &&
               e.getData().equals(this.data_atual));
    }

    //---------------------------------------------------------------------------------------------------------------  
 
    /**
     * Calcula o mail do proprietario dado um veiculo
     *
     * @param v Veiculo
     *
     * @return mail do proprietario
    */

    public String getMailProprietarioVeiculo(Veiculo v){
    
        List <Proprietario> lista = this.proprietarios_Sistema.values().stream().collect(Collectors.toList());
        
        for(Proprietario p: lista){
            
            if (p.getMapVeiculos().containsKey(v.getMatricula())) 
                return p.getEmail();
        }     
        
        return null;
    }

    /**
     * Devolve o cliente do sistema dado o nif
     *
     * @param nif Nif do cliente
     *
     * @return Cliente que possui aquele nif
    */

    public Cliente getCliente (String nif) {

      return this.clientes_Sistema.get(nif);
    }

    /**
     * Devolve o proprietario do sistema dado o nif
     *
     * @param nif Nif do proprietario
     *
     * @return proprietario que possui aquele nif
    */

    public Proprietario getProprietario (String nif) {

      return this.proprietarios_Sistema.get(nif);
    }

    /**
     * Devolve o veiculo do sistema dado o nif
     *
     * @param nif Nif do veiculo
     *
     * @return veiculo que possui aquele nif
    */

    public Veiculo getVeiculo (String matricula) {

      return this.veiculos_Sistema.get(matricula);
    }

    /**
     * Devolve o Map de veiculos do sistema
     *
     * @return Veiculos do sistema
    */

    public Map<String, Veiculo> getVeiculosSistema () {

       return this.veiculos_Sistema.entrySet()
                                   .stream()
                                   .collect(Collectors.toMap(e -> e.getKey(),
                                                             e -> e.getValue(),
                                                            (e1, e2) -> e2,
                                                             HashMap::new));
    }

    /**
     * Devolve o Map de clientes do sistema
     *
     * @return Veiculos do sistema
    */

    public Map<String, Cliente> getClientesSistema () {

       return this.clientes_Sistema.entrySet()
                                   .stream()
                                   .collect(Collectors.toMap(e -> e.getKey(),
                                                             e -> e.getValue(),
                                                            (e1, e2) -> e2,
                                                             HashMap::new));
    }

    /**
     * Devolve o Map de clientes do sistema
     *
     * @return Proprietarios do sistema
    */

    public Map<String, Proprietario> getProprietariosSistema () {

         return this.proprietarios_Sistema.entrySet()
                                           .stream()
                                           .collect(Collectors.toMap(e -> e.getKey(),
                                                                     e -> e.getValue(),
                                                                    (e1, e2) -> e2,
                                                                     HashMap::new));
   }

    /**
     * Devolve o Data de criação do sistema
     *
     * @return Data
    */

   public LocalDate getData () {

       return this.data_atual;
   }
    /**
     * Devolve o numero de proprietarios registados
     *
     * @return Data
    */

   public int getNumProprietarios() {

       return this.proprietarios_Sistema.size();
   }
   
    /**
     * Devolve o numero de clientes registados
     *
     * @return Data
    */
   
   public int getNumClientes() {

       return this.clientes_Sistema.size();
   }
    
    /**
     * Devolve o numero de veiculos registados
     *
     * @return Data
    */

   public int getNumVeiculos() {

     return this.allVeiculos().size();
   }

}