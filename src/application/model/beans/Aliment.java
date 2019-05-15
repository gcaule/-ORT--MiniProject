package application.model.beans;

import java.util.Date;

public class Aliment {
	
	public Aliment(int id, String nom, int quantite, Date dateAchat, Date datePeremption) {
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
	private Date dateAchat;
	private Date datePeremption;
	
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

	public Date getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}

	public Date getDatePeremption() {
		return datePeremption;
	}

	public void setDatePeremption(Date dateperemption) {
		this.datePeremption = dateperemption;
	}

}
