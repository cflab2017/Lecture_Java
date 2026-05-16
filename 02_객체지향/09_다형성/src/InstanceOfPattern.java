public class InstanceOfPattern {
    sealed interface Animal permits Dog, Cat, Fish {}
    record Dog(String name) implements Animal {}
    record Cat(String name) implements Animal {}
    record Fish(String name) implements Animal {}

    static String introduce(Animal a) {
        if (a instanceof Dog d) return d.name() + "는 강아지";
        if (a instanceof Cat c) return c.name() + "는 고양이";
        if (a instanceof Fish f) return f.name() + "는 물고기";
        return "?";
    }

    public static void main(String[] args) {
        Animal[] zoo = { new Dog("바둑이"), new Cat("나비"), new Fish("니모") };
        for (Animal a : zoo) System.out.println(introduce(a));
    }
}
