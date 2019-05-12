import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.util.HashMap;

import java.lang.Character;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main_IO {

    public static List<String> leFicheiroParaLista (String fileName) throws IOException {

        List<String> result;
        
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            result = lines.collect(Collectors.toList());
        }
        return result;

    }

    public static void loadLogs (EstadoSistema e) {

        try {
        
            List<String> logs = leFicheiroParaLista("logs.txt");
            
            //logs.forEach(System.out::println);

            //A partir daqui conseguiu ler o ficheiro
            //Vai percorrer o ficheiro lido na lista de strings (linhas)

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

            double xLido = 0, yLido = 0;
            double velMedia, consumo, precoKM, maxAuto, currAuto;
            
            LocalDate dataLida;

           for (String s: logs) {

                String[] tokens = s.split(",");             

                switch(tokens[0]) {

                    case "NovoCliente":

                        dataLida = LocalDate.parse(tokens[4], formatter);

                        xLido = Double.parseDouble(tokens[5]);
                        yLido = Double.parseDouble(tokens[6]);

                        Cliente cliente_lido = new Cliente(tokens[1],
                                                      tokens[3],
                                                      tokens[2],
                                                      tokens[7],
                                                      dataLida,
                                                      new Localizacao(xLido, yLido),
                                                      new ArrayList<Aluguer>(),
                                                      80);

                        e.adicionaCliente(cliente_lido);

                        break;

                    case "NovoProp":

                        dataLida = LocalDate.parse(tokens[5], formatter);

                        Proprietario prop_lido = new Proprietario (tokens[1],
                                                                   tokens[2],
                                                                   tokens[3],
                                                                   tokens[4],
                                                                   dataLida,
                                                                   0,
                                                                   new ArrayList<Aluguer>(),
                                                                   new HashMap<String, Veiculo>());

                        e.adicionaProprietario(prop_lido);

                        break;

                    case "NovoCarro":
                        
                        velMedia = Double.parseDouble(tokens[5]);
                        precoKM  = Double.parseDouble(tokens[6]);
                        consumo  = Double.parseDouble(tokens[7]);

                        xLido = Double.parseDouble(tokens[8]);
                        yLido = Double.parseDouble(tokens[9]);

                        switch (tokens[1]) {

                            case "gasolina":

                                CarroGasolina carro_gas = 
                                        new CarroGasolina (tokens[2],
                                                           velMedia,
                                                           precoKM,
                                                           0, //classificacao
                                                           new Localizacao(xLido, yLido),
                                                           consumo,
                                                           100,
                                                           100,
                                                           tokens[10],
                                                           true);

                                if (e.existeProprietario(tokens[10])) {

                                    e.getProprietario(tokens[10]).adicionaVeiculo(carro_gas);
                                
                                }
                                
                                break;

                                case "eletrico":
    
                                    CarroEletrico carro_ele = 
                                            new CarroEletrico (tokens[2],
                                                               velMedia,
                                                               precoKM,
                                                               0, //classificacao
                                                               new Localizacao(xLido, yLido),
                                                               consumo,
                                                               100,
                                                               100,
                                                               tokens[10],
                                                               true);
                                            
                                    if (e.existeProprietario(tokens[10])) {

                                        e.getProprietario(tokens[10]).adicionaVeiculo(carro_ele);
                                    
                                    }

                                    break;

                            default: break;
                        }
                    
                    case "Aluguer":

                        switch(tokens[2]) {

                            case "MaisPerto":

                                Veiculo alugado = e.carroMaisProximo(e.getCliente(tokens[1])).clone();
                                                        
                                                  
                                String nomeVeiculo = alugado.getMatricula();
                                String emailProp = e.getMailProprietarioVeiculo(alugado);

                                Aluguer alug = new Aluguer(nomeVeiculo, //Nome do veiculo
                                                            tokens[1], //Nome do cliente
                                                            emailProp, //Nome do prodprietario
                                                            20.0, //Distancia
                                                            alugado.getPrecoPorKM(), //Preco
                                                            e.getData()); //Data de aluguer

                                e.getCliente(tokens[1]).adicionaAluguer(alug);
                                e.getProprietario(alugado.getProprietario()).adicionaAluguer(alug);

                                // System.out.println("\nCarro mais próximo de " + tokens[1] + "\n");
                                // System.out.println(e.getCliente(tokens[1]).getLocalizacao().toString());
                                // System.out.println(e.carroMaisProximo(e.getCliente(tokens[1])));

                            default: break;
                        }

                            break;

                    default: break;
                }

            }

        } catch (IOException ex) {
        
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {

        EstadoSistema e1 = new EstadoSistema();
        
        //Carregar o ficheiro de logs no sistema
        loadLogs(e1);

        //Para dar print ao sistema todo [Debug]
        
        //e1.getProprietario("google.company@toyota.pt").getVeiculoPelaMatricula("OP-34-00").moverParaPosicao(10, 10);
        
        System.out.println(e1.toString());
        
        // System.out.println("Carro mais próximo de maria.silva@gmail.com: "+ e1.getCliente("jose.silva@gmail.com").getLocalizacao().toString());
        // System.out.println(e1.carroMaisProximo(e1.getCliente("maria.silva@gmail.com")));

        // System.out.println("Carro mais barato: ");
        // System.out.println((e1.carroMaisBarato())==null?
        //                  "Não há carros.":(e1.carroMaisBarato()));

        // System.out.println("Carro mais barato, dentro de uma distancia (2km) para jose.silva@gmail.com: " + e1.getCliente("jose.silva@gmail.com").getLocalizacao().toString());
        // System.out.println((e1.carroMaisBarato(e1.getCliente("jose.silva@gmail.com"), 2.0))==null?
        //                  "Não há carros.":(e1.carroMaisBarato(e1.getCliente("jose.silva@gmail.com"), 2.0)));

        // System.out.println("\nSolicitacao de carros a gasolina: \n");
        // System.out.println(e1.carroEspecifico("CarroGasolina"));

        // System.out.println("\nSolicitacao de carros eletricos: \n");
        // System.out.println(e1.carroEspecifico("CarroEletrico"));
    
    }

}