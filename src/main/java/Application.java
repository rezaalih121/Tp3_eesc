

public class Application {


    public static Ecole ecole = new Ecole("EESC", "LAXOU");

    public static void main(String args[]) {

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

        ecole.getMoyenne_globale_notes();
        xmlDbFileHandler.saveObjectsToXMLFile(ecole);

    }

}
