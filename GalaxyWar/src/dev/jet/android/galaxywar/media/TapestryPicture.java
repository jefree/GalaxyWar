package dev.jet.android.galaxywar.media;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.jet.android.galaxywar.world.actors.Entity;

public class TapestryPicture extends SinglePicture {
	
	int tWidth;
	int tHeight;
	
	float initX;
	float initY;
	
	float currentX;
	float currentY;
	
	float dstX;
	float dstY;
	
	int srcX;
	int srcY;
	
	int width;
	int height; 
	
	public void setInitPosition(float x, float y) {
		initX = x;
		initY = y;
	}
	
	private void setPosition(float x, float y) {
		
		if (x >= initX + tWidth) {
			currentX = x - (int)(x/tWidth)*tWidth;
		} else if (x <= initX) {
			currentX = tWidth + (x - (int)(x/tWidth)*tWidth);	
		} else {
			currentX = x;	
		}
		
		if (y >= initY + tHeight) {
			currentY = y - (int)(y/tHeight)*tHeight;
		} else if (y <= 0) {
			currentY = tHeight + (y - (int)(y/tHeight)*tHeight);	
		} else {
			currentY = y;
		}
	}
	
	@Override
	public void load(String fileName) {
		super.load(fileName);
		
		tWidth = (int)image.getRegionWidth();
		tHeight = (int)image.getRegionHeight();
		
	}
	
	private void drawImage(SpriteBatch batch) {
		batch.draw(image.getTexture(), 
				dstX, dstY,
				0, 0,
				width, height,
				1,1,0,
				srcX, srcY,
				width, height,
				false, false);
	}
	
	@Override
	public void draw(Entity e, SpriteBatch batch, float alphaParent) {
		
		setPosition(e.getScreenX(), e.getScreenY());
		
		dstX = currentX;
		dstY = currentY;
		
		srcX = 0;
		srcY = 0;
		
		width = tWidth;
		height = tHeight;
		
		float tempInitX;
		
		//trasladar el punto X inicial de la pantalla
		while (dstX > 0) {
			dstX -= tWidth;
		}
		
		tempInitX = dstX;
		
		//trasladar el punto inicial Y de la pantalla
		while (dstY > 0) {
			dstY -= tHeight;
		}
		
		// !!! optimizar con culling
		while (dstX < sWidth | dstY < sHeight) {
			
			if (dstX >= sWidth) {
				dstY += tHeight;
				dstX = tempInitX;
			}
			
			drawImage(batch);
			
			dstX += tWidth;
		}
		
	}
	
}
