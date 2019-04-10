import java.util.Date;
import java.util.List;

/**
 * Escreva a descrição da classe Cliente aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Cliente extends Atores_de_Sistema {
    
    private Localizacao atual;
    
    /**
     * COnstrutor para objetos da classe Cliente
     */
    public Cliente(String email, String nome, String password, String morada, Date data, Localizacao local)
    {
        
        super(email, nome, password, morada, data);
        this.atual = local;
    }
    
    public Localizacao getLocalizacao(){
        
        return this.atual;
    }
    
    public void setLocalizacao(Localizacao local){
        
        this.atual = local;
    }
    /*
    public aluguerCarroMaisProximo(){
    
    }
    
    public aluguerCarroMaisBarato(){
    
    }
    
    public carroMaisBaratoDistanciaPe(int kmPe){
    
    }

    public aluguerCarroEspecifico(Veiculo v){
    
    }

    public carroComAutonomiaDesejada(int autonomia){
    
    }
    */
}
