package main;

import main.watcher.BeobachterBuch;

import java.util.ArrayList;

public class Messwert {

    private Double wert;

    private ArrayList<BeobachterBuch> beobachterListe = new ArrayList<>();

    public void registriere(BeobachterBuch beobachter)
    {
        beobachterListe.add(beobachter);
    }

    public void entferne(BeobachterBuch beobachter)
    {
        beobachterListe.remove(beobachter);
    }

    public void benachrichtige()
    {
        for(BeobachterBuch b: beobachterListe){
            b.aktualisiere();
        }
    }

    public double getWert()
    {
        return wert;
    }

    public void setWert(double neuerWert){
        wert = neuerWert;
        this.benachrichtige();
    }

}
