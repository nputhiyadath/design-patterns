package com.learning.designpatterns.observer;

import java.util.*;
import java.util.function.Consumer;

class Event<TArgs>
{
    private int count = 0;
    private Map<Integer, Consumer<TArgs>>
            handlers = new HashMap<>();

    public Subscription addHandler(Consumer<TArgs> handler)
    {
        int i = count;
        handlers.put(count++, handler);
        return new Subscription(this, i);
    }

    public void fire(TArgs args)
    {
        for (Consumer<TArgs> handler : handlers.values())
            handler.accept(args);
    }

    public class Subscription implements AutoCloseable
    {
        private Event<TArgs> event;
        private int id;

        public Subscription(Event<TArgs> event, int id)
        {
            this.event = event;
            this.id = id;
        }

        @Override
        public void close() /*throws Exception*/
        {
            event.handlers.remove(id);
        }
    }
}

class PropertyChangedEventArgsSimple
{
    public Object source;
    public String propertyName;

    public PropertyChangedEventArgsSimple(Object source, String propertyName)
    {
        this.source = source;
        this.propertyName = propertyName;
    }
}

class PersonSimple
{
    public Event<PropertyChangedEventArgsSimple>
            propertyChanged = new Event<>();

    private int age;

    public int getAge()
    {
        return age;
    }
    public void setAge(int age)
    {
        if (this.age == age) return;

        boolean oldCanVote = getCanVote();

        this.age = age;
        propertyChanged.fire(new PropertyChangedEventArgsSimple(
                this, "age"
        ));

        if (oldCanVote != getCanVote())
        {
            propertyChanged.fire(new PropertyChangedEventArgsSimple(
                    this, "canVote"
            ));
        }
    }

    public boolean getCanVote()
    {
        return age >= 18;
    }
}

class HandmadeEventsDemo
{
    public static void main(String [] args)
    {
        PersonSimple personSimple = new PersonSimple();
        Event<PropertyChangedEventArgsSimple>.Subscription sub =
                personSimple.propertyChanged.addHandler(x -> {
                    System.out.println("PersonSimple's "
                            + x.propertyName + " has changed");
                });
        personSimple.setAge(17);
        personSimple.setAge(18);
        sub.close();
        personSimple.setAge(19);
    }
}
