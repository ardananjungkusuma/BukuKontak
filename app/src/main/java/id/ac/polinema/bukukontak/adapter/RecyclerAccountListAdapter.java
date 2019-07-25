package id.ac.polinema.bukukontak.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.ac.polinema.bukukontak.R;
import id.ac.polinema.bukukontak.data.Account;

public class RecyclerAccountListAdapter extends RecyclerView.Adapter<AccountItemViewHolder> { //todo : terakhir sampe sini gan
    private Context context;
    private List<Account> accountList;

    public RecyclerAccountListAdapter(Context context)
    {
        this.context = context;
    }

    public List<Account> getAccountList()
    {
        return accountList;
    }

    public void setAccountList(List<Account> accountList)
    {
        this.accountList = accountList;

        // Biar nge-refresh
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AccountItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // Dapatkan inflater
        LayoutInflater inflater = LayoutInflater.from(this.context);

        // Render view pakai inflater
        View accountItemView = inflater.inflate(R.layout.account_item_view, parent, false);//todo: Benerin xml account item view

        // View-nya dilempar ke ViewHolder
        AccountItemViewHolder vhAccountItem = new AccountItemViewHolder(accountItemView);

        // Jadikan nilai balik method ini
        return vhAccountItem;
    }

    // Ketika sedang me-render satu-persatu baris dari RecyclerView-nya...
    // Terjadi pe-renderan view holder satu persatu juga.
    @Override
    public void onBindViewHolder(@NonNull AccountItemViewHolder holder, int position)
    {
        // Dapatkan data di posisi saat ini...
        Account currentAccount = this.accountList.get(position);

        // Pasang datanya di ViewHolder saat ini
        String username = currentAccount.getUsername();
        String password = currentAccount.getPassword();
        String address = currentAccount.getAddress();

        holder.getTxvUsername().setText(username);
        holder.getTxvPassword().setText(password);
        holder.getTxvAddress().setText(address);
    }

    @Override
    public int getItemCount()
    {
        if(this.accountList== null)
            return 0;
        else
            return this.accountList.size();
    }
}
