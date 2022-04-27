package com.yeferic.nubankshorter.presentation.linkshortener.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yeferic.nubankshorter.databinding.LinkItemListBinding;
import com.yeferic.nubankshorter.domain.model.LinkShorter;

import java.util.ArrayList;
import java.util.List;

public class LinkItemListAdapter extends RecyclerView.Adapter<LinkItemListAdapter.LinkItemViewHolder>  {

    private List<LinkShorter> items;

    public LinkItemListAdapter(List<LinkShorter> lsItems){
        this.items = lsItems == null ? new ArrayList<>() : lsItems;
    }

    @NonNull
    @Override
    public LinkItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        LinkItemListBinding itemBinding = LinkItemListBinding.inflate(layoutInflater, parent, false);
        return new LinkItemViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull LinkItemViewHolder holder, int position) {
        LinkShorter product = items.get(position);
        holder.setItemToBinding(product);
    }

    public void addItem(LinkShorter item, int position){
        items.add(position, item);
        this.notifyItemInserted(position);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class LinkItemViewHolder extends RecyclerView.ViewHolder {

        private LinkItemListBinding binding;

        public LinkItemViewHolder(LinkItemListBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setItemToBinding(LinkShorter item) {
            binding.setListItem(item);
            binding.executePendingBindings();
        }
    }
}