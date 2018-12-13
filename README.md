# ExternalLogger-Android
External logger library for android applications


[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/vlad1m1r990/Lemniscate/blob/master/LICENSE)
[![](https://jitpack.io/v/guy-4444/StepLineIndicator.svg)](https://jitpack.io/#guy-4444/ExternalLogger-Android)
[![API](https://img.shields.io/badge/API-15%2B-green.svg?style=flat)]()

Vertical and horizontal step line indicator.


![device-2018-06-06-144912](https://github.com/guy-4444/ExternalLogger-Android/blob/master/Screenshot.png?raw=true)


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

###### StepView Constructor:
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
###### StepView Constructor parameters:

**Create Log**
```java
                ExtLog log = new ExtLog("Click", "Button Clicked");
                MyLoggerDB.getInstance().addLogToDB(log, new MyLoggerDB.LoggerDBCallBack_OnCompleted() {
                    @Override
                    public void onCompleted() {
                        MyLoggerDB.getInstance().getAllLogs(new MyLoggerDB.LoggerDBCallBack_LogsReturned() {
                            @Override
                            public void logsReturned(List<ExtLog> logs) {
                                updateText(logs);
                            }
                        });
                    }
                });
```

######Read all logs
```java
                MyLoggerDB.getInstance().getAllLogsByTag("Click", new MyLoggerDB.LoggerDBCallBack_LogsReturned() {
                    @Override
                    public void logsReturned(List<ExtLog> logs) {
                       // ...
                    }
                });
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
