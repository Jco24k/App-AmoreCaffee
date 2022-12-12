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
import java.util.stream.Collectors;

import ec2_group9.idat.amorecaffeapp.databinding.ItemCategoriaBinding;
import ec2_group9.idat.amorecaffeapp.databinding.ItemProductoBinding;
import ec2_group9.idat.amorecaffeapp.model.Producto;
import ec2_group9.idat.amorecaffeapp.view.DetalleProductoActivity;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder>{

    List<Producto> listProducto = new ArrayList<>();
    List<Producto> listProductoOriginal = new ArrayList<>();
    private Context context;

    public ProductoAdapter(Context context) { this.context = context; }

    @NonNull
    @Override
    public ProductoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemProductoBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent, false
        ));
    }

    public void setProductos(List<Producto> lista){
        listProducto.addAll(lista);
        listProductoOriginal.addAll(lista);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ViewHolder holder, int position) {
        final Producto itemProducto = listProducto.get(position);
        holder.binding.txtNombre.setText(itemProducto.getNombre());
        Glide.with(holder.binding.getRoot())
                .load(itemProducto.getImagenUrl())
                .into(holder.binding.ivProducto);
        holder.binding.btnDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDetalleProductoActivity = new Intent(context,
                        DetalleProductoActivity.class);
                intentDetalleProductoActivity.putExtra("producto", itemProducto);
                context.startActivity(intentDetalleProductoActivity);
            }
        });
    }

    public void filtrado(String nombreProd) {
        int longitud = nombreProd.length();
        if (longitud == 0){
            listProducto.clear();
            listProducto.addAll(listProductoOriginal);
        } else {
            /*
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Producto> collection = listProducto.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(nombreProd.toLowerCase()))
                        .collect(Collectors.toList());
                listProducto.clear();
                listProducto.addAll(collection);
            } else {

            }
             */
            listProducto.clear();
            for (Producto p: listProductoOriginal) {
                if (p.getNombre().toLowerCase().contains(nombreProd.toLowerCase())) {
                    listProducto.add(p);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listProducto.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemProductoBinding binding;
        public ViewHolder(ItemProductoBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
