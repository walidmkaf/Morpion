package com.example.mkaaf.myapplication;

public class ObjetsJeu {

    /*
    *
    *       Declaration des variables de la classe
    *
     */


    private int[][] tableau
            = new int[3][3];

    private boolean victoire
			= false,
			matchNul
			= false,
			tableauPlein
			= false;

    private int joueur
            = 1,
            dernierJouer
			= 0;
    /*
    *
    *       Constructeur de la classe
    *
     */

    public ObjetsJeu()
    {
        initialisation(this.tableau);
    }

    private int[][] initialisation(int[][] tableau) {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                tableau[i][j] = 0 ;
            }
        }
        return tableau;
    }


    /*
    *
    *       Getter Des Objets
    *
    */

	public int getDernierJouer() { return this.dernierJouer; }


    public int getJoueur()
    {
        return this.joueur;
    }


	public boolean getMatchNul(){ return this.matchNul;}


	public boolean getTableauPlein(){

		// On va mettre le booleen vrai puis si une seule fois dans le tableau
		// une valeure est egale a 0, sa veut dire qu'une case est encore libre
		// et donc que le match n'est pas encore nul (ameliorer l'algorithme pour
		// predire le match nul.

		this.tableauPlein = true;

		for (int i = 0; i<9; i++)
		{
			if (getValeurTableau(i) == 0)
			{
				this.tableauPlein = false;
			}
		}

		return this.tableauPlein;
	}


    public int getValeurTableau(int id) {
        int i = id / 3;
        int j = id % 3;
        return this.tableau[i][j];
    }
    //      Fonction qui retourne la valeure du tableau suivant quelle variable on lui donne

    public int getValeurTableau(int i, int j)
    {
        return this.tableau[i][j];
    }


    public boolean getVictoire ()
    {
        return this.victoire;
    }


    /*
    *
    *       Setter Des Objets
    *
    */


    public void setJoueur(int joueur) {
        this.joueur = joueur;
    }


	public void setMatchNul(boolean matchNul){this.matchNul = matchNul; this.tableauPlein = false;}


    public void setValeurTableau(int id, int valeur) {
        int i = id / 3;
        int j = id % 3;
        this.tableau[i][j] = valeur;
		this.dernierJouer = id;
    }


    public void setVictoire(boolean victoire)
    {
        this.victoire = victoire;
    }
}