package Sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestGenerics2 {
    public static void main(String[] args) {
        new TestGenerics2().go();
    }

    public void go() {
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new Dog());
        animals.add(new Cat());
        animals.add(new Dog());
        takeAnimals(animals);

        ArrayList<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog());
        dogs.add(new Dog());
        takeAnimals(dogs);

        //ArrayList<Dog> dogs1 = new ArrayList<Animal>();
        //ArrayList<Animal> animals1 = new ArrayList<Dog>();
        List <Animal> list = new ArrayList<Animal>();
        ArrayList<Dog> dogs1 = new ArrayList<Dog>();
        //animals = dogs;
        List<Dog> dogList = dogs;
        ArrayList<Object> objects = new ArrayList<Object>();
        List<Object> objectList = objects;
//        ArrayList<Object> objects1 = new ArrayList<Dog>();
    }

    public void takeAnimals(ArrayList<? extends Animal> animals) {
        for (Animal animal : animals) {
            animal.eat();

        }
    }
}
