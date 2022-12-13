package ec2_group9.idat.amorecaffeapp.global;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ec2_group9.idat.amorecaffeapp.model.Categoria;
import ec2_group9.idat.amorecaffeapp.model.DetallePedido;
import ec2_group9.idat.amorecaffeapp.model.Producto;

public class CarritoGlobal {
    public static List<DetallePedido> listaDetallePedido = null;
    public static Activity activityCarrito= null;

    public static void cargarListaDetalllePrueba(){
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

        listaDetallePedido = Arrays.asList(detallePedido,detallePedido2,detallePedido3,detallePedido4);
    }

    public static void setActivityCarrito(Activity activity){
        activityCarrito = activity;
    }

    public static void removeByPosition(int position){
        List<DetallePedido> newList = new ArrayList<>(listaDetallePedido);
        newList.remove(position);
        updateListDetallePedido(newList);
    }

    public static void updateListDetallePedido(List<DetallePedido> lista){
        listaDetallePedido = lista;
    }

    public static void agregarDetalle(DetallePedido detallePedido ){
        listaDetallePedido.add(detallePedido);
    }
}
