# Interfaces

- Contracts between classes and users
- Some interfaces are called tagging interfaces
- Used so the implementing objects can be used in a certain way or have
  certain characteristics
- Or interact with another class or classes
- Serialisable clonable etc

# Object serialisation

- The mechanism for saving an object and its state so that it can be
  used again in another program

- Object can live beyond the program execution that created it is called
  persistence.

- We save our object on hard drive

- Store in memory

- Send it through a network - cloud

- Real way is to use databases

# IO streams

- Abstraction of a source or taget of data
- Can represent many data sources
  - Files on a hard disk
  - Another computer on a netwrok
  - Web page
  - Input device
- Represented by java.io classes
- inputstream
- outputstream

## Streams and inheritance

- ALl input stereams extend common super-class inputstream
- ALl output streams extend common superclass outputstream
  - Guarantees all sources of data have the same methods
  - Provides minimal ability to read and write one byte at a time

### Serialisation

- Reading/Writing objects and their exact state into a linear format
  using IO streams

  - Entire objects can be written to files a network a database etc
  - Lets you save your objects to disk and restore alter
  - Avoids converting objects state into an arbitrary text format \###
    Object serialise and de-serialise

- Accomplished using the ObjectOuput/Inputstream classes

- The write and read object methods are used for that

- You must implement the java.io.Serailizable interface for your class
  to be compatible with streams

# Object serialisation

- Takes into account anoy other objects that are referenced by an object
  being sraliszed saving them too

- SUch object must also implement the serialzable interface

- Many classes from the java class library implement serialisable
  including the string class

- The array list can also be serialised

- All primitive datatypes and built into datatypes are serialisable

- Your own classes might not be serialisable

# Transient

- When we don\'t wanna serialise something, we prefer to exclude a
  particular piece of info such as a password

# Clone-able interface

- Has no methods
- Relies on method clone of object class which the programmer may choose
  to call
  - Shallow copy
    - Creates new instance
    - Copies all the fields of the object to that new instances
    - Returns it as object type
    - We need to explicitly declare it
- comparable vs comparator - sort vs equality
