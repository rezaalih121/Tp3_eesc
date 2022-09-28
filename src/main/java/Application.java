import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Application extends Canvas {
    public static Ecole ecole = new Ecole("EESC", "LAXOU");
    public static Etudiant etudiant;
    public static Evaluation evaluation;

    public Application(XmlDbFileHandler xmlDbFileHandler) throws InterruptedException {


        WindowsHandler window = new WindowsHandler("Application de l`ecole", ecole);
        window.addComponentsToPane();
        window.pack();
        window.setResizable(true);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.requestFocus();

    }

    public static void main(String args[]) throws InterruptedException {

        XmlDbFileHandler xmlDbFileHandler = new XmlDbFileHandler();


        ecole = xmlDbFileHandler.loadObjectsFromXmlFile();

        System.out.println("==============================");
        System.out.println("Ecole : " + ecole.getNomEcole() + " Adresse : " + ecole.getAdresse());
        System.out.println(ecole.getListeEtudiant().size());
        for (Etudiant etudiant : ecole.getListeEtudiant()) {

            System.out.println("Nome Etudiant : " + etudiant.getNom() + " age : " + etudiant.getAge());
            for (Evaluation evaluation : etudiant.getListeEvaluations()) {
                System.out.println("Matiere : " + evaluation.getMatiere() + " Note : " + evaluation.getNote());
            }

        }

        new Application(xmlDbFileHandler);

    }

}
