package id.ac.polinema.bukukontak.remotedata;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppServiceProvider
{
    private static final String BASE_URL = "http://192.168.1.19/";

    private static BukuKontakService bukuKontakService;

    public static BukuKontakService getBukuKontakService()
    {
        if(AppServiceProvider.bukuKontakService == null)
        {
            // Siapkan Logging untuk HTTP Client
            HttpLoggingInterceptor httpLogging = new HttpLoggingInterceptor();
            httpLogging.setLevel(HttpLoggingInterceptor.Level.BODY);

            // Buat HTTP Client, nantinya untuk di-set-kan ke Retrofit Builder
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            if(!httpClient.interceptors().contains(httpLogging))
                httpClient.addInterceptor(httpLogging);

            // Buat dulu Retrofit Builder
            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(AppServiceProvider.BASE_URL);
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.client(httpClient.build());

            // Dari Builder baru bisa didapatkan instance Retrofit
            Retrofit retrofit = builder.build();
            AppServiceProvider.bukuKontakService = retrofit.create(BukuKontakService.class);
        }

        return AppServiceProvider.bukuKontakService;
    }
}