package co.simplon.defiferme;

import java.util.ArrayList;

public class Fermier {
	
	private int nbreChats;
	private static ArrayList<Chat> ordreDeChasse;
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
	private static ArrayList<Chat> listeChats = new ArrayList<Chat> ();
	
	public Fermier (int nbreChats) {
		this.nbreChats = nbreChats;
	}
	
	protected void donnerOrdres () {
		listeChats = this.creationChats (nbreChats);
		ordreDeChasse = this.etablirOrdreDeChasse(listeChats);
				
	}
	
	private ArrayList<Chat> creationChats (int nbreChats) {  // creation d'un certain nombre de chats
		ArrayList<Chat> instancesChat = new ArrayList <Chat> ();
		for (int i = 0 ; i < nbreChats ; i++) {
			Chat nouveauChat = new Chat (nomChats.get(i) , ageChats.get(i));
			instancesChat.add(nouveauChat);
		}
		return instancesChat;
	}
	
	protected ArrayList<Chat> etablirOrdreDeChasse (ArrayList<Chat> chats) {
		ArrayList<Chat> intermediaire = new ArrayList<Chat> ();
		for (Chat copie : chats) {
			intermediaire.add(copie);
		}
		ArrayList<Chat> nvlOrdre = new ArrayList<Chat> ();
		for (int i = 0 ; i < nbreChats; i++) {    
			int tirage = (int) (Math.random () * intermediaire.size());
			nvlOrdre.add(intermediaire.get(tirage));
			intermediaire.remove(tirage);
			}
		return nvlOrdre;
	}
	
	public void setNbreChats (int nbreChats) {
		this.nbreChats = nbreChats;
	}
	public int getNbreChats () {
		return this.nbreChats;
	}
	

}
