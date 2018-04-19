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
    private static Fenetre5 f4;
    
    
    public Controleur() {

    //f.local.addActionListener(this);
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
        //f3 = new Fenetre3();
        f4 = new Fenetre5();
        Controleur c =new Controleur();
      
       
   
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== f.recherche)
        {
            f2.setVisible(true);
            Connexion co= f.maconnexion;
            f2.passwdECETexte=f.passwdECETexte;
            f2.passwdBDDTexte=f.passwdBDDTexte;
            f2.loginBDDTexte=f.loginBDDTexte;
            f2.maconnexion=co;
            int loc =f.loc;
            f2.init();
        }
        if(e.getSource()== f.camembert)
        {        
            f3.setVisible(true);
            Connexion co= f.maconnexion;
            f3.passwdECETexte=f.passwdECETexte;
            f3.passwdBDDTexte=f.passwdBDDTexte;
            f3.loginBDDTexte=f.loginBDDTexte;
            f3.maconnexion=co;
            //f3.init();
        }
        if(e.getSource()== f.maj)
        {        
            f4.setVisible(true);
            Connexion co= f.maconnexion;
            //f4.passwdECETexte=f.passwdECETexte;
            //f4.passwdBDDTexte=f.passwdBDDTexte;
            //f4.loginBDDTexte=f.loginBDDTexte;
            f4.maconnexion=co;
            f4.init();
        }
        
    }
}
