package salas;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Sala;
import ferramentas.Tocha;
import ferramentas.Varinha;
import objetos.Codigo;

public class Corredor03 extends Sala {

	public Corredor03() {
		super("CORREDOR", "corredor");
		Tocha tocha = new Tocha();
		getFerramentas().put(tocha.getDescricao(), tocha);

		Codigo code = new Codigo();
		getObjetos().put("ESCRITAS", code);

	}

	@Override
	public String textoDescricao() {
		return "Você aventura pelo local desconhecido, e encontra uma parede com uns escritos estranhos.\n";
	}

	@Override
	public boolean usa(String ferramenta) {
		Ferramenta f = getMochila().usar(ferramenta);

		if (f instanceof Varinha) {
			return this.getObjetos().get("ESCRITAS").usar(f);
		}

		return false;
	}

	@Override
	public boolean interage(String objeto, String maneira) {

		if (getObjetos().containsKey(objeto)) {
			
			if ( ((Codigo) getObjetos().get(objeto)).interagir(maneira)) {
				((PortaFinal04) this.getPortas().get("PORTAO")).destravaMagia();
				return true;
			}
			
		}

		return false;
	}

}
