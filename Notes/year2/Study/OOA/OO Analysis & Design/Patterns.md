### Abstraction Occurrence Pattern

1.  Context

    - There is a set of related objects
    - The members share common information but differ in important ways
      ​###### Problem
    - Need to duplicate objects without duplicating common information
      ​###### Scenario
    - A library has multiple copies of the same book
    - Author, ISBN, title is all the same... maybe something like an ID
      is different so each book can be distinguished
    - Is there a point of replicating all these objects? *nah* \######
      Solution? We create an *occurrence class* to represent the
      individual things There is a one to many class between the
      Abstraction and the Occurrence The Abstraction class has all the
      common elements The Occurrence class contains all the different
      elements \### Delegation Pattern The *delegation pattern* is a OO
      design pattern that allows object composition to achieve the same
      code reuse as inheritance. In *delegation*, an object handles a
      request by delegating to a second object. The second object is a
      helper object to the original object **Composition** is favourable
      over inheritance Composition has *several* advantages over
      inheritance. \### Singleton Pattern In the singleton pattern,
      there can only be one instance of a class. It provides a single
      point of access for that class. \#### Implementation Have a static
      member which contains the instance of the class Have a private
      constructor to private anyone else being able to instantiate the
      class Have a static public helper method to allow a global point
      of access to allow other objects to be able to access this one
      instance. \#### When? We use this pattern when we want only a
      single instance of the class to be created inside our application.
