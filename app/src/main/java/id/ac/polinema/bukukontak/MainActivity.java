package id.ac.polinema.bukukontak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import id.ac.polinema.bukukontak.adapter.RecyclerContactListAdapter;
import id.ac.polinema.bukukontak.data.Contact;
import id.ac.polinema.bukukontak.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity
{
    //viewmodel
    private MainViewModel mainViewModel;

    // Data
    private RecyclerContactListAdapter recyclerContactListAdapter;
    private ArrayList<Contact> contactList;

    // Components
    private RecyclerView recyclerContactList;
    private EditText edtName;
    private EditText edtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mendapatkan instance ViewModel
        //ini harus import dulu di gradle
        this.mainViewModel= ViewModelProviders.of(this).get(MainViewModel.class);

        this.initData();
        this.initComponents();
    }

    private void initData()
    {
        this.contactList = new ArrayList<>();

        // Tambahin dummy data..
        /*this.contactList.add(new Contact("Alan Walker", "0857-123-456"));*/

        // Seting recycler view-nya
        this.recyclerContactListAdapter = new RecyclerContactListAdapter(this);
    }

    private void initComponents()
    {
        this.recyclerContactList = this.findViewById(R.id.recycler_contact_list);
        this.recyclerContactList.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerContactList.setAdapter(this.recyclerContactListAdapter);

        this.edtName = this.findViewById(R.id.edt_name);
        this.edtPhone = this.findViewById(R.id.edt_phone);

        // Load data ke recycler View
        /*this.recyclerContactListAdapter.setContactList(this.contactList);*/
        this.mainViewModel.getContactList().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                recyclerContactListAdapter.setContactList(contacts);
            }
        });
    }

    public void onBtnSave_Click(View view)
    {
        Contact newContact = this.makeContact();
        this.mainViewModel.saveContact(newContact);
/*        this.contactList.add(newContact);

        this.recyclerContactListAdapter.setContactList(this.contactList);*/
    }

    private Contact makeContact()
    {
        String name = this.edtName.getText().toString();
        String phoneNumber = this.edtPhone.getText().toString();

        Contact c = new Contact(name, phoneNumber);

        return c;
    }
}
