![Header](src/main/resources/pictures/analytical-tool.jpeg)

# Analytical tool

##  Description
A small analytical program for processing data related to customer support

---
## Implementation details
- **Model** represents a set of data from a query
- All operations are available at the **Service**
- **FileReader** for reading data from file
- **RecordParser** is used for parsing data to Java object
- **RecordService** is responsible for processing data
- **FileWriter** for writing data to the output file
---

## Quickstart
1. Fork this repository
2. Clone the project to your computer 
3. Put your input file into **resources** folder. The file should be named **input.txt**.
You can also use the existing input.txt file for testing
4. Run AnalyticalApp
5. At the output, you will receive a file with processed data in txt format in the **resources** folder

### Please check the date formats in the input file queries. It should be **dd.MM.yyyy**

---

## Author

[Vitalii Shkliarskyi](https://github.com/VitaliiShkliarskyi)