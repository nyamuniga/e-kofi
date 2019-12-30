package co.myechelon.trial;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.Random;

public class colorActivity extends AppCompatActivity {
private RelativeLayout r;
    private ProgressBar bar;
    private Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
       r= (RelativeLayout) findViewById(R.id.color);
        bar= (ProgressBar)findViewById(R.id.Bar);
        b=(Button) findViewById(R.id.butt);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getColor();
            }
        });

    }
    public void getColor(){
        Random random = new Random();
        int[] res= {R.drawable.circle, R.drawable.circle2,R.drawable.cicle3,R.drawable.circle4,R.drawable.circle5,R.drawable.cicle6,R.drawable.circle7,R.drawable.circle8};

        int rnd=random.nextInt(7);

        r.setBackgroundResource(res[rnd]);
          if(rnd==0){

              bar.getProgressDrawable().setColorFilter(Color.parseColor("#ffd014"), PorterDuff.Mode.SRC_IN);

          }
       else if(rnd==1){

            bar.getProgressDrawable().setColorFilter(Color.parseColor("#3F51B5"), PorterDuff.Mode.SRC_IN);

        }
       else if(rnd==2){

            bar.getProgressDrawable().setColorFilter(Color.parseColor("#00dd67"), PorterDuff.Mode.SRC_IN);

        }
       else if(rnd==3){

            bar.getProgressDrawable().setColorFilter(Color.parseColor("#42c2f4"), PorterDuff.Mode.SRC_IN);

        }
       else if(rnd==4){

            bar.getProgressDrawable().setColorFilter(Color.parseColor("#2d1117"), PorterDuff.Mode.SRC_IN);

        }
       else if(rnd==5){

            bar.getProgressDrawable().setColorFilter(Color.parseColor("#f44268"), PorterDuff.Mode.SRC_IN);

        }
       else if(rnd==6){

            bar.getProgressDrawable().setColorFilter(Color.parseColor("#ff721a"), PorterDuff.Mode.SRC_IN);

        }
       else {

            bar.getProgressDrawable().setColorFilter(Color.parseColor("#eb53ff"), PorterDuff.Mode.SRC_IN);

        }

        bar.setProgress(30);

    }
}
