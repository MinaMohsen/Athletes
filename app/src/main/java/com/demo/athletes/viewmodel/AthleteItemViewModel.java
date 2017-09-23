package com.demo.athletes.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.demo.athletes.R;
import com.demo.athletes.model.Athlete;
import com.demo.athletes.utils.CircleTransform;
import com.demo.athletes.view.AthleteDetailActivity;
import com.squareup.picasso.Picasso;


public class AthleteItemViewModel extends BaseObservable {

    private Athlete athlete;
    private Context context;

    public AthleteItemViewModel(Athlete athlete, Context context) {
        this.athlete = athlete;
        this.context = context;
    }

    public String getName() {
        return athlete.getName();
    }

    public String getCirclePictureProfile() {
        return athlete.getAthleteImage();
    }

    @BindingAdapter("imageUrl")
    public static void setCircleImageUrl(final ImageView imageView, String url) {
        if (url != null && !url.isEmpty()) {
            Picasso.with(imageView.getContext()).load(url).error(R.drawable.profile_user).transform(new CircleTransform()).fit().into(imageView);
        }
    }

    public void onItemClick(View view) {
        context.startActivity(AthleteDetailActivity.launchDetail(view.getContext(), athlete));
    }

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
        notifyChange();
    }
}
