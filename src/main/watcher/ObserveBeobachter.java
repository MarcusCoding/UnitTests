package main.watcher;

import main.Beobachter;
import main.Messwert;

public class ObserveBeobachter implements Beobachter {

    private Messwert anzeigeWert;

    public ObserveBeobachter(Messwert messwert){
        anzeigeWert = messwert;
        //this.anzeigeWert.registriere(this);
    }

    @Override
    public void aktualisere() {
        double neuerWert = anzeigeWert.getWert();
        System.out.printf("Neuer Messwert [KonsolenBeobachter]: %.2f%n", neuerWert);
    }
}
