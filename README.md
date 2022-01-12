## Simple Json Flattener CLI

## Description
This is a simple json command line flattener. Accepts a file path and flattens the nested keys into
one flattened key -> value structure
Example: 

Input

```{
     "Order": {
       "Number": "SO43659",
       "Date": "2011-05-31T00:00:00"
     },
     "Product": {
       "source": "India",
       "SubProduct": {
         "type" : "grain",
         "price" : "12.3",
         "datepacked" : "12/12/21"
       }
     }
   }
```
Output

 ```
 {
  "Order.Date":"2011-05-31T00:00:00",
  "Product.SubProduct.type":"grain",
  "Order.Number":"SO43659",
  "Product.SubProduct.datepacked":"12/12/21",
  "Product.source":"India",
  "Product.SubProduct
 }
```
## Getting Started

### Dependencies

* JDK 15 or higher
* maven
* Ensure java and mvn is added to PATH
### Installing

* run mvn install


### Executing program

* How to run the program
* In termainal in linux or cmd in windows run the following commands from root of the project

Package the jar

**mvn package**

Windows:
 
runParser.cmd -f fullFilePath

For e.g.

**runParser.cmd -f D:\source\JsonParser\src\test\resources\data.json**

Linux:
 
./runParser.sh -f fullFilePath

For e.g.

**./runParser.sh -f /user/abc/JsonParser/src/test/resources/data.json**
### Running tests
mvn test

## Help

## Version History

* 1.0
    * Initial version of Json flattener
 ## License

This project is licensed under the [  ] License - see the LICENSE.md file for details

## Acknowledgments
 