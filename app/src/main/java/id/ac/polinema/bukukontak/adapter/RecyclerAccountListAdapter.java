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
import id.ac.polinema.bukukontak.data.Contact;

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
        return vhContactItem;
    }

    // Ketika sedang me-render satu-persatu baris dari RecyclerView-nya...
    // Terjadi pe-renderan view holder satu persatu juga.
    @Override
    public void onBindViewHolder(@NonNull ContactItemViewHolder holder, int position)
    {
        // Dapatkan data di posisi saat ini...
        Contact currentContact = this.contactList.get(position);

        // Pasang datanya di ViewHolder saat ini
        String name = currentContact.getName();
        String phoneNumber = currentContact.getPhoneNumber();
        String image = currentContact.getImage();

        holder.getTxvName().setText(name);
        holder.getTxvPhone().setText(phoneNumber);
        Picasso.get().load(image).placeholder(R.drawable.ic_launcher_background).into(holder.getTxvImage());//todo : picasso
    }

    @Override
    public int getItemCount()
    {
        if(this.contactList == null)
            return 0;
        else
            return this.contactList.size();
    }
}
