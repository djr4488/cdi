# CDI Library
Purpose of this was mostly for myself so I didn't have to repeat certain reused things in every app I wrote.  I figured I'd share them with the world.

## A few useful things for me were:
1. property injection multiple ways: file, database, environment, or annotation
2. injectable logging
3. Json to Object and Object to Json converter(using Jackson)
4. Custom Jackson ObjectMapper injection
5. A way to lookup **@Named** beans managed by CDI

## Testing Coverage and Build Reports
* Build: [![Build Status](https://travis-ci.org/djr4488/cdi.svg?branch=master)](https://travis-ci.org/djr4488/cdi)
* Coverage: [![Coverage Status](https://coveralls.io/repos/djr4488/cdi/badge.svg?branch=master)](https://coveralls.io/r/djr4488/cdi?branch=master) [![codecov](https://codecov.io/gh/djr4488/cdi/branch/master/graph/badge.svg)](https://codecov.io/gh/djr4488/cdi)

## How to use
### Inject a logger:
```
public class Foo {
  @Inject
  private Logger log;
}
```

### Lookup a named bean
```
@ApplicationScoped
@Named("Foo")
public class Foo {
...
}
```
Then you would look by -  I think I'd rather make this a static class so the lookup bean could be used in a non-managed CDI class, however I have not done that currently.
```
@ApplicationScoped
public class Bar {
  @Inject
  Lookup lookup;
  public void fooBar() {
    Foo foo = lookup.get("Foo", Foo.class);
  }
}  
```

### Converting to and from JSON
Given the following class
```
public class Person {
  private String firstName;
  private String lastName;
  
  ...
}
```
To convert to JSON
```
public class Foo {
  @ApplicationScoped
  private JsonConverter jsonConverter;
  
  public String convertToJson(Person person) {
      return jsonConverter.toJsonString(person);
  }
}
```
Given the following JSON and the above Person class
```
{
  "firstName": "first",
  "lastName": "last"
}
```
Then to convert json to Person
```
public class Foo {
  @ApplicationScoped
  private JsonConverter jsonConverter;
  
  public String convertToPerson(String jsonPerson) {
      return jsonConverter.toObjectFromString(jsonPerson, Person.class);
  }
}
```

### Inject custom Jackson ObjectMapper
The example uses the implmentation provided in JsonConverter.
```
    @JacksonModule(jacksonModules = {JodaModule.class})
    @JacksonMapperFeature(features = {
            @MapperFeatureConfig(feature = MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, value = false),
            @MapperFeatureConfig(feature = MapperFeature.AUTO_DETECT_GETTERS, value = true)})
    @JacksonDeserializationFeature(features = {
            @DeserializationFeatureConfig(feature = DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, value = false)})
    @Inject
    @JacksonObjectMapper
    private ObjectMapper objectMapper;
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

