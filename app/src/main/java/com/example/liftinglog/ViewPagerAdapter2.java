package com.example.liftinglog;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerAdapter2 extends PagerAdapter {
    Context context;

    int[] images;

    LayoutInflater mLayoutInflater;

    public ViewPagerAdapter2(Context context, int[] images){
        this.context = context;
        this.images = images;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((ConstraintLayout) object);
    }

    public Object instantiateItem(@NonNull ViewGroup container, final int position){
        View itemView = mLayoutInflater.inflate(R.layout.currentworkout, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageViewMain);
        imageView.setImageResource(images[position]);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position == 0){
                    Toast.makeText(context, "Bench Press selected", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), BenchPressGraph.class);
                    view.getContext().startActivity(intent);
                }
//                else if(position == 1){
//                    Toast.makeText(context, "Deadlift selected", Toast.LENGTH_SHORT).show();
//                    Intent intent1 = new Intent(view.getContext(), InputInfoScreen.class);
//                    view.getContext().startActivity(intent1);
//                }
//                else if(position == 2){
//                    Toast.makeText(context, "Chest Press selected", Toast.LENGTH_SHORT).show();
//                    Intent intent2 = new Intent(view.getContext(), InputInfoScreen.class);
//                    view.getContext().startActivity(intent2);
//                }
            }
        });

        ViewPager vp = (ViewPager) container;
        vp.addView(itemView, 0);

        return itemView;
    }

    public void destroyItem(ViewGroup container, int position, Object object){
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
