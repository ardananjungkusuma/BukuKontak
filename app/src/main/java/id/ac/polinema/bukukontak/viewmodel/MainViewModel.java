package id.ac.polinema.bukukontak.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.polinema.bukukontak.data.Contact;
import id.ac.polinema.bukukontak.repository.ContactRepository;

public class MainViewModel extends AndroidViewModel {
    private ContactRepository contactRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);

        this.contactRepository = new ContactRepository(application);
    }

    //mendapatkan semua kontak yang ada didalam database
    public LiveData<List<Contact>> getContactList(){
        //mengambil data dari repo
        return this.contactRepository.getContactList();
    }

    //untuk menyimpan kontak
    public void saveContact(Contact contact){

    }
}