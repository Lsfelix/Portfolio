using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI; //Habilita os componentes de User Interface
using UnityEngine.SceneManagement;

public class temaJogo : MonoBehaviour
{

    public Button btnPlay;
    public Text txtNomeTema;

    public Text infoTemaNovo;



    public string[] nomeTema;
    public int numeroQuestoes;



    private int idTema;

    // Use this for initialization
    void Start()
    {
        idTema = 0;
        txtNomeTema.text = nomeTema[idTema];
        infoTemaNovo.text = nomeTema[idTema + 4];
        btnPlay.interactable = false;
    }

    public void selecioneTema(int i)
    {
        idTema = i;
        txtNomeTema.text = nomeTema[i];
        infoTemaNovo.text = nomeTema[idTema + 1];
        PlayerPrefs.SetInt("tema", idTema);

        btnPlay.interactable = true;
    }

    public void jogar()
    {
        PlayerPrefs.SetInt("tema", idTema);

        SceneManager.LoadScene("T" + idTema.ToString());
    }

}
