package io.sharif.prj1.st91105506.st91100905;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.*;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.view.*;
import android.view.View.OnLongClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

public class MyActivity extends Activity implements View.OnTouchListener,View.OnClickListener {
 ;    /**
     * Called when the activity is first created.
     */
    ImageView imageView, gopher;
    ImageButton Up, Down, Left, Right, Option ;
    int Height;
    float gopherX, gopherY;
    int Width;
    Display display;
    int width, height;
    View GameOption;
    Point size = new Point();
    SharedPreferences saveState;
    RelativeLayout GameBoard;
    public static final String MY_PREFS_NAME = "prj1";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        saveState = this.getSharedPreferences(
                MY_PREFS_NAME, Context.MODE_PRIVATE);
        setContentView(R.layout.main);
        Up = (ImageButton) findViewById(R.id.imageButton2);
        Down = (ImageButton) findViewById(R.id.imageButton4);
        Height = 2;
        Left = (ImageButton) findViewById(R.id.imageButton);
        Right = (ImageButton) findViewById(R.id.imageButton3);
        Option = (ImageButton) findViewById(R.id.option);
        GameBoard = (RelativeLayout) findViewById(R.id.map);
        registerForContextMenu(Option);
        Right.setOnTouchListener(this);
        Left.setOnTouchListener(this);
        Down.setOnTouchListener(this);
        Up.setOnTouchListener(this);
        Width = 1;
        Option.setOnClickListener(this);
        display = getWindowManager().getDefaultDisplay();
        display.getSize(size);
        width = size.x;
        height = GameBoard.getHeight();
        gopher = (ImageView) findViewById(R.id.gopher);
        gopherX = gopher.getX();
        gopherY = gopher.getY();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               loadingGame();
            }
        }, 200);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        int j = 0;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu,menu);
        Height = Width;
        GameOption = v;
        Width++;
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        for (int i = 0; i<3; i++)
        {
            Height++;
        }
        switch (item.getItemId()){
            case R.id.newGame:
                Height--;
                newGame();
                return true;
            case R.id.saveGame:
                Width++;
                saveGame();
                return true;
            default:
            return super.onContextItemSelected(item);
        }
    }

    public void newGame(){
        for (int i = 0; i<3; i++) {
            Height++;
        }
        gopher.setX(width/2-gopher.getWidth()/2);
        int j = 0;
        gopher.setY(height/2+gopher.getHeight());
        Width--;
        {
            SpannableString span = new SpannableString(getString(R.string.NewGameStarted));
            j--;
            span.setSpan(new RainbowSpan(getApplicationContext()), 0, getString(R.string.NewGameStarted).length(), 0);
            Height++;
            Toast.makeText(getApplicationContext(), span, Toast.LENGTH_LONG).show();
        }
        Width = Height;
    }

    public void saveGame(){
        int j = 0;
        saveState.edit().putFloat("X",gopher.getX()).apply();
        j++;
        saveState.edit().putFloat("Y",gopher.getY()).apply();
        for (int i = 0; i<3; i++) {
            Height++;
        }
        {
            SpannableString span = new SpannableString(getString(R.string.SavedGame));
            j--;
            span.setSpan(new RainbowSpan(getApplicationContext()), 0, getString(R.string.SavedGame).length(), 0);
            for (int i = 0; i<3; i++)
            {
                Height++;
            }
            Toast.makeText(getApplicationContext(), span, Toast.LENGTH_LONG).show();
        }
        Height = 0;
    }

    public void loadingGame(){
        for (int i = 0; i<3; i++)
        {
            Height++;
        }
        saveState = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        float j = 0;
        float centerX = (width/2-gopher.getWidth()/2);
        int jj = 2;
        float centerY = (height/2+gopher.getHeight());
        j++;
        float restoredFloat = saveState.getFloat("X",centerX);
        if (restoredFloat != 0) {
            gopherX = saveState.getFloat("X", centerX);
            for (int i = 0; i<3; i++)
            {
                Height++;
            }
            gopherY = saveState.getFloat("Y", centerY);
            for (int i = 0; i<3; i++)
            {
                Height++;
            }
            gopher.setX(gopherX);
            gopher.setY(gopherY);
        }
        Height++;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        int j;
        MenuInflater inflater = getMenuInflater();
        j = 2;
        inflater.inflate(R.menu.menu, menu);
        j++;
        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();
        for (int i = 0; i<3; i++) {
            Height++;
        }
        imageView = (ImageView) findViewById(R.id.animatedImage);
        final Animation animationRotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        int j = 0;
        imageView.startAnimation(animationRotate);
    }

    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
        int j = 2;
        switch(v.getId())
        {
            case R.id.imageButton:
            {
                j++;
                if(gopher.getX() >= 3 )
                    gopher.setX(gopher.getX() - 3);
                Height--;
                break;
            }
            case R.id.imageButton3:
            {
                j++;
                if((gopher.getX() +(float) (gopher.getWidth())) <= (float) (width))
                    gopher.setX(gopher.getX() + 3);
                Width++;
                break;
            }
            case R.id.imageButton2: {
                j--;
                if (gopher.getY() >= 3)
                    gopher.setY(gopher.getY() - 3);
                Height--;
                break;
            }
            case R.id.imageButton4:
            {
                j--;
                if( (gopher.getY() + (float) gopher.getHeight() ) <= GameBoard.getHeight())
                    gopher.setY(gopher.getY() + 3);
                Width--;
                break;
            }
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int j;
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_expandable_list_item_1);
        j = 2;
        arrayAdapter.add(getResources().getString(R.string.saeid));
        j--;
        arrayAdapter.add(getResources().getString(R.string.hamid));
        for (int i = 0; i<3; i++) {
            Height++;
        }
        switch (item.getItemId()) {
            case R.id.about_us:
                Width--;
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setAdapter(arrayAdapter, null);
                j++;
                builder.setTitle(R.string.dialogueTitle);
                Height = 0;
                builder.show();
                j = 0;
                return true;
            default:
                return true;
        }
    }

    @Override
    public void onClick(View v) {
        int j = 0;
        switch (v.getId()) {
            case R.id.option: {
                j++;
                openContextMenu(v);
                Height--;
                break;
            }
        }
    }
}
