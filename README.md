# MOX 

looks like a state management application, albeit with a more oriented approach to data flows and errors in execution contexts.

Context is a mutable, stateless data handler designed to act as a single source of truth during the execution of an operation in a Java application. Its goal is to facilitate communication between different layers or components (controllers, services, validators, etc.) without being coupled to any specific framework, design pattern, or architecture.

It works as a central container for transporting data, states, and errors, making it easier to model complex business flows in backend applications without relying on heavyweight frameworks. Inspired by the observer pattern, it allows multiple subscribers to be notified when a data value changes, making it useful in scenarios where various components need to react to state changes in a coordinated way.

Additionally, it encapsulates both successful results (ok) and error states (err), ensuring consistent management of operation outcomes, and it securely stores typed properties using Prop<T> objects.

#### What is it useful for?

It is useful in architectures where you need to:

    - Pass mutable context between layers (services, middlewares, validations...).
    - Store and retrieve properties in a generic and safe way at runtime.
    - Capture rich and structured errors, without coupling to a specific implementation
    - Implement a Result / Either type flow, where there is either an ok or an err, but not both
