package co.myechelon.trial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class details extends AppCompatActivity {
    private EditText ty;
    private EditText mney;
    private EditText dt;
    TextView txt;
    private Button btn;
    private Button btn2;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        setTitle("update");
        ty = (EditText) findViewById(R.id.typ);
        mney = (EditText) findViewById(R.id.mny);
        dt = (EditText) findViewById(R.id.dat);

        btn= (Button) findViewById(R.id.update);
        btn2= (Button) findViewById(R.id.delete);






        final DatabaseHandler dbHandler = new DatabaseHandler(this);
       final IncomeDb idd = new IncomeDb(this);
        bundle = getIntent().getExtras();
        if (bundle.getInt("id1")>0) {


            ty.setText("" + bundle.getString("expense"));
            mney.setText("" + bundle.getString("amount"));
            dt.setText("" + bundle.getString("date"));

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ty.getText().toString().equals("") || mney.getText().toString().equals("") || dt.getText().toString().equals("")){

                        Toast.makeText(getApplicationContext(), "Fill in the blanks", Toast.LENGTH_LONG).show();
                    }else {

                        Expenses expenses = new Expenses(bundle.getInt("id1"), bundle.getString("expense"), bundle.getString("amount"), bundle.getString("date"), bundle.getString("cat"));
                        expenses.setId(bundle.getInt("id1"));
                        expenses.setExpense(ty.getText().toString());
                        expenses.setAmount(mney.getText().toString());
                        expenses.setDate(dt.getText().toString());

                        if (dbHandler.updateExpenses(expenses) > 0) {
                            Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(details.this, Main3Activity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();

                        }
                    }
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(ty.getText().toString().equals("") || mney.getText().toString().equals("") || dt.getText().toString().equals("")){

                        Toast.makeText(getApplicationContext(), "Fill in the blanks", Toast.LENGTH_LONG).show();
                    }else {
                        Expenses expenses = new Expenses(bundle.getInt("id1"), bundle.getString("expense"), bundle.getString("amount"), bundle.getString("date"), bundle.getString("cat"));


                        dbHandler.deleteExpenses(expenses);
                        Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(details.this, Main3Activity.class);
                        startActivity(intent);
                    }

                }
            });
        }else if (bundle.getInt("id2") > 0){



            ty.setText("" + bundle.getString("income"));
            mney.setText("" + bundle.getString("amount2"));
            dt.setText("" + bundle.getString("date2"));

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ty.getText().toString().equals("") || mney.getText().toString().equals("") || dt.getText().toString().equals("")){

                        Toast.makeText(getApplicationContext(), "Fill in the blanks", Toast.LENGTH_LONG).show();
                    }else {
                        Incomes incomes = new Incomes(bundle.getInt("id2"), bundle.getString("income"), bundle.getString("amount2"), bundle.getString("date2"));
                        incomes.setId(bundle.getInt("id2"));
                        incomes.setIncome(ty.getText().toString());
                        incomes.setAmount(mney.getText().toString());
                        incomes.setDate(dt.getText().toString());

                        if (idd.updateIncomes(incomes) > 0) {
                            Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(details.this, Main3Activity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();

                        }
                    }
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(ty.getText().toString().equals("") || mney.getText().toString().equals("") || dt.getText().toString().equals("")){

                        Toast.makeText(getApplicationContext(), "Fill in the blanks", Toast.LENGTH_LONG).show();
                    }else {
                        Incomes incomes = new Incomes(bundle.getInt("id2"), bundle.getString("income"), bundle.getString("amount2"), bundle.getString("date2"));


                        idd.deleteIncomes(incomes);
                        Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(details.this, Main3Activity.class);
                        startActivity(intent);

                    }
                }
            });





        }
    }
}
