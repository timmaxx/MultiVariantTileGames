package timmax.tilegame.basemodel.protocol.client;

import java.util.ArrayList;

import timmax.tilegame.baseview.View;

public class ListOfLocalView extends ArrayList<View> {
    public View getViewByViewName(String viewName) {
        for (View view : this) {
            if (view.getViewName().equals(viewName)) {
                return view;
            }
        }
        return null;
    }
}
