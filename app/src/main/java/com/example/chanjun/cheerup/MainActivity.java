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
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class MainActivity extends AppCompatActivity {
    FrameLayout mainframe;
    ScrollView scrollView1;
    ScrollView scrollView2;
    EditText editText;
    Button sendButton;
    TextView resultArea;
    boolean isSearchMenuOpen =false;
    boolean isCategoryMenuOpen=false;
    RelativeLayout SearchMenu;
    RelativeLayout CategoryMenu;


    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView1 = (ScrollView) findViewById(R.id.getSeoulList);
        scrollView2 = (ScrollView) findViewById(R.id.getSuwonList);
        SearchMenu = (RelativeLayout) findViewById(R.id.searchMenu);
        CategoryMenu = (RelativeLayout) findViewById(R.id.CategoryMenu);




        //editText = (EditText) findViewById(R.id.coName);
        //sendButton = (Button) findViewById(R.id.generator);
        //resultArea = (TextView) findViewById(R.id.result);
        //데이터 삽입
        /*sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecruitingData corData = new RecruitingData(editText.getText().toString(),"2017.05.05","Seoul","IT");  // 유저 이름과 메세지로 chatData 만들기
                databaseReference.child("information").push().setValue(corData);  // 기본 database 하위 message라는 child에 chatData를 list로 만들기
                editText.setText("");           }
            });

        //데이터 불러오기
        databaseReference.child("information").addChildEventListener(new ChildEventListener() {  // message는 child의 이벤트를 수신합니다.
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                RecruitingData corData = dataSnapshot.getValue(RecruitingData.class);  // chatData를 가져오고
                resultArea.setText(corData.getCorporationName() + ": " + corData.getDate());  // adapter에 추가합니다.
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });*/

    }

    //액션바 포팅.
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

    //메인화면 라디오버튼 뷰 변경.
    public void onRadioButton1Clicked(View v) {changeImage1();}
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


    //메인화면 메뉴바, 검색 아이콘 클릭 시 뷰 변경.
    public void onSearchMenuClicked(View v){
        openSearchMenu();
    }
    public void openSearchMenu(){
        if(!isSearchMenuOpen) {
            if(isCategoryMenuOpen){
                CategoryMenu.setVisibility(View.INVISIBLE);
                isCategoryMenuOpen = false;
            }
            SearchMenu.setVisibility(View.VISIBLE);
            isSearchMenuOpen = true;
        }
        else if(isSearchMenuOpen){
            SearchMenu.setVisibility(View.INVISIBLE);
            isSearchMenuOpen = false;
        }
    }
    public void onCategoryMenuClicked(View v){
        openCategoryMenu();
    }
    public void openCategoryMenu(){
        if(!isCategoryMenuOpen) {
            if(isSearchMenuOpen){
                SearchMenu.setVisibility(View.INVISIBLE);
                isSearchMenuOpen = false;
            }
            CategoryMenu.setVisibility(View.VISIBLE);
            isCategoryMenuOpen = true;
        }
        else if(isCategoryMenuOpen){
            CategoryMenu.setVisibility(View.INVISIBLE);
            isCategoryMenuOpen = false;
        }
    }




}





