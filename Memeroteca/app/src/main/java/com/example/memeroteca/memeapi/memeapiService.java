package com.example.memeroteca.memeapi;


import com.example.memeroteca.models.MemeRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface memeapiService {
    @GET ("get_memes")
    Call<MemeRespuesta> obtenerListaMeme();

}
