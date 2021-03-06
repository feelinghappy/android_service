package fanxiaoli.call_listener;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class InOutCallReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(final Context context, final Intent intent)
    {
        if(intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL))
        {
            //获取去电的电话号码
            String outcommingNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            Toast.makeText(context, outcommingNumber, Toast.LENGTH_SHORT).show();

        }
        //监听来电
        else
        {
            TelephonyManager tm = (TelephonyManager)context.getSystemService(Service.TELEPHONY_SERVICE);
            String incommingNumber = intent.getStringExtra("incoming_number");
            switch (tm.getCallState())
            {
                //来电响铃
                case TelephonyManager.CALL_STATE_RINGING:
                    Toast.makeText(context,"CALL_STATE_RINGING:"+incommingNumber,Toast.LENGTH_SHORT).show();
                    break;
                //摘机接听
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Toast.makeText(context,"CALL_STATE_OFFHOOK:"+incommingNumber,Toast.LENGTH_SHORT).show();
                    break;
                //挂机
                //摘机接听
                case TelephonyManager.CALL_STATE_IDLE:
                    Toast.makeText(context,"CALL_STATE_IDLE:"+incommingNumber,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}