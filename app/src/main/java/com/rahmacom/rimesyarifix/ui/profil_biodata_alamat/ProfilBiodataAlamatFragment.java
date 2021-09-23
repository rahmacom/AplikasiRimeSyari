package com.rahmacom.rimesyarifix.ui.profil_biodata_alamat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.data.model.UserShipment;
import com.rahmacom.rimesyarifix.databinding.FragmentProfilBiodataAlamatBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.ui.form_profil_biodata_alamat.FormProfilBiodataAlamatFragment;
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
        if (binding == null) {
            binding = FragmentProfilBiodataAlamatBinding.inflate(inflater, container, false);
        }
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
        setupToolbar();
        getShipmentAddresses();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_profil_biodata_alamat_add) {
            newShipmentAddress();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.toolbarProfilBiodataAlamat, navController, appBarConfiguration);
        binding.toolbarProfilBiodataAlamat.setTitle("Alamat yang disimpan");

        binding.toolbarProfilBiodataAlamat.getMenu().clear();
        binding.toolbarProfilBiodataAlamat.inflateMenu(R.menu.menu_profil_biodata_alamat_toolbar);
        binding.toolbarProfilBiodataAlamat.setOnMenuItemClickListener(this::onOptionsItemSelected);
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
                navController
                        .getPreviousBackStackEntry()
                        .getSavedStateHandle()
                        .set("user_shipment_id", userShipment.getId());

                navController.popBackStack();
            } else {
                updateShipmentAddress(userShipment.getId());
            }
        });

        adapter.setOnLongItemClickListener((view, userShipment) -> {
            PopupMenu menu = new PopupMenu(requireContext(), view);
            menu.getMenuInflater().inflate(R.menu.menu_profil_biodata_alamat_floating, menu.getMenu());

            menu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.menu_profil_biodata_alamat_set_as_default:
                        setAsDefaultShipmentAddress(userShipment);
                        return true;

                    case R.id.menu_profil_alamat_edit:
                        ProfilBiodataAlamatFragmentDirections.ProfilBiodataAlamatFragmentToFormAlamatFragment action = ProfilBiodataAlamatFragmentDirections.profilBiodataAlamatFragmentToFormAlamatFragment();
                        action.setUserShipmentId(userShipment.getId());
                        action.setState(FormProfilBiodataAlamatFragment.IS_UPDATING);
                        navController.navigate(action);
                        return true;

                    case R.id.menu_profil_biodata_alamat_remove:
                        removeShipmentAddress(userShipment);
                        return true;
                }

                return false;
            });

            menu.show();
        });
    }

    private void getShipmentAddresses() {
        viewModel.userShipmentAddresses.observe(getViewLifecycleOwner(), userShipment -> {
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

    private void setAsDefaultShipmentAddress(UserShipment shipment) {
        viewModel.setLiveUserShipment(shipment.getId());
        viewModel.setAsDefaultShipment.observe(getViewLifecycleOwner(), userShipment -> {
            Timber.d(userShipment.getMessage());
            Timber.d(userShipment.getStatus().toString());
            switch (userShipment.getStatus()) {
                case SUCCESS:
                    Toast.makeText(requireContext(), "Berhasil set alamat menjadi default", Toast.LENGTH_SHORT).show();
                    manager.setInt(Const.KEY_USER_SHIPMENT_ID, userShipment.getData().getId());
                    adapter.addCheck(userShipment.getData());
                    break;

                case LOADING:
                    break;

                case EMPTY:
                case ERROR:
                case INVALID:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case UNPROCESSABLE_ENTITY:
                    Toast.makeText(requireContext(), "Terjadi error! Silahkan coba lagi atau hubungi admin rime syari", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    private void newShipmentAddress() {
        ProfilBiodataAlamatFragmentDirections.ProfilBiodataAlamatFragmentToFormAlamatFragment action = ProfilBiodataAlamatFragmentDirections.profilBiodataAlamatFragmentToFormAlamatFragment();
        action.setState(FormProfilBiodataAlamatFragment.IS_CREATING);
        navController.navigate(action);
    }

    private void updateShipmentAddress(int userShipmentId) {
        ProfilBiodataAlamatFragmentDirections.ProfilBiodataAlamatFragmentToFormAlamatFragment action = ProfilBiodataAlamatFragmentDirections.profilBiodataAlamatFragmentToFormAlamatFragment();
        action.setUserShipmentId(userShipmentId);
        action.setState(FormProfilBiodataAlamatFragment.IS_UPDATING);
        navController.navigate(action);
    }

    private void removeShipmentAddress(UserShipment shipment) {
        viewModel.setLiveUserShipment(shipment.getId());
        viewModel.removeUserShipmentAddress.observe(getViewLifecycleOwner(), userShipment -> {
            Timber.d(userShipment.getStatus().toString());
            switch (userShipment.getStatus()) {
                case SUCCESS:
                    Toast.makeText(requireContext(), "Alamat berhasil dihapus", Toast.LENGTH_SHORT).show();
                    adapter.removeItem(shipment);
                    break;

                case LOADING:
                    break;

                case EMPTY:
                case ERROR:
                case INVALID:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case UNPROCESSABLE_ENTITY:
                    Toast.makeText(requireContext(), "Terjadi error! Tidak dapat menghapus alamat. Silahkan coba lagi.", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }
}