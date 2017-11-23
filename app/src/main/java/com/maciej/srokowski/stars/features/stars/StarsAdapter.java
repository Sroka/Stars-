package com.maciej.srokowski.stars.features.stars;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maciej.srokowski.stars.R;
import com.maciej.srokowski.stars.data.model.Star;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by maciek on 23.11.17.
 */
public class StarsAdapter extends Adapter<StarsAdapter.StarViewHolder> {


    private List<Star> stars = Collections.emptyList();

    @Override
    public StarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_view_star_item,
                parent, false);
        return new StarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StarViewHolder holder, int position) {
        Star star = stars.get(position);
        Glide.with(holder.imageViewStar)
                .load(star.image())
                .into(holder.imageViewStar);
        holder.textViewName.setText(star.name());
        holder.textViewBorn.setText(star.dob());
        holder.textViewChildren.setText(star.children());
        holder.textViewDescription.setText(star.description());
        holder.textViewHeight.setText(star.height());
        holder.textViewSpouse.setText(star.spouse());

    }

    @Override
    public int getItemCount() {
        return stars.size();
    }

    public void setStars(List<Star> stars) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new StarDiffUtilCallback(this.stars, stars));
        this.stars = stars;
        diffResult.dispatchUpdatesTo(this);
    }

    public static final class StarViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageViewStar)
        CircleImageView imageViewStar;
        @BindView(R.id.textViewName)
        TextView textViewName;
        @BindView(R.id.textViewDescription)
        TextView textViewDescription;
        @BindView(R.id.textViewBorn)
        TextView textViewBorn;
        @BindView(R.id.textViewHeight)
        TextView textViewHeight;
        @BindView(R.id.textViewSpouse)
        TextView textViewSpouse;
        @BindView(R.id.textViewChildren)
        TextView textViewChildren;

        public StarViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    private static class StarDiffUtilCallback extends DiffUtil.Callback {
        private final List<Star> oldStars;
        private final List<Star> newStars;

        private StarDiffUtilCallback(List<Star> oldStars, List<Star> newStars) {
            this.oldStars = oldStars;
            this.newStars = newStars;
        }

        @Override
        public int getOldListSize() {
            return oldStars.size();
        }

        @Override
        public int getNewListSize() {
            return newStars.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            //Recognize them by name, it would be beneficial to have some kind of id for them
            return oldStars.get(oldItemPosition).name().equals(newStars.get(newItemPosition).name());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldStars.get(oldItemPosition).equals(newStars.get(newItemPosition));
        }
    }
}
