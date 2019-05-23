using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEditor;

public class DeletePlayerPrefs : MonoBehaviour {

	[MenuItem("Edit/Reset Playerprefs")]
	public static void DeletePlayerPref()
	{
		PlayerPrefs.DeleteAll();
	}
}
