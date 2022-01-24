package Devoir;
//Guillaume Pouvreau & Axel Vergognan
//TP3B1
import java.io.*;
public class Liste {

    // Les attributs
    boolean listeVide;
    Arbre info;
    Liste reste;

    //Les constructeurs
    Liste (Arbre a, Liste l){
        listeVide=false;
        info = a;
        reste = l;
    };

    Liste (){
        listeVide = true;
    };

    //les méthodes : accesseurs
    public boolean vide () {
        return listeVide;
    };
    // pour les deux suivants, lever des exceptions pour vide s'ils savent le faire!
    public Arbre tete (){
        if (!(listeVide)){
            return info;}
        else {return new Arbre();}
    };

    public Liste reste () {
        if (!(listeVide)){
            return reste;}
        else {Liste lv=new Liste(); return (lv);}
    };

    // les autres méthodes
    public Liste prefixer (Arbre a){
        Liste l = new Liste (a, this);
        return l;
    };

    public void affiche () {
        if (!(this.vide())){
            System.out.print ("( ");
            this.aff();
            System.out.print (")");
            System.out.println ("");
        };
    };

    public void aff () {
        if (!(this.vide())){
            System.out.print(this.tete() + " ");
            this.reste().aff();
        };
    };

    public static boolean appartient (Arbre a, Liste uneListe){
        if (uneListe.vide())
        {return false;}
        else { if (uneListe.tete() == a)
        {return true;}
        else {return appartient (a, uneListe.reste());}
        }
    };

    public static Liste rechercher (Arbre a, Liste uneListe){
        if (uneListe.vide())
        {return uneListe;}
        else { if (uneListe.tete() == a)
        {return uneListe;}
        else {return rechercher (a, uneListe.reste());}
        }
    };

    public static Liste insererOrd (Arbre a, Liste l){
        if (l.vide())
        {return l.prefixer(a);}
        else {
            if (l.tete().info()<a.info()) {return l.prefixer(a);}
        else {return insererOrd(a, l.reste).prefixer(l.tete());}
        }
    };

    public static Liste supprimerOrd (Arbre a, Liste uneListe){
        if (uneListe.vide())
        {return uneListe;}
        else {if (uneListe.tete()==a)
        {return uneListe.reste();}
        else {if (uneListe.tete().info()>a.info())
        {return uneListe;}
        else {return supprimerOrd(a, uneListe.reste()).prefixer(uneListe.tete());}
        }
        }
    };

}
