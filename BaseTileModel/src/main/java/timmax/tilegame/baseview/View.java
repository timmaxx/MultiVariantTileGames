package timmax.tilegame.baseview;

import timmax.tilegame.basemodel.gameevent.GameEvent;

public interface View {
    String getViewName();
    void update(GameEvent gameEvent);
}
