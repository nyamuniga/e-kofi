package co.myechelon.trial;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class Main3Activity extends AppCompatActivity {
    EditText x,m,y,z;
    Button sub,viewall,btn;
    String txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DatabaseHandler bHandler2 = new DatabaseHandler(this);
        IncomeDb idd2 = new IncomeDb(this);
        TextView a = (TextView) findViewById(R.id.balance1);
        int tt = (idd2.getTotal2()) - (bHandler2.getTotal());
        a.setText(tt + "rwf");
        setTitle("");
        setSupportActionBar(toolbar);
        BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Fragment1()).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new Fragment1();
                            break;
                        case R.id.nav_favorites:
                            selectedFragment = new Fragment3();
                            break;
                        case R.id.nav_search:
                            selectedFragment = new Fragment2();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

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

    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Main3Activity.this);

        builder.setTitle("Please confirm");
        builder.setMessage("Are you sure you want to exit the app?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do something when user want to exit the app
                // Let allow the system to handle the event, such as exit the app
                finishAffinity();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do something when want to stay in the app
                Toast.makeText(getApplicationContext(),"thank you",Toast.LENGTH_LONG).show();
            }
        });

        // Create the alert dialog using alert dialog builder
        AlertDialog dialog = builder.create();

        // Finally, display the dialog when user press back button
        dialog.show();
    }




    @Override
    protected void onStop() {
        super.onStop();
        scheduleNotification(getNotification("Remember to write your expenses!"), 1800000);
    }


    private void scheduleNotification(Notification notification, int delay) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(String content) {
        Intent resultIntent = new Intent(this, Main3Activity.class);
// Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
// Get the PendingIntent containing the entire back stack
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(this);

        Uri notifications = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        builder.setContentTitle("e-Kofi");
        builder.setContentIntent(resultPendingIntent);
        builder.setContentText(content);
        builder.setSound(notifications);
        builder.setSmallIcon(R.mipmap.ic_la);
        builder.setDefaults(Notification.DEFAULT_SOUND);
        return builder.build();
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
