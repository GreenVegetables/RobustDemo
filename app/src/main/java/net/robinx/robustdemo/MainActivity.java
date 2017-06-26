package net.robinx.robustdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.meituan.robust.Patch;
import com.meituan.robust.PatchExecutor;
import com.meituan.robust.RobustCallBack;
import com.meituan.robust.patch.RobustModify;
import com.meituan.robust.patch.annotaion.Add;
import com.meituan.robust.patch.annotaion.Modify;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.loadPatch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runRobust();
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Modify
            @Override
            public void onClick(View v) {
//                RobustModify.modify();
//                Toast.makeText(MainActivity.this,"哈哈哈打",Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this,getString(),Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void runRobust() {
        new PatchExecutor(getApplicationContext(), new PatchManipulateImp(), new Callback()).start();
    }

    class Callback implements RobustCallBack {

        @Override
        public void onPatchListFetched(boolean result, boolean isNet, List<Patch> patches) {
            System.out.println(" robust arrived in onPatchListFetched");
        }

        @Override
        public void onPatchFetched(boolean result, boolean isNet, Patch patch) {
            System.out.println(" robust arrived in onPatchFetched");
        }

        @Override
        public void onPatchApplied(boolean result, Patch patch) {
            System.out.println(" robust arrived in onPatchApplied ");

        }

        @Override
        public void logNotify(String log, String where) {
            System.out.println(" robust arrived in logNotify " + where);
        }

        @Override
        public void exceptionNotify(Throwable throwable, String where) {
            throwable.printStackTrace();
            System.out.println(" robust arrived in exceptionNotify " + where);
        }
    }

    @Add
    public String getString() {
        return "Robust";
    }
}
