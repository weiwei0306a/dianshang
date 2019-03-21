package com.bw.xiangmu.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.xiangmu.R;

public class me extends Fragment {

    private TextView text1;
    private TextView grzl, wdqz, wdzj, wdqb, wddz;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me, null, false);
        grzl = view.findViewById(R.id.grzl);
        wdqz = view.findViewById(R.id.wdqz);
        wdzj = view.findViewById(R.id.wdzj);
        wdqb = view.findViewById(R.id.wdqb);
        wddz = view.findViewById(R.id.wddz);
        return view;
    }
}
