package org.catrobat.catroid.test.RtL;

import android.view.View;

import com.robotium.solo.Condition;

import org.catrobat.catroid.ProjectManager;
import org.catrobat.catroid.R;
import org.catrobat.catroid.common.SoundInfo;
import org.catrobat.catroid.content.Project;
import org.catrobat.catroid.content.Script;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.content.StartScript;
import org.catrobat.catroid.content.bricks.HideBrick;
import org.catrobat.catroid.content.bricks.PlaceAtBrick;
import org.catrobat.catroid.content.bricks.PlaySoundBrick;
import org.catrobat.catroid.content.bricks.SetSizeToBrick;
import org.catrobat.catroid.ui.ScriptActivity;
import org.catrobat.catroid.uitest.util.BaseActivityInstrumentationTestCase;
import org.catrobat.catroid.uitest.util.UiTestUtils;

public class FormulaEditorUndoRedoRtLLangTest extends BaseActivityInstrumentationTestCase<ScriptActivity> {

    private Project project;
    private PlaceAtBrick placeAtBrick;

    public FormulaEditorUndoRedoRtLLangTest() {
        super(ScriptActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        // normally super.setUp should be called first
        // but kept the test failing due to view is null
        // when starting in ScriptActivity
        createProject();
        super.setUp();
    }

    public void testUndoAndRedoButton() {
        solo.clickOnView(solo.getView(R.id.brick_place_at_edit_text_x));
        solo.clickOnView(solo.getView(R.id.formula_editor_keyboard_5));
        View undo = solo.getView(R.id.menu_undo);
        solo.waitForCondition(new Condition() {
            @Override
            public boolean isSatisfied() {
                return solo.getView(R.id.menu_undo).isEnabled();
            }
        }, 100);
        solo.clickOnText("206");
        solo.waitForCondition(new Condition() {
            @Override
            public boolean isSatisfied() {
                return solo.getView(R.id.menu_undo).isClickable();
            }
        }, 100);
        assertFalse("Undo button should be disabled", undo.isEnabled());

        solo.clickOnView(solo.getView(R.id.formula_editor_keyboard_6));
        solo.clickOnActionBarItem(R.id.menu_undo);
        View redo = solo.getView(R.id.menu_redo);
        solo.waitForCondition(new Condition() {
            @Override
            public boolean isSatisfied() {
                return solo.getView(R.id.menu_redo).isEnabled();
            }
        }, 100);
        assertTrue("Redo button should be enabled", redo.isEnabled());

        solo.clickOnView(solo.getView(R.id.formula_editor_keyboard_5));
        solo.waitForCondition(new Condition() {
            @Override
            public boolean isSatisfied() {
                return solo.getView(R.id.menu_redo).isClickable();
            }
        }, 100);
        assertFalse("Redo button should be disabled", redo.isEnabled());
    }

    private void createProject() {
        project = new Project(null, UiTestUtils.DEFAULT_TEST_PROJECT_NAME);
        Sprite sprite = new Sprite("cat");
        Script script = new StartScript();
        script.addBrick(new HideBrick());
        placeAtBrick = new PlaceAtBrick(105, 206);
        script.addBrick(placeAtBrick);
        PlaySoundBrick soundBrick = new PlaySoundBrick();
        SoundInfo soundInfo = new SoundInfo();
        soundInfo.setSoundFileName("sound.mp3");
        soundInfo.setTitle("sound.mp3");
        soundBrick.setSoundInfo(soundInfo);
        script.addBrick(soundBrick);

        script.addBrick(new SetSizeToBrick(80));

        sprite.addScript(script);
        project.addSprite(sprite);

        ProjectManager.getInstance().setProject(project);
        ProjectManager.getInstance().setCurrentSprite(sprite);
        ProjectManager.getInstance().setCurrentScript(script);
    }
}
