package ec2_group9.idat.amorecaffeapp.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ec2_group9.idat.amorecaffeapp.databinding.FragmentMiCuentaBinding;
import ec2_group9.idat.amorecaffeapp.model.Cliente;
import ec2_group9.idat.amorecaffeapp.viewModel.ClienteViewModel;

public class MiCuentaFragment extends Fragment {

    private FragmentMiCuentaBinding binding;
    private ClienteViewModel clienteViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMiCuentaBinding.inflate(inflater, container,
                false);
        clienteViewModel = new ViewModelProvider(requireActivity())
                .get(ClienteViewModel.class);
        clienteViewModel.findOne("854c11d8-c578-475f-baf0-27fbd803aec2");
        clienteViewModel.findOneMutableLiveData.observe(getViewLifecycleOwner(), new Observer<Cliente>() {
            @Override
            public void onChanged(Cliente cliente) {
                binding.etnombre.setText(cliente.getNombre());
                binding.etapellido.setText(cliente.getApellidos());
                binding.etcorreo.setText(cliente.getCorreo());
            }
        });

        return binding.getRoot();
    }
}