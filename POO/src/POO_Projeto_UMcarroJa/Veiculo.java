import java.util.List;
import java.util.ArrayList;

/**
 * Escreva a descrição da classe Veiculo aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Veiculo
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    private int velocidadeMediaKm;
    private float precoKm;
    private float consumoKm;
    private List<Aluguer> listaAlugueres;    
    private int classificacao;
    Localizacao local;
    private int autonomiaMaxima;
    private int autonomiaAtual;
    
    /**
     * COnstrutor para objetos da classe Veiculo
     */
    public Veiculo()
    {
        // inicializa variáveis de instância
    }
    
    
    
    
    
    public float percentagemAutonomia(){
     
        return ((this.autonomiaAtual/this.autonomiaAtual)*100);
    }
    
    public void moverParaPosicao(int x, int y){
    
    }
    
    
    
}
