package com.pathteam.hikeitv2.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pathteam.hikeitv2.Model.HikeList;
import com.pathteam.hikeitv2.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by JoshuaMabry on 11/17/16.
 */



public class HikeListAdapter extends RecyclerView.Adapter<HikeListAdapter.HikeHolder> {
    public List<HikeList> hikelist;
    private Context context;

    public HikeListAdapter(List<HikeList> hikelist, Context context) {
        this.hikelist = hikelist;
        this.context = context;
    }


    @Override
    public HikeListAdapter.HikeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflateView = LayoutInflater.from(context).inflate(R.layout.hike_list_item_view, parent, false);
        return new HikeListAdapter.HikeHolder(inflateView);
    }


    @Override
    public void onBindViewHolder(HikeListAdapter.HikeHolder holder, int position) {
        HikeList hikes = hikelist.get(position);
        holder.bindUser(hikes);
    }

    @Override
    public int getItemCount() {
        return hikelist.size();

//        return hikelist == null ? 0 : hikelist.size();
    }


    class HikeHolder extends RecyclerView.ViewHolder {


        @Bind(R.id.hike_notes)
        TextView hikeNotes;

        @Bind(R.id.title)
        TextView title;


        public HikeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        //        Lets put our data in our UI
        public void bindUser(final HikeList hikes) {
            hikeNotes.setText(hikes.getHikenotes());
            title.setText(hikes.getTitle());
        }
    }
}