
# What is a container (Docker)
- Standard unit of software that packages up code and all of its dependencies so that it can run quickly and reliably from one computing environment to another
# Why use containers?
## Lower overhead
 - Given they share the host os kernel, they use far less cpu, memory and storage than VMs which each include their own OS.
## Easier portability
- Containers package the app with all its dependencies making it easier to move between environments
## Faster scaling and rollbacks
- Containers can be quickly replicated or replaced supporting autoscaling, rolling updates with minimal downtime

# Drawbacks of containers:
## Security risks
- Given they share the kernel, misconfiguration or privilege escalations can expose the host system
## Networking challenges
- Container can be harder as you may need overlay networks or service meshes in multi-host clusters
## Storage challenges
- Containers are ephemeral by design meaning when the container is shut down all data attached is lost unless external storage is configured