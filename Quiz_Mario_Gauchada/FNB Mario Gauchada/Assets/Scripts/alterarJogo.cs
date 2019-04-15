using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class alterarJogo : MonoBehaviour
{

    public GameObject cenario;
    public GameObject estrelas;
    public GameObject fundo_novo;
    public Sprite mario_pulo;
    public Sprite mario_estrela;
    private int modo_jogo;

    public Button buraco_negro;
    public GameObject jogoVelho;

    public GameObject jogoNovo;

    public Image componenteDeImagem;
    void Start()
    {
        modo_jogo = 0;
        cenario.SetActive(false);
        estrelas.SetActive(true);
        fundo_novo.SetActive(true);

    }

    // Update is called once per frame

    public void alteraJogo()
    {
        if (modo_jogo == 0)
        {
            cenario.SetActive(true);
            estrelas.SetActive(false);
            fundo_novo.SetActive(false);
            jogoVelho.SetActive(true);
            jogoNovo.SetActive(false);

            componenteDeImagem.sprite = mario_pulo;
            modo_jogo = 1;
        }
        else
        {
            cenario.SetActive(false);
            estrelas.SetActive(true);
            fundo_novo.SetActive(true);
            jogoVelho.SetActive(false);
            jogoNovo.SetActive(true);

            componenteDeImagem.sprite = mario_estrela;
            modo_jogo = 0;
        }
    }
}
