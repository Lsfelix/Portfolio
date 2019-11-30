package ferramentas;

import ClassesBasicas.Ferramenta;
import javafx.scene.image.Image;

public class Tocha extends Ferramenta {

	private boolean acesa;

	public Tocha() {
		super("TOCHA", new Image("file:tocha.png", 60, 60, true, true));
		acesa = false;
		// TODO Auto-generated constructor stub
	}

	public boolean getAcesa() {
		return acesa;
	}

	public void acende() {
		this.acesa = true;
	}

	@Override
	public boolean usar() {
		if (acesa) {
			return true;
		}
		return false;
	}

}
