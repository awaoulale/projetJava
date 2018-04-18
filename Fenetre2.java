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
    //private final JLabel tab, req, res, lignes;
    public JLabel nameECE, passwdECE, loginBDD, passwdBDD, nameBDD, requeteLabel;
    public JTextField nameECETexte, loginBDDTexte, requeteTexte, nameBDDTexte;
    public JPasswordField passwdECETexte, passwdBDDTexte;
    public JButton connect, exec, local;
    private final java.awt.List listeDeTables, listeDeRequetes;
    private final JTextArea fenetreLignes, fenetreRes;
    private final JPanel p0, p1, nord, p2, p3, p4, p5, p6, p7, p8, p9,pA;
    
    //pour rechercher par chambre
    private final JTextField chambre; 
    private final JButton rechercheChambre;
    private final JRadioButton malades;
    private final JRadioButton batiments;
    private final JRadioButton litChambre;
    private final JRadioButton defautChambre;
    
    //pour rechercher par docteur
    private final JTextField docteur; 
    private final JButton rechercheDocteur;
    private final JRadioButton soigneDocteur;
    private final JRadioButton speDocteur;
    private final JRadioButton defautDocteur;
    private final JRadioButton salaireDocteur;

    //Pour rechercher par employe 
    private final JTextField employe;
    private final JButton rechercheEmploye;
    private final JRadioButton defautEmploye;
    private final JRadioButton salaireEmploye;
    private final JRadioButton fonctionEmploye;
    private final JRadioButton serviceEmploye;
    
    //Pour rechercher par infirmier
    private final JTextField infirmier;
    private final JButton rechercheInfirmier;
    private final JRadioButton defautInfirmier;
    private final JRadioButton salaireInfirmier;
    private final JRadioButton rotationInfirmier;
    private final JRadioButton serviceInfirmier;
    
    //Pour rechercher par malade
    private final JTextField malade;
    private final JButton rechercheMalade;
    private final JRadioButton defautMalade;
    private final JRadioButton mutuelleMalade;
    private final JRadioButton chambreMalade;
    private final JRadioButton docteurMalade;
    
    //Pour rechercher par service
    private final JTextField service;
    private final JButton rechercheService;
    private final JRadioButton defautService;
    private final JRadioButton codeService;
    private final JRadioButton batimentService;
    private final JRadioButton employeService;
    
    
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
        connect = new JButton("Connexion ECE");
        local = new JButton("Connexion locale");
        exec = new JButton("Executer");

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
        
        //Pour rechercher par chambre
        chambre=new JTextField();
        rechercheChambre = new JButton("Recherche le num de la chambre");
        malades = new JRadioButton("par malades");
        batiments = new JRadioButton("par batiments");
        litChambre = new JRadioButton("par nombre de lit");
        defautChambre=new JRadioButton("par défaut",true);
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
        salaireDocteur=new JRadioButton("par salaire");
        defautDocteur=new JRadioButton("par défaut",true);
        ButtonGroup bgDocteur = new ButtonGroup();
        bgDocteur.add(soigneDocteur);
        bgDocteur.add(speDocteur);
        bgDocteur.add(defautDocteur);
        
        //Pour rechercher par employe
        employe=new JTextField();
        rechercheEmploye=new JButton("Recherche le nom de l'employe");
        defautEmploye=new JRadioButton("par défaut", true);
        salaireEmploye=new JRadioButton("par salaire");
        fonctionEmploye=new JRadioButton("par fonction");
        serviceEmploye=new JRadioButton("par service");
        ButtonGroup bgEmploye = new ButtonGroup();
        bgEmploye.add(defautEmploye);
        bgEmploye.add(salaireEmploye);
        bgEmploye.add(fonctionEmploye);
        bgEmploye.add(serviceEmploye);
        
        //Pour rechercher par infirmier
        infirmier=new JTextField();
        rechercheInfirmier=new JButton("Recherche le nom de l'infirmier");
        defautInfirmier=new JRadioButton("par défaut", true);
        salaireInfirmier=new JRadioButton("par salaire");
        rotationInfirmier=new JRadioButton("par rotation");
        serviceInfirmier=new JRadioButton("par service");
        ButtonGroup bgInfirmier = new ButtonGroup(); 
        bgInfirmier.add(defautInfirmier);
        bgInfirmier.add(salaireInfirmier);
        bgInfirmier.add(rotationInfirmier);
        bgInfirmier.add(serviceInfirmier);
    
        //Pour rechercher par malade
        malade=new JTextField();
        rechercheMalade=new JButton("Recherche le nom du malade");
        defautMalade=new JRadioButton("par défaut", true);
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
        defautService=new JRadioButton("par défaut", true);
        codeService=new JRadioButton("par code");
        batimentService=new JRadioButton("par batiment");
        employeService=new JRadioButton("par employe");
        ButtonGroup bgService = new ButtonGroup(); 
        bgService.add(defautService);
        bgService.add(codeService);
        bgService.add(batimentService);
        bgService.add(employeService);


        // creation des labels
        //tab = new JLabel("Tables", JLabel.CENTER);
        //lignes = new JLabel("Lignes", JLabel.CENTER);
        //req = new JLabel("Requetes de sélection", JLabel.CENTER);
        //res = new JLabel("Résultats requête", JLabel.CENTER);
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
        p4 = new JPanel();
        p5 = new JPanel();
        p6 = new JPanel();
        p7 = new JPanel();
        p8 = new JPanel();
        p9 = new JPanel();
        pA = new JPanel();

        // mise en page des panneaux
        p0.setLayout(new GridLayout(1, 12)); //Pour la connexion
        p1.setLayout(new GridLayout(1, 6));//Pour la chambre
        nord.setLayout(new GridLayout(1, 1));
        p2.setLayout(new GridLayout(1, 1));//Pour les résultats
        
        p3.setLayout(new GridLayout(1,3)); //regroupe p1,p4,p5
        p4.setLayout(new GridLayout(1,6));//Pour le docteur
        p5.setLayout(new GridLayout(1,6));//Pour l'employe
        
        p6.setLayout(new GridLayout(1,6));//Pour l'infirmier
        p7.setLayout(new GridLayout(1,6));//Pour le malade
        p8.setLayout(new GridLayout(1,6));//Pour le service
        p9.setLayout(new GridLayout(1,3));//regroupe p6,p7,p8


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
        //p1.add(tab);
        //p1.add(lignes);
        //p1.add(req);
        //p1.add(res);
        nord.add("North", p0);
        //nord.add(p1);
        
        //p2.add(listeDeTables);
        //p2.add(fenetreLignes);
        //p2.add(listeDeRequetes);
        //Essai pour position le bouton et les RadioButton pour la recherche par chambre
        p1.setLayout(new BoxLayout(p1,BoxLayout.PAGE_AXIS));
        chambre.setPreferredSize(new Dimension(20,20));
        p1.add(chambre); //mal placé
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
        p4.add(salaireDocteur);
        p4.add(defautDocteur);
        
        //Pour rechercher par employe
         p5.setLayout(new BoxLayout(p5,BoxLayout.PAGE_AXIS));
         p5.add(employe);
         p5.add(rechercheEmploye);
         p5.add(fonctionEmploye);
         p5.add(serviceEmploye);
         p5.add(salaireEmploye);
         p5.add(defautEmploye);

        
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
        p6.add(salaireInfirmier);
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
        p8.add(codeService);
        p8.add(batimentService);
        p8.add(employeService);
        p8.add(defautService);
        
        p9.setLayout(new BoxLayout(p9,BoxLayout.LINE_AXIS));
        p9.add(p6);
        p9.add(p7);
        p9.add(p8);
        
        pA.setLayout(new BoxLayout(pA,BoxLayout.PAGE_AXIS));
        pA.add(p3);
        pA.add(p9);
     
        
        //p3.add(requeteLabel);
        //p3.add(requeteTexte);
        //p3.add(exec);
        
        //Pour rechercher la chambre
        /*p1.add(chambre); //mal placé
        p1.add(rechercheChambre);
        p1.add(malades);*/
        

        // ajout des listeners
        connect.addActionListener(this);
        exec.addActionListener(this);
        local.addActionListener(this);
        nameECETexte.addActionListener(this);
        passwdECETexte.addActionListener(this);
        loginBDDTexte.addActionListener(this);
        passwdBDDTexte.addActionListener(this);
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
        salaireDocteur.addActionListener(this);
        
        //Pour rechercher par employe
        employe.addActionListener(this); 
        rechercheEmploye.addActionListener(this); 
        defautEmploye.addActionListener(this); 
        salaireEmploye.addActionListener(this); 
        fonctionEmploye.addActionListener(this); 
        serviceEmploye.addActionListener(this); 
        // couleurs des objets graphiques
        //tab.setBackground(Color.MAGENTA);
        //lignes.setBackground(Color.MAGENTA);
        //req.setBackground(Color.MAGENTA);
        //res.setBackground(Color.MAGENTA);
        //listeDeTables.setBackground(Color.CYAN);
        //fenetreLignes.setBackground(Color.WHITE);
        //listeDeRequetes.setBackground(Color.GREEN);
        fenetreRes.setBackground(Color.LIGHT_GRAY);
        //p1.setBackground(Color.LIGHT_GRAY);
        //p1.setBackground(Color.WHITE);
        
        //Pour rechercher par infirmier
        infirmier.addActionListener(this); 
        rechercheInfirmier.addActionListener(this); 
        defautInfirmier.addActionListener(this); 
        salaireInfirmier.addActionListener(this); 
        rotationInfirmier.addActionListener(this); 
        serviceInfirmier.addActionListener(this); 

    
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
        codeService.addActionListener(this); 
        batimentService.addActionListener(this); 
        employeService.addActionListener(this); 
     

        // disposition geographique des panneaux
        add("North", nord);
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
    private void remplirTables() {
        maconnexion.ajouterTable("chambre");
        maconnexion.ajouterTable("docteur");
        maconnexion.ajouterTable("employe");
        maconnexion.ajouterTable("hospitalisation");
        maconnexion.ajouterTable("infirmier");
        maconnexion.ajouterTable("malade");
        maconnexion.ajouterTable("service");
        maconnexion.ajouterTable("soigne");
        
    }

    /**
     * Méthode privée qui initialise la liste des requetes de selection
     */
    private void remplirRequetes() {
        /*maconnexion.ajouterRequete("SELECT ename, sal FROM Emp ORDER BY sal;");
        maconnexion.ajouterRequete("SELECT Dept.*, Emp.*, Mission.* FROM Dept, Emp, Mission WHERE Dept.deptno=Emp.deptno AND Emp.empno=Mission.empno;");
        maconnexion.ajouterRequete("SELECT AVG (Emp.sal) FROM Emp, Mission WHERE Emp.empno = Mission.empno;");
        maconnexion.ajouterRequete("SELECT Dept.*, Emp.* FROM Dept, Emp WHERE Dept.deptno=Emp.deptno AND comm>0;");
        maconnexion.ajouterRequete("SELECT hiredate, empno, ename FROM Emp WHERE (((hiredate)>='1981-05-01' And (hiredate)<'1981-05-31'))ORDER BY hiredate;");
        maconnexion.ajouterRequete("SELECT ename, job FROM Emp ORDER BY job;");
        maconnexion.ajouterRequete("SELECT DISTINCT dname, job FROM Dept, Emp WHERE Dept.deptno=Emp.deptno AND job='Clerk';");*/
    }

    /**
     * Méthode privée qui initialise la liste des requetes de MAJ
     */
    private void remplirRequetesMaj() {
        // Requêtes d'insertion
        maconnexion.ajouterRequeteMaj("INSERT INTO Dept (deptno,dname,loc) VALUES (50,'ECE','Paris');");

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
            //try {
                //try {
                    // tentative de connexion si les 4 attributs sont remplis
                   // maconnexion = new Connexion(nameECETexte.getText(), passwdECEString,
                       //     loginBDDTexte.getText(), passwdBDDString);

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

                    // se positionner sur la première table et requête de selection
                    listeDeTables.select(0);
                    listeDeRequetes.select(0);

                    // afficher les champs de la table sélectionnée
                    String nomTable = listeDeTables.getSelectedItem();

                    // recuperer les lignes de la table selectionnee
                    afficherLignes(nomTable);

                    // recuperer la liste des lignes de la requete selectionnee
                    String requeteSelectionnee = listeDeRequetes.getSelectedItem();

                try {
                    // afficher les résultats de la requete selectionnee
                    afficherRes(requeteSelectionnee);
                    //} catch (ClassNotFoundException cnfe) {
                    //  System.out.println("Connexion echouee : probleme de classe");
                    //cnfe.printStackTrace();
                    // }
                    //} catch (SQLException e) {
                    //  System.out.println("Connexion echouee : probleme SQL");
                    // e.printStackTrace();
                } catch (SQLException ex) {
                    Logger.getLogger(Fenetre2.class.getName()).log(Level.SEVERE, null, ex);
                }
            //}
//        } else if (source == local) {
//            ArrayList<String> liste;
//            try {
//                //try {
//                    // tentative de connexion si les 4 attributs sont remplis
//                    //maconnexion = new Connexion(nameBDDTexte.getText(), "root", "");
//
//                    // effacer les listes de tables et de requêtes
//                    listeDeTables.removeAll();
//                    listeDeRequetes.removeAll();
//
//                    // initialisation de la liste des requetes de selection et de MAJ
//                    remplirTables();
//                    remplirRequetes();
//                    remplirRequetesMaj();
//
//                    // afficher la liste de tables et des requetes
//                    afficherTables();
//                    afficherRequetes();
//
//                    // se positionner sur la première table et requête de selection
//                    listeDeTables.select(0);
//                    listeDeRequetes.select(0);
//
//                    // afficher les champs de la table sélectionnée
//                    String nomTable = listeDeTables.getSelectedItem();
//
//                    // recuperer les lignes de la table selectionnee
//                    afficherLignes(nomTable);
//
//                    // recuperer la liste des lignes de la requete selectionnee
//                    String requeteSelectionnee = listeDeRequetes.getSelectedItem();
//
//                    // afficher les résultats de la requete selectionnee
//                    afficherRes(requeteSelectionnee);
//                //} catch (ClassNotFoundException cnfe) {
//                  //  System.out.println("Connexion echouee : probleme de classe");
//                    //cnfe.printStackTrace();
//                //}
//            } catch (SQLException e) {
//                System.out.println("Connexion echouee : probleme SQL");
//                e.printStackTrace();
//            }
        } else if (source == exec) {
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
        else if (source==rechercheChambre)
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
                    Object[][] data = new Object[afficherRes(requeteSelectionnee).size()/2][10];
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
            String requeteSelectionnee="e";
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
                //try {
                    // tentative de connexion si les 4 attributs sont remplis
                    //maconnexion = new Connexion(nameBDDTexte.getText(), "root", "");

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
                //} catch (ClassNotFoundException cnfe) {
                  //  System.out.println("Connexion echouee : probleme de classe");
                    //cnfe.printStackTrace();
                //}
            } catch (SQLException e) {
                System.out.println("Connexion echouee : probleme SQL");
                e.printStackTrace();
            }
    
    
    
    }

    void addActionListener(Controleur aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
