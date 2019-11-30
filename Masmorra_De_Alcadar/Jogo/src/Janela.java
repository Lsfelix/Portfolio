import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Janela extends Application {

	private Stage palco;
	private TextField entradaTexto;
	private TextArea logTexto;
	private Scene cena;
	private Scene menu;
	private ImageView telaDoJogo;
	private List<ImageView> itens;
	private GameController engine;
	private ComboBox<String> classes;
	private Label showCons, showDex, showInt, showForca;
	private ProgressBar vidaS, manaS;

	public static void main(String[] args) {

		launch(args);
	}

	public void start(Stage palco) {

		this.palco = palco;
		palco.setTitle("Masmorra Alcadar");
		palco.setResizable(false);

		GridPane layoutJanela = new GridPane();

		cena = new Scene(layoutJanela, 900, 600);

		telaDoJogo = new ImageView();

		logTexto = new TextArea();

		itens = new ArrayList<>();

		manaS = new ProgressBar();

		engine = new GameController(this);

		// Arrumando Menu

		StackPane fundo = new StackPane();

		GridPane layoutMenu = new GridPane();

		layoutMenu.setVgap(30);
		layoutMenu.setHgap(30);

		menu = new Scene(fundo, 900, 600);

		Image p2 = new Image("file:stonewall.png", 950, 650, false, false);

		ImageView planoDeFundo2 = new ImageView(p2);

		fundo.getChildren().add(planoDeFundo2);

		Label title = new Label("Masmorra de Alcadar");
		title.setStyle("-fx-font-size: 60px;" + "-fx-font-family: \"monospace\";" + "-fx-text-fill: black;"
				+ "-fx-background-color: bisque;");
		title.setAlignment(Pos.CENTER);

		HBox hb = new HBox();

		classes = new ComboBox<>();

		classes.setPromptText("Escola sua Classe");
		classes.getItems().addAll("Guerreiro", "Ladino", "Mago");

		Button Bstart = new Button("Jogar");
		Bstart.setDisable(true);

		Button Bload = new Button("Carregar Jogo");

		classes.setOnAction(e -> desbloqueiaStart(Bstart));

		Bstart.setOnAction(e -> iniciaJogo(e));

		Bload.setOnAction(e -> loadJogo(e));

		hb.getChildren().addAll(classes, Bstart, Bload);

		// layoutMenu.add(planoDeFundo2, 0, 0, 3, 4);
		layoutMenu.add(title, 2, 1, 2, 2);
		layoutMenu.add(hb, 2, 3);
		layoutMenu.setAlignment(Pos.CENTER);
		fundo.getChildren().add(layoutMenu);

		// Plano de Fundo

		Image pl = new Image("file:stonewall.png", 950, 650, false, false);

		ImageView planoDeFundo = new ImageView(pl);

		// Stats

		GridPane stats = new GridPane();

		stats.setVgap(20);
		stats.setHgap(15);

		// HP
		Label vida = new Label("Vida");
		vida.setStyle("-fx-font-size: 40px;" + "-fx-font-family: \"monospace\";" + "-fx-text-fill: red;"
				+ "-fx-background-color: bisque;");
		stats.add(vida, 0, 0);

		vidaS = new ProgressBar();
		stats.add(vidaS, 2, 0);
		vidaS.setStyle("-fx-accent: red;");

		// Mana
		Label mana = new Label("Mana");
		mana.setStyle("-fx-font-size: 40px;" + "-fx-font-family: \"monospace\";" + "-fx-text-fill: blue;"
				+ "-fx-background-color: bisque;");
		stats.add(mana, 0, 1);

		stats.add(manaS, 2, 1);

		// For�a

		Label lforca = new Label("For�a:");
		lforca.setStyle("-fx-font-size: 20px;" + "-fx-font-family: \"monospace\";" + "-fx-text-fill: black;"
				+ "-fx-background-color: bisque;" + "-fx-alignment: center right;");
		stats.add(lforca, 0, 2);

		// Stat For�a
		showForca = new Label();
		showForca.setStyle("-fx-font-size: 24px;" + "-fx-font-family: \"fantasy\";" + "-fx-font-weight: bold;"
				+ "-fx-text-fill: white;");
		stats.add(showForca, 1, 2);

		// Intelig�ncia

		Label lInteligencia = new Label("Intelig�ncia:");
		lInteligencia.setStyle("-fx-font-size: 20px;" + "-fx-font-family: \"monospace\";" + "-fx-text-fill: black;"
				+ "-fx-background-color: bisque;");
		stats.add(lInteligencia, 2, 2);

		// Stat Intelig�ncia
		showInt = new Label();
		showInt.setStyle("-fx-font-size: 24px;" + "-fx-font-family: \"fantasy\";" + "-fx-font-weight: bold;"
				+ "-fx-text-fill: white;");
		stats.add(showInt, 3, 2);

		// Destreza

		Label lDestreza = new Label("Destreza:");
		lDestreza.setStyle("-fx-font-size: 20px;" + "-fx-font-family: \"monospace\";" + "-fx-text-fill: black;"
				+ "-fx-background-color: bisque;");
		stats.add(lDestreza, 0, 3);

		// Stat Destreza
		showDex = new Label();
		showDex.setStyle("-fx-font-size: 24px;" + "-fx-font-family: \"fantasy\";" + "-fx-font-weight: bold;"
				+ "-fx-text-fill: white;");
		stats.add(showDex, 1, 3);

		// Constitui��o

		Label lConstituicao = new Label("Constitui��o:");
		lConstituicao.setStyle("-fx-font-size: 20px;" + "-fx-font-family: \"monospace\";" + "-fx-text-fill: black;"
				+ "-fx-background-color: bisque;");
		stats.add(lConstituicao, 2, 3);

		// Stat Constitui��o
		showCons = new Label();
		showCons.setStyle("-fx-font-size: 24px;" + "-fx-font-family: \"fantasy\";" + "-fx-font-weight: bold;"
				+ "-fx-text-fill: white;");
		stats.add(showCons, 3, 3);

		// Itens

		GridPane mochila = new GridPane();
		mochila.setAlignment(Pos.CENTER_LEFT);
		mochila.setPadding(new Insets(25, 25, 25, 25));

		mochila.add(itens.get(0), 0, 0);
		mochila.add(itens.get(1), 1, 0);
		mochila.add(itens.get(2), 2, 0);
		mochila.add(itens.get(3), 3, 0);
		mochila.add(itens.get(4), 4, 0);
		mochila.add(itens.get(5), 5, 0);
		mochila.add(itens.get(6), 0, 1);
		mochila.add(itens.get(7), 1, 1);
		mochila.add(itens.get(8), 2, 1);
		mochila.add(itens.get(9), 3, 1);
		mochila.add(itens.get(10), 4, 1);
		mochila.add(itens.get(11), 5, 1);

		stats.setGridLinesVisible(false);

		// Entrada de Texto
		entradaTexto = new TextField();
		entradaTexto.setAlignment(Pos.CENTER_RIGHT);
		entradaTexto.setOnKeyReleased(e -> trataTecla(e));

		// Text Log

		logTexto.setEditable(false);
		logTexto.setMaxHeight(180);
		logTexto.setWrapText(true);

		// Entrada
		VBox entrada = new VBox();
		entrada.getChildren().add(logTexto);
		entrada.getChildren().add(entradaTexto);

		// Layout Config

		layoutJanela.setVgap(10);
		layoutJanela.setHgap(10);

		layoutJanela.add(planoDeFundo, 0, 0, 4, 4);
		layoutJanela.add(telaDoJogo, 1, 1, 1, 1);
		layoutJanela.add(mochila, 1, 2);
		layoutJanela.add(stats, 2, 1);
		layoutJanela.add(entrada, 2, 2);

		layoutJanela.setGridLinesVisible(false);

		palco.setScene(menu);
		palco.show();

	}

	private void loadJogo(ActionEvent e) {
		try {
			engine.loadJogo();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		showCons.setText(String.valueOf(engine.getConst()));
		showDex.setText(String.valueOf(engine.getDex()));
		showInt.setText(String.valueOf(engine.getIntel()));
		showForca.setText(String.valueOf(engine.getForca()));

		vidaS.setProgress(1);

		manaS.setProgress(engine.getMana());

		palco.setScene(cena);

	}

	public List<ImageView> getItens() {
		return itens;
	}

	public TextArea getLog() {
		return logTexto;
	}

	public ProgressBar getMana() {
		return manaS;
	}

	public ImageView getTela() {
		return telaDoJogo;
	}

	private void desbloqueiaStart(Button bstart) {
		bstart.setDisable(false);
	}

	private void iniciaJogo(ActionEvent e) {

		if (classes.getValue() != null) {

			String job = classes.getValue();

			engine.start(job);

			showCons.setText(String.valueOf(engine.getConst()));
			showDex.setText(String.valueOf(engine.getDex()));
			showInt.setText(String.valueOf(engine.getIntel()));
			showForca.setText(String.valueOf(engine.getForca()));

			vidaS.setProgress(engine.getVida());
			manaS.setProgress(engine.getMana());

			palco.setScene(cena);

		}

	}

	private void trataTecla(KeyEvent e) {

		KeyCode tecla = e.getCode();

		if (tecla.equals(KeyCode.ENTER)) {

			String auxText = entradaTexto.getText().toUpperCase();

			if (auxText != null) {

				logTexto.appendText(auxText + "\n");

				engine.recebeComando(auxText);

				entradaTexto.clear();
			}

		} else if (tecla.equals(KeyCode.BACK_SPACE)) {

			entradaTexto.deletePreviousChar();
		}

		e.consume();
	}
}
