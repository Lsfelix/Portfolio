package ClassesBasicas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Mochila {
	private Map<String, Ferramenta> coisas;
	private List<ImageView> inventarioImg;
	private Image padrao;

	public Mochila(List<ImageView> invent) {
		coisas = new HashMap<>();
		padrao = new Image("file:item.png", 60, 60, true, true);
		inventarioImg = invent;
		for (int i = 0; i < 12; i++) {
			invent.add(new ImageView(padrao));
		}
	}

	public void guardar(Ferramenta f) {

		coisas.put(f.getDescricao(), f);

		for (ImageView img : inventarioImg) {
			if (img.getImage().equals(padrao)) {
				img.setImage(f.getIcon());
				break;
			}
		}

	}

	public void updateIcon(Image oldIcon, Image newIcon) {
		for (ImageView image : inventarioImg) {
			if (image.getImage().equals(oldIcon)) {
				image.setImage(newIcon);
				break;
			}
		}
	}

	public Ferramenta usar(String descricao) {

		return coisas.get(descricao);
	}

	public void remove(Ferramenta f) {

		if (coisas.containsValue(f)) {
			for (ImageView img : inventarioImg) {
				if (img.getImage().equals(f.getIcon())) {
					img.setImage(padrao);
					break;
				}
			}

			coisas.remove(f.getDescricao());
		}

	}

	public List<ImageView> getInventarioImagens() {
		return inventarioImg;
	}

	public List<String> inventario() {
		return coisas.keySet().stream().collect(Collectors.toList());
	}
}
