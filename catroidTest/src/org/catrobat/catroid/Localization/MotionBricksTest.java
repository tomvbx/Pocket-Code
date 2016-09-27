/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2016 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.catrobat.catroid.Localization;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import junit.framework.Assert;

import org.catrobat.catroid.ProjectManager;
import org.catrobat.catroid.content.Project;
import org.catrobat.catroid.content.Script;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.content.StartScript;
import org.catrobat.catroid.content.bricks.*;
import org.catrobat.catroid.ui.ScriptActivity;
import org.catrobat.catroid.uitest.util.BaseActivityInstrumentationTestCase;
import org.catrobat.catroid.uitest.util.UiTestUtils;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;


public class MotionBricksTest extends BaseActivityInstrumentationTestCase<ScriptActivity> {

    private Project project;
    List<Class<? extends Brick>> bricks = new ArrayList<Class<? extends Brick>>();

    public MotionBricksTest() {
        super(ScriptActivity.class);
        bricks.add(PlaceAtBrick.class);
        bricks.add(SetYBrick.class);
        bricks.add(SetXBrick.class);
        bricks.add(ChangeXByNBrick.class);
        bricks.add(ChangeYByNBrick.class);
        bricks.add(MoveNStepsBrick.class);
        bricks.add(TurnLeftBrick.class);
        bricks.add(TurnRightBrick.class);
        bricks.add(PointInDirectionBrick.class);
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