package ir.pirayeh1485.runtimepermit;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abolhassan
 * on 1/24/2018.
 */
final class PermissionPresenter {

    private OnPermissionListener onPermissionListener;
    private List<String> deniedPermissions;
    private PermissionPacket mPacket;


    public interface OnPermissionListener {
        void onPermitted(PermissionPacket packet);

        void onDenied(PermissionPacket packet);
    }


    void permit(PermissionPacket packet) {
        this.mPacket = packet;

        String[] permissions = checkPermissions(mPacket.getActivity(), packet.getPermissions());

        if (permissions.length == 0 && onPermissionListener != null) {
            onPermissionListener.onPermitted(mPacket);
            return;
        }

        ActivityCompat.requestPermissions(mPacket.getActivity(), permissions, mPacket.getPermissions()[0].getCode());
    }


    private String[] checkPermissions(Activity activity, Permission... permissions) {
        deniedPermissions = new ArrayList<>();

        if (permissions == null || permissions.length == 0) {
            return deniedPermissions.toArray(new String[deniedPermissions.size()]);
        }

        for (Permission permission : permissions) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(activity, permission.getName()) != PackageManager.PERMISSION_GRANTED) {
                    deniedPermissions.add(permission.getName());
                }
            }
        }

        return deniedPermissions.toArray(new String[deniedPermissions.size()]);
    }


    void checkResult(int[] grantResults) {

        if ((grantResults == null || grantResults.length == 0)) {
            if (onPermissionListener != null) {
                onPermissionListener.onDenied(new PermissionPacket() {
                    @Override
                    public void onPermitted() {
                        mPacket.onPermitted();
                    }

                    @Override
                    public void onDenied(PermissionPacket packet) {
                        mPacket.onDenied(this);
                    }

                    @Override
                    public Permission[] getPermissions() {
                        return Permission.getPermissionsByName(deniedPermissions);
                    }

                    @Override
                    public Activity getActivity() {
                        return mPacket.getActivity();
                    }
                });
            }
            return;
        }

        final List<Permission> notAllowList = new ArrayList<>();

        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                notAllowList.add(Permission.getByName(deniedPermissions.get(i)));
            }
        }


        if (onPermissionListener != null) {
            if (notAllowList.size() > 0) {
                onPermissionListener.onDenied(new PermissionPacket() {
                    @Override
                    public void onPermitted() {
                        mPacket.onPermitted();
                    }

                    @Override
                    public void onDenied(PermissionPacket packet) {
                        mPacket.onDenied(this);
                    }

                    @Override
                    public Permission[] getPermissions() {
                        return notAllowList.toArray(new Permission[notAllowList.size()]);
                    }

                    @Override
                    public Activity getActivity() {
                        return mPacket.getActivity();
                    }
                });
            } else {
                onPermissionListener.onPermitted(mPacket);
            }
        }
    }

    PermissionPresenter setOnPermissionListener(OnPermissionListener listener) {
        this.onPermissionListener = listener;
        return this;
    }
}
