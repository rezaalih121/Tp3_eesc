import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonHandler extends JButton implements ActionListener {

    protected WindowsHandler windowsHandler;
    protected Ecole ecole;

    public ButtonHandler() {
    }

    public ButtonHandler(String text, WindowsHandler windowsHandler) {
        super(text);
        this.setWindowsHandler(windowsHandler);
        this.setEcole(windowsHandler.ecole);
        this.addActionListener(this);
    }

    public WindowsHandler getWindowsHandler() {
        return windowsHandler;
    }

    public void setWindowsHandler(WindowsHandler windowsHandler) {
        this.windowsHandler = windowsHandler;
    }

    public Ecole getEcole() {
        return ecole;
    }

    public void setEcole(Ecole ecole) {
        this.ecole = ecole;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ButtonHandler buttonHandler = (ButtonHandler) e.getSource();

        if (buttonHandler.getText().equals("Ajout Etudiant")) {

            String ageText = String.valueOf(windowsHandler.ageEtudiantList.getSelectedItem());
            String nomText = windowsHandler.nomEtudiantText.getText().trim();

            windowsHandler.nomEtudiantText.setText(nomText);
            int age = 0;
            boolean isAgeOk = false;

            if (nomText == "") {
                JOptionPane.showMessageDialog(windowsHandler, "Donnez nom et age SLP");
            } else {
                try {
                    age = Integer.parseInt(ageText);
                    isAgeOk = true;
                } catch (NumberFormatException nfe) {
                    isAgeOk = false;// Not a number.
                }
            }
            if (isAgeOk) {

                windowsHandler.etudiant = new Etudiant(nomText, age);

                windowsHandler.infoEtudiantLbl2.setText(windowsHandler.etudiant.getNom());
                windowsHandler.panelAddEtudiantForm.setVisible(false);
                windowsHandler.panelAddEvaluations.setVisible(true);

            }
        }

        if (buttonHandler.getText().equals("Ajout Evaluation")) {
            String matiere = windowsHandler.matiereJlist.getSelectedItem().toString();
            String noteText = windowsHandler.noteText.getText().trim();

            windowsHandler.noteText.setText(noteText);
            int note = 0;
            boolean isNoteOk = false;
            try {
                note = Integer.parseInt(noteText);
                isNoteOk = true;
            } catch (NumberFormatException nfe) {
                isNoteOk = false;// Not a number.
            }
            if (isNoteOk) {
                Evaluation evaluation = new Evaluation(note, matiere);
                windowsHandler.etudiant.ajouteEvaluations(evaluation);
                ecole.ajouteEtudiants(windowsHandler.etudiant);
                windowsHandler.etudiantListTable.setTable();

            } else {
                JOptionPane.showMessageDialog(windowsHandler, "Note n'est pas nomero !");
            }
        }
        if (buttonHandler.getText().equals("Ajout Nouvel Etudiant")) {
            windowsHandler.panelAddEtudiantForm.setVisible(true);
            windowsHandler.panelAddEvaluations.setVisible(false);
        }
    }
}

