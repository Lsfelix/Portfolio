package salas;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Sala;
import execcoes.SalaTrancadaException;
import ferramentas.Circulo;
import ferramentas.Quadrado;
import ferramentas.Triangulo;

public class Cela01 extends Sala {

	private boolean trancado, tri, quadrado;

	public Cela01() {
		super("CELA", "cela");
		Triangulo triangulo = new Triangulo();
		Quadrado quad = new Quadrado();
		Circulo circ = new Circulo();

		getFerramentas().put(triangulo.getDescricao(), triangulo);
		getFerramentas().put(circ.getDescricao(), circ);
		getFerramentas().put(quad.getDescricao(), quad);

		trancado = true;
		tri = false;
		quadrado = false;

		// TODO Auto-generated constructor stub
	}

	public void load(boolean a, boolean b, boolean c) {
		this.trancado = a;
		this.tri = b;
		this.quadrado = c;
	}

	@Override
	public Sala sai(String porta) {
		if (trancado == true) {
			throw new SalaTrancadaException();
		} else {
			return super.sai(porta);
		}
	}

	public String save() {
		return trancado + ";" + tri + ";" + quadrado;
	}

	@Override
	public String textoDescricao() {
		if (trancado == false) {
			return "Voc� ouve o barulho de engrenagens enferrujadas, se movendo ap�s muito tempo. As barras de ferro que antes o prendiam lentamente submergem para o ch�o.\n";
		} else if (quadrado == true) {
			return "Um clique soa do mecanismo, falta apenas uma pe�a!\n";
		} else if (tri == true) {
			return "Ao colocar a pe�a no mecanismo, voc� ouve um barulho encorajador.\n";
		} else {
			return "Voc� olha ao seu redor. Est� em uma sala c�bica e pequena, com tr�s parades de pedra e uma feita com grades de a�o. Est� dentro de uma cela.\n";
		}
	}

	@Override
	public boolean usa(String ferramenta) {
		Ferramenta f = this.getMochila().usar(ferramenta);

		if (f instanceof Triangulo) {
			tri = true;
			this.getMochila().remove((Triangulo) f);

			return true;
		}

		if (f instanceof Quadrado && tri == true) {
			quadrado = true;
			this.getMochila().remove((Quadrado) f);
			return true;
		}

		if (f instanceof Circulo && quadrado == true) {
			trancado = false;
			setRepVisual("celaaberta");
			this.getMochila().remove((Circulo) f);
			return true;
		}

		return false;
	}

	@Override
	public boolean interage(String objeto, String maneira) {
		return false;
	}

}
