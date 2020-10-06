package it.finzicontini.digiledger;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.List;

public class LedgerDataListAdapter extends RecyclerView.Adapter<LedgerDataListAdapter.ViewHolder> {
    List<LedgerData> listOfLedgerData;

    public LedgerDataListAdapter(List<LedgerData>listOfLedgerData){
        this.listOfLedgerData = listOfLedgerData;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from ( parent.getContext());
        View listItem = li.inflate(R.layout.ledger_data, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final LedgerData myListData = listOfLedgerData.get(position);
        final DateFormat sf = new SimpleDateFormat("dd/MM/YYYY HH:MM");
        holder.dateView.setText(sf.format(myListData.getDate()));
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(2);
        format.setCurrency(Currency.getInstance("EUR"));
        holder.amountView.setText(format.format(myListData.getAmount()));
        holder.categoryView.setText(myListData.getCategory());
    }


    @Override
    public int getItemCount() {
        return this.listOfLedgerData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dateView;
        public TextView amountView;
        public TextView categoryView;
        public LinearLayout linearLayoutVert;
        public LinearLayout linearLayoutHor;
        public ViewHolder(View itemView) {
            super(itemView);
            this.dateView = (TextView) itemView.findViewById(R.id.txtDate);
            this.amountView = (TextView) itemView.findViewById(R.id.txtAmount);
            this.categoryView = (TextView) itemView.findViewById(R.id.txtCategory);
            linearLayoutVert = (LinearLayout)itemView.findViewById(R.id.linLayoutVert);
            linearLayoutHor = (LinearLayout)itemView.findViewById(R.id.linLayoutHor);
        }
    }
}
