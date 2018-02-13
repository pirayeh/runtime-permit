package ir.pirayeh1485.runtimepermit;

import android.os.Handler;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abolhassan
 * on 2/10/2018.
 */

public class PermissionRequester {

    private final List<PermissionPacket> queue;
    private final PermissionPresenter presenter;

    private static final PermissionRequester ourInstance = new PermissionRequester();

    public static PermissionRequester getInstance() {
        return ourInstance;
    }

    private PermissionRequester() {
        queue = new ArrayList<>();
        presenter = new PermissionPresenter();
        presenter.setOnPermissionListener(new PermissionPresenter.OnPermissionListener() {
            @Override
            public void onPermitted(final PermissionPacket packet) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        packet.onPermitted();
                    }
                }, 100);


                if (queue.size() > 0) {
                    queue.remove(0);
                }

                checkQueue();
            }

            @Override
            public void onDenied(final PermissionPacket packet) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        packet.onDenied(packet);
                    }
                }, 100);

                if (queue.size() > 0) {
                    queue.remove(0);
                }

                checkQueue();
            }
        });
    }

    private void checkQueue() {
        if (queue.size() == 0) {
            return;
        }

        presenter.permit(queue.get(0));
    }

    public void request(PermissionPacket... packets) {
        if (packets == null || packets.length == 0) {
            throw new RuntimeException("permission packets is empty!");
        }

        for (PermissionPacket packet : packets) {
            if (queue.size() > 0) {
                queue.add(packet);
            } else {
                queue.add(packet);
                presenter.permit(queue.get(0));
            }
        }
    }

    public void checkResult(int[] grantResults) {
        presenter.checkResult(grantResults);
    }
}
