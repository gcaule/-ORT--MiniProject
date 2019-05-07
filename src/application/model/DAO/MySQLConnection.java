package application.model.DAO;

import java.sql.*;

public class MySQLConnection {

	/** Paramètres de connexion: URL de connection et login, pass pour la BDD */
	private static String url = "jdbc:mysql://localhost/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String user = "root";
	private static String passwd = "";

	/** * Objet Connection */
	private static Connection connect;  

	/** * Méthode qui va nous retourner notre instance * et la créer si elle n'existe pas... * @return */
	public static Connection getInstance(){
		
		if(connect == null){
			try {
				connect = DriverManager.getConnection(url, user, passwd);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connect;
	}

}