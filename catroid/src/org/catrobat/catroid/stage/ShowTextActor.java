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

package org.catrobat.catroid.stage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import org.catrobat.catroid.ProjectManager;
import org.catrobat.catroid.common.Constants;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.formulaeditor.DataContainer;
import org.catrobat.catroid.formulaeditor.UserVariable;

import java.util.List;
import java.util.Map;

public class ShowTextActor extends Actor {
	private int posX;
	private int posY;
	private String variableName;
	private String linkedVariableName;
	private float scale = 3f;
	String variableValue;

	public ShowTextActor(String text, int x, int y) {
		this.variableName = text;
		this.posX = x;
		this.posY = y;
		this.linkedVariableName = variableName;
	}

	private void drawText(Batch batch, String text) {
		// Convert to bitmap
		Paint paint = new Paint();
		paint.setTextSize(16 * scale);
		paint.setARGB(0xff, 0, 0, 0);
		paint.setAntiAlias(true);
		paint.setTextAlign(Paint.Align.LEFT);
		float baseline = -paint.ascent();
		int width = (int) (paint.measureText(text) + 0.5f);
		int height = (int) (baseline + paint.descent() + 0.5f);
		Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		canvas.drawText(text, 0, baseline, paint);

		// Convert to texture, draw and dispose
		Texture tex = new Texture(bitmap.getWidth(), bitmap.getHeight(), Pixmap.Format.RGBA8888);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex.getTextureObjectHandle());
		GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
		bitmap.recycle();
		batch.draw(tex, posX, posY);
		batch.flush();
		tex.dispose();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		DataContainer projectVariableContainer = ProjectManager.getInstance().getCurrentProject().getDataContainer();
		List<UserVariable> projectVariableList = projectVariableContainer.getProjectVariables();

		Map<Sprite, List<UserVariable>> spriteVariableMap = projectVariableContainer.getSpriteVariableMap();
		Sprite currentSprite = ProjectManager.getInstance().getCurrentSprite();
		List<UserVariable> spriteVariableList = spriteVariableMap.get(currentSprite);

		if (variableName.equals(Constants.NO_VARIABLE_SELECTED)) {
			drawText(batch, variableValue);
		} else {
			for (UserVariable variable : projectVariableList) {
				if (variable.getName().equals(variableName)) {
					variableValue = variable.getValue().toString();
					if (variable.getVisible()) {
						drawText(batch, variableValue);
					}
					break;
				}
			}
			if (spriteVariableList != null) {
				for (UserVariable variable : spriteVariableList) {
					if (variable.getName().equals(variableName)) {
						variableValue = variable.getValue().toString();
						if (variable.getVisible()) {
							drawText(batch, variableValue);
						}
						break;
					}
				}
			}
		}
	}

	public void setX(int x) {
		this.posX = x;
	}

	public void setY(int y) {
		this.posY = y;
	}

	public String getLinkedVariableName() {
		return linkedVariableName;
	}
}

