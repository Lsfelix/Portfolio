package objetos;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Objeto;

public class Alavanca extends Objeto {

	public Alavanca() {
		super("A alavanca está enferrujada, somente alguem muito forte consiguirá manusea-lá.",
				"A alavanca continua enferrujada, porém agora pro lado certo!");
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
