import java.util.ArrayList;
import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		
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
											  14,
											  45,
											  new Localizacao (20.0, 30.1),
											  20,
											  100000,
											  50000);


		CarroGasolina cg3 = new CarroGasolina("57-47-AX",
											  200,
											  14,
											  45,
											  new Localizacao (900.0, 300.1),
											  20,
											  100000,
											  50000);

		CarroEletrico ce1 = new CarroEletrico("29-20-PW",
											  200,
											  14,
											  45,
											  new Localizacao (1.1, 1.1),
											  20,
											  100000,
											  50000);

		CarroEletrico ce2 = new CarroEletrico("12-02-LS",
											  200,
											  14,
											  45,
											  new Localizacao (90.0, 3.1),
											  20,
											  100000,
											  50000);

		EstadoSistema e1 = new EstadoSistema();
		
		e1.adicionaProprietario(p1);
		e1.adicionaProprietario(p2);

		//Adicionar veiculos ao proprietario 1
		e1.adicionaVeiculo(p1, cg1);
		e1.adicionaVeiculo(p1, cg2);
		e1.adicionaVeiculo(p1, cg3);

		//Adicionar veiculos ao proprietario 2
		e1.adicionaVeiculo(p2, ce1);
		e1.adicionaVeiculo(p2, ce2);

		//Mostrar o carro mais próximo do cliente 1
		System.out.println("\nCarro mais próximo do cliente 1: \n");
		System.out.println(e1.carroMaisProximo(c1).toString());

		//Mostrar o carro mais próximo do cliente 2
		System.out.println("\nCarro mais próximo do cliente 2: \n");
		System.out.println(e1.carroMaisProximo(c2).toString());
	}

}