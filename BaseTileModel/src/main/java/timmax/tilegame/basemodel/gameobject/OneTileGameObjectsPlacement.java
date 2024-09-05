package timmax.tilegame.basemodel.gameobject;

import timmax.tilegame.basemodel.protocol.server.GameType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//  Расположение игровых объектов (матча)
public class OneTileGameObjectsPlacement {
    //  Три параметра одинаковые для всех вариантов конструкторов:
    private final GameType gameType;
    private final WidthHeightSizes widthHeightSizes;
    private final int playerIndexOfCurrentMove;
    //  ToDo:   Сделать четыре разных конструктора, у которых был-бы различный четвертый параметр.
    //          Но можно не четыре, а хотя-бы два: 1 и 3. Но минимально и одного из четырёх будет достаточно.
    private final Map<Class<OneTileGameObject>, Set<XYCoordinates>> oneTileGameObjectClassXYCoordinatesSet_Map; // 1
    private final Map<OneTileGameObject, XYCoordinates> oneTileGameObjectXYCoordinatesMap;                      // 2
    private final Map<XYCoordinates, Set<Class<OneTileGameObject>>> xyCoordinates_OneTileGameObjectClassSet_Map;// 3
    private final Map<XYCoordinates, Set<OneTileGameObject>> xyCoordinates_OneTileGameObjectSet_Map;            // 4

    private MatchStatus matchStatus;

    private OneTileGameObjectsPlacement(
            GameType gameType,
            WidthHeightSizes widthHeightSizes,
            int playerIndexOfCurrentMove,

            //  Пример для Шахмат: (лаконичный вариант - Тип объекта -> множество координат)
            //      Ладья, Set<a1, h1>
            //      Конь, Set<b1, g1>
            //      Слон, Set<c1, f1>
            //      Ферзь, Set<d1>
            //      Король, Set<e1>
            //      Пешка, Set<a2, b2, c2, d2, e2, f2, g2, h2>
            Map<Class<OneTileGameObject>, Set<XYCoordinates>> oneTileGameObjectClassXYCoordinatesSet_Map,

            //  Пример для Шахмат: (так и даётся по шахматной нотации - Объект -> Координаты)
            //      Король1, e1
            //      Пешка1, a2
            //      Пешка2, b2
            //      Пешка3, c2
            //      Ладья1, a1
            //      Ладья2, h1
            Map<OneTileGameObject, XYCoordinates> oneTileGameObjectXYCoordinatesMap,

            //  Пример для Шахмат: (Координаты -> Множество типов объектов)
            //      a1, Set<Ладья>
            //      b1, Set<Конь>
            //      c1, Set<Слон>
            //      d1, Set<Ферзь>
            //      e1, Set<Король>
            //      f1, Set<Слон>
            //      g1, Set<Конь>
            //      h1, Set<Ладья>
            //      a2, Set<Пешка>
            //      b2, Set<Пешка>
            //      c2, Set<Пешка>
            //      d2, Set<Пешка>
            //      e2, Set<Пешка>
            //      f2, Set<Пешка>
            //      g2, Set<Пешка>
            //      h2, Set<Пешка>
            Map<XYCoordinates, Set<Class<OneTileGameObject>>> xyCoordinates_OneTileGameObjectClassSet_Map,

            //  Пример для Шахмат: (Координаты -> Множество объектов (на этих координатах))
            //      a1, <Ладья1>
            //      e1, <Король1>
            //      h1, <Ладья2>
            //      a2, <Пешка1>
            //      b2, <Пешка2>
            //      c3, <Пешка3>
            Map<XYCoordinates, Set<OneTileGameObject>> xyCoordinates_OneTileGameObjectSet_Map) {
        this.gameType = gameType;
        //  ToDo:   Нужно проверить, а соответствует-ли WidthHeightSizes и GameType.
        this.widthHeightSizes = widthHeightSizes;

        if (playerIndexOfCurrentMove < 0 || playerIndexOfCurrentMove >= gameTypeCountOfGamers()) {
            throw new RuntimeException("Wrong playerIndexOfCurrentMove.");
        }
        this.playerIndexOfCurrentMove = playerIndexOfCurrentMove;

        this.oneTileGameObjectClassXYCoordinatesSet_Map = oneTileGameObjectClassXYCoordinatesSet_Map;
        this.oneTileGameObjectXYCoordinatesMap = oneTileGameObjectXYCoordinatesMap;
        this.xyCoordinates_OneTileGameObjectClassSet_Map = xyCoordinates_OneTileGameObjectClassSet_Map;
        this.xyCoordinates_OneTileGameObjectSet_Map = xyCoordinates_OneTileGameObjectSet_Map;

        this.matchStatus = new MatchStatus0Undefined();
    }

    public OneTileGameObjectsPlacement(OneTileGameObjectsPlacement oneTileGameObjectsPlacement) {
        this(oneTileGameObjectsPlacement.gameType,
                oneTileGameObjectsPlacement.widthHeightSizes,
                oneTileGameObjectsPlacement.playerIndexOfCurrentMove,
                oneTileGameObjectsPlacement.oneTileGameObjectClassXYCoordinatesSet_Map,
                oneTileGameObjectsPlacement.oneTileGameObjectXYCoordinatesMap,
                oneTileGameObjectsPlacement.xyCoordinates_OneTileGameObjectClassSet_Map,
                oneTileGameObjectsPlacement.xyCoordinates_OneTileGameObjectSet_Map
        );
    }

    //  Реализация конструктора по варианту 2:
    public OneTileGameObjectsPlacement(
            GameType gameType,
            WidthHeightSizes widthHeightSizes,
            int playerIndexOfCurrentMove,
            Map<OneTileGameObject, XYCoordinates> oneTileGameObjectXYCoordinatesMap) {
        this(gameType, widthHeightSizes, playerIndexOfCurrentMove, new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>());

        if (oneTileGameObjectXYCoordinatesMap == null) {
            //  ToDo:   Может быть бывают игры, когда пустая доска недопустимая расстановка,
            //          тогда выход из конструктора не нужно здесь делать. Даже более того - нужно исключение делать!
            //          Например, Шахматы не предполагают расстановку, на которой нет ни одной фигуры или пешки.
            return;
        }

        //  Заполнение четырёх мап.
        for (Map.Entry<OneTileGameObject, XYCoordinates> entry : oneTileGameObjectXYCoordinatesMap.entrySet()) {
            OneTileGameObject oneTileGameObject = entry.getKey();
            XYCoordinates xyCoordinates = entry.getValue();
            //  Warning:(123, 63) Unchecked cast: 'java.lang.Class<capture<? extends timmax.tilegame.basemodel.gameobject.OneTileGameObject>>' to 'java.lang.Class<timmax.tilegame.basemodel.gameobject.OneTileGameObject>'
            Class<OneTileGameObject> oneTileGameObjectClass = (Class<OneTileGameObject>) oneTileGameObject.getClass();

            //  ToDo:   Для проверки целостности всей расстановки можно сделать предварительное заполнение 1-й мапы
            //              Map<Class<OneTileGameObject>, Set<XYCoordinates>> oneTileGameObjectClassXYСoordinatesSet_Map
            //          А именно первично заполнить всеми возможными типами объектов.
            //          Сейчас такой Set уже есть в GameType.
            //          И потом уже добавлять в Set те координаты, которые поступили в конструкторе,
            //          даже если их изначально не подали в конструктор.
            //          Например для Шахмат:
            //              - король должен быть у каждого из игроков.
            //              - Например подают такую мапу (по 1-му варианту):
            //                  Конь, Set<b1, g1>
            //                  Слон, Set<c1, f1>
            //                  Пешка, Set<a2, b2>
            //                Создаём пустую:
            //                  Ладья, Set<>
            //                  Конь, Set<>
            //                  Слон, Set<>
            //                  Ферзь, Set<>
            //                  Король, Set<>
            //                  Пешка, Set<>
            //                Дописываем её координатами из того, что пришло в конструкторе:
            //                  Ладья, Set<>
            //                  Конь, Set<b1, g1>
            //                  Слон, Set<c1, f1>
            //                  Ферзь, Set<>
            //                  Король, Set<>
            //                  Пешка, Set<a2, b2>
            //                Тогда становится видно, что короля-то и нет - возбудить исключение.

            //  1   this.oneTileGameObjectClassXYСoordinatesSet_Map
            //  ToDo:   Проверить, что строка сразу под комментом, работает также, как и то, что закомментировано.
            //          И удалить.
/*
            Set<XYCoordinates> xyCoordinatesSet = this.oneTileGameObjectClassXYCoordinatesSet_Map.get(oneTileGameObjectClass);
            if (xyCoordinatesSet == null) {
                xyCoordinatesSet = new HashSet<>();
                this.oneTileGameObjectClassXYCoordinatesSet_Map.put(oneTileGameObjectClass, xyCoordinatesSet);
            }
*/
            Set<XYCoordinates> xyCoordinatesSet = this.oneTileGameObjectClassXYCoordinatesSet_Map.computeIfAbsent(oneTileGameObjectClass, k -> new HashSet<>());

            xyCoordinatesSet.add(xyCoordinates);

            //  2   this.oneTileGameObjectXYСoordinatesMap
            this.oneTileGameObjectXYCoordinatesMap.put(oneTileGameObject, xyCoordinates);

            //  3   this.xyСoordinates_OneTileGameObjectClassSet_Map
            //  ToDo:   Проверить, что строка сразу под комментом, работает также, как и то, что закомментировано.
            //          И удалить.
/*
            Set<Class<OneTileGameObject>> oneTileGameObjectClassSet = this.xyCoordinates_OneTileGameObjectClassSet_Map.get(xyCoordinates);
            if (oneTileGameObjectClassSet == null) {
                oneTileGameObjectClassSet = new HashSet<>();
                this.xyCoordinates_OneTileGameObjectClassSet_Map.put(xyCoordinates, oneTileGameObjectClassSet);
            }
*/
            Set<Class<OneTileGameObject>> oneTileGameObjectClassSet = this.xyCoordinates_OneTileGameObjectClassSet_Map.computeIfAbsent(xyCoordinates, k -> new HashSet<>());

            oneTileGameObjectClassSet.add(oneTileGameObjectClass);

            //  4   this.xyСoordinates_OneTileGameObjectSet_Map
            //  ToDo:   Проверить, что строка сразу под комментом, работает также, как и то, что закомментировано.
            //          И удалить.
/*
            Set<OneTileGameObject> oneTileGameObjectSet = this.xyCoordinates_OneTileGameObjectSet_Map.get(xyCoordinates);
            if (oneTileGameObjectSet == null) {
                oneTileGameObjectSet = new HashSet<>();
                this.xyCoordinates_OneTileGameObjectSet_Map.put(xyCoordinates, oneTileGameObjectSet);
            }
*/
            Set<OneTileGameObject> oneTileGameObjectSet = this.xyCoordinates_OneTileGameObjectSet_Map.computeIfAbsent(xyCoordinates, k -> new HashSet<>());

            oneTileGameObjectSet.add(oneTileGameObject);
        }

        for (Map.Entry<Class<OneTileGameObject>, Set<XYCoordinates>> entry : this.oneTileGameObjectClassXYCoordinatesSet_Map.entrySet()) {
            Class<OneTileGameObject> oneTileGameObjectClass = entry.getKey();
            Set<XYCoordinates> xyCoordinatesSet = entry.getValue();
            verifyOneTileGameObjectClass_XYCoordinatesSet(oneTileGameObjectClass, xyCoordinatesSet);
        }

        for (Map.Entry<XYCoordinates, Set<Class<OneTileGameObject>>> entry : this.xyCoordinates_OneTileGameObjectClassSet_Map.entrySet()) {
            XYCoordinates xyCoordinates = entry.getKey();
            Set<Class<OneTileGameObject>> oneTileGameObjectClassSet = entry.getValue();
            verifyXYCoordinatesSet_OneTileGameObjectClassSet(xyCoordinates, oneTileGameObjectClassSet);
        }

        //  ToDo:   Нужно реализовать проверку на:
        //          - полный обзор всех объектов, относительно других объектов,
        //          - учитывать индекс игрока, чей ход следующий.
        //  ToDo:   Не забыть и проверку на возможно обязательное наличие каких-либо типов объектов - особенно,
        //          если в расстановке их нет.
        matchStatus = verifyAllGameObjects();
    }


    //  ToDo:   Вероятно пробовать каждую из вариантов мап сделать отдельным классом и тогда уже делать конструкторы
    //          с другими вариантами.
    //          Реализация конструктора по варианту 4:
    //          В такой сигнатуре не получается объявить, т.к. отличие только в четвёртом параматре,
    //          а во всех вариантах там мапа и компилятор не считает такие сигнатуры разными.
    /*
        public OneTileGameObjectsPlacement(
                GameType gameType,
                WidthHeightSizes widthHeightSizes,
                int playerIndexOfCurrentMove,
                Map<XYCoordinates, Set<OneTileGameObject>> xyСoordinates_OneTileGameObjectSet_Map) {
            this(gameType, widthHeightSizes, playerIndexOfCurrentMove, new HashMap<>(), new HashMap<>(), new HashMap<>());

            if (xyСoordinates_OneTileGameObjectSet_Map == null) {
                return;
            }

            for (Map.Entry<XYCoordinates, Set<OneTileGameObject>> entry : xyСoordinates_OneTileGameObjectSet_Map.entrySet()) {
                XYCoordinates xyСoordinates = entry.getKey();
                Set<OneTileGameObject> oneTileGameObjectSet = entry.getValue();


            }
            // ...
        }
    */

    protected GameType getGameType() {
        return gameType;
    }

    protected int getPlayerIndexOfCurrentMove() {
        return playerIndexOfCurrentMove;
    }

    protected MatchStatus getMatchStatus() {
        return matchStatus;
    }

    protected void setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
    }

    public int gameTypeCountOfGamers() {
        return gameType.getCountOfGamers();
    }

    public WidthHeightSizes getWidthHeightSizes() {
        return widthHeightSizes;
    }

    //  Применить один игровой ход:
    //  - проверить, что статус == MatchStatus1Running, если нет создать исключение, если да, то:
    //  - внести изменения в расстановку,
    //  - рассчитать статус и выдать его.
    protected MatchStatus applyGameMove(GameMove gameMove) {
        if (!(matchStatus instanceof MatchStatus1Running)) {
            throw new RuntimeException("Status is " + matchStatus + ". It is impossible to apply game move.");
        }
        return applyGameMove_(gameMove);
    }

    protected MatchStatus applyGameMove_(GameMove gameMove) {
        return new MatchStatus0Undefined();
    }

    protected void moveOneTileGameObject(OneTileGameObject oneTileGameObject, XYCoordinates xyCoordinatesCurrent, XYCoordinates xyCoordinatesNew) {
    }

    protected void deleteOneTileGameObject(OneTileGameObject oneTileGameObject, XYCoordinates xyCoordinatesCurrent) {
    }

    protected void createOneTileGameObject(OneTileGameObject oneTileGameObject, XYCoordinates xyCoordinatesNew) {
    }

    protected void transformOneTileGameObject(OneTileGameObject oneTileGameObject, XYCoordinates xyCoordinatesCurrent, XYCoordinates xyCoordinatesNew) {
    }


    //  Расстановка всех элементов должна удовлетворять правилам типа игры.

    //      - ограниечение на наличие/количество/взаимное расположение объектов определённого типа как
    //        на всей доске, так и на определённых координатах.
    //        Например, для Шахмат не может быть, что-бы:
    //          - хотя-бы у одной из сторон не было-бы короля или королей было-бы несколько,
    protected void verifyOneTileGameObjectClass_XYCoordinatesSet(Class<OneTileGameObject> oneTileGameObjectClass, Set<XYCoordinates> xyCoordinatesSet) {
    }

    //      - ограничение на перечень типов объектов, которые могут находиться на одних координатах.
    //          Например, для Шахмат не может быть, что-бы:
    //              - на одной плитке был бы больше чем один объект.
    //          Например, для Сокобан не может быть, что-бы:
    //              - на одной клетке был-бы:
    //                  - стена и стена,
    //                  - стена и дом,
    //                  - стена и ящик,
    //                  - стена и игрок,
    //                  - дом и дом,
    //                  - ящик и ящик,
    //                  - ящик и игрок,
    //                  - игрок и игрок.
    protected void verifyXYCoordinatesSet_OneTileGameObjectClassSet(XYCoordinates xyCoordinates, Set<Class<OneTileGameObject>> oneTileGameObjectClassSet) {
    }

    //      - ограничения на взаимное расположение всех объектов:
    //          Например, для Шахмат не может быть, что-бы:
    //              - короли обоих противников были-бы одновременно под боем.
    //          И в т.ч. нужно вычислить статус матча и вернуть его.
    protected MatchStatus verifyAllGameObjects() {
        return new MatchStatus0Undefined();
    }
}
