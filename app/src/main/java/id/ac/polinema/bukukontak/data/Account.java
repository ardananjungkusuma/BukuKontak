package id.ac.polinema.bukukontak.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "account")
public class Account {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @Expose
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "username")
    @Expose
    @SerializedName("username")
    private String username;

    @ColumnInfo(name = "password")
    @Expose
    @SerializedName("password")
    private String password;

    @ColumnInfo(name="address")
    @Expose
    @SerializedName("address")
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Account(String username, String password, String address)
    {
        this.username = username;
        this.password = password;
        this.address = address;
    }

}
