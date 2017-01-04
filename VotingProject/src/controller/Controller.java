package controller;

import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import database.DBHelper;

//@generic, emre
public class Controller {
	/*Generic Def*/
	private static Controller instance = null;
	
	/*Arzum Def*/
	
	/*Burcu Def*/
	public DBHelper db = null;
	
	/*Emre Def*/
	
	/*Guven Def*/
	
	/*Leyla Def*/
	
	/*Pelin Def*/
	
	//@generic, emre
	private Controller(){
		/*Generic Def*/
		
		/*Arzum Def*/
		
		/*Burcu Def*/
		db = new DBHelper();
		
		/*Emre Def*/
		
		/*Guven Def*/
		
		/*Leyla Def*/
		
		/*Pelin Def*/
		
	}
	
	//@generic, emre
	public static Controller getInstance(){
		if(instance == null){
			instance = new Controller();
		}
		return instance;
	}
	
	
}
