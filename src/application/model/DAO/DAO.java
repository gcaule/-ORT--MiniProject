package application.model.DAO;

import java.sql.*;

import application.model.beans.Aliment;
import javafx.collections.ObservableList;

public abstract class DAO<T> {

	//Je récupère une instance de connextion grâce à la méthode statique getInstance()
	//de MySQLConnection.java
	public Connection connect = MySQLConnection.getInstance();

	/** * Permet de créer une base de données */
	public abstract T createDB();

	/** * Permet de récupérer une liste de tuples de la base de données */
	public abstract ObservableList<Aliment> fetchEntries();

	/** * Permet de récupérer un objet via son ID * @param id * @return */
	public abstract T find(int id);

	/** * Permet de créer une entrée dans la base de données * par rapport à un objet*/
	public abstract T create(T obj);

	/** * Permet de mettre à jour les données d'une entrée dans la base */
	public abstract T update(T obj);

	/** * Permet la suppression d'une entrée de la base * @param obj */
	public abstract void delete(T obj);

}