using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class ResponderSegredo : MonoBehaviour
{

    public InputField namefield;
    public Text text;


    private string resposta;

    public void Awake()
    {
    }


    public void OnSubmit()
    {
        resposta = namefield.text;

        Debug.Log("You selected" + resposta);



        if (resposta == "TRIBAGUAL")
        {
            Debug.Log("Certo");

            SceneManager.LoadScene("Final");

        }
        else
        {
            text.text = "Não é bem assim...";
        }

    }
}
