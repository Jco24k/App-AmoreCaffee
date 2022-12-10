package ec2_group9.idat.amorecaffeapp.view.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ec2_group9.idat.amorecaffeapp.databinding.FragmentMenuBinding;
import ec2_group9.idat.amorecaffeapp.model.Categoria;
import ec2_group9.idat.amorecaffeapp.view.MenuActivity;
import ec2_group9.idat.amorecaffeapp.viewModel.CategoriaViewModel;

public class MenuFragment extends Fragment {
    private FragmentMenuBinding binding;
    private CategoriaViewModel categoriaViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMenuBinding.inflate(inflater, container,
                false);

        /*categoriaViewModel = new ViewModelProvider(requireActivity())
                .get(CategoriaViewModel.class);
        categoriaViewModel.findAll();
        categoriaViewModel.findAllMutableLiveData.observe(getViewLifecycleOwner(),
                new Observer<List<Categoria>>() {
                    @Override
                    public void onChanged(List<Categoria> responseCategorias) {
                        for (int i = 0; i<responseCategorias.size();i++){
                            Log.i(i+1+"CATEGORIAS: ", responseCategorias.get(i).getNombre());
                        }
                    }
                });*/
        return binding.getRoot();
    }
}