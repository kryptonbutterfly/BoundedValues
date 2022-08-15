# BoundedValues
Provides a way to restrict the range of a type 

## Download

java version | library version | Download
:----------: | :-------------: | :-------
18+          | 1.0.0           | [**BoundedValues.jar**](https://github.com/tinycodecrank/BoundedValues/releases/download/v1.0.0/BoundedValues.jar)

## Dependencies
* [**Monads.jar**](https://github.com/tinycodecrank/tinyMonads/releases/download/v1.0.0/Monads.jar)
* [**tiny_iterators-1.0.0.jar**](https://github-registry-files.githubusercontent.com/524711166/350b3900-1c76-11ed-8320-cec0958c6479?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIWNJYAX4CSVEH53A%2F20220815%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20220815T201928Z&X-Amz-Expires=300&X-Amz-Signature=211367363e34dbe0942f1d0fc12b07930549de77241330f70c1b208744a60318&X-Amz-SignedHeaders=host&actor_id=0&key_id=0&repo_id=524711166&response-content-disposition=filename%3Dtiny_iterators-1.0.0.jar&response-content-type=application%2Foctet-stream)
* [**better_functionals-1.0.0.jar**](https://github-registry-files.githubusercontent.com/520194187/a8ac4680-1c74-11ed-8aae-8026dc765d3a?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIWNJYAX4CSVEH53A%2F20220815%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20220815T202126Z&X-Amz-Expires=300&X-Amz-Signature=abef8e2528faf6a205af945f6a8365e72b3c57048950181120beb5a453b61563&X-Amz-SignedHeaders=host&actor_id=0&key_id=0&repo_id=520194187&response-content-disposition=filename%3Dbetter_functionals-1.0.0.jar&response-content-type=application%2Foctet-stream)

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
