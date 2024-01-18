# BoundedValues
Provides a way to restrict the range of a type

## Getting the latest release

```xml
<repository>
  <id>github</id>
  <url>https://maven.pkg.github.com/kryptonbutterfly/maven-repo</url>
</repository>
```

```xml
<dependency>
  <groupId>kryptonbutterfly</groupId>
  <artifactId>bounded_values</artifactId>
  <version>2.0.0</version>
</dependency>
```

## Download

java version | library version | Download
:----------: | :-------------: | :-------
18+          | 2.0.0           | [**bounded_values-2.0.0.jar**](https://github-registry-files.githubusercontent.com/731108692/f6469300-b660-11ee-8b04-5e95f9beea1f?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAVCODYLSA53PQK4ZA%2F20240118%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240118T232402Z&X-Amz-Expires=300&X-Amz-Signature=2302310e1bdfa5519063d133a3f31aed2f305320d3dc14ea30b4a9e19510c0c1&X-Amz-SignedHeaders=host&actor_id=0&key_id=0&repo_id=731108692&response-content-disposition=filename%3Dbounded_values-2.0.0.jar&response-content-type=application%2Foctet-stream)
18+          | 1.0.0           | [**BoundedValues.jar**](https://github.com/kryptonbutterfly/BoundedValues/releases/download/v1.0.0/BoundedValues.jar)

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
