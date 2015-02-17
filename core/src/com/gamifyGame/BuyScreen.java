package com.gamifyGame;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;

/**
 * Created by Stephen on 2/1/2015.
 */
public class BuyScreen extends GamifyScreen implements Screen
{
    private ClickListener buildingListener;
    // ArrayList<Building> buyableBuildings;

    public BuyScreen(gamifyGame game) {
        super(game);
        buildingListener = new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ChangingImage eventImage = (ChangingImage) event.getListenerActor();
                return true;
            }
        };

    }

    @Override
    public void show() {
        //Image itemBar = renderer.imageSetup("ItemBar.png", layer1, 0, 254);
        Image placeHold = renderHelper.getRenderHelper().imageSetup("placeholder128x24.png", renderHelper.getRenderHelper().getLayer(1), 26, 8);
        placeHold.addListener(game.getListenerHelper().goScreen(0));

        // Make a new instance of the buildings that is interactable
        Json json = new Json();
        Preferences pref = game.getPrefs();
        String[] underground = json.fromJson(String[].class, pref.getString("undergroundBuildings"));
        Integer[] bridges = json.fromJson(Integer[].class, pref.getString("undergroundBridges"));

        ChangingImage[] undergroundBuild = renderHelper.getRenderHelper().makeUnderground(renderHelper.getRenderHelper().getLayer(1), underground);
        renderHelper.getRenderHelper().makeBridges(renderHelper.getRenderHelper().getLayer(1), bridges);
        addBuildingListenerToEachHandle(undergroundBuild);
        new ScrollBar(Building.getDefaultBuildings(), undergroundBuild, game);
    }


    @Override
    public void hide() {
        // called when current screen changes from this to a different screen
        //renderer.getLayer(1).removeListener(dragHandle);
        renderHelper.getRenderHelper().getLayer(0).clear();
        super.hide();
    }

    private void addBuildingListenerToEachHandle(ChangingImage[] imageHandles){
        for(int i=0; i <= imageHandles.length-1; i++){
            imageHandles[i].addListener(buildingListener);
        }
    }

}
