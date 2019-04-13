import java.util.ArrayList;
import java.util.List;
import java.lang.String;

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
       		
            System.out.println ("\nFicheiro de logs: \n");

           	//logs.forEach(System.out::println);

            //A partir daqui conseguiu ler o ficheiro
            //Vai percorrer o ficheiro lido
            for (String s: logs) {

            	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

            	int xLido = 0, yLido = 0;
				double velMedia, consumo, precoKM, maxAuto, currAuto;

				String[] tokens = s.split(",");            	

				switch(tokens[0]) {

					case "NovoCliente": //**********************************************

						LocalDate dataLida = LocalDate.parse(tokens[4], formatter);

						xLido = Character.getNumericValue(tokens[5].charAt(1));
						yLido = Character.getNumericValue(tokens[5].charAt(3));

						Cliente cliente_lido = new Cliente(tokens[1],
													  tokens[3],
													  tokens[2],
													  tokens[6],
													  dataLida,
													  new Localizacao(xLido, yLido),
													  new ArrayList<Aluguer>(),
													  0);

						e.adicionaCliente(cliente_lido);

						break;

					case "NovoProp":

//						LocalDate dataLida = LocalDate.parse(tokens[5], formatter);
/*
						Proprietario prop_lido = new Proprietario (tokens[1],
																   tokens[2],
																   tokens[3],
																   tokens[4],
																   dataLida,
																   0);

						e.adicionaProprietario(prop_lido);
*/
						break;

					case "NovoCarro": //**********************************************
						
						velMedia = Double.parseDouble(tokens[5]);
						precoKM  = Double.parseDouble(tokens[6]);
						consumo  = Double.parseDouble(tokens[7]);

						xLido = Character.getNumericValue(tokens[8].charAt(1));
						yLido = Character.getNumericValue(tokens[8].charAt(3));

						switch (tokens[1]) {

							case "gasolina": //---------------------------------------

								CarroGasolina carro_gas = 
										new CarroGasolina (tokens[2],
														   velMedia,
														   precoKM,
														   0, //classificacao
														   new Localizacao(xLido, yLido),
														   consumo,
														   100,
														   0);
								
								if (e.existeProprietario(tokens[4])) {


								}		
								

						break;

							case "eletrico":

							default: 
								break;
						}
					break;


					default:
					
						//System.out.println("None of those options were detected!"); 
						break;
				}

            }

        	System.out.println(e.toString());

        } catch (IOException ex) {
        
            ex.printStackTrace();
        }

	}

	public static void main(String[] args) {

		EstadoSistema e1 = new EstadoSistema();
		
		loadLogs(e1);

	    //-------------------------------------------------------//  
/*
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
										   new ArrayList<Veiculo>());

		Proprietario p2 = new Proprietario("propriatario2@mail.pt",
										   "Clutilde",
										   "feminismoftw",
										   "Sao paulo, Brasil",
										   LocalDate.of(2000, 9, 2),
										   70,
										   new ArrayList<Aluguer>(),
										   new ArrayList<Veiculo>());


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

*/	
	}

}