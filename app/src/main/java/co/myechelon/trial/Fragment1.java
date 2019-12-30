package co.myechelon.trial;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * Created by Teacher on 6/8/2019.
 */

public class Fragment1 extends Fragment {
    TextView a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,pr1,pr2,pr3,pr4,pr5,pr6;
    RelativeLayout r1,r2,r3,r4,r5,r6;
    ProgressBar s,t,u,v,w,x;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main2, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        try {

            DatabaseHandler bHandler = new DatabaseHandler(getActivity());
            IncomeDb idd = new IncomeDb(getActivity());


            //expenses
            b = (TextView) view.findViewById(R.id.exp1);
            RelativeLayout relex = (RelativeLayout) view.findViewById(R.id.relex);
            b.setText(bHandler.getTotal() + "rwf");



            //income
            c = (TextView) view.findViewById(R.id.income1);
            RelativeLayout relin = (RelativeLayout) view.findViewById(R.id.relin);
            c.setText(idd.getTotal2() + "rwf");



            //barchart

            BarChart chart = (BarChart) view.findViewById(R.id.barchart);

            Date date=new Date();

            Calendar calendar= Calendar.getInstance();
            calendar.setTime(date);
            int dd= calendar.get(Calendar.YEAR);
            ArrayList NoOfEmp = new ArrayList();





            NoOfEmp.add(new BarEntry(Math.round((((double)bHandler.getChart("jan", "janvier", dd) / (double)idd.getChart("jan", "janvier", dd)))*100), 0));
            NoOfEmp.add(new BarEntry(Math.round((((double)bHandler.getChart("feb","février",dd) / (double)idd.getChart("feb","février",dd)))*100), 1));
            NoOfEmp.add(new BarEntry(Math.round((((double)bHandler.getChart("march","mars",dd) / (double)idd.getChart("march","mars",dd)))*100), 2));
            NoOfEmp.add(new BarEntry(Math.round((((double)bHandler.getChart("apr","avril",dd) / (double)idd.getChart("apr","avril",dd)))*100), 3));
            NoOfEmp.add(new BarEntry(Math.round((((double)bHandler.getChart("may","mai",dd) / (double)idd.getChart("may","mai",dd)))*100), 4));
            NoOfEmp.add(new BarEntry(Math.round((((double)bHandler.getChart("jun","juin",dd) / (double)idd.getChart("jun","juin",dd)))*100), 5));
            NoOfEmp.add(new BarEntry(Math.round((((double)bHandler.getChart("jul","juillet",dd) / (double)idd.getChart("jul","juillet",dd)))*100), 6));
            NoOfEmp.add(new BarEntry(Math.round((((double)bHandler.getChart("aug","août",dd) / (double)idd.getChart("aug","août",dd)))*100), 7));
            NoOfEmp.add(new BarEntry(Math.round((((double)bHandler.getChart("sep","septembre",dd) / (double)idd.getChart("sep","septembre",dd)))*100), 8));
            NoOfEmp.add(new BarEntry(Math.round((((double)bHandler.getChart("oct","octobre",dd) / (double)idd.getChart("oct","octobre",dd)))*100), 9));
            NoOfEmp.add(new BarEntry(Math.round((((double)bHandler.getChart("nov","novembre",dd) / (double)idd.getChart("nov","novembre",dd)))*100), 10));
            NoOfEmp.add(new BarEntry(Math.round((((double)bHandler.getChart("dec","décembre",dd) / (double)idd.getChart("dec","décembre",dd)))*100), 11));


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
            BarDataSet bardataset = new BarDataSet(NoOfEmp, "annual overview percentage");
            chart.animateY(5000);
            BarData data = new BarData(year, bardataset);
            bardataset.setColors(ColorTemplate.VORDIPLOM_COLORS);
            chart.setData(data);













            //average
            d = (TextView) view.findViewById(R.id.avg);
            d.setText(bHandler.getTotal() / bHandler.getCounts() + "rwf");


            //today
            e = (TextView) view.findViewById(R.id.today);
            Calendar calendar2 = Calendar.getInstance();
            final String date2 = DateFormat.getDateInstance(DateFormat.FULL).format(calendar2.getTime());
            e.setText(bHandler.getToday(date2) + "rwf");


            //week
            f = (TextView) view.findViewById(R.id.week);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.add(Calendar.DATE, -30);
            final String date1 = DateFormat.getDateInstance(DateFormat.FULL).format(calendar1.getTime());

            f.setText(bHandler.getWeekAvg(date2, date1) + "rwf");




            g =(TextView) view.findViewById(R.id.title1);
            h =(TextView) view.findViewById(R.id.time1);
            pr1 =(TextView) view.findViewById(R.id.timee1);

            h.setText(bHandler.getCatTotal("Transport")+"rwf");

            s=(ProgressBar) view.findViewById(R.id.progressBar);

            int perc=(idd.getTotal2())/(100);
            pr1.setText(bHandler.getCatTotal("Transport")/perc+"%");
            s.setProgress(bHandler.getCatTotal("Transport")/perc);
            r1=(RelativeLayout) view.findViewById(R.id.r1);
            r1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("trans",g.getText().toString());
                    Intent intent=new Intent(getActivity(),MainActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });








            //shopping########################




            i =(TextView) view.findViewById(R.id.title12);
            i.setText("Shopping");

            j =(TextView) view.findViewById(R.id.time2);
            pr2 =(TextView) view.findViewById(R.id.timee12);
            j.setText(bHandler.getCatTotal("Shopping")+"rwf");
            t=(ProgressBar) view.findViewById(R.id.progressBar2);
            t.setProgress(bHandler.getCatTotal("Shopping")/perc);
            pr2.setText(bHandler.getCatTotal("Shopping")/perc+"%");
            r2=(RelativeLayout) view.findViewById(R.id.r12);
            r2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("trans",i.getText().toString());
                    Intent intent=new Intent(getActivity(),MainActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });




            //health
            k =(TextView) view.findViewById(R.id.title123);
            k.setText("Health");
            pr3 =(TextView) view.findViewById(R.id.timee123);
            l =(TextView) view.findViewById(R.id.time23);
            l.setText(bHandler.getCatTotal("Health")+"rwf");
            u=(ProgressBar) view.findViewById(R.id.progressBar23);
            u.setProgress(bHandler.getCatTotal("Health")/perc);
            pr3.setText(bHandler.getCatTotal("Health")/perc+"%");

            r3=(RelativeLayout) view.findViewById(R.id.r123);
            r3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("trans",k.getText().toString());
                    Intent intent=new Intent(getActivity(),MainActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });



            //food
            m =(TextView) view.findViewById(R.id.title1234);
            m.setText("Food");
            pr4 =(TextView) view.findViewById(R.id.timee1234);
            n =(TextView) view.findViewById(R.id.time234);
            n.setText(bHandler.getCatTotal("Food")+"rwf");
            v =(ProgressBar) view.findViewById(R.id.progressBar234);
            v.setProgress(bHandler.getCatTotal("Food")/perc);
            pr4.setText(bHandler.getCatTotal("Food")/perc+"%");
            r4=(RelativeLayout) view.findViewById(R.id.r1234);
            r4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("trans",m.getText().toString());
                    Intent intent=new Intent(getActivity(),MainActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });





            //airtime
            o =(TextView) view.findViewById(R.id.title12345);
            o.setText("Airtime");
            pr5 =(TextView) view.findViewById(R.id.timee12345);
            p =(TextView) view.findViewById(R.id.time2345);
            p.setText(bHandler.getCatTotal("Airtime")+"rwf");
            w=(ProgressBar) view.findViewById(R.id.progressBar2345);
            w.setProgress(bHandler.getCatTotal("Airtime")/perc);
            pr5.setText(bHandler.getCatTotal("Airtime")/perc+"%");

            r5=(RelativeLayout) view.findViewById(R.id.r12345);
            r5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("trans",o.getText().toString());
                    Intent intent=new Intent(getActivity(),MainActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });



            //loans
            q =(TextView) view.findViewById(R.id.title123456);
            q.setText("Loans");
            pr6 =(TextView) view.findViewById(R.id.timee123456);
            r=(TextView) view.findViewById(R.id.time23456);
            r.setText(bHandler.getCatTotal("Loans")+"rwf");
            x=(ProgressBar) view.findViewById(R.id.progressBar23456);
            x.setProgress(bHandler.getCatTotal("Loans")/perc);
            pr6.setText(bHandler.getCatTotal("Loans")/perc+"%");

            r6=(RelativeLayout) view.findViewById(R.id.r123456);
            r6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("trans",q.getText().toString());
                    Intent intent=new Intent(getActivity(),MainActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

            //gifts

            final TextView q2 =(TextView) view.findViewById(R.id.title1234567);
            q2.setText("Gifts");
            TextView  pr66 =(TextView) view.findViewById(R.id.timee1234567);
            TextView  r2=(TextView) view.findViewById(R.id.time234567);
            r2.setText(bHandler.getCatTotal("Gifts")+"rwf");
            ProgressBar x2=(ProgressBar) view.findViewById(R.id.progressBar234567);
            x2.setProgress(bHandler.getCatTotal("Gifts")/perc);
            pr66.setText(bHandler.getCatTotal("Gifts")/perc+"%");

            RelativeLayout r66=(RelativeLayout) view.findViewById(R.id.r1234567);
            r66.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("trans",q2.getText().toString());
                    Intent intent=new Intent(getActivity(),MainActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });


            //leasure


            final TextView q22 =(TextView) view.findViewById(R.id.title12345678);
            q22.setText("Leasure");
            TextView  pr666 =(TextView) view.findViewById(R.id.timee12345678);
            TextView  r22=(TextView) view.findViewById(R.id.time2345678);
            r22.setText(bHandler.getCatTotal("Leasure")+"rwf");
            ProgressBar x22=(ProgressBar) view.findViewById(R.id.progressBar2345678);
            x22.setProgress(bHandler.getCatTotal("Leasure")/perc);
            pr666.setText(bHandler.getCatTotal("Leasure")/perc+"%");

            RelativeLayout r666=(RelativeLayout) view.findViewById(R.id.r12345678);
            r666.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("trans",q22.getText().toString());
                    Intent intent=new Intent(getActivity(),MainActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });


            //housing


            final TextView q222 =(TextView) view.findViewById(R.id.title);
            q222.setText("Housing");
            TextView  pr6666 =(TextView) view.findViewById(R.id.timee);
            TextView  r222=(TextView) view.findViewById(R.id.time);
            r222.setText(bHandler.getCatTotal("Housing")+"rwf");
            ProgressBar x222=(ProgressBar) view.findViewById(R.id.progressBa);
            x222.setProgress(bHandler.getCatTotal("Housing")/perc);
            pr6666.setText(bHandler.getCatTotal("Housing")/perc+"%");

            RelativeLayout r6666=(RelativeLayout) view.findViewById(R.id.r);
            r6666.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("trans",q222.getText().toString());
                    Intent intent=new Intent(getActivity(),MainActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });


            //Investments


            final TextView q2222 =(TextView) view.findViewById(R.id.title123456789);
            q2222.setText("Investments");
            TextView  pr66666 =(TextView) view.findViewById(R.id.timee123456789);
            TextView  r2222=(TextView) view.findViewById(R.id.time23456789);
            r2222.setText(bHandler.getCatTotal("Investments")+"rwf");
            ProgressBar x2222=(ProgressBar) view.findViewById(R.id.progressBar23456789);
            x2222.setProgress(bHandler.getCatTotal("Investments")/perc);
            pr66666.setText(bHandler.getCatTotal("Investments")/perc+"%");

            RelativeLayout r66666=(RelativeLayout) view.findViewById(R.id.r123456789);
            r66666.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("trans",q2222.getText().toString());
                    Intent intent=new Intent(getActivity(),MainActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });


            //Others


            final TextView q22222 =(TextView) view.findViewById(R.id.title12345678910);
            q22222.setText("Others");
            TextView  pr666666 =(TextView) view.findViewById(R.id.timee12345678910);
            TextView  r22222=(TextView) view.findViewById(R.id.time2345678910);
            r22222.setText(bHandler.getCatTotal("Others")+"rwf");
            ProgressBar x22222=(ProgressBar) view.findViewById(R.id.progressBar2345678910);
            x22222.setProgress(bHandler.getCatTotal("Others")/perc);
            pr666666.setText(bHandler.getCatTotal("Others")/perc+"%");

            RelativeLayout r666666=(RelativeLayout) view.findViewById(R.id.r12345678910);
            r666666.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("trans",q22222.getText().toString());
                    Intent intent=new Intent(getActivity(),MainActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });


        }catch (Exception e){
            //Toast.makeText(getActivity(),"cant divide by zero",Toast.LENGTH_LONG).show();

        }


    }
}
