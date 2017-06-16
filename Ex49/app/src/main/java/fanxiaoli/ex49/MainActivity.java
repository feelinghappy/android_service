package fanxiaoli.ex49;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/*
 * 取得手机电池的剩余量
 * Android API中的BroadcastReceiver(Android.content.BroadcastReceiver)
 * 类有点像Button中的Listener，当Receiver被注册后，会在后台等待其他程序
 * 的调用，程序将通过注册BroadcastReceiver时设置的IntentFilter来捕捉系统
 * 发出的Intent.ACTION_BATTERY_CHANGED这个action,再以此取得手机电池的剩
 * 余量。
 */
public class MainActivity extends AppCompatActivity {
    private int intLevel;
    private int intScale;
    private Button mButton01;
    private AlertDialog d;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton01 = (Button) findViewById(R.id.myButton1);
        mButton01.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub 
                // 注册一个BroadcastReceiver，作为访问电池计量之用 
                registerReceiver(mBatInfoReveiver, new IntentFilter(
                        Intent.ACTION_BATTERY_CHANGED));
            }
        });
    }

    // 创建BroadcastReceiver 
    private BroadcastReceiver mBatInfoReveiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub 
            String action = intent.getAction();
            // 如果捕捉到action是ACRION_BATTERY_CHANGED 
            // 就运行onBatteryInfoReveiver() 
            if (intent.ACTION_BATTERY_CHANGED.equals(action)) {
                intLevel = intent.getIntExtra("level", 0);
                intScale = intent.getIntExtra("scale", 100);
                onBatteryInfoReceiver(intLevel, intScale);
            }
        }
    };


    // 拦截到ACTION_BATTRY_CHANGED后要执行的动作 
    private void onBatteryInfoReceiver(int intLevel, int intScale) {
        // TODO Auto-generated method stub 
        final Dialog d = new Dialog(MainActivity.this);
        d.setTitle(R.string.str_dialog_title);
        d.setContentView(R.layout.mydialog);
        Window window = d.getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        TextView mTextView04 = (TextView) d.findViewById(R.id.text4);
        mTextView04.setText(getResources().getText(R.string.str_dialog_body)
                + String.valueOf(intLevel * 100 / intScale) + "%");
        Button mButton02 = (Button) d.findViewById(R.id.myButton2);
        mButton02.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                unregisterReceiver(mBatInfoReveiver);
                d.dismiss();
            }
        });
        d.show();
    }
}

