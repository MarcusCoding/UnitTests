package main.watcher;

import main.Beobachter;
import main.Messwert;

public class BeobachterBuch {

    protected Messwert angezeigterWert;

    private String beobachterName;

    public BeobachterBuch(Messwert messwert, String beobachterName){
        angezeigterWert = messwert;
        this.beobachterName = beobachterName;
    }

    public void aktualisiere(){
        double neuerWert = angezeigterWert.getWert();
        System.out.printf("Neuer Messwert [%s]: %.2f%n", beobachterName, neuerWert);
    }


}
