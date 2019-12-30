package co.myechelon.trial;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Teacher on 5/13/2019.
 */

public class incomeAdapter extends RecyclerView.Adapter<incomeAdapter.MyViewHolder> {
    private List<Incomes> incomesList;

    private Context context;






    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView exp;
        public TextView amo;
        public TextView dat;

        public RelativeLayout crd;

        //initilising Custom row in constructor
        public MyViewHolder(View view) {
            super(view);
            exp=(TextView)view.findViewById(R.id.titleee);
            amo=(TextView)view.findViewById(R.id.ammm);
            dat=(TextView)view.findViewById(R.id.timm);
            crd=(RelativeLayout) view.findViewById(R.id.rell);



        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)

    public incomeAdapter(List<Incomes> incomesList,Context context){
        this.incomesList=incomesList;
        this.context=context;
    }

    @Override
    public incomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.income ,parent,false);

        return new incomeAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final incomeAdapter.MyViewHolder holder, final int position) {
        final Incomes incomes=incomesList.get(position);
        holder.exp.setText(incomes.getIncome());
        holder.amo.setText(incomes.getAmount()+"Rwf");
        holder.dat.setText(incomes.getDate());
        holder.crd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id2",incomes.getId());
                bundle.putString("income",incomes.getIncome());
                bundle.putString("amount2",incomes.getAmount());
                bundle.putString("date2",incomes.getDate());
                Intent intent = new Intent(context,details.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return incomesList.size();
    }
}