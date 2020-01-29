package com.example.PokeAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeService {
    String BASE_URL = "https://pokeapi.co/api/v2/";

    @GET("pokemon/{id}")
    Call<Poke> getPokemonById(@Path("id") String id);

    @GET("pokemon/{name}")
    Call<Poke> getPokemonByName(@Path("name") String name);
}
