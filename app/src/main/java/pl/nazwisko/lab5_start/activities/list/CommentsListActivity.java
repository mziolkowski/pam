package pl.nazwisko.lab5_start.activities.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import pl.nazwisko.lab5_start.BookWebApplication;
import pl.nazwisko.lab5_start.R;
import pl.nazwisko.lab5_start.activities.service.BookWebService;
import pl.nazwisko.lab5_start.comment.Comment;
import pl.nazwisko.lab5_start.comment.CommentAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Aktywność wyświetlająca listę komentarzy.
 *
 * @author Michał Ciołek
 */
public class CommentsListActivity extends AppCompatActivity {

    private CommentAdapter commentsListAdapter;
    BookWebApplication bookWebApplication;
    BookWebService bookWebService ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_list);

        // TODO Spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner5);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner5, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              if(position == 0) {
                  downloadComments(10);
              } else if (position == 1) {
                  downloadComments(20);
              } else if (position == 2) {
                  downloadComments(30);
              }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // znajdź RecyclerView
        RecyclerView commentListRecyclerView = findViewById(R.id.comments_list_recycler_view);

        // ustawienie sposobu rozmieszczenia elementów
        commentListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // utworzenie adaptera wraz z komentarzami odczytanymi z pliku
        commentsListAdapter = new CommentAdapter();

        // połączenie adaptera z RecyclerView
        commentListRecyclerView.setAdapter(commentsListAdapter);

        downloadComments(2);
    }

    /**
     * Pobieranie komentarzy z Rest API
     *
     * @param perPage liczba pobranych komentarzy
     */
    private void downloadComments(int perPage) {
        //1. pobranie komentarzy z serwera
        bookWebApplication = (BookWebApplication)getApplication();
        bookWebService = bookWebApplication.getBookWebService();

        Call<List<Comment>> repo = bookWebService.listComments(perPage);

        repo.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(response.isSuccessful()){
                    //2. przekazanie komentarzy do commentsListAdapter
                    commentsListAdapter.setComments(response.body());
                    for(int i = 0; i<response.body().size();i++) {
                        Log.d("TAG", response.body().get(i).toString());
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.d("TAG", "Wystapil blad!");
                Log.d("TAG", t.toString());
            }
        });


    }
}
