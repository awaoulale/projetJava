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
 * Affiche dans la fenetre le Reporting
 *
 * @author segado
 */
public class Fenetre3 extends JFrame implements ActionListener, ItemListener {

    /*
     * Attribut privés : objets de Connexion, AWT et Swing
     * 
     */
    public Connexion maconnexion;
    public JLabel nameECE, passwdECE, loginBDD, passwdBDD, nameBDD;
    public JTextField nameECETexte, loginBDDTexte, nameBDDTexte;
   // public JPasswordField passwdECETexte, passwdBDDTexte;
    private final JButton exec;
    private final JButton bout1, bout2, bout3, bout5;
    private final JPanel p0, p1;
    
    
    
    /**
     * Constructeur qui initialise tous les objets graphiques de la fenetre
     */
    public Fenetre3() {

        // creation par heritage de la fenetre
        super("Reporting");

        // mise en page (layout) de la fenetre visible

        setBounds(0, 0, 400, 400);
        setResizable(true);
        setVisible(false);

        // creation des boutons

        exec = new JButton("Executer");
        bout1 = new JButton("Infos services");
        bout2 = new JButton("Infos docteurs");
        bout3 = new JButton("Infos infirmiers");
        bout5 = new JButton("Infos générales");

     



        // creation des panneaux
        p0 = new JPanel();
        p1 = new JPanel();

    

        // mise en page des panneaux
        p0.setLayout(new GridLayout(1, 2));
        p1.setLayout(new GridLayout(1, 5));


        // ajout des objets graphqiues dans les panneaux
        p1.add(bout1);
        p1.add(bout2);
        p1.add(bout3);
       // p1.add(bout4);
        p1.add(bout5);



        // ajout des listeners
        exec.addActionListener(this);
        bout1.addActionListener(this);
        bout2.addActionListener(this);
        bout3.addActionListener(this);

        bout5.addActionListener(this);


     
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
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(D) FROM (SELECT no_docteur as D ,COUNT(no_malade) as N FROM `soigne` as S GROUP BY no_docteur) as M WHERE N>5");
            a = liste.get(0);
            a = a.replace("\n", "");
            i = Integer.parseInt(a);
            liste = maconnexion.remplirChampsRequete("SELECT COUNT(D) FROM (SELECT no_docteur as D ,COUNT(no_malade) as N FROM `soigne` as S GROUP BY no_docteur) as M WHERE N<=5");
            a = liste.get(0);
            a = a.replace("\n", "");
            j = Integer.parseInt(a);
//            System.out.println(liste);
            // create a dataset... 
            DefaultPieDataset dataset2 = new DefaultPieDataset();
            dataset2.setValue("Docteurs s'occupant de plus de 5 patient "+i, i);
            dataset2.setValue("Docteurs s'occupant de 5 patient ou moins "+j, j);
// create a chart... 
            JFreeChart chart2 = ChartFactory.createPieChart(
                    "Occupation des medecins",
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


            
            
                  
           // ------ histo répartition infirmiers jour/nuit par service
// Extraction des infos de la base
           float ii,jj,kk,i2,j2,k2,j3,i3,k3;
             liste = maconnexion.remplirChampsRequete("SELECT AVG(N) FROM (SELECT no_docteur as D ,COUNT(no_malade) as N FROM `soigne` as S GROUP BY no_docteur) as M");
            a = liste.get(0);
            a = a.replace("\n", "");
            ii = Float.parseFloat(a);
            liste = maconnexion.remplirChampsRequete("SELECT AVG(D) FROM (SELECT COUNT(no_docteur) as D ,no_malade as N FROM `soigne` as S GROUP BY no_malade) as M");
            a = liste.get(0);
            a = a.replace("\n", "");
            jj = Float.parseFloat(a);
           
            
     
            
              
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue(ii, "Nombre moyen de malades par medecin", "Moyenne");
            dataset.addValue(jj, "Nombre moyen de medecin par malade ", "Moyenne" );

            JFreeChart chart3 = ChartFactory.createBarChart(
                    "Nombre de medecins par patient", // chart title 
                    "", // domain axis label 
                    "Moyenne", // range axis label 
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
       

        validate(); 
       
        
        
    }

    
    
    
    /**
     *
     * Pour gerer les actions sur items d'une liste on utilise la methode
     * itemStateChanged
     * @param evt
     */
    @Override
    @SuppressWarnings("CallToThreadDumpStack")
    public void itemStateChanged(ItemEvent evt) {
        
        
    }
}
//       