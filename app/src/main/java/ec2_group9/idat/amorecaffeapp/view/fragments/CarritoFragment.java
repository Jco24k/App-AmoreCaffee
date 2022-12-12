package ec2_group9.idat.amorecaffeapp.view.fragments;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;

import ec2_group9.idat.amorecaffeapp.R;
import ec2_group9.idat.amorecaffeapp.databinding.FragmentCarritoBinding;
import ec2_group9.idat.amorecaffeapp.databinding.ItemProductoBinding;
import ec2_group9.idat.amorecaffeapp.model.Categoria;
import ec2_group9.idat.amorecaffeapp.model.DetallePedido;
import ec2_group9.idat.amorecaffeapp.model.Producto;
import ec2_group9.idat.amorecaffeapp.view.adapters.CarritoAdapter;

import java.util.List;

public class CarritoFragment extends Fragment{
    private static FragmentCarritoBinding binding;
    public static CarritoAdapter adapterCar = new CarritoAdapter();
    private ItemProductoBinding bindingProducto;
    public static List<DetallePedido> listDetallePedido = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCarritoBinding.inflate(inflater, container,
                false);
        bindingProducto = ItemProductoBinding.inflate(inflater, container,
                false);
        //CREANDO INSTANCIAS A UTILIZAR
        Categoria categoria = new Categoria("1","bebidas",true,null);
        Producto producto = new Producto("1","agua","agua cielo 1L",3.50,10,categoria,true);
        Producto producto2 = new Producto("1","coca cola","coca cola 600ml",2.00,15,categoria,true);
        int cantidadPedido = 2,cantidadPedido2 = 3;
        double precioPedido = producto.getPrecio(), precioPedido2 = producto2.getPrecio();

        //CREAR LISTA DE  DETALLE_PEDIDO
        DetallePedido detallePedido = new DetallePedido(
                precioPedido,cantidadPedido, precioPedido * cantidadPedido,producto
        );
        DetallePedido detallePedido2 = new DetallePedido(
                precioPedido,cantidadPedido, precioPedido2 * cantidadPedido2,producto2
        );
        DetallePedido detallePedido3 = new DetallePedido(
                precioPedido,cantidadPedido, precioPedido * cantidadPedido,producto
        );
        DetallePedido detallePedido4 = new DetallePedido(
                precioPedido,cantidadPedido, precioPedido2 * cantidadPedido2,producto2
        );

        listDetallePedido = Arrays.asList(detallePedido,detallePedido2,detallePedido3,detallePedido4);


        //CALCULAR TOTAL Y ENVIAR AL INPUT LOS RESULTADOS
        enviarResultadoInputs();

        //CARGANDO LOS DATOS AL RECYCLERVIEW CON EL ADAPTADOR
        binding.nvdetpedido.setLayoutManager(new LinearLayoutManager(requireActivity()));
        adapterCar.setListDetPedido(listDetallePedido);
        binding.nvdetpedido.setAdapter(adapterCar);





        return binding.getRoot();
    }


    private static double calcularTotal(){
        double total = 0;
        for (DetallePedido det:
             listDetallePedido) {
            total += det.getSubtotal();
        }
        return total;
    }

    private static void enviarResultadoInputs(){
        Log.i("TOTAL:",String.format("%.2f", calcularTotal()));
        binding.txtTotal.setText(String.format("%.2f", calcularTotal()));
    }

    public static void cargarRecyclerView(List<DetallePedido> lista){
        listDetallePedido = lista;
        for (int i = 0; i<listDetallePedido.size();i++){
            Log.i(i+1+"DetPedido",lista.get(i).getProducto().getNombre()+lista.get(i).getCantidad()+"");
        }
        enviarResultadoInputs();
        adapterCar.setListDetPedido(listDetallePedido);
        binding.nvdetpedido.setAdapter(adapterCar);
    }

}