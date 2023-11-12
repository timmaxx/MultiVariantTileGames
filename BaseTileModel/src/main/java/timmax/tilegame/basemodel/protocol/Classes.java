package timmax.tilegame.basemodel.protocol;

public class Classes {
    //  Метод isInstanceOf можно использовать там, где не получается использовать оператор
    //  'goalInstance instanceof SomeClass' - где SomeClass - явное константное имя класса.
    //  на вызов
    //  'Classes.instanceOf(goalInstance, someClass)' - someClass - переменная типа Class.
    public static boolean isInstanceOf(Object value, Class<?> clazz) {
        Class<?> classOfValue = value.getClass();
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