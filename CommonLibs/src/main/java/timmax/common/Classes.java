package timmax.common;

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

/*
    public static Class<?> throwExceptionIfNotInstanceOf(Object value, Class<?> clazz) {
        if (!isInstanceOf(value, clazz)) {
            throw new ClassCastException("value = " + value + " is class of " + value.getClass() + ". But is not instance of " + clazz + ".");
        }
        return clazz.getClass().cast(value);
    }
*/

    public static void throwExceptionIfNotInstanceOf(Object value, Class<?> clazz) {
        if (!isInstanceOf(value, clazz)) {
            throw new ClassCastException("value = " + value + " is class of " + value.getClass() + ". But is not instance of " + clazz + ".");
        }
    }

}
