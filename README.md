# BuilderPatternExample

## Description
This is an example for an Builder/Fluent-Pattern. 
Before an object is created by the "build"-Method, it can be validated by BuilderValidators.
If the object is not valid, an BuilderValidationException is thrown thats holds a list of ValidationErrors
