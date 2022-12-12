package ec2_group9.idat.amorecaffeapp.view.adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import ec2_group9.idat.amorecaffeapp.databinding.ItemProductoBinding;
import ec2_group9.idat.amorecaffeapp.model.DetallePedido;
import ec2_group9.idat.amorecaffeapp.view.fragments.CarritoFragment;


public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.ViewHolder> {

    List<DetallePedido> listDetPedido = new ArrayList<>();
    TextWatcher textWatcher = null;

    @NonNull
    @Override
    public CarritoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return new ViewHolder(ItemProductoBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    public void setListDetPedido(List<DetallePedido> lista){
        listDetPedido.addAll(lista);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoAdapter.ViewHolder holder, int position) {
        final DetallePedido responseDetalle = listDetPedido.get(position);

        int pos = position;
        holder.binding.txtNomPro.setText(responseDetalle.getProducto().getNombre().toUpperCase());
        holder.binding.txtDescripcion.setText(responseDetalle.getProducto().getDescripcion());
        holder.binding.txtSubTotal.setText(String.format("%.2f", responseDetalle.getSubtotal()));
        holder.binding.txtCantidad.setText(responseDetalle.getCantidad()+"");
        int indexSelection = String.valueOf(holder.binding.txtCantidad.getText()).length();
        holder.binding.txtCantidad.setSelection(indexSelection);
        holder.binding.txtCantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enviarCantidad(holder.binding.txtCantidad.getText().toString(),pos);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return listDetPedido.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemProductoBinding binding;
        public ViewHolder(ItemProductoBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    private void enviarCantidad(String cant, int index){
        try {
            int cantidad = Integer.parseInt(cant);
            updateListDetallePedido(cantidad,index);
        }catch (Exception ex){
            updateListDetallePedido(1,index);
        }
    }


    public void updateListDetallePedido(int cantidad,int index){
        Log.i("cantidad",cantidad+"");
        listDetPedido.get(index).setCantidad(cantidad);
        listDetPedido.get(index).setSubtotal(cantidad*listDetPedido.get(index).getPrecio());
        CarritoFragment.cargarRecyclerView(listDetPedido);
    }

}
