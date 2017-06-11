package com.example.chanjun.cheerup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class MainActivity extends AppCompatActivity {
    FrameLayout mainframe;
    ScrollView scrollView1;
    ScrollView scrollView2;
    EditText editText;
    Button sendButton;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView1 = (ScrollView) findViewById(R.id.getSeoulList);
        scrollView2 = (ScrollView) findViewById(R.id.getSuwonList);




        editText = (EditText) findViewById(R.id.coName);
        sendButton = (Button) findViewById(R.id.generator);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecruitingData corData = new RecruitingData(editText.getText().toString(),"20170505","Seoul","IT");  // 유저 이름과 메세지로 chatData 만들기
                databaseReference.child("information").push().setValue(corData);  // 기본 database 하위 message라는 child에 chatData를 list로 만들기
                editText.setText("");           }
            });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar actionBar = getSupportActionBar();

        // Custom Actionbar를 사용하기 위해 CustomEnabled을 true 시키고 필요 없는 것은 false 시킨다
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);            //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        actionBar.setDisplayShowTitleEnabled(false);        //액션바에 표시되는 제목의 표시유무를 설정합니다.
        actionBar.setDisplayShowHomeEnabled(false);            //홈 아이콘을 숨김처리합니다.


        //layout을 가지고 와서 actionbar에 포팅을 시킵니다.
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionbar = inflater.inflate(R.layout.layout_actionbar, null);
        actionBar.setCustomView(actionbar);

        //액션바 색상.
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.argb(255,85,129,57)));

        //액션바 양쪽 공백 없애기
        //Toolbar parent = (Toolbar)actionbar.getParent();
        //parent.setContentInsetsAbsolute(0,0);

        return true;
    }

    public void onRadioButton1Clicked(View v) {
        changeImage1();
    }

    public void changeImage1() {
        scrollView1.setVisibility(View.VISIBLE);
        scrollView2.setVisibility(View.INVISIBLE);
    }
    public void onRadioButton2Clicked(View v) {
        changeImage2();
    }

    public void changeImage2() {
        scrollView1.setVisibility(View.INVISIBLE);
        scrollView2.setVisibility(View.VISIBLE);
    }



}





