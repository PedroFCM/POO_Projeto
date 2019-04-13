import java.util.ArrayList;
import java.time.LocalDate;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        
        //-------------------------------------------------------//  

        Cliente c1 = new Cliente("maildo1@mail.pt",
                                 "Jose",
                                 "miaKhalifa922",
                                 "Rua flores, Famalicao",
                                 LocalDate.of(1999, 12, 2),
                                 new Localizacao(1.0, 1.0),
                                 new ArrayList<Aluguer>(),
                                 15);

        Cliente c2 = new Cliente("maildo2@mail.pt",
                                 "Filipe",
                                 "filipesecret69",
                                 "Rua colatra, Paralelo torto",
                                 LocalDate.of(1920, 2, 20),
                                 new Localizacao(90.0, 1.0),
                                 new ArrayList<Aluguer>(),
                                 40);


        Proprietario p1 = new Proprietario("propriatario1@mail.pt",
                                           "Firmino",
                                           "eusouofirmino",
                                           "Fanecas de baixo",
                                           LocalDate.of(1912, 1, 2),
                                           100,
                                           new ArrayList<Aluguer>(),
                                           new HashMap<String,Veiculo>());

        Proprietario p2 = new Proprietario("propriatario2@mail.pt",
                                           "Clutilde",
                                           "feminismoftw",
                                           "Sao paulo, Brasil",
                                           LocalDate.of(2000, 9, 2),
                                           70,
                                           new ArrayList<Aluguer>(),
                                           new HashMap<String,Veiculo>());


        CarroGasolina cg1 = new CarroGasolina("00-11-22",
                                              200,
                                              14,
                                              45,
                                              new Localizacao (20.0, 3.1),
                                              20,
                                              100000,
                                              50000);

        CarroGasolina cg2 = new CarroGasolina("20-00-22",
                                              200,
                                              16,
                                              45,
                                              new Localizacao (20.0, 30.1),
                                              20,
                                              100000,
                                              50000);


        CarroGasolina cg3 = new CarroGasolina("57-47-AX",
                                              200,
                                              900,
                                              45,
                                              new Localizacao (900.0, 300.1),
                                              20,
                                              100000,
                                              50000);

        CarroEletrico ce1 = new CarroEletrico("29-20-PW",
                                              200,
                                              1,
                                              45,
                                              new Localizacao (1.1, 1.1),
                                              20,
                                              100000,
                                              50000);

        CarroEletrico ce2 = new CarroEletrico("12-02-LS",
                                              200,
                                              2,
                                              45,
                                              new Localizacao (90.0, 3.1),
                                              20,
                                              100000,
                                              50000);


        //-------------------------------------------------------//  

        EstadoSistema e1 = new EstadoSistema();
        
        //Adicionar veiculos ao proprietario 1
        p1.adicionaVeiculo(cg1);
        p1.adicionaVeiculo(cg2);
        p1.adicionaVeiculo(cg3);

        //Adicionar veiculos ao proprietario 2
        p2.adicionaVeiculo(ce1);
        p2.adicionaVeiculo(ce2);
        
        //Adicionar proprietarios ao sistema
        e1.adicionaProprietario(p1);
        e1.adicionaProprietario(p2);
        
        Aluguer alug1 = new Aluguer("CarroGasolina",
                                    c1.getNome(),
                                    "Firmino",
                                    20.0,
                                    15,
                                    e1.getData());

        c1.adicionaAluguer(alug1);

        //Adicionar clientes ao sistema
        e1.adicionaCliente(c1);
        e1.adicionaCliente(c2);

       
        p1.alteraPrecoPorKm(cg1, 20);
       
        //-------------------------------------------------------//  

        // System.out.println("Carro mais próximo de c1: \n");
        // System.out.println(e1.carroMaisProximo(c1));

        // System.out.println("Carro mais próximo de c2: \n");
        // System.out.println(e1.carroMaisProximo(c2));

        // System.out.println("Carro mais barato: ");
        // System.out.println((e1.carroMaisBarato())==null?
        //                  "Não há carros.":(e1.carroMaisBarato()));

        // System.out.println("Carro mais barato, dentro de uma distancia (2km) para c1: ");
        // System.out.println((e1.carroMaisBarato(c1, 2.0))==null?
        //                  "Não há carros.":(e1.carroMaisBarato(c1, 2.0)));

        //Para carros específicos (Gasolina, Eletricos, Hibridos, etc...):
        // System.out.println("Solicitacao de carros a gasolina: \n");
        // System.out.println(e1.carroEspecifico("CarroGasolina"));

        // System.out.println("Solicitacao de carros eletricos: \n");
        // System.out.println(e1.carroEspecifico("CarroEletrico"));

        //-------------------------------------------------------//  

        //Para dar print ao sistema -> DEBUG
        
        System.out.println(e1.toString());
       
        //-------------------------------------------------------//  
    }

}