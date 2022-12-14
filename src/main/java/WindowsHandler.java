import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WindowsHandler extends JFrame implements WindowListener, WindowStateListener, WindowFocusListener {

    public WindowsHandler(String title, Ecole ecole) throws HeadlessException {
        super(title);
        this.setEcole(ecole);
        this.addWindowListener(this);
        this.addWindowStateListener(this);
        this.addWindowFocusListener(this);
    }

    public Ecole getEcole() {
        return ecole;
    }

    public void setEcole(Ecole ecole) {
        this.ecole = ecole;
    }

    protected Ecole ecole;
    protected Etudiant etudiant;

    protected JLabel moyenneLabel, headerLabel, nomEtudiantLbl, ageEtudiantLbl, matiereLbl, noteLbl, infoEtudiantLbl, infoEtudiantLbl2;
    protected JPanel panel, panelAddEtudiantForm, panelAddEvaluations;
    protected TableHandler etudiantListTable;
    protected ButtonHandler addEtudiantBtn, addEvaluationBtn, addNewEtudiantBtn;
    JButton moyenneBtn;
    protected JScrollPane jScrollPaneTable;
    protected JTextField nomEtudiantText, noteText;
    protected JComboBox<String> ageEtudiantList, matiereJlist;

    protected void addComponentsToPane() {
        panel = new JPanel();
        panelAddEtudiantForm = new JPanel();
        panelAddEvaluations = new JPanel();
        panelAddEvaluations.setVisible(false);
        panel = (JPanel) this.getContentPane();
        setBounds(0, 0, 500, 700);

        panel.setPreferredSize(new Dimension(500, 700));
        panelAddEtudiantForm.setBounds(0, 0, 500, 200);
        panelAddEvaluations.setBounds(0, 0, 500, 200);

        panel.setLayout(new FlowLayout());
        panelAddEtudiantForm.setLayout(new GridLayout(3, 1));
        panelAddEvaluations.setLayout(new GridLayout(6, 1));

        headerLabel = new JLabel("Application de l`ecole " + ecole.getNomEcole() + " a " + ecole.getAdresse());
        headerLabel.setBorder(BorderFactory.createRaisedBevelBorder());
        headerLabel.setFont(new Font("Broadway", Font.BOLD, 21));
        panel.add(headerLabel);

        etudiantListTable = new TableHandler(ecole);
        jScrollPaneTable = new JScrollPane(etudiantListTable);
        jScrollPaneTable.setMaximumSize(new Dimension(200, 200));
        panel.add(jScrollPaneTable);


        moyenneLabel = new JLabel("Moyenne de globale notes :  " + 0);

        moyenneBtn = new JButton("Calculez Moyenne Note");
        moyenneBtn.addActionListener(e -> this.moyenneLabel.setText(this.moyenneLabel.getText().replace("0", String.valueOf(ecole.moyenneGlobale()))));

        moyenneLabel.setLabelFor(moyenneBtn);

        panel.add(moyenneLabel);
        panel.add(moyenneBtn);


        nomEtudiantLbl = new JLabel("Nom d'etudiant :  ");
        nomEtudiantText = new JTextField();
        nomEtudiantText.setSize(121, 20);
        nomEtudiantText.setText("                               ");
        nomEtudiantLbl.setLabelFor(nomEtudiantText);

        panelAddEtudiantForm.add(nomEtudiantLbl);
        panelAddEtudiantForm.add(nomEtudiantText);

        ageEtudiantLbl = new JLabel("Age d'etudiant :  ");
        String[] ageNum = new String[33];
        for (int i = 0; i < 33; i++) {
            ageNum[i] = (i + 18) + "";
        }
        ageEtudiantList = new JComboBox<>(ageNum);

        ageEtudiantList.addItemListener((ItemEvent e) -> {
            JComboBox jComboBox = (JComboBox) e.getSource();
            System.out.println("Selected : " + jComboBox.getSelectedItem());
        });

        ageEtudiantLbl.setLabelFor(ageEtudiantList);

        addEtudiantBtn = new ButtonHandler("Ajout Etudiant", this);

        panelAddEtudiantForm.add(ageEtudiantLbl);
        panelAddEtudiantForm.add(ageEtudiantList);
        panelAddEtudiantForm.add(addEtudiantBtn);


        infoEtudiantLbl = new JLabel("Ajout evaluation pour :  ");
        infoEtudiantLbl2 = new JLabel();

        panelAddEvaluations.add(infoEtudiantLbl);
        panelAddEvaluations.add(infoEtudiantLbl2);
        infoEtudiantLbl2.setFont(new Font("Tahoma", Font.BOLD, 14));


        matiereLbl = new JLabel("Matiere :  ");
        String[] matieres = {"JAVA", "PHP", "SQL", "PYTON", "C++", "JavaScript", "CSS-HTML"};
        matiereJlist = new JComboBox<>(matieres);

        matiereJlist.addItemListener((ItemEvent e) -> {
            JComboBox jComboBox = (JComboBox) e.getSource();
            System.out.println("Selected : " + jComboBox.getSelectedItem());
        });
        matiereLbl.setLabelFor(matiereJlist);

        panelAddEvaluations.add(matiereLbl);
        panelAddEvaluations.add(matiereJlist);


        noteLbl = new JLabel("Note :  ");
        noteText = new JTextField();
        noteLbl.setLabelFor(noteText);

        panelAddEvaluations.add(noteLbl);
        panelAddEvaluations.add(noteText);


        addEvaluationBtn = new ButtonHandler("Ajout Evaluation", this);
        addNewEtudiantBtn = new ButtonHandler("Ajout Nouvel Etudiant", this);

        panelAddEvaluations.add(addEvaluationBtn);
        panelAddEvaluations.add(addNewEtudiantBtn);

        panel.add(panelAddEtudiantForm);
        panel.add(panelAddEvaluations);


    }

    @Override
    public void windowGainedFocus(WindowEvent e) {

    }

    @Override
    public void windowLostFocus(WindowEvent e) {

    }

    @Override
    public void windowStateChanged(WindowEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

        Object[] choix = {"Oui", "Nope :-("};
        int reponse = JOptionPane.showOptionDialog(this, "Est-ce que vous arreter l'application ? ", "Confirmer ! ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);
        if (reponse == JOptionPane.YES_NO_OPTION) {

            XmlDbFileHandler xmlDbFileHandler = new XmlDbFileHandler();
            xmlDbFileHandler.saveObjectsToXMLFile(ecole);
        } else {
            XmlDbFileHandler xmlDbFileHandler = new XmlDbFileHandler();
            xmlDbFileHandler.saveObjectsToXMLFile(ecole);
            System.exit(0);
        }

    }

    @Override
    public void windowClosed(WindowEvent e) {


    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
