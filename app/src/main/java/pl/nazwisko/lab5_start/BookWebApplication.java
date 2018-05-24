package pl.nazwisko.lab5_start;

import android.app.Application;
import android.util.Log;

import java.util.List;

import pl.nazwisko.lab5_start.activities.service.BookWebService;
import pl.nazwisko.lab5_start.comment.Comment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class BookWebApplication extends Application {
BookWebService bookWebService;
Comment comment;

    public BookWebService getBookWebService() {
        return bookWebService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://bookweb.ciolek.info/api/v1/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        bookWebService = retrofit.create(BookWebService.class);
    }
}
