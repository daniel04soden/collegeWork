# Authentication

- The user or computer has to prove its identity to the server or the
  client
- Process by which a server determines if the client ahs permission to
  use a resource or access a file
  - It is usually coupled with authentication so that the server has
    some concept of who the client that is accessing the resources is
- An authentication scheme is a defintion of what is required for an
  authentication process.
- Authentication is sued by a server when the server needs to know
  exactly who is accessing their information or site

## Behavioural Biometrics

- Unique to each individual on each device

- Captchas

- First vendor to offer as part of a risk assesment for access control.

## HTTP Authentication

- It provides a good general framework for access control and
  authentication

  - It restricts access to a server

- This http authentication framework is designed by RFC 7235, which can
  be used by a server to challenge a users authentication

- In securty protocols a challenge is some data sent to teh client by
  the server in order to generae a different response over time.

- In challenge-response authentication, one party presenets a question
  (the server to the client) and another party must provide a valid
  answer (\"response\"), to be authenticated.

- It guards against replay attacks where an attacker listens to previous
  messages and uses them after some time to fool the server.

<!-- -->

- The http authetication protocol is challenge-response based, though
  the basic protocol doesn\'t use a real challenge (its always the same)

### The challenge and response flow works like this:

- A client sends a request to a server to access a resource.

  - The server responds to a client with a 401 reponse status and
    provides information on how to authroize with www-authenticate
    response header with at least one callenge.

- A client can then authenticate by including an authroization request
  header with credentials.

  - Usually a client will present a password prompt to the suer and will
    then issue the request including the correct Authorization header.
