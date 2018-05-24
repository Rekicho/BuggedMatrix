package com.buggedmatrix.game.view.entities;

import com.buggedmatrix.game.BuggedMatrix;
import com.buggedmatrix.game.model.entities.EntityModel;

import java.util.HashMap;
import java.util.Map;

import static com.buggedmatrix.game.model.entities.EntityModel.ModelType.CHEST;
import static com.buggedmatrix.game.model.entities.EntityModel.ModelType.LEG;

public class ViewFactory {

    private static Map<EntityModel.ModelType, EntityView> cache =
            new HashMap<EntityModel.ModelType, EntityView>();

    public static EntityView makeView(BuggedMatrix game, EntityModel model) {
        if (!cache.containsKey(model.getType())) {
            if (model.getType() == CHEST) {
                cache.put(model.getType(), new MemberView(game));
            }

            else if (model.getType() == LEG) {
                cache.put(model.getType(), new MemberView(game));
            }
        }
        return cache.get(model.getType());
    }

}
