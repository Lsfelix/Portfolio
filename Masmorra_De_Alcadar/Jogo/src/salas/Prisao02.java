package salas;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Sala;
import ferramentas.Marreta;
import ferramentas.Punhos;
import objetos.ParedeRachada;

public class Prisao02 extends Sala {

	private boolean quebrou;

	public Prisao02() {
		super("PRISAO", "prisao");
		ParedeRachada racha = new ParedeRachada();

		quebrou = false;

		getObjetos().put("RACHADURA", racha);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String textoDescricao() {
		if (quebrou) {
			return "A parede estilha�a aos seus p�s.\n";
		}

		return "Ao sair de sua cela, voc� se depara com uma s�rie de corredores misteriosos. Como far� para sair da�?\n";
	}

	@Override
	public boolean usa(String ferramenta) {
		Ferramenta f = getMochila().usar(ferramenta);

		if (!this.quebrou) {

			if (f instanceof Marreta) {
				this.quebrou = ((ParedeRachada) (this.getObjetos().get("RACHADURA"))).usar(f);

				if (quebrou) {
					this.setRepVisual("prisaoquebrada");
					Sala segredo = new SegredoQ();
					this.getPortas().put(segredo.getNome(), segredo);
					segredo.getPortas().put(this.getNome(), this);
					quebrou = true;
					return true;
				}
				return false;
			}

			if (f instanceof Punhos) {
				this.quebrou = ((ParedeRachada) (this.getObjetos().get("RACHADURA"))).usar(f);

				if (quebrou) {
					this.setRepVisual("prisaoquebrada");
					Sala segredo = new SegredoQ();
					this.getPortas().put(segredo.getNome(), segredo);
					segredo.getPortas().put(this.getNome(), this);
					quebrou = true;
					return true;
				}
				return false;

			}
		}

		return false;
	}

	@Override
	public boolean interage(String objeto, String maneira) {
		return false;
	}
}
