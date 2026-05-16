public class SwitchPattern {
    sealed interface Pet permits DogPet, CatPet {}
    record DogPet(String name, int age) implements Pet {}
    record CatPet(String name) implements Pet {}

    static String describe(Pet p) {
        return switch (p) {
            case DogPet d -> d.name() + "(" + d.age() + "살)";
            case CatPet c -> c.name();
        };
    }

    public static void main(String[] args) {
        System.out.println(describe(new DogPet("바둑이", 3)));
        System.out.println(describe(new CatPet("나비")));
    }
}
