package objetos;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Objeto;
import ferramentas.Varinha;

public class Codigo extends Objeto {

	private String solucao;

	public Codigo() {
		super("Uma parede com escritos estranhos est� a sua frente. \"A escurid�o traz � luz\".",
				"Voc� desvendou o desafio.");
		solucao = "CAESAR";
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean usar(Ferramenta f) {

		if (f instanceof Varinha) {
			return f.usar();
		}

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
