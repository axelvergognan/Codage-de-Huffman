package Devoir;
//Guillaume Pouvreau & Axel Vergognan
//TP3B1

import java.util.*;
public class Arbre {
    //Attributs

    boolean arbreVide;
    int info;
    char lettre;
    Arbre fg;
    Arbre fd;

    //Constructeurs

    Arbre (){
        arbreVide = true;
    };

    Arbre (int e){
        arbreVide=false;
        info = e;
        fg = new Arbre();
        fd = new Arbre();
    };

    Arbre (int e, char l){
        arbreVide=false;
        info = e;
        lettre = l;
        fg = new Arbre();
        fd = new Arbre();
    }

    Arbre (int e, Arbre a1, Arbre a2){
        arbreVide=false;
        info = e;
        fg = a1;
        fd = a2;
    };

    //Méthodes

    public boolean vide () {
        return arbreVide;
    };

    public int info (){
        if (!(arbreVide)){
            return info;}
        else {return 0;}  // idéalement une exception, mais s'ils ne sconnaissent pas...
    };

    public Arbre fg () { //là aussi il faudrait une exception...
        return fg;
    };

    public Arbre fd () { // et là
        return fd;
    };

    public static void afficheInfixe (Arbre unArbre) {// pas très lisible!
        afficheInf(unArbre);
        System.out.println("");
    };

    // TD2 : pas à faire...
    public boolean estFeuille(){
        if (this.vide())
        {return false;}
        else {return this.fg().vide() && this.fd().vide();}
    };

    public int somme(){
        if (this.vide())
        {return 0;}
        else {return this.info() + this.fg().somme() + this.fd().somme();}
    };

    public boolean contien(char c){
        if (vide()){return false;}
        if (c == lettre){
            return true;
        }
        return this.fg.contien(c) || this.fd.contien(c);
    };

    public static void afficheInf(Arbre unArbre) { // un peu plus lisible...
        if (!(unArbre.vide()))
        {
            System.out.print("(");
            afficheInf(unArbre.fg());
            System.out.print(unArbre.info());
            afficheInf(unArbre.fd());
            System.out.print(")");
        };
    };

    public static void afficheGraph(Arbre unArbre) { // affiche un arbre couché...
        System.out.println("=================================");
        if (!(unArbre.vide()))
        {	afficheGr(unArbre,0);};
        System.out.println("=================================");
    };

    public static void afficheGr(Arbre unArbre, int d) {
        if (!(unArbre.vide())){
            afficheGr(unArbre.fd(),d+1);
            for (int i=0;i<d;i++){
                System.out.print("      ");};
            System.out.println(unArbre.info());
            afficheGr(unArbre.fg(),d+1);
        };
    };

   public boolean contenue (char c){
        if (this.vide()){return false; };
        if(c == 0) return false;
        if (c==this.lettre){
            return true;
        };
        return (this.fg().contenue(c) || this.fd().contenue(c));
    };

}