package com.diploma.lilian.engine;

public class Model {
/*
	private int x,y;
	private IsoSprite[] spriteInfoCollection;
	private int nextempty;
	
	
	public Model(int x, int y, int spritenumber) {
		this.x = x;
		this.y = y;
		spriteInfoCollection = new IsoSprite[spritenumber];
		nextempty = 0;
	}
	
	public boolean addSprite(int xoffset, int yoffset, IsoSprite sprite) {
		if(nextempty == spriteInfoCollection.length)
			return false;
		
		int xdif, ydif;
		
		xdif = (int) ((x+xoffset)-sprite.getDrawX());
		ydif = (int) ((y+yoffset)-sprite.getDrawY());
		sprite.moveInPlot(xdif, ydif, 0);
		spriteInfoCollection[nextempty] = sprite;
		nextempty++;
		
		return true;
	}
	
	public void setViewport(Viewport vp) {
		for(int i=0; i<nextempty; i++) {
			spriteInfoCollection[i].setViewport(vp);
			vp.addElement(spriteInfoCollection[i]);
		}
	}
	
	public void moveInPlot(int dx, int dy, int dz) {
		for(int i=0; i<nextempty; i++) {
			if(spriteInfoCollection[i].isShadow())
				spriteInfoCollection[i].moveInPlot(dx, dy, 0);
			else
				spriteInfoCollection[i].moveInPlot(dx, dy, dz);
		}
	}
	
	public void moveInIso(int dx, int dy, int dz) {
		for(int i=0; i<nextempty; i++) {
			if(spriteInfoCollection[i].isShadow())
				spriteInfoCollection[i].moveInPlot(dx, dy, 0);
			else
				spriteInfoCollection[i].moveInPlot(dx, dy, dz);
		}
	}
	
	public IsoSprite getSprite(int index) {
		if(index < nextempty)
			return spriteInfoCollection[index];
		else
			return null;
	}
	*/
}
