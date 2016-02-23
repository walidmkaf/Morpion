package com.example.mkaaf.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Mkaaf on 19/02/16.
 */
public class Solo extends Activity {
	Boutons       lesBoutons;
	ObjetsJeu     objetsJeu;
	SoloFonctions fonctionJeu;
	Button[]      boutonnerie;
	TextView      textJoueur;
	IA            iaDeLordi;

	protected void onCreate(Bundle savedInstanceState) {

		//
		//  Creation de la vue Pour l'activité Morpion
		//

		super.onCreate(savedInstanceState);
		setContentView(R.layout.jeu);


        /*
        *
        *
        *       On creer tout les objets grace a une fonction, qui va initialiser tout les objet
        *       qu'on va utiliser.
        *
        *
        */

		objetsJeu = new ObjetsJeu();
		fonctionJeu = new SoloFonctions();
		lesBoutons = new Boutons(this);
		textJoueur = (TextView) findViewById(R.id.textJoueur);
		boutonnerie = lesBoutons.getBoutonerie();
		iaDeLordi = new IA();

		textJoueur.setText("A vous de jouer !");

        /*
        *
        *       On initialise la boucle pour si on clique sur un bouton
        *
        */
		if (objetsJeu.getJoueur() == 1) {
			int iTmp = 0;
			for (final Button bouton : boutonnerie) {
	        /*
            *
            *       On appelle la fonction uniquement si on a pas encore gagné (je vais enlever toute
            *       facon on s'en fout, on recommence quand c'est fini), ou si on a pas deja cliqué
            *       sur le bouton
            *
            */

				final int i = iTmp;
				if (objetsJeu.getValeurTableau(iTmp) == 0) {
					bouton.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
                        /*
                        *       On Appelle un Listener pour quand on clique sur le bouton, il lance
                        *       la fonction principale du jeu, dans la classe Ingame, qui permet de jouer
                        */
							fonctionJeu.FonctionPrincipaleJoueur(objetsJeu, i, lesBoutons);

							// On affiche le texte de cest a qui de jouer en haut, Faudrais aussi faire
							// un Toast pour avoir le petit truc qui dis cest a qui de jouer.
							String messageJoueur = "A vous de jouer !";
							textJoueur.setText(messageJoueur);
						}
					});
				}
				iTmp++;
			}
		}

		// A l'ordinateur de joué
		else if (objetsJeu.getJoueur() == 2) {
			fonctionJeu.FonctionPrincipaleOrdi(objetsJeu, lesBoutons);
		}


		/*
        *       On Apelle la fonction pour creer une boite de dialogue si quelqu'un
        *       a gagné, et on choisi alors s'il veut recommencer ou pas.
        */

		// Affiche les boites de dialogue si l'un des deux a gagné
		if (objetsJeu.getVictoire() && objetsJeu.getJoueur() == 1) {
			dialogVictoire(0);
		}
		else if (objetsJeu.getMatchNul()) {
			dialogVictoire(1);
		}
		else if (objetsJeu.getVictoire() && objetsJeu.getJoueur() == 2){
			dialogVictoire(2);
		}
	}


	// Fonction qui va initialiser chaque objet lorsqu'on l'appelle

	private void Recommencer() {
		int id = 0;

		for (Button bouton : boutonnerie) {
			// On vide le texte a l'interieur du bouton
			bouton.setText(" ");

			// Ensuite on reinitialise la case dans le tableau
			objetsJeu.setValeurTableau(id, 0);

			// Enfin on remet le booleen comme faux
			//tableau.setRecommencer(false);
			objetsJeu.setVictoire(false);
			objetsJeu.setMatchNul(false);
			bouton.setClickable(true);
			id++;
		}

		iaDeLordi.setFoirerMatchNul(false);
	}


    /*
    *
    * Fonction qui permet d'afficher une boite de dialogue lorsque quelqu'un gagne
    * (Id permet de recuperer si le match se termine si quelqu'un gagne ou si c'est un
    * match nul)
    *
    */

	private void dialogVictoire(int id) {
		// On passe par un Builder pour creer la boite de dialogue (plus simple, mieux)
		String message = "", titre = "";

		switch (id) {
			case 0:
				titre = "Victoire ! ";
				message = "Vous avez gagné cette manche !!";
				break;
			case 1:
				titre = "Match Nul";
				message = "Vous avez fait match nul.";
				break;
			case 2:
				titre = "Defaite";
				message = "Malheuresement, c'est l'IA qui a gagné !";
				break;
		}

		AlertDialog.Builder victoire = new AlertDialog.Builder(this);
		victoire.setTitle(titre).setMessage(message)

			// On creer deux boutons, un positif pour recommencer, et un negatif pour quitter
			// et revenir a la page precedent.

			.setPositiveButton("Recommencer", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					Recommencer();
				}
			}).setNegativeButton("Quitter", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Solo.this.finish();
				// (Faut quitter aussi lapplication mainactivity)
				// Je sais pas si on quitte aussi main activity ou si on quitte juste le
				// morpion et sa fait retour menu principale genre.
			}
		});

		AlertDialog dialog = victoire.create();
		dialog.show();
	}
}
