package co.myechelon.trial;

/**
 * Created by Teacher on 5/13/2019.
 */

public class Incomes  {
    int id;
    String income;
    String amount;
    String date;

    public Incomes() {
    }

    public Incomes(int id, String income, String amount, String date) {
        this.id = id;
        this.income = income;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getIncome() {
        return income;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }
    @Override
    public String toString(){
        return date;
    }
}
