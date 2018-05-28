package com.buggedmatrix.game.view.entities;

import com.buggedmatrix.game.BuggedMatrix;
import com.buggedmatrix.game.model.entities.EntityModel;
import com.buggedmatrix.game.model.entities.PlayerModel;

import java.util.HashMap;
import java.util.Map;

import static com.buggedmatrix.game.model.entities.EntityModel.ModelType;

public class ViewFactory {

    private static Map<EntityModel.ModelType, EntityView> cache =
            new HashMap<EntityModel.ModelType, EntityView>();

    public static EntityView makeView(BuggedMatrix game, EntityModel model) {
        if (!cache.containsKey(model.getType())) {
            if (model.getType() == ModelType.CHEST1)
                cache.put(model.getType(), new MemberView(game,4));
            else if (model.getType() == ModelType.LEG1)
                cache.put(model.getType(), new MemberView(game,1));
            else if (model.getType() == ModelType.ARM1)
                cache.put(model.getType(), new MemberView(game,2));
            else if (model.getType() == ModelType.HEAD1)
                cache.put(model.getType(), new MemberView(game, 3));
            else if (model.getType() == ModelType.CHEST2)
                cache.put(model.getType(), new MemberView(game, 5));
            else if (model.getType() == ModelType.LEG2)
                cache.put(model.getType(), new MemberView(game, 6));
            else if (model.getType() == ModelType.ARM2)
                cache.put(model.getType(), new MemberView(game, 7));
            else if (model.getType() == ModelType.HEAD2)
                cache.put(model.getType(), new MemberView(game, 8));
            else if (model.getType() == ModelType.BULLET)
                cache.put(model.getType(), new BulletView(game));
        }
        return cache.get(model.getType());
    }


}
