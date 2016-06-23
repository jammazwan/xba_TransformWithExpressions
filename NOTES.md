### TransformWithExpressions NOTES:

There are so many ways to transform with expressions that providing working examples in one project might seem silly. But most are pretty straightforward, so the strategy will be to pick ten or so, staring with the obvious, and maybe even get in a couple odd ones.

If the array of examples seems disjunct to you, they did to me as well. And most are directly from the online docs or the code, so the value proposition to this project is only to present them in an already running form

### What are we trying to do here, anyway?

Directly from the [API docs](https://camel.apache.org/maven/camel-2.15.0/camel-core/apidocs/org/apache/camel/Expression.html), here's the interface:

```java
<T> T evaluate(Exchange exchange,
             Class<T> type);
```

If that's not open-ended enough to drive you batty, compare that to a typical use case from camel-core, which informs us that we'll be disguising our calls to the Expression API with a fluentAPI call:

```java
.transform(body().prepend("FOOTER:")).to(....
```

One conclusion, from a practical perspective - it's more **_about learning about very specific implementations of the Expression capability_** than writing code in the lower level API of the Expression itself. This distinction was a bit challenging for me, at first, even if it now seems rather obvious.

### Aids: Code snippets from camel's own test cases

Feel free to examine sortedTransforms.txt from the root of this project, for hints at taking this further. 

These strings were grepped from camel code, and if you wanted to learn more about any function you could in turn, grep camel test code for any one of these strings that you might be interested in.

### The More Obvious: Constant, Prepend, Append, Headers

 * **constant** comes in many forms, this one is the simplest form, and from the [camelinaction v1 code](https://github.com/camelinaction/camelinaction)
 * prepend
 * append
 * header

transform().simple("<index>${in.header.CamelSplitIndex}</index>${in.body}")

### Fun uses of the Simple language

These are directly from [camel docs](http://camel.apache.org/simple.html)

 * date:now
 * id
 * sysenv
 * random()

### OGNL

 * Here we are using an expression to peel out the value of a field from a java object.
 * simple("${body.address.lines[1]}")
 * simple("${body.address.lines[last-1]}")

### More specialized

 * messageHistory

### Saved for a more specific x__project

 * [property related](later)
 * [method()](https://github.com/jammazwan/xaz_TransformWithBean)
 * xslt conversion
 * exceptionMessage()
 
### Did not address, because do not know enough about

... or, in some cases, couldn't even get my head around

 * sendTo()
 * language()
 * terser()
 * ack
 * any more specific camel component, such as groovy or javascript
 








