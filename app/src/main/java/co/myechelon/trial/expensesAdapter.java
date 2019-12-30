package co.myechelon.trial;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Teacher on 5/10/2019.
 */

public class expensesAdapter extends RecyclerView.Adapter<expensesAdapter.MyViewHolder> {
    private List<Expenses> expensesList;

   private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView exp;
        public TextView amo;
        public TextView dat;
        public TextView inc;
        public TextView amo2;
        public TextView dat2;
        public RelativeLayout crd;

        //initilising Custom row in constructor
        public MyViewHolder(View view) {
            super(view);
            exp=(TextView)view.findViewById(R.id.titlee);
            amo=(TextView)view.findViewById(R.id.amm);
            dat=(TextView)view.findViewById(R.id.tim);
             crd=(RelativeLayout) view.findViewById(R.id.rel);



        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)

    public expensesAdapter(List<Expenses> expensesList,Context context){
        this.expensesList=expensesList;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.expense2 ,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Expenses expenses=expensesList.get(position);


        holder.exp.setText(expenses.getExpense());
        holder.amo.setText(expenses.getAmount()+"rwf");

        holder.dat.setText(expenses.getDate());
         holder.crd.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Bundle bundle = new Bundle();
                 bundle.putInt("id1",expenses.getId());
                 bundle.putString("expense",expenses.getExpense());
                 bundle.putString("amount",expenses.getAmount());
                 bundle.putString("date",expenses.getDate());
                 bundle.putString("cat",expenses.getCat());
                 Intent intent=new Intent(context,details.class);
                 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 intent.putExtras(bundle);
                 context.startActivity(intent);

             }
         });



    }

    @Override
    public int getItemCount() {
        return expensesList.size();
    }
}
