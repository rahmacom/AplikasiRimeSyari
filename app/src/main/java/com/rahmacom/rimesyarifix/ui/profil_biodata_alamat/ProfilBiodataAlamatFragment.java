package com.rahmacom.rimesyarifix.ui.profil_biodata_alamat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rahmacom.rimesyarifix.data.model.UserShipment;
import com.rahmacom.rimesyarifix.databinding.FragmentProfilBiodataAlamatBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@AndroidEntryPoint
public class ProfilBiodataAlamatFragment extends Fragment {

    private FragmentProfilBiodataAlamatBinding binding;
    private ProfilBiodataAlamatAdapter adapter;
    private ProfilBiodataAlamatViewModel viewModel;
    private PreferenceManager manager;
    private NavController navController;
    private ProfilBiodataAlamatFragmentArgs args;

    private int state;

    public static final int IS_SELECTING = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfilBiodataAlamatBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ProfilBiodataAlamatViewModel.class);
        manager = new PreferenceManager(requireContext());
        navController = Navigation.findNavController(view);

        args = ProfilBiodataAlamatFragmentArgs.fromBundle(getArguments());
        state = args.getViewState();
        Timber.d(String.valueOf(state));

        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));
        getShipmentAddresses();
    }

    private void setupRecyclerView(ArrayList<UserShipment> items) {
        adapter = new ProfilBiodataAlamatAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        adapter.setList(items);

        binding.rvProfilBiodataAlamat.setAdapter(adapter);
        binding.rvProfilBiodataAlamat.setHasFixedSize(true);
        binding.rvProfilBiodataAlamat.setLayoutManager(layoutManager);
        binding.rvProfilBiodataAlamat.addItemDecoration(new DividerItemDecoration(requireContext(), layoutManager.getOrientation()));

        adapter.setOnItemClickListener(userShipment -> {
            if (state == IS_SELECTING) {
                setShipmentAddressForNewOrder(userShipment.getId());
            } else {
                updateShipmentAddress(userShipment.getId());
            }
        });
    }

    private void setShipmentAddressForNewOrder(int userShipmentId) {
        navController
                .getPreviousBackStackEntry()
                .getSavedStateHandle()
                .set("user_shipment_id", userShipmentId);

        navController.popBackStack();
    }

    private void getShipmentAddresses() {
        viewModel.getUserShipmentAddresses.observe(getViewLifecycleOwner(), userShipment -> {
            switch (userShipment.getStatus()) {
                case SUCCESS:
                    setupRecyclerView((ArrayList<UserShipment>) userShipment.getData());
                    break;

                case EMPTY:
                case LOADING:
                case ERROR:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case INVALID:
                case UNPROCESSABLE_ENTITY:
                    break;
            }
        });
    }

    private void updateShipmentAddress(int userShipmentId) {
        ProfilBiodataAlamatFragmentDirections.ProfilBiodataAlamatFragmentToFormAlamatFragment action = ProfilBiodataAlamatFragmentDirections.profilBiodataAlamatFragmentToFormAlamatFragment();
        action.setUserShipmentId(userShipmentId);
        navController.navigate(action);
    }
}