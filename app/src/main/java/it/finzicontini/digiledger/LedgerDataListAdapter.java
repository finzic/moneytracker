package it.finzicontini.digiledger;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
        holder.textView.setText(myListData.getCategory());
        holder.imageView.setImageResource(listdata[position].getImgId());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+myListData.getDescription(),Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dateView;
        public TextView amountView;
        public TextView descrView;
        public LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.dateView = (TextView) itemView.findViewById(R.id.editTextDate);
            this.amountView = (TextView) itemView.findViewById(R.id.editTextNumber);
            this.descrView = (TextView) itemView.findViewById(R.id.editTextTextPersonName);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linLayout);
        }
    }
}
