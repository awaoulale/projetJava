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
public class Fenetre extends JFrame implements ActionListener, ItemListener {
    /*
     * Attribut privés : objets de Connexion, AWT et Swing
     * 
     */

    private Connexion maconnexion;
    private final JLabel tab, req, res, lignes;
    private final JLabel nameECE, passwdECE, loginBDD, passwdBDD, nameBDD, requeteLabel;
    private final JTextField nameECETexte, loginBDDTexte, requeteTexte, nameBDDTexte;
    private final JPasswordField passwdECETexte, passwdBDDTexte;
    private final JButton connect, exec, local;
    private final java.awt.List listeDeTables, listeDeRequetes;
    private final JTextArea fenetreLignes, fenetreRes;
    private final JPanel p0, p1, nord, p2, p3;
    
    //AWA
    private final JPanel pDroit, pChoix, pChgt;
    public JRadioButton insertion, suppression, maj;
    public JButton OK;
    
    //DOCTEUR
    JLabel numero;
    JTextField numeroText;
    JLabel specialite;
    JTextField specialiteText;  
    //CHAMBRE
    JLabel code_service;
    JTextField code_serviceText;
    JLabel no_chambre;
    JTextField no_chambreText;
    JLabel surveillant;
    JTextField surveillantText;
    JLabel nb_lits;
    JTextField nb_litsText;
    //EMPLOYE
    //meme numero que dans DOCTEUR
    JLabel nom;
    JTextField nomText;
    JLabel prenom;
    JTextField prenomText;
    JLabel adresse;
    JTextField adresseText;
    JLabel tel;
    JTextField telText;
    //HOSPITALISATION
    JLabel no_malade;
    JTextField no_maladeText;
    //meme code_service que dans CHAMBRE
    //meme no_chambre que dans CHAMBRE
    JLabel lit;
    JTextField litText;
    //INFIRMIER
    //meme numero que dans DOCTEUR
    //meme code_service que dans CHAMBRE
    JLabel rotation;
    JTextField rotationText;
    JLabel salaire;
    JTextField salaireText;
    //MALADE
    //meme numero que dans DOCTEUR
    //meme nom que dans EMPLOYE
    //meme prenom que dans EMPLOYE
    //meme adresse que dans EMPLOYE
    //meme tel que dans EMPLOYE
    JLabel mutuelle;
    JTextField mutuelleText;
    //SERVICE
    JLabel code;
    JTextField codeText;
    //meme nom que dans EMPLOYE
    JLabel batiment;
    JTextField batimentText;
    JLabel directeur;
    JTextField directeurText;
    //SOIGNE
    JLabel no_docteur;
    JTextField no_docteurText;
    //meme no_malade que dans HOSPITALISATION

    
    /**
     * Constructeur qui initialise tous les objets graphiques de la fenetre
     */
    public Fenetre() {

        // creation par heritage de la fenetre
        super("Projet d'utilisation de JDBC dans MySQL");

        // mise en page (layout) de la fenetre visible
        setLayout(new BorderLayout());
        setBounds(0, 0, 600, 600);
        setResizable(true);
        setVisible(true);

        // creation des boutons
        connect = new JButton("Connexion ECE");
        local = new JButton("Connexion locale");
        exec = new JButton("Executer");
        
        OK=new JButton("OK");
        
        // creation des listes pour les tables et les requetes
        listeDeTables = new java.awt.List(10, false);
        listeDeRequetes = new java.awt.List(10, false);

        // creation des textes
        nameECETexte = new JTextField();
        passwdECETexte = new JPasswordField(8);
        loginBDDTexte = new JTextField();
        passwdBDDTexte = new JPasswordField(8);
        nameBDDTexte = new JTextField();
        
        fenetreLignes = new JTextArea();
        fenetreRes = new JTextArea();
        requeteTexte = new JTextField();

        // creation des labels
        tab = new JLabel("Tables", JLabel.CENTER);
        lignes = new JLabel("Lignes", JLabel.CENTER);
        req = new JLabel("Requetes de sélection", JLabel.CENTER);
        res = new JLabel("Résultats requête", JLabel.CENTER);
        
        nameECE = new JLabel("login ECE :", JLabel.CENTER);
        passwdECE = new JLabel("password ECE :", JLabel.CENTER);
        loginBDD = new JLabel("login base :", JLabel.CENTER);
        passwdBDD = new JLabel("password base :", JLabel.CENTER);
        nameBDD = new JLabel("nom base :", JLabel.CENTER);
        requeteLabel = new JLabel("Entrez votre requete de sélection :", JLabel.CENTER);

        // creation des panneaux
        p0 = new JPanel();
        p1 = new JPanel();
        nord = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        
        pDroit=new JPanel();
        pChoix=new JPanel();
        pChgt=new JPanel();
        
        insertion=new JRadioButton("Insertion");
        suppression=new JRadioButton("Suppression");
        maj=new JRadioButton("Mise a jour");   
        
        // mise en page des panneaux
        p0.setLayout(new GridLayout(1, 11));
        p1.setLayout(new GridLayout(1, 4));
        nord.setLayout(new GridLayout(2, 1));
        //p2.setLayout(new GridLayout(1, 3)); //AWA
        p2.setLayout(new BorderLayout());
        p3.setLayout(new GridLayout(1, 3));
        pDroit.setLayout(new GridLayout(3,1));// AWA

        // ajout des objets graphqiues dans les panneaux
        p0.add(nameECE);
        p0.add(nameECETexte);
        p0.add(passwdECE);
        p0.add(passwdECETexte);
        p0.add(loginBDD);
        p0.add(loginBDDTexte);
        p0.add(passwdBDD);
        p0.add(passwdBDDTexte);
        p0.add(connect);
        p0.add(nameBDD);
        p0.add(nameBDDTexte);
        p0.add(local);        
        p1.add(tab);
        p1.add(lignes);
        p1.add(req);
        p1.add(res);
        nord.add("North", p0);
        nord.add("North", p1);
              
        p2.add(listeDeTables, BorderLayout.WEST);
        p2.add(fenetreLignes, BorderLayout.CENTER);
        pChoix.add(insertion);
        pChoix.add(suppression);
        pChoix.add(maj);
        pChoix.add(OK);
        pDroit.add(pChoix);
        pDroit.add(pChgt);
        pDroit.add(fenetreRes);
        p2.add(pDroit, BorderLayout.NORTH);
        
        //p2.add(listeDeRequetes);    
                
        p3.add(requeteLabel);
        p3.add(requeteTexte);
        p3.add(exec);

        // ajout des listeners
        connect.addActionListener(this);
        exec.addActionListener(this);
        local.addActionListener(this);
        
        insertion.addActionListener(this);
        suppression.addActionListener(this);
        maj.addActionListener(this);
        OK.addActionListener(this);
        
        nameECETexte.addActionListener(this);
        passwdECETexte.addActionListener(this);
        loginBDDTexte.addActionListener(this);
        passwdBDDTexte.addActionListener(this);
        
        listeDeTables.addItemListener(this);
        listeDeRequetes.addItemListener(this);

        // couleurs des objets graphiques
        tab.setBackground(Color.BLUE);
        lignes.setBackground(Color.YELLOW);
        req.setBackground(Color.RED);
        res.setBackground(Color.BLUE);
        
        p1.setBackground(Color.LIGHT_GRAY);
        listeDeTables.setBackground(Color.LIGHT_GRAY);
        fenetreLignes.setBackground(Color.PINK);
        pChoix.setBackground(Color.BLUE);
        pChgt.setBackground(Color.ORANGE);
        fenetreRes.setBackground(Color.GREEN);
        
        //listeDeRequetes.setBackground(Color.BLUE);
        //pChgt.setBackground(Color.BLUE);    

        // disposition geographique des panneaux
        add("North", nord);
        add("Center", p2);
        add("South", p3);
        
        
        //DOCTEUR
        numero=new JLabel("numero");
        numeroText=new JTextField(8);
        specialite=new JLabel("specialite");
        specialiteText=new JTextField(8);
        //CHAMBRE
        code_service=new JLabel("code_service");
        code_serviceText=new JTextField(8);
        no_chambre=new JLabel("no_chambre");
        no_chambreText=new JTextField(8);
        surveillant=new JLabel("surveillant");
        surveillantText=new JTextField(8);
        nb_lits=new JLabel("nb_lits");
        nb_litsText=new JTextField(8);
        //EMPLOYE
        //meme numero que dans DOCTEUR
        nom=new JLabel("nom");
        nomText=new JTextField(8);
        prenom=new JLabel("prenom");
        prenomText=new JTextField(8);
        adresse=new JLabel("adresse");
        adresseText=new JTextField(8);
        tel=new JLabel("nb_lits");
        telText=new JTextField(8);
        //HOSPITALISATION
        no_malade=new JLabel("no_malade");
        no_maladeText=new JTextField(8);
        //meme code_service que dans CHAMBRE
        //meme no_chambre que dans CHAMBRE
        lit=new JLabel("lit");
        litText=new JTextField(8);
        //INFIRMIER
        //meme numero que dans DOCTEUR
        //meme code_service que dans CHAMBRE
        rotation=new JLabel("rotation");
        rotationText=new JTextField(8);
        salaire=new JLabel("salaire");
        salaireText=new JTextField(8);
        //MALADE
        //meme numero que dans DOCTEUR
        //meme nom que dans EMPLOYE
        //meme prenom que dans EMPLOYE
        //meme adresse que dans EMPLOYE
        //meme tel que dans EMPLOYE
        mutuelle=new JLabel("mutuelle");
        mutuelleText=new JTextField(8);
        //SERVICE
        code=new JLabel("code");
        codeText=new JTextField(8);
        //meme nom que dans EMPLOYE
        batiment=new JLabel("batiment");
        batimentText=new JTextField(8);
        directeur=new JLabel("directeur");
        directeurText=new JTextField(8);
        //SOIGNE
        no_docteur=new JLabel("no_docteur");
        no_docteurText=new JTextField(8);
        //meme no_malade que dans HOSPITALISATION


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
    private void remplirTables() {
        maconnexion.ajouterTable("service");
        maconnexion.ajouterTable("chambre");
        maconnexion.ajouterTable("employe");
        maconnexion.ajouterTable("docteur");
        maconnexion.ajouterTable("infirmier");
        maconnexion.ajouterTable("malade");
        maconnexion.ajouterTable("hospitalisation");
        maconnexion.ajouterTable("soigne");
    }

    /**
     * Méthode privée qui initialise la liste des requetes de selection
     */
    private void remplirRequetes() {
        maconnexion.ajouterRequete("SELECT no_chambre, nb_lits FROM chambre WHERE nb_lits >=2;");
        
    }

    /**
     * Méthode privée qui initialise la liste des requetes de MAJ
     */
    private void remplirRequetesMaj() {
        // Requêtes d'insertion
        //maconnexion.ajouterRequeteMaj("INSERT INTO Dept (deptno,dname,loc) VALUES (50,'ECE','Paris');");
        maconnexion.ajouterRequeteMaj("INSERT INTO docteur(numero,specialite) VALUES(999,'Cardiologue');"); //AWA marche pas

        
        // Requêtes de modification
        maconnexion.ajouterRequeteMaj("UPDATE Dept SET loc='Eiffel' WHERE loc='Paris';");

        // Requêtes de suppression
        maconnexion.ajouterRequeteMaj("DELETE FROM Dept WHERE loc='Eiffel';");

    }

    /**
     *
     * Afficher les tables
     */
    public void afficherTables() {
        for (String table : maconnexion.tables) {
            listeDeTables.add(table);
        }
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
     *AWA
     * Afficher les requetes de MAJ dans la fenetre
     */
    public void afficherRequetesMaj() {
        for (String requeteMaj : maconnexion.requetesMaj) {
            listeDeRequetes.add(requeteMaj);
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
        if (source == connect) {
            ArrayList<String> liste;
            String passwdECEString = new String(passwdECETexte.getPassword());
            String passwdBDDString = new String(passwdBDDTexte.getPassword());
            try {
                try {
                    // tentative de connexion si les 4 attributs sont remplis
                    maconnexion = new Connexion(nameECETexte.getText(), passwdECEString,
                            loginBDDTexte.getText(), passwdBDDString);

                    // effacer les listes de tables et de requêtes
                    listeDeTables.removeAll();
                    listeDeRequetes.removeAll();

                    // initialisation de la liste des requetes de selection et de MAJ
                    remplirTables();
                    remplirRequetes();
                    remplirRequetesMaj();

                    // afficher la liste de tables et des requetes
                    afficherTables();
                    afficherRequetes();
                    afficherRequetesMaj(); //AWA

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
                    
                } catch (ClassNotFoundException cnfe) {
                    System.out.println("Connexion echouee : probleme de classe");
                    cnfe.printStackTrace();
                }
            } catch (SQLException e) {
                System.out.println("Connexion echouee : probleme SQL");
                e.printStackTrace();
            }
        } else if (source == local) {
            ArrayList<String> liste;
            try {
                try {
                    // tentative de connexion si les 4 attributs sont remplis
                    maconnexion = new Connexion(nameBDDTexte.getText(), "root", "root"); //MODIFIE CAR MAC

                    // effacer les listes de tables et de requêtes
                    listeDeTables.removeAll();
                    listeDeRequetes.removeAll();

                    // initialisation de la liste des tables + des requetes de selection et de MAJ
                    remplirTables();
                    remplirRequetes();
                    remplirRequetesMaj();

                    // afficher la liste de tables et des requetes
                    afficherTables();
                    afficherRequetes();
                    afficherRequetesMaj(); //AWA

                    /* se positionner sur la première table et requête de selection
                    //listeDeTables.select(0);
                    //listeDeRequetes.select(0);

                    // afficher les champs de la table sélectionnée
                    String nomTable = listeDeTables.getSelectedItem();
                    // recuperer les lignes de la table selectionnee
                    afficherLignes(nomTable);

                    // recuperer la liste des lignes de la requete selectionnee
                    //String requeteSelectionnee = listeDeRequetes.getSelectedItem();
                    // afficher les résultats de la requete selectionnee
                    //afficherRes(requeteSelectionnee);*/
                  
                } catch (ClassNotFoundException cnfe) {
                    System.out.println("Connexion echouee : probleme de classe");
                    cnfe.printStackTrace();
                }
            } catch (SQLException e) {
                System.out.println("Connexion echouee : probleme SQL");
                e.printStackTrace();
            }         
        }
        else if(source==OK) //PROCEDER A UNE MAJ
        {
            //DOCTEUR
            if(listeDeTables.getSelectedItem()=="docteur" && insertion.isSelected() && !(suppression.isSelected()) && !(maj.isSelected()))
            {
                try
                {
                    int numeroVal=Integer.parseInt(numeroText.getText());
                    String specialiteVal=specialiteText.getText();
                    //maconnexion.executeUpdate("INSERT INTO docteur(numero, specialite) VALUES (25, 'Cardiologue');"); //REQUETE VALIDE
                    maconnexion.executeUpdate("INSERT INTO docteur(numero, specialite) VALUES('" + numeroVal + "', '" + specialiteVal + "');"); //REQUETE VALIDE
                   
                    fenetreRes.setText("");
                    fenetreRes.append("insertion reussie");
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                    fenetreRes.setText("");
                    fenetreRes.append("probleme insertion");
                }
                insertion.setSelected(false);
            }
            if(listeDeTables.getSelectedItem()=="docteur" && suppression.isSelected() && !(insertion.isSelected()) && !(maj.isSelected()))
            {
                try
                {
                    int numeroVal=Integer.parseInt(numeroText.getText());
                    maconnexion.executeUpdate("DELETE from docteur WHERE numero= '" + numeroVal + "';");
                    fenetreRes.setText("");
                    fenetreRes.append("suppression reussie");
                }
                catch(SQLException ex) 
                {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                    fenetreRes.setText("");
                    fenetreRes.append("probleme suppression");
                }
                suppression.setSelected(false);
            }
            if(listeDeTables.getSelectedItem()=="docteur" && maj.isSelected() && !(insertion.isSelected()) && !(suppression.isSelected()))
            {
                try
                {
                    int numeroVal=Integer.parseInt(numeroText.getText());
                    String specialiteVal=specialiteText.getText();
                    maconnexion.executeUpdate("UPDATE docteur SET specialite= '" + specialiteVal + "' WHERE numero= '" + numeroVal + "';");
                    fenetreRes.setText("");
                    fenetreRes.append("modification reussie");
                }
                catch(SQLException ex) 
                {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                    fenetreRes.setText("");
                    fenetreRes.append("probleme modification");
                }
                maj.setSelected(false);
            }
            //CHAMBRE
            if(listeDeTables.getSelectedItem()=="chambre" && insertion.isSelected() && !(suppression.isSelected()) && !(maj.isSelected()))
            {
                try
                {
                    String code_serviceVal=code_serviceText.getText();
                    int no_chambreVal=Integer.parseInt(no_chambreText.getText());
                    int surveillantVal=Integer.parseInt(surveillantText.getText());
                    int nb_litsVal=Integer.parseInt(nb_litsText.getText());
                    
                    maconnexion.executeUpdate("INSERT INTO chambre(code_service, no_chambre, surveillant, nb_lits) VALUES('" + code_serviceVal + "', '" + no_chambreVal + "', '" + surveillantVal + "', '" + nb_litsVal + "');"); 
                   
                    fenetreRes.setText("");
                    fenetreRes.append("insertion reussie");
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                    fenetreRes.setText("");
                    fenetreRes.append("probleme insertion");
                }
                insertion.setSelected(false);
            }
            if(listeDeTables.getSelectedItem()=="chambre" && suppression.isSelected() && !(insertion.isSelected()) && !(maj.isSelected()))
            {
                try
                {
                    String code_serviceVal=code_serviceText.getText();
                    int no_chambreVal=Integer.parseInt(no_chambreText.getText());
                    maconnexion.executeUpdate("DELETE from chambre WHERE code_service= '" + code_serviceVal + "' AND no_chambre= '" + no_chambreVal + "';");
                    fenetreRes.setText("");
                    fenetreRes.append("suppression reussie");
                }
                catch(SQLException ex) 
                {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                    fenetreRes.setText("");
                    fenetreRes.append("probleme suppression");
                }
                suppression.setSelected(false);
            }
            if(listeDeTables.getSelectedItem()=="chambre" && maj.isSelected() && !(insertion.isSelected()) && !(suppression.isSelected()))
            {
                String code_serviceVal=code_serviceText.getText();
                int no_chambreVal=Integer.parseInt(no_chambreText.getText());
                //int surveillantVal=Integer.parseInt(surveillantText.getText());
                //int nb_litsVal=Integer.parseInt(nb_litsText.getText());
              
                if(!"".equals(surveillantText.getText()) && "".equals(nb_litsText.getText()))
                {
                    try
                    {
                        int surveillantVal=Integer.parseInt(surveillantText.getText());
                        maconnexion.executeUpdate("UPDATE chambre SET surveillant= '" + surveillantVal + "' WHERE (code_service= '" + code_serviceVal + "' AND no_chambre= '" + no_chambreVal + "');");
                        fenetreRes.setText("");
                        fenetreRes.append("modification reussie");                   
                    }
                    catch(SQLException ex) 
                    {
                        Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                        fenetreRes.setText("");
                        fenetreRes.append("probleme modification");
                    }
                }
                if("".equals(surveillantText.getText()) && !"".equals(nb_litsText.getText()))
                {
                    try
                    {
                        int nb_litsVal=Integer.parseInt(nb_litsText.getText());
                        maconnexion.executeUpdate("UPDATE chambre SET nb_lits= '" + nb_litsVal + "' WHERE (code_service= '" + code_serviceVal + "' AND no_chambre= '" + no_chambreVal + "');");
                        fenetreRes.setText("");
                        fenetreRes.append("modification reussie");
                    }
                    catch(SQLException ex) 
                    {
                        Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                        fenetreRes.setText("");
                        fenetreRes.append("probleme modification");
                    }
                }
                if(!"".equals(surveillantText.getText()) && !"".equals(nb_litsText.getText()))
                {
                    try
                    {
                        int surveillantVal=Integer.parseInt(surveillantText.getText());
                        int nb_litsVal=Integer.parseInt(nb_litsText.getText());
                        maconnexion.executeUpdate("UPDATE chambre SET surveillant= '" + surveillantVal + "', nb_lits= '" + nb_litsVal + "' WHERE (code_service= '" + code_serviceVal + "' AND no_chambre= '" + no_chambreVal + "');");
                        fenetreRes.setText("");
                        fenetreRes.append("modification reussie");
                    }
                    catch(SQLException ex) 
                    {
                        Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                        fenetreRes.setText("");
                        fenetreRes.append("probleme modification");
                    }
                }
                maj.setSelected(false);
            }
        }
       
     
        else if (source == exec) {
            String requeteSelectionnee = requeteTexte.getText(); // récupérer le texte de la requête

            // effacer les résultats
            fenetreRes.removeAll();

            try {
                // afficher les résultats de la requete selectionnee
                if (afficherRes(requeteSelectionnee) != null) {
                    maconnexion.ajouterRequete(requeteSelectionnee);
                    listeDeRequetes.removeAll();
                    afficherRequetes();
                }

            } catch (SQLException ex) {

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
        if (evt.getSource() == listeDeRequetes) 
        {
            // recuperer la liste des lignes de la requete selectionnee
            String requeteSelectionnee = listeDeRequetes.getSelectedItem();
            try {
                afficherRes(requeteSelectionnee);
            } catch (SQLException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if (evt.getSource() == listeDeTables) {
            // afficher les lignes de la table sélectionnée
            String nomTable = listeDeTables.getSelectedItem();
            afficherLignes(nomTable);
            
            if("docteur".equals(nomTable))
            {
                pChgt.removeAll();
                pChgt.setVisible(false);
                fenetreRes.setText("");
                pChgt.add(numero);
                pChgt.add(numeroText);
                pChgt.add(specialite);
                pChgt.add(specialiteText);
                pChgt.setVisible(true);
                fenetreRes.append("Insertion: renseignez tous les champs\nSuppression: renseignez le numero\n"
                        + "Mise a jour: renseignez le numero du docteur puis entrez la nouvelle specialite");
            }
            if("chambre".equals(nomTable))
            {
                pChgt.removeAll();
                pChgt.setVisible(false);                
                fenetreRes.setText("");
                pChgt.add(code_service);
                pChgt.add(code_serviceText);
                pChgt.add(no_chambre);
                pChgt.add(no_chambreText);
                pChgt.add(surveillant);
                pChgt.add(surveillantText);
                pChgt.add(nb_lits);
                pChgt.add(nb_litsText);
                pChgt.setVisible(true);                
                fenetreRes.append("Insertion:renseignez tous les champs\nSuppression: renseignez le numero de la chambre "
                        + "et le code service\nMise a jour: renseignez le numero de la chambre et le code service "
                        + "puis faites la (les) modification(s) souhaitee(s)");
            }
            if("employe".equals(nomTable))
            {
                pChgt.removeAll();
                pChgt.setVisible(false);                
                fenetreRes.setText("");
                pChgt.add(numero);
                pChgt.add(numeroText);
                pChgt.add(nom);
                pChgt.add(nomText);
                pChgt.add(prenom);
                pChgt.add(prenomText);
                pChgt.add(adresse);
                pChgt.add(adresseText);
                pChgt.add(tel);
                pChgt.add(telText);
                pChgt.setVisible(true);                
                fenetreRes.append("Insertion:renseignez tous les champs\nSuppression: renseignez le nom, le prenom "
                        + "et le tel\nMise a jour: renseignez le nom, le prenom et le tel "
                        + "puis faites la (les) modification(s) souhaitee(s)"); //Y REFLECHIR
            }
            if("hospitalisation".equals(nomTable))
            {
                pChgt.removeAll();
                pChgt.setVisible(false);                
                fenetreRes.setText("");
                pChgt.add(no_malade);
                pChgt.add(no_maladeText);
                pChgt.add(code_service);
                pChgt.add(code_serviceText);
                pChgt.add(no_chambre);
                pChgt.add(no_chambreText);
                pChgt.add(lit);
                pChgt.add(litText);
                pChgt.setVisible(true);                
                fenetreRes.append("Insertion:renseignez tous les champs\nSuppression: renseignez le no_malade\n"
                        + "Mise a jour: renseignez le no_malade puis faites la (les) modification(s) souhaitee(s)"); //Y REFLECHIR
            }
            if("infirmier".equals(nomTable))
            {
                pChgt.removeAll();
                pChgt.setVisible(false);                
                fenetreRes.setText("");
                pChgt.add(numero);
                pChgt.add(numeroText);
                pChgt.add(code_service);
                pChgt.add(code_serviceText);
                pChgt.add(rotation);
                pChgt.add(rotationText);
                pChgt.add(salaire);
                pChgt.add(salaireText);
                pChgt.setVisible(true);                
                fenetreRes.append("Insertion:renseignez tous les champs\nSuppression: renseignez le numero\n"
                        + "Mise a jour: renseignez le numero puis faites la (les) modification(s) souhaitee(s)"); //Y REFLECHIR
            }
            if("malade".equals(nomTable))
            {
                pChgt.removeAll();
                pChgt.setVisible(false);                
                fenetreRes.setText("");
                pChgt.add(numero);
                pChgt.add(numeroText);
                pChgt.add(nom);
                pChgt.add(nomText);
                pChgt.add(prenom);
                pChgt.add(prenomText);
                pChgt.add(adresse);
                pChgt.add(adresseText);
                pChgt.add(tel);
                pChgt.add(telText);
                pChgt.add(mutuelle);
                pChgt.add(mutuelleText);
                pChgt.setVisible(true);                
                fenetreRes.append("Insertion:renseignez tous les champs\nSuppression: renseignez le nom, le prenom "
                        + "et le tel\nMise a jour: renseignez le nom, le prenom et le tel "
                        + "puis faites la (les) modification(s) souhaitee(s)"); //Y REFLECHIR
            }
            if("service".equals(nomTable))
            {
                pChgt.removeAll();
                pChgt.setVisible(false);                
                fenetreRes.setText("");
                pChgt.add(code);
                pChgt.add(codeText);
                pChgt.add(nom);
                pChgt.add(nomText);
                pChgt.add(batiment);
                pChgt.add(batimentText);
                pChgt.add(directeur);
                pChgt.add(directeurText);
                pChgt.setVisible(true);                
                fenetreRes.append("Insertion:renseignez tous les champs\nSuppression: renseignez le code\n"
                        + "Mise a jour: renseignez le code puis faites la (les) modification(s) souhaitee(s)"); //Y REFLECHIR
            }
            if("soigne".equals(nomTable))
            {
                pChgt.removeAll();
                pChgt.setVisible(false);                
                fenetreRes.setText("");
                pChgt.add(no_docteur);
                pChgt.add(no_docteurText);
                pChgt.add(no_malade);
                pChgt.add(no_maladeText);
                pChgt.setVisible(true);                
                fenetreRes.append("Insertion:renseignez tous les champs\nSuppression: renseignez le code\n"
                        + "Mise a jour: renseignez le code puis faites la (les) modification(s) souhaitee(s)"); //Y REFLECHIR
            }
        }
    }
    
    
   
}
