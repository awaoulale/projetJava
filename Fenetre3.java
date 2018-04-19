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
import static java.lang.Float.parseFloat;
import java.util.*;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * Affiche dans la fenetre graphique les champs de tables et les requetes de la
 * BDD
 *
 * @author segado
 */
public class Fenetre3 extends JFrame implements ActionListener, ItemListener {

    /*
     * Attribut privés : objets de Connexion, AWT et Swing
     * 
     */
    public Connexion maconnexion;
    private final JLabel tab, req, res, lignes;
    private final JLabel requeteLabel;
    private final JTextField requeteTexte;
    public JLabel nameECE, passwdECE, loginBDD, passwdBDD, nameBDD;
    public JTextField nameECETexte, loginBDDTexte, nameBDDTexte;
    public JPasswordField passwdECETexte, passwdBDDTexte;
    private final JButton exec;
    private final JButton bout1, bout2, bout3 ,bout4, bout5;
    private final java.awt.List listeDeTables, listeDeRequetes;
    private final JTextArea fenetreLignes, fenetreRes;
    private final JPanel p0, p1, nord, p2, p3;
    //private ChartPanel cp1,cp2;
    
    /**
     * Constructeur qui initialise tous les objets graphiques de la fenetre
     */
    public Fenetre3() {

        // creation par heritage de la fenetre
        super("Reporting");

        // mise en page (layout) de la fenetre visible
//        super.rootPane.setLayout(new BorderLayout());
//        setLayout(new BoxLayout(super.rootPane,BoxLayout.LINE_AXIS));
//        setLayout(new BorderLayout());
        setBounds(0, 0, 400, 400);
        setResizable(true);
        setVisible(false);

        // creation des boutons
//        connect = new JButton("Connexion ECE");
//        local = new JButton("Connexion locale");
        exec = new JButton("Executer");
        bout1 = new JButton("Infos services");
        bout2 = new JButton("Infos docteurs");
        bout3 = new JButton("Infos infirmiers");
        bout4 = new JButton("Infos malades");
        bout5 = new JButton("Infos générales");

        // creation des listes pour les tables et les requetes
        listeDeTables = new java.awt.List(10, false);
        listeDeRequetes = new java.awt.List(10, false);

        // creation des textes
//        nameECETexte = new JTextField();
//        passwdECETexte = new JPasswordField(8);
//        loginBDDTexte = new JTextField();
//        passwdBDDTexte = new JPasswordField(8);
//        nameBDDTexte = new JTextField();
        fenetreLignes = new JTextArea();
        fenetreRes = new JTextArea();
        requeteTexte = new JTextField();

        // creation des labels
        tab = new JLabel("Tables", JLabel.CENTER);
        lignes = new JLabel("Lignes", JLabel.CENTER);
        req = new JLabel("Requetes de sélection", JLabel.CENTER);
        res = new JLabel("Résultats requête", JLabel.CENTER);
//        nameECE = new JLabel("login ECE :", JLabel.CENTER);
//        passwdECE = new JLabel("password ECE :", JLabel.CENTER);
//        loginBDD = new JLabel("login base :", JLabel.CENTER);
//        passwdBDD = new JLabel("password base :", JLabel.CENTER);
//        nameBDD = new JLabel("nom base :", JLabel.CENTER);
        requeteLabel = new JLabel("Entrez votre requete de sélection :", JLabel.CENTER);

        // creation des panneaux
        p0 = new JPanel();
        p1 = new JPanel();
        nord = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
//        cp1 = new JPanel();
//        cp2 = new JPanel();

        // mise en page des panneaux
        p0.setLayout(new GridLayout(1, 2));
        p1.setLayout(new GridLayout(1, 5));
//        nord.setLayout(new GridLayout(2, 1));
//        p2.setLayout(new GridLayout(1, 4));
//        p2.setLayout(new );
//        p3.setLayout(new GridLayout(1, 3));

        // ajout des objets graphqiues dans les panneaux
        p1.add(bout1);
        p1.add(bout2);
        p1.add(bout3);
        p1.add(bout4);
        p1.add(bout5);

//        p1.add(lignes);
//        p1.add(req);
//        p1.add(res);
//        nord.add("North", p0);
//        nord.add("North", p1);
//        p2.add(listeDeTables);
//        p2.add(fenetreLignes);
//        p2.add(listeDeRequetes);
//        p2.add(fenetreRes);
//        p3.add(requeteLabel);
//        p3.add(requeteTexte);
//        p3.add(exec);

        // ajout des listeners
        exec.addActionListener(this);
        bout1.addActionListener(this);
        bout2.addActionListener(this);
        bout3.addActionListener(this);
        bout4.addActionListener(this);
        bout5.addActionListener(this);
        listeDeTables.addItemListener(this);
        listeDeRequetes.addItemListener(this);

        // couleurs des objets graphiques
        tab.setBackground(Color.MAGENTA);
        lignes.setBackground(Color.MAGENTA);
        req.setBackground(Color.MAGENTA);
        res.setBackground(Color.MAGENTA);
        listeDeTables.setBackground(Color.CYAN);
        fenetreLignes.setBackground(Color.WHITE);
        listeDeRequetes.setBackground(Color.GREEN);
        fenetreRes.setBackground(Color.WHITE);
        p1.setBackground(Color.LIGHT_GRAY);

       // p0.add(cp1);
       //p0.add(cp2);
        add("Center",p0);
       add("South",p1);
         
        
        
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
        maconnexion.ajouterRequete("SELECT ename, sal FROM Emp ORDER BY sal;");
        maconnexion.ajouterRequete("SELECT Dept.*, Emp.*, Mission.* FROM Dept, Emp, Mission WHERE Dept.deptno=Emp.deptno AND Emp.empno=Mission.empno;");
        maconnexion.ajouterRequete("SELECT AVG (Emp.sal) FROM Emp, Mission WHERE Emp.empno = Mission.empno;");
        maconnexion.ajouterRequete("SELECT Dept.*, Emp.* FROM Dept, Emp WHERE Dept.deptno=Emp.deptno AND comm>0;");
        maconnexion.ajouterRequete("SELECT hiredate, empno, ename FROM Emp WHERE (((hiredate)>='1981-05-01' And (hiredate)<'1981-05-31'))ORDER BY hiredate;");
        maconnexion.ajouterRequete("SELECT ename, job FROM Emp ORDER BY job;");
        maconnexion.ajouterRequete("SELECT DISTINCT dname, job FROM Dept, Emp WHERE Dept.deptno=Emp.deptno AND job='Clerk';");
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
         p0.removeAll();
         String a;
        int i,j,k,l,m,n;
        ArrayList<String> liste;

        
        if(source==bout1)
        try {
// ------ Camembert répartition infirmiers jour/nuit
// Extraction des infos de la base
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `chambre` WHERE code_service=\"REA\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            i = Integer.parseInt(a);
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `chambre` WHERE code_service=\"CAR\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            j = Integer.parseInt(a);
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `chambre` WHERE code_service=\"CHG\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            k = Integer.parseInt(a);
// create a dataset... 
            DefaultPieDataset dataset1 = new DefaultPieDataset();
            dataset1.setValue("Reanimation et Traumatologie "+i, i);
            dataset1.setValue("Cardiologie "+j, j);
            dataset1.setValue("Chirurgie generale "+k, j);
// create a chart... 
            JFreeChart chart1 = ChartFactory.createPieChart(
                    "Nombre de chambres par service",
                    dataset1,
                    true, // legend?
                    true, // tooltips?
                    false // URLs?
            );
// create and display a ... 
            ChartPanel cp1 = new ChartPanel(chart1);
            p0.add(cp1);
// ------ Camembert 
           liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `hospitalisation` WHERE code_service=\"REA\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            i = Integer.parseInt(a);
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `hospitalisation` WHERE code_service=\"CAR\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            j = Integer.parseInt(a);
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `hospitalisation` WHERE code_service=\"CHG\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            k = Integer.parseInt(a);
            // create a dataset... 
            DefaultPieDataset dataset2 = new DefaultPieDataset();
            dataset2.setValue("Reanimation et Traumatologie "+i, i);
            dataset2.setValue("Cardiologie "+j, j);
            dataset2.setValue("Chirurgie generale "+k, j);
// create a chart... 
            JFreeChart chart2 = ChartFactory.createPieChart(
                    "Nombre de malade par service",
                    dataset2,
                    true, // legend?
                    true, // tooltips?
                    false // URLs?
            );
// create and display a ... 
            ChartPanel cp2 = new ChartPanel(chart2);
            p0.add(cp2);
            
           // ------ Camembert répartition infirmiers jour/nuit
// Extraction des infos de la base
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `infirmier` WHERE code_service=\"REA\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            i = Integer.parseInt(a);
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `infirmier` WHERE code_service=\"CAR\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            j = Integer.parseInt(a);
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `infirmier` WHERE code_service=\"CHG\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            k = Integer.parseInt(a);
// create a dataset... 
            DefaultPieDataset dataset3 = new DefaultPieDataset();
            dataset3.setValue("Reanimation et Traumatologie "+i, i);
            dataset3.setValue("Cardiologie "+j, j);
            dataset3.setValue("Chirurgie generale "+k, j);
// create a chart... 
            JFreeChart chart3 = ChartFactory.createPieChart(
                    "Nombre d'infirmier par service",
                    dataset3,
                    true, // legend?
                    true, // tooltips?
                    false // URLs?
            );
// create and display a ... 
            ChartPanel cp3 = new ChartPanel(chart3);
            p0.add(cp3);
            
            
            
            
            

        } catch (SQLException ex) {
            System.out.println("Problème remplirChampsRequete !");
//                Logger.getLogger(TestProjet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
         if(source==bout2)
        try {
// ------ Camembert répartition infirmiers jour/nuit
// Extraction des infos de la base
             liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `docteur`GROUP BY specialite");
            int i1,i2,i3,i4,i5,i6;
            String a1,a2,a3,a4,a5,a6;
            a1 = liste.get(0);
            a1 = a1.replace("\n", "");
            i1 = Integer.parseInt(a1);
            a2 = liste.get(1);
            a2 = a2.replace("\n", "");
            i2 = Integer.parseInt(a2);
            a3 = liste.get(2);
            a3 = a3.replace("\n", "");
            i3 = Integer.parseInt(a3);
            a4 = liste.get(3);
            a4 = a4.replace("\n", "");
            i4 = Integer.parseInt(a4);
            a5 = liste.get(4);
            a5 = a5.replace("\n", "");
            i5 = Integer.parseInt(a5);
            a6 = liste.get(5);
            a6 = a6.replace("\n", "");
            i6 = Integer.parseInt(a6);
           
// create a dataset... 
            DefaultPieDataset dataset1 = new DefaultPieDataset();
            dataset1.setValue("Anesthésiste"+i1, i1);
            dataset1.setValue("Cardiologue "+i2, i2);
            dataset1.setValue("Orthopédiste "+i3, i3);
            dataset1.setValue("Pneumologue "+i4, i4);
            dataset1.setValue("Radiologue "+i5, i5);
            dataset1.setValue("Traumatologue "+i5, i5);
// create a chart... 
            JFreeChart chart1 = ChartFactory.createPieChart(
                    "Nombre de docteur par spécialité",
                    dataset1,
                    true, // legend?
                    true, // tooltips?
                    false // URLs?
            );
// create and display a ... 
            ChartPanel cp1 = new ChartPanel(chart1);
            p0.add(cp1);
            
            
// ------ Camembert 
            liste = maconnexion.remplirChampsRequete("SELECT * FROM `malade` WHERE 1");
//            System.out.println(liste);
            // create a dataset... 
            DefaultPieDataset dataset2 = new DefaultPieDataset();
            dataset2.setValue("Libre", 2);
            dataset2.setValue("Occupé", 10);
// create a chart... 
            JFreeChart chart2 = ChartFactory.createPieChart(
                    "Occupation chambres",
                    dataset2,
                    true, // legend?
                    true, // tooltips?
                    false // URLs?
            );
// create and display a ... 
            ChartPanel cp2 = new ChartPanel(chart2);
            p0.add(cp2);

        } catch (SQLException ex) {
            System.out.println("Problème remplirChampsRequete !");
//                Logger.getLogger(TestProjet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        
         
         
         
           if(source==bout3)
        try {
// ------ Camembert répartition infirmiers jour/nuit
// Extraction des infos de la base
                      // ------ histo répartition infirmiers jour/nuit par service
// Extraction des infos de la base
           float rea,car,chg,rea2,car2,chg2;
             liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `infirmier`WHERE rotation=\"JOUR\" AND code_service=\"REA\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            rea = Float.parseFloat(a);
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `infirmier`WHERE rotation=\"JOUR\" AND code_service=\"CAR\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            car = Float.parseFloat(a);
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `infirmier`WHERE rotation=\"JOUR\" AND code_service=\"CHG\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            chg = Float.parseFloat(a);
            
             liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `infirmier`WHERE rotation=\"NUIT\" AND code_service=\"REA\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            rea2 = Float.parseFloat(a);
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `infirmier`WHERE rotation=\"NUIT\" AND code_service=\"CAR\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            car2 = Float.parseFloat(a);
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `infirmier`WHERE rotation=\"NUIT\" AND code_service=\"CHG\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            chg2 = Float.parseFloat(a);
            
           
            
              
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
            dataset2.addValue(car, "jour", "Cardiologie");
            dataset2.addValue(chg, "jour", "Chirurgie generale");
            dataset2.addValue(rea, "jour", "Reanimation et Traumatologie");
            dataset2.addValue(car2, "nuit", "Cardiologie");
            dataset2.addValue(chg2, "nuit", "Chirurgie generale");
            dataset2.addValue(rea2, "nuit", "Reanimation et Traumatologie");
            JFreeChart chart2 = ChartFactory.createBarChart(
                    "Infirmiers par service", // chart title 
                    "Service", // domain axis label 
                    "Nombre d'infirmier", // range axis label 
                    dataset2, // data 
                    PlotOrientation.VERTICAL, // orientation 
                    true, // include legend 
                    true, // tooltips? 
                    false // URLs? 
            );


            ChartPanel cp2 = new ChartPanel(chart2, false);
            p0.add(cp2);   
         
            
            
            
            
            
              
           // ------ histo répartition infirmiers jour/nuit par service
// Extraction des infos de la base
           float ii,jj,kk,i2,j2,k2,j3,i3,k3;
             liste = maconnexion.remplirChampsRequete("SELECT AVG(salaire) FROM `infirmier`WHERE code_service=\"REA\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            ii = Float.parseFloat(a);
            liste = maconnexion.remplirChampsRequete("SELECT AVG(salaire) FROM `infirmier`WHERE code_service=\"CAR\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            jj = Float.parseFloat(a);
            liste = maconnexion.remplirChampsRequete("SELECT AVG(salaire) FROM `infirmier`WHERE code_service=\"CHG\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            kk = Float.parseFloat(a);
            
             liste = maconnexion.remplirChampsRequete("SELECT MIN(salaire) FROM `infirmier`WHERE code_service=\"REA\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            i2 = Float.parseFloat(a);
            liste = maconnexion.remplirChampsRequete("SELECT MIN(salaire) FROM `infirmier`WHERE code_service=\"CAR\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            j2 = Float.parseFloat(a);
            liste = maconnexion.remplirChampsRequete("SELECT MIN(salaire) FROM `infirmier`WHERE code_service=\"CHG\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            k2 = Float.parseFloat(a);
            
             liste = maconnexion.remplirChampsRequete("SELECT MAX(salaire) FROM `infirmier`WHERE code_service=\"REA\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            i3 = Float.parseFloat(a);
            liste = maconnexion.remplirChampsRequete("SELECT MAX(salaire) FROM `infirmier`WHERE code_service=\"CAR\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            j3 = Float.parseFloat(a);
            liste = maconnexion.remplirChampsRequete("SELECT MAX(salaire) FROM `infirmier`WHERE code_service=\"CHG\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            k3 = Float.parseFloat(a);
            
              
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue(jj, "Moyenne", "Cardiologie");
            dataset.addValue(kk, "Moyenne", "Chirurgie generale");
            dataset.addValue(ii, "Moyenne", "Reanimation et Traumatologie");
            dataset.addValue(j2, "Min", "Cardiologie");
            dataset.addValue(k2, "Min", "Chirurgie generale");
            dataset.addValue(i2, "Min", "Reanimation et Traumatologie");
            dataset.addValue(j3, "Max", "Cardiologie");
            dataset.addValue(k3, "Max", "Chirurgie generale");
            dataset.addValue(i3, "Max", "Reanimation et Traumatologie");
            JFreeChart chart3 = ChartFactory.createBarChart(
                    "Salaire des infirmiers", // chart title 
                    "Service", // domain axis label 
                    "Salaire", // range axis label 
                    dataset, // data 
                    PlotOrientation.VERTICAL, // orientation 
                    true, // include legend 
                    true, // tooltips? 
                    false // URLs? 
            );


            ChartPanel cp3 = new ChartPanel(chart3, false);
            p0.add(cp3);   
         
            
            
            
        

        } catch (SQLException ex) {
            System.out.println("Problème remplirChampsRequete !");
//                Logger.getLogger(TestProjet.class.getName()).log(Level.SEVERE, null, ex);
        }
         
           
           
           
         
         
         
           
           
           
           
           
           
           
           
           
           
            if(source==bout4)
        try {
// ------ Camembert répartition infirmiers jour/nuit
// Extraction des infos de la base
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `chambre` WHERE code_service=\"REA\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            i = Integer.parseInt(a);
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `chambre` WHERE code_service=\"CAR\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            j = Integer.parseInt(a);
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `chambre` WHERE code_service=\"CHG\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            k = Integer.parseInt(a);
// create a dataset... 
            DefaultPieDataset dataset1 = new DefaultPieDataset();
            dataset1.setValue("Reanimation et Traumatologie "+i, i);
            dataset1.setValue("Cardiologie "+j, j);
            dataset1.setValue("Chirurgie generale "+k, j);
// create a chart... 
            JFreeChart chart1 = ChartFactory.createPieChart(
                    "Nombre de chambres par service",
                    dataset1,
                    true, // legend?
                    true, // tooltips?
                    false // URLs?
            );
// create and display a ... 
            ChartPanel cp1 = new ChartPanel(chart1);
            p0.add(cp1);
// ------ Camembert 
           liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `hospitalisation` WHERE code_service=\"REA\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            i = Integer.parseInt(a);
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `hospitalisation` WHERE code_service=\"CAR\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            j = Integer.parseInt(a);
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `hospitalisation` WHERE code_service=\"CHG\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            k = Integer.parseInt(a);
            // create a dataset... 
            DefaultPieDataset dataset2 = new DefaultPieDataset();
            dataset2.setValue("Reanimation et Traumatologie "+i, i);
            dataset2.setValue("Cardiologie "+j, j);
            dataset2.setValue("Chirurgie generale "+k, j);
// create a chart... 
            JFreeChart chart2 = ChartFactory.createPieChart(
                    "Nombre de malade par service",
                    dataset2,
                    true, // legend?
                    true, // tooltips?
                    false // URLs?
            );
// create and display a ... 
            ChartPanel cp2 = new ChartPanel(chart2);
            p0.add(cp2);
            
           // ------ Camembert répartition infirmiers jour/nuit
// Extraction des infos de la base
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `infirmier` WHERE code_service=\"REA\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            i = Integer.parseInt(a);
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `infirmier` WHERE code_service=\"CAR\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            j = Integer.parseInt(a);
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `infirmier` WHERE code_service=\"CHG\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            k = Integer.parseInt(a);
// create a dataset... 
            DefaultPieDataset dataset3 = new DefaultPieDataset();
            dataset3.setValue("Reanimation et Traumatologie "+i, i);
            dataset3.setValue("Cardiologie "+j, j);
            dataset3.setValue("Chirurgie generale "+k, j);
// create a chart... 
            JFreeChart chart3 = ChartFactory.createPieChart(
                    "Nombre d'infirmier par service",
                    dataset3,
                    true, // legend?
                    true, // tooltips?
                    false // URLs?
            );
// create and display a ... 
            ChartPanel cp3 = new ChartPanel(chart3);
            p0.add(cp3);
            
            
            
            
            

        } catch (SQLException ex) {
            System.out.println("Problème remplirChampsRequete !");
//                Logger.getLogger(TestProjet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
         
            
            
            
            
            
            
            
            
            
            
            
            
            
            
                
           
           
           
            if(source==bout5)
        try {
// ------ Camembert Chambre occupé/vide
// Extraction des infos de la base
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(DISTINCT hospitalisation.no_chambre,hospitalisation.code_service) FROM chambre,hospitalisation WHERE hospitalisation.no_chambre=chambre.no_chambre AND chambre.code_service=hospitalisation.code_service;");
            a = liste.get(0);
            a = a.replace("\n", "");
            i = Integer.parseInt(a);
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM chambre;");
            a = liste.get(0);
            a = a.replace("\n", "");
            j = Integer.parseInt(a);
            j=j-i;
            
// create a dataset... 
            DefaultPieDataset dataset1 = new DefaultPieDataset();
            dataset1.setValue("Chambre occupées "+i, i);
            dataset1.setValue("Chambre libres "+j, j);
   
// create a chart... 
            JFreeChart chart1 = ChartFactory.createPieChart(
                    "Occupation des chambres",
                    dataset1,
                    true, // legend?
                    true, // tooltips?
                    false // URLs?
            );
// create and display a ... 
            ChartPanel cp1 = new ChartPanel(chart1);
            p0.add(cp1);

// ------ Camembert 
           liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `hospitalisation` WHERE code_service=\"REA\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            i = Integer.parseInt(a);
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `hospitalisation` WHERE code_service=\"CAR\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            j = Integer.parseInt(a);
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `hospitalisation` WHERE code_service=\"CHG\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            k = Integer.parseInt(a);
            // create a dataset... 
            DefaultPieDataset dataset2 = new DefaultPieDataset();
            dataset2.setValue("Reanimation et Traumatologie "+i, i);
            dataset2.setValue("Cardiologie "+j, j);
            dataset2.setValue("Chirurgie generale "+k, j);
// create a chart... 
            JFreeChart chart2 = ChartFactory.createPieChart(
                    "Nombre de malade par service",
                    dataset2,
                    true, // legend?
                    true, // tooltips?
                    false // URLs?
            );
// create and display a ... 
            ChartPanel cp2 = new ChartPanel(chart2);
            p0.add(cp2);
            
           // ------ Camembert répartition infirmiers jour/nuit
// Extraction des infos de la base
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `infirmier` WHERE code_service=\"REA\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            i = Integer.parseInt(a);
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `infirmier` WHERE code_service=\"CAR\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            j = Integer.parseInt(a);
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(*) FROM `infirmier` WHERE code_service=\"CHG\"");
            a = liste.get(0);
            a = a.replace("\n", "");
            k = Integer.parseInt(a);
// create a dataset... 
            DefaultPieDataset dataset3 = new DefaultPieDataset();
            dataset3.setValue("Reanimation et Traumatologie "+i, i);
            dataset3.setValue("Cardiologie "+j, j);
            dataset3.setValue("Chirurgie generale "+k, j);
// create a chart... 
            JFreeChart chart3 = ChartFactory.createPieChart(
                    "Nombre d'infirmier par service",
                    dataset3,
                    true, // legend?
                    true, // tooltips?
                    false // URLs?
            );
// create and display a ... 
            ChartPanel cp3 = new ChartPanel(chart3);
            p0.add(cp3);
            
            
            
            
            

        } catch (SQLException ex) {
            System.out.println("Problème remplirChampsRequete !");
//                Logger.getLogger(TestProjet.class.getName()).log(Level.SEVERE, null, ex);
        }
       

            
            
            
            
            
            
            
            
            
            
            
            
            
            
         
         
         
        
        validate(); 
       
        
        
        
        
        
        
        
        
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
                Logger.getLogger(Fenetre3.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getSource() == listeDeTables) {
            // afficher les lignes de la table sélectionnée
            String nomTable = listeDeTables.getSelectedItem();
            afficherLignes(nomTable);
        }
    }


//
//    public void init() {
//                    }
}
