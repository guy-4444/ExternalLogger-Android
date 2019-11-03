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


![device-2018-06-06-144912](https://github.com/guy-4444/ExternalLogger-Android/blob/master/Screenshot3.png?raw=true)


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
	implementation 'com.github.guy-4444:ExternalLogger-Android:1.00.03'
}
```
## Usage

### ExternalLogger Constructor:
No Constructor needed anymore, it happens automatically with Content Provider.

### ExternalLogger Functions:

**Create Log:**
```java
// Simple call:
MyLoggerDB.getInstance().addLogToDB("Life", "Activity Created");

// Or create ExtLog first:
MyLoggerDB.getInstance().addLogToDB(new ExtLog("Click", "Button Clicked"));


// With onCompletelistener - Simple call:
MyLoggerDB.getInstance().addLogToDB("Click", "Button Clicked", new MyLoggerDB.LoggerDBCallBack_OnCompleted() {
    @Override
    public void onCompleted() {
        // Do something
    }
});

// With onCompletelistener - creating ExtLog first:
ExtLog log = new ExtLog("Click", "Button Clicked");
MyLoggerDB.getInstance().addLogToDB(log, new MyLoggerDB.LoggerDBCallBack_OnCompleted() {
    @Override
    public void onCompleted() {
        // Do something
    }
});

```

**Read all logs:**
You can filter the logs by tag (like this example), text or time.
```java
MyLoggerDB.getInstance().getAllLogsByTag("Click", new MyLoggerDB.LoggerDBCallBack_LogsReturned() {
    @Override
    public void logsReturned(List<ExtLog> logs) {
    	// Run methid in UIThread !
	// Because data processing happens in another hassle
	runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM HH:mm:ss");
                String str = "";
                if (logs != null) {
                    Collections.sort(logs);
                    Log.d("pttt", "SIZE= " + logs.size());
                    for (int i = 0; i < logs.size(); i++) {
                        str += logs.get(i).getUid()
                                + ". "
                                + sdf.format(logs.get(i).getTime())
                                + " - "
                                + logs.get(i).getTag()
                                + " "
                                + logs.get(i).getText()
                                + "\n";
                    }
                }
                data.setText(str);
            }
        });
    }
})
``` 

**Sort by time:**

ExtLog has a buildin comparator to sort the logs by time
```java
Collections.sort(logs);
``` 

**get logs from last hour:**
```java
MyLoggerDB.getInstance().getAllLogsFromDate(System.currentTimeMillis() - 3600*1000l, new MyLoggerDB.LoggerDBCallBack_LogsReturned() {
    @Override
    public void logsReturned(List<ExtLog> logs) {
        updateText(logs);
    }
});
```
**Delete all logs:**
```java
MyLoggerDB.getInstance().deleteAll();
```
## Credits

Room library to manage SQL easily

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
