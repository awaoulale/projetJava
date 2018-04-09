/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcv2018;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbcv2018.Connexion;



/**
 *
 * @author elev
 */
public class Test {

private static Connexion connexion1;
private static ArrayList<String> liste;
        
        
public static void main(String[] args) {
      System.out.println("coucou");
      
    try {          
        connexion1 = new Connexion("hopital","root","");
    } catch (SQLException ex) {
        System.out.println("erreur");  
        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        System.out.println("erreur");  
        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
    }
           
   
   
    String requete="SELECT * FROM `chambre` WHERE 1";
   connexion1.ajouterRequete(requete);
     
    try {
        liste= connexion1.remplirChampsRequete(requete);
        System.out.println(liste);
    } catch (SQLException ex) {
        System.out.println("Problemo");  
        //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
    }
   
      System.out.println("au revoir"); 
}
    }
    

