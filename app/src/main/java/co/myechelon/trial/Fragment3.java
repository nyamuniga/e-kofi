package co.myechelon.trial;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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

/**
 * Created by Teacher on 6/9/2019.
 */

public class Fragment3 extends Fragment {
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
        return inflater.inflate(R.layout.activity_all_e, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final DatabaseHandler databaseHandler = new DatabaseHandler(getActivity());

        Spinner spinner=(Spinner)view.findViewById(R.id.spinner4);
       
        dateList=databaseHandler.getAllDate();
        Set<String> set=new HashSet<String>(dateList);

        dateList.clear();
        dateList.addAll(set);

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getActivity(),R.layout.spinner_item,dateList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = parent.getItemAtPosition(position).toString();
                expensesList=databaseHandler.getAllExpenses(s);
                expensesAdapter = new expensesAdapter(expensesList,getActivity());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(expensesAdapter);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Toast.makeText(getBaseContext(),txt,Toast.LENGTH_LONG).show();







        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview2);

        income=(TextView) view.findViewById(R.id.incomee1);


        in=(RelativeLayout) view.findViewById(R.id.incomee);





        income.setText(databaseHandler.getTotal() + "Rwf");






    }
    }
