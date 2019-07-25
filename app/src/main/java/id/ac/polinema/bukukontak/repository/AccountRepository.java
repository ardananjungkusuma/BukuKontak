package id.ac.polinema.bukukontak.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.polinema.bukukontak.data.Account;
import id.ac.polinema.bukukontak.data.AccountDao;
import id.ac.polinema.bukukontak.data.AppDatabase;
import id.ac.polinema.bukukontak.data.AppDbProvider;
import id.ac.polinema.bukukontak.remotedata.AppServiceProvider;
import id.ac.polinema.bukukontak.remotedata.BukuKontakService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepository {
    //property instance databse
    private AppDatabase database;
    //property instance service
    private BukuKontakService service;

    private LiveData<List<Account>> accountList;

    public AccountRepository(Context context){
        //isi instance database
        this.database= AppDbProvider.getAppDbInstance(context);

        //isi instance service
        this.service = AppServiceProvider.getBukuKontakService();
    }

    public LiveData<List<Account>>getAccountList(){
        if(this.isOnline()){
            this.getAccountListFromDb();
            this.getAccountListFromWeb();
        }
        else{
            this.getAccountListFromDb();
        }
        return this.accountList;
    }

    private boolean isOnline(){
        return true;
    }

    private void getAccountListFromWeb() {
        Call<List<Account>> accountListCall=this.service.getAccount();

        //kirim request
        accountListCall.enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                //hapus dulu semua database sebelum download
                new AccountRepository.DeleteAllAccount().execute();
                List<Account>accountList=response.body();
                Account[] arrAccount = new Account[accountList.size()];
                for(int i=0;i<arrAccount.length;i++){
                    arrAccount [i] = accountList.get(i);
                }new AccountRepository.SaveAccountTask().execute(arrAccount);
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                Log.e("Errorrrrrrrr","erroorrrrrr " + t.getMessage());
            }
        });
    }

    private void getAccountListFromDb(){
        //Ambil dao dari database
        AccountDao dao = this.database.accountDao();

        //ambil data dari tabel lewat dao
        this.accountList= dao.findAll();
    }
    public class SaveAccountTask extends AsyncTask<Account,Void,Void> {

        @Override
        protected Void doInBackground(Account... accounts) {
            AccountDao dao = database.accountDao();
            //ambil kontak pertama dari parameter

            for(int i=0;i<accounts.length;i++){
                Account c = accounts[i];
                dao.addNew(c);
            }
            return null;
        }
    }
    public void saveAccount(Account account){
        if(this.isOnline()){
            this.saveAccountToWeb(account);
        }
        else{
            this.saveAccountToDb(account);
        }
    }

    private class DeleteAllAccount extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            //hapus data lewat DAO
            database.accountDao().removeAll();

            return null;
        }
    }
    private void saveAccountToWeb(Account account) {
        Call<Account>postAccountCall=this.service.postAccount(account);
        postAccountCall.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                getAccountListFromWeb();
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {

            }
        });
    }

    private void saveAccountToDb(Account account) {
        new AccountRepository.SaveAccountTask().execute(account);
    }
}
