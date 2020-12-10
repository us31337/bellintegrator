# Java SpringBoot CRUD API
Simple java business application with CRUD functional made for course.
This app can manage organisations, offices and users from ornganisations' offices.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

To run it you need to have
- Git  
- JDK 8 or later
- [Maven](https://maven.apache.org/) - Dependency Management

### Installing
 
 Just clone this repository to your IDE i.e from Git
 ```
 git clone https://github.com/us31337/bellintegrator
 ```
## Running the tests
 ```
mvn verify
 ```

## How to use
To run application type
```
mvn spring-boot:run
```

This app uses Swagger, you can see it on page
```
http://<yourhostname>:<port>/swagger-ui.html
```

### This app uses following agreements:

- All described return data types are in the data parameter. On error, the error parameter is returned.
- For example, if the request works correctly, the backend returns in the response body:

`{“data”:{out parameter}}`

- And if the request works wrong it returns
 
`{“error”:”error GUID; error text”}`

- If HTTP method not specified should use POST

### api/organization/list

```In (filter):
{
  “name”:” ”, //required
  “inn”:” ”,
  “isActive”:” ”
}
Out:
[
  {
    “id”:” ”,
    “name”:” ”,
    “isActive”:”true”
  },
  ...
]
```

### api/organization/{id}
#### method:GET
```
Out:
{
  “id”:” ”,
  “name”:” ”,
  “fullName”:” ”,
  “inn”:” ”,
  “kpp”:” ”,
  “address”:” ”,
  “phone”,” ”,
  “isActive”:”true”
}
```
### api/organization/update
```
In:
{
  “id”:” ”, //required
  “name”:” ”, //required
  “fullName”:” ”, //required
  “inn”:” ”, //required
  “kpp”:” ”,  //required
  “address”:” ”, //required
  “phone”,” ”,
  “isActive”:”true”
}

Out:
{
    “result”:”success”
}
```
### api/organization/save
```
In:
{
  “name”:” ”, //required
  “fullName”:” ”, //required
  “inn”:” ”, //required
  “kpp”:” ”, //required
  “address”:” ”, //required
  “phone”,” ”,
  “isActive”:”true”
}

Out:
{
    “result”:”success”
}
```
### api/office/list
```
In (filter):
{
  “orgId”:” ”, //required
  “name”:” ”,
  “phone”:” ”,
  “isActive” 
}

Out:
[
  {
    “id”:” ”,
    “name”:” ”,
    “isActive”:”true”
  },
  ...
]
```
### api/office/{id}
#### method:GET
```
Out:
{
  “id”:” ”,
  “name”:” ”,
  “address”:” ”,
  “phone”,” ”,
  “isActive”:”true”
}
```
### api/office/update
```
In:
{
  “id”:” ”, //required
  “name”:” ”, //required
  “address”:” ”, //required
  “phone”,” ”,
  “isActive”:”true” //пример
}

Out:
{
    “result”:”success”
}
```
### api/office/save
```
In:
{
  “orgId”:” ”, //required
  “name”:” ”,
  “address”:” ”,
  “phone”,” ”,
  “isActive”:”true”
}

Out:
{
    “result”:”success”
}
```
### api/user/list
```
In (фильтр):
{
  “officeId”:” ”, //required
  “firstName”:” ”,
  “lastName”:” ”,
  “middleName”:” ”,
  “position”,” ”,
  “docCode”:” ”,
  “citizenshipCode”:” ”
}
Out:
[
    {
      “id”:” ”,
      “firstName”:” ”,
      “lastName”:” ”,
      “middleName”:” ”,
      “position”:” ”
    },
    ...
]
```
### api/user/{id}
#### method:GET
```
Out:
{
  “id”:” ”,
  “firstName”:” ”,
  “lastName”:” ”,
  “middleName”:” ”,
  “position”:” ”
  “phone”,” ”,
  “docName”:” ”,
  “docNumber”:” ”,
  “docDate”:” ”,
  “citizenshipName”:” ”,
  “citizenshipCode”:” ”,
  “isIdentified”:”true”
}
```
### api/user/update
```
In:
{
  “id”:” ”, //required
  “officeId”:” ”,
  “firstName”:” ”, //required
  “lastName”:” ”,
  “middleName”:” ”,
  “position”:” ” //required
  “phone”,” ”,
  “docName”:” ”,
  “docNumber”:” ”,
  “docDate”:” ”,
  “citizenshipCode”:” ”,
  “isIdentified”:”true” //пример
}

Out:
{
    “result”:”success”
}
```
### api/user/save
```
In:
{
  “officeId”:” ”, //required
  “firstName”:” ”, //required
  “lastName”:” ”,
  “middleName”:” ”,
  “position”:” ” //required
  “phone”,” ”,
  “docCode”:” ”,
  “docName”:” ”,
  “docNumber”:” ”,
  “docDate”:” ”,
  “citizenshipCode”:” ”,
  “isIdentified”:”true” //пример
}
```
### api/docs
#### method:GET
`[
  {
    “name”:“Паспорт гражданина РФ”,
    “code”:”21”
  },
  ...
]`

### api/countries
`[
  {
    “name”:“Российская Федерация”,
    “code”:”643”
  },
  ...
]`






 
 


