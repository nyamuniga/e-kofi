package co.myechelon.trial;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import java.util.Calendar;

import android.os.SystemClock;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Teacher on 5/18/2019.
 */

public class CategoryActivity extends AppCompatActivity {
        TextView a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,pr1,pr2,pr3,pr4,pr5,pr6;
    RelativeLayout r1,r2,r3,r4,r5,r6;
    ProgressBar s,t,u,v,w,x;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("Overview");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        DatabaseHandler bHandler = new DatabaseHandler(this);
      IncomeDb idd = new IncomeDb(this);
try {

    a = (TextView) findViewById(R.id.balance1);
    int tt = (idd.getTotal2()) - (bHandler.getTotal());
    a.setText(tt + "rwf");


    //expenses
    b = (TextView) findViewById(R.id.exp1);
    RelativeLayout relex = (RelativeLayout) findViewById(R.id.relex);
    b.setText(bHandler.getTotal() + "rwf");
    relex.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(getApplicationContext(), AllEActivity.class);

            startActivity(intent);
        }
    });


    //income
    c = (TextView) findViewById(R.id.income1);
    RelativeLayout relin = (RelativeLayout) findViewById(R.id.relin);
    c.setText(idd.getTotal2() + "rwf");
    relin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(getApplicationContext(), AllActivity.class);

            startActivity(intent);
        }
    });


    //barchart

    BarChart chart = (BarChart) findViewById(R.id.barchart);

    Date date=new Date();

    Calendar calendar= Calendar.getInstance();
    calendar.setTime(date);
    int dd= calendar.get(Calendar.YEAR);
    ArrayList NoOfEmp = new ArrayList();

    NoOfEmp.add(new BarEntry( bHandler.getChart("jan", "janvier", dd), 0));
    NoOfEmp.add(new BarEntry(bHandler.getChart("feb","février",dd), 1));
    NoOfEmp.add(new BarEntry(bHandler.getChart("march","mars",dd), 2));
    NoOfEmp.add(new BarEntry(bHandler.getChart("apr","avril",dd), 3));
    NoOfEmp.add(new BarEntry(bHandler.getChart("may","mai",dd), 4));
    NoOfEmp.add(new BarEntry(bHandler.getChart("jun","juin",dd), 5));
    NoOfEmp.add(new BarEntry(bHandler.getChart("jul","juillet",dd), 6));
    NoOfEmp.add(new BarEntry(bHandler.getChart("aug","août",dd), 7));
    NoOfEmp.add(new BarEntry(bHandler.getChart("sep","septembre",dd), 8));
    NoOfEmp.add(new BarEntry(bHandler.getChart("oct","octobre",dd), 9));
    NoOfEmp.add(new BarEntry(bHandler.getChart("nov","novembre",dd), 10));
    NoOfEmp.add(new BarEntry(bHandler.getChart("dec","décembre",dd), 11));


    ArrayList year = new ArrayList();

    year.add("jan");
    year.add("feb");
    year.add("mar");
    year.add("apr");
    year.add("may");
    year.add("jun");
    year.add("jul");
    year.add("aug");
    year.add("sep");
    year.add("oct");
    year.add("nov");
    year.add("dec");

    //int[] color=new int[]{Color.YELLOW,Color.GREEN,Color.c};
    BarDataSet bardataset = new BarDataSet(NoOfEmp, "annual overview");
    chart.animateY(5000);
    BarData data = new BarData(year, bardataset);
    bardataset.setColors(ColorTemplate.VORDIPLOM_COLORS);
    chart.setData(data);













    //average
    d = (TextView) findViewById(R.id.avg);
    d.setText(bHandler.getTotal() / bHandler.getCounts() + "rwf");


    //today
    e = (TextView) findViewById(R.id.today);
    Calendar calendar2 = Calendar.getInstance();
    final String date2 = DateFormat.getDateInstance(DateFormat.FULL).format(calendar2.getTime());
    e.setText(bHandler.getToday(date2) + "rwf");


    //week
    f = (TextView) findViewById(R.id.week);
    Calendar calendar1 = Calendar.getInstance();
    calendar1.add(Calendar.DATE, -30);
    final String date1 = DateFormat.getDateInstance(DateFormat.FULL).format(calendar1.getTime());

    f.setText(bHandler.getWeekAvg(date2, date1) + "rwf");




        g =(TextView) findViewById(R.id.title1);
        h =(TextView) findViewById(R.id.time1);
        pr1 =(TextView) findViewById(R.id.timee1);

        h.setText(bHandler.getCatTotal("Transport")+"rwf");

        s=(ProgressBar) findViewById(R.id.progressBar);

       int perc=(idd.getTotal2())/(100);
        pr1.setText(bHandler.getCatTotal("Transport")/perc+"%");
     s.setProgress(bHandler.getCatTotal("Transport")/perc);
         r1=(RelativeLayout) findViewById(R.id.r1);
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("trans",g.getText().toString());
                Intent intent=new Intent(getBaseContext(),MainActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        //shopping########################
       i =(TextView) findViewById(R.id.title12);
        i.setText("Shopping");

        j =(TextView) findViewById(R.id.time2);
        pr2 =(TextView) findViewById(R.id.timee12);
        j.setText(bHandler.getCatTotal("Shopping")+"rwf");
        t=(ProgressBar) findViewById(R.id.progressBar2);
         t.setProgress(bHandler.getCatTotal("Shopping")/perc);
        pr2.setText(bHandler.getCatTotal("Shopping")/perc+"%");
        r2=(RelativeLayout) findViewById(R.id.r12);
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("trans",i.getText().toString());
                Intent intent=new Intent(getBaseContext(),MainActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });




        //health
       k =(TextView) findViewById(R.id.title123);
        k.setText("Health");
        pr3 =(TextView) findViewById(R.id.timee123);
       l =(TextView) findViewById(R.id.time23);
    l.setText(bHandler.getCatTotal("Health")+"rwf");
        u=(ProgressBar) findViewById(R.id.progressBar23);
        u.setProgress(bHandler.getCatTotal("Health")/perc);
        pr3.setText(bHandler.getCatTotal("Health")/perc+"%");

        r3=(RelativeLayout) findViewById(R.id.r123);
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("trans",k.getText().toString());
                Intent intent=new Intent(getBaseContext(),MainActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



        //food
        m =(TextView) findViewById(R.id.title1234);
        m.setText("Food");
        pr4 =(TextView) findViewById(R.id.timee1234);
       n =(TextView) findViewById(R.id.time234);
       n.setText(bHandler.getCatTotal("Food")+"rwf");
        v =(ProgressBar) findViewById(R.id.progressBar234);
        v.setProgress(bHandler.getCatTotal("Food")/perc);
        pr4.setText(bHandler.getCatTotal("Food")/perc+"%");
        r4=(RelativeLayout) findViewById(R.id.r1234);
        r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("trans",m.getText().toString());
                Intent intent=new Intent(getBaseContext(),MainActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });





        //airtime
       o =(TextView) findViewById(R.id.title12345);
        o.setText("Airtime");
        pr5 =(TextView) findViewById(R.id.timee12345);
        p =(TextView) findViewById(R.id.time2345);
       p.setText(bHandler.getCatTotal("Airtime")+"rwf");
        w=(ProgressBar) findViewById(R.id.progressBar2345);
        w.setProgress(bHandler.getCatTotal("Airtime")/perc);
        pr5.setText(bHandler.getCatTotal("Airtime")/perc+"%");

        r5=(RelativeLayout) findViewById(R.id.r12345);
        r5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("trans",o.getText().toString());
                Intent intent=new Intent(getBaseContext(),MainActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



        //loans
       q =(TextView) findViewById(R.id.title123456);
        q.setText("Loans");
        pr6 =(TextView) findViewById(R.id.timee123456);
        r=(TextView) findViewById(R.id.time23456);
       r.setText(bHandler.getCatTotal("Loans")+"rwf");
        x=(ProgressBar) findViewById(R.id.progressBar23456);
       x.setProgress(bHandler.getCatTotal("Loans")/perc);
        pr6.setText(bHandler.getCatTotal("Loans")/perc+"%");

        r6=(RelativeLayout) findViewById(R.id.r123456);
        r6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("trans",q.getText().toString());
                Intent intent=new Intent(getBaseContext(),MainActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    //gifts

    final TextView q2 =(TextView) findViewById(R.id.title1234567);
    q2.setText("Gifts");
    TextView  pr66 =(TextView) findViewById(R.id.timee1234567);
    TextView  r2=(TextView) findViewById(R.id.time234567);
    r2.setText(bHandler.getCatTotal("Gifts")+"rwf");
    ProgressBar x2=(ProgressBar) findViewById(R.id.progressBar234567);
    x2.setProgress(bHandler.getCatTotal("Gifts")/perc);
    pr66.setText(bHandler.getCatTotal("Gifts")/perc+"%");

    RelativeLayout r66=(RelativeLayout) findViewById(R.id.r1234567);
    r66.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putString("trans",q2.getText().toString());
            Intent intent=new Intent(getBaseContext(),MainActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    });


    //leasure


    final TextView q22 =(TextView) findViewById(R.id.title12345678);
    q22.setText("Leasure");
    TextView  pr666 =(TextView) findViewById(R.id.timee12345678);
    TextView  r22=(TextView) findViewById(R.id.time2345678);
    r22.setText(bHandler.getCatTotal("Leasure")+"rwf");
    ProgressBar x22=(ProgressBar) findViewById(R.id.progressBar2345678);
    x22.setProgress(bHandler.getCatTotal("Leasure")/perc);
    pr666.setText(bHandler.getCatTotal("Leasure")/perc+"%");

    RelativeLayout r666=(RelativeLayout) findViewById(R.id.r12345678);
    r666.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putString("trans",q22.getText().toString());
            Intent intent=new Intent(getBaseContext(),MainActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    });





}catch (Exception e){
    //Toast.makeText(this,"cant divide by zero",Toast.LENGTH_LONG).show();

}


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
        Intent resultIntent = new Intent(this, post.class);
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






}
