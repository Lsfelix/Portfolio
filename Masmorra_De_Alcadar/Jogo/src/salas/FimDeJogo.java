package salas;

import ClassesBasicas.Sala;

public class FimDeJogo extends Sala {

	public FimDeJogo() {
		super("FIM", "salaEscura");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String textoDescricao() {
		// TODO Auto-generated method stub
		return "Parabéns! Muito obrigado por jogar.\n";
	}

	@Override
	public boolean usa(String ferramenta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean interage(String objeto, String maneira) {
		// TODO Auto-generated method stub
		return false;
	}

}
