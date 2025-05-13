### Software Rigidity
*Rigidity* is the tendency for software to be difficult to change, even in simple ways.
*Symptom:* Every change causes a cascade of subsequent changes in dependent modules
*Effect:* When software is like this, managers fear developers to fix non-critical issues.
### Software Fragility
*Fragility* is the tendency of the software to break in many places every time it is cased. Often breakage occurs in areas that have no conceptual relationship with the area that was changed.
*Symptom:* Every fix makes it worse. introducing more problems than are solved
*Effect:* Every time managers/leaders authorise a fix, they are scared what the software will break is some unexpected way.
### Software Immobility
*Immobility* is the inability to reuse software fro other projects or parts of the same project.
*Symptom:* A developer discovers he needs to use a module that is similar to what another wrote but their is just too much baggage to use it.
*Effect:* The software is rewritten
### Software Viscosity
*Viscosity* is the tendency of software/development environment to encourage software changes that are hacks rather than software that keeps to the original design intent
*Symptom:* It is easy to do the wrong thing, but hard to do the right thing
*Effect:* The software maintainability degenerates due to hacks, workarounds, shortcuts, temporary fixes etc
### Why do these problems happen?
There are some **obvious** reasons
- Lack of design skills or practices
- Changing technologies
- Time/Resource constraints
- Domain complexity
There are also some **not so obvious** reasons
- Software rotting is a slow process. A clean system can devolve over time
- Dependencies can go unmanaged
- Requirements can change in a way that wasn't planned
### Law of Demeter
*Also known as the Principle of least knowledge*, is a design principle which provides a guideline for designing a system with minimal dependencies. Often summarised as *only talk to your immediate friends*
- Minimise the number of acquaintances which directly communicate with a supplier
	- Reduce amount of rework if supplier interface is modified
	- Only allowed certified classes to call supplier
- Access supplier method only through methods of *preferred acquaintances*
	- Bypass preferred supplier *only* if later optimisation demands direct access
The Law of Demeter for methods requires that a method M of an object O may only invoke the methods of the following kind of objects
- O itself
- M's parameters
- Any objects created/instantiated within M
- O's direct component objects
- Global variables, accessible by O, in the scope of M
### Acceptable Violations
If optimisation requires violation
- Speed or memory restrictions
If module accessed is a fully stabilised "Black Box"
- No changes to interface can reasonably be expected due to extensive testing/usage etc
**Otherwise, do not violate this law** 