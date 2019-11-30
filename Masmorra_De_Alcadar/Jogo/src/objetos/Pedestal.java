package objetos;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Objeto;

public class Pedestal extends Objeto {

	private String solucao;

	public Pedestal() {
		super("No pedestal está escrito: \"Minha solução é a letra que se semelha ao formato deste labirinto.\n",
				"O pedestal parece inativo.\n");
		solucao = "T";
	}

	@Override
	public boolean usar(Ferramenta f) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean interagir(String s) {

		if (s.equalsIgnoreCase(solucao)) {
			return true;
		}

		return false;
	}

}
