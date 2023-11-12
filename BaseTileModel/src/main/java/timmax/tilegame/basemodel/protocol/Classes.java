package timmax.tilegame.basemodel.protocol;

public class Classes {
    //  Метод isInstanceOf можно использовать там, где не получается использовать оператор
    //  'goalInstance instanceof SomeClass' - где SomeClass - явное имя класса.
    //  на вызов
    //  'Classes.instanceOf(goalInstance, someClass)' - someClass - переменная типа Class.
    public static boolean isInstanceOf(Object object, Class<?> clazz) {
/*
        System.out.println("object = " + object);
        System.out.println("clazz = " + clazz);
*/
        Class<?> fThis = object.getClass();
//      int i = 0;
        while (!fThis.equals(Object.class) ) {
/*
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            i++;
            System.out.println( "fThis = " + fThis);
*/
            if (fThis.equals(clazz)) {
                return true;
            } else {
                fThis = fThis.getSuperclass();
            }

        }
        return false;
    }
}