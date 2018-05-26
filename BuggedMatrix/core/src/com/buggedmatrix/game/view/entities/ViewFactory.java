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

    public static EntityView makeView(BuggedMatrix game, EntityModel model) {
        if (!cache.containsKey(model.getType())) {
            if (model.getType() == CHEST) {
                System.out.print("OK1\n");
                cache.put(model.getType(), new MemberView(game, 4));
            }
            if (model.getType() == LEG) {
                System.out.print("OK2\n");
                cache.put(model.getType(), new MemberView(game, 1));
            }
            if (model.getType() == ARM)
                cache.put(model.getType(), new MemberView(game, 2));
            if (model.getType() == HEAD)
                cache.put(model.getType(), new MemberView(game, 3));
        }
        return cache.get(model.getType());
    }


}
