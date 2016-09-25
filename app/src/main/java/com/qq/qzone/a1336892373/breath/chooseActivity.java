package com.qq.qzone.a1336892373.breath;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.qq.qzone.a1336892373.breath.tools.myColor;

public class chooseActivity extends Activity {

    private String[] data = new String[]{ getResources().getString(R.string.chen),
            getResources().getString(R.string.lan),
            getResources().getString(R.string.fen),
            getResources().getString(R.string.zi),
            getResources().getString(R.string.huang),
            getResources().getString(R.string.lv)        };
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        huanse();

        mListView = (ListView) findViewById(R.id.yanse_listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(chooseActivity.this, android.R.layout.simple_list_item_1, data);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0 :
                        shedin(0);
                        break;
                    case 1 :
                        shedin(1);
                        break;
                    case 2 :
                        shedin(2);
                        break;
                    case 3 :
                        shedin(3);
                        break;
                    case 4 :
                        shedin(4);
                        break;
                    case 5 :
                        shedin(5);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void shedin(int flag){
        SharedPreferences mySharedPreferences= getSharedPreferences("mydata", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        int yanse = myColor.GREEN;
        switch (flag){
            case myColor.CHEN :
                yanse = myColor.CHEN;
                break;
            case myColor.BLUE :
                yanse = myColor.BLUE;
                break;
            case myColor.FEN :
                yanse = myColor.FEN;
                break;
            case myColor.ZI :
                yanse = myColor.ZI;
                break;
            case myColor.YELLOW :
                yanse = myColor.YELLOW;
                break;
            case myColor.GREEN:
                yanse = myColor.GREEN;
                break;
            default:
                break;
        }
        editor.putInt("yanse", yanse);
        editor.commit();
        chongqi();
    }

    private void huanse(){
        LinearLayout layout = (LinearLayout) findViewById(R.id.choose_layout);
        myColor color = new myColor(chooseActivity.this);
        layout.setBackgroundColor(getResources().getColor(color.getQIAN()));
    }

    private void chongqi(){
        startActivity( new Intent(chooseActivity.this, chooseActivity.class));
        finish();
    }

}
