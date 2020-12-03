package main.watcher;

import main.Beobachter;
import main.Messwert;

public class KonsolenBeobachter extends BeobachterBuch {

    public KonsolenBeobachter(Messwert messwert, String beobachterName) {
        super(messwert, beobachterName);
    }

}
