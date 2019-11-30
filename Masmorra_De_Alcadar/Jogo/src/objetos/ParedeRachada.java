package objetos;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Objeto;
import ferramentas.Marreta;
import ferramentas.Punhos;

public class ParedeRachada extends Objeto {

	public ParedeRachada() {
		super("Está parede parece estar um pouco frágil.", "Apenas resta o rombo deixado após esta barbaridade.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean usar(Ferramenta f) {

		if (!this.isAcaoOk()) {

			if (f instanceof Marreta) {
				this.setAcaoOk(true);

				return true;
			}

			if (f instanceof Punhos) {
				this.setAcaoOk(true);

				return true;
			}

		}

		return false;
	}

	@Override
	public boolean interagir(String s) {
		// TODO Auto-generated method stub
		return false;
	}

}
