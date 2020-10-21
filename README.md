# GetPosition
# apk 下载链接
https://raw.githubusercontent.com/Dullyoung/GetPosition/main/app/release/%E5%9D%90%E6%A0%87%E9%80%89%E5%8F%96.apk
# 使用方法
### 要用到手机和电脑 手机打开开发者模式，usb连接到电脑,不会的自己百度
 - 下载 坐标获取.apk 安装到手机上 下载adb.exe和脚本.bat到电脑上
 - 打开apk按照提示获取你要点击的坐标点
 - 把脚本.bat用记事本打开 
 - choice /t 3 /d y /n >nul 延时3秒
 - adb shell input tap 850 1450 点击850 1450 坐标
 - 这两行命令 /t 3这个3 是延时多少秒， 850 1450就是要点击的坐标点。按照你自己的设备获取到的坐标自己设置。
- startTask就是进去点去完成，endTask就是点右上角的x  GOTO表示跳转，要做其他更复杂的可以自己去定义。 冒号定义函数名，goto 跳转到这个函数名 .
修改完成保存 把脚本.bat 和adb.exe放在同一目录下 双击脚本.bat运行就完了。
