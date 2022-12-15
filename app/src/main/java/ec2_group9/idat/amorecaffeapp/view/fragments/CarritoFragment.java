package ec2_group9.idat.amorecaffeapp.view.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import ec2_group9.idat.amorecaffeapp.R;
import ec2_group9.idat.amorecaffeapp.databinding.FragmentCarritoBinding;
import ec2_group9.idat.amorecaffeapp.global.CarritoGlobal;
import ec2_group9.idat.amorecaffeapp.model.DetallePedido;
import ec2_group9.idat.amorecaffeapp.view.DetalleProductoActivity;
import ec2_group9.idat.amorecaffeapp.view.PagoActivity;
import ec2_group9.idat.amorecaffeapp.view.adapter.CarritoAdapter;

import java.util.List;

public class CarritoFragment extends Fragment implements View.OnClickListener {
    private static FragmentCarritoBinding binding;
    public static CarritoAdapter adapterCar = new CarritoAdapter();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCarritoBinding.inflate(inflater, container,
                false);

        binding.btnPedido.setOnClickListener(this);
        CarritoGlobal.setActivityCarrito(this.getActivity());

        enviarResultadoInputs();

        //CARGANDO LOS DATOS AL RECYCLERVIEW CON EL ADAPTADOR
        binding.nvdetpedido.setLayoutManager(new LinearLayoutManager(requireActivity()));
        adapterCar.setListDetPedido(CarritoGlobal.listaDetallePedido);
        binding.nvdetpedido.setAdapter(adapterCar);

        return binding.getRoot();
    }


    private static double calcularTotal(){
        double total = 0;
        for (DetallePedido det:
                CarritoGlobal.listaDetallePedido) {
            total += det.getSubtotal();
        }
        return total;
    }

    private static void enviarResultadoInputs(){
        binding.txtTotal.setText(String.format("%.2f", calcularTotal()));
    }

    public static void cargarRecyclerView(){
        enviarResultadoInputs();
        adapterCar.setListDetPedido(CarritoGlobal.listaDetallePedido);
        binding.nvdetpedido.setAdapter(adapterCar);
    }


    public static void updateListPedido(List<DetallePedido> lista ){
        CarritoGlobal.updateListDetallePedido(lista);
        enviarResultadoInputs();
    }


    public static void eliminarPedido(int position){
        CarritoGlobal.removeByPosition(position);
        cargarRecyclerView();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnPedido) {
            if(CarritoGlobal.listaDetallePedido.size() == 0){
                Toast.makeText(CarritoGlobal.activityCarrito, "Carrito de Pedido Vacio",Toast.LENGTH_SHORT).show();
            }else{
                Intent intentPago = new Intent(this.getContext(),
                        PagoActivity.class);
                this.getContext().startActivity(intentPago);
            }
        }
    }
}