package com.example.mkaaf.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView   lancementSolo = null, lancementMulti = null;

    /*
    *
    *   Creation de la premiere activitée, l'activité principale, on mettra dessus le choix si on veut
    *   jouer au morpion / au puissance 4 / en solo / en multi / et les options
    *
    */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lancementSolo = (TextView) findViewById(R.id.LancementSolo);
		lancementMulti = (TextView) findViewById(R.id.LancementMulti);


		// On lance le jeu en solo ou multi en fonction de l'id cliqué
		lancementSolo.setOnClickListener(VersSolo);
		lancementMulti.setOnClickListener(VersMultiOffline);
	}


    /*
    *
    *   Permet de creer un listener qui va creer une nouvelle activité pour pouvoir changer de page
    *   (ici part vers l'activité "jouer au morpion", on peut creer un autre listener pour creer une
    *   activité vers le puissance 4 par ex)
    *   Chercher comment lancer le morpion en multijoueur (grace au bluetooth ou wi-fi).
    *
    */


	private OnClickListener VersSolo = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(MainActivity.this, Solo.class);
			startActivity(intent);
		}
	};


	private OnClickListener VersMultiOffline = new OnClickListener() {
		@Override
		public void onClick(View v) {

			Intent intent = new Intent(MainActivity.this, MultijoueurOffline.class);
			startActivity(intent);
		}
	};
}



/*
*
*	Ameliorations a faire, classé par ordre d'importance :
*		-Mettre le mode solo
*		-Mettre le jeu en reseau (bluetooth, wifi..)
*		-Ajouter un tableau de score (nombre de victoire/defaites)
*		-Ajouter plusieurs jeux (Puissance 4, un tetris...)
*	    -Ajouter un morpion ultime (9 morpions en tout, on joue sur une case qui fait qu'on va devoir jouer sur une autre case ...
*                       , y ajouter un defilement de camera pour les deplacement, avec zoom / dezoom
*
*
 */

