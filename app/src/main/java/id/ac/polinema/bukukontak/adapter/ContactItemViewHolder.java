package id.ac.polinema.bukukontak.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.ac.polinema.bukukontak.R;

public class ContactItemViewHolder extends RecyclerView.ViewHolder
{
    private TextView txvName;
    private TextView txvPhone;
    private ImageView txvImage;

    public ContactItemViewHolder(@NonNull View itemView)
    {
        super(itemView);

        this.txvName = itemView.findViewById(R.id.txv_name);
        this.txvPhone = itemView.findViewById(R.id.txv_phone);
        this.txvImage = itemView.findViewById(R.id.image);
    }

    public TextView getTxvName() {
        return txvName;
    }

    public TextView getTxvPhone() {
        return txvPhone;
    }

    public ImageView getTxvImage(){
        return txvImage;
    }
}
