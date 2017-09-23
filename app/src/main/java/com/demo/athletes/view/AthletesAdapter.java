package com.demo.athletes.view;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.demo.athletes.R;
import com.demo.athletes.databinding.AthleteListItemBinding;
import com.demo.athletes.model.Athlete;
import com.demo.athletes.viewmodel.AthleteItemViewModel;

import java.util.Collections;
import java.util.List;

public class AthletesAdapter extends RecyclerView.Adapter<AthletesAdapter.AthleteAdapterViewHolder> {

    private List<Athlete> athletesList;

    public AthletesAdapter() {
        this.athletesList = Collections.emptyList();
    }

    @Override
    public AthleteAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AthleteListItemBinding athleteListItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.athlete_list_item,
                        parent, false);
        return new AthleteAdapterViewHolder(athleteListItemBinding);
    }

    @Override
    public void onBindViewHolder(AthleteAdapterViewHolder holder, int position) {
        holder.bindAthlete(athletesList.get(position));
    }

    @Override
    public int getItemCount() {
        return athletesList.size();
    }

    public void setAthletesList(List<Athlete> athletesList) {
        this.athletesList = athletesList;
        notifyDataSetChanged();
    }

    public static class AthleteAdapterViewHolder extends RecyclerView.ViewHolder {
        AthleteListItemBinding mAthleteItemBinding;

        public AthleteAdapterViewHolder(AthleteListItemBinding athleteItemBinding) {
            super(athleteItemBinding.itemAthlete);
            this.mAthleteItemBinding = athleteItemBinding;
        }

        void bindAthlete(Athlete athlete) {
            if (mAthleteItemBinding.getAthleteItemViewModel() == null) {
                mAthleteItemBinding.setAthleteItemViewModel(
                        new AthleteItemViewModel(athlete, itemView.getContext()));
            } else {
                mAthleteItemBinding.getAthleteItemViewModel().setAthlete(athlete);
            }
        }
    }
}
