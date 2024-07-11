实现了一个简单的标题栏和七个图标的焦点动画效果


### 转换为系统应用 提交说明
新建一个系统项目
1、在<manifest>标签中添加android:sharedUserId="android.uid.system"属性，指定为系统应用；
2、模块配置文件的<android>标签中当中添加如下配置：
● 注意：storeFile file("../libs/platform.jks")这一条配置所对应的文件必须存在！！
● 这里我的文件放在根目录下的libs（新建的）目录下，如果存放在其他位置，对应的修改路径即可。
● platform.jks文件github网址：https://github.com/chao-bro/focus-animator/blob/27eb27ff547dffae76560fa2297a8ac81ee5eabb/libs/platform.jks
~~~gradle
signingConfigs {
release {
storeFile file("../libs/platform.jks")
storePassword 'android'
keyAlias 'platform'
keyPassword 'android'
}

    debug {
      storeFile file("../libs/platform.jks")
      storePassword 'android'
      keyAlias 'platform'
      keyPassword 'android'
    }
}
~~~