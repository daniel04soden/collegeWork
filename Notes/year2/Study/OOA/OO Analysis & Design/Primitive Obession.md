*Primitive Obsession* refers to an anti-pattern where developers heavily
rely on primitive data types instead of encapsulating related data into
well-defined domain objects When *primitives* are spammed in a codebase,
the resulting code can become cluttered and harder to read. *Primitive
Obsession* leads to a lack of type safety, as there is no inherent
structure or validation associated with primitive data types. \###
Entity An object defined primarily by its identity is called an *entity*
*Entities* have special modelling and design considerations They have
life cycles that can radically change their form and content Their
identities must be defined to that they can effectively be tracked Their
class definitions responsibilities, attributes and associations should
resolve around who they are, rather than particular attributes they
carry A person class could have a name, email and password. Within our
database they have an ID as well. Their attributes may change but the
object remains the same. This is what we call an *entity*. The *entity*
object will maintain the identity cause it has an id. \### Value Objects
*Value objects* differ from entities by lacking the concept of identity.
We do not care who they are but what they are. They are defined and
their attributes should be immutable.
