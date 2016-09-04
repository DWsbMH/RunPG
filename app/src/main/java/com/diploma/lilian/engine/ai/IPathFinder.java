package com.diploma.lilian.engine.ai;

import com.diploma.lilian.engine.IsoSprite;
import com.diploma.lilian.engine.Vec3;

public interface IPathFinder {

	public float getWorldIsoWidth();
	public float getWorldIsoHeight();
	
	public void addObstacle(IsoSprite obstacle);
	public void removeObstacle(IsoSprite obstacle);
	
	public Vec3[] getPath(IsoSprite mover, int toX, int toY);
}
