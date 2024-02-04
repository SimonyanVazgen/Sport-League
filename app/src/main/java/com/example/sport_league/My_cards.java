package com.example.sport_league;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class My_cards extends AppCompatActivity {
    SearchView searchView;
    RecyclerView recyclerView;
    ArrayList<Model> arrayList= new ArrayList<>();

    ArrayList<Model> searchList;
    String[] cardlist = new String[]{"Zelarayan","Messi","Mkitaryan","Neymar Jr","Suarez","Di maria","Konte"};

    String[] cardNum  = new String[]{"card 1","card 2","card 3","card 4","card 5","card 6","card 7","card 8","card 9"};

    int[] imgList = new int[]{R.drawable.zelarayan,R.drawable.messi,R.drawable.mkitaryan,R.drawable.neymar,R.drawable.suarez,R.drawable.dimaria,R.drawable.konate};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cards);
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.serchView);

        for (int i = 0; i < cardlist.length; i++) {
        Model model = new Model();
        model.setCardName(cardlist[i]);
        model.setCardNum(cardNum[i]);
        model.setImg(imgList[i]);
        arrayList.add(model);
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(My_cards.this);
        recyclerView.setLayoutManager(layoutManager);

      CradAdapter cradAdapterd = new CradAdapter(My_cards.this,arrayList);
      recyclerView.setAdapter(cradAdapterd);

      searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
          @Override
          public boolean onQueryTextSubmit(String query) {
              searchList = new ArrayList<>();

              if(query.length() > 0){
                  for (int i = 0; i < arrayList.size(); i++) {
                      if(arrayList.get(i).getCardName().toUpperCase().contains(query.toUpperCase())){

                          Model model = new Model();
                          model.setCardName(arrayList.get(i).getCardName());
                          model.setCardNum(arrayList.get(i).getCardNum());
                          model.setImg(arrayList.get(i).getImg());
                          searchList.add(model);
                      }

                  }
                  RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(My_cards.this);
                  recyclerView.setLayoutManager(layoutManager);

                  CradAdapter cradAdapterd = new CradAdapter(My_cards.this,searchList);
                  recyclerView.setAdapter(cradAdapterd);

              }
              else {
                  RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(My_cards.this);
                  recyclerView.setLayoutManager(layoutManager);

                  CradAdapter cradAdapterd = new CradAdapter(My_cards.this,arrayList);
                  recyclerView.setAdapter(cradAdapterd);

              }
              return false;
          }

          @Override
          public boolean onQueryTextChange(String newText) {
              searchList = new ArrayList<>();

              if(newText.length() > 0){
                  for (int i = 0; i < arrayList.size(); i++) {
                      if(arrayList.get(i).getCardName().toUpperCase().contains(newText.toUpperCase())){

                          Model model = new Model();
                          model.setCardName(arrayList.get(i).getCardName());
                          model.setCardNum(arrayList.get(i).getCardNum());
                          model.setImg(arrayList.get(i).getImg());
                          searchList.add(model);
                      }

                  }
                  RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(My_cards.this);
                  recyclerView.setLayoutManager(layoutManager);

                  CradAdapter cradAdapterd = new CradAdapter(My_cards.this,searchList);
                  recyclerView.setAdapter(cradAdapterd);

              }
              else {
                  RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(My_cards.this);
                  recyclerView.setLayoutManager(layoutManager);

                  CradAdapter cradAdapterd = new CradAdapter(My_cards.this,arrayList);
                  recyclerView.setAdapter(cradAdapterd);

              }

              return false;
          }
      });
    }
}
//R.drawable.capdevil,R.drawable.cordoba,R.drawable.deagocarlos,R.drawable.donarumma,R.drawable.dybala,R.drawable.ferrari,R.drawable.fred,R.drawable.gomez,R.drawable.haland,R.drawable.insigne,R.drawable.jesus,R.drawable.karvajal,R.drawable.mudryk,R.drawable.muriel,R.drawable.rodrigo,R.drawable.rubenneves,R.drawable.sancho,R.drawable.smolarek,R.drawable.reus,R.drawable.apedipele
//,"Reus","Apedi Pele","Capdevil","Cordoba","Diego Carlos","Donarumma","Dybala","Ferrari","Fred","Gomez","Haland","Insigne","Jesus","Karvajal","Mudryk","Muriel","Rodrigo","Ruben Neves","Sancho","Smolarek"