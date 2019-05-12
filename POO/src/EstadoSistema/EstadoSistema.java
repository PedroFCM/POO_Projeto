
package EstadoSistema;

import ComponentesSistema.Veiculos.*;
import ComponentesSistema.AtorSistema.*;

import BaseClasses.Localizacao;
import BaseClasses.Aluguer;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.lang.Integer;
import java.util.TreeMap;
import java.util.SortedMap;

import java.io.IOException;
import EstadoSistema.ExceptionsProgramFlow.*;

import java.io.BufferedWriter;
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.FileWriter;

import java.util.StringTokenizer;

import java.io.Serializable;

public class EstadoSistema implements Serializable {
    
    private static final long serialVersionUID = 1L;

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
    
    public boolean existeProprietario (String nif) {

      return this.proprietarios_Sistema.containsKey(nif);
    }
    
    public void adicionaProprietario (Proprietario p) {

        if (!this.proprietarios_Sistema.containsKey(p.getNif())) {

              this.proprietarios_Sistema.put(p.getNif(), p.clone());
        }
    }

    public void adicionaCliente (Cliente c) {

        if (!this.clientes_Sistema.containsKey(c.getNif())) {

               this.clientes_Sistema.put(c.getNif(), c.clone());
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

    public Veiculo carroMaisProximo (Cliente c, String preferencia) {

        List<Veiculo> todosVeiculos = new ArrayList<Veiculo>();
        
        if (!preferencia.equals("SemPreferencia")) {

          todosVeiculos = this.allVeiculos().stream().filter(v -> v.getClass()
                                                                   .getSimpleName()
                                                                   .equals(preferencia))
                                                                   .collect(Collectors.toList());
        }

        if (todosVeiculos.isEmpty()) {
            return null;
        }

        Localizacao local_c = c.getLocalizacao();

        double distancia_menor = Integer.MAX_VALUE, atual = Integer.MAX_VALUE;

        Veiculo maisPerto = null;

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
    
    public Cliente getCliente (String nif) {

      return this.clientes_Sistema.get(nif);
    }

    public Proprietario getProprietario (String nif) {

      return this.proprietarios_Sistema.get(nif);
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

    //--------------------------------------------------

    public static void carregarEstadoFromLogs (EstadoSistema estado, String path_logs) 
                          throws IOException {

      //--------------------------------------------------

      String linha = null, novoComponente = null;
      
      StringTokenizer tokens = null;

      String[] camposProp = new String[5];
      String[] camposClie = new String[6];
      String[] camposCarr = new String[11];
      String[] camposAlug = new String[5];

      //--------------------------------------------------
      
      BufferedReader inStream = new BufferedReader(new FileReader(path_logs));

      while ((linha = inStream.readLine()) != null) {
          
        tokens = new StringTokenizer(linha);

        novoComponente = tokens.nextToken(":");

        switch(novoComponente) {

      
        //-----------------------------------------------------------------------------------
          
          case "NovoProp": 

            for (int i = 0; tokens.hasMoreTokens(); i++)
              camposProp[i] = tokens.nextToken(","); 

            AtorSistema novoProp = new Proprietario(camposProp[2],
                                  camposProp[0].replaceAll(":", ""),
                                  camposProp[2],
                                  camposProp[3],
                                  LocalDate.now(),
                                  0,
                                  new ArrayList<>(),
                                  new HashMap<>(),
                                  camposProp[1]);

            estado.adicionaProprietario((Proprietario) novoProp);

            break;

        //-----------------------------------------------------------------------------------

          case "NovoCliente":

            for (int i = 0; tokens.hasMoreTokens(); i++)
              camposClie[i] = tokens.nextToken(","); 

            AtorSistema novoClie = new Cliente (camposClie[2],
                                camposClie[0].replaceAll(":", ""),
                                camposClie[2],
                                camposClie[3],
                                LocalDate.now(),
                                new Localizacao(Double.parseDouble(camposClie[4]), 
                                      Double.parseDouble(camposClie[5])),
                              new ArrayList<>(),
                              0,
                              camposClie[1]);

            estado.adicionaCliente ((Cliente) novoClie);


            break;

        //-----------------------------------------------------------------------------------

          case "NovoCarro":

            for (int i = 0; tokens.hasMoreTokens(); i++)
              camposCarr[i] = tokens.nextToken(","); 

            String tipoDeCarro = camposCarr[0].replaceAll(":", "");
      
            switch (tipoDeCarro) {

              case "Electrico":
                              
                VeiculoComAutonomia eletrico 
                  = new CarroEletrico(camposCarr[2],
                              Double.parseDouble(camposCarr[4]),
                              Double.parseDouble(camposCarr[5]),
                              0.0,
                              new Localizacao(Double.parseDouble(camposCarr[8]),
                                        Double.parseDouble(camposCarr[9])),
                              Double.parseDouble(camposCarr[6]),
                              100,
                              Double.parseDouble(camposCarr[7]),
                              "?",
                              true,
                              camposCarr[1]);

                if (estado.existeProprietario(camposCarr[3])) {

                  estado.getProprietario(camposCarr[3])
                        .adicionaVeiculo(eletrico);
                }

                break;

              case "Gasolina": 

                VeiculoComAutonomia gasolina 
                  = new CarroGasolina(camposCarr[2],
                              Double.parseDouble(camposCarr[4]),
                              Double.parseDouble(camposCarr[5]),
                              0.0,
                              new Localizacao(Double.parseDouble(camposCarr[8]),
                                        Double.parseDouble(camposCarr[9])),
                              Double.parseDouble(camposCarr[6]),
                              100,
                              Double.parseDouble(camposCarr[7]),
                              "?",
                              true,
                              camposCarr[1]);

                if (estado.existeProprietario(camposCarr[3])) {

                  estado.getProprietario(camposCarr[3])
                        .adicionaVeiculo(gasolina);
                }

                break;

              case "Hibrido":

                VeiculoComAutonomia hibrido 
                  = new CarroHibrido(camposCarr[2],
                              Double.parseDouble(camposCarr[4]),
                              Double.parseDouble(camposCarr[5]),
                              0.0,
                              new Localizacao(Double.parseDouble(camposCarr[8]),
                                        Double.parseDouble(camposCarr[9])),
                              Double.parseDouble(camposCarr[6]),
                              100,
                              Double.parseDouble(camposCarr[7]),
                              "?",
                              true,
                              camposCarr[1]);

                if (estado.existeProprietario(camposCarr[3])) {

                  estado.getProprietario(camposCarr[3])
                        .adicionaVeiculo(hibrido);
                }

                break;
    
              default: break;

            }

            break;

        //-----------------------------------------------------------------------------------

        case "Aluguer": 

            //-----------------------------------------------------------------------------------

            for (int i = 0; tokens.hasMoreTokens(); i++)
              camposAlug[i] = tokens.nextToken(","); 

            //-----------------------------------------------------------------------------------

            String tipoCarroAlugado = camposAlug[3];
            String cli_nif = camposAlug[0].replaceAll(":", "");
            Cliente cli_aluguer = estado.getCliente(cli_nif);
            String tipoCombustivel = "Carro" + camposAlug[3];

            if (tipoCombustivel.equals("CarroElectrico")) {

              tipoCombustivel = tipoCombustivel.replaceAll("Electrico", 
                                                           "Eletrico");
            }

            //-----------------------------------------------------------------------------------

            if (cli_aluguer == null) 
              continue;

            Localizacao destino = new Localizacao(Double.parseDouble(camposAlug[1]),
                                                  Double.parseDouble(camposAlug[2]));

            double distancia = cli_aluguer.getLocalizacao().distancia(destino);

            //-----------------------------------------------------------------------------------

            switch (camposAlug[4]) {

              case "MaisPerto": 
                
                Veiculo novo = estado.carroMaisProximo(cli_aluguer, tipoCombustivel)
                                     .clone();

                if (novo == null) continue;

                Aluguer novoAluguer = new Aluguer (novo.getMatricula(),
                                                   cli_nif,
                                                   novo.getProprietario(),
                                                   distancia,
                                                   novo.getPrecoPorKM() * distancia,
                                                   LocalDate.now());

                cli_aluguer.adicionaAluguer(novoAluguer);

                

                break;

              case "MaisBarato": 


                break;

              default: break;
            }

          break;

        //-----------------------------------------------------------------------------------

          default: break;

        //-----------------------------------------------------------------------------------

        }
      }
    }


}