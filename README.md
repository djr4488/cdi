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
You can provide an optional propertyName and defaultValue, if you don't provide an optional property name then the property will be searched by class name property name.  e.g. Foo_fooProperty.
```
public class Foo {
  @Inject
  @Config(propertyName="Foo_fooProperty", defaultValue="default")
  private String fooProperty;
```

### How and where to store properties:
There are four places you can have properties be accessed
1. file based properties
2. database based properties
3. environment based properties
4. defined in the @Config annotation

#### File based properties
Work by looking up your application name via a @Resource look up
```
@Resource(lookup="java:app/AppName")
private String appName;
```
Which of course comes from your pom.xml(I use maven) 
```
    <groupId>org.djr.foo</groupId>
    <artifactId>foo-bar</artifactId>
    <version>1.0.0</version>
    <packaging>war</packaging>
```
Using this strategy the propery file name ultimately becomes **foo-bar.properties** I strip off the version from it.  Whether that should happen or not, I'm still debating.

#### Environment properties
Look in your environment for the property by looking at System.getenv();

#### Database properties
Look in a database table named "property_models" which are configured by application name, property name, and property value.

#### Annotation
If not found anywhere else, and a defaultValue is provided, then it will use the defaultValue.
```
public class Foo {
  @Inject
  @Config(propertyName="Foo_fooProperty", defaultValue="default")
  private String fooProperty;
```
