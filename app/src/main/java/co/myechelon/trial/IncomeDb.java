package co.myechelon.trial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teacher on 5/13/2019.
 */

public class IncomeDb extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "incomeManager";
    private static final String TABLE_INCOME = "income";
    private static final String KEY_ID = "id";
    private static final String INCOME = "amount";
    private static final String AMOUNT = "source";
    private static final String DATE = "date";


    public IncomeDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_INCOME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + INCOME + " TEXT,"
                + AMOUNT + " INTEGER,"
                + DATE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_INCOME);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    long addIncomes(Incomes incomes) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(INCOME, incomes.getIncome()); // Contact Name
        values.put(AMOUNT, incomes.getAmount()); // Contact Name
        values.put(DATE, incomes.getDate()); // Contact Phone

        // Inserting Row
        long d = db.insert(TABLE_INCOME, null, values);
        //2nd argument is String containing nullColumnHack

        db.close(); // Closing database connection
        return d;
    }

    // code to get the single contact

    // code to get all contacts in a list view
  public List<Incomes> getAllIncomes(String s) {
      List<Incomes> incomesList = new ArrayList<Incomes>();
      // Select All Query
      String selectQuery = "SELECT  * FROM " + TABLE_INCOME +" where date = '" +s+ "'";

      SQLiteDatabase db = this.getWritableDatabase();
      Cursor cursor = db.rawQuery(selectQuery, null);

      // looping through all rows and adding to list
      if (cursor.moveToFirst()) {
          do {
              Incomes incomes = new Incomes();
              incomes.setId(Integer.parseInt(cursor.getString(0)));
              incomes.setIncome(cursor.getString(1));
              incomes.setAmount(cursor.getString(2));
              incomes.setDate(cursor.getString(3));
              // Adding contact to list
              incomesList.add(incomes);
              Log.d("Data: ", incomes.getIncome() + incomes.getAmount() + incomes.getDate());
          } while (cursor.moveToNext());
      }
      db.close();
      // return contact list
      return incomesList;
  }
    public int getChart(String t,String g,int x) {

        // Select All Query
        String selectQuery = "select sum(source) from income where (date like '%" + t + "%' and CAST(date AS TEXT) like '%" + x + "' ) or (date like '%" + g + "%' and CAST(date AS TEXT) like '%" + x + "' )";

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
    public int getTotal2() {

        // Select All Query
        String selectQuery = "select sum(source) from income";

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
   public int updateIncomes(Incomes incomes) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(INCOME, incomes.getIncome()); // Contact Name
        values.put(AMOUNT, incomes.getAmount()); // Contact Name
        values.put(DATE, incomes.getDate());

        // updating row
        return db.update(TABLE_INCOME, values, KEY_ID + " = ?",
                new String[] { String.valueOf(incomes.getId()) });
    }

    public void deleteIncomes(Incomes incomes) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_INCOME, KEY_ID + " = ?",
                new String[] { String.valueOf(incomes.getId()) });
        db.close();
    }
    public List<String>getALLDates(){
        List<String> dateList = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT * FROM income ORDER BY id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
;

                // Adding contact to list
                dateList.add(cursor.getString(3));


            } while (cursor.moveToNext());
        }

        db.close();
        // return contact list
        return dateList;
    }

}