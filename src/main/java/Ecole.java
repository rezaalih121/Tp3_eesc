import java.util.Collections;
import java.util.HashSet;

public class Ecole {
    protected String nomEcole;
    protected String adresse;

    public float getMoyenne_globale_notes() {
        return moyenne_globale_notes;
    }

    public void setMoyenne_globale_notes(float moyenne_globale_notes) {
        this.moyenne_globale_notes = moyenne_globale_notes;
    }

    protected float moyenne_globale_notes;
    protected HashSet<Etudiant> listeEtudiant = new HashSet<Etudiant>();

    public Ecole(String ecoleNom, String adresse) {
        this.nomEcole = ecoleNom;
        this.adresse = adresse;
    }

    public Ecole(String ecoleNom, String adresse, HashSet<Etudiant> listeEtudiant) {
        this.nomEcole = ecoleNom;
        this.adresse = adresse;
        this.listeEtudiant = listeEtudiant;
    }

    public String getNomEcole() {
        return nomEcole;
    }

    public void setNomEcole(String nomEcole) {
        this.nomEcole = nomEcole;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public HashSet<Etudiant> getListeEtudiant() {
        return listeEtudiant;
    }

    public void addEtudiant(Etudiant listeEtudiant) {
        this.listeEtudiant.add(listeEtudiant);
    }

    public void ajouteEtudiants(Etudiant ... etudiants){
        Collections.addAll(listeEtudiant , etudiants);
    }

    public float moyenneGlobale(){

        float somme = 0;
        for (Etudiant etudiant:listeEtudiant) {
            somme += etudiant.moyenneNotes();
        }
        moyenne_globale_notes = somme/listeEtudiant.size();
        return moyenne_globale_notes;
    }
}
