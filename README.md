Project was developed for Google Chrome browser 
(css styles will not have correct interface view in another browsers).

For correct loading project you need:
1. Create DB using sql-scripts:
	artPortal_create_mysql.sql
	artPortal_insert_mysql.sql
2. Copy folder ArtPortalImages
3. Update file jdbc.properties (ArtPortal_Spring_mvc\src\main\resources), 
where artPortaFilesPath - new path to ArtPortalImages folder