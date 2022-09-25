import java.util.Collections;
import java.util.HashSet;

public class Etudiant extends Personne{

    protected HashSet<Evaluation> listeEvaluations = new HashSet<Evaluation>();

    public Etudiant(String nom, int age) {
        super(nom, age);
    }

    public HashSet<Evaluation> getListeEvaluations() {
        return listeEvaluations;
    }

    public void addListeEvaluations(Evaluation listeEvaluations) {
        this.listeEvaluations.add(listeEvaluations);
    }

    public void ajouteEvaluations(Evaluation ... evaluations) {
        Collections.addAll(listeEvaluations , evaluations);
    }

    public float moyenneNotes(){
        float sommeNote = 0 ;
        for (Evaluation evaluation: listeEvaluations) {
            sommeNote += evaluation.note;
            
        }
        return sommeNote/listeEvaluations.size();
    }
}
