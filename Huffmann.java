package Devoir;
//Guillaume Pouvreau & Axel Vergognan
//TP3B1

//Les imports
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Huffmann {

	//déclaration de la HasMap
    public static Liste HashMapToListe(HashMap<Character, Integer> hash_map){
        Liste liste = new Liste();
        for(char c : hash_map.keySet()){
            liste = liste.prefixer(new Arbre(hash_map.get(c),c));
        }
        return liste;
    }

    public static Arbre ListeToArbre(Liste liste){
        if(liste.reste().vide()){return liste.tete();}
            Arbre a1 = liste.tete();
            Arbre a2 = liste.reste().tete();
            liste = Liste.supprimerOrd(liste.tete(), liste);
            liste = Liste.supprimerOrd(liste.tete(), liste);
            liste = Liste.insererOrd(new Arbre(a1.info()+a2.info(), a1, a2),liste);
            return ListeToArbre(liste);
    }

    public static HashMap<Character, Integer> readCSV(String nameFile) throws IOException{
        String line = "";
        String splitBy = ";";
        BufferedReader br = new BufferedReader(new FileReader("src/Devoir/"+nameFile+".csv"));
        HashMap<Character, Integer> hash_map = new HashMap<Character, Integer>();
        while ((line = br.readLine()) != null)   //returns a Boolean value
        {
            String[] val = line.split(splitBy);
            hash_map.put(val[0].charAt(0),Integer.parseInt(val[1]));
        }
        return hash_map;
    }

    //Déclaration de la fonction pour lire un fichier texte d'un dossier
    public static String readFile(String nameFile) throws IOException {
        File file=new File("src/Devoir/"+nameFile+".txt");
        Scanner sc=new Scanner(file);
        String phrase = "";
        while(sc.hasNextLine()){
            phrase += sc.nextLine();
        }
        return phrase;
    }

    //fonction pour crypter un texte 
    public static String cryptage(Arbre arbre_crypto, String text){
        String code = "";
        for(char c : text.toCharArray()){
            code+=encoder(c, arbre_crypto, "");
        }
        return code;
    }

    //Fonction pour encoder les caractère d'un texte par leurs valeur en 0 et 1
    public static String encoder(char c, Arbre arbre_crypto, String anc){
        String code = anc;
        if(arbre_crypto.lettre == c) return code;
        else if(arbre_crypto.fg.contien(c)){
            code+="0";
            return encoder(c,arbre_crypto.fg,code);
        }
        else if (arbre_crypto.fd.contien(c)) {
            code+= "1";
            return encoder(c, arbre_crypto.fd, code);
        }
        return code;
    }

    //Fonction pour décoder les suites de 0 et 1 pour les remplacer par le caractère correspondant
    public static String decodeur(String crypter, Arbre arbre_crypto){
        Arbre abr = arbre_crypto;
        String  text ="";
        for(int compteur =0; compteur < crypter.length(); compteur++){
            if(crypter.charAt(compteur) == '0'){
                abr = abr.fg();
            }
            if(crypter.charAt(compteur) == '1'){
                abr = abr.fd();
            }
            if(abr.lettre!=0){
                text += abr.lettre;
                abr = arbre_crypto;
            }
        }
        return text;
    }

    public static HashMap<Character, Integer> TextToHashMap(String text) {
        HashMap<Character,Integer> hash_map = new HashMap<Character,Integer>();
        for(char c : text.toCharArray()) {
            if(hash_map.containsKey(c)) {
                hash_map.put(c, hash_map.get(c)+1);
            }
            else hash_map.put(c, 1);
        }
        return hash_map;
    }

    //fonction main pour lancer soit le cryptage soit le décryptage
    public static void main(String []args) throws IOException {
        HashMap<Character,Integer> freq = readCSV("frequences");
        Arbre arbre_crypto = ListeToArbre(HashMapToListe(freq));
        Scanner s1 = new Scanner(System.in);
        System.out.print("Saisissez crypter ou décrypter (1 ou 2) : ");
        int choice = s1.nextInt();
        if(choice == 1){
            System.out.println("Cryptage du document 'texte-à-coder.txt' : "+cryptage(arbre_crypto, readFile("texte-à-coder")));
        }
        else{
            System.out.println(decodeur(readFile("texte-à-decoder"), arbre_crypto));
        }
        //Avec la méthode TexttoHashMap qui transforme un texte en hashmap
        HashMap<Character,Integer> freq2 = TextToHashMap(readFile("texte-à-coder"));
    }
}