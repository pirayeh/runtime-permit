# runtime-permit
easily library for request and handle result android runtime permissions.


# How to Use

* Step 1: Add the JitPack repository to your build file

  Add it in your root build.gradle at the end of repositories:

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

* step 2: Add the dependency to your module gradle 
```
dependencies {
    compile 'com.github.pirayeh:runtime-permit:0.1.0'
}
```

# Example:

* AndroidManifest:

```
<uses-permission android:name="android.permission.CAMERA" />
```

* create a PermissionPacket instance:

```
private final PermissionPacket packet = new PermissionPacket() {
        @Override
        public void onPermitted() {
            mTextMessage.setTextColor(Color.GREEN);
            mTextMessage.setText("successfully!");
        }

        @Override
        public void onDenied(PermissionPacket packet) {
            mTextMessage.setTextColor(Color.RED);
            mTextMessage.setText("access is denied. Please allow to all requirement permissions.");
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
```
  
  * request packet:
  
```
  PermissionRequester.getInstance().request(packet);
```
 
 * override onRequestPermissionsResult method and check result:
 
 ```
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
      PermissionRequester.getInstance().checkResult(grantResults); //check result of requested permissions with PermissionRequester.
  }
```

