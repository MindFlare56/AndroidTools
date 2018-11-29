# Tools

Tools to make your life easier in java and android
- Java: DataTime
- Android: ViewTools, RunnableTask

# How to add

implementation 'com.github.MindFlare56:Tools:Tag'

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
