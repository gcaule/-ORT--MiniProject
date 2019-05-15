package application.model.beans;

public class Aliment {
	
	public Aliment(int id, String nom, int quantite, String dateAchat, String datePeremption) {
		super();
		this.id = id;
		this.nom = nom;
		this.quantite = quantite;
		this.dateAchat = dateAchat;
		this.datePeremption = datePeremption;
	}

	private int id;
	private String nom;
	private int quantite;
	private String dateAchat;
	private String datePeremption;
	
	public Aliment() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public String getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(String dateAchat) {
		this.dateAchat = dateAchat;
	}

	public String getDatePeremption() {
		return datePeremption;
	}

	public void setDatePeremption(String dateperemption) {
		this.datePeremption = dateperemption;
	}

}
