
package EstadoSistema;

import ComponentesSistema.Veiculos.*;
import ComponentesSistema.AtorSistema.*;

import BaseClasses.Localizacao;
import BaseClasses.Aluguer;

import EstadoSistema.ExceptionsProgramFlow.*;

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
import java.util.HashSet;

import java.io.Serializable;

public class EstadoSistema implements Serializable {
    
    private static final long serialVersionUID = 1L;

    //-------------------------------------------------------//  
    
    //Key: Cliente; Value: Nome
    private Map<String, Cliente> clientes_Sistema;

    private Map<String, Proprietario> proprietarios_Sistema;
    
    private Map<String, Veiculo> veiculos_Sistema;

    private LocalDate data_atual;
    
    //-------------------------------------------------------//  

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

    public EstadoSistema (EstadoSistema novo) {

        this.clientes_Sistema = novo.getClientesSistema();
        this.proprietarios_Sistema = novo.getProprietariosSistema();
        this.data_atual = novo.getData();
        this.veiculos_Sistema = novo.getVeiculosSistema();
    }

    public EstadoSistema () {

        this.clientes_Sistema = new HashMap<String, Cliente>();
        this.proprietarios_Sistema = new HashMap<String, Proprietario>();
        this.veiculos_Sistema = new HashMap<String, Veiculo>();
        this.data_atual = LocalDate.now();
    }

    //-------------------------------------------------------//  
    
    public boolean existeProprietario (String nif) {

      return this.proprietarios_Sistema.containsKey(nif);
    }

    public boolean existeMatricula (String matricula) {

      return this.veiculos_Sistema.containsKey(matricula);
    }
    
    public boolean existeCliente (String nif) {

      return this.clientes_Sistema.containsKey(nif);
    }

    public void adicionaProprietario (Proprietario p) {

        if (!this.proprietarios_Sistema.containsKey(p.getNif())) {

              this.proprietarios_Sistema.put(p.getNif(), p.clone());
        }
    }

    public void replaceProprietario (Proprietario p) {

        if (!this.proprietarios_Sistema.containsKey(p.getNif())) {

              this.proprietarios_Sistema.put(p.getNif(), p.clone());
        } else {

          this.proprietarios_Sistema.replace(p.getNif(), p.clone());
        }
    }

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
                           new HashMap<>(), nif);

      if (this.existeProprietario(nif))
        throw new AtorAlreadyExistsException("Nif " + nif + " já existente!");
      else {

        this.adicionaProprietario((Proprietario) novoProp);
      }
    }

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
                           nif);

      if (this.existeCliente(nif))
        throw new AtorAlreadyExistsException("Nif " + nif + " já existente!");
     
      else {

        this.adicionaCliente((Cliente) novoCliente);
      }
    }

    public void adicionaCliente (Cliente c) {

        if (!this.clientes_Sistema.containsKey(c.getNif())) {

               this.clientes_Sistema.put(c.getNif(), c.clone());
        }
    }

    public void adicionaVeiculoSistema (Veiculo novo) {

      this.veiculos_Sistema.put(novo.getMatricula(), novo.clone());
    }

    public void replaceVeiculoSistema (Veiculo novo) {

      this.veiculos_Sistema.replace(novo.getMatricula(), novo.clone());
    }

    public void replaceClienteSistema (Cliente c) {


      this.clientes_Sistema.replace(c.getNif(), c.clone());
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
        
        SortedMap<Double, Veiculo> 
       	veiculosOrdenadosPorPreco = new TreeMap<Double, Veiculo>();

        for (Veiculo v: todosVeiculos) {

          veiculosOrdenadosPorPreco.put(v.getPrecoPorKM(), v.clone());
        }

        List<Veiculo> l = new ArrayList<>();

        Veiculo v = veiculosOrdenadosPorPreco.get(veiculosOrdenadosPorPreco.firstKey());

        l = todosVeiculos.stream()
                         .filter(v1 -> v1.getPrecoPorKM() == v.getPrecoPorKM())
                         .collect(Collectors.toList());

        
        return l;  
    }

    public List<Veiculo> carroMaisBarato (Cliente c, Double distanciaMax, String preferencia) 
            throws CarNotAvailableException {

      List<Veiculo> maisBaratos = carroMaisBarato(preferencia);

      Localizacao clienteLocal = c.getLocalizacao();
    
      List<Veiculo> result = maisBaratos.stream()
                                        .filter(v -> (clienteLocal.distancia(v.getLocalizacao()) <= distanciaMax))
                                        .collect(Collectors.toList());

      return result;  
    }

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

    public void consumirkmAluguer (VeiculoComAutonomia v, Double dist) {

      Double atual = v.getAutonomiaAtual();

      v.setAutonomiaAtual((atual-dist)>=0?(atual-dist):0.0);
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
    
    public Cliente getCliente (String nif) {

      return this.clientes_Sistema.get(nif);
    }

    public Proprietario getProprietario (String nif) {

      return this.proprietarios_Sistema.get(nif);
    }

    public Veiculo getVeiculo (String matricula) {

      return this.veiculos_Sistema.get(matricula);
    }

    public Map<String, Veiculo> getVeiculosSistema () {

       return this.veiculos_Sistema.entrySet()
                                   .stream()
                                   .collect(Collectors.toMap(e -> e.getKey(),
                                                             e -> e.getValue(),
                                                            (e1, e2) -> e2,
                                                             HashMap::new));
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

   public int getNumProprietarios() {

       return this.proprietarios_Sistema.size();
   }

   public int getNumClientes() {

       return this.clientes_Sistema.size();
   }

   public int getNumVeiculos() {

     return this.allVeiculos().size();
   }

}