package ferramentas;

import ClassesBasicas.Ferramenta;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;

public class Varinha extends Ferramenta {

	private ProgressBar mana;

	public Varinha(ProgressBar mana) {
		super("VARINHA", new Image("file:varinha.png", 60, 60, true, true));
		this.mana = mana;

		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean usar() {
		if (this.mana.getProgress() - 0.40 <= 0) {
			return false;
		} else {
			this.mana.setProgress(this.mana.getProgress() - 0.40);
			return true;
		}

	}

}
