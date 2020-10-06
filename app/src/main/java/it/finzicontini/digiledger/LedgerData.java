package it.finzicontini.digiledger;

import java.util.Date;

public class LedgerData {
    private Date date;
    private Float amount;
    private String category;

    public LedgerData(Date d, Float a, String c){
        this.date = d ;
        this.amount = a;
        this.category=c;
    }
// Setters
    public void setDate(Date date) {
        this.date = date;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

// Getters
    public Date getDate() {
        return date;
    }

    public Number getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }
}
