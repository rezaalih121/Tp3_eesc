import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableHandler extends JTable implements ActionListener {

    protected Ecole ecole;


    public TableHandler(Ecole ecole) {
        this.ecole = ecole;
        setTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public JTable setTable() {


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
        this.setModel(model);

        this.setShowGrid(true);
        this.setShowVerticalLines(true);
        this.getScrollableTracksViewportHeight();
        this.getAutoscrolls();
        this.setBounds(0, 0, 300, 200);
        this.getColumn(column[0]).setMaxWidth(40);
        this.getColumn(column[1]).setMaxWidth(30);
        this.repaint();
        return this;
    }
}
