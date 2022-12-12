package ec2_group9.idat.amorecaffeapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;

import ec2_group9.idat.amorecaffeapp.R;
import ec2_group9.idat.amorecaffeapp.databinding.ActivityDetalleProductoBinding;
import ec2_group9.idat.amorecaffeapp.model.Producto;

public class DetalleProductoActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityDetalleProductoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetalleProductoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnAnadir.setOnClickListener(this);
        binding.btnRegresarProducto.setOnClickListener(this);

        if (getIntent().hasExtra("producto")) {
            Producto objProducto = getIntent().getParcelableExtra("producto");
            binding.tvNombreProducto.setText(objProducto.getNombre());
            binding.tvDescripcionProducto.setText(objProducto.getDescripcion());
            binding.tvPrecioProducto.setText(String.valueOf(objProducto.getPrecio()));
            Glide.with(binding.getRoot())
                    .load(objProducto.getImagenUrl())
                    .into(binding.ivDetalleProducto);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnRegresarProducto) {
            onBackPressed();
        } else {

        }
    }
}