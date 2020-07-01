/**
 * Matala MMN15 q.1
 * @author yossef cohen zardi
 * @version 
 * 
 * class that present (one) PolyNode in a Polynom.
 */

public class PolyNode {
	
	private int _power;
	private double _coefficient;
	private PolyNode _next;
	
	
	// constructors
	/**
	 * constructor 1 - get power and coefficient, check and set.
	 * @param power - power for the PolyNode
	 * @param coefficient - coefficient for the PolyNode
	 */
	public PolyNode (int power, double coefficient) {
		defaultValue(power, coefficient); // private method for the set
		_next = null;}
	
	
	/**
	 * constructor 2 - get power, coefficient and pointer, check and set.
	 * @param power - power for the PolyNode
	 * @param coefficient - coefficient for the PolyNode
	 * @param next - pointer for the next PolyNode
	 */
	public PolyNode (int power, double coefficient, PolyNode next) {
		defaultValue(power, coefficient); // private method for the set
		_next = next;}
	
	/**
	 * copy constructor
	 * @param p - input PolyNode for the copy
	 */
	public PolyNode (PolyNode p) {
		_power = p._power;
		_coefficient = p._coefficient;
		_next = p._next;}
	
	
	//getters
	/** @return the power */
	public int getPower() {
		return _power;}
	
	/** @return the coefficient */
	public double getCoefficient() {
		return _coefficient;}
	
	/** @return the pointer for the next PolyNode */
	public PolyNode getNext() {
		return _next;}
	
	
	//setters
	/** set the power
	 * @param power - the value to be set
	 */
	public void setPower(int power) {
		if(power >= 0)
			_power = power;}
	
	/** set the coefficient
	 * @param coeggicient - the value to be set
	 */
	public void setCoefficient(double coefficient) {
		_coefficient = coefficient;}
	
	/** set the pointer next
	 * @param next - the value to be set
	 */
	public void setNext(PolyNode next) {
		_next = next;}
	
	
	/** 
	 * ToString method
	 * @return the object as string in the following format:
	 * "_coefficient + "x^" + _power" = -4x^2 for example
	 * efficiency: <br> O(1) Time, O(1) Place.
	 */
	public String toString() {
		if(_coefficient == 0)
			return "";
		if(_power == 0)
			return _coefficient + "";
		if(_coefficient == 1) {
			if(_power == 1)
				return "x";
			return "x^" + _power;}
		if(_coefficient == -1) {
			if(_power == 1)
				return "-x";
			return "-x^" + _power;}
		if(_power == 1)
			return _coefficient + "x";
		return _coefficient + "x^" + _power; // default string
	}
	
	
	/** 
	 * private method -
	 * default value for constructor 1 and 2.
	 * if the power smaller that 0 the power and the coefficient equals 0.
	 * else set as normal.
	 * efficiency: <br> O(1) Time, O(1) Place.
	 */ 
	private void defaultValue(int power, double coefficient) {
		if(power < 0) { // power can not be negative
			_power = 0;
			_coefficient = 0;}
		else {
			_power = power;
			_coefficient = coefficient;}
	}
}
