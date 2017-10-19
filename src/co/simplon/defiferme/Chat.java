package co.simplon.defiferme;

public class Chat {
	
	String nomChat;
	int ageChat;
	
	public Chat (String nomChat , int ageChat) {
		this.nomChat = nomChat;
		this.ageChat = ageChat;
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
}
