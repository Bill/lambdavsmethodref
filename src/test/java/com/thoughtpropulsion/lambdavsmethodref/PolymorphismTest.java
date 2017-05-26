package com.thoughtpropulsion.lambdavsmethodref;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class PolymorphismTest {

    private interface Speaker {
        String speak();
    }

    private static class AdmiralStockdale implements Speaker {
        public String speak() {return "gridlock!";}
    }

    private Optional<Speaker> speaker;

    @Before
    public void setup() {
        speaker = Optional.of(new AdmiralStockdale());
    }

    @Test
    public void interfaceMethodRefIsPolymorphicTest() {
        /*
         * We don't know what kind of speaker we have so we use a reference to the
         * interface method. Since it's actually AdmiralStockdale we get "gridlock!".
         */
        assertThat( speaker.map(Speaker::speak).get(), is("gridlock!"));
    }

    @Test
    public void lambdaQuacksTest() {
        /*
         * This looks a lot like duck typing to me. At the point where we send the
         * speak() message, we don't assume anything about the type of s--only that
         * it can speak(). Since it's actually AdmiralStockdale we get "gridlock!".
         */
        assertThat( speaker.map(s->s.speak()).get(), is("gridlock!"));
    }

}