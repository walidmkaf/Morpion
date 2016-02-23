package com.example.mkaaf.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MultijoueurOffline extends Activity
{

    Boutons lesBoutons;
    ObjetsJeu objetsJeu;
	MultijoueurOfflineFonctions FonctionJeu;
    Button[] boutonnerie;
    TextView textJoueur;

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
		FonctionJeu = new MultijoueurOfflineFonctions();
		lesBoutons = new Boutons(this);
		textJoueur = (TextView) findViewById(R.id.textJoueur);
		boutonnerie = lesBoutons.getBoutonerie();

        /*
        *
        *       On initialise la boucle pour si on clique sur un bouton
        *
        */
		int iTmp = 0;
		for (final Button bouton:boutonnerie
				) {
            /*
            *
            *       On appelle la fonction uniquement si on a pas encore gagné (je vais enlever toute
            *       facon on s'en fout, on recommence quand c'est fini), ou si on a pas deja cliqué
            *       sur le bouton
            *
            */

			final int i = iTmp;
            if (objetsJeu.getValeurTableau(iTmp) == 0)
            {
                bouton.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
                        /*
                        *       On Appelle un Listener pour quand on clique sur le bouton, il lance
                        *       la fonction principale du jeu, dans la classe Ingame, qui permet de jouer
                        */
						FonctionJeu.FonctionPrincipale(objetsJeu, i, lesBoutons);

						// On affiche le texte de cest a qui de jouer en haut, Faudrais aussi faire
						// un Toast pour avoir le petit truc qui dis cest a qui de jouer.
						String messageJoueur = "Joueur : " + objetsJeu.getJoueur();
						textJoueur.setText(messageJoueur);

                        /*
                        *       On Apelle la fonction pour creer une boite de dialogue si quelqu'un
                        *       a gagné, et on choisi alors s'il veut recommencer ou pas.
                        */
						if (objetsJeu.getVictoire()) {
							dialogVictoire(objetsJeu.getJoueur(), 0);
						} else if (objetsJeu.getMatchNul()) {
							dialogVictoire(objetsJeu.getJoueur(), 1);
						}
						bouton.setClickable(false);
					}
				});
            }
			iTmp++;

        }
    }


    // Fonction qui va initialiser chaque objet lorsqu'on l'appelle

    private void Recommencer()
	{
		int id = 0;

		for (Button bouton:boutonnerie
			 ) {
			// On vide le texte a l'interieur du bouton
			bouton.setText(" ");

			// Ensuite on reinitialise la case dans le tableau
			objetsJeu.setValeurTableau(id,0);

			// Enfin on remet le booleen comme faux
			//tableau.setRecommencer(false);
			objetsJeu.setVictoire(false);
			objetsJeu.setMatchNul(false);
			bouton.setClickable(true);
			id++;
		}

        // Je crois qu'on dois faire un foreach pour tout vider et pas recreer les
        // objets en fait pour recommencer proprement.

    }


    /*
    *
    * Fonction qui permet d'afficher une boite de dialogue lorsque quelqu'un gagne
    * (Id permet de recuperer si le match se termine si quelqu'un gagne ou si c'est un
    * match nul)
    *
    */

    private void dialogVictoire (int joueur, int id)
    {
        // On passe par un Builder pour creer la boite de dialogue (plus simple, mieux)
		String message = "", titre = "";

		switch (id)
		{
			case 0:
				titre = "Victoire ! ";
				message = "Joueur "+ joueur +" a gagné cette manche !";
				break;
			case 1:
				titre = "Match Nul";
				message = "Les deux joueurs on fait match nul !";
				break;
		}

        AlertDialog.Builder victoire = new AlertDialog.Builder(this);
        victoire.setTitle(titre)
                .setMessage(message)

                // On creer deux boutons, un positif pour recommencer, et un negatif pour quitter
                // et revenir a la page precedent.

                .setPositiveButton("Recommencer", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Recommencer();
                    }
                })
                .setNegativeButton("Quitter", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MultijoueurOffline.this.finish();
                        // (Faut quitter aussi lapplication mainactivity)
                        // Je sais pas si on quitte aussi main activity ou si on quitte juste le
                        // morpion et sa fait retour menu principale genre.
                    }
                });

        AlertDialog dialog = victoire.create();
        dialog.show();
    }
}