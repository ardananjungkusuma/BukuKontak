package id.ac.polinema.bukukontak.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AccountDao {
    @Insert
    public void addNew(Account account);

    @Query("select*from account")
    public LiveData<List<Account>> findAll();

    @Query("delete from account")
    public void removeAll();
}
