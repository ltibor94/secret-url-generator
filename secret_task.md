
# Secret Task

Your task is to implement a secret server. The secret server can be used to store and share secrets using the random generated URL. But the secret can be read only a limited number of times after that  it will expire and won’t be available. The secret may have TTL. After the expiration time the secret  won’t be available anymore. You can find the detailed API documentation in the swagger.yaml file.  

We recommend to use  [Swagger](https://editor.swagger.io/"https://editor.swagger.io/")  or any other OpenAPI implementation to read the documentation.  

The advised time for the task is 2-3 hours, if both the backend and UI task is completed. You can take your time if you wish, but we do not require more work on the task than previously stated. 

## Implementation (around 1.5 hour)
 You have to implement the whole Secret Server API. Please use Java 9+ leveraging new features and also Spring/Spring Boot framework. 

The database used is up to you, only requirement is for it to be relational. However it would be wise to store the data using encryption now this is not part of the task. You can use plain text to store your secrets. You are free to create a schema that fits the needs of the application.

Please use a configuration file in order to configure the database access details (ip address, username, pwd, etc.)  

At least controller level error handling is required.

##  Response types

The API must be able to respond with JSON. 

## UI service (around 1 hour)
 You have to create a **minimal** frontend service too, that uses the backend REST API.
 
 Requirement is:
 -  a field to input the secrets
 - some way to visualize the response 
 - provide the possibility to query a secret.
  
 You are advised to use either Angular/Thymeleaf/Plain HTML+JS+CSS. 
 But you can use the tool set that you are the most familiar with, **none of them is more appreciated than the others.**
 We are interested whether you can create a frontend service, not in the actual technology you use.

##  Running the project
 Please provide a detailed description on how to run the project. 
 
 **You  don't have to use HTTPS. HTTP is perfectly fine.**

It is not a requirement to run the backend and UI service with a single command. It is perfectly fine to run them separately, one-by-one. The projects can be in a separate repository as well.

## Testing (Around 0.5 hour)
Please also think about testing. You have to include Unit tests as well, use JUnit5 and your favorite Mocking library. Expected test coverage at least: 90%

UI service does not have to be tested.  **The scope of the tests are strictly the backend service.**

##  Share the code
Please use Git and an online repository (GitHub/GitLab) and share the link with us. Please commit your code incrementally, not just one HUGE commit at the end.  

## Questions?
If you have any questions please feel free to ask. We even appreciate it if you do so, since we can see how you think about the task.

You can reach out to us :

 - zoltan.geczi@capgemini.com
 - ferencz.kovacs@capgemini.com
 - gergely.murvai@capgemini.com
