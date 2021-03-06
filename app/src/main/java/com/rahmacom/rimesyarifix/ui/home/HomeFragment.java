package com.rahmacom.rimesyarifix.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.data.model.Post;
import com.rahmacom.rimesyarifix.data.model.Product;
import com.rahmacom.rimesyarifix.databinding.FragmentHomeBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;

    private NavController navController;
    private PreferenceManager manager;
    private HomeProdukAdapter adapter;
    private HomePostAdapter postAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        navController = Navigation.findNavController(view);
        manager = new PreferenceManager(requireContext());

        if (!manager.keyExists(Const.KEY_TOKEN)) {
            navController.navigate(HomeFragmentDirections.globalToLoginFragment());
        }

        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));
        getAllProducts();
        getLatestPosts();
        refreshProfile();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_keranjang) {
            navController.navigate(HomeFragmentDirections.navHomeToKeranjangFragment());
        }
        return super.onOptionsItemSelected(item);
    }

    private void getAllProducts() {
        viewModel.getAllProducts.observe(getViewLifecycleOwner(), product -> {
            if (product != null) {
                switch (product.getStatus()) {
                    case SUCCESS:
                        ArrayList<Product> products = new ArrayList<>(product.getData());
                        setUpDataProduk(products);
                        adapter.setOnItemClickListener(item -> {
                            HomeFragmentDirections.NavHomeToProdukFragment action = HomeFragmentDirections.navHomeToProdukFragment();
                            action.setProductId(item.getId());
                            navController.navigate(action);
                        });
                        break;

                    case ERROR:
                    case LOADING:
                    case EMPTY:
                    case INVALID:
                }
            }
        });

        binding.toolbarFragmentHome.inflateMenu(R.menu.menu_home);
        binding.toolbarFragmentHome.setOnMenuItemClickListener(this::onOptionsItemSelected);
    }

    private void getLatestPosts() {
        viewModel.getLatestPosts.observe(getViewLifecycleOwner(), posts -> {
            switch (posts.getStatus()) {
                case SUCCESS:
                    setupPostBanner((ArrayList<Post>) posts.getData());
                    break;

                case LOADING:
                case EMPTY:
                case ERROR:
                case INVALID:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case UNPROCESSABLE_ENTITY:
                    break;
            }
        });
    }

    private void setupPostBanner(ArrayList<Post> items) {
        postAdapter = new HomePostAdapter();
        postAdapter.setList(items);

        binding.vpHomePostSlider.setAdapter(postAdapter);
        postAdapter.setOnItemClickListener(post -> {
            HomeFragmentDirections.NavHomeToPostinganFragment action = HomeFragmentDirections.navHomeToPostinganFragment();
            action.setPostId(post.getId());
            navController.navigate(action);
        });
    }

    private void setUpDataProduk(ArrayList<Product> list) {
        adapter = new HomeProdukAdapter();
        adapter.setLists(list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);

        binding.rvHomeProdukGrid.setAdapter(adapter);
        binding.rvHomeProdukGrid.setLayoutManager(gridLayoutManager);
    }

    private void refreshProfile() {
        viewModel.getUserProfile.observe(getViewLifecycleOwner(), user -> {
            switch (user.getStatus()) {
                case SUCCESS:
                    manager.setString(Const.KEY_NIK, user.getData().getNik());
                    manager.setString(Const.KEY_NAMA_LENGKAP, user.getData().getNamaLengkap());
                    manager.setString(Const.KEY_EMAIL, user.getData().getEmail());
                    manager.setString(Const.KEY_JENIS_KELAMIN, user.getData().getJenisKelamin());
                    manager.setString(Const.KEY_ALAMAT, user.getData().getAlamat());
                    manager.setString(Const.KEY_TEMPAT_LAHIR, user.getData().getTempatLahir());
                    manager.setString(Const.KEY_TGL_LAHIR, user.getData().getTglLahir());
                    manager.setString(Const.KEY_NO_TELP, user.getData().getNoHp());
                    manager.setString(Const.KEY_NO_WA, user.getData().getNoWa());
                    manager.setString(Const.KEY_ROLE, user.getData().getRoles().get(0));
                    if (user.getData().getAvatar() != null) {
                        manager.setString(Const.KEY_AVATAR, Const.BASE_URL + user.getData().getAvatar().getPath());
                    }
                    break;

                case EMPTY:
                case ERROR:
                case INVALID:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case UNPROCESSABLE_ENTITY:
                    break;
            }
        });
    }
}