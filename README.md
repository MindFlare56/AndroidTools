# Tools

Tools to make your life easier in java and android
- Java: DataTime
- Android: ViewTools, RunnableTask, WebImage, DefaultAdapter

# How to add

Add this line + the current version to your app.gradle:<br>
implementation 'com.github.MindFlare56:Tools:Tag'

Visit the below link to get the current version and the installation explanation<br/>
https://jitpack.io/#MindFlare56/Tools
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
# DefaultAdapter usage example
```java
listView.setAdapter(new DefaultAdapter(context, list.size(), R.layout.layout) {
    @Override
    public void adaptView(int position, View view) {
        WebImage.setImageView(activity, view.findViewById(R.id.image), list.get(position).getImageUrl(), someWidth, someHeight);
        ((TextView) view.findViewById(R.id.text)).setText(list.get(position).getInfo());
    }
});
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
//Console output example:
//V/Debug | 17:43:24 ->: Progress made: 100!
```
