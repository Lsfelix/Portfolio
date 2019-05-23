using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;



public class comandosBasicos : MonoBehaviour {


	public void Awake(){
		Screen.SetResolution (384, 512, false);
	}

	public void carregaCena (string nomeCena)
	{
		SceneManager.LoadScene(nomeCena);
	}


}
