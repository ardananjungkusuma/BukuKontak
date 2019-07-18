package id.ac.polinema.bukukontak.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.polinema.bukukontak.data.AppDatabase;
import id.ac.polinema.bukukontak.data.AppDbProvider;
import id.ac.polinema.bukukontak.data.Contact;
import id.ac.polinema.bukukontak.data.ContactDao;
import id.ac.polinema.bukukontak.remotedata.AppServiceProvider;
import id.ac.polinema.bukukontak.remotedata.BukuKontakService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactRepository {
    //property instance databse
    private AppDatabase database;
    //property instance service
    private BukuKontakService service;

    private LiveData<List<Contact>> contactList;

    public ContactRepository(Context context){
        //isi instance database
        this.database=AppDbProvider.getAppDbInstance(context);

        //isi instance service
        this.service = AppServiceProvider.getBukuKontakService();
    }

    public LiveData<List<Contact>>getContactList(){
        if(this.isOnline()){
            this.getContactListFromDb();
            this.getContactListFromWeb();
        }
        else{
            this.getContactListFromDb();
        }
    return this.contactList;
    }

    private boolean isOnline(){
        return true;
    }

    private void getContactListFromWeb() {
        Call<List<Contact>> contactListCall=this.service.getContact();

        //kirim request
        contactListCall.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                List<Contact>contactList=response.body();

                /*for(Contact contact = response.body()){
                    new SaveContactTask().execute(c);
                }*/

                Contact [] arrContact = new Contact[contactList.size()];
                for(int i=0;i<arrContact.length;i++){
                    arrContact [i] = contactList.get(i);
                }new SaveContactTask().execute(arrContact);

            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Log.e("Errorrrrrrrr","erroorrrrrr " + t.getMessage());
            }
        });
    }

    private void getContactListFromDb(){
        //Ambil dao dari database
        ContactDao dao = this.database.contactDao();

        //ambil data dari tabel lewat dao
        this.contactList= dao.findAll();
    }
    public class SaveContactTask extends AsyncTask<Contact,Void,Void>{

        @Override
        protected Void doInBackground(Contact... contacts) {
            ContactDao dao = database.contactDao();
            //ambil kontak pertama dari parameter
            Contact c = contacts[0];
            dao.addNew(c);
            return null;
        }
    }
    public void saveContact(Contact contact){
        if(this.isOnline()){
            this.saveContactToWeb(contact);
        }
        else{
            this.saveContactToDb(contact);
        }
    }

    private void saveContactToWeb(Contact contact) {
    }

    private void saveContactToDb(Contact contact) {
        //todo : perbaiki ini agar nanti asynchronous
        new SaveContactTask().execute(contact);
    }
}
