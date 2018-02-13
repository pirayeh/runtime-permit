package ir.pirayeh1485.runtimepermit;

import android.app.Activity;

/**
 * Created by
 * Abolhassan on 2/10/2018.
 */
public abstract class PermissionPacket {

    public abstract void onPermitted();

    public abstract void onDenied(PermissionPacket packet);

    public abstract Permission[] getPermissions();

    public abstract Activity getActivity();
}
