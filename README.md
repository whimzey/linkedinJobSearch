# linkedinJobSearch
 
Based on https://www.scraperapi.com/blog/how-to-build-a-linkedin-scraper/

Change title and location, also if location is not US, you need to find geoId (try developer tools in browser while scrolling job search).
Code finds all jobs and writes title, company, location, posting date and link to a file. You may keep this file and rerun code as often as you wish so listing will be updated with new positions. All duplicated rows are deleted automatically and program will tell you how many new rows were added.

NB. I added filtering by words "load" and "performance" in title, because sometimes search contains irrelevant items. You may just delete it or change if you face same irrelevance issue.
