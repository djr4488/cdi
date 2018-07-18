# CDI Library
Purpose of this was mostly for myself so I didn't have to repeat certain reused things in every app I wrote.  I figured I'd share them with the world.

## A few useful things for me were, property injection multiple ways:
1. database stored properties
2. environment stored properties
3. file stored properties

## A few things I'd like to do
1. a little better annotation driven things for config; that would allow me to specify both the property to look up and a default value if one isn't found

If you want to use it; feel free to fork it and use it to your hearts content; I am just not liable for anything it may or may not do.

## How to use
### Inject a logger:
```
public class Foo {
  @Inject
  private Logger log;
}
```

### Inject and use properties:
```
public class Foo {
  @Inject
  @Config(propertyName="Foo_fooProperty", defaultValue="default")
  private String fooProperty;
```

### How and where to store properties:
There are four ways this can have properties stored: file, environment, database, or in the annotation(if it cannot be found in the other three locations).

In the case of file the properties file name in order to be found should follow the application name as defined by
```
@Resource(lookup="java:app/AppName")
private String appName;
```
For exaxmple if your pom.xml looked like
```
    <groupId>org.djr.foo</groupId>
    <artifactId>foo-bar</artifactId>
    <version>1.0.0</version>
    <packaging>war</packaging>
```
Then the properties file would need to be named: foo-bar.properties to be found.  In the case, the appName cannot be resolved, file properties will try to look for app.properties as a fallback.

In the case of database properties, again the @Resource method of looking up the appName will be performed and will look for that in the database when looking up properties.
You also, need to create a resource.xml config for TomEE and define where your config database will live.  In your resources.xml, the name of the resource should be ConfigDatabase.

