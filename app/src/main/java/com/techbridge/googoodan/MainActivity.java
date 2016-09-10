package com.techbridge.googoodan;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listViewGoogoodan;
    String[] googoodan = new String[81];
    ArrayList<String> glist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewGoogoodan = (ListView) findViewById(R.id.listViewGoogoodan);

        for (int i=1; i<=9; i++) {
            for (int j=1; j<=9; j++) {
                googoodan[9 * (i-1) + (j-1)] = i + " * " + j + " = " + (i*j);
                Log.d("debug", (9 * (i-1) + (j-1)) + ": " + googoodan[9 * (i-1) + (j-1)]); ,
            }
        }

        for (int i=1; i<=9; i++) {
            for (int j=1; j<=9; j++) {
                glist.add(i + " * " + j);
            }
        }

        for (String element : glist) {
            Log.d("debug_list", element);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, glist);
        listViewGoogoodan.setAdapter(adapter);


        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("문제");
                builder.setMessage(glist.get(position));

                final EditText answer = new EditText(MainActivity.this);
                builder.setView(answer);

                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String ans = answer.getText().toString();
                        int intAnswer = Integer.parseInt(ans);


                        Toast.makeText(getApplicationContext(), ans, Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        };

        listViewGoogoodan.setOnItemClickListener(listener);
    }
}
