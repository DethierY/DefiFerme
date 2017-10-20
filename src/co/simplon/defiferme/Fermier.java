package co.simplon.defiferme;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Fermier {
	
	private int nbreChats;
	private ArrayList<Chat> listeChats = new ArrayList<Chat> ();
	private static ArrayList<String> nomChats = new ArrayList<String> () {{
		add ("Nemo");
		add ("Garfield");
		add ("Fripouille");
		add ("Princesse");
		add ("Mimine");
	}};
	private static ArrayList<Integer> ageChats = new ArrayList<Integer> () {{
		add (5);
		add (1);
		add (15);
		add (2);
		add (7);
	}};
	
	public Fermier (int nbreChats) {
		this.nbreChats = nbreChats;
		this.listeChats = this.creationChats (); // liste des instances de la classe chat est en attribue dans l'éventualité qu'on joue avec plusieurs fermiers
	}
	
	protected void donnerOrdres (int nbreZones , ArrayList<Integer> sourisParZone, ArrayList<String> nomZones) {
		// le fermier donne sa suite d'ordres aux chats
		this.etablirOrdreDeChasse();   
		this.donnerMission ();
		this.attribuerZoneChasse (nbreZones);
		this.faireChasserChats (sourisParZone);
		this.demanderRapports (nomZones);
	}
	
	private ArrayList<Chat> creationChats () {  // creation d'un certain nombre de chats
		ArrayList<Chat> instancesChat = new ArrayList <Chat> ();
		for (int i = 0 ; i < nbreChats ; i++) {
			Chat nouveauChat = new Chat (nomChats.get(i) , ageChats.get(i));
			instancesChat.add(nouveauChat);
		}
		return instancesChat;
	}
	
	private void etablirOrdreDeChasse () {
		ArrayList<Chat> intermediaire = new ArrayList<Chat> ();
		for (Chat copie : listeChats) {  // on fait une copie de la liste des Chats
			intermediaire.add(copie);
		}
		ArrayList<Chat> ordreDeChasse = new ArrayList<Chat> ();  // liste intermédiaire qui va contenir les chats dans leur ordre d'action
		for (int i = 0 ; i < nbreChats; i++) {    
			int tirage = (int) (Math.random () * intermediaire.size()); // on tire aléatoirement dans la copie
			ordreDeChasse.add(intermediaire.get(tirage));  // on ajoute le chat de copie désigné dans la liste intermédiaire
			intermediaire.remove(tirage);   // on retire de la liste copie le chat qui a été tiré
			}
		for(int i = 0 ; i < ordreDeChasse.size() ; i++) {
			ordreDeChasse.get(i).setOrdreDAction(i);  // l'index de chaque chaque dans la liste intermédiare leur est injecté comme attribue Ordred'action
		}
	}
	
	private void donnerMission () {
		for (Chat nom : this.listeChats) {
			nom.setObjectifDeChasse( (int) (Math.random () * 15) +15);  // détermine le nbre de souris que le chats doit prendre
		}
	}
	
	private void attribuerZoneChasse (int nbreZones) {
		for (Chat chat : this.listeChats) {
			chat.setZoneDeChasse ( (int) (Math.random () * nbreZones) ); // détermine la zone de chasse de chaque chat aléatoirement et l'injecte en attribue
		}
	}
	
	private void faireChasserChats (ArrayList<Integer> sourisParZone) {
		for (Chat chat : this.listeChats) {
			int sourisDansZone = sourisParZone.get(chat.getZoneDeChasse());  // nbre souris dans zone chassée par chat injecté dans var intermédiaire
			sourisDansZone = sourisDansZone - chat.chasserSouris (sourisDansZone);  // recalcule du nbre souris dans zone après chasse
			sourisParZone.set(chat.getZoneDeChasse(), sourisDansZone);  // on réinjecte le nv nbre de souris de la zone dans le tableau recap principal
		}
	}
	private void demanderRapports (ArrayList<String> nomZones) {
		for (Chat chat : this.listeChats) {
			chat.faireSonRapport (nomZones);
		}
	}
	
	protected void afficherPalmares () {
		Chat [] intermediaire = new Chat [this.listeChats.size()];  // création d'un tableau intermédiaire de Chats 
		for (int i = 0 ; i < this.listeChats.size() ; i++) {
			intermediaire [i] = this.listeChats.get(i);  // on copie la liste de Chats dans le tableau intermédiaire
		}
		ArrayList<Chat> permutation = new ArrayList<Chat> ();  // on création une liste intermediaire de permutation
		List<Chat> ordrePalmares = new ArrayList<Chat>();  // on crée la liste qui va stocké les chats dans l'ordre de leur palmares
		// on stocke les deux Chats du tableau intermédiaire à comparer dans la liste intermédiaire
		// on compare leurs scores
		// si son score est inférieur, on permute leur place dans le tableau
		// on reinitialise la liste intermediaire
		// on ajoute les éléments les uns après les autres dans la liste de plamares leur ordre de score décroissant
		for (int i = 0; i < intermediaire.length - 1; i++) {
			for (int j = i + 1; j < intermediaire.length; j++) {
				permutation.add(intermediaire[i]);
				permutation.add(intermediaire[j]);
				if (intermediaire [i].getScoreTotal () < intermediaire [j].getScoreTotal () ) {
					intermediaire [i] = permutation.get(1);
					intermediaire [j] = permutation.get(0);
				}
				permutation.remove(0);
				permutation.remove(0);
				
			}
			ordrePalmares.add(intermediaire[i]);
		}
		ordrePalmares.add(intermediaire[intermediaire.length - 1]);  // on ajoute le dernier élément restant qu'on a pas besoin de tester : c'est le plus petit score
		for (Chat chat : ordrePalmares) {  // affichage des scores dans l'ordre du palmares
			System.out.println(chat.getNomChat () + " a tué " + chat.getScoreTotal() + " souris.");
		}
	}
	
	public void setNbreChats (int nbreChats) {
		this.nbreChats = nbreChats;
	}
	
	public int getNbreChats () {
		return this.nbreChats;
	}
	
	public void setListeChats (ArrayList<Chat> listeChats) {
		this.listeChats = listeChats;
	}
	
	public ArrayList<Chat> getListeChats () {
		return this.listeChats;
	}

}
