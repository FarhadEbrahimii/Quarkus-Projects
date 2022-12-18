12.1.1 Enabling OpenAPI
With the source code in place, let’s add the following dependency
needed for OpenAPI:
    <dependency>
        <groupId>io.quarkus</groupId>
        <artifactId>quarkus-smallrye-openapi</artifactId>
    </dependency>

An alternative way to add the dependency is by using the Quarkus

Maven plugin, as shown here:
mvn quarkus:add-extension -Dextensions="quarkus-smallrye-openapi"

That’s it! With the addition of one dependency, the Account service
will have an OpenAPI document produced from the code. Let’s try it out.
Start the service in live coding mode as follows:
    mvn quarkus:dev

When started, access http://localhost:8080/q/openapi either with a browser or curl. The default format for the OpenAPI
document is YAML. If the OpenAPI document was accessed in a browser, it will download a file with the following content.

Listing 12.1 The Account service–generated OpenAPI document

openapi: 3.0.3 ❶
info: ❷
    title: Generated API
    version: "1.0"
paths: ❸
    /accounts:
        get:
            responses:
                "200":
                description: OK
                content:
                    application/json:
                        schema:
                            $ref: '#/components/schemas/SetAccount'
        post:
            ....
components:
    schemas: ❹
        SetAccount:
            uniqueItems: true
            type: array
            items:
                $ref: '#/components/schemas/Account'
        Account:
            type: object
            properties:
                accountNumber:
                    format: int64
                    type: integer
        ...

❶ Version of the OpenAPI specification with which the document conforms
❷ Information about the service. In this case, there isn’t any information because it was
generated.
❸ The paths, or API endpoints, exposed by the service
❹ All the entities that the API endpoints require and the schema for each, defining their
structure
Note Some methods were removed from the OpenAPI document shown here for brevity.
Viewing the document locally will also include the API methods for withdrawal and deposit.
The same OpenAPI document can be served in JSON format
instead by accessing http://localhost:8080/q/openapi?
format=json. If explicitness is desired, the YAML format can be
used as well: http://localhost:8080/q/openapi?format=yaml.
Note Instead of using query parameters on a URL, the desired format of the OpenAPI
document can be specified with the Accept HTTP request header. Setting it to application/
json will retrieve JSON instead of YAML.
It’s by no means a great OpenAPI document to use with clients,
but it’s a good first representation of what the Account service
offers and is far better than no OpenAPI document at all.
Readers may have noticed when the service was started earlier
that the console output didn’t show only smallrye-openapi
as a feature. There is also swagger-ui. With Quarkus,
Swagger UI is packaged as part of the OpenAPI extension. Let’s
take a look at Swagger UI in the next section.
