package ec2_group9.idat.amorecaffeapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import ec2_group9.idat.amorecaffeapp.R;
import ec2_group9.idat.amorecaffeapp.databinding.ActivityDetalleProductoBinding;
import ec2_group9.idat.amorecaffeapp.global.CarritoGlobal;
import ec2_group9.idat.amorecaffeapp.model.Producto;

public class DetalleProductoActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityDetalleProductoBinding binding;
    Producto objProducto =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetalleProductoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnAnadir.setOnClickListener(this);
        binding.btnRegresarProducto.setOnClickListener(this);

        if (getIntent().hasExtra("producto")) {
            objProducto = getIntent().getParcelableExtra("producto");
            binding.tvNombreProducto.setText(objProducto.getNombre());
            binding.tvDescripcionProducto.setText(objProducto.getDescripcion());
            binding.tvPrecioProducto.setText("S/. "+String.format("%.2f", objProducto.getPrecio()));
            Glide.with(binding.getRoot())
                    .load(objProducto.getImagenUrl())
                    .into(binding.ivDetalleProducto);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnRegresarProducto) {
            onBackPressed();
        } else if(view.getId() == R.id.btnAnadir) {
            if(objProducto != null){
                boolean msj = CarritoGlobal.agregarDetalle(objProducto);
                if(msj){
                    Toast.makeText(this, "Producto agregado correctamente",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CarritoGlobal.activityCarrito, "Stock maximo disponible: "+objProducto.getCantidad(),
                            Toast.LENGTH_SHORT).show();
                }

            }

        }
    }
}