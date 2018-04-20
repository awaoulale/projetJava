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

    public Connexion maconnexion;
    private final JLabel tab, req, res, lignes;
    public JLabel nameECE, passwdECE, loginBDD, passwdBDD, nameBDD, requeteLabel;
    public JTextField nameECETexte, loginBDDTexte, requeteTexte, nameBDDTexte;
    public JPasswordField passwdECETexte, passwdBDDTexte;
    public JButton connect, exec, local,recherche,maj,camembert;
    private final java.awt.List listeDeTables, listeDeRequetes;
    private final JTextArea fenetreLignes, fenetreRes;
    private final JPanel p0, p1, nord, p2, p3;

    /**
     * Constructeur qui initialise tous les objets graphiques de la fenetre
     */
    public Fenetre() {

        // creation par heritage de la fenetre
        super("Page d'accueil");

        // mise en page (layout) de la fenetre visible
        setLayout(new BorderLayout());
        setBounds(0, 0, 400, 400);
        setResizable(true);
        setVisible(true);

        // creation des boutons
        connect = new JButton("Connexion ECE");
        local = new JButton("Connexion locale");
        exec = new JButton("Executer");
        recherche = new JButton("Rechercher");
        maj = new JButton("Mise à jour");
        camembert = new JButton("Reporting");

        // creation des listes pour les tables et les requetes
        listeDeTables = new java.awt.List(10, false);
        listeDeRequetes = new java.awt.List(10, false);

        // creation des textes
        nameECETexte = new JTextField();
        passwdECETexte = new JPasswordField(8);
        loginBDDTexte = new JTextField();
        passwdBDDTexte = new JPasswordField(8);
        nameBDDTexte = new JTextField("hopital");
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

        // mise en page des panneaux
        
       //BoxLayout g1 = new BoxLayout(p2, BoxLayout.LINE_AXIS);
        //GridLayout g1 = new GridLayout (1,3);
         //p1.setLayout(g1);
       //g1.setHgap(50); // pixels d'espace entre les colonnes 
       //g1.setVgap(5); // 5 pixels d'espace entre les lignes 
       //p1.setLayout(new BoxLayout(p1,BoxLayout.PAGE_AXIS));
        //p1.setLayout(g1)
        
        
        p0.setLayout(new GridLayout(1, 11));
        nord.setLayout(new GridLayout(2, 1));
        
        //p2.setLayout(new GridLayout(1, 4));
        //p3.setLayout(new GridLayout(1, 3));
        
        camembert.setMinimumSize(new Dimension(300,125));
        camembert.setPreferredSize(new Dimension(300,125));
        camembert.setMaximumSize(new Dimension(300,125));
        
        recherche.setMinimumSize(new Dimension(300,125));
        recherche.setPreferredSize(new Dimension(300,125));
        recherche.setMaximumSize(new Dimension(300,125));
        
        maj.setMinimumSize(new Dimension(300,125));
        maj.setPreferredSize(new Dimension(300,125));
        maj.setMaximumSize(new Dimension(300,125));
        
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
        
        p1.add(camembert);
        p1.add(recherche);
        p1.add(maj);
//        p1.add(res);
        nord.add("North", p0);
        //nord.add("North", p1);
//        p2.add(listeDeTables);
//        p2.add(fenetreLignes);
//        p2.add(listeDeRequetes);
//        p2.add(fenetreRes);
  //      p3.add(requeteLabel);
   //     p3.add(requeteTexte);
    //    p3.add(exec);

        // ajout des listeners
        connect.addActionListener(this);
        exec.addActionListener(this);
        local.addActionListener(this);
        nameECETexte.addActionListener(this);
        passwdECETexte.addActionListener(this);
        loginBDDTexte.addActionListener(this);
        passwdBDDTexte.addActionListener(this);
       // listeDeTables.addItemListener(this);
        //listeDeRequetes.addItemListener(this);

        // couleurs des objets graphiques
        tab.setBackground(Color.MAGENTA);
        lignes.setBackground(Color.MAGENTA);
        req.setBackground(Color.MAGENTA);
        res.setBackground(Color.MAGENTA);
        fenetreRes.setBackground(Color.WHITE);
        p1.setBackground(Color.LIGHT_GRAY);
        p0.setBackground(Color.LIGHT_GRAY);


        // disposition geographique des panneaux
        add("North", nord);
        add("South",p1);
        //add("Center", p2);
       // add("South", p3);

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
                   // remplirTables();
                   // remplirRequetes();
                   // remplirRequetesMaj();

                    // afficher la liste de tables et des requetes
                   // afficherTables();
                   // afficherRequetes();

                    // se positionner sur la première table et requête de selection
                    listeDeTables.select(0);
                    listeDeRequetes.select(0);

                    // afficher les champs de la table sélectionnée
                    String nomTable = listeDeTables.getSelectedItem();

                    // recuperer les lignes de la table selectionnee
                    //afficherLignes(nomTable);

                    // recuperer la liste des lignes de la requete selectionnee
                    String requeteSelectionnee = listeDeRequetes.getSelectedItem();

                    // afficher les résultats de la requete selectionnee
                    //afficherRes(requeteSelectionnee);
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
                    maconnexion = new Connexion(nameBDDTexte.getText(), "root", "");

                    // effacer les listes de tables et de requêtes
                    listeDeTables.removeAll();
                    listeDeRequetes.removeAll();

                    // initialisation de la liste des requetes de selection et de MAJ
                    //remplirTables();
                    //remplirRequetes();
                    //remplirRequetesMaj();

                    // afficher la liste de tables et des requetes
                    //afficherTables();
                    //afficherRequetes();

                    // se positionner sur la première table et requête de selection
                    listeDeTables.select(0);
                    listeDeRequetes.select(0);

                    // afficher les champs de la table sélectionnée
                    String nomTable = listeDeTables.getSelectedItem();

                    // recuperer les lignes de la table selectionnee
                    //afficherLignes(nomTable);

                    // recuperer la liste des lignes de la requete selectionnee
                    String requeteSelectionnee = listeDeRequetes.getSelectedItem();

                    // afficher les résultats de la requete selectionnee
                    //afficherRes(requeteSelectionnee);
                } catch (ClassNotFoundException cnfe) {
                    System.out.println("Connexion echouee : probleme de classe");
                    cnfe.printStackTrace();
                }
            } catch (SQLException e) {
                System.out.println("Connexion echouee : probleme SQL");
                e.printStackTrace();
            }
        } else if (source == exec) {
            String requeteSelectionnee = requeteTexte.getText(); // récupérer le texte de la requête

            // effacer les résultats
            fenetreRes.removeAll();

//            try {
//                // afficher les résultats de la requete selectionnee
//                if (afficherRes(requeteSelectionnee) != null) {
//                    maconnexion.ajouterRequete(requeteSelectionnee);
//                    listeDeRequetes.removeAll();
//                    afficherRequetes();
//                }
//
//            } catch (SQLException ex) {

            }

        }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }

    /**
     *
     * Pour gerer les actions sur items d'une liste on utilise la methode
     * itemStateChanged
     */
//    @Override
//    @SuppressWarnings("CallToThreadDumpStack")
//    public void itemStateChanged(ItemEvent evt) {
//        // sélection d'une requete et afficher ses résultats
//        if (evt.getSource() == listeDeRequetes) {
//            // recuperer la liste des lignes de la requete selectionnee
//            String requeteSelectionnee = listeDeRequetes.getSelectedItem();
//            try {
//                afficherRes(requeteSelectionnee);
//            } catch (SQLException ex) {
//                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } else if (evt.getSource() == listeDeTables) {
//            // afficher les lignes de la table sélectionnée
//            String nomTable = listeDeTables.getSelectedItem();
//            afficherLignes(nomTable);
//        }
//    }

