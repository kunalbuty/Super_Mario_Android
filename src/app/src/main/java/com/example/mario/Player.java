package com.example.mario;

// Object for the player
public class Player extends GameObject {
    private boolean superMushroom; // Determines whether Player is in super form
    private int invincible; // Timer for how long invincibility lasts, Player is invincible if nonzero
    private int points; // Number of points the player has

    @Override
    public void collide(GameObject other) {
        // If the object is a block,
        // Stop Player's movement in the direction of the block (i.e. treat block as a solid object)
        // If the block is hit from below,
        // If the block is a Brick Block,
        // Give the player points
        // If the block is a ? block and it contains a coin,
        // Give the player the coin immediately

        // If the object is an item,
        // If the item is a Coin,
        // Give Player points

        // If the item is a Super Mushroom,
        // Give Player points
        // Player enters super form

        // If the item is a

        // If the object is an enemy,
        // If Player is invincible
        // Give Player points

        // If the enemy is a Goomba,
        // If Player is only touching the top of the Goomba,
        // Give Player points
        // Otherwise,
        // Take damage

        // If the enemy is a Piranha Plant,
        // Take damage
    }

    public void damage() {
        if (superMushroom) {
            superMushroom = false;
        }
        else {
            // Die
        }
    }

    public void invincibleCountdown() {
        if (invincible > 0) {
            invincible--;
        }
    }
}