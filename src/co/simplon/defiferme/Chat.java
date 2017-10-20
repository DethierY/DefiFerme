package co.simplon.defiferme;

import java.util.ArrayList;

public class Chat {
	
	String nomChat;
	int ageChat;
	int ordreDAction;
	int objectifDeChasse;
	int handicap;
	int zoneDeChasse;
	int scoreDeLaNuit;
	int scoreTotal;
	
	
	
	
	public Chat (String nomChat , int ageChat) {
		this.nomChat = nomChat;
		this.ageChat = ageChat;
		this.ordreDAction = 0;
		this.objectifDeChasse = 0;
		this.handicap = this.calculHandicap();
		this.zoneDeChasse = 0;
		this.scoreDeLaNuit = 0;
	}

	private int calculHandicap () {
		if (this.ageChat < 3) {
			this.handicap = 4;
		}
		else if (this.ageChat > 10) {
			this.handicap = 3;
		}
		else {
			this.handicap = 2;
		}
		return this.handicap;
	}
	
	protected int chasserSouris (int sourisDansZone) {
		this.scoreDeLaNuit = 0; // réinitialisation de l'attribut
		if (sourisDansZone > this.objectifDeChasse) {  // calcul du score du chat cette nuit
			this.scoreDeLaNuit = this.objectifDeChasse / this.handicap;
		}
		else if  (sourisDansZone > this.handicap) {
			this.scoreDeLaNuit = sourisDansZone / this.handicap;	
		}
		else if (sourisDansZone > 0) {
			this.scoreDeLaNuit = 1;
		}
		else {
			this.scoreDeLaNuit = 0;
		}
		this.scoreTotal = this.scoreTotal + this.scoreDeLaNuit; //incrémente le score du chat
		return this.scoreDeLaNuit;   // retourne le nouveau tableau d'effectifs de souris par zone
	}
	
	protected void faireSonRapport (ArrayList<String> nomZones) {
		System.out.println("Cette nuit, " + this.nomChat + " a chassé " + this.scoreDeLaNuit + " souris dans la "  + nomZones.get(this.zoneDeChasse) + ".");
	}
	public void setNomChat (String nomChat) {
		this.nomChat = nomChat;
	}
	
	public String getNomChat () {
		return this.nomChat;
	}
	
	public void setAgeChat (int ageChat) {
		this.ageChat = ageChat;
	}
	
	public int getAgeChat () {
		return this.ageChat;
	}
	
	public void setOrdreDAction (int ordreDAction) {
		this.ordreDAction = ordreDAction;
	}
	
	public int getOrdreDAction () {
		return this.ordreDAction;
	}
	
	public void setObjectifDeChasse (int objectifDeChasse) {
		this.objectifDeChasse = objectifDeChasse;
	}
	
	public int getObjectifDeChasse () {
		return this.objectifDeChasse;
	}
	
	public void setHandicap (int handicap) {
		this.handicap = handicap;
	}
	
	public int getHandicap () {
		return this.handicap;
	}
	
	public void setZoneDeChasse (int zoneDeChasse) {
		this.zoneDeChasse = zoneDeChasse;
	}
	
	public int getZoneDeChasse () {
		return this.zoneDeChasse;
	}
	
	public void setScoreDeLaNuit (int scoreDeLaNuit) {
		this.scoreDeLaNuit = scoreDeLaNuit;
	}
	
	public int getScoreDeLaNuit () {
		return this.scoreDeLaNuit;
	}
	
	public void setScoreTotal (int scoreTotal) {
		this.scoreTotal = scoreTotal;
	}
	
	public int getScoreTotal () {
		return this.scoreTotal;
	}
}
