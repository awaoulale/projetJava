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
 * Affiche dans la fenetre graphique les résultats d'une recherche sur une ou plusieurs tables avec filtrage des informations
 * 
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
    private final JLabel fenetreRes2 = new JLabel();
   
    private final JPanel p1, p2, p3, p4, p5, p6, p7, p8, pD, pI, pM, pE, pC, pS;
    
    //pour rechercher par chambre 
    private final JTextField chambre; 
    private final JButton rechercheChambre;
    private final JRadioButton malades;
    private final JRadioButton batiments;
    private final JRadioButton surveillantChambre;
    private final JRadioButton defautChambre;
    
    //pour rechercher par docteur 
    private final JTextField nDocteur; 
    private final JTextField pDocteur;
    private final JButton rechercheDocteur;
    private final JRadioButton soigneDocteur;
    private final JRadioButton speDocteur;
    private final JRadioButton defautDocteur;
    

    //Pour rechercher par employe 
    private final JTextField nEmploye;
    private final JTextField pEmploye;
    private final JButton rechercheEmploye;
    private final JRadioButton defautEmploye;
    private final JRadioButton telEmploye;
    private final JRadioButton adresseEmploye;
   
    //Pour rechercher par infirmier 
    private final JTextField nInfirmier;
    private final JTextField pInfirmier;
    private final JButton rechercheInfirmier;
    private final JRadioButton defautInfirmier;
    private final JRadioButton rotationInfirmier;
    private final JRadioButton serviceInfirmier;
    private final JRadioButton chambreInfirmier; 
    
    //Pour rechercher par malade 
    private final JTextField nMalade;
    private final JTextField pMalade;
    private final JButton rechercheMalade;
    private final JRadioButton defautMalade;
    private final JRadioButton mutuelleMalade;
    private final JRadioButton chambreMalade;
    private final JRadioButton docteurMalade; 
    
    //Pour rechercher par service
    private final JTextField service;
    private final JButton rechercheService;
    private final JRadioButton defautService;
    private final JRadioButton batimentService;
    private final JRadioButton directeurService; 
    
    
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

  
        // creation des listes pour les tables et les requetes
        listeDeTables = new java.awt.List(10, false);
        listeDeRequetes = new java.awt.List(10, false);

        // creation des textes
        fenetreLignes = new JTextArea();
        fenetreRes = new JTextArea();
        

        
        //Pour rechercher par chambre
        chambre=new JTextField("");
        rechercheChambre = new JButton("Rechercher avec un numéro de chambre");
        malades = new JRadioButton("malades");
        batiments = new JRadioButton("batiments");
        surveillantChambre = new JRadioButton("surveillants");
        defautChambre=new JRadioButton("par défaut");
        defautChambre.setSelected(true);
      
        ButtonGroup bgChambre = new ButtonGroup(); //pour selectioner 1 seul RadioButton à la fois
        bgChambre.add(malades);
        bgChambre.add(batiments);
        bgChambre.add(surveillantChambre);
        bgChambre.add(defautChambre);


        //Pour rechercher par docteur
        rechercheDocteur = new JButton("Rechercher avec un docteur");
        nDocteur=new JTextField();
        pDocteur=new JTextField();
        soigneDocteur=new JRadioButton("malades");
        speDocteur=new JRadioButton("spécialité");
        defautDocteur=new JRadioButton("par défaut");
        defautDocteur.setSelected(true);
        ButtonGroup bgDocteur = new ButtonGroup();
        bgDocteur.add(defautDocteur);
        bgDocteur.add(soigneDocteur);
        bgDocteur.add(speDocteur);
        
        //Pour rechercher par employe
        nEmploye=new JTextField();
        pEmploye=new JTextField();
        rechercheEmploye=new JButton("Rechercher avec un employé");
        defautEmploye=new JRadioButton("par défaut");
        telEmploye=new JRadioButton("téléphone");
        adresseEmploye=new JRadioButton("adresse");
        defautEmploye.setSelected(true);
        ButtonGroup bgEmploye = new ButtonGroup();
        bgEmploye.add(defautEmploye);
        bgEmploye.add(telEmploye);
        bgEmploye.add(adresseEmploye);
        
        //Pour rechercher par infirmier
        nInfirmier=new JTextField();        
        pInfirmier=new JTextField();
        rechercheInfirmier=new JButton("Rechercher avec un infirmier");
        defautInfirmier=new JRadioButton("par défaut");
        defautInfirmier.setSelected(true);
        rotationInfirmier=new JRadioButton("rotation");
        serviceInfirmier=new JRadioButton("service");
        chambreInfirmier=new JRadioButton("chambres");
        ButtonGroup bgInfirmier = new ButtonGroup(); 
        bgInfirmier.add(defautInfirmier);
        bgInfirmier.add(rotationInfirmier);
        bgInfirmier.add(serviceInfirmier);
        bgInfirmier.add(chambreInfirmier);
    
        //Pour rechercher par malade
        nMalade=new JTextField();
        pMalade=new JTextField();
        rechercheMalade=new JButton("Rechercher avec un malade");
        defautMalade=new JRadioButton("par défaut");
        defautMalade.setSelected(true);
        mutuelleMalade=new JRadioButton("mutuelle");
        chambreMalade=new JRadioButton("chambre");
        docteurMalade=new JRadioButton("docteurs");
        ButtonGroup bgMalade = new ButtonGroup(); 
        bgMalade.add(defautMalade);
        bgMalade.add(mutuelleMalade);
        bgMalade.add(chambreMalade);
        bgMalade.add(docteurMalade);
    
        //Pour rechercher par service
        service=new JTextField();
        rechercheService=new JButton("Rechercher avec un code");
        defautService=new JRadioButton("par défaut");
        defautService.setSelected(true);
        batimentService=new JRadioButton("batiment");
        directeurService=new JRadioButton("directeur");
        ButtonGroup bgService = new ButtonGroup(); 
        bgService.add(defautService);
        bgService.add(batimentService);
        bgService.add(directeurService);


        // creation des panneaux
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        p6 = new JPanel();
        p7 = new JPanel();
        p8 = new JPanel();
        pC = new JPanel();
        pS = new JPanel();
        pD = new JPanel();
        pE = new JPanel();
        pM = new JPanel();
        pI = new JPanel();
        

        // mise en page des panneaux
        p1.setLayout(new GridLayout(6,1));//Pour la chambre
        p2.setLayout(new GridLayout(1,1));//Pour les résultats
        
        p3.setLayout(new GridLayout(2,3,30,20)); //regroupe p1,p4,p5,p6,p7,p8
        p4.setLayout(new GridLayout(6,1));//Pour le docteur
        p5.setLayout(new GridLayout(1,3));//Pour l'employe
        
        p6.setLayout(new GridLayout(6,1));//Pour l'infirmier
        p7.setLayout(new GridLayout(6,1));//Pour le malade
        p8.setLayout(new GridLayout(6,1));//Pour le service


        // ajout des objets graphqiues dans les panneaux
        //Pour rechercher par chambre
        p1.setLayout(new BoxLayout(p1,BoxLayout.PAGE_AXIS));
        pC.setLayout(new BoxLayout(pC,BoxLayout.LINE_AXIS));
        JLabel numC = new JLabel("Numéro de la chambre :");
        pC.add(numC);
        pC.add(chambre);
        p1.add(pC);
        
        p1.add(rechercheChambre);
        p1.add(defautChambre);
        p1.add(malades);
        p1.add(batiments);
        p1.add(surveillantChambre);
                
        //Pour recherche par docteur
        p4.setLayout(new BoxLayout(p4,BoxLayout.PAGE_AXIS));
        pD.setLayout(new BoxLayout(pD,BoxLayout.LINE_AXIS));
        
        nDocteur.setMinimumSize(new Dimension(175,20)); //Arranger la taille des JTextField
        nDocteur.setPreferredSize(new Dimension(175,20));
        nDocteur.setMaximumSize(new Dimension(175,20));
        pDocteur.setMinimumSize(new Dimension(175,20));
        pDocteur.setPreferredSize(new Dimension(175,20));
        pDocteur.setMaximumSize(new Dimension(175,20));
        
        JLabel nomD = new JLabel("Nom :");
        JLabel prenomD = new JLabel("Prénom :");
        pD.add(nomD);
        pD.add(nDocteur);
        pD.add(prenomD);
        pD.add(pDocteur);
        p4.add(pD);
        
        p4.add(rechercheDocteur);
        p4.add(defautDocteur);
        p4.add(soigneDocteur);
        p4.add(speDocteur);
        
        //Pour rechercher par employe
        p5.setLayout(new BoxLayout(p5,BoxLayout.PAGE_AXIS));
        pE.setLayout(new BoxLayout(pE,BoxLayout.LINE_AXIS));
         
        nEmploye.setMinimumSize(new Dimension(175,20)); //Arranger la taille des JTextField
        nEmploye.setPreferredSize(new Dimension(175,20));
        nEmploye.setMaximumSize(new Dimension(175,20));
        pEmploye.setMinimumSize(new Dimension(175,20));
        pEmploye.setPreferredSize(new Dimension(175,20));
        pEmploye.setMaximumSize(new Dimension(175,20));
         
        JLabel nomE = new JLabel("Nom :");
        JLabel prenomE = new JLabel("Prénom :");
        pE.add(nomE);
        pE.add(nEmploye);
        pE.add(prenomE);
        pE.add(pEmploye);
        p5.add(pE);
        p5.add(rechercheEmploye);
        p5.add(defautEmploye);
        p5.add(telEmploye);
        p5.add(adresseEmploye);
       
         //Pour recherche par infirmier
        p6.setLayout(new BoxLayout(p6,BoxLayout.PAGE_AXIS));
        pI.setLayout(new BoxLayout(pI,BoxLayout.LINE_AXIS));
        JLabel nomI = new JLabel("Nom :");
        JLabel prenomI = new JLabel("Prénom :");
        pI.add(nomI);
        pI.add(nInfirmier);
        pI.add(prenomI);
        pI.add(pInfirmier);
        p6.add(pI);
        p6.add(rechercheInfirmier);
        p6.add(defautInfirmier);
        p6.add(rotationInfirmier);
        p6.add(serviceInfirmier);
        p6.add(chambreInfirmier);
  
        //Pour recherche par malalde
        p7.setLayout(new BoxLayout(p7,BoxLayout.PAGE_AXIS));
        pM.setLayout(new BoxLayout(pM,BoxLayout.LINE_AXIS));
        JLabel nomM = new JLabel("Nom :");
        JLabel prenomM = new JLabel("Prénom :");
        pM.add(nomM);
        pM.add(nMalade);
        pM.add(prenomM);
        pM.add(pMalade);
        p7.add(pM);   
        p7.add(rechercheMalade);
        p7.add(defautMalade);
        p7.add(mutuelleMalade);
        p7.add(chambreMalade);
        p7.add(docteurMalade);
        
        
        //Pour recherche par service
        p8.setLayout(new BoxLayout(p8,BoxLayout.PAGE_AXIS));
        pS.setLayout(new BoxLayout(pS,BoxLayout.LINE_AXIS));  
        service.setMinimumSize(new Dimension(330,20));
        service.setPreferredSize(new Dimension(330,20));
        service.setMaximumSize(new Dimension(330,20));
              
        JLabel codeS = new JLabel("Code du service :");
        pS.add(codeS);
        pS.add(service);
        p8.add(pS);
        
        p8.add(rechercheService);
        p8.add(defautService);
        p8.add(batimentService);
        p8.add(directeurService);
 
        p3.add(p1);
        p3.add(p6);
        p3.add(p7);
        p3.add(p5);
        p3.add(p8);
        p3.add(p4);
        
        
        // ajout des listeners

        listeDeTables.addItemListener(this);
        listeDeRequetes.addItemListener(this);
        
        //Pour rechercher par chambre
        chambre.addActionListener(this);
        rechercheChambre.addActionListener(this);
        malades.addActionListener(this);
        batiments.addActionListener(this);
        surveillantChambre.addActionListener(this);
        defautChambre.addActionListener(this);

        //Pour rechercher par docteur 
        nDocteur.addActionListener(this); 
        pDocteur.addActionListener(this); 
        rechercheDocteur.addActionListener(this);
        soigneDocteur.addActionListener(this);
        speDocteur.addActionListener(this);
        defautDocteur.addActionListener(this);
        
        //Pour rechercher par employe
        nEmploye.addActionListener(this); 
        pEmploye.addActionListener(this);
        rechercheEmploye.addActionListener(this); 
        defautEmploye.addActionListener(this); 
        telEmploye.addActionListener(this); 
        adresseEmploye.addActionListener(this); 

        //Pour rechercher par infirmier
        nInfirmier.addActionListener(this); 
        pInfirmier.addActionListener(this);
        rechercheInfirmier.addActionListener(this); 
        defautInfirmier.addActionListener(this);  
        rotationInfirmier.addActionListener(this); 
        serviceInfirmier.addActionListener(this); 
        chambreInfirmier.addActionListener(this); 

        //Pour rechercher par malade
        nMalade.addActionListener(this);
        pMalade.addActionListener(this);
        rechercheMalade.addActionListener(this); 
        defautMalade.addActionListener(this); 
        mutuelleMalade.addActionListener(this); 
        chambreMalade.addActionListener(this); 
        docteurMalade.addActionListener(this); 

        //Pour rechercher par service
        service.addActionListener(this); 
        rechercheService.addActionListener(this); 
        defautService.addActionListener(this);  
        batimentService.addActionListener(this); 
        directeurService.addActionListener(this); 
     
        //Gestion des couleurs 
        //Les pans
        p3.setBackground(Color.ORANGE);
        p1.setBackground(Color.ORANGE);
        p6.setBackground(Color.ORANGE);
        p7.setBackground(Color.ORANGE);
        p5.setBackground(Color.ORANGE);
        p8.setBackground(Color.ORANGE);
        p4.setBackground(Color.ORANGE);
        
        //Les JLabel 
        nomE.setOpaque(true);
        nomI.setOpaque(true);
        nomD.setOpaque(true);
        nomM.setOpaque(true);
        prenomE.setOpaque(true);
        prenomM.setOpaque(true);
        prenomI.setOpaque(true);
        prenomD.setOpaque(true);
        codeS.setOpaque(true);
        numC.setOpaque(true);
        
        nomE.setBackground(Color.ORANGE);
        prenomE.setBackground(Color.ORANGE);
        nomM.setBackground(Color.ORANGE);
        prenomM.setBackground(Color.ORANGE);
        nomI.setBackground(Color.ORANGE);
        prenomI.setBackground(Color.ORANGE);
        nomD.setBackground(Color.ORANGE);
        prenomD.setBackground(Color.ORANGE);
        codeS.setBackground(Color.ORANGE);
        numC.setBackground(Color.ORANGE);
        
        //Les RadioButton
        //Chambre
        defautChambre.setBackground(Color.ORANGE);
        malades.setBackground(Color.ORANGE);
        batiments.setBackground(Color.ORANGE);
        surveillantChambre.setBackground(Color.ORANGE);
        defautChambre.setBackground(Color.ORANGE);
        
        //Docteur
        soigneDocteur.setBackground(Color.ORANGE);
        speDocteur.setBackground(Color.ORANGE);
        defautDocteur.setBackground(Color.ORANGE);
        
        //Service
        defautService.setBackground(Color.ORANGE);  
        batimentService.setBackground(Color.ORANGE);
        directeurService.setBackground(Color.ORANGE);
        
        //Malade
        defautMalade.setBackground(Color.ORANGE);
        mutuelleMalade.setBackground(Color.ORANGE);
        chambreMalade.setBackground(Color.ORANGE);
        docteurMalade.setBackground(Color.ORANGE);
        
        //Employe
        defautEmploye.setBackground(Color.ORANGE);
        telEmploye.setBackground(Color.ORANGE);
        adresseEmploye.setBackground(Color.ORANGE);
        
        //Infirmier
        defautInfirmier.setBackground(Color.ORANGE); 
        rotationInfirmier.setBackground(Color.ORANGE); 
        serviceInfirmier.setBackground(Color.ORANGE);
        chambreInfirmier.setBackground(Color.ORANGE);
        
        //Les JButton
        rechercheMalade.setBackground(Color.GREEN);
        rechercheInfirmier.setBackground(Color.GREEN);
        rechercheEmploye.setBackground(Color.GREEN);
        rechercheChambre.setBackground(Color.GREEN);
        rechercheService.setBackground(Color.GREEN);
        rechercheDocteur.setBackground(Color.GREEN);
        
          
        
        // disposition geographique des panneaux
        add("South",p3);
        add(fenetreRes2); //Pour afficher les tableaux
        add(fenetreRes); 
   
        // pour fermer la fenetre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0); // tout fermer												System.exit(0); // tout fermer
            }
        });
    }


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
     * Afficher et retourner les résultats de la requete sélectionnée en utilisant remplirChampsReqeuete2
     *
     * @param requeteSelectionnee
     */
    public ArrayList<String> afficherRes2(String requeteSelectionnee) throws SQLException {
        ArrayList<String> liste = null;
        try {

            // effacer les résultats
            fenetreRes2.removeAll();

            // recupérér les résultats de la requete selectionnee
            liste = maconnexion.remplirChampsRequete2(requeteSelectionnee);

            // afficher les lignes de la requete selectionnee a partir de la liste
            fenetreRes2.setText("");
            
            for (String liste1 : liste) 
            { 
                fenetreRes2.setText(liste1);
            } 
            
        } catch (SQLException e) {
            // afficher l'erreur dans les résultats
            fenetreRes2.setText("");
            fenetreRes2.setText("Echec requete SQL"); 
        }
        return liste;
    }
    
    /**
     *
     * Afficher les resultats de la recherche dans un tableau 
     * 
     * @param nbColonnes
     * @param reqSel
     * @param title
     */
    void afficherTableau(int nbColonnes, String reqSel, String[] title)
    {
        
         try {
                    int nbLignes = (afficherRes2(reqSel).size())/nbColonnes;
                    Object[][] data = new Object[nbLignes][nbColonnes];
                    int n=0;
                    for (int k=0;k<nbLignes;k++)
                    {
                        for (int l=0;l<nbColonnes;l++)
                        {
                            data[k][l]=afficherRes2(reqSel).get(n);
                            n++;
                        }
                    }
                    JTable tableau= new JTable(data,title);
                    
                   this.getContentPane().add(new JScrollPane(tableau));
                    
                    tableau.getTableHeader().setBackground(Color.PINK);
               } catch (SQLException ex) {
                  Logger.getLogger(Fenetre2.class.getName()).log(Level.SEVERE, null, ex);
                }
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
            { 
               
                requeteSelectionnee = "SELECT service.batiment,malade.nom,malade.prenom,service.code,service.nom"
                                      + " FROM hospitalisation"
                                      + " JOIN malade ON malade.numero=hospitalisation.no_malade"
                                      + " JOIN service ON service.code=hospitalisation.code_service"
                                      + " WHERE hospitalisation.no_chambre=" + numChambre + ";"; 
                              
            String[] titre = {"Batiment","Nom du malade","Prénom du malade","Code du service","Nom du service"};
            int nbCol=5;
            afficherTableau(nbCol,requeteSelectionnee,titre);
            
            }
            if (batiments.isSelected())
            { 
                
                requeteSelectionnee = "SELECT service.batiment,service.code, service.nom"
                        + "            FROM service JOIN chambre ON service.code=chambre.code_service "
                        + "            WHERE chambre.no_chambre=" + numChambre + ";";
                 
            String[] titre = {"Batiment","Code du service","Nom du service"};
              int nbCol=3;
             afficherTableau(nbCol,requeteSelectionnee,titre);
            }
            
            if (surveillantChambre.isSelected())
            {
                requeteSelectionnee = "SELECT chambre.code_service,employe.nom,employe.prenom FROM chambre"
                        + " JOIN employe ON chambre.surveillant=employe.numero"
                        + " WHERE chambre.no_chambre=" + numChambre + ";";
                
            String[] titre = {"Code du service","Nom de l'employé","Prénom de l'employé"};
              int nbCol=3;
              afficherTableau(nbCol,requeteSelectionnee,titre);
            }
            if (defautChambre.isSelected())
            {
                requeteSelectionnee = "SELECT chambre.code_service,chambre.nb_lits FROM chambre"
                        + " WHERE chambre.no_chambre=" + numChambre + ";";
              
             String[] titre = {"Code du service","Nombre de lits"};
               int nbCol=2;
           afficherTableau(nbCol,requeteSelectionnee,titre);
            }
        }
        else if (source==rechercheDocteur)
        {
            String nomDoc = nDocteur.getText();
            String prenomDoc = pDocteur.getText();
            String requeteSelectionnee = null;
            if (speDocteur.isSelected())
            { 
                requeteSelectionnee = "SELECT docteur.specialite FROM docteur "
                        + "JOIN employe ON employe.numero=docteur.numero "
                        + "WHERE employe.nom LIKE '" + nomDoc + "%' "
                        + "AND employe.prenom LIKE '" + prenomDoc + "%';";
                
                String[] titre = {"Spécialité du docteur"};
               int nbCol=1;
           afficherTableau(nbCol,requeteSelectionnee,titre);

            }
            if (soigneDocteur.isSelected())
            {
                requeteSelectionnee = "SELECT malade.nom, malade.prenom FROM soigne "
                        + "JOIN malade ON soigne.no_malade=malade.numero "
                        + "JOIN docteur ON soigne.no_docteur=docteur.numero "
                        + "JOIN employe ON employe.numero=soigne.no_docteur "
                        + "WHERE employe.nom LIKE '" + nomDoc + "%' "
                        + "AND employe.prenom LIKE '" + prenomDoc + "%';";
                
                String[] titre = {"Nom des malades","Prénom des malades"};
               int nbCol=2;
           afficherTableau(nbCol,requeteSelectionnee,titre);
                
            }
            else if (defautDocteur.isSelected()) 
            { 
                requeteSelectionnee = "SELECT employe.nom,employe.prenom,employe.adresse,employe.tel FROM docteur "
                        + "INNER JOIN employe ON employe.numero=docteur.numero "
                        + "WHERE employe.nom LIKE '" + nomDoc + "%' "
                        + "AND employe.prenom LIKE '" + prenomDoc + "%';";
                
                String[] titre = {"Nom du docteur","Prénom du docteur","Adresse du docteur","Code postal et ville","Téléphone du docteur"};
               int nbCol=5;
           afficherTableau(nbCol,requeteSelectionnee,titre);
            }

        }
        else if (source==rechercheEmploye)
        {
            String nomEmpl = nEmploye.getText();
            String prenomEmpl = pEmploye.getText();
            String requeteSelectionnee=null;
            
            if (adresseEmploye.isSelected())
            {
                requeteSelectionnee = "SELECT employe.adresse FROM employe"
                       + " WHERE employe.nom LIKE '" + nomEmpl + "%'"
                       + " AND employe.prenom LIKE '" + prenomEmpl + "%';";
                
                String[] titre = {"Adresse de l'employé","Code postal et ville"};
               int nbCol=2;
           afficherTableau(nbCol,requeteSelectionnee,titre);
            }
            if (telEmploye.isSelected())
            {
                requeteSelectionnee = "SELECT employe.tel FROM employe"
                       + " WHERE employe.nom LIKE '" + nomEmpl + "%'"
                       + " AND employe.prenom LIKE '" + prenomEmpl + "%';";
                
                String[] titre = {"Téléphone de l'employé"};
               int nbCol=1;
           afficherTableau(nbCol,requeteSelectionnee,titre);
            }
            else if (defautEmploye.isSelected())
            {
                requeteSelectionnee = "SELECT employe.nom,employe.prenom,employe.adresse,employe.tel FROM employe"
                       + " WHERE employe.nom LIKE '" + nomEmpl + "%'"
                       + " AND employe.prenom LIKE '" + prenomEmpl + "%';";
                
                String[] titre = {"Nom de l'employé","Prénom de l'employé","Adresse de l'employé","Code postal et ville","Téléphone de l'employé"};
               int nbCol=5;
           afficherTableau(nbCol,requeteSelectionnee,titre);
            }
                    
        }
        else if (source==rechercheInfirmier)
        {
            String nomInfi = nInfirmier.getText();
            String prenomInfi = pInfirmier.getText();
            String requeteSelectionnee=null;
            if (chambreInfirmier.isSelected())
            { 
                requeteSelectionnee = "SELECT chambre.no_chambre,chambre.code_service FROM chambre"
                        + " JOIN infirmier ON chambre.surveillant=infirmier.numero"
                        + " JOIN employe ON chambre.surveillant=employe.numero"
                        + " WHERE employe.nom LIKE '" + nomInfi + "%'"
                        + " AND employe.prenom LIKE '" + prenomInfi + "%';"; 
                
                String[] titre = {"Numéro de chambre","Code su service"};
               int nbCol=2;
           afficherTableau(nbCol,requeteSelectionnee,titre);
            }
            if (rotationInfirmier.isSelected())
            {                
                 requeteSelectionnee = "SELECT infirmier.rotation FROM infirmier"
                        + " JOIN employe ON employe.numero=infirmier.numero"
                       + " WHERE employe.nom LIKE '" + nomInfi + "%'"
                        + " AND employe.prenom LIKE '" + prenomInfi + "%';"; 
                 
                 String[] titre = {"Rotation"};
               int nbCol=1;
           afficherTableau(nbCol,requeteSelectionnee,titre);
                 
            }
            if (serviceInfirmier.isSelected())
            { 
                requeteSelectionnee = "SELECT infirmier.code_service FROM infirmier"
                        + " JOIN employe ON employe.numero=infirmier.numero"
                        + " WHERE employe.nom LIKE '" + nomInfi + "%'"
                        + " AND employe.prenom LIKE '" + prenomInfi + "%';"; 
                
                String[] titre = {"Code du service"};
               int nbCol=1;
           afficherTableau(nbCol,requeteSelectionnee,titre);
            }
            
            else if (defautInfirmier.isSelected())
            {
                requeteSelectionnee = "SELECT employe.nom,employe.prenom,employe.adresse,employe.tel FROM infirmier"
                        + " JOIN employe ON employe.numero=infirmier.numero"
                        + " WHERE employe.nom LIKE '" + nomInfi + "%'"
                        + " AND employe.prenom LIKE '" + prenomInfi + "%';"; 
                
                String[] titre = {"Nom de l'infirmier","Prénom de l'infirmier","Adresse de l'infirmier","Code postal et ville","Téléphone de l'infirmier"};
               int nbCol=5;
           afficherTableau(nbCol,requeteSelectionnee,titre);
            }

        }
        else if (source==rechercheMalade)
        {
            String nomMal = nMalade.getText();
            String prenomMal = pMalade.getText();
            String requeteSelectionnee=null;
            if (mutuelleMalade.isSelected())
            {
                requeteSelectionnee = "SELECT malade.mutuelle FROM malade"
                        + " WHERE malade.nom LIKE '" + nomMal+ "%'"
                        + " AND malade.prenom LIKE '" + prenomMal+ "%';";
                
                String[] titre = {"Mutuelle"};
               int nbCol=1;
           afficherTableau(nbCol,requeteSelectionnee,titre);
            }
            if (chambreMalade.isSelected())
            {                
                 requeteSelectionnee = "SELECT hospitalisation.no_chambre,hospitalisation.code_service,service.batiment,service.nom FROM hospitalisation"
                         + " JOIN malade ON malade.numero=hospitalisation.no_malade"
                         + " JOIN service ON hospitalisation.code_service=service.code"
                          + " WHERE malade.nom LIKE '" + nomMal+ "%'"
                        + " AND malade.prenom LIKE '" + prenomMal+ "%';";
                 
                 String[] titre = {"Numéro de chambre","Code du service","Batiment","Nom du service"};
               int nbCol=4;
           afficherTableau(nbCol,requeteSelectionnee,titre);
            }
            if (docteurMalade.isSelected())
            { 
                requeteSelectionnee = "SELECT employe.nom,employe.prenom FROM docteur"
                        + " JOIN employe ON docteur.numero=employe.numero"
                        + " JOIN soigne ON docteur.numero=soigne.no_docteur"
                        + " JOIN malade ON malade.numero=soigne.no_malade"
                        + " WHERE malade.nom LIKE '" + nomMal+ "%'"
                        + " AND malade.prenom LIKE '" + prenomMal+ "%';";
                
                String[] titre = {"Nom des docteurs","Prénom des docteurs"};
               int nbCol=2;
           afficherTableau(nbCol,requeteSelectionnee,titre);
            }
            else if (defautMalade.isSelected())
            {
                requeteSelectionnee = "SELECT malade.nom,malade.prenom,malade.adresse,malade.tel FROM malade"
                       + " WHERE malade.nom LIKE '" + nomMal+ "%'"
                        + " AND malade.prenom LIKE '" + prenomMal+ "%';";
                
                String[] titre = {"Nom du malade","Prénom du malade","Adresse du malade","Code postal et ville","Téléphone du malade"};
               int nbCol=5;
           afficherTableau(nbCol,requeteSelectionnee,titre);
            }
            
        }
        else if (source==rechercheService)
        {

            String codeService = service.getText();
            String requeteSelectionnee=null;
            if (batimentService.isSelected())
            {                
                 requeteSelectionnee = "SELECT service.batiment FROM service"
                         + " WHERE service.code LIKE '" + codeService + "%';"; 
                 
                 String[] titre = {"Batiment"};
               int nbCol=1;
           afficherTableau(nbCol,requeteSelectionnee,titre);
            }
            if (directeurService.isSelected())
            { 
                requeteSelectionnee = "SELECT employe.nom,employe.prenom FROM employe "
                        + "JOIN service ON service.directeur=employe.numero "
                        + " WHERE service.code LIKE '" + codeService + "%';"; 
                
                String[] titre = {"Nom du directeur","Prénom du directeur"};
               int nbCol=2;
           afficherTableau(nbCol,requeteSelectionnee,titre);
            }
            else if (defautService.isSelected())
            {
                requeteSelectionnee = "SELECT service.nom,service.batiment FROM service"
                         + " WHERE service.code LIKE '" + codeService + "%';"; 
                
                String[] titre = {"Nom du service","Batiment"};
               int nbCol=2;
           afficherTableau(nbCol,requeteSelectionnee,titre);
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


                    // effacer les listes de tables et de requêtes
                    listeDeTables.removeAll();
                    listeDeRequetes.removeAll();

                    // initialisation de la liste des requetes de selection et de MAJ

                    afficherRequetes();

                    // se positionner sur la première table et requête de selection
                    listeDeTables.select(0);
                    listeDeRequetes.select(0);

                System.out.println("Connexion echouee : probleme SQL");
  
    }
}
