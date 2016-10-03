package org.catrobat.catroid.Localization.SoundBricks;

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
import org.catrobat.catroid.content.bricks.ChangeVolumeByNBrick;
import org.catrobat.catroid.content.bricks.ForeverBrick;
import org.catrobat.catroid.content.bricks.IfLogicBeginBrick;
import org.catrobat.catroid.content.bricks.LoopEndBrick;
import org.catrobat.catroid.content.bricks.LoopEndlessBrick;
import org.catrobat.catroid.content.bricks.NoteBrick;
import org.catrobat.catroid.content.bricks.PlaySoundBrick;
import org.catrobat.catroid.content.bricks.RepeatBrick;
import org.catrobat.catroid.content.bricks.SetVolumeToBrick;
import org.catrobat.catroid.content.bricks.SpeakBrick;
import org.catrobat.catroid.content.bricks.StopAllSoundsBrick;
import org.catrobat.catroid.content.bricks.WaitBrick;
import org.catrobat.catroid.content.bricks.WhenBrick;
import org.catrobat.catroid.content.bricks.WhenStartedBrick;
import org.catrobat.catroid.ui.ScriptActivity;
import org.catrobat.catroid.uitest.util.BaseActivityInstrumentationTestCase;
import org.catrobat.catroid.uitest.util.UiTestUtils;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aiman Awwad on 9/27/2016.
 */
public class SoundBricksMirroringTest extends BaseActivityInstrumentationTestCase<ScriptActivity> {

    private Project project;
    List<Class<? extends Brick>> bricks = new ArrayList<Class<? extends Brick>>();

    public SoundBricksMirroringTest() {
        super(ScriptActivity.class);
        bricks.add(ChangeVolumeByNBrick.class);
        bricks.add(SetVolumeToBrick.class);
        bricks.add(SpeakBrick.class);
        bricks.add(StopAllSoundsBrick.class);

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