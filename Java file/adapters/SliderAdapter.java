package com.thushan.ecommerce.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.thushan.ecommerce.R;

import org.w3c.dom.Text;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }
    int imgArray[]= {
            R.drawable.welcome,
            R.drawable.choose_product,
            R.drawable.fast_delivery,
            R.drawable.good_service
            };
    int headingArray[]= {
            R.string.first_slide,
            R.string.second_slide,
            R.string.third_slide,
            R.string.forth_slide
            };
    int descriptionArray[]= {
            R.string.description1,
            R.string.description2,
            R.string.description3,
            R.string.description4
            };


    @Override
    public int getCount() {

        return headingArray.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container,int position){

        layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.sliding_layout,container,false);

        ImageView imageView=view.findViewById(R.id.slider_img);
        TextView heading=view.findViewById(R.id.heading);
        TextView description=view.findViewById(R.id.description);

        imageView.setImageResource(imgArray[position]);
        heading.setText(headingArray[position]);
        description.setText(descriptionArray[position]);

        container.addView(view);

        return view;
  }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object){
        container.removeView((LinearLayout)object);
    }
}
