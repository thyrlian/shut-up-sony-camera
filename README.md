shut-up-sony-camera
===================

Disable camera focus and shutter sound for Sony mobile phones.

Still doesn't work because of annoying Sony permission issue (even though it's already added to AndroidManifest file):
```
W/WindowManager(1012):
Access to extended visibility flags denied:
Requires com.sonymobile.permission.SYSTEM_UI_VISIBILITY_EXTENSIONS permission.
```

Sony's camera sound files' path:
- *system/media/audio/ui/camera_click.ogg*
- *system/media/audio/ui/camera_focus.ogg*

Steps of removing sound files (unfortunately it fails):
```
adb root
adb shell mount -o rw,remount /system
adb shell rm system/media/audio/ui/camera_click.ogg
adb shell rm system/media/audio/ui/camera_focus.ogg
adb shell mount -o ro,remount /system
```
