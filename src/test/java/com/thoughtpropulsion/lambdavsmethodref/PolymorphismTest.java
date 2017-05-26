package com.thoughtpropulsion.lambdavsmethodref;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class PolymorphismTest {
    private Optional<Animal> animal;

    @Before
    public void setup() {
        animal = Optional.of(new Dog());
    }

    @Test
    public void methodRefIsPolymorphicTest() {
        /*
         * We don't know what kind of animal we have so we a reference to the
         * interface method. Since animal is actually a Dog, it barks.
         */
        assertThat( animal.map(Animal::speak).get(), is("arf!"));
    }

    @Test
    public void lambdaQuacksTest() {
        /*
         * This looks a lot like duck typing to me. At the point where we send the
         * speak() message, we don't assume anything about the type of a--only that
         * it can speak(). Since animal is actually a Dog, it barks.
         */
        assertThat( animal.map(a->a.speak()).get(), is("arf!"));
    }

}