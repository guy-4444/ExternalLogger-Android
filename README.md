# ExternalLogger-Android
External logger library for android applications.
You can save logs in any component in your app (Activity, Service, Fragment).
The data will be stored in memory even after exiting the application or turning off the device.
You can access the information at any time, export the data to an email, to a text file or to a server.
The data can be accessed by the tag filter, text, or log creation time.
The data will be deleted by a function under your control.



[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/vlad1m1r990/Lemniscate/blob/master/LICENSE)
[![](https://jitpack.io/v/guy-4444/ExternalLogger-Android.svg)](https://jitpack.io/#guy-4444/ExternalLogger-Android)
[![API](https://img.shields.io/badge/API-15%2B-green.svg?style=flat)]()

Vertical and horizontal step line indicator.


![device-2018-06-06-144912](https://github.com/guy-4444/ExternalLogger-Android/blob/master/Screenshot2.png?raw=true)


## Setup
Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
```

Step 2. Add the dependency:

```
dependencies {
	implementation 'com.github.guy-4444:ExternalLogger-Android:1.00.02'
}
```
## Usage

###### ExternalLogger Constructor:
create Application Class (for example MyApplication.java):

```java
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        MyLoggerDB.initHelper(this);
    }
}

```
Declare name in manifest:
```java
    <application
        android:name=".MyApplication"
        //......
```
###### ExternalLogger Functionss:

**Create Log**
```java
ExtLog log = new ExtLog("Click", "Button Clicked");
MyLoggerDB.getInstance().addLogToDB(log, new MyLoggerDB.LoggerDBCallBack_OnCompleted() {
    @Override
    public void onCompleted() {
        // Do something
    }
});
		
//or without onCompletelistener.
MyLoggerDB.getInstance().addLogToDB(new ExtLog("Click", "Button Clicked"), null);
```

######Read all logs
```java
MyLoggerDB.getInstance().getAllLogsByTag("Click", new MyLoggerDB.LoggerDBCallBack_LogsReturned() {
    @Override
    public void logsReturned(List<ExtLog> logs) {
       // ...
    }
})
``` 
#####get logn from last hour:
```java
                MyLoggerDB.getInstance().getAllLogsFromDate(System.currentTimeMillis() - 3600*1000l, new MyLoggerDB.LoggerDBCallBack_LogsReturned() {
    @Override
    public void logsReturned(List<ExtLog> logs) {
        updateText(logs);
    }
});
```
#####Delete all logs:
```java
MyLoggerDB.getInstance().deleteAll();
```
## Credits

Thanks to [vipulasri](https://github.com/vipulasri/Timeline-View)

## License

    Copyright 2018 Guy Isakov

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
