package timmax.tilegame.baseview;

import java.lang.reflect.Constructor;

public abstract class ViewClass {
    protected final Class<? extends View> viewClass;

    public ViewClass(Class<? extends View> viewClass) {
        this.viewClass = viewClass;
    }

    // Что-то похожее на фабричный метод - т.е.:
    // - на вход подаём базовый класс выборки,
    // - по этому классу определяем класс выборки, реализованной в JFX,
    // - для этой выборки определяем конструктор.
    protected abstract Constructor<? extends View> getViewConstructor();
}
