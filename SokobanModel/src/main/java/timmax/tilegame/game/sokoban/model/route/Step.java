package timmax.tilegame.game.sokoban.model.route;

import timmax.tilegame.basemodel.gameobject.XYOffset;

public class Step {
    private final XYOffset xyOffset;
    private final boolean isBoxMoved;

    public Step(XYOffset xyOffset, boolean isBoxMoved) {
        this.xyOffset = xyOffset;
        this.isBoxMoved = isBoxMoved;
    }

    public Step getOpposite() {
        return new Step(xyOffset.getOpposite(), isBoxMoved);
    }

    public XYOffset getOppositeXYOffset() {
        return getOpposite().xyOffset;
    }

    public boolean isBoxMoved() {
        return isBoxMoved;
    }

    public XYOffset getXyOffset() {
        return xyOffset;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Step step)) {
            return false;
        }

        return this.isBoxMoved == step.isBoxMoved && xyOffset.equals(step.xyOffset);
    }

    @Override
    public String toString() {
        return "Step{ " +
                "XYOffset = " + xyOffset +
                ", isBoxMoved = " + isBoxMoved +
                "}";
    }
}
