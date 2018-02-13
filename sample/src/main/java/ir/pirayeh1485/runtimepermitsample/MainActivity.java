package ir.pirayeh1485.runtimepermitsample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import ir.pirayeh1485.runtimepermit.Permission;
import ir.pirayeh1485.runtimepermit.PermissionPacket;
import ir.pirayeh1485.runtimepermit.PermissionRequester;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        mTextMessage.setTextColor(0xFF1d5407);
                        mTextMessage.setText(R.string.title_home);
                        return true;
                    case R.id.navigation_dashboard:
                        PermissionRequester.getInstance().request(dashboardPermissions);// request dashboard permission packet
                        return true;
                    case R.id.navigation_notifications:
                        PermissionRequester.getInstance().request(notificationsPermissions);// request notification permission packet
                        return true;
                }
                return false;
            }
        });
    }

    //create Permission packets
    private final PermissionPacket dashboardPermissions = new PermissionPacket() {
        @Override
        public void onPermitted() {
            mTextMessage.setTextColor(0xFF1d5407);
            mTextMessage.setText(R.string.title_dashboard);
        }

        @Override
        public void onDenied(PermissionPacket packet) {
            mTextMessage.setTextColor(0xFFFF0000);
            mTextMessage.setText("Dashboard access is denied. Please allow to all requirement permissions. \n");

            for (Permission p : packet.getPermissions()) {
                mTextMessage.append("- " + p.getTranslate() + "\n" + p.getName() + "\n");
            }
        }

        @Override
        public Permission[] getPermissions() {
            return new Permission[]{Permission.CAMERA};
        }

        @Override
        public Activity getActivity() {
            return MainActivity.this;
        }
    };

    private final PermissionPacket notificationsPermissions = new PermissionPacket() {
        @Override
        public void onPermitted() {
            mTextMessage.setTextColor(0xFF1d5407);
            mTextMessage.setText(R.string.title_notifications);
        }

        @Override
        public void onDenied(PermissionPacket packet) {
            mTextMessage.setTextColor(0xFFFF0000);
            mTextMessage.setText("Notifications access is denied. Please allow to all requirement permissions. \n");

            for (Permission p : packet.getPermissions()) {
                mTextMessage.append("- " + p.getTranslate() + "\n" + p.getName() + "\n");
            }
        }

        @Override
        public Permission[] getPermissions() {
            return new Permission[]{Permission.ACCESS_FINE_LOCATION, Permission.READ_CALENDAR};
        }

        @Override
        public Activity getActivity() {
            return MainActivity.this;
        }
    };


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionRequester.getInstance().checkResult(grantResults); //check result of requested permissions with PermissionRequester.
    }
}
