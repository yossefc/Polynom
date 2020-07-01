/**
 * Matala MMN15 q.2
 * @author 
 * @version 
 * 
 * class that present full Polynom.
 */

public class Polynom {
	
	private PolyNode _head;

	// constructors
	/**
	 * empty constructor
	 */
	public Polynom() {
		_head = null;
	}
	
	/**
	 * copy constructor
	 * @param p - PolyNode to add
	 */
	public Polynom (PolyNode p) {
		_head = p;
	}
	
	//getters
	/** @return the head */
	public PolyNode getHead() {
		return _head;
	}
	
	//setters
	/** set the head
	 * @param p - the PolyNode to be set
	 */
	public void setHead(PolyNode p) {
		_head = p;
	}
	
	
	/**
	 * add PolyNode to the Polynom by the correct order
	 * @param p - the new PolyNode
	 * @return the new Polynom after added p
	 * efficiency: <br> O(n) Time, O(1) Place.
	 */
	public Polynom addNode(PolyNode p) {
		PolyNode cur = _head, prev = _head; // current and previous PolyNode
		if(this._head == null) 
			_head = p; // set the first PolyNode
		else {
			while (cur != null && ! (cur.getPower() < p.getPower())) {
				if(cur.getPower() == p.getPower()) {
					/* if the power and the coefficient are equals 
					 * than delete the PolyNode and keep the rest of the Polynom */
					if(cur.getCoefficient()*-1 == p.getCoefficient()) {
						if(cur == _head) // first in the list
							_head = cur.getNext();
						prev.setNext(cur.getNext());
						return this;
					}
					return addEqualsPolyNode(cur, p); // private method add two PolyNode
				}
				prev = cur;
				cur = cur.getNext();
			}
			if(cur == _head) {
				p.setNext(cur);
				_head = p; // set p in the first index in the list
			}
			else if(cur == null) // end of the polynom
				prev.setNext(p); // add p in the last 
			else {
				p.setNext(cur); // add in the right index by the power
				prev.setNext(p);
			}
		}
		return this; // return the current polynom after the changes
	}
	

	/**
	 * multiplier current polynom with any input number
	 * @param mun - input num for the multiplication
	 * @return current polynom after multiplication
	 * efficiency: <br> O(n) Time, O(1) Place.
	 */	
	public Polynom multByScalar(int num) {
		PolyNode temp, prev = _head;
		for(temp = _head; temp != null; temp = temp.getNext()) {
			if(temp.getCoefficient() == 0.0) // skip zero PolyNode that will deleted
				prev.setNext(temp.getNext());
			temp.setCoefficient(temp.getCoefficient()*num);
			prev = temp;
		}
		return this;
	}
	
	/**
	 * add input polynom with the current
	 * @param other - input polynom for the adding
	 * @return the current polynom after the adding
	 * efficiency: <br> O(n+m) Time, O(m) Place.
	 */
	public Polynom addPol(Polynom other) {
		if(other == null) // if one polynom or the other is null
			return this;
		if(this._head == null) { // if the current polynom is empty than copy other to current
			_head = other._head;
			return this;
		}
		PolyNode temp = this._head, tempOther = other._head, prev = this._head, p1 = other._head; // temp PolyNode
		while(tempOther != null) {
			/* if tempOther is fit in the right index - add him to the list */
			if(tempOther.getPower() > temp.getPower() && tempOther.getPower() < prev.getPower()
					|| _head == temp && tempOther.getPower() > temp.getPower()) {
				p1 = new PolyNode(tempOther); // avoid aliasing
				tempOther = tempOther.getNext();
				p1.setNext(temp);
				if(temp == _head) // if it's in the first PolyNode
					_head = p1;
				else
					prev.setNext(p1); // add
				prev = p1;
			}
			/* if the power are equals - add them together */
			else if(tempOther.getPower() == temp.getPower()) {
				/* if the power and the coefficient are equals 
				 * than delete the PolyNode and keep the rest of the Polynom */
				if(temp.getCoefficient()*-1 == tempOther.getCoefficient())
					prev.setNext(temp.getNext());
				addEqualsPolyNode(temp, new PolyNode(tempOther)); // private method add two PolyNode
				tempOther = tempOther.getNext();
			}
			else if(temp.getNext() == null) { // avoid null
				p1 = new PolyNode(tempOther);
				p1.setNext(null);
				temp.setNext(p1);
				tempOther = tempOther.getNext();
			}
			else { // if tempOther.power < temp.power -  keep going in the original list
				prev = temp;
				temp = temp.getNext();
			}
		}
		return this;
	}
	
	
	/**
	 * multiplier input polynom with cuurent polynom 
	 * @param other - input polynom for the multiplication
	 * @return this polynom after the changes
	 * efficiency: <br> O(n^3) Time, O(n) Place.
	 */
	public Polynom multPol(Polynom other) {
		if(other == null)
			return this;
		/* t1 - temp polynode for current polynom
		 * t2 - temp polynode for newPol
		 * tOther - temp polynode for other */
		PolyNode t1, t2, tOther;
		Polynom newPol = new Polynom(); // temp polynom for the calculation
		for(tOther = other._head; tOther != null; tOther = tOther.getNext()) {
			for(t1 = this._head; t1 != null; t1 = t1.getNext()) {
				if(t1.getCoefficient() == 0.0) // skip zero PolyNode that will deleted
					t1 = t1.getNext();
				/* create new PolyNode for newPol with Power added of t1 and tOther 
				 * and multiplication of them */
				t2 = new PolyNode(t1.getPower() + tOther.getPower(), t1.getCoefficient() * tOther.getCoefficient());
				newPol.addNode(new PolyNode(t2)); // add t2 to newPol and in the right index
				t2 = t2.getNext();
			}
		}
		this._head = newPol._head; // replace current polynom with newPol
		return this;
	}

	
	/**
	 * calculate the differential of the polynom
	 * @return the polynom after the differential
	 * efficiency: <br> O(n) Time, O(1) Place.
	 */
	public Polynom differential() {
		PolyNode temp;
		for(temp = _head; temp != null; temp = temp.getNext()) {
			temp.setCoefficient(temp.getCoefficient()*temp.getPower()); 
			temp.setPower(temp.getPower()-1);
			if(temp.getNext() == null) // avoid null pointer exception
				break;
		}
		return this;
	}

	
	/**
	 * ToString method
	 * @return the Polynom as string in the format of PolyNode but for all the Polynom
	 * efficiency: <br> O(n) Time, O(1) Place.
	 */
	public String toString() {
		PolyNode temp;
		String tempStr;
		if(_head == null)
			return "";
		tempStr = "" + _head; // first PolyNode don't need mark
		for(temp = _head.getNext(); temp != null; temp = temp.getNext()) {
			if(temp.getCoefficient() <= 0)
				tempStr += "" + temp; // minus mark existing anyway
			else
				tempStr += "+" + temp; // add plus mark between PolyNode
		}
		if(tempStr.equals(""))
			return tempStr;
		return tempStr = tempStr.charAt(0) == '+' ? tempStr.substring(1) : tempStr;
	}
	
	
	/* private method for add two polynode with equals power
	 * efficiency: <br> O(1) Time, O(1) Place. */
	private Polynom addEqualsPolyNode(PolyNode p1, PolyNode p2) {
		p1.setCoefficient(p1.getCoefficient() + p2.getCoefficient());
		return this;
	}
}
