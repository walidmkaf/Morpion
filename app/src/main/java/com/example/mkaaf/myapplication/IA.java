package com.example.mkaaf.myapplication;

import java.util.Random;
import java.util.Vector;

/**
 * Created by Mkaaf on 22/02/16.
 */
public class IA {
	private ObjetsJeu tableau = null;
	private Vector    caseLibres = null, caseDiagoLibres = null ;
	private boolean FoirerMatchNul = false;

	protected int IA_Difficile(ObjetsJeu monTableau) {
		int    id   = 0, choix;
		Random rand = new Random();
		caseLibres = new Vector(); // Vector a la place de ce vieux int[] tout pourri
		tableau = monTableau;

		// Boucle qui permet de savoir quelle case est jouée ou pas
		for (int i = 0; i < 9; i++) {
			if (tableau.getValeurTableau(i) == 0) {
				caseLibres.add(i);
				//Faire un vector deja pour savoir quelle case est libre ou pas, apres créer l'IA qui choisira
				//ou placer ce qu'elle veut.
			}
		}


		// On creer une boucle pour recuperer les diagonales libres et les cases coté libres
		for (int i = 0; i <= caseLibres.size(); i++) {
			if (i == 0 || i == 2 || i == 6 || i == 8) {
				caseDiagoLibres.add(i);
			}
		}

		/*

		*		On Commence la recherche de la case que l'ordinateur va joué
		*
		*/


		// Si le joueur commence au milieu
		if (tableau.getValeurTableau(4) == 1) {
			id = ligneGagnante();
			if (id == 4) {
				id = ligneDangereuse();
			}
			if (id == 4 && caseDiagoLibres.size() != 0) {
				choix = rand.nextInt(caseDiagoLibres.size() + 1);
				id = (int) caseDiagoLibres.get(choix);
			}
			else {
				choix = rand.nextInt(caseLibres.size() + 1);
				id = (int) caseLibres.get(choix);
			}
		}


		// Si le joueur commence sur l'un des bords
		else if (tableau.getValeurTableau(1) == 1 || tableau.getValeurTableau(1) == 3 || tableau.getValeurTableau(1) == 5 || tableau.getValeurTableau(1) == 7) {


			// On joue notre premier coup au centre
			if (caseLibres.contains(4)) {
				id = 4;
			}


			else {
				// Ce if est la pour jouer uniquement au 3em coup, sinon on fera autre chose au 4em coup
				if (caseLibres.size() > 6) {
					// Ensuite si le joueur a joué a l'opposé du centre (et sa forme un xox)
					if (tableau.getDernierJouer() == 1 || tableau.getDernierJouer() == 3 || tableau.getDernierJouer() == 5 || tableau.getDernierJouer() == 7) {

						choix = rand.nextInt(caseDiagoLibres.size() + 1);
						id = (int) caseDiagoLibres.elementAt(choix);
					}


					// Si le gars joue pas a l'opposé du centre
					else {
						if (tableau.getDernierJouer() == 0 || tableau.getDernierJouer() == 2 || tableau.getDernierJouer() == 6 || tableau.getDernierJouer() == 8) {
							id = ligneDangereuse();

							if (id == 4) {
								choix = rand.nextInt(caseDiagoLibres.size() + 1);
								id = (int) caseDiagoLibres.get(choix);
							}

							if (id == 4) {
								choix = rand.nextInt(caseDiagoLibres.size() + 1);
								id = (int) caseDiagoLibres.get(choix);
								FoirerMatchNul = true;
							}
						}
					}
				}

				if (caseLibres.size() < 6) {
					if (FoirerMatchNul) {

						id = ligneGagnante();
						if (id == 4) {
							id = ligneDangereuse();
						}
						if (id == 4) {
							choix = rand.nextInt(caseLibres.size());
							id = (int) caseLibres.get(choix);
						}
					}

					else {
						// 3em coup, si le joueur joue pour bloquer la diagonale qui va etre faite, on joue sur une autre diagonale pour avoir
						// le choix entre 2 solution et gagné
						if (tableau.getDernierJouer() == 0 || tableau.getDernierJouer() == 2 || tableau.getDernierJouer() == 6 || tableau.getDernierJouer() == 8) {
							if (caseDiagoLibres.size() != 0) {
								choix = rand.nextInt(caseDiagoLibres.size() + 1);
								id = (int) caseDiagoLibres.elementAt(choix);
							}
						}


						// Sinon on a gagné vu qu'on a le centre + 1 diagonale
						else {
							id = ligneGagnante();
					/*
					if (tableau.getValeurTableau(0) == 2) id = 8;
					else if (tableau.getValeurTableau(2) == 2) id = 6;
					else if (tableau.getValeurTableau(6) == 2) id = 2;
					else if (tableau.getValeurTableau(8) == 2) id = 0;*/
						}
					}
				}

				// Ensuite on fait le dernier coup qui bat le joueur, suivant quelle diagonale on a joué
				// On teste si les diagonales sont pas prises
				/*if (testCase(0, 8)) id = 8;
				else if (testCase(2, 6)) id = 6;
				else if (testCase(6, 2)) id = 2;
				else if (testCase(8, 0)) id = 0;
					// Sinon on met dans la ligne qui est pas prise
				else if (testCase(0, 2, 1)) id = 1;
				else if (testCase(2, 8, 5)) id = 5;
				else if (testCase(8, 6, 7)) id = 7;
				else if (testCase(6, 0, 3)) id = 3;*/

				// (Ce code c'est le meme que la ligne "id = ligneGagnante();")
			}
		}

		// Si le joueur commence sur l'un des diagonale
		else {
			if (caseLibres.contains(4)) {
				id = 4;
			}
			// Le code est plus petit car quand on commence par une diagonale en tant que joueur 1, le joueur 2 ne peut pas
			// gagner, a part a cause d'une erreur, c'est pourquoi on test si on peut gagner (ligne gagnante), si sa renvoie
			// 4 alors ya pas de ligne gagnante, donc on se met a l'abri, et si ya pas de ligne dangereuse non plus, on joue
			// au hasard.
			else {
				id = ligneGagnante();
				if (id == 4) {
					id = ligneDangereuse();
				}
				if (id == 4 && caseDiagoLibres.size() != 0) {
					choix = rand.nextInt(caseDiagoLibres.size() + 1);
					id = (int) caseDiagoLibres.get(choix);
				}
				else {
					choix = rand.nextInt(caseLibres.size() + 1);
					id = (int) caseLibres.get(choix);
				}
			}
		}

		return id;
	}



	/*
	*
	* Fonction pour simplifier les "if" en renvoyant true si les cases sont bon a joué ou pas
	*
	*/


	private boolean testCase(int PremiereCase, int DeuxiemmeCase) {
		return tableau.getValeurTableau(PremiereCase) == 2 && caseLibres.contains(DeuxiemmeCase);
	}

	private boolean testCase(int PremiereCase, int DeuxiemmeCase, int troisiemeCase) {
		return tableau.getValeurTableau(PremiereCase) == 2 && tableau.getValeurTableau(DeuxiemmeCase) == 2 && caseLibres.contains(troisiemeCase);
	}




	/*
	*
	*   Les deux fonctions pour savoir si une ligne est deja coché 2 fois par le meme joueur, et donc
	*   pour la premiere fonction de tester si le joueur est deja a 2 case et le bloquer a la 3em
	*   ou si l'ordi a coché 2 cases et donc de gagner grace a la 3em.
	*
	*/


	// Fonction pour tester si une ligne est dangereuse pour l'ordi ou non (et donc jouer dedans)
	// Si le joueur a deja 2 croix en ligne, on choisira donc la 3em case pour l'en empecher.
	private int ligneDangereuse() {
		int id = 4;


		// Test sur la ligne d'en haut
		if (testLigneDangeureuse(1, 2, 0)) {
			id = 0;
		}
		else if (testLigneDangeureuse(0, 1, 2)) {
			id = 2;
		}
		else if (testLigneDangeureuse(0, 2, 1)) {
			id = 1;
		}
		// Test sur la ligne de droite
		else if (testLigneDangeureuse(2, 5, 8)) {
			id = 8;
		}
		else if (testLigneDangeureuse(2, 8, 5)) {
			id = 5;
		}
		else if (testLigneDangeureuse(5, 8, 2)) {
			id = 2;
		}
		// Test sur la ligne du bas
		else if (testLigneDangeureuse(6, 7, 8)) {
			id = 8;
		}
		else if (testLigneDangeureuse(6, 8, 7)) {
			id = 7;
		}
		else if (testLigneDangeureuse(7, 8, 6)) {
			id = 6;
		}
		// Test sur la ligne de gauche
		else if (testLigneDangeureuse(0, 3, 6)) {
			id = 6;
		}
		else if (testLigneDangeureuse(0, 6, 3)) {
			id = 3;
		}
		else if (testLigneDangeureuse(3, 6, 0)) {
			id = 0;
		}

		// Test sur la diagonale de gauche
		else if (testLigneDangeureuse(0, 8, 4)) {
			id = 4;
		}
		else if (testLigneDangeureuse(0, 4, 8)) {
			id = 8;
		}
		else if (testLigneDangeureuse(4, 8, 0)) {
			id = 0;
		}
		// Test sur la diagonale de droite
		else if (testLigneDangeureuse(2, 6, 4)) {
			id = 4;
		}
		else if (testLigneDangeureuse(4, 6, 2)) {
			id = 2;
		}
		else if (testLigneDangeureuse(2, 4, 6)) {
			id = 6;
		}


		return id;
	}


	// Fonction pour tester si une ligne est dangereuse pour le joueur
	// Si le l'ordi a deja 2 case prise, on va jouer dans la 3em pour gagner
	private int ligneGagnante() {
		int id = 4;


		// Test sur la ligne d'en haut
		if (testLigneGagnante(1, 2, 0)) {
			id = 0;
		}
		else if (testLigneGagnante(0, 1, 2)) {
			id = 2;
		}
		else if (testLigneGagnante(0, 2, 1)) {
			id = 1;
		}
		// Test sur la ligne de droite
		else if (testLigneGagnante(2, 5, 8)) {
			id = 8;
		}
		else if (testLigneGagnante(2, 8, 5)) {
			id = 5;
		}
		else if (testLigneGagnante(5, 8, 2)) {
			id = 2;
		}
		// Test sur la ligne du bas
		else if (testLigneGagnante(6, 7, 8)) {
			id = 8;
		}
		else if (testLigneGagnante(6, 8, 7)) {
			id = 7;
		}
		else if (testLigneGagnante(7, 8, 6)) {
			id = 6;
		}
		// Test sur la ligne de gauche
		else if (testLigneGagnante(0, 3, 6)) {
			id = 6;
		}
		else if (testLigneGagnante(0, 6, 3)) {
			id = 3;
		}
		else if (testLigneGagnante(3, 6, 0)) {
			id = 0;
		}

		// Test sur la diagonale de gauche
		else if (testLigneGagnante(0, 8, 4)) {
			id = 4;
		}
		else if (testLigneGagnante(0, 4, 8)) {
			id = 8;
		}
		else if (testLigneGagnante(4, 8, 0)) {
			id = 0;
		}
		// Test sur la diagonale de droite
		else if (testLigneGagnante(2, 6, 4)) {
			id = 4;
		}
		else if (testLigneGagnante(4, 6, 2)) {
			id = 2;
		}
		else if (testLigneGagnante(2, 4, 6)) {
			id = 6;
		}


		return id;
	}




	/*
	*
	*   Les deux fonctions suivantes permetent de raccourcir le code des deux fonction ligne dangereuse
	*   et ligne gagnante, et le rendre + comprehensible
	*
	 */


	// Fonction qui renvoie juste le test si deux case on été coché et la 3eme est vide, si c'est bon, sa renvoie true, sinon, false
	private boolean testLigneDangeureuse(int PremiereCase, int DeuxiemmeCase, int id) {
		return tableau.getValeurTableau(PremiereCase) == 1 && tableau.getValeurTableau(DeuxiemmeCase) == 1 && caseLibres.contains(id);
	}


	// Fonction qui renvoie le test si deux case on été coché par l'ordi pour pouvoir jouer dans la 3em
	private boolean testLigneGagnante(int PremiereCase, int DeuxiemmeCase, int id) {
		return tableau.getValeurTableau(PremiereCase) == 2 && tableau.getValeurTableau(DeuxiemmeCase) == 2 && caseLibres.contains(id);
	}


	// Fonction qui permet juste de remettre le booleen foirermatchnul a false a la fin d'une partie

	public void setFoirerMatchNul(boolean foirer) {
		this.FoirerMatchNul = foirer;
	}
}