package com.buggedmatrix.game.view.entities;

import com.buggedmatrix.game.BuggedMatrix;
import com.buggedmatrix.game.model.entities.EntityModel;
import com.buggedmatrix.game.model.entities.PlayerModel;

import java.util.HashMap;
import java.util.Map;

import static com.buggedmatrix.game.model.entities.EntityModel.ModelType.ARM;
import static com.buggedmatrix.game.model.entities.EntityModel.ModelType.CHEST;
import static com.buggedmatrix.game.model.entities.EntityModel.ModelType.HEAD;
import static com.buggedmatrix.game.model.entities.EntityModel.ModelType.LEG;
import static com.buggedmatrix.game.model.entities.EntityModel.ModelType.PLAYER;

public class ViewFactory {

    private static Map<EntityModel.ModelType, EntityView> cache =
            new HashMap<EntityModel.ModelType, EntityView>();

    public static EntityView makeView(BuggedMatrix game, EntityModel model, int image) {
        System.out.print(model.getType() + "-" + image + "-");
        if (!cache.containsKey(model.getType())) {
            if (model.getType() == CHEST) {
                cache.put(model.getType(), new MemberView(game, image));
            }
            if (model.getType() == LEG) {
                cache.put(model.getType(), new MemberView(game, image));
            }
            if (model.getType() == ARM)
                cache.put(model.getType(), new MemberView(game, image));
            if (model.getType() == HEAD)
                cache.put(model.getType(), new MemberView(game, image));
        }
        return cache.get(model.getType());
    }


}
