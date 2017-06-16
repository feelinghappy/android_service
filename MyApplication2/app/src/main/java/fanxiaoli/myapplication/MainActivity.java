package fanxiaoli.myapplication;

import android.os.Environment;
import android.os.StatFs;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    TextView mTextViewShow01;
    TextView mTextViewShow02;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewShow01 = (TextView)findViewById(R.id.textview1);
        mTextViewShow02 = (TextView)findViewById(R.id.textview2);
        getSd();
        mTextViewShow02.setText(getRomSpaceInfo());

    }
    /**
     * 获取SD的信息的方法
     */
    private void getSd() {
        // TODO Auto-generated method stub
        File path= Environment.getExternalStorageDirectory();
        StatFs stat=new StatFs(path.getPath());
        long blockSize=stat.getBlockSize();
        long totalBlacks=stat.getBlockCount();
        long availableBlocks=stat.getAvailableBlocks();

        long totalSize=blockSize*totalBlacks;
        long availSize=availableBlocks*blockSize;


        String totalStr= Formatter.formatFileSize(MainActivity.this, totalSize);
        String availStr=Formatter.formatFileSize(MainActivity.this, availSize);

        mTextViewShow01.setText("Sd卡的总的容量是"+totalStr+"\n"+"SD卡的可用容量是"+availStr);
    }

    //android获取手机内存的方法

    /**
     * 获取手机内存的方法
     * @return
     */
    private String getRomSpaceInfo() {
        File path=Environment.getDataDirectory();
        StatFs stat=new StatFs(path.getPath());
        long blockSize=stat.getBlockSize();
        long totalBlocks=stat.getBlockCount();
        long availableBlocks=stat.getAvailableBlocks();

        long totalSize=blockSize*totalBlocks;
        long availSize=availableBlocks*blockSize;

        String totalStr=Formatter.formatFileSize(MainActivity.this, totalSize);
        String availStr=Formatter.formatFileSize(MainActivity.this, availSize);
        return "手机的内存:总空间"+totalStr+"\n"+availStr;
    }

}
