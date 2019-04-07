package org.pursuit.mealprep.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.pursuit.mealprep.MainActivity;
import org.pursuit.mealprep.R;
import org.pursuit.mealprep.ViewPagerFragmentInteractionListener;

import static java.lang.Thread.sleep;

public class SplashFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ViewPagerFragmentInteractionListener vpListener;
    private static final int SPLASH_TIME_OUT = 1500;

    public SplashFragment() {
        // Required empty public constructor
    }

    public static SplashFragment newInstance() {
        SplashFragment fragment = new SplashFragment();
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
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof ViewPagerFragmentInteractionListener){
            vpListener = (ViewPagerFragmentInteractionListener) context;
        }else {
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


//        Thread splashThread = new Thread(){
//            @Override
//            public void run() {
////                super.run();
//                try {
//                    sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                vpListener.toSplashPage();
//            }
//        }; SPLASH_TIME_OUT)
//        splashThread.start();
    }
}
