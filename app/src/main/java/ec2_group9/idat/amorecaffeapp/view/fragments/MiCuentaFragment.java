package ec2_group9.idat.amorecaffeapp.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ec2_group9.idat.amorecaffeapp.R;
import ec2_group9.idat.amorecaffeapp.databinding.FragmentMiCuentaBinding;
import ec2_group9.idat.amorecaffeapp.global.AuthClienteGlobal;
import ec2_group9.idat.amorecaffeapp.global.CarritoGlobal;
import ec2_group9.idat.amorecaffeapp.model.Cliente;
import ec2_group9.idat.amorecaffeapp.view.EditarClienteActivity;
import ec2_group9.idat.amorecaffeapp.view.MainActivity;
import ec2_group9.idat.amorecaffeapp.view.MenuActivity;
import ec2_group9.idat.amorecaffeapp.viewModel.ClienteViewModel;

public class MiCuentaFragment extends Fragment implements View.OnClickListener {

    private static FragmentMiCuentaBinding binding;
    private ClienteViewModel clienteViewModel;
    private static Cliente cli = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMiCuentaBinding.inflate(inflater, container,
                false);
        binding.btneditar.setOnClickListener(this);
        binding.btncerrar.setOnClickListener(this);

        clienteViewModel = new ViewModelProvider(requireActivity())
                .get(ClienteViewModel.class);
        clienteViewModel.findOne(AuthClienteGlobal.idCliente);
        clienteViewModel.findOneMutableLiveData.observe(getViewLifecycleOwner(), new Observer<Cliente>() {
            @Override
            public void onChanged(Cliente cliente) {
                cli = cliente;
                binding.etnombre.setText(cli.getNombre());
                binding.etapellido.setText(cli.getApellidos());
                binding.etcorreo.setText(cli.getCorreo());
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btncerrar) {
            CarritoGlobal.iniciarCarrito();
            AuthClienteGlobal.iniciarDatos();
            Intent intentLogin = new Intent(this.getContext(), MainActivity.class);
            startActivity(intentLogin);
        } else if (view.getId() == R.id.btneditar) {
            Intent intentEditarCliente = new Intent(this.getContext(), EditarClienteActivity.class);
            startActivity(intentEditarCliente);
        }
    }

    public static void updateCliente(Cliente updateCliente){
        cli = updateCliente;
        binding.etnombre.setText(cli.getNombre());
        binding.etapellido.setText(cli.getApellidos());
        binding.etcorreo.setText(cli.getCorreo());
    }

}