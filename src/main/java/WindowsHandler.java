import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

public class WindowsHandler extends JFrame implements WindowListener, WindowStateListener, WindowFocusListener {

    public WindowsHandler(String title, Ecole ecole) throws HeadlessException {
        super(title);
        ecole = ecole;
    }

    private Ecole ecole;

    private void addComponentsToPane() {
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        panel = (JPanel) this.getContentPane();
        setBounds(0, 0, 500, 500);

        panel.setPreferredSize(new Dimension(500, 500));
        panel2.setPreferredSize(new Dimension(400, 300));

        panel.setLayout(new FlowLayout());
        panel2.setLayout(new FlowLayout());

        JScrollPane scrollPane = new JScrollPane(new TableHandler(ecole));

        JLabel headerLabel = new JLabel("Application de l`ecole " + ecole.getNomEcole() + " a " + ecole.getAdresse());
        headerLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        headerLabel.setFont(new Font("Broadway", Font.BOLD, 21));

        JLabel moyenneLabel = new JLabel("Moyenne de globale notes :  " + 0);
        ButtonHandler moyenneBtn = new ButtonHandler("Calculez Moyenne Note");
        moyenneLabel.setLabelFor(moyenneBtn);


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
