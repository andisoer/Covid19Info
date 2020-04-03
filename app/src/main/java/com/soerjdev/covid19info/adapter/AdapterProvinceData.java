package com.soerjdev.covid19info.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.soerjdev.covid19info.R;
import com.soerjdev.covid19info.model.provinsi.Provinsi;

import java.util.List;

public class AdapterProvinceData extends RecyclerView.Adapter<AdapterProvinceData.ViewHolder> {

    private List<Provinsi> listProvinsi;
    private Context mContext;

    public AdapterProvinceData(List<Provinsi> listProvinsi, Context mContext) {
        this.listProvinsi = listProvinsi;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_layout_province_data, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Provinsi provinsi = listProvinsi.get(position);

        holder.tvNamaProvinsi.setText(provinsi.getAttributes().getProvinsi());
        holder.tvJumlahPositif.setText(String.valueOf(provinsi.getAttributes().getKasusPosi()));
        holder.tvJumlahSembuh.setText(String.valueOf(provinsi.getAttributes().getKasusSemb()));
        holder.tvJumlahMeninggal.setText(String.valueOf(provinsi.getAttributes().getKasusMeni()));
    }

    @Override
    public int getItemCount() {
        return listProvinsi.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNamaProvinsi, tvJumlahPositif, tvJumlahSembuh, tvJumlahMeninggal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaProvinsi = itemView.findViewById(R.id.tvItemNamaProvinsiProvinceData);
            tvJumlahPositif = itemView.findViewById(R.id.tvItemJumlahPositifProvinceData);
            tvJumlahSembuh = itemView.findViewById(R.id.tvItemJumlahSembuhProvinceData);
            tvJumlahMeninggal = itemView.findViewById(R.id.tvItemJumlahMeninggalProvinceData);
        }
    }
}
