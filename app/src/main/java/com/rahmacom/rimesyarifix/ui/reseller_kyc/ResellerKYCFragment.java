package com.rahmacom.rimesyarifix.ui.reseller_kyc;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.AspectRatio;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.common.util.concurrent.ListenableFuture;
import com.rahmacom.rimesyarifix.databinding.FragmentResellerKycCameraBinding;
import com.rahmacom.rimesyarifix.utils.Helper;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import id.zelory.compressor.Compressor;
import timber.log.Timber;

public class ResellerKYCFragment extends Fragment {

    public static final String KYC_ARGS = "kyc_args";
    public static final int KYC_FACE = 1;
    public static final int KYC_ID_CARD = 2;
    public static final int KYC_FACE_AND_ID_CARD = 3;

    private static final String TAG = ResellerKYCFragment.class.getSimpleName();
    private static final String FILENAME_FORMAT = "yyyy-MM-dd_HH-mm-ss-SSS";
    private static final String[] REQUIRED_PERMISSIONS = new String[]{
            "android.permission.CAMERA",
            "android.permission.WRITE_EXTERNAL_STORAGE"
    };

    private static final int REQUEST_CODE_PERMISSIONS = 10;

    private ResellerKYCViewModel viewModel;
    private FragmentResellerKycCameraBinding binding;
    private ResellerKYCFragmentArgs args;

    private ImageCapture imageCapture;
    private File outputDir;
    private ExecutorService cameraExecutor;
    private CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;
    private Boolean switchCam = true;

    private NavController navController;

    private FragmentActivity activity;

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        requireActivity().getWindow()
                .addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        requireActivity().getWindow()
                .clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentResellerKycCameraBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = requireActivity();
        args = ResellerKYCFragmentArgs.fromBundle(getArguments());

        if (isAllPermissionGranted()) {
            startCamera(cameraSelector);
        } else {
            ActivityCompat.requestPermissions(activity, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }

        navController = Navigation.findNavController(view);

        binding.btnTakePhoto.setOnClickListener(v -> takePhoto());
        binding.btnSwitchCamera.setOnClickListener(v -> {
            switchCam = !switchCam;
            if (switchCam) {
                cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;
            } else {
                cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA;
            }
            startCamera(cameraSelector);
        });

        outputDir = Helper.getOutputDir(activity);

        cameraExecutor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        cameraExecutor.shutdown();
        binding = null;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (isAllPermissionGranted()) {
                startCamera(cameraSelector);
            } else {
                Toast.makeText(activity, "Permission not granted by User", Toast.LENGTH_SHORT)
                        .show();

                activity.finish();
            }
        }
    }

    private void takePhoto() {
        ImageCapture imgCapture = this.imageCapture;
        if (imgCapture != null) {
            File photoFile = new File(outputDir, new SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(System.currentTimeMillis()) + ".jpg");

            ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions.Builder(photoFile).build();

            imgCapture.takePicture(outputFileOptions, ContextCompat.getMainExecutor(requireContext()), new ImageCapture.OnImageSavedCallback() {
                @Override
                public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                    Compressor compressor = new Compressor(requireContext())
                            .setMaxHeight(1280)
                            .setMaxWidth(720);
                    File compressedFile = null;

                    try {
                        compressedFile = compressor.compressToFile(photoFile);
                        String message = "Photo captured successfully: " + photoFile.getAbsolutePath();
                        Timber.d(message);
                    } catch (IOException e) {
                        Timber.e(e);
                    } finally {
                        ResellerKYCFragmentDirections.ResellerKYCFragmentToResellerKYCPreviewFragment action = ResellerKYCFragmentDirections.resellerKYCFragmentToResellerKYCPreviewFragment(null);
                        action.setFileUri(compressedFile.getPath());
                        action.setImageType(args.getImageType());
                        navController.navigate(action);
                    }
                }

                @Override
                public void onError(@NonNull @NotNull ImageCaptureException exception) {
                    Timber.e(exception);
                }
            });
        }
    }

    private void startCamera(CameraSelector cameraSelector) {
        final ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(requireActivity());

        cameraProviderFuture.addListener(() -> {
            ProcessCameraProvider cameraProvider = null;
            try {
                cameraProvider = cameraProviderFuture.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

            Preview preview = new Preview.Builder().build();
            preview.setSurfaceProvider(binding.viewFinder.getSurfaceProvider());

            imageCapture = new ImageCapture.Builder()
                    .setTargetAspectRatio(AspectRatio.RATIO_16_9)
                    .build();

            try {
                if (cameraProvider != null) {
                    cameraProvider.unbindAll();
                    cameraProvider.bindToLifecycle(activity, cameraSelector, preview, imageCapture);
                }
            } catch (Exception e) {
                Timber.e(e);
            }
        }, ContextCompat.getMainExecutor(requireActivity()));
    }

    private boolean isAllPermissionGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(requireContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}