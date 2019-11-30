package salas;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Sala;
import ferramentas.Espada;
import ferramentas.Tocha;
import ferramentas.Varinha;

public class SegredoQ extends Sala {

	private boolean escuro;

	public SegredoQ() {
		super("SEGREDOQ", "salaEscura");
		escuro = true;

		// TODO Auto-generated constructor stub
	}

	@Override
	public String textoDescricao() {

		if (escuro) {
			return "A sala está escura demais para ver qualquer coisa.\n";
		}

		return "Agora que pode ver, encontrou um valioso artefato!\n";
	}

	public boolean isEscura() {
		return escuro;
	}

	public void setNEscuro() {
		this.escuro = false;
		this.setRepVisual("segredoQ");
		Espada espada = new Espada();
		this.getFerramentas().put(espada.getDescricao(), espada);
	}

	@Override
	public boolean usa(String ferramenta) {
		Ferramenta f = this.getMochila().usar(ferramenta);

		if (escuro) {

			if (f instanceof Varinha) {
				if (f.usar()) {
					this.escuro = false;
					this.setRepVisual("segredoQ");
					Espada espada = new Espada();
					this.getFerramentas().put(espada.getDescricao(), espada);
					return true;
				} else {
					return false;
				}
			}

			if (f instanceof Tocha) {
				if (f.usar()) {
					this.setRepVisual("segredoQ");
					Espada espada = new Espada();
					this.getFerramentas().put(espada.getDescricao(), espada);
					return true;
				}
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
