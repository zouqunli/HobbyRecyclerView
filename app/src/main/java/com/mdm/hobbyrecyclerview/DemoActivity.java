package com.mdm.hobbyrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.mdm.myhobbyrecyclerview.HobbyRecyclerView;
import com.mdm.myhobbyrecyclerview.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        HobbyRecyclerView recyclerView = findViewById(R.id.recyclerView);

        List<String> list = new ArrayList<String>(){
            {
                add("哈哈哈哈sdfsdfadfafadfadfadfadfadfadfadfadfadfadfadfadfadfadfadfadfaasasdasdsdfafhfsdfsdfsdfsdfsdfdsfa哈哈哈哈sdfsdfadfafadfadfadfadfadfadfadfadfadfadfadfadfaddfsdfsdfsdfsdfd");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
                add("哈哈哈哈");
            }
        };

        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(new RecyclerAdapter(this,list));

    }
}
