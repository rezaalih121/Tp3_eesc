public class Evaluation {
    protected float note;
    protected String matiere;


    protected enum listeMatieres {JAVA,PHP,PYTON,C,HTML};


    public Evaluation(float note, String matiere) {
        this.note = note;
        this.matiere = matiere;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }
}
