package salas;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Sala;
import ferramentas.Tocha;
import ferramentas.Varinha;
import javafx.scene.image.Image;

public class Cifra05 extends Sala {

	private boolean escuro;

	public Cifra05() {
		super("ESQUERDA", "cifra");
		escuro = false;
		// TODO Auto-generated constructor stub
	}

	public void load(boolean a) {
		this.escuro = a;
	}

	public boolean save() {
		return this.escuro;
	}

	@Override
	public String textoDescricao() {

		if (escuro) {
			return "A sala se escurece, porém uma nova mensagem se revela!\n";
		}

		return "Você encontra uma chama iluminando uma pequena sala. Você sente algo escondido.\n";
	}

	@Override
	public boolean usa(String ferramenta) {
		Ferramenta f = this.getMochila().usar(ferramenta);

		if (escuro) {

			if (f instanceof Varinha) {
				if (f.usar()) {
					this.escuro = false;
					this.setRepVisual("cifra");
					return true;
				} else {
					return false;
				}
			}

		} else {

			if (f instanceof Tocha) {
				this.getMochila().updateIcon(f.getIcon(), new Image("file:tochaacesa.png", 60, 60, true, true));
				((Tocha) f).acende();
				this.escuro = true;
				this.setRepVisual("cifraescuro");
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean interage(String objeto, String maneira) {
		// TODO Auto-generated method stub
		return false;
	}

}
