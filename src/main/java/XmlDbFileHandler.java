import java.io.File;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XmlDbFileHandler {

    private File inputFile;
    private static DocumentBuilderFactory dbFactory;
    private static DocumentBuilder dBuilder;
    private static Document doc;

    public XmlDbFileHandler() {

    }


    private static String getPath() {
        return System.getProperty("user.home") + "\\IdeaProjects\\Tp3_eesc\\src\\main\\java" + File.separator + "properties.xml";
    }

    protected Ecole loadObjectsFromXmlFile() {
        Ecole ecole = null;
        String nomEcole = "";
        String adresseEcole = "";

        Etudiant etudiant = null;

        String nomEtudiant = "";
        int ageEtudiant = 0;


        try {

            File inputFile = new File(getPath());
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            nomEcole = doc.getElementsByTagName("nomEcole").item(0).getTextContent();
            adresseEcole = doc.getElementsByTagName("adresse").item(0).getTextContent();
            ecole = new Ecole(nomEcole, adresseEcole);

            System.out.println(nomEcole + "   " + adresseEcole);
            NodeList nList = doc.getElementsByTagName("etudiant");
            System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                System.out.println("\n Current element : " + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    nomEtudiant = eElement.getElementsByTagName("nom")
                            .item(0)
                            .getTextContent();
                    ageEtudiant = Integer.parseInt(eElement
                            .getElementsByTagName("age")
                            .item(0)
                            .getTextContent());

                    etudiant = new Etudiant(nomEtudiant, ageEtudiant);

                    NodeList evaluationNodeList = eElement.getElementsByTagName("evaluation");
                    System.out.println("Nome Etudiant : " + etudiant.getNom() + " age : " + etudiant.getAge());
                    System.out.println(evaluationNodeList.getLength());


                    for (int it = 0; it < evaluationNodeList.getLength(); it++) {
                        Evaluation evaluation;
                        String eMatiere = "";
                        float eNote = 0;
                        Node nNode2 = evaluationNodeList.item(it);
                        if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement2 = (Element) nNode2;


                            eNote = Float.parseFloat(eElement2
                                    .getElementsByTagName("note")
                                    .item(0)
                                    .getTextContent());

                            eMatiere = eElement2
                                    .getElementsByTagName("matiere")
                                    .item(0)
                                    .getTextContent();

                            evaluation = new Evaluation(eNote, eMatiere);
                            etudiant.ajouteEvaluations(evaluation);
                            System.out.println("Matiere : " + evaluation.getMatiere() + " Note : " + evaluation.getNote());
                        }
                    }
                }
                ecole.ajouteEtudiants(etudiant);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ecole.getMoyenne_globale_notes();
        return ecole;
    }

    protected void saveObjectsToXMLFile(Ecole ecole) {
        try {
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.newDocument();

            // root element
            Element rootElement = doc.createElement("ecole");
            doc.appendChild(rootElement);

            Element nomElement = doc.createElement("nomEcole");
            nomElement.appendChild(doc.createTextNode(ecole.getNomEcole()));
            rootElement.appendChild(nomElement);

            Element adresseEement = doc.createElement("adresse");
            adresseEement.appendChild(doc.createTextNode(ecole.getAdresse()));
            rootElement.appendChild(adresseEement);

            Element moyenneEement = doc.createElement("moyenne_globale_notes");
            moyenneEement.appendChild(doc.createTextNode(String.valueOf(ecole.moyenneGlobale())));
            rootElement.appendChild(moyenneEement);


            for (Etudiant etudiant : ecole.getListeEtudiant()) {
                Element etudiantEement = doc.createElement("etudiant");

                Element nomeEement = doc.createElement("nom");
                nomeEement.appendChild(doc.createTextNode(etudiant.getNom()));
                etudiantEement.appendChild(nomeEement);

                Element ageEement = doc.createElement("age");
                ageEement.appendChild(doc.createTextNode(String.valueOf(etudiant.age)));
                etudiantEement.appendChild(ageEement);


                for (Evaluation evaluation : etudiant.getListeEvaluations()) {
                    Element evaluationEement = doc.createElement("evaluation");

                    Element matiereEement = doc.createElement("matiere");
                    matiereEement.appendChild(doc.createTextNode(evaluation.getMatiere()));
                    evaluationEement.appendChild(matiereEement);

                    Element noteEement = doc.createElement("note");
                    noteEement.appendChild(doc.createTextNode(String.valueOf(evaluation.getNote())));
                    evaluationEement.appendChild(noteEement);

                    etudiantEement.appendChild(evaluationEement);
                }
                rootElement.appendChild(etudiantEement);
            }


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(getPath()));
            transformer.transform(source, result);

            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
