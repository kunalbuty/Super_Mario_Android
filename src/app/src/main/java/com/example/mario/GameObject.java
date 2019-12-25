package com.example.mario;

// Includes all objects in the game that should respond to another object colliding with them in any way
abstract public class GameObject {
    private int xPos;
    private int yPos;

    public abstract void collide(GameObject other);
}