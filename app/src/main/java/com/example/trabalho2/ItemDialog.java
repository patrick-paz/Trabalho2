package com.example.trabalho2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ItemDialog extends DialogFragment implements DialogInterface.OnClickListener {


    private EditText edtItem;
    private String item;
    private OnItemListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.titleDialog);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog_item, null);

        builder.setView(layout);

        edtItem = layout.findViewById(R.id.edtItem);

        builder.setPositiveButton(R.string.positive, this);
        builder.setNegativeButton(R.string.negative, this);

        if(item != null) {
            edtItem.setText(item);
        }

        return builder.create();
    }


    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public void onClick(DialogInterface dialog, int i) {
        if(i == DialogInterface.BUTTON_POSITIVE) {
            String item = edtItem.getText().toString();

            if(!TextUtils.isEmpty(item)){
                listener.onItem(item);
            }
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof OnItemListener){
            listener = (OnItemListener) context;
        }
        else{
            throw new RuntimeException("A activity deve " +
                    "implementar o listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnItemListener{
        void onItem(String item);
    }
}
