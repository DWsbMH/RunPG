package com.diploma.lilian.engine.collition;

import com.diploma.lilian.engine.IsoSprite;

public interface OnCollisionListener {

	public void handleCollision(IsoSprite s1, IsoSprite s2, int collisionGroupMask);
}
