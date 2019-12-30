package co.myechelon.trial;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    List<Expenses> expensesList=new ArrayList<>();
    List<String> dateList=new ArrayList<>();
 List<Incomes> incomeList=new ArrayList<>();
 private Context context;
    Expenses expenses ;
    private RecyclerView recyclerView;
    private expensesAdapter expensesAdapter;
    private incomeAdapter incomeAdapter;
TextView tt,income;
    EditText x,m,y,z;
    Button sub,viewall,btn;
    String txt;
RelativeLayout in,ex;
    String txt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        final DatabaseHandler bHandler = new DatabaseHandler(this);





        setSupportActionBar(toolbar);
        IncomeDb idd = new IncomeDb(this);
        final Bundle bundle = getIntent().getExtras();
        Spinner spinner=(Spinner)findViewById(R.id.spinner3);
        setTitle(bundle.getString("trans"));
        dateList=bHandler.getAllDates(bundle.getString("trans"));
        Set<String> set=new HashSet<String>(dateList);
        dateList.clear();
        dateList.addAll(set);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,R.layout.spinner_item,dateList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String x= parent.getItemAtPosition(position).toString();

                expensesList=bHandler.getAllCatSelected(bundle.getString("trans"),x);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                expensesAdapter = new expensesAdapter(expensesList,getApplicationContext());
                recyclerView.setAdapter(expensesAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
      //  txt=spinner.getSelectedItem().toString();




      //incomeList=idd.getAllIncomes();



       // incomeAdapter = new incomeAdapter(incomeList,getApplicationContext());


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        tt=(TextView) findViewById(R.id.exp1);
        income=(TextView) findViewById(R.id.income1);

        ex=(RelativeLayout) findViewById(R.id.expp);
        in=(RelativeLayout) findViewById(R.id.incomee);





            income.setText(idd.getTotal2() + "Rwf");
            tt.setText(bHandler.getCatTotal(bundle.getString("trans")) + "Rwf");







            in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }




    @Override
    public boolean onOptionsItemSelected(MenuItem Item) {
        switch (Item.getItemId()) {

            case R.id.add:
               openDialog();
                break;


        }
        int id = Item.getItemId();
        if (id==android.R.id.home){

            this.finish();
        }

        return super.onOptionsItemSelected(Item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void openDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_post);
        dialog.setTitle("post");
        dialog.show();

        final DatabaseHandler dbHandler = new DatabaseHandler(this);

        final IncomeDb idd = new IncomeDb(this);
        Calendar calendar= Calendar.getInstance();
        final String date= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());


        x=(EditText)dialog.findViewById(R.id.type);
        m=(EditText)dialog.findViewById(R.id.money);
        y=(EditText)dialog.findViewById(R.id.source);
        z=(EditText)dialog.findViewById(R.id.mony);
        sub =(Button)dialog.findViewById(R.id.submit);
        btn =(Button)dialog.findViewById(R.id.button);
        Spinner spinner=(Spinner)dialog.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.categories,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txt=parent.getItemAtPosition(position).toString();
                Toast.makeText(getBaseContext(),txt,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        viewall =(Button)dialog.findViewById(R.id.viewall);
        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(x.getText().toString().equals("") || m.getText().toString().equals("") ){

                    Toast.makeText(getApplicationContext(), "Fill in the blanks", Toast.LENGTH_LONG).show();
                }else {
                    Expenses expenses = new Expenses();


                    expenses.setExpense(x.getText().toString());
                    expenses.setAmount(m.getText().toString());
                    expenses.setDate(date);
                    expenses.setCat(txt);
                    if (dbHandler.addExpense(expenses) > 0) {

                        Toast.makeText(getApplicationContext(), "Expense saved", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                        Intent intent = new Intent(getBaseContext(), Main3Activity.class);
                        startActivity(intent);
                    }
                    x.setText("");
                    m.setText("");
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(y.getText().toString().equals("") || z.getText().toString().equals("") ){

                    Toast.makeText(getApplicationContext(), "Fill in the blanks", Toast.LENGTH_LONG).show();

                }else {

                    Incomes incomes = new Incomes();
                    incomes.setIncome(y.getText().toString());
                    incomes.setAmount(z.getText().toString());
                    incomes.setDate(date);
                    if (idd.addIncomes(incomes) > 0) {
                        Toast.makeText(getApplicationContext(), "Income Saved", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getBaseContext(), Main3Activity.class);
                        startActivity(intent);

                    }
                    y.setText("");
                    z.setText("");
                }
            }
        });




    }



}

