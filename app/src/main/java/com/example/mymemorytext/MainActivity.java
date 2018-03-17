package com.example.mymemorytext;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> dataList=new ArrayList<>();
    private List<Memory> memoryList;
    private ArrayAdapter<String>adapter;
    private ListView listView;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter=new ArrayAdapter<String>(
                MainActivity.this,android.R.layout.simple_list_item_1,dataList);
        listView=(ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        Connector.getDatabase();
        fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,TextActivity.class);
                startActivity(intent);
            }
        });
        queryMemory();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
               String mem=dataList.get(position);
               Log.d("MainActivity",mem);
               Intent intent=new Intent(MainActivity.this,LookActivity.class);
               intent.putExtra("title",mem);
               startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        queryMemory();
    }

    private void queryMemory(){
        memoryList= DataSupport.select("title").find(Memory.class);
        if(memoryList.size()>=0){
            dataList.clear();
            for(Memory memory:memoryList){
                dataList.add(memory.getTitle());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
        }
        else{
            Toast.makeText(MainActivity.this,"暂无备忘录",Toast.LENGTH_SHORT).show();
        }
    }
}
