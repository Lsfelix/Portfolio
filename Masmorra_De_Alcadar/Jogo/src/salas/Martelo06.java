package salas;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Sala;
import ferramentas.Marreta;
import ferramentas.Varinha;
import objetos.Pedestal;

public class Martelo06 extends Sala {

	private boolean segredoDescoberto;

	public Martelo06() {
		super("DIREITA", "marreta");
		Pedestal pedestal = new Pedestal();
		this.getObjetos().put("PEDESTAL", pedestal);

		Marreta marreta = new Marreta();
		this.getFerramentas().put(marreta.getDescricao(), marreta);

		segredoDescoberto = false;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String textoDescricao() {

		if (segredoDescoberto) {
			return "Uma porta misteriosa aparece, formando uma passagem na parede.\n";
		}

		return "Você esbarra em uma sala peculiar. Um grande pedestal aguarda no meio.\n";
	}

	@Override
	public boolean usa(String ferramenta) {
		Ferramenta f = getMochila().usar(ferramenta);
		if (!segredoDescoberto) {
			if (f instanceof Varinha) {
				if (f.usar()) {
					segredoDescoberto = true;
					this.setRepVisual("marretaaberta");
					Sala segredo = new SegredoT();

					this.getPortas().put(segredo.getNome(), segredo);
					segredo.getPortas().put(this.getNome(), this);

					return true;
				} else {
					return false;
				}

			}
		}

		return false;
	}

	@Override
	public boolean interage(String objeto, String maneira) {

		if (getObjetos().containsKey(objeto)) {

			if (((Pedestal) getObjetos().get(objeto)).interagir(maneira)) {
				this.setRepVisual("marretaaberta");
				Sala segredo = new SegredoT();

				this.getPortas().put(segredo.getNome(), segredo);
				segredo.getPortas().put(this.getNome(), this);

				segredoDescoberto = true;
				return true;
			}

		}

		return false;
	}

}
