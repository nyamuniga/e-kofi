package co.myechelon.trial;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllActivity extends AppCompatActivity {
    List<Expenses> expensesList=new ArrayList<>();
    List<String> dateList=new ArrayList<>();
    List<Incomes> incomeList=new ArrayList<>();
    private Context context;

    private RecyclerView recyclerView;
    private expensesAdapter expensesAdapter;
    private incomeAdapter incomeAdapter;
    TextView tt,income;
    String txt;
    RelativeLayout in,ex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);


        setSupportActionBar(toolbar);


        final IncomeDb idd = new IncomeDb(this);

        Spinner spinner=(Spinner)findViewById(R.id.spinner4);
        setTitle("All");
        dateList=idd.getALLDates();
        Set<String> set=new HashSet<String>(dateList);

        dateList.clear();
        dateList.addAll(set);

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,R.layout.spinner_item,dateList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
      spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              String s=parent.getItemAtPosition(position).toString();
              incomeList=idd.getAllIncomes(s);
              incomeAdapter = new incomeAdapter(incomeList,getApplicationContext());
              RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
              recyclerView.setLayoutManager(mLayoutManager);
              recyclerView.setAdapter(incomeAdapter);
          }


          @Override
          public void onNothingSelected(AdapterView<?> parent) {

          }
      });


        //Toast.makeText(getBaseContext(),txt,Toast.LENGTH_LONG).show();







        recyclerView = (RecyclerView) findViewById(R.id.recyclerview2);

        income=(TextView) findViewById(R.id.incomee1);


        in=(RelativeLayout) findViewById(R.id.incomee);





        income.setText(idd.getTotal2() + "Rwf");






    }




    @Override
    public boolean onOptionsItemSelected(MenuItem Item) {
        switch (Item.getItemId()) {

            case R.id.add:
                Intent intent = new Intent(this,post.class);
                startActivity(intent);
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




}

