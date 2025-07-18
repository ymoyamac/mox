# MOX 

looks like a state management application, albeit with a more oriented approach to data flows and errors in execution contexts.

#### What is it useful for?

It is useful in architectures where you need to:

    - Pass mutable context between layers (services, middlewares, validations...).
    - Store and retrieve properties in a generic and safe way at runtime.
    - Capture rich and structured errors, without coupling to a specific implementation
    - Implement a Result / Either type flow, where there is either an ok or an err, but not both
