package com.linhnt.speedshoot.bases;

/**
 * Created by LT on 14/07/2017.
 */
public class Vector2D {
    public static Vector2D zeroVector = new Vector2D(0, 0);
    public static Vector2D unitVector = new Vector2D(1, 1);

    public float x;
    public float y;
    private Scope scopeX;
    private Scope scopeY;

    public Vector2D() {
    }

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D set(float x, float y) {
        this.x = this.scopeX != null ? Scope.scope(x, this.scopeX) : x;
        this.y = this.scopeY != null ? Scope.scope(y, this.scopeY) : y;
        return this;
    }

    public Vector2D set(Vector2D other) {
        return other != null ? this.set(other.x, other.y) : this;
    }

    public Vector2D clone() {
        return new Vector2D(this.x, this.y);
    }

    public Vector2D add(float x, float y) {
        return this.clone().addThis(x, y);
    }

    public Vector2D add(Vector2D other) {
        return this.clone().addThis(other);
    }

    public Vector2D addThis(float x, float y) {
        return this.set(this.x + x, this.y + y);
    }

    public Vector2D addThis(Vector2D other) {
        return other != null ? this.addThis(other.x, other.y) : this;
    }

    public Vector2D minus(float x, float y) {
        return this.clone().minusThis(x, y);
    }

    public Vector2D minus(Vector2D other) {
        return this.clone().minusThis(other);
    }

    public Vector2D minusThis(float x, float y) {
        return this.set(this.x - x, this.y - y);
    }

    public Vector2D minusThis(Vector2D other) {
        return other != null ? this.minusThis(other.x, other.y) : this;
    }

    public Vector2D multiply(float v) {
        return this.clone().multiplyThis(v);
    }

    public Vector2D multiplyThis(float vx, float vy) {
        return this.set(this.x * vx, this.y * vy);
    }

    public Vector2D multiplyThis(Vector2D other) {
        return other != null ? this.multiplyThis(other.x, other.y) : this;
    }

    public Vector2D multiply(float vx, float vy) {
        return this.clone().multiplyThis(vx, vy);
    }

    public Vector2D multiply(Vector2D other) {
        return this.clone().multiplyThis(other);
    }

    public Vector2D multiplyThis(float v) {
        return this.set(this.x * v, this.y * v);
    }

    public Vector2D reverseX() {
        return this.clone().reverseXThis();
    }

    public Vector2D reverseXThis() {
        this.x *= -1;
        return this;
    }

    public Vector2D reverseY() {
        return this.clone().reverseYThis();
    }

    public Vector2D reverseYThis() {
        this.y *= -1;
        return this;
    }

    public Vector2D reverseMultiplyXThis() {
        return this.set(this.x == 0 ? 0 : 1 / x, y);
    }

    public Vector2D reverseMultiplyYThis() {
        return this.set(x, this.y == 0 ? 0 : 1 / y);
    }

    public Vector2D reverseMultiplyThis() {
        return this.reverseMultiplyXThis().reverseMultiplyYThis();
    }

    public Vector2D reverseMultiplyX() {
        return this.clone().reverseMultiplyXThis();
    }

    public Vector2D reverseMultiplyY() {
        return this.clone().reverseMultiplyYThis();
    }

    public Vector2D reverseMultiply() {
        return this.clone().reverseMultiplyThis();
    }

    public Vector2D reverse() {
        return this.clone().reverseThis();
    }

    public Vector2D reverseThis() {
        return this.reverseXThis().reverseYThis();
    }

    public Vector2D normalize() {
        return this.clone().normalizeThis();
    }

    public Vector2D normalizeThis() {
        return this.multiplyThis(1.f / this.getLength());
    }

    public float getLength() {
        return (float)Math.sqrt(this.x * this.x + this.y * this.y);
    }

    @Override
    public String toString() {
        return String.format("(x: %s; y: %s)", this.x, this.y);
    }

    public Scope getScopeX() {
        return scopeX;
    }

    public void setScopeX(Scope scopeX) {
        this.scopeX = scopeX;
    }

    public Scope getScopeY() {
        return scopeY;
    }

    public void setScopeY(Scope scopeY) {
        this.scopeY = scopeY;
    }

    public float lengthTo(float x, float y) {
        return this.minus(x, y).getLength();
    }

    public float lengthTo(Vector2D other) {
        return other != null ? lengthTo(other.x, other.y) : 0;
    }

    public Vector2D setLength(float length) {
        return this.clone().setLengthThis(length);
    }

    public Vector2D setLengthThis(float length) {
        return this.multiplyThis(length / this.getLength());
    }

    public void rotateThis(float angleRadian){
        angleRadian += Math.atan(y/x);
        float length = this.getLength();
        this.set(length * (float)Math.cos(angleRadian), length * (float)Math.sin(angleRadian));
    }

    public Vector2D rotate(float angleRadian){
        Vector2D vector2D = this.clone();
        vector2D.rotateThis(angleRadian);

        return vector2D;
    }

    public static void main(String[] args) {
        Vector2D x1 = new Vector2D(2, 2);
        System.out.println(x1.setLength((float)Math.sqrt(2)));
    }


}
