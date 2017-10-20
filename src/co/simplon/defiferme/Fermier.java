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
		this.listeChats = this.creationChats ();
	}
	
	protected void donnerOrdres (int nbreZones , ArrayList<Integer> sourisParZone, ArrayList<String> nomZones) {
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
		for (Chat copie : listeChats) {
			intermediaire.add(copie);
		}
		ArrayList<Chat> ordreDeChasse = new ArrayList<Chat> ();
		for (int i = 0 ; i < nbreChats; i++) {    
			int tirage = (int) (Math.random () * intermediaire.size());
			ordreDeChasse.add(intermediaire.get(tirage));
			intermediaire.remove(tirage);
			}
		for(int i = 0 ; i < ordreDeChasse.size() ; i++) {
			ordreDeChasse.get(i).setOrdreDAction(i);
		}
	}
	
	private void donnerMission () {
		for (Chat nom : this.listeChats) {
			nom.setObjectifDeChasse( (int) (Math.random () * 15) +15);
		}
	}
	
	private void attribuerZoneChasse (int nbreZones) {
		for (Chat chat : this.listeChats) {
			chat.setZoneDeChasse ( (int) (Math.random () * nbreZones) );
		}
	}
	
	private void faireChasserChats (ArrayList<Integer> sourisParZone) {
		for (Chat chat : this.listeChats) {
			int sourisDansZone = sourisParZone.get(chat.getZoneDeChasse());
			sourisDansZone = sourisDansZone - chat.chasserSouris (sourisDansZone);
			sourisParZone.set(chat.getZoneDeChasse(), sourisDansZone);
		}
	}
	private void demanderRapports (ArrayList<String> nomZones) {
		for (Chat chat : this.listeChats) {
			chat.faireSonRapport (nomZones);
		}
	}
	
	protected void afficherPalmares () {
		Chat [] intermediaire = new Chat [this.listeChats.size()];
		for (int i = 0 ; i < this.listeChats.size() ; i++) {
			intermediaire [i] = this.listeChats.get(i); 
		}
		ArrayList<Chat> permutation = new ArrayList<Chat> ();
		
		List<Chat> ordrePalmares = new ArrayList<Chat>();
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
		ordrePalmares.add(intermediaire[intermediaire.length - 1]);
		for (Chat chat : ordrePalmares) {
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
