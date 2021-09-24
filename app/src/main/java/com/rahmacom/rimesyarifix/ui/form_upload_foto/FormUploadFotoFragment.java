package com.rahmacom.rimesyarifix.ui.form_upload_foto;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.data.model.Image;
import com.rahmacom.rimesyarifix.databinding.FragmentFormUploadFotoBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

import java.io.File;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@AndroidEntryPoint
public class FormUploadFotoFragment extends Fragment {

    private FormUploadFotoViewModel viewModel;
    private FragmentFormUploadFotoBinding binding;
    private NavController navController;
    private PreferenceManager manager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = FragmentFormUploadFotoBinding.inflate(inflater, container, false);
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(FormUploadFotoViewModel.class);
        navController = Navigation.findNavController(view);
        manager = new PreferenceManager(requireContext());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private void setDataBinding(Image image) {
        Glide.with(binding.getRoot())
                .load(Const.BASE_URL + image.getPath())
                .into(binding.ivFormUploadFotoProfil);
    }

    private void updateProfilePhoto() {

    }

    private void showUploadImageDialog() {
        AlertDialog alertDialog = new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Pilih foto")
                .setItems(R.array.dialog_form_upload_foto_list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Timber.d(String.valueOf(which));
                        if (which == 0) {
                            openGallery();
                        }
                    }
                })
                .create();
    }

    private void openGallery() {
        ActivityResultLauncher<String> getSelectedImage = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                Glide.with(binding.getRoot())
                        .load(result)
                        .into(binding.ivFormUploadFotoProfil);
            }
        });

        getSelectedImage.launch("image/*");
    }

    private void openCamera() {

    }
}