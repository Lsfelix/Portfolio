using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class notaFinal : MonoBehaviour
{

    private int idTema;

    public Text txtMostra;
    public GameObject notaMaxima;

    private int notaF;

    // Use this for initialization
    void Start()
    {
        idTema = PlayerPrefs.GetInt("tema");
        notaF = PlayerPrefs.GetInt("notaFinalTemp" + idTema.ToString());

        txtMostra.text = notaF.ToString();
    }

}
