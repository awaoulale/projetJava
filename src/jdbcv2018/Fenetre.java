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
        pChoix.setBackground(Color.RED);
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
        tel=new JLabel("tel");
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
        else if(source==OK) //PROCEDER A UNE MAJ (insertion, suppression ou modification)
        {
            //DOCTEUR
            if("docteur".equals(listeDeTables.getSelectedItem()) && insertion.isSelected() && !(suppression.isSelected()) && !(maj.isSelected()))
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
            if("docteur".equals(listeDeTables.getSelectedItem()) && suppression.isSelected() && !(insertion.isSelected()) && !(maj.isSelected()))
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
            if("docteur".equals(listeDeTables.getSelectedItem()) && maj.isSelected() && !(insertion.isSelected()) && !(suppression.isSelected()))
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
            if("chambre".equals(listeDeTables.getSelectedItem()) && insertion.isSelected() && !(suppression.isSelected()) && !(maj.isSelected()))
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
            if("chambre".equals(listeDeTables.getSelectedItem()) && suppression.isSelected() && !(insertion.isSelected()) && !(maj.isSelected()))
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
            if("chambre".equals(listeDeTables.getSelectedItem()) && maj.isSelected() && !(insertion.isSelected()) && !(suppression.isSelected()))
            {
                String code_serviceVal=code_serviceText.getText();
                int no_chambreVal=Integer.parseInt(no_chambreText.getText());
              
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
            //EMPLOYE
            if("employe".equals(listeDeTables.getSelectedItem()) && insertion.isSelected() && !(suppression.isSelected()) && !(maj.isSelected()))
            {
                try
                {
                    int numeroVal=Integer.parseInt(numeroText.getText());
                    String nomVal=nomText.getText();
                    String prenomVal=prenomText.getText();
                    String adresseVal=adresseText.getText();
                    String telVal=telText.getText();
                    maconnexion.executeUpdate("INSERT INTO employe(numero, nom, prenom, adresse, tel) VALUES('" + numeroVal + "', '" + nomVal + "', '" + prenomVal + "', '" + adresseVal + "', '" + telVal + "');");
                   
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
            if("employe".equals(listeDeTables.getSelectedItem()) && suppression.isSelected() && !(insertion.isSelected()) && !(maj.isSelected()))
            {
                try
                {
                    int numeroVal=Integer.parseInt(numeroText.getText());
                    maconnexion.executeUpdate("DELETE from employe WHERE numero= '" + numeroVal + "';");
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
            if("employe".equals(listeDeTables.getSelectedItem()) && maj.isSelected() && !(insertion.isSelected()) && !(suppression.isSelected()))
            {
                int numeroVal=Integer.parseInt(numeroText.getText());
              
                if(!"".equals(adresseText.getText()) && "".equals(telText.getText()))
                {
                    try
                    {
                        String adresseVal=adresseText.getText();
                        maconnexion.executeUpdate("UPDATE employe SET adresse= '" + adresseVal + "' WHERE numero= '" + numeroVal + "';");
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
                if("".equals(adresseText.getText()) && !"".equals(telText.getText()))
                {
                    try
                    {
                        String telVal=telText.getText();
                        maconnexion.executeUpdate("UPDATE employe SET tel= '" + telVal + "' WHERE numero= '" + numeroVal + "';");
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
                if(!"".equals(adresseText.getText()) && !"".equals(telText.getText()))
                {
                    try
                    {
                        String adresseVal=adresseText.getText();
                        String telVal=telText.getText();
                        maconnexion.executeUpdate("UPDATE employe SET adresse= '" + adresseVal + "', tel= '" + telVal + "' WHERE numero= '" + numeroVal + "';");
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
            //HOSPITALISATION
            if("hospitalisation".equals(listeDeTables.getSelectedItem()) && insertion.isSelected() && !(suppression.isSelected()) && !(maj.isSelected()))
            {
                try
                {
                    int no_maladeVal=Integer.parseInt(no_maladeText.getText());
                    String code_serviceVal=code_serviceText.getText();
                    int no_chambreVal=Integer.parseInt(no_chambreText.getText());
                    int litVal=Integer.parseInt(litText.getText());
                    maconnexion.executeUpdate("INSERT INTO hospitalisation(no_malade, code_service, no_chambre, lit) VALUES('" + no_maladeVal + "', '" + code_serviceVal + "', '" + no_chambreVal + "', '" + litVal + "');");
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
            if("hospitalisation".equals(listeDeTables.getSelectedItem()) && suppression.isSelected() && !(insertion.isSelected()) && !(maj.isSelected()))
            {
                try
                {
                    int no_maladeVal=Integer.parseInt(no_maladeText.getText());
                    maconnexion.executeUpdate("DELETE from hospitalisation WHERE no_malade= '" + no_maladeVal + "';");
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
            if("hospitalisation".equals(listeDeTables.getSelectedItem()) && maj.isSelected() && !(insertion.isSelected()) && !(suppression.isSelected()))
            {
                int no_maladeVal=Integer.parseInt(no_maladeText.getText());
              
                if(!"".equals(litText.getText()) && "".equals(no_chambreText.getText()) && "".equals(code_serviceText.getText()))
                {
                    try
                    {
                        int litVal=Integer.parseInt(litText.getText());
                        maconnexion.executeUpdate("UPDATE hospitalisation SET lit= '" + litVal + "' WHERE no_malade= '" + no_maladeVal + "';");
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
                if(!"".equals(litText.getText()) && !"".equals(no_chambreText.getText()) && "".equals(code_serviceText.getText()))
                {
                    try
                    {
                        int no_chambreVal=Integer.parseInt(no_chambreText.getText());
                        int litVal=Integer.parseInt(litText.getText());
                        maconnexion.executeUpdate("UPDATE hospitalisation SET no_chambre='" + no_chambreVal + "', lit= '" + litVal + "' WHERE no_malade= '" + no_maladeVal + "';");
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
                if(!"".equals(litText.getText()) && !"".equals(no_chambreText.getText()) && !"".equals(code_serviceText.getText()))
                {
                    try
                    {
                        String code_serviceVal=code_serviceText.getText();
                        int no_chambreVal=Integer.parseInt(no_chambreText.getText());
                        int litVal=Integer.parseInt(litText.getText());
                        maconnexion.executeUpdate("UPDATE hospitalisation SET code_service='" + code_serviceVal + "', no_chambre='" + no_chambreVal + "', lit= '" + litVal + "' WHERE no_malade= '" + no_maladeVal + "';");
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
            //INFIRMIER
            if("infirmier".equals(listeDeTables.getSelectedItem()) && insertion.isSelected() && !(suppression.isSelected()) && !(maj.isSelected()))
            {
                try
                {
                    int numeroVal=Integer.parseInt(numeroText.getText());
                    String code_serviceVal=code_serviceText.getText();
                    String rotationVal=rotationText.getText();
                    float salaireVal=Float.parseFloat(salaireText.getText());
                    maconnexion.executeUpdate("INSERT INTO infirmier(numero, code_service, rotation, salaire) VALUES('" + numeroVal + "', '" + code_serviceVal + "', '" + rotationVal + "', '" + salaireVal + "');");
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
            if("infirmier".equals(listeDeTables.getSelectedItem()) && suppression.isSelected() && !(insertion.isSelected()) && !(maj.isSelected()))
            {
                try
                {
                    int numeroVal=Integer.parseInt(numeroText.getText());
                    maconnexion.executeUpdate("DELETE from infirmier WHERE numero= '" + numeroVal + "';");
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
            if("infirmier".equals(listeDeTables.getSelectedItem()) && maj.isSelected() && !(insertion.isSelected()) && !(suppression.isSelected()))
            {
                int numeroVal=Integer.parseInt(numeroText.getText());
              
                if(!"".equals(code_serviceText.getText()) && "".equals(rotationText.getText()) && "".equals(salaireText.getText()))
                {
                    try
                    {
                        String code_serviceVal=code_serviceText.getText();
                        maconnexion.executeUpdate("UPDATE infirmier SET code_service= '" + code_serviceVal + "' WHERE numero= '" + numeroVal + "';");
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
                if("".equals(code_serviceText.getText()) && !"".equals(rotationText.getText()) && "".equals(salaireText.getText()))
                {
                    try
                    {
                        String rotationVal=rotationText.getText();
                        maconnexion.executeUpdate("UPDATE infirmier SET rotation= '" + rotationVal + "' WHERE numero= '" + numeroVal + "';");
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
                if("".equals(code_serviceText.getText()) && "".equals(rotationText.getText()) && !"".equals(salaireText.getText()))
                {
                    try
                    {
                        float salaireVal=Float.parseFloat(salaireText.getText());
                        maconnexion.executeUpdate("UPDATE infirmier SET salaire= '" + salaireVal + "' WHERE numero= '" + numeroVal + "';");
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
                if(!"".equals(code_serviceText.getText()) && !"".equals(rotationText.getText()) && "".equals(salaireText.getText()))
                {
                    try
                    {
                        String code_serviceVal=code_serviceText.getText();
                        String rotationVal=rotationText.getText();
                        maconnexion.executeUpdate("UPDATE infirmier SET code_service= '" + code_serviceVal + "', rotation='" + rotationVal + "' WHERE numero= '" + numeroVal + "';");
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
                if("".equals(code_serviceText.getText()) && !"".equals(rotationText.getText()) && !"".equals(salaireText.getText()))
                {
                    try
                    {
                        String rotationVal=rotationText.getText();
                        float salaireVal=Float.parseFloat(salaireText.getText());
                        maconnexion.executeUpdate("UPDATE infirmier SET rotation= '" + rotationVal + "', salaire='" + salaireVal + "' WHERE numero= '" + numeroVal + "';");
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
                if(!"".equals(code_serviceText.getText()) && "".equals(rotationText.getText()) && !"".equals(salaireText.getText()))
                {
                    try
                    {
                        String code_serviceVal=code_serviceText.getText();
                        float salaireVal=Float.parseFloat(salaireText.getText());
                        maconnexion.executeUpdate("UPDATE infirmier SET code_service='" + code_serviceVal + "', salaire= '" + salaireVal + "' WHERE numero= '" + numeroVal + "';");
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
                if(!"".equals(code_serviceText.getText()) && !"".equals(rotationText.getText()) && !"".equals(salaireText.getText()))
                {
                    try
                    {
                        String code_serviceVal=code_serviceText.getText();
                        String rotationVal=rotationText.getText();
                        float salaireVal=Float.parseFloat(salaireText.getText());
                        maconnexion.executeUpdate("UPDATE infirmier SET code_service='" + code_serviceVal + "', rotation='" + rotationVal + "', salaire= '" + salaireVal + "' WHERE numero= '" + numeroVal + "';");
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
            //MALADE
            if("malade".equals(listeDeTables.getSelectedItem()) && insertion.isSelected() && !(suppression.isSelected()) && !(maj.isSelected()))
            {
                try
                {
                    int numeroVal=Integer.parseInt(numeroText.getText());
                    String nomVal=nomText.getText();
                    String prenomVal=prenomText.getText();
                    String adresseVal=adresseText.getText();
                    String telVal=telText.getText();
                    String mutuelleVal=mutuelleText.getText();
                    maconnexion.executeUpdate("INSERT INTO malade(numero, nom, prenom, adresse, tel, mutuelle) VALUES('" + numeroVal + "', '" + nomVal + "', '" + prenomVal + "', '" + adresseVal + "', '" + telVal + "', '" + mutuelleVal + "');");
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
            if("malade".equals(listeDeTables.getSelectedItem()) && suppression.isSelected() && !(insertion.isSelected()) && !(maj.isSelected()))
            {
                try
                {
                    int numeroVal=Integer.parseInt(numeroText.getText());
                    maconnexion.executeUpdate("DELETE from malade WHERE numero= '" + numeroVal + "';");
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
            if("malade".equals(listeDeTables.getSelectedItem()) && maj.isSelected() && !(insertion.isSelected()) && !(suppression.isSelected()))
            {
                int numeroVal=Integer.parseInt(numeroText.getText());
              
                if(!"".equals(adresseText.getText()) && "".equals(telText.getText()) && "".equals(mutuelleText.getText()))
                {
                    try
                    {
                        String adresseVal=adresseText.getText();
                        maconnexion.executeUpdate("UPDATE malade SET adresse= '" + adresseVal + "' WHERE numero= '" + numeroVal + "';");
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
                if("".equals(adresseText.getText()) && !"".equals(telText.getText()) && "".equals(mutuelleText.getText()))
                {
                    try
                    {
                        String telVal=telText.getText();
                        maconnexion.executeUpdate("UPDATE malade SET tel= '" + telVal + "' WHERE numero= '" + numeroVal + "';");
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
                if("".equals(adresseText.getText()) && "".equals(telText.getText()) && !"".equals(mutuelleText.getText()))
                {
                    try
                    {
                        String mutuelleVal=mutuelleText.getText();
                        maconnexion.executeUpdate("UPDATE malade SET mutuelle= '" + mutuelleVal + "' WHERE numero= '" + numeroVal + "';");
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
                if(!"".equals(adresseText.getText()) && !"".equals(telText.getText()) && "".equals(mutuelleText.getText()))
                {
                    try
                    {
                        String adresseVal=adresseText.getText();
                        String telVal=telText.getText();
                        maconnexion.executeUpdate("UPDATE malade SET adresse= '" + adresseVal + "', tel='" + telVal + "' WHERE numero= '" + numeroVal + "';");
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
                if("".equals(adresseText.getText()) && !"".equals(telText.getText()) && !"".equals(mutuelleText.getText()))
                {
                    try
                    {
                        String telVal=telText.getText();
                        String mutuelleVal=mutuelleText.getText();
                        maconnexion.executeUpdate("UPDATE malade SET tel= '" + telVal + "', mutuelle='" + mutuelleVal + "' WHERE numero= '" + numeroVal + "';");
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
                if(!"".equals(adresseText.getText()) && "".equals(telText.getText()) && !"".equals(mutuelleText.getText()))
                {
                    try
                    {
                        String adresseVal=adresseText.getText();
                        String mutuelleVal=mutuelleText.getText();
                        maconnexion.executeUpdate("UPDATE malade SET adresse= '" + adresseVal + "', mutuelle= '" + mutuelleVal + "' WHERE numero= '" + numeroVal + "';");
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
                if(!"".equals(adresseText.getText()) && !"".equals(telText.getText()) && !"".equals(mutuelleText.getText()))
                {
                    try
                    {
                        String adresseVal=adresseText.getText();
                        String telVal=telText.getText();
                        String mutuelleVal=mutuelleText.getText();
                        maconnexion.executeUpdate("UPDATE malade SET adresse= '" + adresseVal + "', tel='" + telVal + "', mutuelle= '" + mutuelleVal + "' WHERE numero= '" + numeroVal + "';");
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
            //SERVICE
            if("service".equals(listeDeTables.getSelectedItem()) && insertion.isSelected() && !(suppression.isSelected()) && !(maj.isSelected()))
            {
                try
                {
                    String codeVal=codeText.getText();
                    String nomVal=nomText.getText();
                    String batimentVal=batimentText.getText();
                    int directeurVal=Integer.parseInt(directeurText.getText());
                    maconnexion.executeUpdate("INSERT INTO service(code, nom, batiment, directeur) VALUES('" + codeVal + "', '" + nomVal + "', '" + batimentVal + "', '" + directeurVal + "');");
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
            if("service".equals(listeDeTables.getSelectedItem()) && suppression.isSelected() && !(insertion.isSelected()) && !(maj.isSelected()))
            {
                String codeVal=codeText.getText();
                try
                {
                    maconnexion.executeUpdate("DELETE from service WHERE code= '" + codeVal + "';");
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
            if("service".equals(listeDeTables.getSelectedItem()) && maj.isSelected() && !(insertion.isSelected()) && !(suppression.isSelected()))
            {
                String codeVal=codeText.getText();
              
                if(!"".equals(batimentText.getText()) && "".equals(directeurText.getText()))
                {
                    try
                    {
                        String batimentVal=batimentText.getText();
                        maconnexion.executeUpdate("UPDATE service SET batiment= '" + batimentVal + "' WHERE code= '" + codeVal + "';");
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
                if("".equals(batimentText.getText()) && !"".equals(directeurText.getText()))
                {
                    try
                    {
                        int directeurVal=Integer.parseInt(directeurText.getText());
                        maconnexion.executeUpdate("UPDATE service SET directeur= '" + directeurVal + "' WHERE code= '" + codeVal + "';");
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
                if(!"".equals(batimentText.getText()) && !"".equals(directeurText.getText()))
                {
                    try
                    {
                        String batimentVal=batimentText.getText();
                        int directeurVal=Integer.parseInt(directeurText.getText());
                        maconnexion.executeUpdate("UPDATE service SET batiment= '" + batimentVal + "', directeur='" + directeurVal + "' WHERE code= '" + codeVal + "';");
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
                fenetreRes.append("Insertion:renseignez tous les champs\nSuppression: renseignez le numero de l'employe\n"
                        + "Mise a jour: renseignez le numero de l'employe, puis vous pouvez entrer une nouvelle adresse et/ou un nouveau numero de tel");
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
                fenetreRes.append("Insertion:renseignez tous les champs (numero de malade existant mais non hospitalise)\nSuppression: renseignez le no_malade\n"
                        + "Mise a jour: renseignez le numero du malade puis 3 POSSIBILITES\n1) entrez uniquement un nouveau lit "
                        + "2) entrez une nouvelle chambre ansi qu'un nouveau lit "
                        + "3) entrez un nouveau code_service, une nouvelle chambre et un nouveau lit");
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
                fenetreRes.append("Insertion:renseignez tous les champs (numero d'infirmier correspondant a un employe existant)\nSuppression: renseignez le numero\n"
                        + "Mise a jour: renseignez le numero puis faites la (les) modification(s) souhaitee(s)");
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
                fenetreRes.append("Insertion:renseignez tous les champs\nSuppression: renseignez le numero du malade\n"
                        + "Mise a jour: renseignez le numero du malade puis entrez une nouvelle adresse et/ou un nouveau tel et/ou une nouvelle mutuelle");
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
                        + "Mise a jour: renseignez le code puis rentrez un nouveau batiment et/ou un nouveau directeur");
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
                fenetreRes.append("Insertion:renseignez le code, le batiment et le directeur\nSuppression: renseignez le code\n"
                        + "Mise a jour: renseignez le code puis entrez un nouveau batiment et/ou un nouveau directeur"); 
            }
        }
    }
    
    
   
}
