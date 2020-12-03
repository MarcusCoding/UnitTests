package test;

import main.Bank;
import main.Konto;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class KontoTest {

    Konto konto1 = null;
    Konto konto2 = null;
    Konto konto3 = null;

    static ArrayList<String> ibans = new ArrayList<>() {{
        //falsch
        add("DE221001005001234456789");
        add("AL902081100800000010393");
        //richtig
        add("DE12500105170648489890");
        add("DE07123412341234123412");
        add("DE68210501700012345678");
        add("GB33210520201555555555");
    }};

    @BeforeEach
    void initializeService() {
        this.konto1 = new Konto("DE08700901001234567890");
        this.konto2 = new Konto("DE41500105170123456789");
        this.konto3 = new Konto("41500105170123456789");
    }


    @org.junit.jupiter.api.Test
    public void getBankBLZ(){
        Bank bankofErfurt = Bank.getInstance();
        assertEquals("70090100", bankofErfurt.getBLZ());
    }

    @org.junit.jupiter.api.Test
    public void getBankName(){
        Bank bankofErfurt = Bank.getInstance();
        assertEquals("Bank of Erfurt", bankofErfurt.getName());
    }

    @org.junit.jupiter.api.Test
    public void getBankInstance(){
        Bank bankofErfurt = Bank.getInstance();
        Bank bank2 = Bank.getInstance();
        assertEquals(bankofErfurt.getCreatedAt(), bank2.getCreatedAt());
    }

    @org.junit.jupiter.api.Test
    public void testIBANUebernommen() {
        assertEquals("DE08700901001234567890", konto1.getIban());
    }

    @org.junit.jupiter.api.Test
    public void testIBANNichtUebernommen() {
        assertNull(konto3.getIban());
    }

    // Kontostand am Anfang 0
    @org.junit.jupiter.api.Test
    public void testKontostandAmAnfang() {
        assertEquals(0, konto1.getKontostand());
    }

    // einzahlen
    @org.junit.jupiter.api.Test
    public void testZahleEin() {
        konto1.zahleEin(200);
        assertEquals(200.00, konto1.getKontostand());
    }

    // auszahlen
    @org.junit.jupiter.api.Test
    public void testHebeAb() {
        konto1.zahleEin(200);
        konto1.hebeAb(150);
        assertEquals(50.00, konto1.getKontostand());
    }

    // auszahlen Rückgabe true
    @org.junit.jupiter.api.Test
    public void testHebeAbTrue() {
        konto1.zahleEin(200);
        assertTrue(konto1.hebeAb(150));
    }

    // auszahlen Rückgabe false
    @org.junit.jupiter.api.Test
    public void testHebeAbFalse() {
        konto1.zahleEin(200);
        assertFalse(konto1.hebeAb(250));
    }

    // auszahlen wenn Betrag auf Konto zu gering
    @org.junit.jupiter.api.Test
    public void testUeberziehe() {
        konto1.zahleEin(200);
        konto1.hebeAb(250);
        assertEquals(200, konto1.getKontostand());
    }

    //test cases: negative values
    @org.junit.jupiter.api.Test
    public void testNegativeZahleEin() {
        konto1.zahleEin(-200);
        assertEquals(0, konto1.getKontostand());
    }

    @org.junit.jupiter.api.Test
    public void testNegativeHebeAb() {
        assertFalse(konto1.hebeAb(-200));
    }

    //neue Testfälle für Aufgabe 2

    //Überweisung auf beliebiges anderes Konto
    @org.junit.jupiter.api.Test
    public void testUeberweisen(){
        konto1.zahleEin(200);
        konto1.ueberweisen(200, konto2);
        assertEquals(200, konto2.getKontostand());
    }

    //Überweisung auf beliebiges anderes Konto
    @org.junit.jupiter.api.Test
    public void testUeberweisenFehler(){
        konto1.zahleEin(150);
        assertFalse(konto1.ueberweisen(200, konto2));
    }

    //Prüfung der IBAN auf Gültigkeit
    @org.junit.jupiter.api.Test
    public void testIsIBANValid() {
        assertTrue(konto1.isIBANValid("DE08700901001234567890"));
    }

    @org.junit.jupiter.api.Test
    public void testIsNullValid() {
        assertFalse(konto1.isIBANValid(null));
    }

    @org.junit.jupiter.api.Test
    public void testIsWrongIBANValid() {
        assertFalse(konto1.isIBANValid("3357333"));
    }

    @org.junit.jupiter.api.Test
    public void testIBANList() {
        int correctIBAN = 0;

        for (String iban : ibans) {
            if (konto1.isIBANValid(iban)) {
                correctIBAN++;
            }
        }
        assertEquals(3, correctIBAN);
    }

}