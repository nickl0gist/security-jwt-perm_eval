#REST Api with usage PermissionEvaluator and JWT

- [Introduction](#intro)<p> 
- [Technologies](#technologies)<p> 
- [Sources](#sources)<p>
- [Entity Relations](#entity-relations)

## Introduction ##

This REST-Api demo application created in order to learn and understand the mechanism of managing Authentication with 
usage <b>JWT</b> and Authorization implementing <b>PermissionEvaluator</b>. Most of examples in net use regular 
Java Collections to store data entities. It causes inability to write reliable tests. This certain example uses JPA module 
with Hibernate for data persisting in H2 Database. 

## Entity Relations ##

Imagine the organization which has several warehouses of different kind: CC, XD, TXD. Each warehouse has two types of 
trucks within it: TTT - for inbound and TPA - for outbound. Each truck has Manifest entities inside which represent a cargo
loaded into the trucks. This organization has several kinds of users with different authorities for each kind of warehouses.
Also these users has certain accesses for particular warehouses to process different CRUD actions. That's why PermissionEvaluator was used. 
All these entities: Warehouse, TPA, TTT, Manifest, User are being stored in H2. Only relations WarehousePermission are stored
in WarehousePermissionRepo with usage of the ArrayList. All relation between objects initiated in DbInit class. 
 
## Technologies ##

* Spring Security JWt
* Spring Security PermissionEvaluator
* Spring JPA Data
* Hibernate
* Maven
* Lombok
* Junit4 for tests

## Sources ##

The code was created using [rjozefowich](#https://github.com/rjozefowicz/spring-security) 
and [dangeabunea](#https://github.com/dangeabunea/RomanianCoderExamples) repositories.

