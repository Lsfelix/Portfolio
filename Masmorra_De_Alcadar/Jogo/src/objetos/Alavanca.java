package objetos;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Objeto;

public class Alavanca extends Objeto {

	public Alavanca() {
		super("A alavanca est� enferrujada, somente alguem muito forte consiguir� manusea-l�.",
				"A alavanca continua enferrujada, por�m agora pro lado certo!");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean usar(Ferramenta f) {

		return false;
	}

	@Override
	public boolean interagir(String s) {
		// TODO Auto-generated method stub
		return false;
	}

}
