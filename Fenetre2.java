/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcv2018;

/*
 * 
 * Librairies importées
 */
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Affiche dans la fenetre graphique les champs de tables et les requetes de la
 * BDD
 *
 * @author segado
 */
public class Fenetre2 extends JFrame implements ActionListener, ItemListener {
    /*
     * Attribut privés : objets de Connexion, AWT et Swing
     * 
     */

    public Connexion maconnexion;
    private final java.awt.List listeDeTables, listeDeRequetes;
    private final JTextArea fenetreLignes, fenetreRes;
    private final JPanel p0, p1, nord, p2, p3, p4, p5, p6, p7, p8, p9,pA;
    
    //pour rechercher par chambre //OK
    private final JTextField chambre; 
    private final JButton rechercheChambre;
    private final JRadioButton malades;
    private final JRadioButton batiments;
    private final JRadioButton litChambre;
    private final JRadioButton defautChambre;
    
    //pour rechercher par docteur //OK
    private final JTextField docteur; 
    private final JButton rechercheDocteur;
    private final JRadioButton soigneDocteur;
    private final JRadioButton speDocteur;
    private final JRadioButton defautDocteur;
    private final JRadioButton directeurDocteur; 

    //Pour rechercher par employe // OK
    private final JTextField employe;
    private final JButton rechercheEmploye;
    private final JRadioButton defautEmploye;
   
    
    //Pour rechercher par infirmier //OK
    private final JTextField infirmier;
    private final JButton rechercheInfirmier;
    private final JRadioButton defautInfirmier;
    //private final JRadioButton salaireInfirmier;
    private final JRadioButton rotationInfirmier;
    private final JRadioButton serviceInfirmier;
    private final JRadioButton maladeInfirmier; //A FAIRE ?
    
    //Pour rechercher par malade //OK
    private final JTextField malade;
    private final JButton rechercheMalade;
    private final JRadioButton defautMalade;
    private final JRadioButton mutuelleMalade;
    private final JRadioButton chambreMalade;
    private final JRadioButton docteurMalade; 
    //private final JRadioButton infirmierMalade; //A FAIRE ?
    //private final JRadioButton surveillantMalade; //A FAIRE 
    
    //Pour rechercher par service
    private final JTextField service;
    private final JButton rechercheService;
    private final JRadioButton defautService;
    private final JRadioButton nomService;
    private final JRadioButton batimentService;
    private final JRadioButton directeurService; //A FAIRE
    
    
    /**
     * Constructeur qui initialise tous les objets graphiques de la fenetre
     */
    public Fenetre2() {

        // creation par heritage de la fenetre
        super("Recherche");

        // mise en page (layout) de la fenetre visible
        setLayout(new BorderLayout());
        setBounds(0, 0, 400, 400);
        setResizable(true);
        setVisible(false);

        // creation des boutons


        // creation des listes pour les tables et les requetes
        listeDeTables = new java.awt.List(10, false);
        listeDeRequetes = new java.awt.List(10, false);

        // creation des textes
        fenetreLignes = new JTextArea();
        fenetreRes = new JTextArea();
        //requeteTexte = new JTextField();
        
        //Pour rechercher par chambre
        chambre=new JTextField();
        rechercheChambre = new JButton("Recherche le num de la chambre");
        malades = new JRadioButton("par malades");
        batiments = new JRadioButton("par batiments");
        litChambre = new JRadioButton("par nombre de lit");
        defautChambre=new JRadioButton("par défaut");
        defautChambre.setSelected(true);
        ButtonGroup bgChambre = new ButtonGroup(); //pour selectioner 1 seul RadioButton à la fois
        bgChambre.add(malades);
        bgChambre.add(batiments);
        bgChambre.add(litChambre);
        bgChambre.add(defautChambre);


        //Pour rechercher par docteur
        rechercheDocteur = new JButton("Recherche le nom du docteur");
        docteur=new JTextField(); 
        soigneDocteur=new JRadioButton("par soigné");
        speDocteur=new JRadioButton("par spécialité");
        directeurDocteur=new JRadioButton("par directeur");
        defautDocteur=new JRadioButton("par défaut");
        defautDocteur.setSelected(true);
        ButtonGroup bgDocteur = new ButtonGroup();
        bgDocteur.add(defautDocteur);
        bgDocteur.add(soigneDocteur);
        bgDocteur.add(speDocteur);
        bgDocteur.add(directeurDocteur);
        
        //Pour rechercher par employe
        employe=new JTextField();
        employe.setMinimumSize(new Dimension(450,20));
        employe.setPreferredSize(new Dimension(450,20));
        employe.setMaximumSize(new Dimension(450,20));
        //p5.setLayout(new BorderLayout());
        
        rechercheEmploye=new JButton("Recherche le nom de l'employe");
        defautEmploye=new JRadioButton("par défaut");
        defautEmploye.setSelected(true);
        ButtonGroup bgEmploye = new ButtonGroup();
        bgEmploye.add(defautEmploye);
        
        //Pour rechercher par infirmier
        infirmier=new JTextField();
        rechercheInfirmier=new JButton("Recherche le nom de l'infirmier");
        defautInfirmier=new JRadioButton("par défaut");
        defautInfirmier.setSelected(true);
        rotationInfirmier=new JRadioButton("par rotation");
        serviceInfirmier=new JRadioButton("par service");
        maladeInfirmier=new JRadioButton("par malade");
        ButtonGroup bgInfirmier = new ButtonGroup(); 
        bgInfirmier.add(defautInfirmier);
        //bgInfirmier.add(salaireInfirmier);
        bgInfirmier.add(rotationInfirmier);
        bgInfirmier.add(serviceInfirmier);
        bgInfirmier.add(maladeInfirmier);
    
        //Pour rechercher par malade
        malade=new JTextField();
        rechercheMalade=new JButton("Recherche le nom du malade");
        defautMalade=new JRadioButton("par défaut");
        defautMalade.setSelected(true);
        mutuelleMalade=new JRadioButton("par mutuelle");
        chambreMalade=new JRadioButton("par chambre");
        docteurMalade=new JRadioButton("par docteur");
        ButtonGroup bgMalade = new ButtonGroup(); 
        bgMalade.add(defautMalade);
        bgMalade.add(mutuelleMalade);
        bgMalade.add(chambreMalade);
        bgMalade.add(docteurMalade);
    
        //Pour rechercher par service
        service=new JTextField();
        rechercheService=new JButton("Recherche le nom du service");
        defautService=new JRadioButton("par défaut");
        defautService.setSelected(true);
        nomService=new JRadioButton("par nom");
        batimentService=new JRadioButton("par batiment");
        directeurService=new JRadioButton("par directeur");
        ButtonGroup bgService = new ButtonGroup(); 
        bgService.add(defautService);
        bgService.add(nomService);
        bgService.add(batimentService);
        bgService.add(directeurService);


        // creation des labels

        // creation des panneaux
        p0 = new JPanel();
        p1 = new JPanel();
        nord = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        p6 = new JPanel();
        p7 = new JPanel();
        p8 = new JPanel();
        p9 = new JPanel();
        pA = new JPanel();

        // mise en page des panneaux
        p1.setLayout(new GridLayout(1, 6));//Pour la chambre
        nord.setLayout(new GridLayout(1, 1));
        p2.setLayout(new GridLayout(1, 1));//Pour les résultats
        
        p3.setLayout(new GridLayout(1,3)); //regroupe p1,p4,p5
        p4.setLayout(new GridLayout(1,6));//Pour le docteur
        p5.setLayout(new GridLayout(1,3));//Pour l'employe
        
        p6.setLayout(new GridLayout(1,6));//Pour l'infirmier
        p7.setLayout(new GridLayout(1,6));//Pour le malade
        p8.setLayout(new GridLayout(1,6));//Pour le service
        p9.setLayout(new GridLayout(1,3));//regroupe p6,p7,p8


        // ajout des objets graphqiues dans les panneaux

        //Essai pour position le bouton et les RadioButton pour la recherche par chambre
        p1.setLayout(new BoxLayout(p1,BoxLayout.PAGE_AXIS));
        chambre.setPreferredSize(new Dimension(20,20));
        p1.add(chambre); 
        p1.add(rechercheChambre);
        p1.add(malades);
        p1.add(batiments);
        p1.add(litChambre);
        p1.add(defautChambre);

                
        //Pour recherche par docteur
        p4.setLayout(new BoxLayout(p4,BoxLayout.PAGE_AXIS));
        p4.add(docteur);
        p4.add(rechercheDocteur);
        p4.add(soigneDocteur);
        p4.add(speDocteur);
        p4.add(directeurDocteur);
        p4.add(defautDocteur);
        
        //Pour rechercher par employe
         p5.setLayout(new BoxLayout(p5,BoxLayout.PAGE_AXIS));
         p5.add(employe);
         p5.add(rechercheEmploye);
         p5.add(defautEmploye);
         
        p5.add(p0,BorderLayout.SOUTH);
        /*p0.setMinimumSize(new Dimension(300,100));
        p0.setPreferredSize(new Dimension(300,100));
        p0.setMaximumSize(new Dimension(300,100));*/
        
        p3.setLayout(new BoxLayout(p3,BoxLayout.LINE_AXIS));
        p3.add(p1);
        p3.add(p4);
        p3.add(p5);
              
        p2.add(fenetreRes);
        
         //Pour recherche par infirmier
        p6.setLayout(new BoxLayout(p6,BoxLayout.PAGE_AXIS));
        p6.add(infirmier);
        p6.add(rechercheInfirmier);
        p6.add(rotationInfirmier);
        p6.add(serviceInfirmier);
        p6.add(maladeInfirmier);
        p6.add(defautInfirmier);

        
        //Pour recherche par malalde
        p7.setLayout(new BoxLayout(p7,BoxLayout.PAGE_AXIS));
        p7.add(malade);
        p7.add(rechercheMalade);
        p7.add(mutuelleMalade);
        p7.add(chambreMalade);
        p7.add(docteurMalade);
        p7.add(defautMalade);
        
        //Pour recherche par service
        p8.setLayout(new BoxLayout(p8,BoxLayout.PAGE_AXIS));
        p8.add(service);
        p8.add(rechercheService);
        p8.add(nomService);
        p8.add(batimentService);
        p8.add(directeurService);
        p8.add(defautService);
        
        p9.setLayout(new BoxLayout(p9,BoxLayout.LINE_AXIS));
        p9.add(p6);
        p9.add(p7);
        p9.add(p8);
        
        pA.setLayout(new BoxLayout(pA,BoxLayout.PAGE_AXIS));
        pA.add(p3);
        pA.add(p9);
 
        // ajout des listeners

        listeDeTables.addItemListener(this);
        listeDeRequetes.addItemListener(this);
        
        //Pour rechercher par chambre
        chambre.addActionListener(this);
        rechercheChambre.addActionListener(this);
        malades.addActionListener(this);
        batiments.addActionListener(this);
        litChambre.addActionListener(this);
        defautChambre.addActionListener(this);

        //Pour rechercher par docteur 
        docteur.addActionListener(this); 
        rechercheDocteur.addActionListener(this);
        soigneDocteur.addActionListener(this);
        speDocteur.addActionListener(this);
        defautDocteur.addActionListener(this);
        directeurDocteur.addActionListener(this);
        
        //Pour rechercher par employe
        employe.addActionListener(this); 
        rechercheEmploye.addActionListener(this); 
        defautEmploye.addActionListener(this); 

        fenetreRes.setBackground(Color.LIGHT_GRAY);

        //Pour rechercher par infirmier
        infirmier.addActionListener(this); 
        rechercheInfirmier.addActionListener(this); 
        defautInfirmier.addActionListener(this); 
        //salaireInfirmier.addActionListener(this); 
        rotationInfirmier.addActionListener(this); 
        serviceInfirmier.addActionListener(this); 
        maladeInfirmier.addActionListener(this); 

        //Pour rechercher par malade
        malade.addActionListener(this); 
        rechercheMalade.addActionListener(this); 
        defautMalade.addActionListener(this); 
        mutuelleMalade.addActionListener(this); 
        chambreMalade.addActionListener(this); 
        docteurMalade.addActionListener(this); 

    
        //Pour rechercher par service
        service.addActionListener(this); 
        rechercheService.addActionListener(this); 
        defautService.addActionListener(this); 
        nomService.addActionListener(this); 
        batimentService.addActionListener(this); 
        directeurService.addActionListener(this); 
     

        // disposition geographique des panneaux
        //add("North", nord);
        add("Center",p2);
        add("South",pA);
        
        // pour fermer la fenetre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0); // tout fermer												System.exit(0); // tout fermer
            }
        });
    }

    /**
     * Méthode privée qui initialise la liste des tables
     */
    

    /**
     * Méthode privée qui initialise la liste des requetes de selection
     */

    /**
     * Méthode privée qui initialise la liste des requetes de MAJ
     */
    

    /**
     *
     * Afficher les tables
     */

    /**
     *
     * Afficher les lignes de la table sélectionnée
     */
    public void afficherLignes(String nomTable) {
        try {
            ArrayList<String> liste;

            // effacer les résultats
            fenetreLignes.removeAll();

            // recupérér les résultats de la table selectionnee
            liste = maconnexion.remplirChampsTable(nomTable);

            // afficher les champs de la table selectionnee 
            fenetreLignes.setText("");
            for (String liste1 : liste) {
                fenetreLignes.append(liste1);
            }

            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select * from " + nomTable + ";";
            liste = maconnexion.remplirChampsRequete(requeteSelectionnee);

            // afficher les lignes de la requete selectionnee a partir de la liste
            for (String liste1 : liste) {
                fenetreLignes.append(liste1);
            }

        } catch (SQLException e) {
            // afficher l'erreur dans les résultats
            fenetreRes.setText("");
            fenetreRes.append("Echec table SQL");
            e.printStackTrace();

        }
    }

    /**
     *
     * Afficher les requetes de selection et de MAJ dans la fenetre
     */
    public void afficherRequetes() {
        for (String requete : maconnexion.requetes) {
            listeDeRequetes.add(requete);
        }
    }

    /**
     *
     * Afficher et retourner les résultats de la requete sélectionnée
     *
     * @param requeteSelectionnee
     */
    public ArrayList<String> afficherRes(String requeteSelectionnee) throws SQLException {
        ArrayList<String> liste = null;
        try {

            // effacer les résultats
            fenetreRes.removeAll();

            // recupérér les résultats de la requete selectionnee
            liste = maconnexion.remplirChampsRequete(requeteSelectionnee);

            // afficher les lignes de la requete selectionnee a partir de la liste
            fenetreRes.setText("");
            for (String liste1 : liste) {
                fenetreRes.append(liste1);
            }
        } catch (SQLException e) {
            // afficher l'erreur dans les résultats
            fenetreRes.setText("");
            fenetreRes.append("Echec requete SQL");
        }
        return liste;
    }
    
    
    

    /**
     *
     * Pour gerer les actions sur les boutons on utilise la fonction
     * actionPerformed
     *
     * @param evt
     */
    @Override
    @SuppressWarnings("CallToThreadDumpStack")
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();

        // tester cas de la commande evenementielle
    
        if (source==rechercheChambre)
        {
            int numChambre = Integer.parseInt(chambre.getText()); //le numéro de chambre que l'utilisateur entre converti en int
            String requeteSelectionnee = null;
            if (malades.isSelected())
            { //OK
               
                requeteSelectionnee = "SELECT hospitalisation.no_chambre,service.batiment,malade.nom,malade.prenom,service.code,service.nom"
                                      + " FROM hospitalisation"
                                      + " JOIN malade ON malade.numero=hospitalisation.no_malade"
                                      + " JOIN service ON service.code=hospitalisation.code_service"
                                      + " WHERE hospitalisation.no_chambre=" + numChambre + ";"; 
                              

            }
            if (batiments.isSelected())
            { //OK
                
                requeteSelectionnee = "SELECT chambre.no_chambre, service.batiment,service.code, service.nom"
                        + "            FROM service JOIN chambre ON service.code=chambre.code_service "
                        + "            WHERE chambre.no_chambre=" + numChambre + ";";
                
                //CREATION DU TABLEAU
                /*try {
                    Object[][] data = new Object[afficherRes(requeteSelectionnee).size()][10];
                    //AJOUT
                    int i = 0;
                    while (i < afficherRes(requeteSelectionnee).size()){
                        data[i][0] = afficherRes(requeteSelectionnee).get(i);
                        data[i][1] = afficherRes(requeteSelectionnee).get(i+1);
                        i=i+2;
 
                    } 
                    //FIN AJOUT
                    String[] title = {"num chambre","service","code","nom"};
                    JTable tableau = new JTable(data,title);

                    this.getContentPane().add(new JScrollPane(tableau));
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre2.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                //FIN CREATION DU TABLEAU
                        
            }
            if (litChambre.isSelected())
            {//OK
                requeteSelectionnee = "SELECT chambre.nb_lits,chambre.code_service FROM chambre"
                        + " JOIN service ON chambre.code_service=service.code"
                        + " WHERE no_chambre=" + numChambre + ";";
               
            }
            else if (defautChambre.isSelected())
            {
                requeteSelectionnee = "SELECT * FROM chambre WHERE no_chambre=" + numChambre + ";";
               
            }
            
           
            //remplirRequetes();
            //afficherRequetes();
            
            //PERMET D AFFICHER LES RESULTATS
            try {
                
                afficherRes(requeteSelectionnee);

            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
            //FIN DU PERMET D AFFICHER LES RESULTATS 

        }
        else if (source==rechercheDocteur)
        {
            String nomDoc = docteur.getText();
            String requeteSelectionnee = null;
            if (speDocteur.isSelected())
            { //OK 
                requeteSelectionnee = "SELECT docteur.specialite FROM docteur "
                        + "JOIN employe ON employe.numero=docteur.numero "
                        + "WHERE employe.nom LIKE '" + nomDoc + "%';";
                

            }
            if (soigneDocteur.isSelected())
            {//OK
                requeteSelectionnee = "SELECT malade.nom, malade.prenom FROM soigne "
                        + "JOIN malade ON soigne.no_malade=malade.numero "
                        + "JOIN docteur ON soigne.no_docteur=docteur.numero "
                        + "JOIN employe ON employe.numero=soigne.no_docteur "
                        + "WHERE employe.nom LIKE '" + nomDoc + "%';";
                
            }
            if (directeurDocteur.isSelected())
            {//PAS OK 
                requeteSelectionnee = "SELECT employe.nom,employe.prenom FROM service "
                        + "JOIN employe ON employe.numero=service.directeur "
                        + "JOIN docteur ON docteur.numero=employe.numero "
                        + "WHERE employe.nom LIKE '" + nomDoc + "%';";
                
            }
            else if (defautDocteur.isSelected()) //SCROLLPANE
            {//OK BOF
                requeteSelectionnee = "SELECT * FROM docteur "
                        + "INNER JOIN employe ON employe.numero=docteur.numero "
                        + "WHERE employe.nom LIKE '" + nomDoc + "%';";
            }
            //remplirRequetes();
            //afficherRequetes();
            
            try {
                afficherRes(requeteSelectionnee);
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre2.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
        else if (source==rechercheEmploye)
        {
            String nomEmploye = employe.getText();
            String requeteSelectionnee=null;
            
            
            if (defautEmploye.isSelected())
            {//OK
                requeteSelectionnee = "SELECT employe.nom,employe.prenom,employe.adresse,employe.tel FROM employe"
                       + " WHERE employe.nom LIKE '" + nomEmploye + "%';";
            }
            try {
                
                afficherRes(requeteSelectionnee);

            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
        }
        else if (source==rechercheInfirmier)
        {//OK
            String nomInfirmier = infirmier.getText();
            String requeteSelectionnee=null;
            /*if (salaireInfirmier.isSelected())
            {//OK 
                requeteSelectionnee = "SELECT infirmier.salaire FROM infirmier"
                        + " JOIN employe ON employe.numero=infirmier.numero"
                        + " WHERE employe.nom LIKE '" + nomInfirmier + "%';"; 
            }*/
            if (rotationInfirmier.isSelected())
            {//OK                
                 requeteSelectionnee = "SELECT infirmier.rotation FROM infirmier"
                        + " JOIN employe ON employe.numero=infirmier.numero"
                        + " WHERE employe.nom LIKE '" + nomInfirmier + "%';";
            }
            if (serviceInfirmier.isSelected())
            {//OK 
                requeteSelectionnee = "SELECT infirmier.code_service FROM infirmier"
                        + " JOIN employe ON employe.numero=infirmier.numero"
                        + " WHERE employe.nom LIKE '" + nomInfirmier + "%';";
            }
            
            else if (defautInfirmier.isSelected())
            {//OK
                requeteSelectionnee = "SELECT employe.nom,employe.prenom,employe.adresse,employe.tel FROM infirmier"
                        + " JOIN employe ON employe.numero=infirmier.numero"
                        + " WHERE employe.nom LIKE '" + nomInfirmier + "%';";
            }
            try {
                
                afficherRes(requeteSelectionnee);

            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (source==rechercheMalade)
        {
            String nomMalade = malade.getText();
            String requeteSelectionnee=null;
            if (mutuelleMalade.isSelected())
            {
                requeteSelectionnee = "SELECT malade.mutuelle FROM malade"
                        + " WHERE malade.nom LIKE '" + nomMalade+ "%';";
            }
            if (chambreMalade.isSelected())
            {//OK                
                 requeteSelectionnee = "SELECT hospitalisation.no_chambre,hospitalisation.code_service,service.batiment,service.nom FROM hospitalisation"
                         + " JOIN malade ON malade.numero=hospitalisation.no_malade"
                         + " JOIN service ON hospitalisation.code_service=service.code"
                          + " WHERE malade.nom LIKE '" + nomMalade+ "%';";
            }
            if (docteurMalade.isSelected())
            {//OK 
                requeteSelectionnee = "SELECT employe.nom,employe.prenom FROM docteur"
                        + " JOIN employe ON docteur.numero=employe.numero"
                        + " JOIN soigne ON docteur.numero=soigne.no_docteur"
                        + " JOIN malade ON malade.numero=soigne.no_malade"
                        + " WHERE malade.nom LIKE '" + nomMalade+ "%';";
            }
            else if (defautMalade.isSelected())
            {//OK
                requeteSelectionnee = "SELECT malade.nom,malade.prenom,malade.adresse,malade.tel,malade.mutuelle FROM malade"
                        + " WHERE malade.nom LIKE '" + nomMalade + "%';";
            }
            
            try {
                
                afficherRes(requeteSelectionnee);

            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (source==rechercheService)
        {//OK

            String codeService = service.getText();
            String requeteSelectionnee=null;
            if (nomService.isSelected())
            {//OK 
                requeteSelectionnee = "SELECT service.nom FROM service"
                         + " WHERE service.code LIKE '" + codeService + "%';"; 
            }
            if (batimentService.isSelected())
            {//OK                
                 requeteSelectionnee = "SELECT service.batiment FROM service"
                         + " WHERE service.code LIKE '" + codeService + "%';"; 
            }
            if (directeurService.isSelected())
            { // OK
                requeteSelectionnee = "SELECT employe.nom,employe.prenom FROM employe "
                        + "JOIN service ON service.directeur=employe.numero "
                        + " WHERE service.code LIKE '" + codeService + "%';"; 
            }
            else if (defautService.isSelected())
            {//OK
                requeteSelectionnee = "SELECT service.nom,service.batiment FROM service"
                         + " WHERE service.code LIKE '" + codeService + "%';"; 
            }
            try {
                
                afficherRes(requeteSelectionnee);

            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * Pour gerer les actions sur items d'une liste on utilise la methode
     * itemStateChanged
     */
    @Override
    @SuppressWarnings("CallToThreadDumpStack")
    public void itemStateChanged(ItemEvent evt) {
        // sélection d'une requete et afficher ses résultats
        if (evt.getSource() == listeDeRequetes) {
            // recuperer la liste des lignes de la requete selectionnee
            String requeteSelectionnee = listeDeRequetes.getSelectedItem();
            try {
                afficherRes(requeteSelectionnee);
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getSource() == listeDeTables) {
            // afficher les lignes de la table sélectionnée
            String nomTable = listeDeTables.getSelectedItem();
            afficherLignes(nomTable);
        }
    }
    
    
    public void init(){
            ArrayList<String> liste;
            try {

                    // effacer les listes de tables et de requêtes
                    listeDeTables.removeAll();
                    listeDeRequetes.removeAll();

                    // initialisation de la liste des requetes de selection et de MAJ

                    afficherRequetes();

                    // se positionner sur la première table et requête de selection
                    listeDeTables.select(0);
                    listeDeRequetes.select(0);

                    // afficher les champs de la table sélectionnée
                    String nomTable = listeDeTables.getSelectedItem();

                    // recuperer les lignes de la table selectionnee
                    afficherLignes(nomTable);

                    // recuperer la liste des lignes de la requete selectionnee
                    String requeteSelectionnee = listeDeRequetes.getSelectedItem();

                    // afficher les résultats de la requete selectionnee
                    afficherRes(requeteSelectionnee);

            } catch (SQLException e) {
                System.out.println("Connexion echouee : probleme SQL");
                e.printStackTrace();
            }
    
    
    
    }

    void addActionListener(Controleur aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
