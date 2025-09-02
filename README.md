# Java Checks

Java Checks (or `Checks`) is a Java utility that provides a set of static utility methods for performing common checks 
and validations throughout your code, improving code readability while also takes a more functional approach to 
such validations allowing for more flexibility.

Java 11+ and Java 21+ versions are available.

`Checks` can be considered as a (stand-alone) alternative to Google Guava's `Preconditions` or Apache Commons Lang's 
`Validate` that can be used generally everywhere.

## Provided Checks

- Boolean expression checks (state or argument checks);
- Null/not-null checks;
- Object equality checks;
- Empty/not-empty checks for strings, collections, maps and arrays;
- Provide custom exception types through functional interfaces.

See [Javadocs](#documentation) for a full list of available methods.

## Examples

### Basic Use

```java
// This...
if (!expression)
    throw new IllegalArgumentException("Expression is false");

// can be replaced with...
Checks.check(expression, "Expression is false");

// And this...
if (obj == null)
    throw new IllegalArgumentException("Object is null");

// can be replaced with...
Checks.isNotNull(obj, "Object is null");
```

### Using Different or Custom Exceptions

```java
// This...
if (object == null)
    throw new IllegalStateException("Object is null");

// can be replaced with...
Checks.checkState(object != null, "Object is null");
// or...
Checks.check(object != null, IllegalStateException::new, "Object is null");
```

## Include

### Maven
```xml
<repository>
    <id>greenadine-releases</id>
    <url>https://repo.greenadine.dev/releases/</url>
</repository>
```

#### Java 21+
```xml
<dependency>
    <groupId>dev.greenadine</groupId>
    <artifactId>checks-21</artifactId>
    <version>1.0.0-21</version>
</dependency>
```

#### Java 11+
```xml
<dependency>
    <groupId>dev.greenadine</groupId>
    <artifactId>checks-11</artifactId>
    <version>1.0.0-11</version>
</dependency>
```

## Documentation

- [Javadocs](https://jd.greenadine.dev/checks/1.0.0/)

## Licensing

This project is licensed under the GNU Lesser General Public License v3.0. See the [license](LICENSE.txt) file for details.