package id.ac.polinema.bukukontak.remotedata;

import java.util.List;

import id.ac.polinema.bukukontak.data.Contact;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BukuKontakService {
    @GET("/bukukontak_service/api.php/contacts")
    Call<List<Contact>>getContact();

    @POST("/bukukontak_service/api.php/contacts")
    Call<Contact>postContact(@Body Contact contact);
}
