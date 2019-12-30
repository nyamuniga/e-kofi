package co.myechelon.trial;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;


public class post extends AppCompatActivity {
    EditText x,m,y,z;
    Button sub,viewall,btn;
    String txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        setTitle("Today");
        final DatabaseHandler dbHandler = new DatabaseHandler(this);

        final IncomeDb idd = new IncomeDb(this);
        Calendar calendar= Calendar.getInstance();
        final String date= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        x=(EditText)findViewById(R.id.type);
        m=(EditText)findViewById(R.id.money);
        y=(EditText)findViewById(R.id.source);
        z=(EditText)findViewById(R.id.mony);
       sub =(Button)findViewById(R.id.submit);
        btn =(Button)findViewById(R.id.button);
        Spinner spinner=(Spinner)findViewById(R.id.spinner2);
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

                viewall =(Button)findViewById(R.id.viewall);
       viewall.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(post.this,CategoryActivity.class);
               startActivity(intent);
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

                        Intent intent = new Intent(post.this, CategoryActivity.class);
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
                        Intent intent = new Intent(post.this, CategoryActivity.class);
                        startActivity(intent);

                    }
                    y.setText("");
                    z.setText("");
                }
            }
        });
    }
}
