package ec2_group9.idat.amorecaffeapp.global;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ec2_group9.idat.amorecaffeapp.model.CabeceraPedido;
import ec2_group9.idat.amorecaffeapp.model.Categoria;
import ec2_group9.idat.amorecaffeapp.model.DetallePedido;
import ec2_group9.idat.amorecaffeapp.model.Producto;

public class CarritoGlobal {
    public static List<DetallePedido> listaDetallePedido = new ArrayList<>();
    public static Activity activityCarrito= null;

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

    public static boolean agregarDetalle(Producto pro ){
        boolean add = true;
        for (DetallePedido det:listaDetallePedido) {
            if(det.getProducto().getId().equals(pro.getId())){
                if(det.getCantidad() < pro.getCantidad()){
                    det.setCantidad(det.getCantidad()+1);
                    det.setSubtotal(det.getPrecio()* det.getCantidad());
                }else return false;
                add = false;
                break;
            }
        }
        if(add) {
            listaDetallePedido.add(new DetallePedido(
                    pro.getPrecio(), 1, pro.getPrecio() * 1, pro
            ));
        }
        return true;
    }

    public static void iniciarCarrito(){
        listaDetallePedido = new ArrayList<>();
    }

    public static void addCabeceraPedido(CabeceraPedido cab){
        for (DetallePedido det:listaDetallePedido) {
            det.setCabeceraPedido(cab);
        }
    }
}
