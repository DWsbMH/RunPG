package com.diploma.lilian.engine.collition;

import com.diploma.lilian.engine.IsoSprite;

public class CollisionPair {

	public IsoSprite sprite1;
	public IsoSprite sprite2;
	
	public String toString() {
		return sprite1.getName() +" - "+ sprite2.getName();
	}
}
