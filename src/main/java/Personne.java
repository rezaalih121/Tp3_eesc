public abstract class Personne {

    protected String nom;
    protected int age;

    protected boolean isMajiur = false;

    public Personne(String nom, int age) {
        this.nom = nom;
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {

        if (age > 18)
            isMajiur = true;
        this.age = age;
    }
}
