package dev.jet.android.galaxywar.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import dev.jet.android.galaxywar.world.actors.Entity;

public class Background extends Entity {
	
	int tWidth;
	int tHeight;
	
	int sWidth;
	int sHeight;
	
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
	
	@Override
	public void create(BaseWorld world, TextureRegion image) {
		super.create(world, image);
		
		tWidth = (int)image.getRegionWidth();
		tHeight = (int)image.getRegionHeight();
	}
	
	public void setScreenSize(int width, int height) {
		sWidth = width;
		sHeight = height;
	}
	
	public void setInitPosition(float x, float y) {
		initX = x;
		initY = y;
	}
	
	private void setBackPosition(float x, float y) {
		
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
	public void draw(SpriteBatch batch, float alphaParent) {
		
		setBackPosition(getScreenX(), getScreenY());
		
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
