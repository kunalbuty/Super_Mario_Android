package com.example.mario;

// Items such as Coin, Super Mushroom, Star
public class Item extends GameObject {
    @Override
    public void collide(GameObject other) {
        // If touched by Player,
        // Destroy this object
    }
}