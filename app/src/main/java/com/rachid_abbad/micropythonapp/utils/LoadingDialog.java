package com.rachid_abbad.micropythonapp.utils;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rachid_abbad.micropythonapp.R;

public class LoadingDialog extends BottomSheetDialogFragment {
    Context c;
    String msg;

    public LoadingDialog(Context c, String msg){
        this.c = c;
        this.msg = msg;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(c).inflate(R.layout.loading_layout,container,false);

        ((TextView) v.findViewById(R.id.msg)).setText(msg);

        return v;
    }
}
