When `map()`ing via Java streams or `Optional`, it's often convenient to use
method references:

```java
interface Speaker {
    String speak();
}

class AdmiralStockdale implements Speaker {
    public String speak() {return "gridlock!";}
}

final Optional<Speaker> animal = Optional.of(new AdmiralStockdale());

System.out.println(animal.map(Speaker::speak).get());

=> "gridlock!"
```

But because a method reference always includes a class (or interface)
name this sometimes seems needlessly verbose. (`Speaker::speak`).
It's not only verbose—it's worse than that. It mentions the class (interface)
name. This often forces the developer to go look up that name. Later, that
name must be maintained.

An alternative is to use lambda:

```java
System.out.println(animal.map(animal.map(a->a.speak()).get()).get());

=> "gridlock!"
```

At the point where we send the `speak()` message, we don't assume anything 
about the type of a—only that it can `speak()`. That looks a lot like duck typing!

The compiler takes care of figuring out if `a` (`animal`) can `speak()`. Later, if
the class names, or class/interface hierarchy change, our mapping code is stable.
Our type checker ensures compatibility.
         
Don't repeat yourself!