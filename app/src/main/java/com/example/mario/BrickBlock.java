package com.example.mario;

// Standard breakable block
public class BrickBlock extends Block {
    public void collide(GameObject other) {
        // If hit by Player from below
        // Move the block upward slightly (used to destroy enemies on top of the block)
        // Play sound effect
        // If Player is in super form,
        // Destroy this object and play sound effect
    }
}