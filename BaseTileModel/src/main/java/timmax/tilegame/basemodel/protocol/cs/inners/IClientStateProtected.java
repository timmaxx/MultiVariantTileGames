package timmax.tilegame.basemodel.protocol.cs.inners;

import timmax.tilegame.basemodel.protocol.cs.IClientState;

public interface IClientStateProtected<Model> extends IClientState<Model> {
    void setAsCurrent();
}
