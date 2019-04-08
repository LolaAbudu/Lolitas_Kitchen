package org.pursuit.mealprep.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.pursuit.mealprep.R;

public class AboutMeFragment extends Fragment {

    public AboutMeFragment() {
    }

    public static AboutMeFragment newInstance() {
        AboutMeFragment fragment = new AboutMeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about_me, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView github = view.findViewById(R.id.about_me_github);
        github.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://github.com/LolaAbudu");
            Intent gitHubIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(gitHubIntent);
        });

        TextView linkedIn = view.findViewById(R.id.about_me_linkedIn);
        linkedIn.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://www.linkedin.com/in/omolola-abudu/");
            Intent linkedInIntent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(linkedInIntent);
        });
    }
}
