package fanxiaoli.screen;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTextview = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取屏幕分辨率
        WindowManager manager = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int mScreenWidth = display.getWidth();
        int ScreenHeight = display.getHeight();

        mTextview = (TextView)findViewById(R.id.textview1);
        mTextview.setText("屏幕分辨率为:"+mScreenWidth+"x"+ScreenHeight);
    }
}
