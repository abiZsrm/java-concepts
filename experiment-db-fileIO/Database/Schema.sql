-- Create Database. 
CREATE DATABASE ExpFileIODB; 
USE ExpFileIODB; 

-- Create USER for Database. 
CREATE LOGIN FileIOUser WITH PASSWORD = 'FileIO@2021'; 

-- Learn to grant privelieges and make the user as sysadmin without relying on SSMS to do the same. 

-- Create a TABLE TO ACCEPT BLOBS.