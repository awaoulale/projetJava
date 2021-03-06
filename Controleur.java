/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcv2018;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * Contrôle l'interrogation de la BDD dans la Fenetre
 *
 * @author segado
 */
public class Controleur implements ActionListener{

    private static Fenetre f;
    private static Fenetre2 f2;
    private static Fenetre3 f3;
    private static Fenetre4 f4;
    
    
    /**
     * Constructeur qui initialise toutes les fenetres
     */
    public Controleur() {

    f.camembert.addActionListener(this);
    f.recherche.addActionListener(this);
    f.maj.addActionListener(this);

}
    
    /**
     *
     * une methode principal (main) pour lancer l'application
     *
     * @param s
     */
    public static void main(String[] s) {
        // creation de la fenetre
        //Fenetremagali f5= new Fenetremagali();
       
        f = new Fenetre();
        f3= new Fenetre3();
        f2 = new Fenetre2();
        f4 = new Fenetre4();
        Controleur c =new Controleur();
   
    }
    
    /**
     *
     * Pour gerer les actions sur les boutons on utilise la fonction
     * actionPerformed
     *
     * @param e : appui sur les boutons
     */
     @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== f.recherche)
        {
            f2.setVisible(true);
            Connexion co= f.maconnexion;
            f2.maconnexion=co;
            //f2.init();
        }
        if(e.getSource()== f.camembert)
        {        
            f3.setVisible(true);
            Connexion co= f.maconnexion;

            f3.loginBDDTexte=f.loginBDDTexte;
            f3.maconnexion=co;

        }
        if(e.getSource()== f.maj)
        {        
            f4.setVisible(true);
            Connexion co= f.maconnexion;
            f4.maconnexion=co;
            f4.init();

            
        }
        
    }
}