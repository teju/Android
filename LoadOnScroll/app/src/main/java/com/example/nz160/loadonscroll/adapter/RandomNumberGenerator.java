package com.example.nz160.loadonscroll.adapter;

import java.util.Random;

public class RandomNumberGenerator {
	// Random generator method this will generate int nos.
	public int RandomGenerator() {
		Random random = new Random();
		int randomNum = random.nextInt(9);

		return randomNum;
	}

}
