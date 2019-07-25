package id.ac.polinema.bukukontak.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class,Account.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();
    public abstract AccountDao accountDao();//todo : ini harus bikin 2 ato gimana
}



