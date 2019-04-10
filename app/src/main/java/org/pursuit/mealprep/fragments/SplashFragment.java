package org.pursuit.mealprep.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.pursuit.mealprep.FragmentInteractionListener;
import org.pursuit.mealprep.R;

import static java.lang.Thread.sleep;

public class SplashFragment extends Fragment {

    private FragmentInteractionListener vpListener;
    private static final int SPLASH_TIME_OUT = 1500;

    public SplashFragment() { }

    public static SplashFragment newInstance() {
        SplashFragment fragment = new SplashFragment();
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
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInteractionListener) {
            vpListener = (FragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        vpListener = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        new Handler().postDelayed(() -> {
            try {
                sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            vpListener.toViewPagerFragment();
        }, SPLASH_TIME_OUT);
    }
}
