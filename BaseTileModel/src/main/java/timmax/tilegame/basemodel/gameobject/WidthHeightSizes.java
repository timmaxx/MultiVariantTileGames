package timmax.tilegame.basemodel.gameobject;

//  Ширина и высота
public record WidthHeightSizes(int width, int height) {
    //  ToDo:   Вероятно эти переменные лучше переместить в GameType - что-бы у разных типов игр
    //          было можно по разному выставлять ограничения на размеры досок.
    //  ToDo:   Также рассмотреть и возможность хранения для типа игры перечня допустимых размеров.
    //              Например, для Го есть три основных варианта размеров (из википедии):
    //              Стандартная доска имеет разлиновку 19×19 линий. Для обучения и коротких неофициальных игр могут
    //              применяться доски меньших размеров: обычно 13×13 или 9×9 линий, намного реже — 11×11, 15×15, 17×17
    //              линий, но, теоретически, ничто не мешает использовать произвольную прямоугольную доску.
    //              На интернет-серверах иногда играют на досках нестандартного, в том числе намного большего
    //              размера (например, 37×37 линий).
    public final static WidthHeightSizes MIN_WIDTH_Height_SIZES = new WidthHeightSizes(1, 1);
    public final static WidthHeightSizes MAX_WIDTH_Height_SIZES = new WidthHeightSizes(20, 20);

    public WidthHeightSizes {
        if (width < MIN_WIDTH_Height_SIZES.width() ||
                width > MAX_WIDTH_Height_SIZES.width() ||
                height < MIN_WIDTH_Height_SIZES.height() ||
                height > MAX_WIDTH_Height_SIZES.height()) {
            throw new RuntimeException("Wrong width and/or height. You cannot set them into (width < MIN_WIDTH || width > MAX_WIDTH || height < MIN_HEIGHT || height > MAX_HEIGHT).");
        }
    }
}
