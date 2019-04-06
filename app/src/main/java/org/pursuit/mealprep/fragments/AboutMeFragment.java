package org.pursuit.mealprep.fragments;


import android.content.Intent;
import android.content.UriMatcher;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.pursuit.mealprep.R;

public class AboutMeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
//TextView getapp =(TextView) findViewById(R.id.getapp);
//getapp.setMovementMethod(LinkMovementMethod.getInstance());

    public AboutMeFragment() {
        // Required empty public constructor
    }

    public static AboutMeFragment newInstance() {
        AboutMeFragment fragment = new AboutMeFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_me, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        TextView linkedIn = view.findViewById(R.id.linkedIn);
//        linkedIn.setMovementMethod(LinkMovementMethod.getInstance());
////        super.onViewCreated(view, savedInstanceState);

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
