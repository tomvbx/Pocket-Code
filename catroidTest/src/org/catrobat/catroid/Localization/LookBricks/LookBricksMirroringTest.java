package org.catrobat.catroid.Localization.LookBricks;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import junit.framework.Assert;

import org.catrobat.catroid.ProjectManager;
import org.catrobat.catroid.content.Project;
import org.catrobat.catroid.content.Script;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.content.StartScript;
import org.catrobat.catroid.content.bricks.Brick;
import org.catrobat.catroid.content.bricks.ChangeBrightnessByNBrick;
import org.catrobat.catroid.content.bricks.ChangeSizeByNBrick;
import org.catrobat.catroid.content.bricks.ChangeTransparencyByNBrick;
import org.catrobat.catroid.content.bricks.ClearGraphicEffectBrick;
import org.catrobat.catroid.content.bricks.HideBrick;
import org.catrobat.catroid.content.bricks.SetBrightnessBrick;
import org.catrobat.catroid.content.bricks.SetTransparencyBrick;
import org.catrobat.catroid.content.bricks.ShowBrick;
import org.catrobat.catroid.ui.ScriptActivity;
import org.catrobat.catroid.uitest.util.BaseActivityInstrumentationTestCase;
import org.catrobat.catroid.uitest.util.UiTestUtils;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aiman Awwad on 9/27/2016.
 */
public class LookBricksMirroringTest extends BaseActivityInstrumentationTestCase<ScriptActivity> {

    private Project project;
    List<Class<? extends Brick>> bricks = new ArrayList<Class<? extends Brick>>();

    public LookBricksMirroringTest() {
        super(ScriptActivity.class);
        bricks.add(ChangeBrightnessByNBrick.class);
        bricks.add(ChangeTransparencyByNBrick.class);
        bricks.add(ClearGraphicEffectBrick.class);
        bricks.add(SetBrightnessBrick.class);
        bricks.add(SetTransparencyBrick.class);
        bricks.add(ChangeSizeByNBrick.class);
        bricks.add(HideBrick.class);
        bricks.add(ShowBrick.class);
        bricks.add(ChangeSizeByNBrick.class);
        bricks.add(ShowBrick.class);
    }

    @Override
    public void setUp() throws Exception {
        createProject();
        super.setUp();
    }

    public List<Brick> getBrickInstances() {
        List<Brick> list = new ArrayList<Brick>();
        for (Class type: bricks)
        {
            if (Modifier.isAbstract(type.getModifiers()))
                continue;

            Brick brick = null;
            try {
                brick = (Brick)type.newInstance();
            } catch (InstantiationException e) {
                Assert.fail("Can't instantiate: " + type.toString());
            } catch (IllegalAccessException e) {
                Assert.fail();
            }
            if (brick != null)
                list.add(brick);
        }
        return list;
    }

    public void testMirrored() throws IllegalAccessException, InstantiationException {
        ScriptActivity act = getActivity();
        BaseAdapter adapter = act.getScriptFragment().getAdapter();

        for (Brick brick: getBrickInstances())
        {
            ViewGroup view;
            view = (ViewGroup)brick.getView(act, 42, adapter);
            Assert.assertNotNull(brick.toString(), view);
            for(int index=0; index<view.getChildCount(); ++index) {
                View nextChild = view.getChildAt(index);
                if (nextChild.getBackground() != null)
                    Assert.assertTrue(brick.toString(), nextChild.getBackground().isAutoMirrored());
            }

            view = (ViewGroup)brick.getViewWithAlpha(0);
            if (view != null) {
                for (int index = 0; index < view.getChildCount(); ++index) {
                    View nextChild = view.getChildAt(index);
                    if (nextChild.getBackground() != null)
                        Assert.assertTrue(brick.toString(), nextChild.getBackground().isAutoMirrored());
                }
            }
        }
    }

    private void createProject() {
        project = new Project(null, UiTestUtils.DEFAULT_TEST_PROJECT_NAME);
        Sprite sprite = new Sprite("cat");
        Script script = new StartScript();
        sprite.addScript(script);
        project.addSprite(sprite);
        ProjectManager.getInstance().setProject(project);
        ProjectManager.getInstance().setCurrentSprite(sprite);
        ProjectManager.getInstance().setCurrentScript(script);
    }
}