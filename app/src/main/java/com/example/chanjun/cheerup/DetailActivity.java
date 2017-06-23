package com.example.chanjun.cheerup;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class DetailActivity extends AppCompatActivity {

    boolean isSearchMenuOpen =false;
    boolean isCategoryMenuOpen=false;
    RelativeLayout SearchMenu;
    RelativeLayout CategoryMenu;
    Button MainButton;
    Button sendButton;
    EditText editText;
    ListView listView;
    static ChatAdapter adapter;
    static int countReplies;
    TextView comment;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_information);


        SearchMenu = (RelativeLayout) findViewById(R.id.searchMenu);
        CategoryMenu = (RelativeLayout) findViewById(R.id.CategoryMenu);

        MainButton = (Button)findViewById(R.id.MainButton);
        MainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

        });



        editText = (EditText) findViewById(R.id.createReply);
        sendButton = (Button) findViewById(R.id.generator);
        listView = (ListView) findViewById(R.id.listView);



        adapter = new ChatAdapter();
        listView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView);
        comment = (TextView) findViewById(R.id.comment);
        comment.setText("댓글("+adapter.getCount()+")");


        //데이터 삽입
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatData chatData = new ChatData("현재사용자", editText.getText().toString());  // 유저 이름과 메세지로 chatData 만들기
                databaseReference.child("message").push().setValue(chatData);  // 기본 database 하위 message라는 child에 chatData를 list로 만들기
                editText.setText("");
                comment = (TextView) findViewById(R.id.comment);
            }
            });


        //데이터 불러오기
       databaseReference.child("message").addChildEventListener(new ChildEventListener() {  // message는 child의 이벤트를 수신합니다.
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatData chatData = dataSnapshot.getValue(ChatData.class);  // chatData를 가져오고
                adapter.addItem(chatData.getUserName(), chatData.getMessage());  // adapter에 추가합니다.
                adapter.notifyDataSetChanged();
                setListViewHeightBasedOnChildren(listView);
                comment.setText("댓글("+adapter.getCount()+")");

            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) { }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

    }

    //길이 초과되는 리스트뷰 표시.
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
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

    //메뉴바 열고 닫기
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

    //ListView 호출
    class ChatAdapter extends BaseAdapter {
        ArrayList<ChatData> items = new ArrayList<ChatData>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(String cntUser, String cntContent){
            ChatData item = new ChatData();
            item.setUserName(cntUser);
            item.setMessage(cntContent);
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean isEnabled(int position) {
            return false;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {

            ChatdataView view = new  ChatdataView (getApplicationContext());
            ChatData item = items.get(position);

            view.setName(item.getComment());

            return view;
        }
    }

}


