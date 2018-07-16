#CDI Library
Purpose of this was mostly for myself so I didn't have to repeat certain reused things in every app I wrote.  I figured I'd share them with the world.

## A few useful things for me were, property injection multiple ways:
1. database stored properties
2. environment stored properties
3. file stored properties

## A few things I'd like to do
1. a little better annotation driven things for config; that would allow me to specify both the property to look up and a default value if one isn't found

If you want to use it; feel free to fork it and use it to your hearts content; I am just not liable for anything it may or may not do.

## How to use
###Inject a logger:
```
public class Foo {
  @Inject
  private Logger log;
}
```

###Inject and use properties:
```
public class FooPropertyManager {
  @Inject
  @FileProperties
  private Properties properties;

  @Produces
  public String injectString(InjectionPoint injectionPoint) {
    String className = injectionPoint.getMember().getDeclaringClass().getSimpleName();
    String fieldName = injectionPoint.getMember().getName();
    return properties.getProperty(className + "_" + fieldName);
  }
}
```

####In the case of FileProperties and DatabaseProperties the following snippet is used to find the appName
```
@Resource(lookup="java:app/AppName")
private String appName;
```
> In the case of FileProperties, the following is used to build the property filename; will default to appName = "app" in the case of no properties found
```
String propertyFileName = appName+".properties";
```
In the case of DatabaseProperties, the appName again defaults to "app", but if found via the resource lookup will use that.
This is probably something I should look at, but since I use TomEE as my container and I know this works well for my purposes I have never changed.