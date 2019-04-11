import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Proprietario extends AtorSistema
{

    //-------------------------------------------------------//  

    private List<Veiculo> listaVeiculos;
    
    //-------------------------------------------------------//  

    public Proprietario(String email, 
                        String nome, 
                        String password, 
                        String morada, 
                        LocalDate data, 
                        int classificacao,
                        List<Aluguer> historico,
                        List<Veiculo> veiculos) {
     
        super(email, nome, password, morada, data, classificacao, historico);
        this.listaVeiculos = veiculos.stream()
                                     .map(Veiculo::clone)
                                     .collect(Collectors.toList());
    }
    
    public Proprietario (Proprietario umProprietario) {
    
        super(umProprietario);
        this.listaVeiculos = umProprietario.getListaVeiculos();
    }
    
    public Proprietario () {
        
        super();
        this.listaVeiculos = new ArrayList<Veiculo>();
    }

    //-------------------------------------------------------//  

    public void adicionaVeiculo (Veiculo v) {

        this.listaVeiculos.add(v);
    }

    //-------------------------------------------------------//  

    public Proprietario clone() {

        return new Proprietario(this);
    }

    //-------------------------------------------------------//  

    public List<Veiculo> getListaVeiculos() {

        List<Veiculo> lista = new ArrayList<>(); 
        
        for(Veiculo v: this.listaVeiculos) {

            lista.add(v.clone());
        }
        
        return lista;
    }

    //-------------------------------------------------------//  
    

    /*
    public ArrayList<Veiculo> getListaVeiculos(){
        
        return this.listaVeiculos.stream()
                          .collect(Collectors.toCollection(ArrayList::new));
                          
        //Penso que nao esteja certo
    }
    
    */
   /*
    public ArrayList<Veiculo> carrosDisponiveis(){
            
        ArrayList<Veiculo> carrosDisponiveis = new ArrayList<>();
        
        for(Veiculo v: getListaVeiculos()){
            
            if(v.percentagemAutonomia() > 10) carrosDisponiveis.add(v);
        }
        
        return carrosDisponiveis;
    }
    */
    /*
    public void abastecerVeiculo(){
    
    }
    
    public void alterarPrecoKm(){
    
    }
    
    public boolean aceitarRejeitarAluguer(){
    
    }
    
    public float custoViagem(){
    
    }
   */
}