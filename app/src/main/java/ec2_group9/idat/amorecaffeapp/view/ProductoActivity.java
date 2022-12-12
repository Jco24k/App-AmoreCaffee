package ec2_group9.idat.amorecaffeapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import java.util.List;

import ec2_group9.idat.amorecaffeapp.R;
import ec2_group9.idat.amorecaffeapp.databinding.ActivityProductoBinding;
import ec2_group9.idat.amorecaffeapp.model.Categoria;
import ec2_group9.idat.amorecaffeapp.model.Producto;
import ec2_group9.idat.amorecaffeapp.view.adapter.ProductoAdapter;
import ec2_group9.idat.amorecaffeapp.view.fragments.MenuFragment;
import ec2_group9.idat.amorecaffeapp.viewModel.ProductoViewModel;

public class ProductoActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, View.OnClickListener {

    private ActivityProductoBinding binding;
    private ProductoViewModel productoViewModel;
    private ProductoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnRegresarCategoria.setOnClickListener(this);

        if (getIntent().hasExtra("categoria")){
            Categoria objCategoria = getIntent().getParcelableExtra("categoria");
            binding.txtNombreCategoria.setText(objCategoria.getNombre());
            productoViewModel = new ViewModelProvider(this)
                    .get(ProductoViewModel.class);
            binding.rvProducto.setLayoutManager(new LinearLayoutManager(ProductoActivity.this));
            adapter = new ProductoAdapter(ProductoActivity.this);
            binding.rvProducto.setAdapter(adapter);
            productoViewModel.findProductCateg(objCategoria.getNombre());
            productoViewModel.findProductCategMutableLiveData.observe(ProductoActivity.this,
                    new Observer<List<Producto>>() {
                        @Override
                        public void onChanged(List<Producto> productos) {
                            adapter.setProductos(productos);
                        }
                    });
            binding.txtBuscar.setOnQueryTextListener(this);
        }

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnRegresarCategoria) {
            onBackPressed();
        }
    }
}