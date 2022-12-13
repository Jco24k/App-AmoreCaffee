package ec2_group9.idat.amorecaffeapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ec2_group9.idat.amorecaffeapp.databinding.ItemCategoriaBinding;
import ec2_group9.idat.amorecaffeapp.model.Categoria;
import ec2_group9.idat.amorecaffeapp.view.ProductoActivity;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.ViewHolder>{

    List<Categoria> listCategoria = new ArrayList<>();
    private Context context;

    public CategoriaAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CategoriaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemCategoriaBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent, false
        ));
    }

    public void setCategorias(List<Categoria> lista) {
        listCategoria = lista;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaAdapter.ViewHolder holder, int position) {
        final Categoria itemCategoria = listCategoria.get(position);
        Glide.with(holder.binding.getRoot())
                .load(itemCategoria.getImagenUrl())
                .into(holder.binding.ivCategoria);
        holder.binding.contenedorCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentProductoActivity = new Intent(context,
                        ProductoActivity.class);
                intentProductoActivity.putExtra("categoria", itemCategoria);
                context.startActivity(intentProductoActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCategoria.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemCategoriaBinding binding;
        public ViewHolder(ItemCategoriaBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
