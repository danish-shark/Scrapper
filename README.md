# Scrapper
A multi threaded jsoup based web scrapper using Hibernate,iText and Maven. Saves all the records in a text file,pdf file or/and a MySQL database.

# Input
Input is one or more html files containing all the links to be scanned. These files should be placed in the input directory. Current input is obtained from [here](http://www.edulix.com/unisearch/index.php). The parsing logic will need to be modified to work with other pages.

# Working
The input pages are parsed and the extracted links are scanned in parllel using a thread pool.

# Error logs
All the log files are produced using the default java logging utility and are placed in the logs directory.

## Timeout
Contains a list of links that timed out.

##DBLog
Contains all errors related to database operations.

##Misc
Contains all other misc errors.

# Output
A txt file,a pdf file and/or a MySQL data. The output files are stored in the output directory.

