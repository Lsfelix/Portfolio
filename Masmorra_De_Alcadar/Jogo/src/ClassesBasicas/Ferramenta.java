package ClassesBasicas;

import javafx.scene.image.Image;

public class Ferramenta {
	private String descricao;
	private Image icon;

	public Ferramenta(String descricao, Image icon) {
		super();
		this.descricao = descricao;
		this.icon = icon;
	}

	public String getDescricao() {
		return descricao;
	}

	public boolean usar() {
		return true;
	}

	public Image getIcon() {
		return icon;
	}

	public void setIcon(Image icon) {
		this.icon = icon;
	}
}
