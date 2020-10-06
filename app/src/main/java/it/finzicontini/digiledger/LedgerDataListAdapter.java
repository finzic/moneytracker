package it.finzicontini.digiledger;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.dateView.setText(myListData.getDate().toString());
        holder.amountView.setText(myListData.getAmount().toString());
        holder.categoryView.setText(myListData.getCategory());
    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dateView;
        public TextView amountView;
        public TextView categoryView;
        public LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.dateView = (TextView) itemView.findViewById(R.id.txtDate);
            this.amountView = (TextView) itemView.findViewById(R.id.txtAmount);
            this.categoryView = (TextView) itemView.findViewById(R.id.txtCategory);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linLayout);
        }
    }
}
