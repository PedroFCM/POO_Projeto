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
       		
            System.out.println ("\nFicheiro de logs: \n");

           	//logs.forEach(System.out::println);

            //A partir daqui conseguiu ler o ficheiro
            //Vai percorrer o ficheiro lido na lista de strings (linhas)

            for (String s: logs) {

            	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

            	int xLido = 0, yLido = 0;
				double velMedia, consumo, precoKM, maxAuto, currAuto;
				
				LocalDate dataLida;

				String[] tokens = s.split(",");            	

				switch(tokens[0]) {

					case "NovoCliente": //**********************************************

						dataLida = LocalDate.parse(tokens[4], formatter);

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

								if (e.existeProprietario(tokens[9])) {

									e.getProprietario(tokens[9]).adicionaVeiculo(carro_gas);
								
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
															   0);
											
									if (e.existeProprietario(tokens[9])) {

										e.getProprietario(tokens[9]).adicionaVeiculo(carro_ele);
									
									}

									break;

							}
							break;

					default:
					
						//System.out.println("None of those options were detected!"); 
						break;
				}

            }

        } catch (IOException ex) {
        
            ex.printStackTrace();
        }

	}

	public static void main(String[] args) {

		EstadoSistema e1 = new EstadoSistema();
		
		loadLogs(e1);

       	System.out.println(e1.toString());
	    
	    //-------------------------------------------------------//  
/*		Aluguer alug1 = new Aluguer("CarroGasolina",
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