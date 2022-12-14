package ec2_group9.idat.amorecaffeapp.filters;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.widget.Toast;

import ec2_group9.idat.amorecaffeapp.global.CarritoGlobal;

public class CantidadFilter implements InputFilter {

    private int min, max;

    public CantidadFilter(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public CantidadFilter(String min, String max) {
        this.min = Integer.parseInt(min);
        this.max = Integer.parseInt(max);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            String cantidadAnterior = dest.toString();
            String teclaPersionada = source.toString();
            String cantidadActual = cantidadAnterior.substring(0,dend)+teclaPersionada+cantidadAnterior.substring(dend);
            if (isInRange(min, max, Integer.parseInt(cantidadActual)))
                return null;
        } catch (NumberFormatException nfe) { return ""; }
        Toast.makeText(CarritoGlobal.activityCarrito, "Stock maximo disponible: "+max,
                Toast.LENGTH_SHORT).show();
        return "";
    }

    private boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }

}