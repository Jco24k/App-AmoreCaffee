package ec2_group9.idat.amorecaffeapp.view.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ec2_group9.idat.amorecaffeapp.databinding.ItemDetallepedidoBinding;
import ec2_group9.idat.amorecaffeapp.databinding.ItemProductoBinding;
import ec2_group9.idat.amorecaffeapp.global.CarritoGlobal;
import ec2_group9.idat.amorecaffeapp.model.DetallePedido;
import ec2_group9.idat.amorecaffeapp.view.fragments.CarritoFragment;


public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.ViewHolder> {

    List<DetallePedido> listDetPedido = new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return new ViewHolder(ItemDetallepedidoBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    public void setListDetPedido(List<DetallePedido> lista){
        listDetPedido = lista;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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
                enviarCantidad(holder,pos);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mensajeEmergente(pos);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listDetPedido.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemDetallepedidoBinding binding;
        public ViewHolder(ItemDetallepedidoBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    private void enviarCantidad(@NonNull ViewHolder holder, int index){
        String cant = holder.binding.txtCantidad.getText().toString();
        DetallePedido detallePedido = listDetPedido.get(index);
        double precio = detallePedido.getPrecio();
        int cantidad = 0;
        try {
            cantidad = Integer.parseInt(cant);
            updateSubTotal(holder,precio*cantidad);
            updateListDetallePedido(detallePedido,precio,cantidad);
        }catch (Exception ex){
            cantidad = 1;
            updateSubTotal(holder,precio*cantidad);
            updateListDetallePedido(detallePedido,precio,cantidad);
        }
    }


    public void updateListDetallePedido(DetallePedido detallePedido,double precio,int cantidad){
        detallePedido.setCantidad(cantidad);
        detallePedido.setSubtotal(precio*cantidad);
        CarritoFragment.updateListPedido(listDetPedido);
    }

    public void updateSubTotal(@NonNull ViewHolder holder, double subtotal){
        holder.binding.txtSubTotal.setText(String.format("%.2f",subtotal));
    }

    public int maximoStock(int cantidad, int maximo){
        if(maximo < cantidad){
            Toast.makeText(CarritoGlobal.activityCarrito, "Sotck maximo es "+maximo,
                    Toast.LENGTH_LONG).show();
            return cantidad;
        }
        return cantidad;
    }


    public void mensajeEmergente(int position){
        AlertDialog dialogo = new AlertDialog
                .Builder(CarritoGlobal.activityCarrito)
                .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eliminarProducto(position);

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setTitle("Confirmar") // El título
                .setMessage("¿Deseas eliminar esta Pedido?") // El mensaje
                .create();
        dialogo.show();
    }

    public void eliminarProducto(int position){
        CarritoFragment.eliminarPedido(position);
    }


}
