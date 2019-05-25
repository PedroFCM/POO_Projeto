//-------------------------------------------------------------------------------------------------------------

/**
 * Class que implementa um Proprietario, cuja superclass é AtorSistema. 
 *
 * @author João Pedro Rodrigues Azevedo
 * @author Pedro Filipe Costa Machado
 * @author Paulo Jorge da Silva Araújo 
 *
 * @version 2019/05/25
 */

//-------------------------------------------------------------------------------------------------------------

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
import java.lang.Math;

import java.io.Serializable;

//-------------------------------------------------------------------------------------------------------------

public class Proprietario extends AtorSistema {

    //-------------------------------------------------------------------------------------------------------------

    /*
     * Map de matricula para veiculo, armazena os veiculos dum proprietario
    */
    private Map<String,Veiculo> mapVeiculos;
    /*
     * Lista de pedidos de aluguer do proprietario
    */    
    private List<Aluguer> pedidosAluguer;

    //-------------------------------------------------------------------------------------------------------------

    /**
     * Construtor parameterizado da classe CarroEletrico
     *
     * @param email Email do cliente.
     * @param nome Nome do cliente.
     * @param password Password do cliente
     * @param morada Morada do cliente
     * @param data Data de criação do cliente
     * @param historico Historico de Alugueres do cliente
     * @param classificacao Classificacao do cliente
     * @param nif Nif do cliente
     * @param pedidos Lista de Alugueres (pedidos)
     * @param veiculos Map de veiculos do proprietario
     *
    */

    public Proprietario(String email, 
                        String nome, 
                        String password, 
                        String morada, 
                        LocalDate data, 
                        int classificacao,
                        List<Aluguer> historico,
                        Map<String,Veiculo> veiculos,
                        String nif,
                        List<Aluguer> pedidos) {
     
        super(email, nome, password, morada, data, classificacao, historico, nif);
        
        this.mapVeiculos = veiculos.entrySet()
                                        .stream()
                                        .collect(Collectors.toMap(e -> e.getKey(),
                                                                  e -> e.getValue(),
                                                                    (e1, e2) -> e2,
                                                                    HashMap::new));
    
        this.pedidosAluguer = pedidos.stream().map(Aluguer::clone).collect(Collectors.toList());
    }

    /**
     * Construtor copia da classe Proprietario
     *
     * @param umProprietario novo Proprietario.
     *
    */

    public Proprietario (Proprietario umProprietario) {
    
        super(umProprietario);
        this.mapVeiculos = umProprietario.getMapVeiculos();
        this.pedidosAluguer = umProprietario.getPedidosAluguer();
    }
 
    /**
     * Construtor vazio da classe Proprietario
    */

    public Proprietario () {
        
        super();
        this.mapVeiculos = new HashMap<String, Veiculo>();
        this.pedidosAluguer = new ArrayList<>();
    }

    //-------------------------------------------------------------------------------------------------------  

    /**
     * Converte um proprietario para a sua representação em String
     *
     * @return A representação do proprietario em String.
    */

    public String toString (){
    
        StringBuilder s =  new StringBuilder();

        s.append("\nProprietario do sistema:");
        s.append(super.toString()).append("Carros: ");
        s.append(this.getListaVeiculos().toString()).append("\n");

        return s.toString();
    }

    /**
     * Determina se dois proprietario são iguais.
     *
     * @param o Objeto para fazer comparação.
     *
     * @return true se forem iguais.
    */

    public boolean equals(Object o) {
        
        if (this == o) return true;
        
        if ((o == null) || (this.getClass() != o.getClass()))
            return false;
        
        Proprietario p = (Proprietario) o;

        boolean veiculosIgual = true;
     
        return (super.equals(p) &&
                p.getListaVeiculos().equals(this.mapVeiculos));
    }

    /**
     * Constrói uma cópia de um proprietario.
     *
     * @return Novo proprietario.
    */

    public Proprietario clone() {

        return new Proprietario(this);
    }

    //---------------------------------------------------------------------------------------------- 

    /**
     * Remove um aluguer da lista de pedidos.
     *
     * @param a aluguer.
    */

    public void removePedidoAluguer (Aluguer a) {

        if (this.pedidosAluguer.contains(a)) {

            this.pedidosAluguer.remove(a);
        }
    }

    /**
     * Adiciona veiculo a lista de veiculos.
     *
     * @param v veiculo.
    */

    public void adicionaVeiculo (Veiculo v) {

        
        if (!this.mapVeiculos.containsKey(v.getMatricula())) {

            this.mapVeiculos.put(v.getMatricula(),v.clone());
            this.sinalizarVeiculo(mapVeiculos.get(v.getMatricula()));
        }
    }

    /**
     * Adiciona pedido a lista de pedidos.
     *
     * @param a aluguer.
    */

    public void adicionaPedidoAluguer (Aluguer a) {

        this.pedidosAluguer.add(a);
    }

    /**
     * Susbstitui um veiculo do Map de veiculos por um novo.
     *
     * @param novo novo veiculo.
    */

    public void replaceVeiculo (Veiculo novo) {

        if (!this.mapVeiculos.containsKey(novo.getMatricula())) {

              this.mapVeiculos.put(novo.getMatricula(), novo.clone());
        } else {

          this.mapVeiculos.replace(novo.getMatricula(), novo.clone());
        }

    }

    /**
     * Altera o preco por km de um veiculo
     *
     * @param v Veiculo.
     * @param preco preco do veiculo.
    */

    public void alteraPrecoPorKm(Veiculo v, double preco){
        
        if(this.mapVeiculos.containsValue(v)){
            
            this.mapVeiculos.get(v.getMatricula()).setPrecoPorKm(preco);
        }
    }
    
    /**
     * Devolve o map de veiculos do proprietario
     *
     * @return Map de veiculos.
    */

    public Map<String, Veiculo> getMapVeiculos() {

       return this.mapVeiculos.entrySet()
                                   .stream()
                                   .collect(Collectors.toMap(e -> e.getKey(),
                                                             e -> e.getValue(),
                                                            (e1, e2) -> e2,
                                                             HashMap::new));
    }

    /**
     * Devolve a lista de pedidos de aluguer
     *
     * @return Lista de pedidos.
    */

    public List<Aluguer> getPedidosAluguer () {

        return this.pedidosAluguer.stream().map(Aluguer::clone).collect(Collectors.toList());
    }

    /**
     * Devolve a lista de veiculos do Map de veiculos
     *
     * @return Lista de veiculos.
    */

    public List<Veiculo> getListaVeiculos() {

       return this.mapVeiculos.values()
                                   .stream()
                                   .map(Veiculo::clone)
                                   .collect(Collectors.toList());
    }
 
    /**
     * Verifica se existe um veiculo no Map de veiculos, dado o objeto veiculo
     *
     * @return true se existir.
    */

    public boolean existeVeiculo (Veiculo v) {

        return this.mapVeiculos.containsKey(v.getMatricula());
    }

    /**
     * Verifica se existe um veiculo no Map de veiculos, dado a sua matricula
     *
     * @return true se existir.
    */

    public boolean existeVeiculo (String matricula) {

        return this.mapVeiculos.containsKey(matricula);
    }

    /**
     * Sinaliza um veiculo como disponivel
     *
     * @param Veiculo v.
    */

    public void sinalizarVeiculo (Veiculo v) {
    
        if(this.mapVeiculos.containsValue(v)) {
            
            if(v.veiculoDisponivelAluguer()) this.mapVeiculos.get(v.getMatricula()).setDisponivel(true);
            else this.mapVeiculos.get(v.getMatricula()).setDisponivel(false);

        }
    }

    /**
     * Aumenta a quantidade de combustivel de um veiculo do proprietario
     *
     * @param v Veiculo para abastecer
     * @param quant quantidade para adicionar
    */

    public void abastecer (VeiculoComAutonomia v, Double quant) {
    
        Double atual = ((VeiculoComAutonomia) v).getAutonomiaAtual(), 
               max   = ((VeiculoComAutonomia) v).getAutonomiaMaxima();

        if (atual + quant > max) {

            atual = max;
        } else atual += quant;

        ((VeiculoComAutonomia) v).setAutonomiaAtual(atual);
    }
    
    /**
     * Vai buscar um veiculo pela matricula dele
     *
     * @param matricula matricula do veiculo
     *
     * @return o Veiculo
    */

    public Veiculo getVeiculoPelaMatricula (String matricula){
        
        return this.mapVeiculos.containsKey(matricula)?this.mapVeiculos.get(matricula):null;
    }

    /**
     * Classifica um proprietario mediante a classificacao dos veiculos
     *
     * @param v_novo Veiculo para classificar
     *
    */

    public void classifica(Veiculo v_novo) {

        int n = 0, size = 0;

        List<Veiculo> veiculos = this.mapVeiculos.values()
                                                 .stream()
                                                 .collect(Collectors.toList());

        veiculos.add(v_novo);

        size = veiculos.size();

        for (Veiculo v: veiculos) {

            n += v.getClassificacao();
        }

        this.setClassificacao((int) Math.round(n / size));
    }
}