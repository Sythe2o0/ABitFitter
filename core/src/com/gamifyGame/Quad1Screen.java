package com.gamifyGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.gamifyGame.Corner;
import com.gamifyGame.GamifyScreen;
import com.gamifyGame.gamifyGame;
import com.gamifyGame.renderHelper;

import java.util.HashMap;

/**
 * Created by Stephen on 2/1/2015.
 */
public class Quad1Screen extends GamifyScreen implements Screen {

    GamifyGraph[] testGraphs;

    public Quad1Screen(gamifyGame game) {
        super(game);
    }

    public void render(float delta) {
        super.render(delta);
        renderHelper.getRenderHelper().moveCorner(this.retBox, Corner.LOWER_LEFT, 30);

        int i = game.getPrefs().getInteger("currentScreen1Graph",0) % 2;
        if (i == -1){i = 1; game.getPrefs().putInteger("currentScreen1Graph",1);}

        renderHelper.getRenderHelper().getLayer(1).draw();
        testGraphs[i].shapeRender();
        renderHelper.getRenderHelper().getBatch().begin();


        testGraphs[i].textRender();
        renderHelper.getRenderHelper().getBatch().end();
        renderHelper.getRenderHelper().endRender();
        renderHelper.getRenderHelper().getLayer(2).draw();
    }

    public void show() {
        renderHelper renderer = renderHelper.getRenderHelper();
        retBox = renderer.imageSetupCenter("stepBox.png", renderer.getLayer(1), 37, 50);
        Image leftBox = renderer.imageSetup("arrowBoxLeft.png", renderer.getLayer(1),132,0);
        Image rightBox = renderer.imageSetup("arrowBoxRight.png", renderer.getLayer(1),156,0);

        retBox.addListener(new GoScreenClickListener(game.mainS, game));
        leftBox.addListener(game.getListenerHelper().setInt("currentScreen1Graph","--"));
        rightBox.addListener(game.getListenerHelper().setInt("currentScreen1Graph","++"));

        testGraphs = new GamifyGraph[2];
        renderer.imageSetup("largeScreenBox.png", renderer.getLayer(1), 36, 42);

        HashMap<Long,Integer> testData = new HashMap<Long,Integer>();
        testData.put(System.currentTimeMillis()-240000000,40);
        testData.put(System.currentTimeMillis()-190000000,45);
        testData.put(System.currentTimeMillis()-180000000,5);
        testData.put(System.currentTimeMillis()-170000000,70);
        testData.put(System.currentTimeMillis()-160000000,5);
        testData.put(System.currentTimeMillis()-150000000,90);
        testData.put(System.currentTimeMillis()-100000000,4);
        testData.put(System.currentTimeMillis()-90000000,15);
        testData.put(System.currentTimeMillis()-80000000,20);
        testData.put(System.currentTimeMillis()-70000000,64);
        testData.put(System.currentTimeMillis()-60000000,92);
        testData.put(System.currentTimeMillis()-50000000,5);
        testData.put(System.currentTimeMillis()-40000000,3);
        testData.put(System.currentTimeMillis()-10000000,9);
        testData.put(System.currentTimeMillis()-9000000,59);
        testData.put(System.currentTimeMillis()-8000000,20);
        testData.put(System.currentTimeMillis()-7000000,68);
        testData.put(System.currentTimeMillis()-6000000,60);
        testData.put(System.currentTimeMillis()-5000000,30);
        testData.put(System.currentTimeMillis()-4000000,42);
        testData.put(System.currentTimeMillis()-2000000,40);
        testData.put(System.currentTimeMillis()-1900000,45);
        testData.put(System.currentTimeMillis()-1800000,40);
        testData.put(System.currentTimeMillis()-1700000,68);
        testData.put(System.currentTimeMillis()-1600000,80);
        testData.put(System.currentTimeMillis()-1500000,34);
        testData.put(System.currentTimeMillis()-1400000,5);
        testData.put(System.currentTimeMillis()-1000000,40);
        testData.put(System.currentTimeMillis()-90000,45);
        testData.put(System.currentTimeMillis()-80000,0);
        testData.put(System.currentTimeMillis()-70000,68);
        testData.put(System.currentTimeMillis()-60000,70);
        testData.put(System.currentTimeMillis()-50000,34);
        testData.put(System.currentTimeMillis()-40000,78);
        testGraphs[0] = new LineGraph(testData,"Vitality Gained",GamifyColor.GREEN,36,42);

        HashMap<Integer,Integer> spiderData = new HashMap<Integer,Integer>();
        spiderData.put(0,50);
        spiderData.put(1,22);
        spiderData.put(2,0);
        spiderData.put(3,33);
        spiderData.put(4,46);
        spiderData.put(5,64);
        String[] labels = {"Percent Time Active", "Percent Time Excercising", "Vitamin Intake", "Percent Daily Values Reached", " Percent Days Well-Slept", "Percent Challenges Completed"};
        testGraphs[1] = new SpiderGraph(spiderData,labels,"Activity Distribution (this week)",GamifyColor.YELLOW, 38, 54);
    }


}
