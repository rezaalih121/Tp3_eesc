import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application extends Canvas {


    public static Ecole ecole = new Ecole("EESC", "LAXOU");


    public Application() throws InterruptedException {
        JFrame fenetre = new JFrame("Casse brique");
        //On récupère le panneau de la fenetre principale
        JPanel panneau = (JPanel) fenetre.getContentPane();
        JPanel panneau2 = new JPanel();
        //On définie la hauteur / largeur de l'écran
        panneau.setPreferredSize(new Dimension(500, 500));
        panneau2.setPreferredSize(new Dimension(400, 300));
        setBounds(0, 0, 500, 500);

        panneau.setLayout(new FlowLayout());
        panneau.add(panneau2);
        panneau2.setLayout(new GridLayout());
        JLabel label = new JLabel("Application de l`ecole " + ecole.getNomEcole() + " a " + ecole.getAdresse());
        JLabel label2 = new JLabel("Moyenne de globale notes :  " + 0);


        String column[] = {"NOM", "AGE", "EVALUATIONS\n Metiere : Note"};
        String data[][] = new String[ecole.getListeEtudiant().size()][3];
        int i = 0;
        for (Etudiant etudiant : ecole.getListeEtudiant()) {
            data[i][0] = etudiant.getNom();
            data[i][1] = etudiant.getAge() + "";
            String evals = "";
            for (Evaluation evaluation : etudiant.getListeEvaluations()) {
                evals += " " + evaluation.getMatiere() + " : " + evaluation.getNote() + " | ";
            }
            data[i][2] = evals;
            i++;
        }
        DefaultTableModel model = new DefaultTableModel(data, column);
        JTable jt = new JTable(model);
        jt.setShowGrid(true);
        jt.setShowVerticalLines(true);
        jt.getScrollableTracksViewportHeight();
        jt.getAutoscrolls();
        jt.setBounds(0, 0, 300, 200);
        jt.getColumn(column[0]).setMaxWidth(40);
        jt.getColumn(column[1]).setMaxWidth(30);
        JScrollPane sp = new JScrollPane(jt);
        JButton button = new JButton();
        JButton button2 = new JButton();
        JTextField textField1 = new JTextField();
        textField1.setText("--------------------------------------------");
        button2.setText("calculez le moyenne globale note");
        button.setText("Ajoutez etudiant");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField1.setText("Beavenue a l`ecole application.");
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label2.setText("Moyenne de globale notes :  " + ecole.moyenneGlobale());
            }
        });
        panneau.add(label);

        panneau2.add(sp);
        panneau.add(panneau2);
        panneau.add(label2);
        panneau.add(button2);
        panneau.add(button);
        panneau.add(textField1);
        //On ajoute cette classe (qui hérite de Canvas) comme composant du panneau principal
        panneau.add(this);


        fenetre.pack();
        fenetre.setResizable(false);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.requestFocus();

        //On indique que le raffraichissement de l'ecran doit être fait manuellement.
        createBufferStrategy(2);
        setIgnoreRepaint(true);
        this.setFocusable(false);

        //demarrer();
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

        new Application();

        //ecole.getListeEtudiant().iterator().next().getListeEvaluations().iterator().next().setMatiere("PYTON");
        //ecole.getListeEtudiant().iterator().next().getListeEvaluations().iterator().next().setNote(7);
        //ecole.getMoyenne_globale_notes();
        xmlDbFileHandler.saveObjectsToXMLFile(ecole);

    }

}
