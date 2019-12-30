package co.myechelon.trial;

import android.content.Context;

/**
 * Created by Teacher on 5/10/2019.
 */

public class Expenses {

    int id;
    String expense;
    String amount;
    String date;
    String cat;


    public Expenses() {
    }

    public Expenses(int id, String expense, String amount, String date,String cat) {
        this.id = id;

        this.expense = expense;
        this.amount = amount;
        this.date = date;
        this.cat = cat;

    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    @Override
    public String toString(){
        return date;
    }

}
