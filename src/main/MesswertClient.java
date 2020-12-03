package main;

import main.watcher.BeobachterBuch;
import main.watcher.KonsolenBeobachter;
import main.watcher.WebBeobachter;

public class MesswertClient {

    public static void main(String[] args) {

        Messwert neuerMesswert = new Messwert();

        //Tutorial von Tornau
     /*   KonsolenBeobachter beobachter = new KonsolenBeobachter(neuerMesswert);

        neuerMesswert.setWert(50.5);
        //WOW :D*/

        //Aufgabe aus dem Buch
        BeobachterBuch b1 = new WebBeobachter(neuerMesswert, "WebBeobachter");
        BeobachterBuch b2 = new KonsolenBeobachter(neuerMesswert, "KonsolenBeobachter");

        neuerMesswert.registriere(b1);
        neuerMesswert.registriere(b2);

        neuerMesswert.setWert(10.0);
        neuerMesswert.setWert(15.0);

    }

}
