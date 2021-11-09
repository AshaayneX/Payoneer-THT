package com.payoneer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.payoneer.R;
import com.payoneer.model.ApplicableNetwork;

import java.util.ArrayList;
import java.util.List;
/**
 * Adapter class to bind response data into the RecyclerView layout
 */
public class PaymentListResultsAdapter extends RecyclerView.Adapter<PaymentListResultsAdapter.PaymentListResultsHolder> {

    /** The response received */
    private List<ApplicableNetwork> queryResults = new ArrayList<>();

    public void setQueryResults(List<ApplicableNetwork> queryResults) {
        this.queryResults = queryResults;
        notifyDataSetChanged();
    }

    /** inflate layout to ViewHolder */
    @NonNull
    @Override
    public PaymentListResultsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.payment_list_item, parent, false);

        return new PaymentListResultsHolder(itemView);
    }

    /** loading response values  */
    @Override
    public void onBindViewHolder(@NonNull PaymentListResultsHolder holder, int position) {
        ApplicableNetwork applicableNetwork = queryResults.get(position);
        holder.paymentOptionTitle.setText(applicableNetwork.getLabel());
        Glide.with(holder.itemView)
                .load(applicableNetwork.getLinks().get("logo"))
                .fitCenter()
                .into(holder.paymentOptionLogo);
    }

    @Override
    public int getItemCount() {
        return queryResults.size();
    }

    /**
     * inner class ViewHolder, binds layout elements
     */
    class PaymentListResultsHolder extends RecyclerView.ViewHolder {

        private TextView paymentOptionTitle;
        private ImageView paymentOptionLogo;

        public PaymentListResultsHolder(@NonNull View itemView) {
            super(itemView);
            paymentOptionTitle = itemView.findViewById(R.id.item_title);
            paymentOptionLogo = itemView.findViewById(R.id.item_logo);
        }
    }
}
