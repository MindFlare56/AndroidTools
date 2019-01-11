[![](https://jitpack.io/v/MindFlare56/Tools.svg)](https://jitpack.io/#MindFlare56/Tools)
[![API](https://img.shields.io/badge/API-26%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=26)
# Tools

Tools to make your life easier in java and android
- Java: DataTime, LinkedMap, Tools...
- Android: ViewTools, RunnableTask, NfcFragment, WebImage, DefaultAdapter,
         DateTime, RecyclerListView, Notification, Dialog, Translator...

# How to add

Add this line + the current version to your app.gradle:<br>
implementation 'com.github.MindFlare56:Tools:Tag'

Visit the below link to get the current version and the installation explanation<br/>
https://jitpack.io/#MindFlare56/Tools<br/>
```android
allprojects {
    repositories {
	maven { url 'https://jitpack.io' }
     }
}
implementation 'com.github.MindFlare56.Tools:androidtools:1.4.5'
```
<br/>
## Also require these usefull tools: <br/>
annotationProcessor "com.jakewharton:butterknife:8.5.1"<br/>
annotationProcessor "com.jakewharton:butterknife-compiler:8.5.1"<br/>
annotationProcessor 'com.github.bumptech.glide:compiler:4.8.0'<br/>
annotationProcessor "org.projectlombok:lombok:1.16.18"<br/>
compileOnly "javax.annotation:jsr250-api:1.0"<br/>
## Unfortunatly require: <br/>
in android studio: file->settings->plugins->browse reposistories->Lobok Plugin->Install<br/>
```android
android { //put packaginOptions inside your android {} in your build.gradle
    packagingOptions {
        exclude 'META-INF/DEPENDENCIES'
        exclude 'META-INF/LICENSE'
        exclude 'META-INF/LICENSE.txt'
        exclude 'META-INF/license.txt'
        exclude 'META-INF/NOTICE'
        exclude 'META-INF/NOTICE.txt'
        exclude 'META-INF/notice.txt'
        exclude 'META-INF/ASL2.0'
    }  
}
```
# ------------------------------------------------
# RunnableTask usage example
```java
RunnableTask runnableTask = new RunnableTask() {
    @Override
    public void run() {
        mainController.someMethodInitDatabase();
    }
    @Override
    public void progress() {
        progressAfter(someMethodIsArrayFilled(), "Some message Array is filled", intProgress);
    }
    @Override
    public void end() {
        someMethodBuildUserArray()    
    }
};
runnableTask.start(this, delayInMilliseconds);
```
# Translator
```java
new Translator("hello world I am Dave and it's nice to meet y'all", "en", "fr") {
   @Override             //lang can be optional it is english to french default
   public void onResult(String result) {
       ViewTools.logv(result);
   }
};
//than you can reuse it if you declared Translator translator = new Translator(...
translator.setDestination("fr");
translator.add("A phrase to traduce in french");
translator.add("Another phrase to traduce in french");
translator.add("Some other text", "en", "it");
translator.add("Some other text to traduce in italian");
```
# NfcFragment
```java
//Just extends the NfcFragment class and set these
public class SomeFragment extends NfcFragment {

    @Override
    public int setLayout() {
        return R.layout.some_fragment;//set your fragment layout here
    }
    
    @Override
    public void onCreate(View view) {
        //get fragment built view here
    }
    
    @Override
    public void handleMessage(String message) {
        //get message here
    }
}

//last step that I couldn't avoid unfortunatly
public class MainActivity extends AppCompatActivity {

    private NfcFragment nfcFragment;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nfcFragment = new SomeFragment();
        ViewTools.changeFragment(this, R.id.main_frame, nfcFragment);
    }
    
    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        String nfcTagData = nfcFragment.resolveIntent(intent);
    }
}
```
# RecyclerAdapter && WebImage usage example
```java
listView.setAdapter(new RecyclerAdapter(context, list.size(), R.layout.layout) {
    @Override
    public void adaptView(int position, View view) {
        WebImage.setImageView(activity, view.findViewById(R.id.image), list.get(position).getImageUrl(), someWidth, someHeight);
        ((TextView) view.findViewById(R.id.text)).setText(list.get(position).getInfo());
    }
});
```
# RecyclerListView
```java
    @BindView(R.id.recyclerview) RecyclerView recyclerView;
    @BindView(R.id.test_textview) TextView textView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final ArrayList<String> strings = new ArrayList<>();
        strings.add("string 1");
        strings.add("string 2");       
        new RecyclerListView(recyclerView, this, strings.size(), R.layout.test_view) {
            @Override
            protected void adaptView(int position, View view) {
                textView.setText(strings.get(position));
            }
        };
    }
```

# DateTime method prototype showcase
```java
    // format == iso8601
    public static String getCurrentDateTime(); "yyyy-MM-dd HH:mm:ss"
    public static String getCurrentDate(); // "yyyy-MM-dd"
    public static String getCurrentYear(); // "yyyy"
    public static String getCurrentTime(); // "HH:mm:ss"
    public static String getCurrentHours();
    public static String getCurrentMilliSeconds();
    public static double getCurrentMilliSecondsValue();
    public static String getADateMilliSeconds(String dateString);
    public static String changeDateFormatToIso8601(String stringDate, final String OLD_FORMAT);
    public static String extractDay(String dateString); // "dd"
    public static boolean isDateBefore(String inputDate, String comparedDate);
    public static boolean isDateValid(String inputDate);
    public static int getAge(String birthDate);
    ...............
```

# ViewTools usage examples
```java
public class SomeFragment extends Fragment {
    ...
    private void someMethod() {    
        if (condition) {
            ViewTools.changeFragment(activity, R.id.mainDrawerFrame, new SomeFragment(), getString("Fragment title"));
        } else {
            ViewTools.changeFragment(activity, R.id.mainDrawerFrame, new SomeOtherFragment(), getString("Other fragment title"));
        }
    }
}
```
```java
private void someMethod(int progress) {
    ViewTools.logv("Progress made: " + progress + "!");
}
/** 
Console output example:
V/Debug | 17:43:24 ->: Progress made: 100!
**/
```
# Notification
```java
notification = new Notification(this)
        .setChannelId(getString(R.string.channel_id))
        .setIcon(R.drawable.landfill)
        .setDescription(getString(R.string.notification_description))
        .setChannelText(getString(R.string.notification_channel_text))
        .setTitle(getString(R.string.notification_title_plants))
        .setContentText("")
        .setTargetClass(PlantFragment.class);
notification.show();
```
# LinkedMap usage exmaple with adaptView
```java
LinkedMap<String, MyObject>> myObjectMap = new LinkedMap<>(keyList, myObjectList);
for (MyObject myObject : myObjectMap.getValues()) {
    //do something
}
...
MyObject myObject = myObjectMap.get("key");
...
//Here's one of the case that made me do this
private Button button;

@Override
protected void adaptView(int i, View view) {
    findViews(view);
    MyObject myObject = myObjectMap.get(i);    
    button.setOnClickListener(v -> controller.doSomething(myObject));
}
```
# Tools usage example
```java
Map<String, SomeObject> someObjectMap = getSomeObjectMap();
List<SomeObject> someObjects = Tools.mapToArray(someObjectMap);
listView.setAdapter(new DefaultAdapter(context, list.size(), R.layout.layout) {
    @Override
    public void adaptView(int position, View view) {
        WebImage.setImageView(activity, view.findViewById(R.id.image), someObjects.get(position).getImageUrl(), someWidth, someHeight);
        ((TextView) view.findViewById(R.id.text)).setText(someObjects.get(position).getInfo());
    }
});
```
# ---------------------------
## Lambda support:<br/> //(under android in build.gradle)
```android
compileOptions {
    sourceCompatibility = '1.8'
    targetCompatibility = '1.8'
    sourceCompatibility JavaVersion.VERSION_1_8
    targetCompatibility JavaVersion.VERSION_1_8
}
```
# Project dependencies:
    compileOnly "javax.annotation:jsr250-api:1.0"
    annotationProcessor "com.jakewharton:butterknife:8.5.1"
    annotationProcessor "com.jakewharton:butterknife-compiler:8.5.1"
    annotationProcessor 'com.github.bumptech.glide:compiler:4.8.0'
    annotationProcessor "org.projectlombok:lombok:1.16.18"
    implementation 'org.jetbrains:annotations:15.0'
    implementation 'com.github.bumptech.glide:glide:4.8.0'
    implementation 'com.jakewharton:butterknife:8.5.1'
    implementation 'com.jakewharton:butterknife-compiler:8.5.1'
    implementation 'com.github.psinetron:slycalendarview:0.0.7'         
    implementation 'org.projectlombok:lombok:1.16.18'
    
# --------------------------------
# In beta
# LoginHandler
```java
@BindView(R.id.lh_username) EditText username;
@BindView(R.id.lh_password) EditText password;
@BindView(R.id.lh_checkbox) CheckBox rememberMe;
@BindView(R.id.lh_button) Button submit;

@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.login_handler);
     ButterKnife.bind(this);
     Activity ref = this;
     LoginFields loginFields = new LoginFields(username, password, rememberMe, submit);
     loginFields.setRequestFields("http://yourIpHere/phpFunctionHere", "userTableName", "passwordTableName");
     LoginHandler loginHandler = new LoginHandler(this, loginFields) {
         @Override
         public void onLogRequestEnd() {
              ViewTools.logv("rdy to change activity !");
              ViewTools.changeActivity(ref, NextActivity.class);
         }
     };
     loginHandler.log();
}
```
