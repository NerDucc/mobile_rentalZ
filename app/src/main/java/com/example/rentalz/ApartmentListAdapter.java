package com.example.rentalz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentalz.data.ApartmentEntity;
import com.example.rentalz.databinding.ListApartmentBinding;

import java.util.List;

public class ApartmentListAdapter extends RecyclerView.Adapter<ApartmentListAdapter.ApartmentViewHolder> {

    public class ApartmentViewHolder extends RecyclerView.ViewHolder{

        private final ListApartmentBinding itemViewBinding;

        public ApartmentViewHolder(View itemView) {
            super(itemView);
            itemViewBinding = ListApartmentBinding.bind(itemView);
        }

        public void bindData(ApartmentEntity bData){
            itemViewBinding.apartmentProperty.setText(bData.getId());
            itemViewBinding.getRoot().setOnClickListener(
                    v -> {
                        listener.onItemClick(bData.getId());
                    }
            );
        }
    }

    private List<ApartmentEntity> apartmentList;
    private ListApartmentListener listener;


    public ApartmentListAdapter(List<ApartmentEntity> apartmentList, ListApartmentListener listener) {
        this.apartmentList = apartmentList;
        this.listener = listener;

    }

    public interface ListApartmentListener{
        void onItemClick(String apartmentId);
    }

    @NonNull
    @Override
    public ApartmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_apartment, parent, false);
        return new ApartmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApartmentViewHolder holder, int position) {
        ApartmentEntity bData = apartmentList.get(position);
        holder.bindData(bData);

    }

    @Override
    public int getItemCount() {
        return apartmentList.size();
    }
}
