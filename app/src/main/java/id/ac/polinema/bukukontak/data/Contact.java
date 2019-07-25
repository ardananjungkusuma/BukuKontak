package id.ac.polinema.bukukontak.data;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "contact")
public class Contact
{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @Expose
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "name")
    @Expose
    @SerializedName("name")
    private String name;

    @ColumnInfo(name = "phone_number")
    @Expose
    @SerializedName("phone_number")
    private String phoneNumber;

    @ColumnInfo(name="image")
    @Expose
    @SerializedName("image")
    private String image;

    public Contact(String name, String phoneNumber,String image)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
