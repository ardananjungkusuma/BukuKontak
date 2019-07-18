package id.ac.polinema.bukukontak.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {
    @Insert
    public void addNew(Contact contact);

    @Query("select*from contact")
    public LiveData<List<Contact>>findAll();

    @Query("delete from contact")
    public void removeAll();
}
