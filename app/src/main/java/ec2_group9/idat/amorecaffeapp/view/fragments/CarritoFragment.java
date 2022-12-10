package ec2_group9.idat.amorecaffeapp.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ec2_group9.idat.amorecaffeapp.databinding.FragmentCarritoBinding;

public class CarritoFragment extends Fragment {
    private FragmentCarritoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCarritoBinding.inflate(inflater, container,
                false);
        return binding.getRoot();
    }
}