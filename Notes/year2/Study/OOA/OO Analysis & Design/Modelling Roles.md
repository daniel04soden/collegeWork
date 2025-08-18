### Roles
*Roles* are what any concept/class would play within the context of its related concept/classes
For an example, a company would be the *supplier* of x product. *Supplier* is the role in this case.
Roles can be solved by selecting one of the 5 role patterns.
### Role Patterns
#### Role Inheritance
Think of *roles* as *types*
Lets go back to our past example but consider this A supplier could buy a product from a vendor. This means the supplier can now been seen as a customer.
###### MetaModel
With the use of abstraction we make a new base class. Now our supplier can inherit from this base class and so can a vendor. This allows for a suppler to be a customer or supplier but not both at the same time.
#### Association Role Solution

