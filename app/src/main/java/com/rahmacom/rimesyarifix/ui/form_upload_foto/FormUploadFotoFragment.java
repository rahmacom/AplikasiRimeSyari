package com.rahmacom.rimesyarifix.ui.form_upload_foto;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.FragmentFormUploadFotoBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;
import com.rahmacom.rimesyarifix.utils.Helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@AndroidEntryPoint
public class FormUploadFotoFragment extends Fragment {

    private FormUploadFotoViewModel viewModel;
    private FragmentFormUploadFotoBinding binding;
    private NavController navController;
    private PreferenceManager manager;
    private FormUploadFotoFragmentArgs args;

    private ActivityResultLauncher<String> galleryLauncher;

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
        args = FormUploadFotoFragmentArgs.fromBundle(getArguments());

        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));
        setImageBinding(args.getImageUri());

        binding.ivFormUploadFotoProfil.setOnClickListener(v -> showUploadImageDialog());
        binding.ivFormUploadFotoUbah.setOnClickListener(v -> showUploadImageDialog());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        galleryLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                uriResult -> {
                    if (uriResult != null) {
                        Glide.with(binding.getRoot())
                                .load(uriResult)
                                .into(binding.ivFormUploadFotoProfil);

                        try {
                            File file = Helper.bitmapToFile(MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), uriResult), requireActivity());

                            binding.btnFormUploadFotoSimpan.setOnClickListener(v -> updateProfilePhoto(file));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void setImageBinding(String url) {
        Glide.with(binding.getRoot())
                .load(url)
                .into(binding.ivFormUploadFotoProfil);
    }

    private void updateProfilePhoto(File file) {
        viewModel.setLiveFile(file);
        viewModel.uploadImage.observe(getViewLifecycleOwner(), image -> {
            switch (image.getStatus()) {
                case SUCCESS:
                    Toast.makeText(requireContext(), "Foto profil berhasil disimpan.", Toast.LENGTH_SHORT).show();
                    manager.setString(Const.KEY_AVATAR, Const.BASE_URL + image.getData().getPath());
                    break;
                case EMPTY:
                case ERROR:
                case INVALID:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case UNPROCESSABLE_ENTITY:
                    Toast.makeText(requireContext(), "Terjadi error! Tidak dapat mengupload foto. Silahkan hubungi admin rime.", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    private void showUploadImageDialog() {
        AlertDialog alertDialog = new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Pilih foto")
                .setItems(R.array.dialog_form_upload_foto_list, (dialog, which) -> {
                    Timber.d(String.valueOf(which));
                    if (which == 0) {
                        galleryLauncher.launch("image/*");
                    }
                })
                .create();

        alertDialog.show();
    }
}