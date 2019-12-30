package co.myechelon.trial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teacher on 5/10/2019.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION =5;
    private static final String DATABASE_NAME = "expenseManager";
    private static final String TABLE_EXPENSES = "expenses";
    private static final String KEY_ID = "id";
    private static final String EXPENSE = "expense";
    private static final String AMOUNT = "amount";
    private static final String DATE = "date";
    private static final String CAT = "cat";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_EXPENSES + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + EXPENSE + " TEXT,"
                + AMOUNT + " INTEGER,"
                + DATE + " TEXT,"
                + CAT + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSES);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    long addExpense(Expenses expenses) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EXPENSE, expenses.getExpense()); // Contact Name
        values.put(AMOUNT, expenses.getAmount()); // Contact Name
        values.put(DATE, expenses.getDate());
        values.put(CAT, expenses.getCat());// Contact Phone

        // Inserting Row
        long d= db.insert(TABLE_EXPENSES, null, values);
        //2nd argument is String containing nullColumnHack

        db.close(); // Closing database connection
        return d;
    }

    // code to get the single contact

    // code to get all contacts in a list view
    public List<Expenses> getAllExpenses(String t) {
        List<Expenses> expensesList = new ArrayList<Expenses>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EXPENSES + " where date = '" + t + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Expenses expenses = new Expenses();
                expenses.setId(Integer.parseInt(cursor.getString(0)));
                expenses.setExpense(cursor.getString(1));
                expenses.setAmount(cursor.getString(2));
                expenses.setDate(cursor.getString(3));
                expenses.setCat(cursor.getString(4));
                // Adding contact to list
                expensesList.add(expenses);
                Log.d("Data: ",expenses.getExpense()+expenses.getAmount()+expenses.getDate());
            } while (cursor.moveToNext());
        }
db.close();
        // return contact list
        return expensesList;
    }
  public int getTotal() {

       // Select All Query
       String selectQuery = "select sum(amount) from expenses";

      SQLiteDatabase db = this.getWritableDatabase();
      Cursor cursor = db.rawQuery(selectQuery, null);
      int j;

      if (cursor.moveToFirst()) {

          j = cursor.getInt(0);

      }else {
          j=-1;
      }



      db.close();
      return j;
   }

    // code to update the single contact
    public int updateExpenses(Expenses expenses) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EXPENSE, expenses.getExpense()); // Contact Name
        values.put(AMOUNT, expenses.getAmount()); // Contact Name
        values.put(DATE, expenses.getDate());
        values.put(CAT, expenses.getCat());

        // updating row
        return db.update(TABLE_EXPENSES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(expenses.getId()) });
    }


    public void deleteExpenses(Expenses expenses) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EXPENSES, KEY_ID + " = ?",
                new String[] { String.valueOf(expenses.getId()) });
        db.close();
    }

//today

    public int getToday(String t) {

        // Select All Query
        String selectQuery = "select sum(amount) from expenses where date = '" + t + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int j;

        if (cursor.moveToFirst()) {

            j = cursor.getInt(0);

        }else {
            j=-1;
        }



        db.close();
        return j;
    }
//chart
    public int getChart(String t,String g,int x) {

        // Select All Query
        String selectQuery = "select sum(amount) from expenses where (date like '%" + t + "%' and CAST(date AS TEXT) like '%" + x + "' ) or (date like '%" + g + "%' and CAST(date AS TEXT) like '%" + x + "' )";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int j;

        if (cursor.moveToFirst()) {

            j = cursor.getInt(0);

        }else {
            j=-1;
        }



        db.close();
        return j;
    }






//
    public int getCatTotal(String t) {

        // Select All Query
        String selectQuery = "select SUM(amount) from expenses where cat = '" + t + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        int j;

            if (cursor.moveToFirst()) {

                    j = cursor.getInt(0);

            }else {
                j=-1;
            }

        db.close();
        return j;
    }


//get

    public List<Expenses> getAllCatSelected(String s,String h) {
        List<Expenses> expensesList = new ArrayList<Expenses>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EXPENSES + " where cat = '" + s + "' and date = '" + h + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Expenses expenses = new Expenses();
                expenses.setId(Integer.parseInt(cursor.getString(0)));
                expenses.setExpense(cursor.getString(1));
                expenses.setAmount(cursor.getString(2));
                expenses.setDate(cursor.getString(3));
                expenses.setCat(cursor.getString(4));
                // Adding contact to list
                expensesList.add(expenses);
                Log.d("Data: ",expenses.getExpense()+expenses.getAmount()+expenses.getDate());
            } while (cursor.moveToNext());
        }
        db.close();
        // return contact list
        return expensesList;
    }


    public int getWeekAvg(String t,String k) {

        // Select All Query
        String selectQuery = "select SUM(amount) from expenses where date between '" + t + "' and '" +k+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        int j;

        if (cursor.moveToFirst()) {

            j = cursor.getInt(0);

        }else {
            j=-1;
        }

        db.close();
        return (j/4);
    }

    public int getCounts() {

        // Select All Query
        String selectQuery = "select * from expenses";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

            int c= cursor.getCount();
        cursor.close();
        return c;
    }
    public List<String> getAllDates(String s) {
        List<String> dateList = new ArrayList<String>();
        // Select All Query
        // Select All Query
        String selectQuery = "SELECT * FROM expenses WHERE cat= '" +s+ "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // Adding contact to list

                // Adding contact to list

                dateList.add(cursor.getString(3));


            } while (cursor.moveToNext());
        }

        db.close();
        // return contact list
        return dateList;
    }
    public List<String> getAllDate() {
        List<String> dateList = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT * FROM expenses order by id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // Adding contact to list

                dateList.add(cursor.getString(3));


            } while (cursor.moveToNext());
        }

        db.close();
        // return contact list
        return dateList;
    }

}
