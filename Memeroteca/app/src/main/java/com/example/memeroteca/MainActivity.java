package com.example.memeroteca;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;

import com.example.memeroteca.memeapi.memeapiService;
import com.example.memeroteca.models.Meme;
import com.example.memeroteca.models.MemeRespuesta;

import java.net.URI;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static final  String TAG = "Memes";
    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.imgflip.com/get_memes")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        obtenerDatos();
    }

    private void obtenerDatos() {
        memeapiService sevice = retrofit.create(memeapiService.class);
        Call<MemeRespuesta> memeRespuestaCall = sevice.obtenerListaMeme();
        memeRespuestaCall.enqueue(new Callback<MemeRespuesta>() {
            @Override
            public void onResponse(Call<MemeRespuesta> call, Response<MemeRespuesta> response) {
                if(response.isSuccessful()){
                    MemeRespuesta memeRespuesta = response.body();
                    ArrayList<Meme> listaMemes = MemeRespuesta.getResults();
                    for (int i = 0; i < listaMemes.size(); i++){
                        Meme m = listaMemes.get(i);
                        Log.i(TAG," memes: " + m.getName());
                    }
                }else{
                    Log.e(TAG, " onResponse" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<MemeRespuesta> call, Throwable t) {
                Log.e(TAG,"onFailure:" + t.getMessage());
            }
        });
    }
}