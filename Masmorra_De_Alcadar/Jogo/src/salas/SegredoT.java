package salas;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Sala;
import ferramentas.Orbe;
import ferramentas.Tocha;
import ferramentas.Varinha;

public class SegredoT extends Sala {

	private boolean escuro;

	public SegredoT() {
		super("SEGREDOT", "salaEscura");
		escuro = true;

		// TODO Auto-generated constructor stub
	}

	public boolean isEscura() {
		return escuro;
	}

	@Override
	public String textoDescricao() {

		if (escuro) {
			return "A sala está escura demais para ver qualquer coisa.\n";
		}

		return "Agora que pode ver, encontrou um valioso artefato!\n";
	}

	public void setNEscuro() {
		this.escuro = false;
		this.setRepVisual("segredoT");
		Orbe orb = new Orbe();
		this.getFerramentas().put(orb.getDescricao(), orb);
	}

	@Override
	public boolean usa(String ferramenta) {
		Ferramenta f = this.getMochila().usar(ferramenta);

		if (escuro) {

			if (f instanceof Varinha) {
				if (f.usar()) {
					this.escuro = false;
					this.setRepVisual("segredoT");
					Orbe orb = new Orbe();
					this.getFerramentas().put(orb.getDescricao(), orb);
					return true;
				} else {
					return false;
				}
			}

			if (f instanceof Tocha) {
				if (f.usar()) {
					this.setRepVisual("segredoT");
					Orbe orb = new Orbe();
					this.getFerramentas().put(orb.getDescricao(), orb);
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
