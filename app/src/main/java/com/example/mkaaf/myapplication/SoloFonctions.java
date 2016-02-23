package com.example.mkaaf.myapplication;

import java.util.Random;
import java.util.Vector;

/**
 * Created by Mkaaf on 19/02/16.
 */
public class SoloFonctions extends IA {
	/*
	*
    *       Constructeur de la classe
    *
    */

	public SoloFonctions() {
	}



	/*
	*
	* 		Fonctions principales : Principale joueur quand c'est le tour du joueur
	* 								Principal ordi quand c'est le tour de l'IA
	*
	 */

	public void FonctionPrincipaleJoueur(ObjetsJeu tableau, int id, Boutons lesBoutons)
	 {
		tableau.setValeurTableau(id, 1);

		String x = affichageBouton(tableau);

		lesBoutons.getBouton(id).setText(x);

		lesBoutons.getBouton(id).setClickable(false);


		verifVictoire(tableau, id);

		if (!tableau.getVictoire()) {
			changementDeJoueur(tableau);
		}
	}


	public void FonctionPrincipaleOrdi(ObjetsJeu tableau, Boutons lesBoutons) {
		int id;

		id = IA_Difficile(tableau);

		tableau.setValeurTableau(id, 2);

		String x = affichageBouton(tableau);

		lesBoutons.getBouton(id).setText(x);

		lesBoutons.getBouton(id).setClickable(false);


		verifVictoire(tableau, id);

		if (!tableau.getVictoire()) {
			changementDeJoueur(tableau);
		}
	}



	/*
	*
    *       Fonctions secondaires utilisé par la fonction principale
    *
    */

	//Fonction pour recuperer le char qu'on va metter sur le bouton, a changer un jour pour un canvas ou des images

	private String affichageBouton(ObjetsJeu tableau) {
		String x = "";
		if (!tableau.getVictoire()) {
			if (tableau.getJoueur() == 1) {
				x = "X";
			}
			else {
				x = "O";
			}
		}
		return x;
	}


	//Fonction pour changer de joueur a la fin d'un tour

	private void changementDeJoueur(ObjetsJeu tableau) {
		if (tableau.getJoueur() == 1) {
			tableau.setJoueur(2);
		}
		else {
			tableau.setJoueur(1);
		}
	}


	// Fonction pour verifier si on a gagné ou pas

	private void verifVictoire(ObjetsJeu tableau, int i) {
		boolean finDeJeu = false;
		int     ligne    = i / 3;
		int     colonne  = i % 3;

		/*
		*
		*
		* On teste si la dans le jeu la case centrale est cochée par l'un des joueurs ou l'autre
		* (sinon les diagonales ne peuvent pas etre gagnante si aucun des joueurs n'a coché le centre)
		* Ensuite on teste la ligne et la colonne du bouton que le joueur viens de coché.
		*
		*/
		if (tableau.getValeurTableau(1, 1) != 0) {
			if (tableau.getValeurTableau(0, 0) == tableau.getValeurTableau(1, 1) && tableau.getValeurTableau(1, 1) == tableau.getValeurTableau(2, 2)) finDeJeu = true;
			else if (tableau.getValeurTableau(2, 0) == tableau.getValeurTableau(1, 1) && tableau.getValeurTableau(1, 1) == tableau.getValeurTableau(0, 2)) finDeJeu = true;
		}

		if (tableau.getValeurTableau(ligne, 0) == tableau.getValeurTableau(ligne, 1) && tableau.getValeurTableau(ligne, 1) == tableau.getValeurTableau(ligne, 2)) finDeJeu = true;
		if (tableau.getValeurTableau(0, colonne) == tableau.getValeurTableau(1, colonne) && tableau.getValeurTableau(1, colonne) == tableau.getValeurTableau(2, colonne)) finDeJeu = true;

		tableau.setVictoire(finDeJeu);

		/*
		*
		* Une fois que la victoire est testée, on teste le match nul (algorithme a ameliorer pour avoir
		* le match nul meme avant que toute les cases soit prises, genre quand il reste que 1 ou 2 case
		* vide mais pas possible de gagner)
		*
		*/

		if (tableau.getTableauPlein()) {
			tableau.setMatchNul(true);
		}
	}


}
