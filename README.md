# BoundedValues
Provides a way to restrict the range of a type 

## Download

java version | library version | Download
:----------: | :-------------: | :-------
18+          | 1.0.0           | [**BoundedValues.jar**](https://github.com/tinycodecrank/BoundedValues/releases/download/v1.0.0/BoundedValues.jar)

## Dependencies
* [**Monads.jar**](https://github.com/tinycodecrank/tinyMonads/releases/download/v1.0.0/Monads.jar)
* [**tinyIterators.jar**](https://github.com/tinycodecrank/tinyIterators/releases/download/v1.0.0/tinyIterators.jar)
* [**BetterFunctionals.jar**](https://github.com/tinycodecrank/betterFunctionals/releases/download/v1.0.0/BetterFunctionals.jar)

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
