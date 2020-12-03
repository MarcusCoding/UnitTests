package main;

import java.math.BigInteger;

public class Konto {

    private double kontostand;
    private final String iban;

    public Konto(String iban) {
        if(isIBANValid(iban)){
            this.iban = iban;
        }else{
            this.iban = null;
        }
    }

    public double getKontostand() {
        return kontostand;
    }

    public String getIban() {
        return iban;
    }

    public void zahleEin(double betrag) {
        //falls Betrag negativ, Einzahlung verhindern
        if(betrag > 0){
            kontostand += betrag;
        }
    }

    public boolean ueberweisen(double betrag, Konto konto){

        //Mein Kontostand zu niedrig
        if(kontostand < betrag){
            return false;
        }

        if(konto == null){
            return false;
        }

        if(hebeAb(betrag)){
            konto.zahleEin(betrag);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean hebeAb(double betrag) {
        if(kontostand < betrag){
            return false;
        }else{
            if(betrag > 0){
                kontostand -= betrag;
                return true;
            }else{
                //Negative Beiträge können nicht abgehoben werden
                return false;
            }
        }
    }

    public boolean isIBANValid(String IBAN){

        if(IBAN == null){
            return false;
        }

        if(IBAN.length() < 22){
            return false;
        }

        //REGEX
        //[A-Z] 1 Buchstabe zwischen A-Z
        ///[A-Z] 1 Buchstabe zwischen A-Z
        ///[\d]{20} Zahlen von 0 - 9, davon genau 20 Stück
        if(!IBAN.matches("[A-Z][A-Z][\\d]{20}")){
            return false;
        }

        //Ländercode ersetzen
        String countryValue = IBAN.substring(0, 2);
        IBAN = IBAN.replace(countryValue, String.format("%d%d", Character.getNumericValue(countryValue.charAt(0)), Character.getNumericValue(countryValue.charAt(1))));
        String first6Chars = IBAN.substring(0, 6);

        //erste 6 Zeichen ersetzen
        IBAN = IBAN.replace(first6Chars, "");

        //finale Zahl zusammenbauen aus IBAN und den ersten 6 Stellen am Ende
        IBAN = String.format("%s%s", IBAN, first6Chars);

        //Konvertierung zu BigInt und Modulo 97
        BigInteger parsedIBAN = new BigInteger(IBAN);
        BigInteger modulo = parsedIBAN.mod(new BigInteger("97"));
        return modulo.equals(new BigInteger("1"));
    }

    public boolean isIBANStringValid(String IBAN){

        if(IBAN == null){
            return false;
        }

        if(IBAN.length() < 22){
            return false;
        }

        //Ländercode replace
        String countryValue = IBAN.substring(0, 2);
        IBAN = IBAN.replace(countryValue, String.format("%d%d", Character.getNumericValue(countryValue.charAt(0)), Character.getNumericValue(countryValue.charAt(1))));
        String first6Chars = IBAN.substring(0, 6);

        //erste 6 Zeichen ersetzen
        IBAN = IBAN.replace(first6Chars, "");

        //finale Zahl zusammenbauen aus IBAN und den ersten 6 Stellen am Ende
        IBAN = String.format("%s%s", IBAN, first6Chars);

        //Konvertierung zu BigInt und Modulo 97
        int modulo = mod(IBAN, 97);
        return modulo == 1;
    }


    static int mod(String num, int a)
    {
        // Initialize result
        int res = 0;

        // One by one process all digits of 'num'
        for (int i = 0; i < num.length(); i++)
            res = (res * 10 + (int)num.charAt(i)
                    - '0') % a;

        return res;

    }
}

