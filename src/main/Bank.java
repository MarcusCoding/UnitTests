package main;

import java.util.ArrayList;
import java.util.Date;

public class Bank {

    private static Bank bank;

    private String blz;

    private String name;

    private Date createdAt;

    private ArrayList<Konto> Konto;

    private Bank(){
        this.blz = "70090100";
        this.name = "Bank of Erfurt";
        this.createdAt = new Date();
    }

    public static Bank getInstance(){
        if(bank == null)
            bank = new Bank();
        return bank;
    }

    public String getBLZ(){
        return blz;
    }

    public String getName(){
        return name;
    }

    public Date getCreatedAt(){
        return createdAt;
    }

}
