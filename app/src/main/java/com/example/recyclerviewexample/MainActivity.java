package com.example.recyclerviewexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button buttonInsert;
    private Button buttonRemove;
    private EditText rem_Position,Add_contact,Add_Number;
    private EditText editTextRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createExampleList();
        buildRecyclerView();
        setButtons();
    }

    public void insertItem(int position,String add_contact,String add_number) {
        mExampleList.add(position, new ExampleItem(R.drawable.ic_user, add_contact, "+91"+add_number));
        mAdapter.notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void changeItem(int position, String text) {
        mExampleList.get(position).changeText1(text);
        mAdapter.notifyItemChanged(position);
    }

    public void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.ic_man, "Manish", "+919039250820"));
        mExampleList.add(new ExampleItem(R.drawable.ic_user, "shiva", "+917587100227"));
        mExampleList.add(new ExampleItem(R.drawable.ic_woman, "papa", "+919425557395"));
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeItem(position, "Clicked");
            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }

    public void setButtons() {
        buttonInsert = findViewById(R.id.button_insert);
        buttonRemove = findViewById(R.id.button_remove);
        editTextRemove = findViewById(R.id.edittext_remove);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                // Get the layout inflater
                Context context=getApplicationContext();

                LayoutInflater inflater =getLayoutInflater();
                View view=inflater.inflate(R.layout.dialogbox_activity,null);

                rem_Position=(EditText)view.findViewById(R.id.Position);
                Add_contact=(EditText)view.findViewById(R.id.Add_Cname);
                Add_Number=(EditText)view.findViewById(R.id.Add_Pnum);

                builder.setTitle("Add Contact");

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(view)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which)
                                    {
                                        String add_contact=Add_contact.getText().toString();
                                        String add_number=Add_Number.getText().toString();
                                        int pos=Integer.parseInt(rem_Position.getText().toString());
                                        insertItem(pos,add_contact,add_number);
                                    }
                                });

                // Set the Negative button with No name
                // OnClickListener method is use
                // of DialogInterface interface.
                builder
                        .setNegativeButton(
                                "Cancel",
                                new DialogInterface
                                        .OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which)
                                    {

                                        // If user click no
                                        // then dialog box is canceled.
                                        dialog.cancel();
                                    }
                                });

                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();

                // Show the Alert Dialog box
                alertDialog.show();

            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextRemove.getText().toString());
                removeItem(position);
            }
        });
    }
}

