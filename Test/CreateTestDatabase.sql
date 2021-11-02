USE [master]
GO	

IF NOT EXISTS (SELECT * FROM sys.Databases WHERE name = 'Java_Test_Server')
BEGIN
	CREATE DATABASE Java_Test_Server ON  PRIMARY 
	(	
	NAME = N'Java_Test_Server'
	, FILENAME = N'D:\MS SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Java_Test_Server.mdf' 
	, SIZE = 65536KB 
	, MAXSIZE = UNLIMITED
	, FILEGROWTH = 65536KB 
	)
	LOG ON 
	( 
	NAME = N'Java_Test_Server_log' 
	, FILENAME = N'D:\MS SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\Java_Test_Server_log.ldf' 
	, SIZE = 8192KB 
	, MAXSIZE = 2048GB 
	, FILEGROWTH = 65536KB 
	)
END;
GO