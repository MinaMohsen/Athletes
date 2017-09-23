package com.demo.athletes.viewmodel;


import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.demo.athletes.R;
import com.demo.athletes.model.Athlete;
import com.squareup.picasso.Picasso;

public class AthleteDetailViewModel {

    private Athlete athlete;

    public AthleteDetailViewModel(Athlete athlete) {
        this.athlete = athlete;
    }

    public String getName() {
        return athlete.getName();
    }

    public String getBrief() {
        return athlete.getBrief();
    }

    public String getFullPictureProfile() {
        return athlete.getAthleteImage();
    }

    @BindingAdapter({"imageFullUrl"})
    public static void setFullImageUrl(ImageView imageView, String url) {
        if (url != null && !url.isEmpty()) {
            Picasso.with(imageView.getContext()).load(url).fit().error(R.drawable.profile_user).into(imageView);
        }
    }
}
