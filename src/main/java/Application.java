import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Application extends Canvas {


    public static Ecole ecole = new Ecole("EESC", "LAXOU");
    public static Etudiant etudiant;
    public static Evaluation evaluation;

    public Application(XmlDbFileHandler xmlDbFileHandler) throws InterruptedException {

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
        JLabel labelMoyenne = new JLabel("Moyenne de globale notes :  " + 0);


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
        JButton ajouteEtudiantButton = new JButton();
        JButton moyenneButton2 = new JButton();


        JDialog alertDialog = new JDialog(fenetre, "Donnez nom et age SLP", false);
        alertDialog.setLayout(new FlowLayout());
        Button dButton = new Button("OK");
        dButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alertDialog.setVisible(false);
            }
        });
        alertDialog.add(new JLabel("Donnez nom et age SLP"));
        alertDialog.add(new JLabel(" Click ok to continue"));

        JLabel pasNomerolbl = new JLabel();
        pasNomerolbl.setVisible(false);
        pasNomerolbl.setForeground(Color.RED);
        alertDialog.add(pasNomerolbl);


        alertDialog.add(dButton);
        alertDialog.setLocation(500, 200);
        alertDialog.setSize(300, 300);

        JDialog ajoutEvaluqtionDialog = new JDialog(fenetre, "Ajout Evaluqtions pour etudiant a donne", false);
        ajoutEvaluqtionDialog.setLayout(new FlowLayout());
        ajoutEvaluqtionDialog.setSize(300, 300);
        ajoutEvaluqtionDialog.setLocation(500, 300);

        JLabel ajouteEvaluationHeader = new JLabel();


        JLabel Matiere = new JLabel("Matiere : ");

        String[] MatiereTextList = new String[]{"JAVA", "PHP", "SQL", "PYTON"};
        JList ListMatiere = new JList(MatiereTextList);

        ajoutEvaluqtionDialog.add(Matiere);
        ajoutEvaluqtionDialog.add(ListMatiere);

        JLabel Note = new JLabel("Note : ");
        JTextField textFieldNomNote = new JTextField();
        textFieldNomNote.setText("         ");
        ajoutEvaluqtionDialog.add(Note);
        ajoutEvaluqtionDialog.add(textFieldNomNote);

        JLabel ajouteEvaluationInfo = new JLabel("ajoute Evaluation Info ");
        JButton ajoutEvaluationInfoButton = new JButton("Ajoutez evaluation Info");


        ajoutEvaluqtionDialog.add(ajouteEvaluationInfo);
        ajoutEvaluqtionDialog.add(ajoutEvaluationInfoButton);

        JLabel labeltTextField1 = new JLabel("Nom d'etudiant :  ");
        JTextField textFieldNomEtudiant = new JTextField();
        textFieldNomEtudiant.setSize(121, 30);

        JLabel labelTextField2 = new JLabel("Age d'etudiant :  ");
        JTextField textFieldAge = new JTextField();
        textFieldAge.setSize(121, 30);

        textFieldNomEtudiant.setText("                                                                                          ");
        textFieldAge.setText("                                                                                          ");

        ajoutEvaluqtionDialog.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                etudiant = null;
                textFieldNomEtudiant.setText("");
                textFieldAge.setText("");
                textFieldNomNote.setText("");
                xmlDbFileHandler.saveObjectsToXMLFile(ecole);

            }
        });
        textFieldNomEtudiant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFieldNomEtudiant.setText("");
            }
        });
        ajoutEvaluationInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isNoteOk = false;

                int note = 0;
                if (textFieldNomNote.getText().trim().length() > 0) {
                    try {
                        note = Integer.parseInt(textFieldNomNote.getText().trim());
                        isNoteOk = true;
                    } catch (NumberFormatException nfe) {
                        isNoteOk = false;// Not a number.
                    }
                    if (isNoteOk) {
                        Evaluation evaluation = new Evaluation(note, MatiereTextList[ListMatiere.getAnchorSelectionIndex()]);
                        etudiant.ajouteEvaluations(evaluation);
                        ecole.ajouteEtudiants(etudiant);

                        String column[] = {"NOM", "AGE", "EVALUATIONS\n Metiere : Note"};
                        String data[][] = new String[ecole.getListeEtudiant().size()][3];
                        int i = 0;
                        for (Etudiant etudiant2 : ecole.getListeEtudiant()) {
                            data[i][0] = etudiant2.getNom();
                            data[i][1] = etudiant2.getAge() + "";
                            String evals = "";
                            for (Evaluation evaluation2 : etudiant2.getListeEvaluations()) {
                                evals += " " + evaluation2.getMatiere() + " : " + evaluation2.getNote() + " | ";
                            }
                            data[i][2] = evals;
                            i++;
                        }
                        DefaultTableModel model = new DefaultTableModel(data, column);
                        jt.setModel(model);
                        jt.repaint();

                    }
                }

            }
        });
        moyenneButton2.setText("Calculez moyenne globale note");
        ajouteEtudiantButton.setText("Ajoutez etudiant");
        ajouteEtudiantButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String ageText = textFieldAge.getText().trim();
                String nomText = textFieldNomEtudiant.getText().trim();

                textFieldAge.setText(ageText);
                textFieldNomEtudiant.setText(nomText);
                int age = 0;
                boolean isAgeOk = false;

                if (nomText == "" || ageText == "") {
                    alertDialog.setVisible(true);

                } else {
                    try {
                        age = Integer.parseInt(ageText);
                        isAgeOk = true;
                    } catch (NumberFormatException nfe) {
                        isAgeOk = false;// Not a number.
                    }
                }
                if (isAgeOk && 7 < age) {
                    ajoutEvaluqtionDialog.setVisible(true);
                    ajouteEvaluationHeader.setText("Ajoute Evaluation Info for : " + nomText);
                    etudiant = new Etudiant(nomText, age);

                } else {
                    alertDialog.setVisible(true);
                    pasNomerolbl.setText(ageText + " n'est pas nomero d'age !! ");
                    pasNomerolbl.setVisible(true);
                    textFieldAge.setText("");
                }
            }
        });
        moyenneButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                labelMoyenne.setText(String.valueOf(ecole.moyenneGlobale()));
            }
        });
        panneau.add(label);

        panneau2.add(sp);
        panneau.add(panneau2);
        panneau.add(new JLabel("__________________________________________________________________"));
        panneau.add(labelMoyenne);
        panneau.add(moyenneButton2);
        panneau.add(new JLabel("__________________________________________________________________"));
        panneau.add(labeltTextField1);
        panneau.add(textFieldNomEtudiant);
        panneau.add(labelTextField2);
        panneau.add(textFieldAge);

        panneau.add(ajouteEtudiantButton);

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

        new Application(xmlDbFileHandler);

        //ecole.getListeEtudiant().iterator().next().getListeEvaluations().iterator().next().setMatiere("PYTON");
        //ecole.getListeEtudiant().iterator().next().getListeEvaluations().iterator().next().setNote(7);
        //ecole.getMoyenne_globale_notes();
        xmlDbFileHandler.saveObjectsToXMLFile(ecole);

    }

}
