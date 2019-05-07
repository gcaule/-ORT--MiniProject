package application.model.beans;

import java.time.LocalDate;

public class Aliment {
	
	private int id;
	private String nom;
	private int quantite;
	private LocalDate dateAchat;
	private LocalDate datePeremption;
	
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

	public LocalDate getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(LocalDate dateAchat) {
		this.dateAchat = dateAchat;
	}

	public LocalDate getDatePeremption() {
		return datePeremption;
	}

	public void setDatePeremption(LocalDate dateperemption) {
		this.datePeremption = dateperemption;
	}

}
