package timmax.tilegame.basemodel.protocol.client;

import java.util.ArrayList;

import timmax.tilegame.baseview.View;

public class ListOfLocalView extends ArrayList<View> {
    public View getViewByViewId(String viewId) {
        for (View view : this) {
            if (view.getViewName().equals(viewId)) {
                return view;
            }
        }
        return null;
    }
}
