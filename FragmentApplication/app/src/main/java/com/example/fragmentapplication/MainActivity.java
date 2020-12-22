package com.example.fragmentapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private BlankFragment blankFragment = new BlankFragment();
    private BlankFragment2 blankFragment2 = new BlankFragment2();
    private BlankFragment3 blankFragment3 = new BlankFragment3();
    private TabLayout tabLayout;
    int now = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Step01-FragmentTransaction加入三個Fragment，暫時先隱藏另外兩張Fragment，只顯示第一頁:
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //加入fragment頁面
        fragmentTransaction.add(R.id.fragment_t, blankFragment, "Menu");
        fragmentTransaction.add(R.id.fragment_t, blankFragment2, "Computer");
        fragmentTransaction.add(R.id.fragment_t, blankFragment3, "tools");

        //將暫不顯示的頁面隱藏起來
        fragmentTransaction.hide(blankFragment2);//隱藏
        fragmentTransaction.hide(blankFragment3);//隱藏

        fragmentTransaction.commit();

        //監聽事件
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            //寫一個方法，tab.getPosition是按下哪個按鈕，將之傳入fragmentChange方法內:
                fragmentChange(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void fragmentChange(int position){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //先判斷目前的Fragment，並將之隱藏(now)我預設為0，因為tab.Position的0為第一頁):
        //在點擊上方選項前頁面都隱藏
        //使用switch case隱藏或顯示頁面
        switch (now){
            case 0:
                fragmentTransaction.hide(blankFragment);
                break;
            case 1:
                fragmentTransaction.hide(blankFragment2);
                break;
            case 2:
                fragmentTransaction.hide(blankFragment3);
                break;
        }
        //點擊時顯示該頁面
        switch (position){
            case 0:
                fragmentTransaction.show(blankFragment);
                Button button = (Button)findViewById(R.id.btn2);
                Button button1 = (Button)findViewById(R.id.btn1);
                final TextView textView = (TextView)findViewById(R.id.textView);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.setText("Hello World");
                    }
                });
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.setText("");
                    }
                });
                break;

            case 1:
                fragmentTransaction.show(blankFragment2);
                final TextView textView1 = (TextView)findViewById(R.id.textView2);
                final EditText editText = (EditText)findViewById(R.id.edit);
                Button button3 = (Button)findViewById(R.id.btn);
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String str = editText.getText().toString();
                        textView1.setText(str);
                    }
                });
                break;

            case 2:
                fragmentTransaction.show(blankFragment3);
                Button button2 = (Button)findViewById(R.id.btn3);
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ImageView imageView = (ImageView)findViewById(R.id.imageView);
                        imageView.setImageResource(R.mipmap.tool);
                    }
                });

                break;
        }
        //更新目前的fragment
        fragmentTransaction.commit();
        now = position;
    }
}