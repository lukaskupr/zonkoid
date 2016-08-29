package eu.urbancoders.zonkysniper;

/**
 * Author: Ondrej Steger (ondrej@steger.cz)
 * Date: 16.08.2016
 */

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import eu.urbancoders.zonkysniper.dataobjects.Investment;
import eu.urbancoders.zonkysniper.dataobjects.Rating;
import eu.urbancoders.zonkysniper.integration.ZonkyClient;

import java.text.DecimalFormat;
import java.util.List;

public class InvestorsAdapter extends RecyclerView.Adapter<InvestorsAdapter.InvestorsViewHolder> {

    private List<Investment> investorsList;
    private Context context;

    public class InvestorsViewHolder extends RecyclerView.ViewHolder {
        public TextView investorNickname, timeCreated, amount, firstPlusAdditional;

        public InvestorsViewHolder(View view) {
            super(view);
            investorNickname = (TextView) view.findViewById(R.id.investorNickname);
            timeCreated = (TextView) view.findViewById(R.id.timeCreated);
            amount = (TextView) view.findViewById(R.id.amount);
            firstPlusAdditional = (TextView) view.findViewById(R.id.firstPlusAdditional);
        }
    }


    public InvestorsAdapter(Context context, List<Investment> investorsList) {
        this.investorsList = investorsList;
        this.context = context;
    }

    @Override
    public InvestorsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.investors_list_row, parent, false);

        return new InvestorsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InvestorsViewHolder holder, int position) {
        Investment investment = investorsList.get(position);
        holder.investorNickname.setText(investment.getInvestorNickname());
        holder.timeCreated.setText(Constants.DATE_DD_MM_YYYY_HH_MM.format(investment.getTimeCreated()));
        holder.amount.setText(Constants.FORMAT_NUMBER_NO_DECIMALS.format(investment.getAmount()) + " Kč");
        if(investment.getAdditionalAmount() > 0) {
            holder.firstPlusAdditional.setText(Constants.FORMAT_NUMBER_NO_DECIMALS.format(investment.getFirstAmount()) + " + " +
                    Constants.FORMAT_NUMBER_NO_DECIMALS.format(investment.getAdditionalAmount())
            );
        }
    }

    @Override
    public int getItemCount() {
        return investorsList.size();
    }
}