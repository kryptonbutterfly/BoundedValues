# BoundedValues
Provides a way to restrict the range of a type

## Getting the latest release

```xml
<repository>
  <id>github</id>
  <url>https://maven.pkg.github.com/tinycodecrank/maven-repo</url>
</repository>
```

```xml
<dependency>
  <groupId>de.tinycodecrank</groupId>
  <artifactId>bounded_values</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Download

java version | library version | Download
:----------: | :-------------: | :-------
18+          | 1.0.0           | [**BoundedValues.jar**](https://github.com/tinycodecrank/BoundedValues/releases/download/v1.0.0/BoundedValues.jar)

## Example

```java
for (int divider : Range.range(-2, 5, 2))
{
	String result = int_not_zero.builder()
		.createOpt(divider)
		.map(div -> 4 / div.value())
		.map(div -> div.toString())
		.get(() -> "Divide by zero not allowed");
	System.out.println(result);
}
```
Output:
```bash
-2
Divide by zero not allowed
2
1
```
