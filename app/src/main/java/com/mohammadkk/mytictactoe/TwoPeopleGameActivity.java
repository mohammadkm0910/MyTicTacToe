package com.mohammadkk.mytictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.Objects;

public class TwoPeopleGameActivity extends AppCompatActivity {
    private static final int PLAYER_ONE = 1;
    private static final int PLAYER_TWO = 2;
    private static final int EMPTY = 3;
    private static final int NO_WINNER = 0;
    private int winner = NO_WINNER;
    private int[] status = {EMPTY, EMPTY, EMPTY,
            EMPTY, EMPTY, EMPTY,
            EMPTY, EMPTY, EMPTY};
    private int turn = PLAYER_ONE;
    private int[][] winnerPos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    private LinearLayout resultLayout;
    private AppCompatImageView imageView0,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8;
    private MediaPlayer clickSoundO,clickSoundX;
    private TextInputEditText edtPlayerNameOne,edtPlayerNameTwo;
    private Button btnRecreate;
    private TextView namePlayerOne,namePlayerTwo,scorePlayerOne,scorePlayerTwo,textMsg;
    private String PLAYER_ONE_NAME = "بازیکن شماره ۱";
    private String PLAYER_TWO_NAME = "بازیکن شماره ۲";
    private int SCORE_PLAYER_ONE = 0;
    private int SCORE_PLAYER_TWO = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_people_game);
        resultLayout = findViewById(R.id.resultLayout);
        imageView0 = findViewById(R.id.ImageView0);
        imageView1 = findViewById(R.id.ImageView1);
        imageView2 = findViewById(R.id.ImageView2);
        imageView3 = findViewById(R.id.ImageView3);
        imageView4 = findViewById(R.id.ImageView4);
        imageView5 = findViewById(R.id.ImageView5);
        imageView6 = findViewById(R.id.ImageView6);
        imageView7 = findViewById(R.id.ImageView7);
        imageView8 = findViewById(R.id.ImageView8);
        namePlayerOne = findViewById(R.id.namePlayerOne);
        namePlayerTwo = findViewById(R.id.namePlayerTwo);
        scorePlayerOne = findViewById(R.id.scorePlayerOne);
        scorePlayerTwo = findViewById(R.id.scorePlayerTwo);
        textMsg = findViewById(R.id.textMsg);
        btnRecreate = findViewById(R.id.btnRecreate);
        clickSoundO = MediaPlayer.create(this,R.raw.sound_o);
        clickSoundX = MediaPlayer.create(this,R.raw.sound_x);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_player_name,null);
        builder.setView(dialogView);
        edtPlayerNameOne = dialogView.findViewById(R.id.edtPlayerNameOne);
        edtPlayerNameTwo = dialogView.findViewById(R.id.edtPlayerNameTwo);
        Button btnGameStart = dialogView.findViewById(R.id.btnGameStart);
        final AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
        btnGameStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PLAYER_ONE_NAME = Objects.requireNonNull(edtPlayerNameOne.getText()).toString().trim();
                PLAYER_TWO_NAME = Objects.requireNonNull(edtPlayerNameTwo.getText()).toString().trim();
                if (!PLAYER_ONE_NAME.equals("") && !PLAYER_TWO_NAME.equals("") &&
                        PLAYER_ONE_NAME.length() < 31 &&  PLAYER_TWO_NAME.length() < 31){
                    namePlayerOne.setText(PLAYER_ONE_NAME);
                    namePlayerTwo.setText(PLAYER_TWO_NAME);
                    dialog.dismiss();
                }
            }
        });

    }
    public void game(View view) {
        int tags = Integer.parseInt(view.getTag().toString());
        if (winner != NO_WINNER || status[tags] != EMPTY){
            return;
        }
        AppCompatImageView imageView = (AppCompatImageView) view;
        Drawable animO = AnimatedVectorDrawableCompat.create(this,R.drawable.anim_o);
        Drawable animX = AnimatedVectorDrawableCompat.create(this,R.drawable.anim_x);

        if (turn == PLAYER_ONE){
            imageView.setImageDrawable(animO);
            final Animatable animatable = (Animatable) imageView.getDrawable();
            animatable.start();
            status[tags] = PLAYER_ONE;
            turn = PLAYER_TWO;
            namePlayerOne.setTextColor(ContextCompat.getColor(this,R.color.indigo300));
            namePlayerTwo.setTextColor(ContextCompat.getColor(this,R.color.redA400));
            clickSoundO.start();
        } else {
            imageView.setImageDrawable(animX);
            final Animatable animatable = (Animatable) imageView.getDrawable();
            animatable.start();
            status[tags] = PLAYER_TWO;
            turn = PLAYER_ONE;
            namePlayerOne.setTextColor(ContextCompat.getColor(this,R.color.redA400));
            namePlayerTwo.setTextColor(ContextCompat.getColor(this,R.color.indigo300));
            clickSoundX.start();
        }
        result();
    }
    @SuppressLint("SetTextI18n")
    private void result() {
        winner = checkWinner();
        if (winner != NO_WINNER || full()) {
            String resultGame = "";
            if (winner == NO_WINNER) {
                resultGame = "مساوی شدید";
            } else if (winner == PLAYER_ONE){
                resultGame = PLAYER_ONE_NAME + " برنده شد";
                SCORE_PLAYER_ONE++;
                scorePlayerOne.setText(Integer.toString(SCORE_PLAYER_ONE));
            } else if (winner == PLAYER_TWO){
                resultGame = PLAYER_TWO_NAME + " برنده شد";
                SCORE_PLAYER_TWO++;
                scorePlayerTwo.setText(Integer.toString(SCORE_PLAYER_TWO));
            }
            showMsg(resultGame);
        }
    }
    private int checkWinner(){
        for (int[] pos : winnerPos) {
            if (status[pos[0]] == status[pos[1]] &&
                status[pos[1]] == status[pos[2]] &&
                status[pos[0]] != EMPTY){
                return status[pos[0]];
            }
        }
        return NO_WINNER;
    }
    private boolean full(){
        for (int value : status) {
            if (value == EMPTY) {
                return false;
            }
        }
        return true;
    }
    private void showMsg(String msg){
        textMsg.setText(msg);
        resultLayout.setVisibility(View.VISIBLE);
        btnRecreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }
    private void reset() {
        namePlayerOne.setTextColor(ContextCompat.getColor(this,R.color.indigo300));
        namePlayerTwo.setTextColor(ContextCompat.getColor(this,R.color.indigo300));
        resultLayout.setVisibility(View.GONE);
        turn = PLAYER_ONE;
        winner = NO_WINNER;
        Arrays.fill(status, EMPTY);
        imageView0.setImageResource(0);
        imageView1.setImageResource(0);
        imageView2.setImageResource(0);
        imageView3.setImageResource(0);
        imageView4.setImageResource(0);
        imageView5.setImageResource(0);
        imageView6.setImageResource(0);
        imageView7.setImageResource(0);
        imageView8.setImageResource(0);
    }
}