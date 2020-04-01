package com.example.imagesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button button;
    //pointer = 0, the pointer say where I am.
    private int pointer;
    //Array that contains all the images (7 in total)
    private int arrayAux[] = {R.drawable.empty_dice,R.drawable.dice_1,R.drawable.dice_2,R.drawable.dice_3,R.drawable.dice_4,R.drawable.dice_5,R.drawable.dice_6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.buttom);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //++pointer return 1
                imageView.setImageResource(arrayAux[(++pointer)%7]);
            }
        });
    }
}
