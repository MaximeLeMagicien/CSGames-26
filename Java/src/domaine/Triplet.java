/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domaine;

/**
 *
 */
public class Triplet<T, U, V> {

    private final Touche first;
    private final long second;
    private final boolean third;

    public Triplet(Touche first, long second, boolean third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public Touche getFirst() { return first; }
    public long getSecond() { return second; }
    public boolean getThird() { return third; }
}