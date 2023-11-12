package timmax.tilegame.basemodel.protocol.structs;

// import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import timmax.tilegame.basemodel.ServerBaseModel;

import java.util.HashSet;

//@JsonDeserialize(as = SetOfServerBaseModel.class)
public class SetOfServerBaseModel extends HashSet<Class <? extends ServerBaseModel>> {
}