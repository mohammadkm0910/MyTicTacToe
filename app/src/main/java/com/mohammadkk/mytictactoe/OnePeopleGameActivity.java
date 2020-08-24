package com.mohammadkk.mytictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.annotation.SuppressLint;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class OnePeopleGameActivity extends AppCompatActivity {
    private static final int PLAYER = 1;
    private static final int PLAYER_BOT = 2;
    private static final int EMPTY = 3;
    private static final int NO_WINNER = 0;
    private int winner = NO_WINNER;
    private int[] status = {EMPTY, EMPTY, EMPTY,
            EMPTY, EMPTY, EMPTY,
            EMPTY, EMPTY, EMPTY};
    private int[][] winnerPos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    private LinearLayout resultLayout;
    private AppCompatImageView imageView0, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8;
    private MediaPlayer clickSoundO, clickSoundX;
    private EditText edtPlayerNameOne;
    private Button btnRecreate;
    private TextView namePlayer, namePlayerTwo, scorePlayer, scoreRobotPlayer, textMsg;
    private String PLAYER_NAME = "بازیکن شماره ۱";
    private int SCORE_PLAYER_ONE = 0;
    private int SCORE_BOT_PLAYER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_people_game);
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
        namePlayer = findViewById(R.id.namePlayer);
        namePlayerTwo = findViewById(R.id.namePlayerTwo);
        scorePlayer = findViewById(R.id.scorePlayer);
        scoreRobotPlayer = findViewById(R.id.scoreRobotPlayer);
        textMsg = findViewById(R.id.textMsg);
        btnRecreate = findViewById(R.id.btnRecreate);
        clickSoundO = MediaPlayer.create(this, R.raw.sound_o);
        clickSoundX = MediaPlayer.create(this, R.raw.sound_x);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.dialog_player_one_name, null);
        builder.setView(dialogView);
        edtPlayerNameOne = dialogView.findViewById(R.id.edtPlayerNameOne);
        Button btnGameStart = dialogView.findViewById(R.id.btnGameStart);
        final AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
        btnGameStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PLAYER_NAME = edtPlayerNameOne.getText().toString();
                namePlayer.setText(PLAYER_NAME);
                dialog.dismiss();
                random();
            }
        });
    }

    public void game(View view) {
        int tag = Integer.parseInt(view.getTag().toString());
        if (winner != NO_WINNER || status[tag] != EMPTY){
            return;
        }
        AppCompatImageView imageView = (AppCompatImageView) view;
        animationOView(imageView);
        status[tag] = PLAYER;
        confrontation();
        result();
    }
    //معنی مقابله
    private void confrontation() {
        if (status[0] == PLAYER && status[1] == PLAYER && status[2] == EMPTY) {
            status[2] = PLAYER_BOT;
            animationXView(imageView2);
            clickSoundX.start();
        } else if (status[1] == PLAYER && status[2] == PLAYER && status[0] == EMPTY) {
            status[0] = PLAYER_BOT;
            animationXView(imageView0);
            clickSoundX.start();
        } else if (status[0] == PLAYER && status[2] == PLAYER && status[1] == EMPTY) {
            status[1] = PLAYER_BOT;
            animationXView(imageView1);
            clickSoundX.start();
        } else if (status[3] == PLAYER && status[4] == PLAYER && status[5] == EMPTY) {
            status[5] = PLAYER_BOT;
            animationXView(imageView5);
            clickSoundX.start();
        } else if (status[4] == PLAYER && status[5] == PLAYER && status[3] == EMPTY) {
            status[3] = PLAYER_BOT;
            animationXView(imageView3);
            clickSoundX.start();
        } else if (status[3] == PLAYER && status[5] == PLAYER && status[4] == EMPTY) {
            status[4] = PLAYER_BOT;
            animationXView(imageView4);
            clickSoundX.start();
        } else if (status[6] == PLAYER && status[7] == PLAYER && status[8] == EMPTY) {
            status[8] = PLAYER_BOT;
            animationXView(imageView8);
            clickSoundX.start();
        } else if (status[7] == PLAYER && status[8] == PLAYER && status[6] == EMPTY) {
            status[6] = PLAYER_BOT;
            animationXView(imageView6);
            clickSoundX.start();
        } else if (status[6] == PLAYER && status[8] == PLAYER && status[7] == EMPTY) {
            status[7] = PLAYER_BOT;
            animationXView(imageView7);
            clickSoundX.start();
        } else if (status[0] == PLAYER && status[3] == PLAYER && status[6] == EMPTY) {
            status[6] = PLAYER_BOT;
            animationXView(imageView6);
            clickSoundX.start();
        } else if (status[3] == PLAYER && status[6] == PLAYER && status[0] == EMPTY) {
            status[0] = PLAYER_BOT;
            animationXView(imageView0);
            clickSoundX.start();
        } else if (status[0] == PLAYER && status[6] == PLAYER && status[3] == EMPTY) {
            status[3] = PLAYER_BOT;
            animationXView(imageView3);
            clickSoundX.start();
        } else if (status[1] == PLAYER && status[4] == PLAYER && status[7] == EMPTY) {
            status[7] = PLAYER_BOT;
            animationXView(imageView7);
            clickSoundX.start();
        } else if (status[4] == PLAYER && status[7] == PLAYER && status[1] == EMPTY) {
            status[1] = PLAYER_BOT;
            animationXView(imageView1);
            clickSoundX.start();
        } else if (status[1] == PLAYER && status[7] == PLAYER && status[4] == EMPTY) {
            status[4] = PLAYER_BOT;
            animationXView(imageView4);
            clickSoundX.start();
        } else if (status[2] == PLAYER && status[5] == PLAYER && status[8] == EMPTY) {
            status[8] = PLAYER_BOT;
            animationXView(imageView8);
            clickSoundX.start();
        } else if (status[5] == PLAYER && status[8] == PLAYER && status[2] == EMPTY) {
            status[2] = PLAYER_BOT;
            animationXView(imageView2);
            clickSoundX.start();
        } else if (status[2] == PLAYER && status[8] == PLAYER && status[5] == EMPTY) {
            status[5] = PLAYER_BOT;
            animationXView(imageView5);
            clickSoundX.start();
        } else if (status[0] == PLAYER && status[4] == PLAYER && status[8] == EMPTY) {
            status[8] = PLAYER_BOT;
            animationXView(imageView8);
            clickSoundX.start();
        } else if (status[4] == PLAYER && status[8] == PLAYER && status[0] == EMPTY) {
            status[0] = PLAYER_BOT;
            animationXView(imageView0);
            clickSoundX.start();
        } else if (status[0] == PLAYER && status[8] == PLAYER && status[4] == EMPTY) {
            status[4] = PLAYER_BOT;
            animationXView(imageView4);
            clickSoundX.start();
        } else if (status[2] == PLAYER && status[4] == PLAYER && status[6] == EMPTY) {
            status[6] = PLAYER_BOT;
            animationXView(imageView6);
            clickSoundX.start();
        } else if (status[4] == PLAYER && status[6] == PLAYER && status[2] == EMPTY) {
            status[2] = PLAYER_BOT;
            animationXView(imageView2);
            clickSoundX.start();
        } else if (status[2] == PLAYER && status[6] == PLAYER && status[4] == EMPTY) {
            status[4] = PLAYER_BOT;
            animationXView(imageView4);
            clickSoundX.start();
        } else {
            move();
        }
    }
    private void move() {
        if (status[0]== PLAYER_BOT && status[1]== EMPTY){
            status[1] = PLAYER_BOT;
            animationXView(imageView1);
        }else if (status[0]== PLAYER_BOT && status[3]== EMPTY){
            status[3] = PLAYER_BOT;
            animationXView(imageView3);
        }else if (status[0]== PLAYER_BOT && status[4]== EMPTY){
            status[4] = PLAYER_BOT;
            animationXView(imageView4);
        }else if (status[1]== PLAYER_BOT && status[0]== EMPTY){
            status[0] = PLAYER_BOT;
            animationXView(imageView0);
        }else if (status[1]== PLAYER_BOT && status[2]== EMPTY){
            status[2] = PLAYER_BOT;
            animationXView(imageView2);
        }else if (status[1]== PLAYER_BOT && status[4]== EMPTY){
            status[4] = PLAYER_BOT;
            animationXView(imageView4);
        }else if (status[2]== PLAYER_BOT && status[1]== EMPTY){
            status[1] = PLAYER_BOT;
            animationXView(imageView1);
        }else if (status[2]== PLAYER_BOT && status[4]== EMPTY){
            status[4] = PLAYER_BOT;
            animationXView(imageView4);
        }else if (status[2]== PLAYER_BOT && status[5]== EMPTY){
            status[5] = PLAYER_BOT;
            animationXView(imageView5);
        }else if (status[3]== PLAYER_BOT && status[0]== EMPTY){
            status[0] = PLAYER_BOT;
            animationXView(imageView0);
        }else if (status[3]== PLAYER_BOT && status[4]== EMPTY){
            status[4] = PLAYER_BOT;
            animationXView(imageView4);
        }else if (status[3]== PLAYER_BOT && status[6]== EMPTY){
            status[6] = PLAYER_BOT;
            animationXView(imageView6);
        } else if (status[4]== PLAYER_BOT && status[3]== EMPTY){
            status[3] = PLAYER_BOT;
            animationXView(imageView3);
        }else if (status[4]== PLAYER_BOT && status[1]== EMPTY){
            status[1] = PLAYER_BOT;
            animationXView(imageView1);
        }else if (status[4]== PLAYER_BOT && status[5]== EMPTY) {
            status[5] = PLAYER_BOT;
            animationXView(imageView5);
        }else if (status[4]== PLAYER_BOT && status[7]== EMPTY) {
            status[7] = PLAYER_BOT;
            animationXView(imageView7);
        }else if (status[5]== PLAYER_BOT && status[2]== EMPTY) {
            status[2] = PLAYER_BOT;
            animationXView(imageView2);
        }else if (status[5]== PLAYER_BOT && status[4]== EMPTY) {
            status[4] = PLAYER_BOT;
            animationXView(imageView4);
        }else if (status[5]== PLAYER_BOT && status[8]== EMPTY) {
            status[8] = PLAYER_BOT;
            animationXView(imageView8);
        }else if (status[6]== PLAYER_BOT && status[3]== EMPTY) {
            status[3] = PLAYER_BOT;
            animationXView(imageView3);
        }else if (status[6]== PLAYER_BOT && status[7]== EMPTY) {
            status[7] = PLAYER_BOT;
            animationXView(imageView7);
        }else if (status[6]== PLAYER_BOT && status[4]== EMPTY){
            status[4] = PLAYER_BOT;
            animationXView(imageView4);
        }else if (status[7]== PLAYER_BOT && status[6]== EMPTY) {
            status[6] = PLAYER_BOT;
            animationXView(imageView6);
        }else if (status[7]== PLAYER_BOT && status[4]== EMPTY) {
            status[4] = PLAYER_BOT;
            animationXView(imageView4);
        }else if (status[7]== PLAYER_BOT && status[8]== EMPTY) {
            status[8] = PLAYER_BOT;
            animationXView(imageView8);
        }else if (status[8]== PLAYER_BOT && status[5]== EMPTY) {
            status[5] = PLAYER_BOT;
            animationXView(imageView5);
        }else if (status[8]== PLAYER_BOT && status[7]== EMPTY) {
            status[7] = PLAYER_BOT;
            animationXView(imageView7);
        }else if (status[8]== PLAYER_BOT && status[4]== EMPTY){
            status[4] = PLAYER_BOT;
            animationXView(imageView4);
        }
    }

    @SuppressLint("SetTextI18n")
    private void result() {
        winner = checkWinner();
        if (winner != NO_WINNER || full()) {
            String resultGame = "";
            if (winner == NO_WINNER) {
                resultGame = "مساوی شدید";
            } else if (winner == PLAYER) {
                resultGame = PLAYER_NAME + " برنده شد";
                SCORE_PLAYER_ONE++;
                scorePlayer.setText(Integer.toString(SCORE_PLAYER_ONE));
            } else if (winner == PLAYER_BOT) {
                resultGame = "ربات برنده شد";
                SCORE_BOT_PLAYER++;
                scoreRobotPlayer.setText(Integer.toString(SCORE_BOT_PLAYER));
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
                recreate();
            }
        });
    }
    private void random() {
        Random random = new Random();
        int num = random.nextInt(8);
        status[num] = PLAYER_BOT;
        clickSoundX.start();
        switch (num) {
            case 0:
                animationXView(imageView0);
                break;
            case 1:
                animationXView(imageView1);
                break;
            case 2:
                animationXView(imageView2);
                break;
            case 3:
                animationXView(imageView3);
                break;
            case 4:
                animationXView(imageView4);
                break;
            case 5:
                animationXView(imageView5);
                break;
            case 6:
                animationXView(imageView6);
                break;
            case 7:
                animationXView(imageView7);
                break;
            case 8:
                animationXView(imageView8);
                break;
        }

    }
    private void animationXView(AppCompatImageView imageView){
        Drawable animX = AnimatedVectorDrawableCompat.create(this,R.drawable.anim_x);
        imageView.setImageDrawable(animX);
        final Animatable animatable = (Animatable) imageView.getDrawable();
        animatable.start();
    }
    private void animationOView(AppCompatImageView imageView){
        Drawable animX = AnimatedVectorDrawableCompat.create(this,R.drawable.anim_o);
        imageView.setImageDrawable(animX);
        final Animatable animatable = (Animatable) imageView.getDrawable();
        animatable.start();
    }
}

