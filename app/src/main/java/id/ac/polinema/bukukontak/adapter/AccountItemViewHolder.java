package id.ac.polinema.bukukontak.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.ac.polinema.bukukontak.R;

public class AccountItemViewHolder extends RecyclerView.ViewHolder{
    private TextView txvUsername,txvPassword,txvAddress;

    public AccountItemViewHolder(@NonNull View itemView)
    {
        super(itemView);

        this.txvUsername = itemView.findViewById(R.id.txv_username);
        this.txvPassword = itemView.findViewById(R.id.txv_password);
        this.txvAddress = itemView.findViewById(R.id.txv_address);
    }

    public TextView getTxvUsername() {
        return txvUsername;
    }

    public TextView getTxvPassword() {
        return txvPassword;
    }

    public TextView getTxvAddress(){ return txvAddress; }
}
