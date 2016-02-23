package com.example.mkaaf.myapplication;

import android.app.Activity;
import android.widget.Button;


public class Boutons
{
    /*
    *
    *       Declaration des variables de la classe
    *
     */

    private Activity JeuMorpion;
    private Button bouton1;
    private Button bouton2;
    private Button bouton3;
    private Button bouton4;
    private Button bouton5;
    private Button bouton6;
    private Button bouton7;
    private Button bouton8;
    private Button bouton9;


    /*
    *
    *       Constructeur de la classe
    *
    */


    public Boutons(Activity JeuMorpion) {
        this.JeuMorpion = JeuMorpion;

        setBouton1((Button) JeuMorpion.findViewById(R.id.button));
        setBouton2((Button) JeuMorpion.findViewById(R.id.button2));
        setBouton3((Button) JeuMorpion.findViewById(R.id.button3));
        setBouton4((Button) JeuMorpion.findViewById(R.id.button4));
        setBouton5((Button) JeuMorpion.findViewById(R.id.button5));
        setBouton6((Button) JeuMorpion.findViewById(R.id.button6));
        setBouton7((Button) JeuMorpion.findViewById(R.id.button7));
        setBouton8((Button) JeuMorpion.findViewById(R.id.button8));
        setBouton9((Button) JeuMorpion.findViewById(R.id.button9));
    }


    /*
    *
    *
    *       Gang de Setter, appellé dans le constructeur pour creer les boutons
    *       et les appeller dans l'activité principale a travers un seul bouton.
    *
    *
    */


    private void setBouton1(Button bouton)
    {
        this.bouton1 = bouton;
    }
    private void setBouton2(Button bouton)
    {
        this.bouton2 = bouton;
    }
    private void setBouton3(Button bouton)
    {
        this.bouton3 = bouton;
    }
    private void setBouton4(Button bouton)
    {
        this.bouton4 = bouton;
    }
    private void setBouton5(Button bouton)
    {
        this.bouton5 = bouton;
    }
    private void setBouton6(Button bouton)
    {
        this.bouton6 = bouton;
    }
    private void setBouton7(Button bouton)
    {
        this.bouton7 = bouton;
    }
    private void setBouton8(Button bouton)
    {
        this.bouton8 = bouton;
    }
    private void setBouton9(Button bouton)
    {
        this.bouton9 = bouton;
    }



    /*
    *
    *
    *       Getter des objets boutons, on pourrait creer des setters si on voulais
    *       creer des boutons dynamiquement (pas besoin pour le morpion, utile pour
    *       creer les images dans le puissance 4.
    *
    *
    */


    //Permet de recuperer une listes de boutons pour avoir tout les boutons dans un
    //foreach par exemple.
    public Button[] getBoutonerie() {
        Button[] boutonerie = {this.bouton1, this.bouton2, this.bouton3, this.bouton4, this.bouton5, this.bouton6, this.bouton7, this.bouton8, this.bouton9};
        return boutonerie;
    }



    //Permet de recuperer chaque bouton en fonction de son ID, et ainsi pouvoir le
    //modifier et lui donner des parametres directement.
    public Button getBouton(int id) {
        Button util = null;
        switch (id) {
            case 0:
                util = this.bouton1;
                break;
            case 1:
                util = this.bouton2;
                break;
            case 2:
                util = this.bouton3;
                break;
            case 3:
                util = this.bouton4;
                break;
            case 4:
                util = this.bouton5;
                break;
            case 5:
                util = this.bouton6;
                break;
            case 6:
                util = this.bouton7;
                break;
            case 7:
                util = this.bouton8;
                break;
            case 8:
                util = this.bouton9;
                break;
        }
        return util;
    }
}








