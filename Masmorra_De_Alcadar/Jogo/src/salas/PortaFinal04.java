package salas;

import ClassesBasicas.Ferramenta;
import ClassesBasicas.Sala;
import ferramentas.Espada;
import ferramentas.Orbe;
import ferramentas.Punhos;
import ferramentas.Tocha;
import ferramentas.Varinha;
import objetos.Alavanca;

public class PortaFinal04 extends Sala {

	private boolean aberto;
	private boolean magia;

	public PortaFinal04() {
		super("PORTAO", "portaFinal");
		aberto = false;
		magia = true;
		Alavanca ala = new Alavanca();
		this.getObjetos().put("ALAVANCA", ala);
	}

	@Override
	public String textoDescricao() {
		if (aberto) {
			return "O port�o se abre, enquanto as chamas ilumina a antes escurecida �rea. Voc� chegou no fim de sua jornada.\n";
		}

		if (magia) {
			return "Voc� se depara com um amea�ador port�o dourado. Um feiti�o parece afeta-l�, de modo que n�o consegues tocar nele.\n";
		}
		return "O feiti�o foi desfeito, porem como ir� abrir a porta agora?\n";
	}

	public void load(boolean a, boolean b) {
		this.aberto = a;
		this.magia = b;
	}

	public String save() {
		return aberto + ";" + magia;
	}

	public void destravaMagia() {
		this.magia = false;
	}

	@Override
	public boolean usa(String ferramenta) {
		Ferramenta f = this.getMochila().usar(ferramenta);

		if (magia) {

			if (f instanceof Espada || f instanceof Orbe) {
				destravaMagia();
				return true;
			}

			return false;
		}

		if (!aberto) {

			if (f instanceof Tocha || f instanceof Punhos) {
				aberto = true;
				this.setRepVisual("portaFinalAberta");
				this.getObjetos().get("ALAVANCA").setAcaoOk(true);
				Sala fim = new FimDeJogo();
				this.getPortas().put(fim.getNome(), fim);
				fim.getPortas().put(this.getNome(), this);
				return true;
			}

			if (f instanceof Varinha) {
				if (f.usar()) {
					aberto = true;
					this.setRepVisual("portaFinalAberta");
					this.getObjetos().get("ALAVANCA").setAcaoOk(true);
					Sala fim = new FimDeJogo();
					this.getPortas().put(fim.getNome(), fim);
					fim.getPortas().put(this.getNome(), this);
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
		// TODO Auto-generated method stub
		return false;
	}

}
