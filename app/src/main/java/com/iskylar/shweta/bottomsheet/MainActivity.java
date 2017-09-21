package com.iskylar.shweta.bottomsheet;

import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    // BottomSheetBehavior variable
    private BottomSheetBehavior bottomSheetBehavior;

    // TextView variable
    private TextView bottomSheetHeading;


    // Button variables
    private Button expandBottomSheetButton;
    private Button collapseBottomSheetButton;
    private Button hideBottomSheetButton;
    private Button showBottomSheetDialogButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();


    }
    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottomSheetLayout));
        bottomSheetHeading = (TextView) findViewById(R.id.bottomSheetHeading);
        expandBottomSheetButton = (Button) findViewById(R.id.expand_bottom_sheet_button);
        collapseBottomSheetButton = (Button) findViewById(R.id.collapse_bottom_sheet_button);
      //  hideBottomSheetButton = (Button) findViewById(R.id.hide_bottom_sheet_button);
        showBottomSheetDialogButton = (Button) findViewById(R.id.show_bottom_sheet_dialog_button);


    }



    private void initListeners() {
        // register the listener for button click
        expandBottomSheetButton.setOnClickListener(this);
        collapseBottomSheetButton.setOnClickListener(this);
       // hideBottomSheetButton.setOnClickListener(this);
        showBottomSheetDialogButton.setOnClickListener(this);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                bottomSheetBehavior.setHideable(false);


                bottomSheetHeading.setText("hello friends");


                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:

                        Log.e("Bottom Sheet Behaviour", "STATE_COLLAPSED");

                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.e("Bottom Sheet Behaviour", "STATE_DRAGGING");

                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.e("Bottom Sheet Behaviour", "STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.e("Bottom Sheet Behaviour", "STATE_HIDDEN");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.e("Bottom Sheet Behaviour", "STATE_SETTLING");
                        break;

                }

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }

        });



    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.collapse_bottom_sheet_button:
                // Collapsing the bottom sheet
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case R.id.expand_bottom_sheet_button:
                // Expanding the bottom sheet
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
           /* case R.id.hide_bottom_sheet_button:
                // Hiding the bottom sheet
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;*/
            case R.id.show_bottom_sheet_dialog_button:
                new CustomBottomSheetDialogFragment().show(getSupportFragmentManager(), "Dialog");
                break;

        }
    }


    //collpase when click outside

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_DOWN){
            if (bottomSheetBehavior.getState()== BottomSheetBehavior.STATE_EXPANDED){
                Rect outRect =new Rect();
            // bottomSheet.getGlobalVisibleRect(outRect);

            if(!outRect.contains((int)ev.getRawX(), (int)ev.getRawY()))
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
        }
        return super.dispatchTouchEvent(ev);
    }






}

