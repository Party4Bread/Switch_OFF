package com.ezo.switchoff;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

public class SingleGame extends Activity {

    Timer mTimer;
    SharedPreferences pref;
    TextView curtimeTxt, hightimeTxt, stageTxt;
    GridLayout gv;
    ArrayList<ImageButton> rests;
    boolean dd=false;
    int[][] SkinList = {
            {R.drawable.kaori_back, R.drawable.kaori_front},
            {R.drawable.unknown_back, R.drawable.bicycle_front},
            //{R.drawable.off_back, R.drawable.on_front},
            {R.drawable.yuuki_back, R.drawable.kaori_front},
           // {R.drawable.Hero_back, R.drawable.Hios_front},
           // {R.drawable.mac_back, R.drawable.mac_front},
            {R.drawable.rem_back, R.drawable.ram_front},
            //{R.drawable.mix_back, R.drawable.exynos_front},
            {R.drawable.yuuki_back, R.drawable.asuna_front},
            {R.drawable.musebee_back, R.drawable.mitsuha_front}
    };

    int[][][] stageinfo = {
            //0
            {
                    {0, 0}
            },
            //tutorial1
            {
                    {0},
                    {1}
            },
            //tutorial2
            {
                    {0},
                    {1},
                    {2}
            },
            //tutorial3
            {
                    {0, 0},
                    {1, 2},
                    {0, 2},
                    {2, 3}
            },
            //1
            {
                    {0, 0},
                    {1, 2},
                    {1, 2},
                    {2, 3},
                    {4, 1}
            },
            //2
            {
                    {0, 0},
                    {1, 2},
                    {2, 1},
                    {2, 3},
                    {3, 4}
            },
            //3
            {
                    {0, 0},
                    {1, 4},
                    {2, 1},
                    {3, 0},
                    {0, 4}
            },
            //4
            {
                    {0, 0, 0},
                    {1, 4, 2},
                    {2, 1, 4},
                    {3, 0, 1},
                    {0, 4, 0}
            },
            //5
            {
                    {0, 0, 0},
                    {1, 2, 3},
                    {1, 2, 4},
                    {3, 0, 0},
                    {2, 4, 0}
            },
            //6
            {
                    {0, 0, 0},
                    {3, 1, 4},
                    {2, 0, 1},
                    {1, 3, 0},
                    {1, 4, 0}
            },
            //7
            {
                    {0, 0, 0},
                    {1, 3, 4},
                    {2, 1, 0},
                    {0, 3, 2},
                    {3, 0, 4}
            },
            //8
            {
                    {0, 0, 0},
                    {1, 2, 3},
                    {1, 2, 0},
                    {0, 3, 4},
                    {2, 4, 0}
            },
            //9
            {
                    {0, 0, 0},
                    {1, 2, 0},
                    {0, 2, 0},
                    {0, 2, 3},
                    {1, 2, 4}
            },
            //10
            {
                    {0, 0},
                    {1, 2},
                    {1, 2},
                    {3, 2},
                    {4, 0},
                    {1, 5}
            },
            //11
            {
                    {0, 0},
                    {1, 2},
                    {2, 4},
                    {3, 5},
                    {4, 3},
                    {5, 0}
            },
            //12
            {
                    {0, 0},
                    {1, 5},
                    {2, 1},
                    {3, 5},
                    {4, 3},
                    {5, 3}
            },
            //13
            {
                    {0, 0},
                    {1, 0},
                    {2, 1},
                    {3, 2},
                    {4, 3},
                    {5, 2}
            },
            //14
            {
                    {0, 0, 0},
                    {1, 2, 3},
                    {2, 3, 4},
                    {3, 4, 5},
                    {4, 5, 1},
                    {5, 1, 2}
            },
            //15
            {
                    {0, 0, 0, 0},
                    {1, 2, 0, 4},
                    {2, 3, 0, 5},
                    {3, 4, 0, 1},
                    {4, 5, 3, 2},
                    {5, 1, 0, 0}
            },
            //16
            {
                    {0, 0, 0},
                    {1, 2, 0},
                    {2, 3, 0},
                    {3, 4, 5},
                    {4, 0, 6},
                    {5, 6, 1},
                    {6, 1, 0}
            },
            //17
            {
                    {0, 0, 0},
                    {1, 3, 4},
                    {2, 1, 5},
                    {3, 1, 5},
                    {4, 1, 3},
                    {5, 3, 4},
                    {6, 1, 4}
            },
            //18
            {
                    {0, 0, 0},
                    {1, 2, 3},
                    {2, 3, 4},
                    {3, 4, 5},
                    {4, 5, 6},
                    {5, 6, 2},
                    {6, 1, 3}
            },
            //19
            {
                    {0, 0, 0},
                    {1, 2, 3},
                    {2, 4, 6},
                    {3, 5, 2},
                    {4, 3, 1},
                    {5, 6, 1},
                    {6, 1, 2}
            },
            //20
            {
                    {0, 0, 0},
                    {1, 3, 4},
                    {2, 6, 3},
                    {3, 5, 2},
                    {4, 2, 6},
                    {5, 1, 2},
                    {6, 3, 4}
            },
            //mid_tutorial
            {
                    {5}, //term(term번 마다 교체
                    {1, 3, 4},
                    {2, 6, 3},
                    {3, 5, 2},
                    {4, 2, 6},
                    {5, 1, 2},
                    {6, 3, 4}
            },
            //21
            {
                    {4},
                    {1, 3, 4},
                    {2, 6, 3},
                    {3, 5, 2},
                    {4, 2, 6},
                    {5, 1, 2},
                    {6, 3, 4}
            },
            //22
            {
                    {5},
                    {1, 4, 5},
                    {2, 5, 1},
                    {3, 2, 5},
                    {4, 3, 1},
                    {5, 6, 4},
                    {6, 5, 3}
            },
            //23
            {
                    {5},
                    {1, 3, 4},
                    {2, 4, 6},
                    {3, 2, 5},
                    {4, 6, 2},
                    {5, 4, 3},
                    {6, 5, 4}
            },
            //24
            {
                    {5},
                    {1, 5, 6},
                    {2, 3, 5},
                    {3, 4, 1},
                    {4, 1, 5},
                    {5, 3, 2},
                    {6, 2, 3}
            },
            //25
            {
                    {3},
                    {1, 3, 4},
                    {2, 4, 6},
                    {3, 7, 2},
                    {4, 5, 2},
                    {5, 6, 7},
                    {6, 4, 2},
                    {7, 2, 5}
            }
    };
    int[][] first_arr = {
            //tutorial1
            {0},
            //t2
            {0, 0},
            //t3
            {0, 0, 0},
            //stage1
            {0, 0, 0, 0, 0},
            //s2
            {0, 1, 0, 1, 0},
            //s3
            {0, 1, 1, 0, 0},
            //s4
            {0, 1, 0, 0, 1},
            //s5
            {0, 1, 0, 1, 0},
            //s6
            {0, 1, 0, 1, 1},
            //s7
            {0, 1, 1, 0, 1},
            //s8
            {0, 1, 0, 1, 0},
            //s9
            {0, 0, 0, 0, 0},
            //s10
            {0, 0, 0, 0, 0, 0},
            //s11
            {0, 0, 0, 0, 0, 0},
            //s12
            {0, 1, 0, 1, 0, 1},
            //s13
            {0, 1, 0, 1, 0, 0},
            //s14
            {0, 1, 1, 1, 0, 0},
            //s15
            {0, 0, 0, 0, 0, 0},
            //s16
            {0, 0, 1, 0, 0, 0, 0},
            //s17
            {0, 0, 0, 1, 0, 1, 0},
            //s18
            {0, 0, 0, 1, 1, 0, 0},
            //s19
            {0, 1, 0, 1, 1, 0, 0},
            //s20
            {0, 0, 1, 1, 0, 1, 0},
            //mid_tutorial
            {0, 0, 1, 0, 0, 0, 1},
            //s21
            {0, 0, 0, 0, 0, 0, 1},
            //s22
            {0, 1, 0, 0, 1, 0, 0},
            //s23
            {0, 0, 1, 0, 0, 0, 0},
            //s24
            {0, 0, 0, 0, 0, 1, 1},
            //s25
            {0, 0, 0, 0, 1, 1, 1}
    };
    Drawable[] curSkin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_game);
        pref = getSharedPreferences("pref", MODE_PRIVATE);
        stageTxt = (TextView) findViewById(R.id.stage);
        //String[] stageinfo={"1-1","1-1~2-2","1-1,2~2-2~3-2,3"};
        curSkin = new Drawable[]{ContextCompat.getDrawable(this, SkinList[getSkin()][0]), ContextCompat.getDrawable(this, SkinList[getSkin()][1])};
        curtimeTxt= (TextView) findViewById(R.id.currtime);
        rests = new ArrayList<ImageButton>();
        gv = (GridLayout) findViewById(R.id.switchLayout);
        makeStage(getStage());
    }

    int getStage() {
        int p = pref.getInt("stage", 0);
        if (p == 0) {
            pref.edit().putInt("stage", 1).commit();
            p = 1;
        }
        return p;
    }

    int getSkin() {
        int p = pref.getInt("skin", 0);
        return p;
    }

    boolean makeStage(int stage) {
        stageTxt.setText(Integer.toString(stage));
        final int[][] crustginfo = stageinfo[stage];
        rests.clear();
        gv.removeAllViews();
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        final long start = System.currentTimeMillis();



        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(
                        new Runnable() {
                            @Override
                            public void run() {
                                long millis = System.currentTimeMillis() - start;
                                String hms = String.format("%02d:%02d:%03d",TimeUnit.MILLISECONDS.toMinutes(millis),
                                        TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1),
                                        millis%TimeUnit.SECONDS.toMillis(1));
                                curtimeTxt.setText(hms);
                            }
                        }
                );
            }
        };
        for (int i = 1; i < crustginfo.length; i++) {

            GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
            lp.setGravity(Gravity.FILL);
            //lp.rowSpec = GridLayout.spec(GridLayout.UNDEFINED,1f);
            //lp.columnSpec=GridLayout.spec (GridLayout.UNDEFINED,1f);
            lp.width = width / 3;
            lp.height = width / 3;
            ImageButton switchbtn = new ImageButton(this);
            switchbtn.setId(View.generateViewId());
            switchbtn.setLayoutParams(lp);
            switchbtn.setBackground(curSkin[first_arr[stage-1][i-1]==0?1:0]);
            final int[] tempArr = crustginfo[i];
            switchbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dd)
                        return;
                    for (final int target : tempArr) {
                        if (target == 0) {
                            continue;
                        }
                        rests.get(target - 1).setBackground(rests.get(target - 1).getBackground() == curSkin[0] ? curSkin[1] : curSkin[0]);
                    }
                    if (checkAnswer()) {
                        int stagee = getStage();
                        pref.edit().putInt("stage", stagee + 1).apply();
                        final int stage = getStage();
                        t.cancel();
                        Toast.makeText(SingleGame.this,"CLEAR!",Toast.LENGTH_SHORT).show();
                        dd=true;
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                SystemClock.sleep(4000); // Sleep 4 seconds
                                // Now change the color back. Needs to be done on the UI thread
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        makeStage(stage);
                                        dd=false;
                                    }
                                });
                            }
                        }).start();
                    }
                }
            });
            rests.add(switchbtn);
            gv.addView(switchbtn);
        }
        t.schedule(tt, 0, 1);
        return true;
    }

    boolean checkAnswer() {
        boolean isAnswer = true;
        for (ImageButton i : rests) {
            isAnswer = i.getBackground() == curSkin[0] ? isAnswer : false;
        }
        return isAnswer;
    }
}
