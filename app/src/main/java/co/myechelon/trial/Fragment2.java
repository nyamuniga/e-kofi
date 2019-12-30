package co.myechelon.trial;

/**
 * Created by Teacher on 6/8/2019.
 */
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Fragment2 extends Fragment {
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_all, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        final IncomeDb idd = new IncomeDb(getActivity());

        Spinner spinner=(Spinner)view.findViewById(R.id.spinner4);
        
        dateList=idd.getALLDates();
        Set<String> set=new HashSet<String>(dateList);

        dateList.clear();
        dateList.addAll(set);

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getActivity(),R.layout.spinner_item,dateList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s=parent.getItemAtPosition(position).toString();
                incomeList=idd.getAllIncomes(s);
                incomeAdapter = new incomeAdapter(incomeList,getActivity());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(incomeAdapter);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Toast.makeText(getBaseContext(),txt,Toast.LENGTH_LONG).show();







        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview2);

        income=(TextView) view.findViewById(R.id.incomee1);


        in=(RelativeLayout) view.findViewById(R.id.incomee);





        income.setText(idd.getTotal2() + "Rwf");






    }



}