import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Mochila;
import ClassesBasicas.Sala;
import execcoes.SalaTrancadaException;
import ferramentas.Circulo;
import ferramentas.Espada;
import ferramentas.Marreta;
import ferramentas.Orbe;
import ferramentas.Punhos;
import ferramentas.Quadrado;
import ferramentas.Tocha;
import ferramentas.Triangulo;
import ferramentas.Varinha;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import salas.Cela01;
import salas.Cifra05;
import salas.Corredor03;
import salas.FimDeJogo;
import salas.Martelo06;
import salas.PortaFinal04;
import salas.Prisao02;
import salas.SegredoQ;
import salas.SegredoT;

public class GameController {

	private Janela janela;
	private Sala salaAtual;
	private Mochila mochila;
	private Map<String, Image> salasVisual;
	private TextArea log;
	private ImageView tela;
	private Integer forca, intel, dex, con;
	private Map<String, Sala> listaSalas;
	private Map<String, Ferramenta> listaItens;
	private Double mana, vida;

	public GameController(Janela janela) {
		this.janela = janela;
		log = janela.getLog();
		tela = janela.getTela();
		salasVisual = new HashMap<>();
		mochila = new Mochila(janela.getItens());
		carregaJogo();

	}

	public void carregaJogo() {
		// Instancia Salas
		Sala cela = new Cela01();
		Sala prisao = new Prisao02();
		Sala corredor = new Corredor03();
		Sala portao = new PortaFinal04();
		Sala cifra = new Cifra05();
		Sala marreta = new Martelo06();

		listaSalas = new HashMap<>();
		listaSalas.put(cela.getNome(), cela);
		listaSalas.put(prisao.getNome(), prisao);
		listaSalas.put(corredor.getNome(), corredor);
		listaSalas.put(portao.getNome(), portao);
		listaSalas.put(cifra.getNome(), cifra);
		listaSalas.put(marreta.getNome(), marreta);

		// Cataloga Itens
		Ferramenta Circulo = new Circulo();
		Ferramenta Triangulo = new Triangulo();
		Ferramenta Quadrado = new Quadrado();
		Ferramenta Tocha = new Tocha();
		Ferramenta Marreta = new Marreta();
		Ferramenta Orbe = new Orbe();
		Ferramenta Espada = new Espada();
		Ferramenta Varinha = new Varinha(janela.getMana());
		Ferramenta Punhos = new Punhos();

		listaItens = new HashMap<>();
		listaItens.put(Circulo.getDescricao(), Circulo);
		listaItens.put(Triangulo.getDescricao(), Triangulo);
		listaItens.put(Quadrado.getDescricao(), Quadrado);
		listaItens.put(Tocha.getDescricao(), Tocha);
		listaItens.put(Marreta.getDescricao(), Marreta);
		listaItens.put(Orbe.getDescricao(), Orbe);
		listaItens.put(Espada.getDescricao(), Espada);
		listaItens.put(Varinha.getDescricao(), Varinha);
		listaItens.put(Punhos.getDescricao(), Punhos);

		// ConectaSalas

		cela.getPortas().put(prisao.getNome(), prisao);

		prisao.getPortas().put(cela.getNome(), cela);
		prisao.getPortas().put(corredor.getNome(), corredor);

		corredor.getPortas().put(prisao.getNome(), prisao);
		corredor.getPortas().put(portao.getNome(), portao);

		portao.getPortas().put(corredor.getNome(), corredor);
		portao.getPortas().put(cifra.getNome(), cifra);
		portao.getPortas().put(marreta.getNome(), marreta);

		cifra.getPortas().put(portao.getNome(), portao);

		marreta.getPortas().put(portao.getNome(), portao);

		// CriaVisuais
		salasVisual.put(cela.getRepVisual(), new Image("file:cela.png", 500, 400, false, false));
		salasVisual.put(prisao.getRepVisual(), new Image("file:prisao.png", 500, 400, false, false));
		salasVisual.put("celaaberta", new Image("file:celaaberta.png", 500, 400, false, false));
		salasVisual.put("prisaoquebrada", new Image("file:prisaoquebrada.png", 500, 400, false, false));
		salasVisual.put(corredor.getRepVisual(), new Image("file:corredor.png", 500, 400, false, false));
		salasVisual.put(portao.getRepVisual(), new Image("file:portaFinal.png", 500, 400, false, false));
		salasVisual.put("portaFinalAberta", new Image("file:portaFinalAberta.png", 500, 400, false, false));
		salasVisual.put(cifra.getRepVisual(), new Image("file:cifra.png", 500, 400, false, false));
		salasVisual.put("cifraescuro", new Image("file:cifraescuro.png", 500, 400, false, false));
		salasVisual.put(marreta.getRepVisual(), new Image("file:marreta.png", 500, 400, false, false));
		salasVisual.put("marretaaberta", new Image("file:marretaaberta.png", 500, 400, false, false));
		salasVisual.put("salaEscura", new Image("file:salaEscura.png", 500, 400, false, false));
		salasVisual.put("segredoQ", new Image("file:segredoQ.png", 500, 400, false, false));
		salasVisual.put("segredoT", new Image("file:segredoT.png", 500, 400, false, false));

		salaAtual = cela;

	}

	private void salvarJogo() throws IOException {
		FileWriter fw = new FileWriter("save.txt");
		PrintWriter pw = new PrintWriter(fw);
		pw.print(salaAtual.getNome() + ";");
		pw.print("" + intel + ";" + dex + ";" + con + ";" + forca + ";");
		pw.println(janela.getMana().getProgress() + ";");
		pw.println(mochila.inventario());

		Collection<Sala> lstAux = listaSalas.values();

		for (Sala s : lstAux) {

			Collection<Ferramenta> ferraments = s.getFerramentas().values();

			for (Ferramenta f : ferraments) {
				if (mochila.inventario().contains(f.getDescricao())) {
					s.getFerramentas().remove(f.getDescricao());
				}
			}

		}

		if (listaSalas.get("PRISAO").getPortas().containsKey("SEGREDOQ")) {
			pw.println("TRUE");

			if (((SegredoQ) listaSalas.get("PRISAO").getPortas().get("SEGREDOQ")).isEscura()) {
				pw.println("TRUE");
			} else {
				pw.println("FALSE");
			}
		} else {
			pw.println("FALSE");
			pw.println("FALSE");
		}

		if (listaSalas.get("DIREITA").getPortas().containsKey("SEGREDOT")) {
			pw.println("TRUE");

			if (((SegredoT) listaSalas.get("DIREITA").getPortas().get("SEGREDOT")).isEscura()) {
				pw.println("TRUE");
			} else {
				pw.println("FALSE");
			}
		} else {
			pw.println("FALSE");
			pw.println("FALSE");
		}

		pw.println(((Cela01) listaSalas.get("CELA")).save());
		pw.println(((PortaFinal04) listaSalas.get("PORTAO")).save());
		pw.println(((Cifra05) listaSalas.get("ESQUERDA")).save());

		pw.close();
		System.exit(0);
	}

	public void loadJogo() throws IOException {

		File file = new File("save.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String stats = br.readLine();
		System.out.println(stats);
		String[] statsX = stats.split(";");

		intel = Integer.parseInt(statsX[1]);
		dex = Integer.parseInt(statsX[2]);
		con = Integer.parseInt(statsX[3]);
		forca = Integer.parseInt(statsX[4]);
		mana = Double.parseDouble(statsX[5]);

		String mochilaC = br.readLine();
		System.out.println(mochilaC);
		mochilaC = mochilaC.replaceAll("[^a-zA-Z0-9 .,]|(?<!\\d)[.,]|[.,](?!\\d)", "");

		System.out.println(mochilaC);

		String[] mochilaX = mochilaC.split(" ");

		ProgressBar manaS = janela.getMana();

		for (String s : mochilaX) {
			if (s.equals("VARINHA")) {
				mochila.guardar(new Varinha(manaS));
			}
			System.out.println(s);
			if (s != " " && !s.equals("VARINHA"))
				mochila.guardar(listaItens.get(s));
		}

		System.out.println(mochila.inventario());

		Collection<Sala> lstAux = listaSalas.values();

		for (Sala s : lstAux) {

			List<String> retirar = new ArrayList<>();

			Collection<Ferramenta> ferraments = s.getFerramentas().values();

			for (Ferramenta f : ferraments) {
				if (mochila.inventario().contains(f.getDescricao())) {
					retirar.add(f.getDescricao());
				}
			}

			for (String str : retirar) {
				s.getFerramentas().remove(str);
			}
		}

		String salaq = br.readLine();
		String escuroq = br.readLine();
		if (salaq.equals("TRUE")) {
			Sala segredo = new SegredoQ();
			listaSalas.get("PRISAO").getPortas().put(segredo.getNome(), segredo);
			segredo.getPortas().put(listaSalas.get("PRISAO").getNome(), listaSalas.get("PRISAO"));
			listaSalas.get("PRISAO").setRepVisual("prisaoquebrada");
			if (escuroq.equals("FALSE")) {
				((SegredoQ) segredo).setNEscuro();
			}
		}

		String salat = br.readLine();
		String escurot = br.readLine();

		if (salat.equals("TRUE")) {
			Sala segredo = new SegredoT();
			listaSalas.get("DIREITA").getPortas().put(segredo.getNome(), segredo);
			segredo.getPortas().put(listaSalas.get("DIREITA").getNome(), listaSalas.get("DIREITA"));
			listaSalas.get("DIREITA").setRepVisual("marretaaberta");

			if (escurot.equals("FALSE")) {
				((SegredoT) segredo).setNEscuro();
			}

		}

		String cela = br.readLine();
		System.out.println(cela);
		String[] celaX = cela.split(";");

		boolean a = Boolean.valueOf(celaX[0]);
		boolean b = Boolean.valueOf(celaX[1]);
		boolean c = Boolean.valueOf(celaX[2]);

		((Cela01) listaSalas.get("CELA")).load(a, b, c);

		String port = br.readLine();

		String[] aux = port.split(";");

		a = Boolean.valueOf(aux[0]);
		b = Boolean.valueOf(aux[1]);

		if (a && b) {
			Sala fim = new FimDeJogo();
			listaSalas.get("PORTAO").getPortas().put(fim.getNome(), fim);
			fim.getPortas().put(listaSalas.get("PORTAO").getNome(), listaSalas.get("PORTAO"));
		}

		((PortaFinal04) listaSalas.get("PORTAO")).load(a, b);

		String esq = br.readLine();

		((Cifra05) listaSalas.get("ESQUERDA")).load(Boolean.valueOf(esq));

		salaAtual = listaSalas.get(statsX[0]);
		salaAtual.entra(mochila);
		tela.setImage(salasVisual.get(salaAtual.getRepVisual()));

		br.close();
	}

	public void start(String classe) {

		switch (classe) {
		case "Guerreiro":
			forca = 10;
			intel = 3;
			dex = 7;
			con = 8;
			mana = 0.0;
			Punhos punhos = new Punhos();
			mochila.guardar(punhos);
			break;
		case "Mago":
			forca = 3;
			intel = 10;
			dex = 6;
			con = 4;
			mana = 1.0;
			Varinha varinha = new Varinha(janela.getMana());
			mochila.guardar(varinha);
			break;

		case "Ladino":
			forca = 6;
			intel = 7;
			dex = 10;
			con = 5;
			mana = 0.5;
			Varinha varinhax = new Varinha(janela.getMana());
			mochila.guardar(varinhax);
			break;
		default:
			break;
		}

		vida = 1.0;

		log.appendText("Voc� acorda deitado em um ch�o sujo.\n");
		log.appendText("Digite \"Ajuda\" para saber mais como jogar.\n");

		salaAtual.entra(mochila);
		tela.setImage(salasVisual.get(salaAtual.getRepVisual()));
	}

	public void recebeComando(String texto) {

		StringTokenizer comando = new StringTokenizer(texto);

		if (comando.hasMoreTokens()) {

			switch (comando.nextToken()) {

			case "AJUDA":

				log.appendText("\nPodes usar 6 comandos diferentes:" + "\nENTRAR <nome da sala> - entra em outra sala"
						+ "\nINSPECIONAR (vazio) ou <nome do objeto> da detalhes sobre um objeto ou sala."
						+ "\nUSAR <nome do item>- Usa um item de seu invent�rio."
						+ "\nPEGAR <nome do item> - Pega um item presente na sala."
						+ "\nINTERAGIR <nome do objeto> <solucao do desafio> - Interage com certos objetos."
						+ "\nAJUDA - abre este menu.\n");
				break;

			case "ENTRAR":

				try {
					String sala = comando.nextToken();
					if (salaAtual.getPortas().containsKey(sala)) {

						salaAtual = salaAtual.sai(sala);
						salaAtual.entra(mochila);
						tela.setImage(salasVisual.get(salaAtual.getRepVisual()));
					} else {
						log.appendText("Esta sala n�o est� conectada com a atual.\n");

					}

					break;

				} catch (NullPointerException e) {
					log.appendText("Esta sala n�o existe.\n");
					break;
				} catch (SalaTrancadaException e) {
					log.appendText("Voc� n�o consegue sair, a porta est� trancada.\n");
					break;
				} catch (NoSuchElementException e) {
					log.appendText("Comando inv�lido.\n");
					break;
				}

			case "INSPECIONAR":

				if (comando.hasMoreTokens()) {
					String inspec = comando.nextToken();

					if (salaAtual.getObjetos().containsKey(inspec)) {
						log.appendText(salaAtual.getObjetos().get(inspec).getDescricao() + "\n");
					} else {
						log.appendText("Este objeto n�o existe nesta sala." + "\n");

					}

					break;
				} else {
					log.appendText(salaAtual.textoDescricao());
					log.appendText("Voc� procura por ferramentas.\n");
					log.appendText(salaAtual.ferramentasDisponiveis().toString());
					log.appendText("\nVoc� procura por objetos.\n");
					log.appendText(salaAtual.objetosDisponiveis().toString());
					log.appendText("\nVoc� procura por sa�das.\n");
					log.appendText(salaAtual.portasDisponiveis().toString() + "\n");
					break;
				}

			case "USAR":
				try {

					String item = comando.nextToken();

					if (mochila.inventario().contains(item)) {

						if (salaAtual.usa(item) == false) {
							log.appendText("Este item n�o deve ser usado agora.\n");
							break;
						}

						tela.setImage(salasVisual.get(salaAtual.getRepVisual()));
						log.appendText(salaAtual.textoDescricao());

					} else {
						log.appendText("Este item n�o est� em seu invent�rio.\n");

					}

					break;
				} catch (NoSuchElementException e) {
					log.appendText("Comando inv�lido.\n");
					break;
				}

			case "PEGAR":
				try {
					String itemAux = comando.nextToken();

					if (salaAtual.getFerramentas().containsKey(itemAux)) {
						mochila.guardar(salaAtual.getFerramentas().get(itemAux));
						salaAtual.getFerramentas().remove(itemAux);
						log.appendText("Voc� pegou: " + itemAux + "\n");

					} else {
						log.appendText("Este item n�o pode ser encontrado nesta sala.\n");
					}
					;
					break;
				} catch (NoSuchElementException e) {
					log.appendText("Comando inv�lido.\n");
					break;
				}

			case "INTERAGIR":
				try {

					String obj = comando.nextToken();
					String maneira = comando.nextToken();

					if (salaAtual.getObjetos().containsKey(obj)) {

						if (intel < 5) {
							log.appendText("Voc� n�o tem intelig�ncia suficiente para enteder esse Puzzle." + "\n");
							break;
						}

						if (salaAtual.interage(obj, maneira)) {
							log.appendText("A parege reage ao seu comando!" + "\n");

							tela.setImage(salasVisual.get(salaAtual.getRepVisual()));
							log.appendText(salaAtual.textoDescricao());

						} else {
							log.appendText("A parede continua im�vel." + "\n");
						}

					} else {
						log.appendText("Este objeto n�o existe nesta sala." + "\n");
					}

					break;

				} catch (NoSuchElementException e) {
					log.appendText("Comando inv�lido.\n");
					break;
				}

			case "SAIR":
				try {
					salvarJogo();
				} catch (IOException e) {
					log.appendText("Ocorreu um erro ao salvar o jogo.\n");

				}
				break;

			case "MERECE10?":
				log.appendText("Sim.\n");
				break;

			default:
				log.appendText("Comando inv�lido.\n");
				break;

			}

		} else

		{
			log.appendText("Comando inv�lido.\n");

		}

	}

	public Integer getForca() {
		return forca;
	}

	public Integer getDex() {
		return dex;
	}

	public Integer getIntel() {
		return intel;
	}

	public Integer getConst() {
		return con;
	}

	public Double getVida() {
		return vida;
	}

	public Double getMana() {
		return mana;
	}

}
