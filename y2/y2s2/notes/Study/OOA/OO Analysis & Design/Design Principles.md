### Coupling & Cohesion
###### Aims
- Easier to develop
- Easier to add new features
- Less fragile
- Easier to maintain
**Cohesion** is used to indicate the degree to which a class has a single, well-focused purpose. The higher the cohesiveness of the class, the better the OO design.
**Coupling** refers to the degree of direct knowledge that one element has of another 
- *Tight Coupling:* A class knows more than it should about the way another class is implemented
- *Loose Coupling:* Classes are mostly independent. A class only knows what another class has through its exposed interface.
###### Benefits of Higher Cohesion
Highly *cohesive classes* are much easier to maintain and less frequently changed.
Such classes are more usable than others as they are designed with a well-focused purpose. 
### Single Responsibility Principle
This principle states that every class should have a single responsibility. That responsibility should be entirely encapsulated by the class. All services should be narrowly aligned with that responsibility. 
#### God Class
A GOD is a class that keeps track of a lot of information and have several responsibilities.
A *god object* is an object that knows too much or does too much. It is an example of an anti-pattern.

#### Why?
- Helps organise the code
- It is *less fragile*. When a class has more than one reason to be changed, it is more *fragile*
- More responsibilities leads to higher *coupling*. 
- Refactoring is much easier for a single responsibility module

### Dependency Inversion Principle
- Depends on abstractions, not concretions
- Program to interfaces not implementations
- Program to the most abstract class possible
- Why? Concrete classes may change a lot, Abstract classes/Interfaces change very little
### Interface Segregation Principle
- Don't make large multipurpose interfaces - instead use several small focused ones
- Don't make clients depend on interfaces they don;t use
- Class should depend on each other through the smallest possible interface
- Why? When I change something, I want to minimise changes for everyone else
This design principle deals with the disadvantages of *fat interfaces*
Interfaces containing methods that are not specific to it are called polluted or fat interfaces
How can we identify these *disgusting and gross fat interfaces*
1. See if your interface has too many methods.
2. See if your interfaces have *low cohesion*
3. This problem can apply to abstract classes as well
### Open Closed Principle
This principle states that software entities should be open for extension but closed for modification. As a result when the business requirements change, then the entity can be extended, but not modified. 
To adhere to this principle we can use abstract and polymorphism. Lets use the example of a payment system.
```java
public interface PaymentGateway
	void processPayment(String cardNumber, String amount); 

public class StripePaymentGateway implements PaymentGateway {
	public void processPayment(String cardNumber, String  amount) {
		// Use stripe API
	}
public class PayPalPaymentGateway implements PaymentGateway {
	public void processPayment(String cardNumber, String  amount) {
		// Use paypal API
	}

public class PaymentService() {
	private PaymentGateway paymentGateway;
	public PaymentService(PaymentGateway paymentGateway){
		this.paymentGateway = paymentGateway;	
	}
	public void processPayment(String cardNumber, String amount) {
		paymentGateway.processPayment(cardNumber, amount);
	}
}
```
#### Benefits?
We can add new systems by creating new classes of the payment gateway interface without needing to ever modify the payment service class.
#### Common Violations
- *Modifying Existing Code*: Defeats the purpose of OCP as it introduces risks of breaking existing functionality
- *God Classes/Modules*: These god classes tend to grow and become difficult to extend and maintain
- *Conditional Statements*: Adding new conditional cases requires modifying the existing code
- 