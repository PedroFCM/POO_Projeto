import java.time.LocalDate;
import java.util.List;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.lang.Integer;

public class EstadoSistema  {
    
    //-------------------------------------------------------//  
    
    private List<Cliente> clientes_Sistema;
    private List<Proprietario> proprietarios_Sistema;
    private LocalDate data_atual;
    
    //-------------------------------------------------------//  

    public EstadoSistema (List<Cliente> clientes,
                          List<Proprietario> proprietarios,
                          LocalDate data) {

        this.clientes_Sistema = clientes.stream()
                                        .map(Cliente::clone)
                                        .collect(Collectors.toList());
    
        this.proprietarios_Sistema = proprietarios.stream()
                                                  .map(Proprietario::clone)
                                                  .collect(Collectors.toList());

        this.data_atual = data;
    }

    public EstadoSistema (EstadoSistema novo) {

        this.clientes_Sistema = novo.getClientesSistema();
        this.proprietarios_Sistema = novo.getProprietariosSistema();
        this.data_atual = novo.getData();
    }

    public EstadoSistema () {

        this.clientes_Sistema = new ArrayList<Cliente>();
        this.proprietarios_Sistema = new ArrayList<Proprietario>();
        this.data_atual = LocalDate.now();
    }

    //-------------------------------------------------------//  

    public void adicionaProprietario (Proprietario p) {

        this.proprietarios_Sistema.add(p);
    }

    public HashMap<Proprietario,List<Veiculo>> allVeiculos() {

        HashMap<Proprietario,List<Veiculo>> h = new HashMap<>();
        
        for(Proprietario p: proprietarios_Sistema)
        {
            h.put(p, p.getListaVeiculos());  // ADICIONAR UM A UM
        }
        
        return h;
    }

    public void adicionaVeiculo (Proprietario p, Veiculo v) {

        for (Proprietario p1: this.proprietarios_Sistema) {

            if (p1.equals(p)) {
                p1.adicionaVeiculo(v);
                break;
            }
        }
    }

    public Veiculo carroMaisProximo (Cliente c) {

        List<Veiculo> todosVeiculos = new ArrayList<Veiculo>();
        
        todosVeiculos =  this.allVeiculos()
                             .values()
                             .stream()
                             .flatMap(l -> l.stream())
                             .map(Veiculo::clone)
                             .collect(Collectors.toList());

        if (todosVeiculos.isEmpty())
            return null;

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
        s.append("\n------------Data Sistema------------\n" + this.data_atual.toString() + "\n");

        return s.toString();
    }

    //-------------------------------------------------------//  

    public List<Cliente> getClientesSistema () {

       return this.clientes_Sistema.stream()
                                   .map(Cliente::clone)
                                   .collect(Collectors.toList());   
    }

    public List<Proprietario> getProprietariosSistema () {

       return this.proprietarios_Sistema.stream()
                                        .map(Proprietario::clone)
                                        .collect(Collectors.toList()); 
    }

    public LocalDate getData () {

        return this.data_atual;
    }
}