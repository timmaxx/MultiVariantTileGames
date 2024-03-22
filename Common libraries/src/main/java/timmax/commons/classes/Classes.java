package timmax.commons.classes;

public class Classes {
    //  Метод isInstanceOf можно использовать там, где не получается использовать оператор instanceof.
    //  Т.е. вместо:
    //  'goalInstance instanceof SomeClass'
    //  где goalInstance - экземпляр произвольного класса,
    //      SomeClass - явное константное имя класса.
    //  меняем на вызов:
    //  'Classes.instanceOf(goalInstance, someClass)'
    //  где goalInstance - экземпляр произвольного класса,
    //      someClass - переменная типа Class.
    public static boolean isInstanceOf(Object value, Class<?> clazz) {
        Class<?> classOfValue = value.getClass();
        if (classOfValue.equals(java.lang.Class.class)) {
            classOfValue = (Class<?>) value;
        }
        while (!classOfValue.equals(Object.class)) {
            if (classOfValue.equals(clazz)) {
                return true;
            } else {
                classOfValue = classOfValue.getSuperclass();
            }
        }
        return false;
    }
}