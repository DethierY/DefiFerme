package co.simplon.defiferme;

import java.util.ArrayList;

public class Ferme {
	
	int nbreSouris;
	int nbreZones;
	int nbreChatsDeFerme;
	static ArrayList<String> identificationZone = new ArrayList<String> () {{ // le nom des zones
		add ("zone A");
		add ("zone B");
		add ("zone C");
		add ("zone D");
	}};
	static ArrayList<Integer> nbreSourisParZone = new ArrayList<Integer> ();
	static int nbreTours = 0;
	
	public Ferme(int nbreZones, int nbreChatDeFerme , int nbreSouris) {
		this.nbreZones = nbreZones;
		this.nbreChatsDeFerme = nbreChatDeFerme;
		this.nbreSouris = nbreSouris;
	}
	
	public void lancerJeu () {
		Fermier fermier = this.creationFermier(this.nbreChatsDeFerme);  // création du fermier
		nbreSourisParZone = this.repartirSourisDebut(this.nbreSouris);  // répartitioin initiale des souris
		while (this.nbreSouris > 0) {   // boucle qui fait faire un tour de jeu tant qu'il y a des souris vivantes
			this.faireUnTourDeJeu(fermier);
			nbreTours ++;
		}
		System.out.println("La chasse a duré " + nbreTours + " jours.");
		fermier.afficherPalmares ();  // affichage du palmares classé ordre décroissant
	}
	
	private Fermier creationFermier (int chats) {
		Fermier proprio = new Fermier (chats);
		return proprio;
	}
	
	private ArrayList<Integer> repartirSourisDebut (int nombre) {
		ArrayList<Integer> intermediaire = new ArrayList<Integer> ();
		ArrayList<Integer> repartition = new ArrayList<Integer> ();
		int sourisARepartir = nombre;
		for (int i = 0 ; i < nbreZones - 1 ; i++) {  // on affecte les souris zone après zone
			intermediaire.add((int) (Math.random () * sourisARepartir));  // on tir ealéatoirement dans le nombre de souris qui restent à répartir
			sourisARepartir = sourisARepartir - intermediaire.get(i); // a réactualise le nombre de souris à répartir
		}
			intermediaire.add(sourisARepartir); // la dernière zone a les souris qui restent
		for (int i = 0 ; i < nbreZones; i++) {    // on fait un remélange des tirages pour éviter les grandes valeurs systématiques dans les premieres zones
			int tirage = (int) (Math.random () * intermediaire.size() );
			repartition.add(intermediaire.get(tirage));
			intermediaire.remove(tirage);			
		}
		return repartition;
	}
	
	private void faireUnTourDeJeu (Fermier fermier) {  // méthode qui fait un tour de jeu
		this.changerSourisDeZone ();
		this.afficherNbreSourisParZone ();
		fermier.donnerOrdres (this.nbreZones , nbreSourisParZone , identificationZone); // le fermier donne ses ordres aux chats
		nbreSouris = this.recalculerNbreSouris ();  // le nbre de souris est affecté par la chasse des chats
	}
	
	private void changerSourisDeZone () {
		Integer sourisRestant;
		Integer sourisArrivant;
		ArrayList<Integer> nouvelleRepartition = new ArrayList<Integer> ();
		for (int i = 0; i < nbreZones ; i++) {
			if (i == 0) {  // gère l'exception du passage de fin de tableau à 0
				sourisArrivant = (nbreSourisParZone.get(nbreSourisParZone.size() - 1) + 1) /2; // le nombre de souris bougeant est arrondis au sup
				sourisRestant = nbreSourisParZone.get(0) /2; // le nombre de souris restant est arrondi à l'inf
			}
			else {
				sourisArrivant = (nbreSourisParZone.get(i-1) + 1) /2;
				sourisRestant = nbreSourisParZone.get(i)  /2;
			}
			nouvelleRepartition.add(sourisRestant + sourisArrivant);  // construction de l'arraylist avec la nouvelle répartition des souris par zone
		}
		for (int i = 0 ; i < nouvelleRepartition.size(); i++) {
			nbreSourisParZone.set(i, nouvelleRepartition.get(i));  // copie de l'arraylist nouvelleRepartitioin dans nbreSourisParZone
		}
	}
	
	private void afficherNbreSourisParZone () {
		int increment = 0;
		for(int nbre : nbreSourisParZone) {
			System.out.println(identificationZone.get(increment) + " : " + nbre + " souris.");
			increment ++;
		}
	}
	
	private int recalculerNbreSouris () {  // pour recalculer le nombre de souris après la chasse, on additionne les souris de chaque zone après la chasse
		int total = 0;
		for (int nbre : nbreSourisParZone) {
			total = total + nbre;
		}
		return total; 
	}
	
	public void setNbreSouris(int nbreSouris) {
		this.nbreSouris = nbreSouris;
	}
	
	public int getNbreSouris() {
		return this.nbreSouris;
	}

	public void setNbreChatsDuJeu (int nbreChatsDuJeu) {
		this.nbreChatsDeFerme = nbreChatsDuJeu;
	}
	public int getNbreChatsDuJeu () {
		return this.nbreChatsDeFerme;
	}
	
	public void setNbreZones(int nbreZones) {
		this.nbreZones = nbreZones;
	}
	
	public int getNbreZones() {
		return this.nbreZones;
	}

	
}
