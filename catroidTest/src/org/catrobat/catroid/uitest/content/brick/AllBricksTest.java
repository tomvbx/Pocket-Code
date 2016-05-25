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

package org.catrobat.catroid.uitest.content.brick;

import android.util.Log;
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
import org.catrobat.catroid.formulaeditor.Formula;
import org.catrobat.catroid.ui.ScriptActivity;
import org.catrobat.catroid.ui.adapter.BrickAdapter;
import org.catrobat.catroid.uitest.util.BaseActivityInstrumentationTestCase;
import org.catrobat.catroid.uitest.util.UiTestUtils;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lazka on 5/25/16.
 */
public class AllBricksTest extends BaseActivityInstrumentationTestCase<ScriptActivity> {

	private Project project;
	List<Class<? extends Brick>> bricks = new ArrayList<Class<? extends Brick>>();

	public AllBricksTest() {
		super(ScriptActivity.class);
		bricks.add(AddItemToUserListBrick.class);
		bricks.add(ArduinoSendDigitalValueBrick.class);
		bricks.add(ArduinoSendPWMValueBrick.class);
		bricks.add(BroadcastBrick.class);
		bricks.add(BroadcastReceiverBrick.class);
		bricks.add(BroadcastWaitBrick.class);
		bricks.add(CameraBrick.class);
		bricks.add(ChangeBrightnessByNBrick.class);
		bricks.add(ChangeSizeByNBrick.class);
		bricks.add(ChangeTransparencyByNBrick.class);
		bricks.add(ChangeVariableBrick.class);
		bricks.add(ChangeVolumeByNBrick.class);
		bricks.add(ChangeXByNBrick.class);
		bricks.add(ChangeYByNBrick.class);
		bricks.add(ChooseCameraBrick.class);
		bricks.add(ClearGraphicEffectBrick.class);
		bricks.add(ComeToFrontBrick.class);
		bricks.add(DeleteItemOfUserListBrick.class);
		bricks.add(DroneBasicControlBrick.class);
		bricks.add(DroneBasicLookBrick.class);
		bricks.add(DroneEmergencyBrick.class);
		bricks.add(DroneFlipBrick.class);
		bricks.add(DroneMoveBackwardBrick.class);
		bricks.add(DroneMoveBrick.class);
		bricks.add(DroneMoveDownBrick.class);
		bricks.add(DroneMoveForwardBrick.class);
		bricks.add(DroneMoveLeftBrick.class);
		bricks.add(DroneMoveRightBrick.class);
		bricks.add(DroneMoveUpBrick.class);
		bricks.add(DronePlayLedAnimationBrick.class);
		bricks.add(DroneSpinnerBrick.class);
		bricks.add(DroneSwitchCameraBrick.class);
		bricks.add(DroneTakeOffLandBrick.class);
		bricks.add(DroneTurnLeftBrick.class);
		bricks.add(DroneTurnLeftMagnetoBrick.class);
		bricks.add(DroneTurnRightBrick.class);
		bricks.add(DroneTurnRightMagnetoBrick.class);
		bricks.add(FlashBrick.class);
		bricks.add(ForeverBrick.class);
		bricks.add(FormulaBrick.class);
		bricks.add(GlideToBrick.class);
		bricks.add(GoNStepsBackBrick.class);
		bricks.add(HideBrick.class);
		bricks.add(HideTextBrick.class);
		bricks.add(IfLogicBeginBrick.class);
		bricks.add(IfLogicElseBrick.class);
		bricks.add(IfLogicEndBrick.class);
		bricks.add(IfOnEdgeBounceBrick.class);
		bricks.add(InsertItemIntoUserListBrick.class);
		bricks.add(LegoNxtMotorMoveBrick.class);
		bricks.add(LegoNxtMotorStopBrick.class);
		bricks.add(LegoNxtMotorTurnAngleBrick.class);
		bricks.add(LegoNxtPlayToneBrick.class);
		bricks.add(LoopEndBrick.class);
		bricks.add(LoopEndlessBrick.class);
		bricks.add(MoveNStepsBrick.class);
		bricks.add(NextLookBrick.class);
		bricks.add(NoteBrick.class);
		bricks.add(PhiroIfLogicBeginBrick.class);
		bricks.add(PhiroMotorMoveBackwardBrick.class);
		bricks.add(PhiroMotorMoveForwardBrick.class);
		bricks.add(PhiroMotorStopBrick.class);
		bricks.add(PhiroPlayToneBrick.class);
		bricks.add(PhiroRGBLightBrick.class);
		bricks.add(PlaceAtBrick.class);
		bricks.add(PlaySoundBrick.class);
		bricks.add(PointInDirectionBrick.class);
		bricks.add(PointToBrick.class);
		bricks.add(RaspiIfLogicBeginBrick.class);
		bricks.add(RaspiPwmBrick.class);
		bricks.add(RaspiSendDigitalValueBrick.class);
		bricks.add(RepeatBrick.class);
		bricks.add(ReplaceItemInUserListBrick.class);
		bricks.add(ScriptBrick.class);
		bricks.add(SetBrightnessBrick.class);
		bricks.add(SetLookBrick.class);
		bricks.add(SetSizeToBrick.class);
		bricks.add(SetTextBrick.class);
		bricks.add(SetTransparencyBrick.class);
		bricks.add(SetVariableBrick.class);
		bricks.add(SetVolumeToBrick.class);
		bricks.add(SetXBrick.class);
		bricks.add(SetYBrick.class);
		bricks.add(ShowBrick.class);
		bricks.add(ShowTextBrick.class);
		bricks.add(SpeakBrick.class);
		bricks.add(StopAllSoundsBrick.class);
		bricks.add(TurnLeftBrick.class);
		bricks.add(TurnRightBrick.class);
		bricks.add(UserBrick.class);
		bricks.add(UserBrickParameter.class);
		bricks.add(UserListBrick.class);
		bricks.add(UserScriptDefinitionBrick.class);
		bricks.add(UserVariableBrick.class);
		bricks.add(VibrationBrick.class);
		bricks.add(WaitBrick.class);
		bricks.add(WhenBrick.class);
		bricks.add(WhenNfcBrick.class);
		bricks.add(WhenRaspiPinChangedBrick.class);
		bricks.add(WhenStartedBrick.class);
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
