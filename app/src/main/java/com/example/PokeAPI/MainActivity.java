package com.example.PokeAPI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button buttonSearch;
    private TextView textViewName;
    private TextView textViewHeight;
    private TextView textViewWeight;
    private EditText editTextSearchById;
    private EditText editTextSearchByName;

    private String searchId = "";
    private String searchName = "";

    private double height;
    private double weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String in;
//        JSONObject reader = new JSONObject(in);
//        JSONObject types  = reader.getJSONObject("sys");
//        country = types.getString("country");

        wireWidgets();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PokeService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PokeService pokeService = retrofit.create(PokeService.class);

//        Call<Poke> pokeCall = pokeService.getPokemonById("1");
//        pokeCall.enqueue(new Callback<Poke>() {
//            @Override
//            public void onResponse(Call<Poke> call, Response<Poke> response) {
//                Poke foundPoke = response.body();
//                if(foundPoke != null) {
//                    Toast.makeText(MainActivity.this, foundPoke.getName(), Toast.LENGTH_SHORT).show();
//                    textViewName.setText(foundPoke.getName());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Poke> call, Throwable t) {
//                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pokeById(pokeService);
            }
        });
        editTextSearchById.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchId = editTextSearchById.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editTextSearchByName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchName = editTextSearchByName.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void pokeById(PokeService pokeService) {
        if(searchId.equals("")) {
            Call<Poke> pokeCall = pokeService.getPokemonById(Integer.toString((int) (Math.random() * 807 + 1)));
            pokeCall.enqueue(new Callback<Poke>() {
                @Override
                public void onResponse(Call<Poke> call, Response<Poke> response) {
                    // ANY CODE THAT DEPENDS ON THE RESULT OF THE SEARCH HAS TO GO HERE
                    Poke foundPoke = response.body();
                    // check if the body isn't null
                    if (foundPoke != null) {
                        textViewName.setText("Name: " + foundPoke.getName());
                        height = Double.parseDouble(foundPoke.getHeight()) / 10;
                        textViewHeight.setText("Height: " + height + "m");
                        weight = Double.parseDouble(foundPoke.getWeight()) / 10;
                        textViewWeight.setText("Weight: " + weight + "kg");
                    }
                }

                @Override
                public void onFailure(Call<Poke> call, Throwable t) {
                    // TOAST THE FAILURE
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Call<Poke> pokeCall = pokeService.getPokemonById(searchId);
            pokeCall.enqueue(new Callback<Poke>() {
                @Override
                public void onResponse(Call<Poke> call, Response<Poke> response) {
                    // ANY CODE THAT DEPENDS ON THE RESULT OF THE SEARCH HAS TO GO HERE
                    Poke foundPoke = response.body();
                    // check if the body isn't null
                    if (foundPoke != null) {
                        textViewName.setText("Name: " + foundPoke.getName());
                        height = Double.parseDouble(foundPoke.getHeight()) / 10;
                        textViewHeight.setText("Height: " + height + "m");
                        weight = Double.parseDouble(foundPoke.getWeight()) / 10;
                        textViewWeight.setText("Weight: " + weight + "kg");
                    }
                }

                @Override
                public void onFailure(Call<Poke> call, Throwable t) {
                    // TOAST THE FAILURE
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

//    public void pokeByName(PokeService pokeService) {
//        if(searchName.equals("")) {
//            Call<Poke> pokeCall = pokeService.getPokemonById(Integer.toString((int) (Math.random() * 807 + 1)));
//            pokeCall.enqueue(new Callback<Poke>() {
//                @Override
//                public void onResponse(Call<Poke> call, Response<Poke> response) {
//                    // ANY CODE THAT DEPENDS ON THE RESULT OF THE SEARCH HAS TO GO HERE
//                    Poke foundPoke = response.body();
//                    // check if the body isn't null
//                    if (foundPoke != null) {
//                        textViewName.setText("Name: " + foundPoke.getName());
//                        height = Double.parseDouble(foundPoke.getHeight()) / 10;
//                        textViewHeight.setText("Height: " + height + "m");
//                        weight = Double.parseDouble(foundPoke.getWeight()) / 10;
//                        textViewWeight.setText("Weight :" + weight + "kg");
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Poke> call, Throwable t) {
//                    // TOAST THE FAILURE
//                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        else {
//
//        }
//    }

    private void wireWidgets() {
        buttonSearch = findViewById(R.id.button_main_search);
        textViewName = findViewById(R.id.textView_main_name);
        textViewHeight = findViewById(R.id.textView_main_height);
        textViewWeight = findViewById(R.id.textView_main_weight);
        editTextSearchById = findViewById(R.id.editText_main_id);
        editTextSearchByName = findViewById(R.id.editText_main_name);
    }
}
