using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class language : MonoBehaviour
{

    private bool pt = true;

    public Text Nossa;
    public Text Terra;
    public Text Nosso;
    public Text Ceu;

    public Text e;


    public void changeLanguage()
    {
        pt = !pt;
        if (pt == false)
        {
            Nossa.text = "Nuestra";
            Terra.text = "Tierra";
            Nosso.text = "Nuestro";
            Ceu.text = "Cielo";
            e.text = "y";

        }
        else
        {
            Nossa.text = "Nossa";
            Terra.text = "Terra";
            Nosso.text = "Nosso";
            Ceu.text = "Céu";
            e.text = "&";

        }
    }
}
