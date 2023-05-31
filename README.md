# GrowattDataScraper

A Java application that will read data from the Growatt API and write it to S3 storage. 

Intended to be run on a schedule to accumulate data over time (as Growatt only keeps a limited amount of back history) for use in dashboards or analytics.

Heavily based on the great work at [https://github.com/indykoning/PyPi_GrowattServer/tree/master](https://github.com/indykoning/PyPi_GrowattServer)
