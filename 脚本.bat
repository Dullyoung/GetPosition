:startTask
choice /t 3 /d y /n >nul
adb shell input tap 850 1450
GOTO endTask


:endTask
choice /t 22 /d y /n >nul
adb shell input tap 1000 160
GOTO startTask