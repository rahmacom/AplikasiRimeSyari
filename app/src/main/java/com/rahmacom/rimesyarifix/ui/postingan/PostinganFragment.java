package com.rahmacom.rimesyarifix.ui.postingan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.rahmacom.rimesyarifix.data.model.Post;
import com.rahmacom.rimesyarifix.databinding.FragmentPostinganBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;
import com.rahmacom.rimesyarifix.utils.Helper;

public class PostinganFragment extends Fragment {

    private PostinganViewModel viewModel;
    private FragmentPostinganBinding binding;
    private PreferenceManager manager;
    private NavController navController;
    private PostinganFragmentArgs args;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPostinganBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(PostinganViewModel.class);
        manager = new PreferenceManager(requireContext());
        navController = Navigation.findNavController(view);
        args = PostinganFragmentArgs.fromBundle(getArguments());

        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));
        setupToolbar();
        viewPost();
    }

    private void setupToolbar() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.toolbarPostingan, navController, appBarConfiguration);
    }

    private void setDataBinding(Post post) {
        Glide.with(requireView())
                .load(Const.BASE_URL + post.getImage().getPath())
                .into(binding.ivPostFoto);

        binding.tvPostJudul.setText(post.getJudul());
        binding.tvPostKonten.setText(HtmlCompat.fromHtml(post.getKonten(), HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));
        binding.tvPostCreatedAt.setText("Updated: " + Helper.toDate(post.getLastUpdated()));
    }

    private void viewPost() {
        viewModel.setLivePostId(args.getPostId());
        viewModel.viewPost.observe(getViewLifecycleOwner(), post -> {
            switch (post.getStatus()) {
                case SUCCESS:
                    setDataBinding(post.getData());
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
}